package com.example.mywarehouse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id = UUID.randomUUID();

    private Integer article;

    private String name;

    private Integer lastPurchasePrice;

    private Integer lastSalePrice;

    private Integer remainingGoods;

    private boolean isDeleted = Boolean.FALSE;

    private UUID stockId;
}
