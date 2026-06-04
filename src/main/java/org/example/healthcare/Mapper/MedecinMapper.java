package org.example.healthcare.Mapper;


import org.example.healthcare.Dto.MedecinDto;
import org.example.healthcare.Entity.Medecin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel= "spring")
public interface MedecinMapper {

    @Mapping(target = "id", ignore = true)
    Medecin toEntity(MedecinDto dto);


    MedecinDto toDto(Medecin medecin);


    @Mapping(target = "id",ignore = true)
    void updateMedecinDto (MedecinDto dto , @MappingTarget Medecin medecin);

}
