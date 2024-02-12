package com.example.mywarehouse.repository;

import com.example.mywarehouse.entity.StockProductQuantity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockProductQuantityRepository extends JpaRepository<StockProductQuantity, UUID> {
}
