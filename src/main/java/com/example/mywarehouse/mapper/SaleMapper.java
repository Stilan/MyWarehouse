package com.example.mywarehouse.mapper;

import com.example.mywarehouse.dto.MovingDto;
import com.example.mywarehouse.dto.SaleDto;
import com.example.mywarehouse.entity.Document;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaleMapper {

    SaleDto toDto(Document document);

    Document toEntity(SaleDto dto);
}
