package com.example.mywarehouse.dto;

import com.example.mywarehouse.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class MovingDto {

    private Integer documentNumber;

    private UUID stockId;

    private UUID loadingWarehouseId;

    private List<Product> products;

}
