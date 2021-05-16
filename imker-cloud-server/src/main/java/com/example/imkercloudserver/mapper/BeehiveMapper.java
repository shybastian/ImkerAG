package com.example.imkercloudserver.mapper;

import com.example.imkercloudserver.controller.dto.BeehiveDTO;
import com.example.imkercloudserver.repository.entity.Beehive;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BeehiveMapper {
    @Mapping(target = "users", ignore = true)
    Beehive toEntity(BeehiveDTO dto);

    BeehiveDTO toDto(Beehive entity);
}
