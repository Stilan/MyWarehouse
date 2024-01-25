package com.example.mywarehouse.mapper;

import com.example.mywarehouse.dto.ProductStockIdDto;
import com.example.mywarehouse.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductStockIdMapper extends AbstractMapper<ProductStockIdDto, Product> {
}
