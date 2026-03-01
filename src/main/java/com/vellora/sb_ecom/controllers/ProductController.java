package com.vellora.sb_ecom.controllers;

import com.vellora.sb_ecom.models.Product;
import com.vellora.sb_ecom.payload.ProductDTO;
import com.vellora.sb_ecom.payload.ProductResponse;
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

    @GetMapping("public/products")
    public ResponseEntity<ProductResponse> getAllProducts(){
        ProductResponse productResponse = productService.getAllProducts();
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getProudctByCategory(@PathVariable Long categoryId){
        ProductResponse productResponse = productService.searchByCategory(categoryId);
        return new ResponseEntity<>(productResponse , HttpStatus.OK);
    }

    @GetMapping("public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductsByKeyword(@PathVariable String keyword){
        ProductResponse productResponse = productService.searchProductByKeyword(keyword);
        return new ResponseEntity<>(productResponse , HttpStatus.FOUND);
    }

    @PutMapping("admin/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody Product product,
                                                    @PathVariable Long productId){
        ProductDTO productDTO = productService.updateProduct(productId , product);
        return new ResponseEntity<>(productDTO , HttpStatus.OK);
    }

    @DeleteMapping("admin/products/{productId}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long productId){
        ProductDTO productDTO = productService.deleteProduct(productId);
        return new ResponseEntity<>(productDTO , HttpStatus.OK);
    }

}
