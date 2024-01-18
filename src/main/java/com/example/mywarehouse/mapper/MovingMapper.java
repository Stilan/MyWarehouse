package com.example.mywarehouse.mapper;

import com.example.mywarehouse.dto.MovingProductDto;
import com.example.mywarehouse.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovingMapper extends AbstractMapper<MovingProductDto, Product> {


}
