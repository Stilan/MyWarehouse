package com.example.mywarehouse.repository;

import com.example.mywarehouse.dto.ProductDocDto;
import com.example.mywarehouse.entity.Product;
import com.example.mywarehouse.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByIsDeletedFalse();

    Optional<Product> findByIdAndIsDeletedFalse(UUID id);

    List<Product> findAllByName(String name);


}
