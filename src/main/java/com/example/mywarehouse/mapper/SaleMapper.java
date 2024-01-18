package com.example.mywarehouse.mapper;

import com.example.mywarehouse.dto.SaleProductDto;
import com.example.mywarehouse.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaleMapper extends AbstractMapper<SaleProductDto, Product> {

}
