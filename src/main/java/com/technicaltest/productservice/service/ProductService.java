package com.technicaltest.productservice.service;

import com.technicaltest.productservice.entity.ProductEntity;
import com.technicaltest.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    public ProductEntity getById(Long productId) {
        return productRepository.findById(productId).orElse(new ProductEntity());
    }

    public ProductEntity save(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }
}
