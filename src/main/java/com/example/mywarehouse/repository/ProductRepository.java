package com.example.mywarehouse.repository;

import com.example.mywarehouse.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByIsDeletedFalse();

    Optional<Product> findByIdAndIsDeletedFalse(UUID id);

    List<Product> findAllByName(String name);


}
