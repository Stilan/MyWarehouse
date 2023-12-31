package com.example.mywarehouse.entity;

import jakarta.persistence.*;
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
    @Column(nullable = false)
    private Integer article;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer lastPurchasePrice;
    @Column(nullable = false)
    private Integer lastSalePrice;
    private Integer remainingGoods;
    @Column(nullable = false)
    private boolean isDeleted = Boolean.FALSE;



}
