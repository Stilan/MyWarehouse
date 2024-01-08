package com.example.mywarehouse.mapper;

import com.example.mywarehouse.dto.MovingDto;
import com.example.mywarehouse.entity.Document;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovingMapper {

    MovingDto toDto(Document document);

    Document toEntity(MovingDto dto);
}
