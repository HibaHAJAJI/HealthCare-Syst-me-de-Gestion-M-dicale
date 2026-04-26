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
    public List<PatientDto> getAllPatients(){
      return mapper.toDtos(repository.findAll());
    }

    public void deletePatient(Long id){
        Patient patient = repository.findById(id)
                .orElseThrow(()->new  RuntimeException ("Patient introvable !"));
             repository.delete(patient);
    }

    public PatientDto updatePatient(Long id, PatientDto dto){
        Patient patient=repository.findById(id)
                .orElseThrow(()->new RuntimeException("Patient introvable !"));
        mapper.updatePatientDto(dto,patient);
        return mapper.toDto(repository.save(patient));
    }


   public PatientDto getPatientById(Long id){
        Patient patient=repository.findById(id)
                .orElseThrow(()->new RuntimeException("Patient introvable !"));
        return mapper.toDto(patient);
   }
}