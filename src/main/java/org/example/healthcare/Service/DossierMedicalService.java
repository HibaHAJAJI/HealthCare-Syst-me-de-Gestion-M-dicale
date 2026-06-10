package org.example.healthcare.Service;

import org.example.healthcare.Dto.DossierMedicalDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface DossierMedicalService {


    DossierMedicalDto addDossierMedical(DossierMedicalDto dto);

    DossierMedicalDto addDiagnostic(Long id, String diagnostic);

    DossierMedicalDto addObservation(Long id, String observation);

    DossierMedicalDto getDossierMedical(Long id);

    Page<DossierMedicalDto> getAllDossierMedicaux(Pageable pageable);

    Page<DossierMedicalDto>getDossierMedicalByDiagnostic(String diagnostic,Pageable pageable);

}
