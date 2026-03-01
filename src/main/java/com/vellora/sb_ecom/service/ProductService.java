package com.vellora.sb_ecom.service;

import com.vellora.sb_ecom.models.Product;
import com.vellora.sb_ecom.payload.ProductDTO;
import com.vellora.sb_ecom.payload.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    ProductDTO addProduct(Long categoryId, Product product);

    ProductResponse getAllProducts();

    ProductResponse searchByCategory(Long categoryId);

    ProductResponse searchProductByKeyword(String keyword);

    ProductDTO updateProduct(Long productId, Product product);

    ProductDTO deleteProduct(Long productId);
}
