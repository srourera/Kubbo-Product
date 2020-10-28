package com.technicaltest.productservice.facade;

import com.technicaltest.productservice.dto.ProductData;
import com.technicaltest.productservice.entity.ProductEntity;
import com.technicaltest.productservice.mapper.ProductMapper;
import com.technicaltest.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductFacade {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    public List<ProductData> getAll() {
        List<ProductEntity> products = productService.getAll();
        return productMapper.productEntityListToProductDataList(products);
    }
}
