package com.technicaltest.productservice.repository;

import com.technicaltest.productservice.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {

    List<ProductEntity> findAll();

}