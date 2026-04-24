package org.example.healthcare.Mapper;


import org.example.healthcare.Dto.RendezVousDto;
import org.example.healthcare.Entity.RendezVous;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel= "string")
public interface RendezVousMapper {

    RendezVous toEntity(RendezVousDto dto);

    RendezVousDto toDto(RendezVous rendezVous);

    List<RendezVousDto>toDtos(List<RendezVous>rendezVous);

    void updateRendezVous(RendezVousDto dto,@MappingTarget RendezVous rendezVous);

}
