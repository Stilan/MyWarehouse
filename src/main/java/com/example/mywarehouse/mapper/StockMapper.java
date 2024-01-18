package com.example.mywarehouse.mapper;

import com.example.mywarehouse.dto.StockDto;
import com.example.mywarehouse.entity.Stock;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper extends AbstractMapper<StockDto, Stock> {


}
