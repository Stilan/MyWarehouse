package com.example.mywarehouse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private int article;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int lastPurchasePrice;
    @Column(nullable = false)
    private int lastSalePrice;
}
