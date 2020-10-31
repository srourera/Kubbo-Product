package com.technicaltest.productservice.controller;

import com.technicaltest.productservice.dto.ProductData;
import com.technicaltest.productservice.facade.ProductFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ProductData> createProduct(@RequestBody ProductData product){
        return new ResponseEntity<>(productFacade.create(product),HttpStatus.CREATED);
    }

    @PutMapping(
            value = "/{productId}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ProductData> editProduct(@PathVariable Long productId, @RequestBody ProductData product) throws Exception {
        return new ResponseEntity<>(productFacade.edit(productId, product),HttpStatus.CREATED);
    }

    @PutMapping(
            value = "/activate/{productId}"
    )
    public ResponseEntity activateProduct(@PathVariable Long productId) throws Exception {
        productFacade.editEnabled(productId,true);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(
            value = "/deactivate/{productId}"
    )
    public ResponseEntity deactivateProduct(@PathVariable Long productId) throws Exception {
        productFacade.editEnabled(productId,false);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
