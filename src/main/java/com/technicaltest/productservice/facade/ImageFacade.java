package com.technicaltest.productservice.facade;

import com.technicaltest.productservice.entity.ImageEntity;
import com.technicaltest.productservice.service.ImageService;
import com.technicaltest.productservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ImageFacade {

    @Autowired
    private ImageService imageService;

    public Long upload(MultipartFile file) {
        return imageService.upload(file);
    }
    public byte[] get(Long imageId) {
        ImageEntity imageEntity = imageService.get(imageId);
        return !Utils.isEmpty(imageEntity) ? imageEntity.getData() : null;
    }
    public void delete(Long imageId) {
        imageService.delete(imageId);
    }
}
