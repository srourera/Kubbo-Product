package com.technicaltest.productservice.controller;

import com.technicaltest.productservice.facade.ImageFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping(
        value = "/image",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ImageController {

    @Autowired
    ImageFacade imageFacade;

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    ResponseEntity<Long> uploadImage(@RequestPart("imageFile") MultipartFile file) throws IOException {
        return new ResponseEntity<>(imageFacade.upload(file),HttpStatus.CREATED);
    }

    @GetMapping(
            value = "/{imageId}"
    )
    ResponseEntity<byte[]> getImage(@PathVariable Long imageId) {
        return new ResponseEntity<>(imageFacade.get(imageId),HttpStatus.OK);
    }

    @DeleteMapping
    ResponseEntity deleteImage(@PathVariable Long imageId) {
        imageFacade.delete(imageId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
