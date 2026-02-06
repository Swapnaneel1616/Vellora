package com.vellora.sb_ecom.controllers;

import com.vellora.sb_ecom.models.Category;
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
    public ResponseEntity<List<Category>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getCategories() , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category){
        return new ResponseEntity<>(categoryService.addCategory(category) , HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        return new ResponseEntity<>(categoryService.deleteCategory(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id ,
                                                 @Valid @RequestBody Category updatedCategory){
        Category savedCategory = categoryService.updateCategory(id , updatedCategory);
        return new ResponseEntity<>("Category is Created"+ savedCategory , HttpStatus.OK);
    }

}
