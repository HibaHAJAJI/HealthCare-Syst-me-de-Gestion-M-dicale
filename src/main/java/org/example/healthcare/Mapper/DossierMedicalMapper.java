package org.example.healthcare.Mapper;

import org.example.healthcare.Dto.DossierMedicalDto;
import org.example.healthcare.Entity.DossierMedical;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DossierMedicalMapper {

    @Mapping(source = "patientId",target = "patient.id")
    DossierMedical toEntity(DossierMedicalDto dto);

    @Mapping(source = "patient.id",target = "patientId")
    DossierMedicalDto toDto(DossierMedical dossierMedical);

}
