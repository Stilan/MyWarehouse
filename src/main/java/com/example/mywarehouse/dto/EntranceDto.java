package com.example.mywarehouse.dto;

import com.example.mywarehouse.entity.Product;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class EntranceDto {

    private Integer documentNumber;

    private UUID stockId;

    private List<Product> products;

}
