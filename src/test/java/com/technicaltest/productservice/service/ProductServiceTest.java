package com.technicaltest.productservice.service;

import com.technicaltest.productservice.BaseTest;
import com.technicaltest.productservice.dto.ProductData;
import com.technicaltest.productservice.entity.ProductEntity;
import com.technicaltest.productservice.facade.ProductFacade;
import com.technicaltest.productservice.mapper.ProductMapper;
import com.technicaltest.productservice.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(value = ProductService.class, controllers = ProductService.class)
public class ProductServiceTest extends BaseTest {

    @InjectMocks
    ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Before
    public void init() {
    }

    @Test
    public void test_getAll_success() {
        // Given
        List<ProductEntity> expectedResult = getProductEntityList();
        doReturn(getProductEntityList())
                .when(productRepository).findAll();
        // When
        List<ProductEntity> actualResult = productService.getAll();
        // Then
        verify(productRepository,times(1)).findAll();
        assertProductEntityList(actualResult, expectedResult);
    }

    @Test
    public void test_getById_success() {
        // Given
        ProductEntity expectedResult = getProductEntity();
        Optional<ProductEntity> existing = Optional.ofNullable(getProductEntity());
        doReturn(existing)
                .when(productRepository).findById(anyLong());
        // When
        ProductEntity actualResult = productService.getById(1L);
        // Then
        verify(productRepository,times(1)).findById(1L);
        assertProductEntity(actualResult, expectedResult);
    }

    @Test
    public void test_getById_returnNull_success() {
        // Given
        Optional<ProductEntity> existing = Optional.ofNullable(null);
        doReturn(existing)
                .when(productRepository).findById(anyLong());
        // When
        ProductEntity actualResult = productService.getById(1L);
        // Then
        verify(productRepository,times(1)).findById(1L);
        Assert.assertNull(actualResult);
    }

    @Test
    public void test_save_success() {
        // Given
        ProductEntity expectedResult = getProductEntity();
        doReturn(getProductEntity())
                .when(productRepository).save(any(ProductEntity.class));
        // When
        ProductEntity actualResult = productService.save(getProductEntity());
        // Then
        verify(productRepository,times(1)).save(any(ProductEntity.class));
        assertProductEntity(actualResult, expectedResult);
    }

    @Test
    public void test_delete_success() {
        // Given
        // When
        productService.delete(1L);
        // Then
        verify(productRepository,times(1)).deleteById(anyLong());
    }
}