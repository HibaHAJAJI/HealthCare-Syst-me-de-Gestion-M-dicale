package org.example.healthcare.Service.ServiceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.Dto.PatientDto;
import org.example.healthcare.Entity.Patient;
import org.example.healthcare.Enum.Role;
import org.example.healthcare.Mapper.PatientMapper;
import org.example.healthcare.Repository.PatientRepository;
import org.example.healthcare.Service.PatientService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    @Transactional
    @CacheEvict(value ={"patients", "patients-page", "patients-search"}, allEntries = true)
    public PatientDto addPatient(PatientDto dto){
        Patient patient = mapper.toEntity(dto);
        patient.setRole(Role.PATIENT);
        patient.setPassword(passwordEncoder.encode(dto.getPassword()));
        return mapper.toDto(repository.save(patient));
    }

    @Override
    @Cacheable(value = "patients-page", key = "#pageable")
    public Page<PatientDto> getAllPatients(Pageable pageable){
        return  repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    @CacheEvict(value = {"patients-id", "patients-page", "patients-search"}, allEntries = true)    public void deletePatient(Long id){
        Patient patient = repository.findById(id)
                .orElseThrow(()->new  RuntimeException ("Patient introuvable !"));
        repository.delete(patient);
    }

    @Override
    @CacheEvict(value = {"patients-id", "patients-page", "patients-search"}, allEntries = true)    public PatientDto updatePatient(Long id, PatientDto dto){
        Patient patient=repository.findById(id)
                .orElseThrow(()->new RuntimeException("Patient introuvable !"));
        mapper.updatePatientDto(dto,patient);
        return mapper.toDto(repository.save(patient));
    }

    @Override
    @Cacheable(value = "patients",key = "#id")
    public PatientDto getPatientById(Long id){
        Patient patient=repository.findById(id)
                .orElseThrow(()->new RuntimeException("Patient introuvable !"));
        return mapper.toDto(patient);
    }

    @Override
    @Cacheable(value = "patients-search", key = "#username + '_' + #pageable.pageNumber + '_' + #pageable.pageSize + '_' + #pageable.sort.toString()")
    public Page<PatientDto>chercherPatients(String username, Pageable pageable){
        return repository.findByUsernameContainingIgnoreCase(username,pageable).map(mapper::toDto);
    }
}
