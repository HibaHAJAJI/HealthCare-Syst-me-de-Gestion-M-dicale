package org.example.healthcare.Mapper;


import org.example.healthcare.Dto.MedecinDto;
import org.example.healthcare.Entity.Medecin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel= "spring")
public interface MedecinMapper {

    @Mapping(target = "user.username", source = "username")
    @Mapping(target = "user.email", source = "email")
    @Mapping(target = "user.password", source = "password")
    @Mapping(target = "user.role", source = "role")
    Medecin toEntity(MedecinDto dto);

    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.role", target = "role")
    @Mapping(target = "password", ignore = true)
    MedecinDto toDto(Medecin medecin);


    @Mapping(target = "id",ignore = true)
    @Mapping(target = "user.username", source = "username")
    @Mapping(target = "user.email", source = "email")
    void updateMedecinDto (MedecinDto dto , @MappingTarget Medecin medecin);

}
