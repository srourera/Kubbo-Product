package com.technicaltest.productservice;

import com.technicaltest.productservice.dto.ProductData;
import com.technicaltest.productservice.entity.ProductEntity;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseTest {
    public ProductData getProductData(){
        ProductData productData = new ProductData();
        productData.setId(1L);
        productData.setName("ProductName");
        productData.setSku("ProductSku");
        productData.setBarcode("ProductBarcode");
        productData.setEnabled(true);
        productData.setImage(1L);
        productData.setPrice("35.00");
        return productData;
    }

    public List<ProductData> getProductDataList() {
        List<ProductData> productDataList = new ArrayList<>();
        productDataList.add(getProductData());
        return productDataList;
    }
    public ProductEntity getProductEntity(){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setName("ProductName");
        productEntity.setSku("ProductSku");
        productEntity.setBarcode("ProductBarcode");
        productEntity.setEnabled(true);
        productEntity.setImage(1L);
        productEntity.setPrice("35.00");
        return productEntity;
    }

    public List<ProductEntity> getProductEntityList() {
        List<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(getProductEntity());
        return productEntityList;
    }

    protected void assertProductDataList(List<ProductData> actual, List<ProductData> expected){
        Assert.assertEquals(actual.size(),expected.size());
        for(int i = 0; i < actual.size(); ++i){
            assertProductData(actual.get(0),expected.get(0));
        }
    }

    protected void assertProductData(ProductData actual, ProductData expected){
        Assert.assertEquals(actual.getId(),expected.getId());
        Assert.assertEquals(actual.getName(),expected.getName());
        Assert.assertEquals(actual.getBarcode(),expected.getBarcode());
        Assert.assertEquals(actual.getEnabled(),expected.getEnabled());
        Assert.assertEquals(actual.getImage(),expected.getImage());
        Assert.assertEquals(actual.getSku(),expected.getSku());
    }

    protected void assertProductEntityList(List<ProductEntity> actual, List<ProductEntity> expected){
        Assert.assertEquals(actual.size(),expected.size());
        for(int i = 0; i < actual.size(); ++i){
            assertProductEntity(actual.get(0),expected.get(0));
        }
    }

    protected void assertProductEntity(ProductEntity actual, ProductEntity expected){
        Assert.assertEquals(actual.getId(),expected.getId());
        Assert.assertEquals(actual.getName(),expected.getName());
        Assert.assertEquals(actual.getBarcode(),expected.getBarcode());
        Assert.assertEquals(actual.getEnabled(),expected.getEnabled());
        Assert.assertEquals(actual.getImage(),expected.getImage());
        Assert.assertEquals(actual.getSku(),expected.getSku());
    }
}
