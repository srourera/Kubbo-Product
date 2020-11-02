package com.technicaltest.productservice.facade;

import com.technicaltest.productservice.BaseTest;
import com.technicaltest.productservice.dto.ProductData;
import com.technicaltest.productservice.entity.ProductEntity;
import com.technicaltest.productservice.mapper.ProductMapper;
import com.technicaltest.productservice.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(value = ProductFacade.class, controllers = ProductFacade.class)
public class ProductFacadeTest extends BaseTest {

    @InjectMocks
    ProductFacade productFacade;

    @Mock
    private ProductService productService;

    @Mock
    private ProductMapper productMapper;

    @Before
    public void init() {
    }

    @Test
    public void test_getAll_success() {
        // Given
        List<ProductData> expectedResult = getProductDataList();
        when(productService.getAll()).thenReturn(getProductEntityList());
        when(productMapper.productEntityListToProductDataList(any())).thenReturn(getProductDataList());
        // When
        List<ProductData> actualResult = productFacade.getAll();
        // Then
        assertProductDataList(actualResult, expectedResult);
    }

    @Test
    public void test_getById_success() {
        // Given
        ProductData expectedResult = getProductData();
        when(productService.getById(anyLong())).thenReturn(getProductEntity());
        when(productMapper.productEntityToProductData(any(ProductEntity.class))).thenReturn(getProductData());
        // When
        ProductData actualResult = productFacade.getById(1L);
        // Then
        assertProductData(actualResult, expectedResult);
    }

    @Test(expected = ResponseStatusException.class)
    public void test_getById_failure() {
        // Given
        when(productService.getById(anyLong())).thenReturn(null);
        // When
        ProductData actualResult = productFacade.getById(1L);
    }
}