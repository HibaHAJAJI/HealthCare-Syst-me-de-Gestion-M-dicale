package org.example.healthcare.servicesTest;

import org.example.healthcare.Dto.MedecinDto;
import org.example.healthcare.Entity.Medecin;
import org.example.healthcare.Repository.MedecinRepository;
import org.example.healthcare.Service.MedecinService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class medecinTest {

    @Autowired
    private MedecinRepository repository;

    @Autowired
    private MedecinService service;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void should_Create_Medecin() {

        MedecinDto dto = new MedecinDto();
        dto.setNom("imane");
        dto.setEmail("sr" + UUID.randomUUID() + "@email.com");
        dto.setSpecialite("generaliste");
        dto.setTelephone("0987654");

        MedecinDto result = service.saveMedecin(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @Test
    void should_update_medecin() {

        Medecin medecin = new Medecin();
        medecin.setNom("yasmine");
        medecin.setEmail("dr" + UUID.randomUUID() + "@email.com");
        medecin.setSpecialite("cardiologie");
        medecin.setTelephone("0987654");

        Medecin saved = repository.save(medecin);

        MedecinDto dto = new MedecinDto();
        dto.setNom("yasmine updated");
        dto.setEmail(saved.getEmail());
        dto.setSpecialite("neurologie");
        dto.setTelephone("1111111");

        MedecinDto updated = service.updateMedecinById(saved.getId(), dto);

        assertNotNull(updated);
        assertEquals("neurologie", updated.getSpecialite());
    }

    @Test
    void should_delete_medecin() {

        Medecin medecin = new Medecin();
        medecin.setNom("yasmine");
        medecin.setEmail("dr" + UUID.randomUUID() + "@email.com");
        medecin.setSpecialite("cardiologie");
        medecin.setTelephone("0987654");

        Medecin saved = repository.save(medecin);

        service.DeleteMedecinById(saved.getId());

        assertFalse(repository.existsById(saved.getId()));
    }

    @Test
    void should_getAll_medecin() {

        Medecin m1 = new Medecin();
        m1.setNom("karima");
        m1.setEmail("dr" + UUID.randomUUID() + "@email.com");
        m1.setSpecialite("cardiologie");
        m1.setTelephone("0987654");
        repository.save(m1);

        Medecin m2 = new Medecin();
        m2.setNom("noura");
        m2.setEmail("nr" + UUID.randomUUID() + "@email.com");
        m2.setSpecialite("neurologie");
        m2.setTelephone("04567875");
        repository.save(m2);

        List<MedecinDto> result = service.getAllMedecins();

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}