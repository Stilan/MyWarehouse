package com.example.mywarehouse.mapper;

import com.example.mywarehouse.dto.MovingProductStockDto;
import com.example.mywarehouse.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovingProductStockMapper extends AbstractMapper<MovingProductStockDto, Product> {
}
