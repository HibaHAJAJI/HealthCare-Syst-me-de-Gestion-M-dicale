package org.example.healthcare.Auth;


import org.example.healthcare.Dto.UserDto;
import org.example.healthcare.Entity.User;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDto dto);
    UserDto toDto(User user);
}
