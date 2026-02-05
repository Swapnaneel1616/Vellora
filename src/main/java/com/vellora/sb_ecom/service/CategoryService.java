package com.vellora.sb_ecom.service;

import com.vellora.sb_ecom.models.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final List<Category> categoryList = new ArrayList<>();
    private Long categoryIDCounter = 1L;

    public List<Category> getCategories() {
        return categoryList;
    }


    public String addCategory(Category category) {
        category.setCategoryId(categoryIDCounter++);
        categoryList.add(category);
        return "Category Added Successfully";
    }

    public String deleteCategory(Long id) {
        Category category = categoryList.stream()
                .filter(c -> c.getCategoryId().equals(id))
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Rersource Not Found"));
        categoryList.remove(category);
        return "Category Deleted Successfully";
    }

    public Category updateCategory(Long id, Category updatedCategory) {
        Optional<Category> categoryOptional = categoryList.stream().
                filter(c->c.getCategoryId().equals(id))
                .findFirst();
        if(categoryOptional.isPresent()){
            Category existingcategory = categoryOptional.get();
            existingcategory.setCategoryName(updatedCategory.getCategoryName());
            return existingcategory;
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Category Not Found");
        }
    }
}
