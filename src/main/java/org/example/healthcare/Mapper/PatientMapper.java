package org.example.healthcare.Mapper;


import org.example.healthcare.Dto.PatientDto;
import org.example.healthcare.Entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel= "spring")
public interface PatientMapper {

  @Mapping(target = "user.username", source = "username")
  @Mapping(target = "user.email", source = "email")
  @Mapping(target = "user.password", source = "password")
  @Mapping(target = "user.role", source = "role")
  Patient toEntity(PatientDto dto);

  @Mapping(source = "user.username", target = "username")
  @Mapping(source = "user.email", target = "email")
  @Mapping(source = "user.role", target = "role")
  @Mapping(target = "password", ignore = true)
  PatientDto toDto(Patient patient);

  @Mapping(target = "id",ignore = true)
  @Mapping(target = "user.username", source = "username")
  @Mapping(target = "user.email", source = "email")
  void updatePatientDto(PatientDto dto,@MappingTarget Patient patient);

}
