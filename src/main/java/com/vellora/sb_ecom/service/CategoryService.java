package com.vellora.sb_ecom.service;

import com.vellora.sb_ecom.Exceptions.APIExcepton;
import com.vellora.sb_ecom.Exceptions.ResourceNotFoundException;
import com.vellora.sb_ecom.models.Category;
import com.vellora.sb_ecom.payload.CategoryDTO;
import com.vellora.sb_ecom.payload.CategoryResponse;
import com.vellora.sb_ecom.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    public CategoryResponse getCategories(Integer pageNo , Integer pageSize) {

        Pageable pageDetails = PageRequest.of(pageNo,pageSize);
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);
        List<Category> categories = categoryPage.getContent();
        if(categories.isEmpty()){
            throw new APIExcepton("NO CATEGORY CREATED TILL NOW !!!!");
        }
        List<CategoryDTO> categoryDTOS = categories.stream().map(category -> modelMapper.map(category , CategoryDTO.class)).toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setLastPage(categoryPage.isLast());
        return categoryResponse;
    }
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO , Category.class);
        Category categoryFromDB = categoryRepository.findByCategoryName(category.getCategoryName());
        if(categoryFromDB!= null){
            throw new APIExcepton("Category with the name "+ category.getCategoryName()+ " already exists");
        }
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory , CategoryDTO.class);
    }
    public CategoryDTO deleteCategory(Long id) {


        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category" , "categoryId" , id));
        categoryRepository.delete((category));
        return modelMapper.map(category , CategoryDTO.class);
    }
    public CategoryDTO updateCategory(Long id, CategoryDTO updatedCategoryDTO) {
        Category savedCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category" , "categoryId" , id));
        Category updatedCategory = modelMapper.map(updatedCategoryDTO , Category.class);
        updatedCategory.setCategoryId(id);
        savedCategory = categoryRepository.save(updatedCategory);

        return modelMapper.map(savedCategory , CategoryDTO.class);
    }
}
