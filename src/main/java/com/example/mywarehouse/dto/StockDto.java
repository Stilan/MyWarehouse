package com.example.mywarehouse.dto;

import com.example.mywarehouse.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class StockDto {

    private String name;

    private List<Product> products;
}
