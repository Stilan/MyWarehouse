package com.example.mywarehouse.mapper;

import com.example.mywarehouse.dto.LeftoversProductDto;
import com.example.mywarehouse.dto.ProductDocDto;
import com.example.mywarehouse.dto.ProductDto;
import com.example.mywarehouse.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends AbstractMapper<ProductDto, Product> {

    ProductDocDto toDocDto(Product product);

    LeftoversProductDto toLeftoversProduct(Product product);
}
