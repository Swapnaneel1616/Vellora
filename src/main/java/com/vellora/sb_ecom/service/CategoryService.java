package com.vellora.sb_ecom.service;

import com.vellora.sb_ecom.Exceptions.APIExcepton;
import com.vellora.sb_ecom.Exceptions.ResourceNotFoundException;
import com.vellora.sb_ecom.models.Category;
import com.vellora.sb_ecom.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
    public String addCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedCategory!= null){
            throw new APIExcepton("Category with the name "+ category.getCategoryName()+ " already exists");
        }
        categoryRepository.save(category);
        return "Category Added Successfully";
    }
    public String deleteCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category" , "categoryId" , id));
        categoryRepository.delete((category));
        return "CATEGORY with ID" + id + "is DELETED";
    }
    public Category updateCategory(Long id, Category updatedCategory) {
        Category savedCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category" , "categoryId" , id));
        updatedCategory.setCategoryId(id);
        savedCategory = categoryRepository.save(updatedCategory);
        return savedCategory;
    }
}
