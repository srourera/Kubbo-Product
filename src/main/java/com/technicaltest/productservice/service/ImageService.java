package com.technicaltest.productservice.service;

import com.technicaltest.productservice.entity.ImageEntity;
import com.technicaltest.productservice.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public Long upload(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        ImageEntity imageEntity;
        try {
            imageEntity = new ImageEntity(fileName, file.getContentType(), file.getBytes());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid image file",e);
        }
        return imageRepository.save(imageEntity).getId();
    }

    public ImageEntity get(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    public void delete(Long imageId) {
        try {
            imageRepository.deleteById(imageId);
        } catch (Exception ignored) {
        }
    }
}
