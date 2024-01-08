package com.example.mywarehouse.mapper;

import com.example.mywarehouse.dto.StockDto;
import com.example.mywarehouse.entity.Stock;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper {

    StockDto toDto(Stock stock);
    Stock toEntity(StockDto stockDto);
}
