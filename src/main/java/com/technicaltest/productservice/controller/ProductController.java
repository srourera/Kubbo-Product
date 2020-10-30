package com.technicaltest.productservice.controller;

import com.technicaltest.productservice.dto.ProductData;
import com.technicaltest.productservice.facade.ProductFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private ProductFacade productFacade;

    @GetMapping
    ResponseEntity<List<ProductData>> getProducts(){
        return new ResponseEntity<>(productFacade.getAll(), HttpStatus.OK);
    }

    @GetMapping(
            value = "/{productId}"
    )
    ResponseEntity<ProductData> getProductById(@PathVariable Long productId){
        return new ResponseEntity<>(productFacade.getById(productId), HttpStatus.OK);
    }
}
