package com.example.imkercloudserver.mapper;

import com.example.imkercloudserver.controller.dto.UserDTO;
import com.example.imkercloudserver.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {
    @Mapping(target = "notifications", ignore = true)
    @Mapping(target = "beehives", ignore = true)
    User toEntity(UserDTO dto);

    UserDTO toDto(User entity);
}
