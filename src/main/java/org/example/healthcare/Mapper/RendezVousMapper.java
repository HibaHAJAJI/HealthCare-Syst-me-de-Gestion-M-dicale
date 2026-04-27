package org.example.healthcare.Mapper;


import org.example.healthcare.Dto.RendezVousDto;
import org.example.healthcare.Entity.RendezVous;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel= "spring")
public interface RendezVousMapper {

   @Mapping(source = "medecinId" ,target="medecin.id")
   @Mapping(source = "patientId",target="patient.id")
    RendezVous toEntity(RendezVousDto dto);

    @Mapping(source = "medecin.id" ,target="medecinId")
    @Mapping(source = "patient.id",target="patientId")
    RendezVousDto toDto(RendezVous rendezVous);

    List<RendezVousDto>toDtos(List<RendezVous>rendezVous);

    @Mapping(target = "id",ignore = true)
    void updateRendezVous(RendezVousDto dto,@MappingTarget RendezVous rendezVous);

}
