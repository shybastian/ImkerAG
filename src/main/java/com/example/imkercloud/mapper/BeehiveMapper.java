package com.example.imkercloud.mapper;

import com.example.imkercloud.controller.dto.BeehiveDTO;
import com.example.imkercloud.repository.entity.Beehive;
import org.mapstruct.Mapper;

@Mapper
public interface BeehiveMapper {
    Beehive toEntity(BeehiveDTO dto);

    BeehiveDTO toDto(Beehive entity);
}
