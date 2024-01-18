package com.example.mywarehouse.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class SaleProductDto {

    private UUID id;

    private String name;

    private Integer article;

    private Integer lastSalePrice;

    private Integer remainingGoods;



}
