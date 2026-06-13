package org.example.healthcare.Users;

import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto dto);
    UserDto toDto(User user);
}
