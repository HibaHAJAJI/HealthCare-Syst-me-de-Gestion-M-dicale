package org.example.healthcare.Mapper;


import org.example.healthcare.Dto.MedecinDto;
import org.example.healthcare.Entity.Medecin;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel= "spring")
public interface MedecinMapper {

    Medecin toEntity(MedecinDto dto);

    MedecinDto toDto(Medecin medecin);

    List<MedecinDto>toDtos(List<Medecin>medecins);
}
