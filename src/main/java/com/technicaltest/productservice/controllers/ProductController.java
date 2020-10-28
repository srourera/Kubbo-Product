package com.technicaltest.productservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductController {

    private static final String SERVICE_NAME = "Product Service";
    private static final String RESPONSE_EXAMPLE = "Hello world!";

    @GetMapping
    ResponseEntity<Map<String, String>> getProducts(){
        Map<String, String> body = new HashMap<>();
        body.put(SERVICE_NAME, RESPONSE_EXAMPLE);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
