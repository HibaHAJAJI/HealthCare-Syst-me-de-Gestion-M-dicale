package org.example.healthcare.Mapper;


import org.example.healthcare.Dto.PatientDto;
import org.example.healthcare.Entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel= "spring")
public interface PatientMapper {

  Patient toEntity(PatientDto dto);

  PatientDto toDto(Patient patient);

  List<PatientDto>toDtos(List<Patient>patients);

  void updatePatientDto(PatientDto dto,@MappingTarget Patient patient);

}
