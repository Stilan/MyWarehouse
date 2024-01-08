package com.example.mywarehouse.dto;

import lombok.Data;

@Data
public class ProductDto {

    private Integer article;

    private String name;

    private Integer lastPurchasePrice;

    private Integer lastSalePrice;

    private Integer remainingGoods;
}
