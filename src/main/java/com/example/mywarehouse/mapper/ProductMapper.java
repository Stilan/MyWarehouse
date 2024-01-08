package com.example.mywarehouse.mapper;

import com.example.mywarehouse.dto.ProductDocDto;
import com.example.mywarehouse.dto.ProductDto;
import com.example.mywarehouse.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(Product product);
    Product toEntity(ProductDto productDto);

    ProductDocDto toDocDto(Product product);
}
