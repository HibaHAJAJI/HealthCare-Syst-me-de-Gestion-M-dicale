package org.example.healthcare.ServiceImpl;

import lombok.RequiredArgsConstructor;
import org.example.healthcare.Dto.PatientDto;
import org.example.healthcare.Entity.Patient;
import org.example.healthcare.Enum.Role;
import org.example.healthcare.Mapper.PatientMapper;
import org.example.healthcare.Repository.PatientRepository;
import org.example.healthcare.Service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PatientServiceImpl  implements PatientService {

    private final PatientRepository repository;
    private final PatientMapper mapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public PatientDto addPatient(PatientDto dto){
        Patient patient = mapper.toEntity(dto);
        patient.setRole(Role.PATIENT);
        patient.setPassword(passwordEncoder.encode(dto.getPassword()));
        return mapper.toDto(repository.save(patient));
    }

    @Override
    public Page<PatientDto> getAllPatients(Pageable pageable){
        Page<Patient>patients=repository.findAll(pageable);
        return patients.map(mapper::toDto);
    }

    @Override
    public void deletePatient(Long id){
        Patient patient = repository.findById(id)
                .orElseThrow(()->new  RuntimeException ("Patient introuvable !"));
        repository.delete(patient);
    }

    @Override
    public PatientDto updatePatient(Long id, PatientDto dto){
        Patient patient=repository.findById(id)
                .orElseThrow(()->new RuntimeException("Patient introuvable !"));
        mapper.updatePatientDto(dto,patient);
        return mapper.toDto(repository.save(patient));
    }

    @Override
    public PatientDto getPatientById(Long id){
        Patient patient=repository.findById(id)
                .orElseThrow(()->new RuntimeException("Patient introuvable !"));
        return mapper.toDto(patient);
    }

    @Override
    public Page<PatientDto>chercherPatients(String username, Pageable pageable){
        Page<Patient> patients =repository.findByUsernameContainingIgnoreCase(username,pageable);
        return patients.map(mapper::toDto);
    }
}
