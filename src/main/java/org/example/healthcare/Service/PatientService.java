package org.example.healthcare.Service;

import org.example.healthcare.Dto.PatientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface PatientService {


    PatientDto addPatient(PatientDto dto);

    Page<PatientDto> getAllPatients(Pageable pageable);

    void deletePatient(Long id);

    PatientDto updatePatient(Long id, PatientDto dto);

    PatientDto getPatientById(Long id);

    Page<PatientDto>chercherPatients(String username, Pageable pageable);

}