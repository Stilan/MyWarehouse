package com.example.mywarehouse.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class StockDto {

    @NotBlank
    private String name;

}
