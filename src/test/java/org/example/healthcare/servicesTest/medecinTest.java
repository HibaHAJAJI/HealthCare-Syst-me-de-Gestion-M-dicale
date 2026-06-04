package org.example.healthcare.servicesTest;

import org.example.healthcare.Dto.MedecinDto;
import org.example.healthcare.Entity.Medecin;
import org.example.healthcare.Enum.Role;
import org.example.healthcare.Mapper.MedecinMapper;
import org.example.healthcare.Repository.MedecinRepository;
import org.example.healthcare.Service.MedecinService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class medecinTest {

    @Mock
    private MedecinRepository repository;

    @Mock
    private MedecinMapper mapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private MedecinService service;



    @Test
    void should_Create_Medecin() {

        MedecinDto dto = new MedecinDto();
        dto.setUsername("imane");
        dto.setEmail("test@email.com");
        dto.setPassword("1234");
        dto.setSpecialite("generaliste");
        dto.setTelephone("0987654");

        Medecin entity = new Medecin();
        entity.setId(1L);

        Medecin saved = new Medecin();
        saved.setId(1L);

        MedecinDto response = new MedecinDto();
        response.setUsername("imane");

        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(saved);
        when(mapper.toDto(saved)).thenReturn(response);

        MedecinDto result = service.saveMedecin(dto);

        assertNotNull(result);
        assertEquals("imane", result.getUsername());

        verify(mapper).toEntity(dto);
        verify(repository).save(entity);
        verify(mapper).toDto(saved);
    }

    @Test
    void should_return_all_medecins_paginated() {

        Pageable pageable = PageRequest.of(0, 5);

        Medecin m1 = new Medecin();
        m1.setId(1L);

        Medecin m2 = new Medecin();
        m2.setId(2L);

        Page<Medecin> page = new PageImpl<>(List.of(m1, m2));

        when(repository.findAll(pageable)).thenReturn(page);
        when(mapper.toDto(m1)).thenReturn(new MedecinDto());
        when(mapper.toDto(m2)).thenReturn(new MedecinDto());

        Page<MedecinDto> result = service.getAllMedecins(pageable);

        assertEquals(2, result.getContent().size());

        verify(repository).findAll(pageable);
    }

    @Test
    void should_update_medecin() {

        Medecin existing = new Medecin();
        existing.setId(1L);
        existing.setRole(Role.MEDECIN);

        Medecin updated = new Medecin();
        updated.setId(1L);

        MedecinDto dto = new MedecinDto();
        dto.setUsername("newName");

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(updated);
        when(mapper.toDto(updated)).thenReturn(dto);

        MedecinDto result = service.updateMedecinById(1L, dto);

        assertEquals("newName", result.getUsername());

        verify(repository).findById(1L);
        verify(repository).save(existing);
    }

    @Test
    void should_delete_medecin() {

        Medecin medecin = new Medecin();
        medecin.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(medecin));

        service.DeleteMedecinById(1L);

        verify(repository).delete(medecin);
    }

    @Test
    void should_get_medecins_by_specialite() {

        Pageable pageable = PageRequest.of(0, 5);

        Medecin m = new Medecin();
        m.setId(1L);

        Page<Medecin> page = new PageImpl<>(List.of(m));

        when(repository.findBySpecialite("cardio", pageable)).thenReturn(page);
        when(mapper.toDto(m)).thenReturn(new MedecinDto());

        Page<MedecinDto> result =
                service.getMedecinBySpecialite("cardio", pageable);

        assertEquals(1, result.getContent().size());

        verify(repository).findBySpecialite("cardio", pageable);
    }
}



