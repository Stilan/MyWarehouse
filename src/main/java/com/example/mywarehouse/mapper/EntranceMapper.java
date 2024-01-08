package com.example.mywarehouse.mapper;

import com.example.mywarehouse.dto.EntranceDto;
import com.example.mywarehouse.entity.Document;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntranceMapper {


    EntranceDto toDto(Document document);

    Document toEntity(EntranceDto dto);
}
