package org.example.healthcare.Mapper;

import org.example.healthcare.Dto.DossierMedicalDto;
import org.example.healthcare.Entity.DossierMedical;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DossierMedicalMapper {
    DossierMedical toEntity(DossierMedicalDto dto);

    DossierMedicalDto toDto(DossierMedical dossierMedical);

    List<DossierMedicalDto>toDtos(List<DossierMedical>dossierMedicals);
}
