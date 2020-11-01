package com.technicaltest.productservice.service;

import com.technicaltest.productservice.entity.ImageEntity;
import com.technicaltest.productservice.entity.ProductEntity;
import com.technicaltest.productservice.repository.ImageRepository;
import com.technicaltest.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public Long upload(MultipartFile file) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        ImageEntity imageEntity = new ImageEntity(fileName, file.getContentType(), file.getBytes());

        return imageRepository.save(imageEntity).getId();
    }

    public ImageEntity get(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    public void delete(Long imageId) {
        imageRepository.deleteById(imageId);
    }
}
