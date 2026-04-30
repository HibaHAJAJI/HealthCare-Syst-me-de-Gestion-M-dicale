package org.example.healthcare.servicesTest;

import jakarta.transaction.Transactional;
import org.example.healthcare.Dto.PatientDto;
import org.example.healthcare.Entity.Patient;
import org.example.healthcare.Repository.PatientRepository;
import org.example.healthcare.Service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PatientTest {

    @Autowired
    private PatientRepository repository;
    @Autowired
    private PatientService service;

    @Test
    void sould_create_patient() {
        PatientDto dto = new PatientDto();
        dto.setNom("Sara");
        dto.setPrenom("sr");
        dto.setEmail("sr@email.com");
        dto.setTelephone("098765432");
        dto.setDateNaissance(LocalDate.of(2002, 02, 12));

        PatientDto result = service.addPatient(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @Test
    void should_update_Patient(){
        Patient patient = new Patient();
        patient.setNom("Sara");
        patient.setPrenom("AHMED");
        patient.setEmail("Jr@email.com");
        patient.setTelephone("098765432");
        patient.setDateNaissance(LocalDate.of(2005, 04, 10));

        Patient result= repository.save(patient);

        PatientDto dto = new PatientDto();
        dto.setNom("Sara");
        dto.setPrenom("sr");
        dto.setEmail("sr@email.com");
        dto.setTelephone("098765432");
        dto.setDateNaissance(LocalDate.of(2002, 02, 12));

        PatientDto update= service.updatePatient(result.getId(),dto);
        assertNotNull(update);
        assertEquals("sr",update.getPrenom());
    }

    @Test
    void should_getAll_Patient(){
        Patient patient = new Patient();
        patient.setNom("Sara");
        patient.setPrenom("sr");
        patient.setEmail("sr@email.com");
        patient.setTelephone("098765432");
        patient.setDateNaissance(LocalDate.of(2002, 02, 12));
        repository.save(patient);

        Patient patient2 = new Patient();
        patient2.setNom("zineb");
        patient2.setPrenom("zin");
        patient2.setEmail("zin@email.com");
        patient2.setTelephone("0345678");
        patient2.setDateNaissance(LocalDate.of(2006, 06, 23));
        repository.save(patient2);
        List<PatientDto> result = service.getAllPatients();
        assertNotNull(result);
        assertEquals(3,result.size());
    }
}
