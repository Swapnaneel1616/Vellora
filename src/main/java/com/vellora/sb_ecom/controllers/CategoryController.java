package com.vellora.sb_ecom.controllers;

import com.vellora.sb_ecom.models.Category;
import com.vellora.sb_ecom.payload.CategoryDTO;
import com.vellora.sb_ecom.payload.CategoryResponse;
import com.vellora.sb_ecom.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/public/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<?> getAllCategories(){
        return new ResponseEntity<>(categoryService.getCategories() , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.addCategory(categoryDTO) , HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        return new ResponseEntity<>(categoryService.deleteCategory(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id ,
                                                 @Valid @RequestBody CategoryDTO updatedCategory){
        CategoryDTO savedCategoryDTO = categoryService.updateCategory(id , updatedCategory);
        return new ResponseEntity<>(savedCategoryDTO , HttpStatus.OK);
    }

}
