package com.technicaltest.productservice.repository;

import com.technicaltest.productservice.entity.ImageEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ImageRepository extends CrudRepository<ImageEntity, Long> {

    Optional<ImageEntity> findByName(String name);

}