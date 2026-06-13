package org.example.healthcare.Mapper;


import org.example.healthcare.Dto.PatientDto;
import org.example.healthcare.Entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel= "spring")
public interface PatientMapper {

  @Mapping(target = "id", ignore = true)
  Patient toEntity(PatientDto dto);


  PatientDto toDto(Patient patient);

  @Mapping(target = "id",ignore = true)
  void updatePatientDto(PatientDto dto,@MappingTarget Patient patient);

}
