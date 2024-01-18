package com.example.mywarehouse.repository;

import com.example.mywarehouse.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StockRepository extends JpaRepository<Stock, UUID> {

    List<Stock> findAllByIsDeletedFalse();

    Optional<Stock> findByIdAndIsDeletedFalse(UUID id);

    List<Stock> findAllByName(String name);

    Stock findByName(String name);
}
