package org.example.healthcare.Service;

import lombok.AllArgsConstructor;
import org.example.healthcare.Dto.PatientDto;
import org.example.healthcare.Entity.Patient;
import org.example.healthcare.Enum.Role;
import org.example.healthcare.Mapper.PatientMapper;
import org.example.healthcare.Repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PatientService {

    private final PatientRepository repository;
    private final PatientMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public PatientDto addPatient(PatientDto dto){
        Patient patient = mapper.toEntity(dto);
        patient.setRole(Role.PATIENT);
        return mapper.toDto(repository.save(patient));
    }
    public Page<PatientDto> getAllPatients(Pageable pageable){
        Page<Patient>patients=repository.findAll(pageable);
           return patients.map(mapper::toDto);
    }

    public void deletePatient(Long id){
        Patient patient = repository.findById(id)
                .orElseThrow(()->new  RuntimeException ("Patient introuvable !"));
             repository.delete(patient);
    }

    public PatientDto updatePatient(Long id, PatientDto dto){
        Patient patient=repository.findById(id)
                .orElseThrow(()->new RuntimeException("Patient introuvable !"));
        mapper.updatePatientDto(dto,patient);
        return mapper.toDto(repository.save(patient));
    }
    
   public PatientDto getPatientById(Long id){
        Patient patient=repository.findById(id)
                .orElseThrow(()->new RuntimeException("Patient introuvable !"));
        return mapper.toDto(patient);
   }

   public Page<PatientDto>chercherPatients(String username, Pageable pageable){
        Page<Patient> patients =repository.findByUsernameContainingIgnoreCase(username,pageable);
        return patients.map(mapper::toDto);
   }
}