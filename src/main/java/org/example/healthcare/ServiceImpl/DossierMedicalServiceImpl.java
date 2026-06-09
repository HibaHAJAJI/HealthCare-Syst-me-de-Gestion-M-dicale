package org.example.healthcare.ServiceImpl;


import lombok.RequiredArgsConstructor;
import org.example.healthcare.Dto.DossierMedicalDto;
import org.example.healthcare.Entity.DossierMedical;
import org.example.healthcare.Entity.Patient;
import org.example.healthcare.Mapper.DossierMedicalMapper;
import org.example.healthcare.Repository.DossierMedicalRepository;
import org.example.healthcare.Repository.PatientRepository;
import org.example.healthcare.Service.DossierMedicalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class DossierMedicalServiceImpl  implements DossierMedicalService {

    private final DossierMedicalRepository repository;
    private final DossierMedicalMapper mapper;
    private final PatientRepository patientRepository;

    @Override
    public DossierMedicalDto addDossierMedical(DossierMedicalDto dto){
        Patient patient=patientRepository.findById(dto.getPatientId())
                .orElseThrow(()->new RuntimeException("aucun patient"));

        DossierMedical dossierMedical= mapper.toEntity(dto);
        dossierMedical.setDateCreation(LocalDate.now());
        dossierMedical.setPatient(patient);

        return mapper.toDto(repository.save(dossierMedical));
    }

    @Override
    public DossierMedicalDto addDiagnostic(Long id, String diagnostic){
        DossierMedical dossierMedical = repository.findById(id)
                .orElseThrow(()->new RuntimeException("aucun dossier medical"));

        dossierMedical.setDiagnostic(diagnostic);
        return mapper.toDto(repository.save(dossierMedical));
    }

    @Override
    public DossierMedicalDto addObservation(Long id, String observation){
        DossierMedical dossierMedical=repository.findById(id)
                .orElseThrow(()->new RuntimeException("aucun dossier medical"));

        dossierMedical.setObservation(observation);
        return mapper.toDto(repository.save(dossierMedical));
    }

    @Override
    public DossierMedicalDto getDossierMedical(Long id){
        DossierMedical dossierMedical=repository.findById(id)
                .orElseThrow();
        return mapper.toDto(repository.save(dossierMedical));
    }

    @Override
    public Page<DossierMedicalDto> getAllDossierMedicaux(Pageable pageable){
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public Page<DossierMedicalDto>getDossierMedicalByDiagnostic(String diagnostic,Pageable pageable){
        Page<DossierMedical>dossierMedicals= repository.findDossierMedicalByDiagnostic(diagnostic,pageable);
        return dossierMedicals.map(mapper::toDto);
    }
}
