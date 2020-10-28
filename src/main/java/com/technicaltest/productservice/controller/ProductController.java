package com.technicaltest.productservice.controller;

import com.technicaltest.productservice.dto.ProductData;
import com.technicaltest.productservice.facade.ProductFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductFacade productFacade;

    @GetMapping
    ResponseEntity<List<ProductData>> getProducts(){
        return new ResponseEntity<>(productFacade.getAll(), HttpStatus.OK);
    }
}
