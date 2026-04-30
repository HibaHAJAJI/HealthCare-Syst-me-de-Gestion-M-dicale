package org.example.healthcare.servicesTest;

import jakarta.transaction.Transactional;
import org.example.healthcare.Dto.RendezVousDto;
import org.example.healthcare.Entity.Medecin;
import org.example.healthcare.Entity.Patient;
import org.example.healthcare.Enum.Statut;
import org.example.healthcare.Repository.MedecinRepository;
import org.example.healthcare.Repository.PatientRepository;
import org.example.healthcare.Repository.RendezVousRepository;
import org.example.healthcare.Service.RendezVousService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RendezVousTest {

    @Autowired
    private  RendezVousService rendezVousService;
    @Autowired
    private  RendezVousRepository rendezVousRepository;
    @Autowired
    private  MedecinRepository medecinRepository;
    @Autowired
    private  PatientRepository patientRepository;

    private  Patient patientTest;
    private Medecin medecinTest;

    @BeforeEach
    void setUp(){
        Patient patient =new Patient();
        patient.setNom("Sara");
        patient.setPrenom("sr");
        patient.setEmail("sr@email.com");
        patient.setTelephone("098765432");
        patient.setDateNaissance(LocalDate.of(2002,02,12));

        patientTest= patientRepository.save(patient);

        Medecin medecin = new Medecin();
        medecin.setNom("imane");
        medecin.setSpecialite("Cardio");
        medecin.setEmail("dr@email.com");
        medecin.setTelephone("0987654");
        medecinTest=medecinRepository.save(medecin);
    }

    @Test
    void should_Create_RendezVous(){
        RendezVousDto dto =new RendezVousDto();
        dto.setMedecinId(medecinTest.getId());
        dto.setPatientId(patientTest.getId());
        dto.setDateRendezVous(LocalDateTime.now());
        dto.setStatut(Statut.EN_ATTENTE);
        RendezVousDto result =rendezVousService.addRendezVous(dto);
        assertNotNull(result);
        assertNotNull(result.getId());
    }


}
