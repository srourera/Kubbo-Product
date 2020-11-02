package com.technicaltest.productservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technicaltest.productservice.BaseTest;
import com.technicaltest.productservice.dto.ProductData;
import com.technicaltest.productservice.facade.ProductFacade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = ProductController.class, controllers = ProductController.class)
public class ProductControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductFacade productFacade;

    private ObjectMapper mapper;

    @Before
    public void init(){
        mapper = new ObjectMapper();
    }

    @Test
    public void test_invalidMethod() throws Exception {
        // Given
        // When
        MvcResult result = mockMvc.perform(put("/"))
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }

    @Test
    public void test_notFound() throws Exception {
        // Given
        // When
        MvcResult result = mockMvc.perform(post("/test/notfound"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void test_getProducts_success() throws Exception {
        // Given
        List<ProductData> expectedResponse = getProductDataList();
        doReturn(getProductDataList())
                .when(productFacade).getAll();
        // When
        MvcResult result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();
        // Then
        List<ProductData> actualResponse = mapper.readValue(result.getResponse().getContentAsString(),new TypeReference<>(){});
        assertProductDataList(actualResponse,expectedResponse);
    }

    @Test
    public void test_getProductById_success() throws Exception {
        // Given
        ProductData expectedResponse = getProductData();
        doReturn(getProductData())
                .when(productFacade).getById(1L);
        // When
        MvcResult result = mockMvc.perform(get("/1"))
                .andExpect(status().isOk())
                .andReturn();
        // Then
        ProductData actualResponse = mapper.readValue(result.getResponse().getContentAsString(),new TypeReference<>(){});
        assertProductData(actualResponse,expectedResponse);
    }

    @Test
    public void test_getProductById_failure() throws Exception {
        // Given
        doThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST))
                .when(productFacade).getById(1L);
        // When
        MvcResult result = mockMvc.perform(get("/1"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void test_createProduct_success() throws Exception {
        // Given
        ProductData expectedResponse = getProductData();
        String requestBody = mapper.writeValueAsString(getProductData());
        doReturn(getProductData())
                .when(productFacade).create(any(ProductData.class));
        // When
        MvcResult result = mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated())
                .andReturn();
        // Then
        ProductData actualResponse = mapper.readValue(result.getResponse().getContentAsString(),new TypeReference<>(){});
        assertProductData(actualResponse,expectedResponse);
    }

    @Test
    public void test_editProduct_success() throws Exception {
        // Given
        ProductData expectedResponse = getProductData();
        String requestBody = mapper.writeValueAsString(getProductData());
        doReturn(getProductData())
                .when(productFacade).edit(anyLong(),any(ProductData.class));
        // When
        MvcResult result = mockMvc.perform(put("/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated())
                .andReturn();
        // Then
        ProductData actualResponse = mapper.readValue(result.getResponse().getContentAsString(),new TypeReference<>(){});
        assertProductData(actualResponse,expectedResponse);
    }

    @Test
    public void test_editProduct_failure() throws Exception {
        // Given
        String requestBody = mapper.writeValueAsString(getProductData());
        doThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST))
                .when(productFacade).edit(anyLong(),any(ProductData.class));
        // When
        MvcResult result = mockMvc.perform(put("/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void test_activateProduct_success() throws Exception {
        // Given
        // When
        MvcResult result = mockMvc.perform(put("/activate/1"))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void test_activateProduct_failure() throws Exception {
        // Given
        doThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST))
                .when(productFacade).editEnabled(anyLong(),anyBoolean());
        // When
        MvcResult result = mockMvc.perform(put("/activate/1"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void test_deactivateProduct_success() throws Exception {
        // Given
        // When
        MvcResult result = mockMvc.perform(put("/deactivate/1"))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void test_deactivateProduct_failure() throws Exception {
        // Given
        doThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST))
                .when(productFacade).editEnabled(anyLong(),anyBoolean());
        // When
        MvcResult result = mockMvc.perform(put("/deactivate/1"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void test_deleteProduct_success() throws Exception {
        // Given
        // When
        MvcResult result = mockMvc.perform(delete("/1"))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void test_deleteProduct_failure() throws Exception {
        // Given
        doThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST))
                .when(productFacade).delete(anyLong());
        // When
        MvcResult result = mockMvc.perform(delete("/1"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}
