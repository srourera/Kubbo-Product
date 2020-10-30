package com.technicaltest.productservice.repository;

import com.technicaltest.productservice.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    Optional<ProductEntity> findById(Long productId);

    List<ProductEntity> findAll();

}