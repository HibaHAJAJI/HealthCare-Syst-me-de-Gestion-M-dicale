package org.example.healthcare.Service;

import lombok.AllArgsConstructor;
import org.example.healthcare.Dto.DossierMedicalDto;
import org.example.healthcare.Entity.DossierMedical;
import org.example.healthcare.Entity.Patient;
import org.example.healthcare.Mapper.DossierMedicalMapper;
import org.example.healthcare.Repository.DossierMedicalRepository;
import org.example.healthcare.Repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class DossierMedicalService {

    private final DossierMedicalRepository repository;
    private final DossierMedicalMapper mapper;
    private final PatientRepository patientRepository;


    public DossierMedicalDto addDossierMedical(DossierMedicalDto dto){
        Patient patient=patientRepository.findById(dto.getPatientId())
                .orElseThrow(()->new RuntimeException("aucun patient"));

        DossierMedical dossierMedical= mapper.toEntity(dto);
        dossierMedical.setDateCreation(LocalDate.now());
        dossierMedical.setPatient(patient);

        return mapper.toDto(repository.save(dossierMedical));
    }

    public DossierMedicalDto addDiagnostic(Long id, String diagnostic){
        DossierMedical dossierMedical = repository.findById(id)
                .orElseThrow(()->new RuntimeException("aucun dossier medical"));

        dossierMedical.setDiagnostic(diagnostic);
        return mapper.toDto(repository.save(dossierMedical));
    }

    public DossierMedicalDto addObservation(Long id, String observation){
        DossierMedical dossierMedical=repository.findById(id)
                .orElseThrow(()->new RuntimeException("aucun dossier medical"));

        dossierMedical.setObservation(observation);
        return mapper.toDto(repository.save(dossierMedical));
    }

    public DossierMedicalDto getDossierMedical(Long id){
        DossierMedical dossierMedical=repository.findById(id)
                .orElseThrow();
        return mapper.toDto(repository.save(dossierMedical));
    }
}
