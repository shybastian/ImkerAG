package com.example.imkercloudserver.mapper;

import com.example.imkercloudserver.controller.dto.BeehiveDTO;
import com.example.imkercloudserver.repository.entity.Beehive;
import org.mapstruct.Mapper;

@Mapper
public interface BeehiveMapper {
    Beehive toEntity(BeehiveDTO dto);

    BeehiveDTO toDto(Beehive entity);
}
