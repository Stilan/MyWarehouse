package com.example.mywarehouse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id = UUID.randomUUID();
    @Column(nullable = false)
    private Integer article;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer lastPurchasePrice;
    @Column(nullable = false)
    private int lastSalePrice;
    //    а зачем lastSalePrice ?
    // Товар (Артикул, Наименование, Цена последней закупки, Цена последней продажи). Там поля по заданию

}
