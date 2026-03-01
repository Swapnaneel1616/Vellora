package com.vellora.sb_ecom.controllers;

import com.vellora.sb_ecom.models.Product;
import com.vellora.sb_ecom.payload.ProductDTO;
import com.vellora.sb_ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody Product product , @PathVariable Long categoryId){

        ProductDTO productDTO = productService.addProduct(categoryId , product);
        return new ResponseEntity<>(productDTO , HttpStatus.CREATED);
    }
}
