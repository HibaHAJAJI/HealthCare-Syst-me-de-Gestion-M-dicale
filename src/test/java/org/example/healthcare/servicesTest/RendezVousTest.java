package org.example.healthcare.servicesTest;

import org.example.healthcare.Entity.Medecin;
import org.example.healthcare.Entity.Patient;

import org.example.healthcare.Repository.MedecinRepository;
import org.example.healthcare.Repository.PatientRepository;
import org.example.healthcare.Repository.RendezVousRepository;
import org.example.healthcare.Service.RendezVousService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RendezVousTest {

    @Autowired
    private RendezVousService rendezVousService;

    @Autowired
    private RendezVousRepository rendezVousRepository;

    @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private PatientRepository patientRepository;

    private Patient patientTest;
    private Medecin medecinTest;

    @BeforeEach
    void setUp() {
        rendezVousRepository.deleteAll();
        medecinRepository.deleteAll();
        patientRepository.deleteAll();
    }

    @Test
    void should_Create_RendezVous() {

        Patient patient = new Patient();
        patient.setNom("Sara");
        patient.setPrenom("sr");
        patient.setEmail("sr" + UUID.randomUUID() + "@email.com");
        patient.setTelephone("098765432");
        patient.setDateNaissance(LocalDate.of(2002, 2, 12));

        patientTest = patientRepository.save(patient);

        Medecin medecin = new Medecin();
        medecin.setNom("imane");
        medecin.setSpecialite("Cardio");
        medecin.setEmail("dr" + UUID.randomUUID() + "@email.com");
        medecin.setTelephone("0987654");

        medecinTest = medecinRepository.save(medecin);

        RendezVousDto dto = new RendezVousDto();
        dto.setMedecinId(medecinTest.getId());
        dto.setPatientId(patientTest.getId());
        dto.setDateRendezVous(LocalDateTime.now());
        dto.setStatut(Statut.EN_ATTENTE);

        RendezVousDto result = rendezVousService.addRendezVous(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @Test
    void should_update_RendezVous() {

        Patient patient = new Patient();
        patient.setNom("Sara");
        patient.setPrenom("sr");
        patient.setEmail("sr" + UUID.randomUUID() + "@email.com");
        patient.setTelephone("098765432");
        patient.setDateNaissance(LocalDate.of(2002, 2, 12));

        patientTest = patientRepository.save(patient);

        Medecin medecin = new Medecin();
        medecin.setNom("imane");
        medecin.setSpecialite("Cardio");
        medecin.setEmail("dr" + UUID.randomUUID() + "@email.com");
        medecin.setTelephone("0987654");

        medecinTest = medecinRepository.save(medecin);

        RendezVous rendezVous = new RendezVous();
        rendezVous.setMedecin(medecinTest);
        rendezVous.setPatient(patientTest);
        rendezVous.setDateRendezVous(LocalDateTime.now());
        rendezVous.setStatut(Statut.EN_ATTENTE);

        RendezVous saved = rendezVousRepository.save(rendezVous);

        RendezVousDto dto = new RendezVousDto();
        dto.setMedecinId(medecinTest.getId());   // ✔ IMPORTANT
        dto.setPatientId(patientTest.getId());
        dto.setDateRendezVous(LocalDateTime.now().plusDays(1));
        dto.setStatut(Statut.CONFIRME);

        RendezVousDto update = rendezVousService.updateRendezVous(saved.getId(), dto);

        assertNotNull(update);
        assertEquals(Statut.CONFIRME, update.getStatut());
    }

    @Test
    void should_lister_RendezVous() {

        Patient patient = new Patient();
        patient.setNom("lina");
        patient.setPrenom("ln");
        patient.setEmail("ln" + UUID.randomUUID() + "@email.com");
        patient.setTelephone("098765432");
        patient.setDateNaissance(LocalDate.of(2005, 2, 12));

        patientRepository.save(patient);

        Medecin medecin = new Medecin();
        medecin.setNom("imane");
        medecin.setSpecialite("Cardio");
        medecin.setEmail("dr" + UUID.randomUUID() + "@email.com");
        medecin.setTelephone("0987654");

        medecinRepository.save(medecin);

        List<RendezVousDto> result = rendezVousService.getAllRendezVous();

        assertNotNull(result);
    }

    @Test
    void should_annuler_RendezVous() {

        Patient patient = new Patient();
        patient.setNom("lina");
        patient.setPrenom("ln");
        patient.setEmail("ln" + UUID.randomUUID() + "@email.com");
        patient.setTelephone("098765432");
        patient.setDateNaissance(LocalDate.of(2005, 2, 12));

        patientTest = patientRepository.save(patient);

        Medecin medecin = new Medecin();
        medecin.setNom("imane");
        medecin.setSpecialite("Cardio");
        medecin.setEmail("dr" + UUID.randomUUID() + "@email.com");
        medecin.setTelephone("0987654");

        medecinTest = medecinRepository.save(medecin);

        RendezVousDto dto = new RendezVousDto();
        dto.setMedecinId(medecinTest.getId());
        dto.setPatientId(patientTest.getId());
        dto.setDateRendezVous(LocalDateTime.now());
        dto.setStatut(Statut.EN_ATTENTE);

        RendezVousDto create = rendezVousService.addRendezVous(dto);

        RendezVousDto annuler = rendezVousService.annulerRendezVous(create.getId());

        assertNotNull(annuler);
        assertEquals(Statut.ANNULE, annuler.getStatut());
    }

    @Test
    void should_findByMedecin_RendezVous() {

        Patient patient = new Patient();
        patient.setNom("lina");
        patient.setPrenom("ln");
        patient.setEmail("ln" + UUID.randomUUID() + "@email.com");
        patient.setTelephone("098765432");
        patient.setDateNaissance(LocalDate.of(2005, 2, 12));

        patientTest = patientRepository.save(patient);

        Medecin medecin = new Medecin();
        medecin.setNom("imane");
        medecin.setSpecialite("Cardio");
        medecin.setEmail("dr" + UUID.randomUUID() + "@email.com");
        medecin.setTelephone("0987654");

        medecinTest = medecinRepository.save(medecin);

        RendezVousDto dto = new RendezVousDto();
        dto.setMedecinId(medecinTest.getId());
        dto.setPatientId(patientTest.getId());
        dto.setDateRendezVous(LocalDateTime.now());
        dto.setStatut(Statut.EN_ATTENTE);

        rendezVousService.addRendezVous(dto);

        List<RendezVousDto> result =
                rendezVousService.getRendezVousByMedecinById(medecinTest.getId()); // ✔ FIXED

        assertNotNull(result);
    }
}