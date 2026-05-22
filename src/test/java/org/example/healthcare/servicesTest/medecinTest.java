package org.example.healthcare.servicesTest;

import org.example.healthcare.Dto.MedecinDto;
import org.example.healthcare.Entity.Medecin;
import org.example.healthcare.Repository.MedecinRepository;
import org.example.healthcare.Service.MedecinService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

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




}