package com.technicaltest.productservice.facade;

import com.technicaltest.productservice.BaseTest;
import com.technicaltest.productservice.dto.ProductData;
import com.technicaltest.productservice.entity.ProductEntity;
import com.technicaltest.productservice.mapper.ProductMapper;
import com.technicaltest.productservice.service.ImageService;
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
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(value = ProductFacade.class, controllers = ProductFacade.class)
public class ProductFacadeTest extends BaseTest {

    @InjectMocks
    ProductFacade productFacade;

    @Mock
    private ProductService productService;

    @Mock
    private ImageService imageService;

    @Mock
    private ProductMapper productMapper;

    @Before
    public void init() {
    }

    @Test
    public void test_getAll_success() {
        // Given
        List<ProductData> expectedResult = getProductDataList();
        doReturn(getProductEntityList())
                .when(productService).getAll();
        doReturn(getProductDataList())
                .when(productMapper).productEntityListToProductDataList(any());
        // When
        List<ProductData> actualResult = productFacade.getAll();
        // Then
        verify(productService,times(1)).getAll();
        verify(productMapper,times(1)).productEntityListToProductDataList(any());
        assertProductDataList(actualResult, expectedResult);
    }

    @Test
    public void test_getById_success() {
        // Given
        ProductData expectedResult = getProductData();
        doReturn(getProductEntity())
                .when(productService).getById(anyLong());
        doReturn(getProductData())
                .when(productMapper).productEntityToProductData(any(ProductEntity.class));
        // When
        ProductData actualResult = productFacade.getById(1L);
        // Then
        verify(productService,times(1)).getById(1L);
        verify(productMapper,times(1)).productEntityToProductData(any(ProductEntity.class));
        assertProductData(actualResult, expectedResult);
    }

    @Test(expected = ResponseStatusException.class)
    public void test_getById_failure() {
        // Given
        doReturn(null)
                .when(productService).getById(anyLong());
        // When
        ProductData actualResult = productFacade.getById(1L);
        // Then
        verify(productService,times(1)).getById(1L);
    }

    @Test
    public void test_create_success() {
        // Given
        ProductData expectedResult = getProductData();
        ProductData requestBody = getProductData();
        doReturn(getProductEntity())
                .when(productMapper).productDataToProductEntity(any(ProductData.class));
        doReturn(getProductEntity())
                .when(productService).save(any(ProductEntity.class));
        doReturn(getProductData())
                .when(productMapper).productEntityToProductData(any(ProductEntity.class));
        // When
        ProductData actualResult = productFacade.create(requestBody);
        // Then
        verify(productMapper,times(1)).productDataToProductEntity(any(ProductData.class));
        verify(productService,times(1)).save(any(ProductEntity.class));
        verify(productMapper,times(1)).productEntityToProductData(any(ProductEntity.class));
        assertProductData(actualResult, expectedResult);
    }

    @Test
    public void test_edit_success() {
        // Given
        ProductData expectedResult = getProductData();
        ProductData requestBody = getProductData();
        ProductEntity existing = getProductEntity();
        existing.setImage(null);
        doReturn(existing)
                .when(productService).getById(anyLong());
        doReturn(getProductEntity())
                .when(productMapper).productDataToProductEntity(any(ProductData.class));
        doReturn(getProductEntity())
                .when(productService).save(any(ProductEntity.class));
        doReturn(getProductData())
                .when(productMapper).productEntityToProductData(any(ProductEntity.class));
        // When
        ProductData actualResult = productFacade.edit(1L,requestBody);
        // Then
        verify(productService,times(1)).getById(1L);
        verify(imageService,times(0)).delete(anyLong());
        verify(productMapper,times(1)).productDataToProductEntity(any(ProductData.class));
        verify(productService,times(1)).save(any(ProductEntity.class));
        verify(productMapper,times(1)).productEntityToProductData(any(ProductEntity.class));
        assertProductData(actualResult, expectedResult);
    }

    @Test
    public void test_edit_withImage_success() {
        // Given
        ProductData expectedResult = getProductData();
        ProductData requestBody = getProductData();
        ProductEntity existing = getProductEntity();
        existing.setImage(2L);
        doReturn(existing)
                .when(productService).getById(anyLong());
        doReturn(getProductEntity())
                .when(productMapper).productDataToProductEntity(any(ProductData.class));
        doReturn(getProductEntity())
                .when(productService).save(any(ProductEntity.class));
        doReturn(getProductData())
                .when(productMapper).productEntityToProductData(any(ProductEntity.class));
        // When
        ProductData actualResult = productFacade.edit(1L,requestBody);
        // Then
        verify(productService,times(1)).getById(1L);
        verify(imageService,times(1)).delete(2L);
        verify(productMapper,times(1)).productDataToProductEntity(any(ProductData.class));
        verify(productService,times(1)).save(any(ProductEntity.class));
        verify(productMapper,times(1)).productEntityToProductData(any(ProductEntity.class));
        assertProductData(actualResult, expectedResult);
    }

    @Test(expected = ResponseStatusException.class)
    public void test_edit_failure() {
        // Given
        ProductData requestBody = getProductData();
        doReturn(null)
                .when(productService).getById(anyLong());
        // When
        ProductData actualResult = productFacade.edit(1L,requestBody);
        // Then
        verify(productService,times(1)).getById(1L);
    }

    @Test
    public void test_editEnabled_success() {
        // Given
        doReturn(getProductEntity())
                .when(productService).getById(anyLong());
        doReturn(getProductEntity())
                .when(productService).save(any(ProductEntity.class));
        // When
        productFacade.editEnabled(1L,true);
        // Then
        verify(productService,times(1)).getById(1L);
        verify(productService,times(1)).save(any(ProductEntity.class));
    }

    @Test(expected = ResponseStatusException.class)
    public void test_editEnabled_failure() {
        // Given
        doReturn(null)
                .when(productService).getById(anyLong());
        // When
        productFacade.editEnabled(1L,true);
        // Then
        verify(productService,times(1)).getById(1L);
        verify(productService,times(0)).save(any(ProductEntity.class));
    }

    @Test
    public void test_delete_success() {
        // Given
        ProductEntity existing = getProductEntity();
        existing.setImage(null);
        doReturn(existing)
                .when(productService).getById(anyLong());
        // When
        productFacade.delete(1L);
        // Then
        verify(productService,times(1)).getById(1L);
        verify(imageService,times(0)).delete(anyLong());
        verify(productService,times(1)).delete(anyLong());
    }

    @Test
    public void test_delete_withImage_success() {
        // Given
        ProductEntity existing = getProductEntity();
        existing.setImage(2L);
        doReturn(existing)
                .when(productService).getById(anyLong());
        // When
        productFacade.delete(1L);
        // Then
        verify(productService,times(1)).getById(1L);
        verify(imageService,times(1)).delete(2L);
        verify(productService,times(1)).delete(anyLong());
    }

    @Test(expected = ResponseStatusException.class)
    public void test_delete_failure() {
        // Given
        doReturn(null)
                .when(productService).getById(anyLong());
        // When
        productFacade.delete(1L);
        // Then
        verify(productService,times(1)).getById(1L);
        verify(productService,times(0)).save(any(ProductEntity.class));
    }

}