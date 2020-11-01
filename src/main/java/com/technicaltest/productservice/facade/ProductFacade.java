package com.technicaltest.productservice.facade;

import com.technicaltest.productservice.dto.ProductData;
import com.technicaltest.productservice.entity.ProductEntity;
import com.technicaltest.productservice.mapper.ProductMapper;
import com.technicaltest.productservice.service.ImageService;
import com.technicaltest.productservice.service.ProductService;
import com.technicaltest.productservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductFacade {

    @Autowired
    private ProductService productService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ProductMapper productMapper;

    public List<ProductData> getAll() {
        List<ProductEntity> products = productService.getAll();
        return productMapper.productEntityListToProductDataList(products);
    }

    public ProductData getById(Long productId) {
        ProductEntity product = productService.getById(productId);
        return productMapper.productEntityToProductData(product);
    }

    public ProductData create(ProductData productData) {
        ProductEntity requestBody = productMapper.productDataToProductEntity(productData);
        if(!Utils.isEmpty(requestBody.getId())){
            requestBody.setId(null);
        }
        ProductEntity product = productService.save(requestBody);
        return productMapper.productEntityToProductData(product);
    }

    public ProductData edit(Long productId,ProductData productData) throws Exception {
        ProductEntity existing = productService.getById(productId);
        if(Utils.isEmpty(existing.getId())){
            throw new Exception("Product not found");
        }
        if(!Utils.isEmpty(existing.getImage()) && existing.getImage() != productData.getImage()){
            imageService.delete(existing.getImage());
        }
        ProductEntity requestBody = productMapper.productDataToProductEntity(productData);
        requestBody.setId(existing.getId());
        ProductEntity product = productService.save(requestBody);
        return productMapper.productEntityToProductData(product);
    }

    public void editEnabled(Long productId, Boolean enabled) throws Exception {
        ProductEntity existing = productService.getById(productId);
        if(Utils.isEmpty(existing.getId())){
            throw new Exception("Product not found");
        }
        existing.setEnabled(enabled);
        ProductEntity product = productService.save(existing);
    }

    public void delete(Long productId) {
        ProductEntity existing = productService.getById(productId);
        if(!Utils.isEmpty(existing)){
            if(!Utils.isEmpty(existing.getImage())){
                imageService.delete(existing.getImage());
            }
            productService.delete(productId);
        }
    }
}
