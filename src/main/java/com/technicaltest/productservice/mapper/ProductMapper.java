package com.technicaltest.productservice.mapper;

import com.technicaltest.productservice.dto.ProductData;
import com.technicaltest.productservice.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductMapper {
    ProductData productEntityToProductData(ProductEntity productEntity);
    ProductEntity productDataToProductEntity(ProductData productData);

    List<ProductData> productEntityListToProductDataList(List<ProductEntity> productEntityList);
    List<ProductEntity> productDataListToProductEntityList(List<ProductData> productDataList);
}