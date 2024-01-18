package com.example.mywarehouse.mapper;

import com.example.mywarehouse.dto.EntranceProductDto;
import com.example.mywarehouse.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntranceMapper extends AbstractMapper<EntranceProductDto, Product> {

}
