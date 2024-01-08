package com.example.mywarehouse.dto;

import com.example.mywarehouse.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
public class SaleDto {

    private Integer documentNumber;

    private UUID stockId;

    private List<Product> products;

}
