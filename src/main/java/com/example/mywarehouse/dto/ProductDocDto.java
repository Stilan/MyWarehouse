package com.example.mywarehouse.dto;

import lombok.Data;

@Data
public class ProductDocDto {

    private Integer article;

    private String name;

    private Integer lastPurchasePrice;
}
