package com.example.mywarehouse.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductStockIdDto {

    private UUID id;

    private String name;

    private Integer article;

    private UUID stockId;

    private Integer lastPurchasePrice;

    private Integer remainingGoods;

}
