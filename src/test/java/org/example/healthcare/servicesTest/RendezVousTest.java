package org.example.healthcare.servicesTest;

import org.example.healthcare.Dto.RendezVousDto;
import org.example.healthcare.Entity.Medecin;
import org.example.healthcare.Entity.Patient;
import org.example.healthcare.Entity.RendezVous;
import org.example.healthcare.Enum.Statut;
import org.example.healthcare.Repository.MedecinRepository;
import org.example.healthcare.Repository.PatientRepository;
import org.example.healthcare.Repository.RendezVousRepository;
import org.example.healthcare.Service.RendezVousService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    private static int count=0;

    @Test
    void should_Create_RendezVous(){
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

        RendezVousDto dto =new RendezVousDto();
        dto.setMedecinId(medecinTest.getId());
        dto.setPatientId(patientTest.getId());
        dto.setDateRendezVous(LocalDateTime.now());
        dto.setStatut(Statut.EN_ATTENTE);
        RendezVousDto result =rendezVousService.addRendezVous(dto);
        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @Test
    void should_update_RendezVous(){
          count++;
        Patient patient =new Patient();
        patient.setNom("Sara");
        patient.setPrenom("sr");
        patient.setEmail("sr"+count+ "@email.com");
        patient.setTelephone("098765432");
        patient.setDateNaissance(LocalDate.of(2002,02,12));

        patientTest= patientRepository.save(patient);

        Medecin medecin = new Medecin();
        medecin.setNom("imane");
        medecin.setSpecialite("Cardio");
        medecin.setEmail("dr"+count+"@email.com");
        medecin.setTelephone("0987654");
        medecinTest=medecinRepository.save(medecin);

        RendezVous rendezVous=new RendezVous();
        rendezVous.setMedecin(medecinTest);
        rendezVous.setPatient(patientTest);
        rendezVous.setDateRendezVous(LocalDateTime.now());
        rendezVous.setStatut(Statut.EN_ATTENTE);
        RendezVous result = rendezVousRepository.save(rendezVous);

        RendezVousDto dto=new RendezVousDto();
        dto.setMedecinId(medecinTest.getId());
        dto.setPatientId(patientTest.getId());
        dto.setDateRendezVous(LocalDateTime.now().plusDays(1));
        dto.setStatut(Statut.CONFIRME);
        RendezVousDto update=rendezVousService.updateRendezVous(result.getId(),dto);
        assertNotNull(update);
    }

    @Test
    void should_lister_RendezVous(){
        Patient patient =new Patient();
        patient.setNom("lina");
        patient.setPrenom("ln");
        patient.setEmail("ln1@email.com");
        patient.setTelephone("098765432");
        patient.setDateNaissance(LocalDate.of(2005,02,12));
        patientTest= patientRepository.save(patient);

        Medecin medecin = new Medecin();
        medecin.setNom("imane");
        medecin.setSpecialite("Cardio");
        medecin.setEmail("dr"+count+"@email.com");
        medecin.setTelephone("0987654");
        medecinTest=medecinRepository.save(medecin);

       List<RendezVousDto>  result = rendezVousService.getAllRendezVous();

       assertNotNull(result);
       assertFalse(result.isEmpty());
    }

    @Test
    void should_annuler_RendezVous(){
        Patient patient =new Patient();
        patient.setNom("lina");
        patient.setPrenom("ln");
        patient.setEmail("ln3@email.com");
        patient.setTelephone("098765432");
        patient.setDateNaissance(LocalDate.of(2005,02,12));
        patientTest= patientRepository.save(patient);

        Medecin medecin = new Medecin();
        medecin.setNom("imane");
        medecin.setSpecialite("Cardio");
        medecin.setEmail("dr3@email.com");
        medecin.setTelephone("0987654");
        medecinTest=medecinRepository.save(medecin);

        RendezVousDto dto =new RendezVousDto();
        dto.setMedecinId(medecinTest.getId());
        dto.setPatientId(patientTest.getId());
        dto.setDateRendezVous(LocalDateTime.now());
        dto.setStatut(Statut.EN_ATTENTE);
        RendezVousDto create =rendezVousService.addRendezVous(dto);

        RendezVousDto annuler =rendezVousService.annulerRendezVous(create.getId());

        assertNotNull(annuler);
        assertEquals(Statut.ANNULE,annuler.getStatut());
    }

    @Test
    void should_findByMedecin_RendezVous(){
        Patient patient =new Patient();
        patient.setNom("lina");
        patient.setPrenom("ln");
        patient.setEmail("ln4@email.com");
        patient.setTelephone("098765432");
        patient.setDateNaissance(LocalDate.of(2005,02,12));
        patientTest= patientRepository.save(patient);

        Medecin medecin = new Medecin();
        medecin.setNom("imane");
        medecin.setSpecialite("Cardio");
        medecin.setEmail("dr4@email.com");
        medecin.setTelephone("0987654");
        medecinTest=medecinRepository.save(medecin);

        RendezVousDto dto =new RendezVousDto();
        dto.setMedecinId(medecinTest.getId());
        dto.setPatientId(patientTest.getId());
        dto.setDateRendezVous(LocalDateTime.now());
        dto.setStatut(Statut.EN_ATTENTE);
        RendezVousDto create =rendezVousService.addRendezVous(dto);

         List<RendezVousDto> recherche =rendezVousService.getRendezVousByMedecinById(create.getId());
          assertNotNull(recherche);
          assertFalse(recherche.isEmpty());
    }
}
