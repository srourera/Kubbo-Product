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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;

import static org.mockito.Mockito.when;
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
    public void test_getProducts_success() throws Exception {
        // Given
        List<ProductData> expectedResponse = getProductDataList();
        when(productFacade.getAll()).thenReturn(getProductDataList());
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
        when(productFacade.getById(1L)).thenReturn(getProductData());
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
        ProductData expectedResponse = getProductData();
        when(productFacade.getById(1L)).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));
        // When
        MvcResult result = mockMvc.perform(get("/1"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}
