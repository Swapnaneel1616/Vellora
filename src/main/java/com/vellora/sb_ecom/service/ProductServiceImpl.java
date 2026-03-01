package com.vellora.sb_ecom.service;

import com.vellora.sb_ecom.Exceptions.ResourceNotFoundException;
import com.vellora.sb_ecom.models.Category;
import com.vellora.sb_ecom.models.Product;
import com.vellora.sb_ecom.payload.ProductDTO;
import com.vellora.sb_ecom.repositories.CategoryRepository;
import com.vellora.sb_ecom.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ProductDTO addProduct(Long categoryId, Product product) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","categoryId", categoryId));
        product.setCategory(category);
        product.setImage("default.page");
        double specialPrice = product.getPrice() - ((product.getDiscount()*0.01) * product.getPrice());
        product.setSpecialPrice(specialPrice);
        Product savedProduct = productRepository.save(product);

        return modelMapper.map(savedProduct , ProductDTO.class);
    }
}
