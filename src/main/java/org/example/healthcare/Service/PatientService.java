package org.example.healthcare.Service;

import org.example.healthcare.Dto.PatientDto;
import org.example.healthcare.Entity.Patient;
import org.example.healthcare.Mapper.PatientMapper;
import org.example.healthcare.Repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository repository;
    private final PatientMapper mapper;

    public PatientService(PatientRepository repository, PatientMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public PatientDto addPatient(PatientDto dto){
        Patient patient = mapper.toEntity(dto);
        return mapper.toDto(repository.save(patient));
    }
    public List<PatientDto> getAllPatient(){
      return mapper.toDtos(repository.findAll());
    }

}
