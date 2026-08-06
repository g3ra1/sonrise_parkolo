package org.example.sonrise_parkolo.mapper;

import org.example.sonrise_parkolo.dto.input.UserInputDTO;
import org.example.sonrise_parkolo.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserInputDTO userInputDTO);
}
