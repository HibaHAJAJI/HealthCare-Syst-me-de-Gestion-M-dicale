package org.example.healthcare.servicesTest;

import org.example.healthcare.Dto.RendezVousDto;
import org.example.healthcare.Entity.Medecin;
import org.example.healthcare.Entity.Patient;

import org.example.healthcare.Entity.RendezVous;
import org.example.healthcare.Enum.Statut;
import org.example.healthcare.Mapper.RendezVousMapper;
import org.example.healthcare.Repository.MedecinRepository;
import org.example.healthcare.Repository.PatientRepository;
import org.example.healthcare.Repository.RendezVousRepository;
import org.example.healthcare.Service.RendezVousService;
import org.example.healthcare.Service.ServiceImpl.RendezVousServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class RendezVousTest {

    @Mock
    private RendezVousRepository rendezVousRepository;

    @Mock
    private MedecinRepository medecinRepository;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private RendezVousMapper mapper;

    @InjectMocks
    private RendezVousServiceImpl rendezVousService;


    @Test
    void should_Create_RendezVous() {

        RendezVousDto dto = new RendezVousDto();
        dto.setMedecinId(1L);
        dto.setPatientId(1L);

        Medecin medecin = new Medecin();
        medecin.setId(1L);

        Patient patient = new Patient();
        patient.setId(1L);

        RendezVous entity = new RendezVous();
        entity.setId(10L);

        RendezVous saved = new RendezVous();
        saved.setId(10L);

        RendezVousDto response = new RendezVousDto();
        response.setId(10L);

        when(medecinRepository.findById(1L)).thenReturn(Optional.of(medecin));
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(rendezVousRepository.save(entity)).thenReturn(saved);
        when(mapper.toDto(saved)).thenReturn(response);

        RendezVousDto result = rendezVousService.addRendezVous(dto);

        assertNotNull(result);
        assertEquals(10L, result.getId());

        verify(medecinRepository).findById(1L);
        verify(patientRepository).findById(1L);
        verify(rendezVousRepository).save(entity);
    }

    @Test
    void should_return_paginated_rendezVous() {

        Pageable pageable = PageRequest.of(0, 5);

        RendezVous r1 = new RendezVous();
        r1.setId(1L);

        RendezVous r2 = new RendezVous();
        r2.setId(2L);

        Page<RendezVous> page =
                new PageImpl<>(List.of(r1, r2), pageable, 2);

        when(rendezVousRepository.findAll(pageable)).thenReturn(page);
        when(mapper.toDto(r1)).thenReturn(new RendezVousDto());
        when(mapper.toDto(r2)).thenReturn(new RendezVousDto());

        Page<RendezVousDto> result = rendezVousService.getAllRendezVous(pageable);

        assertNotNull(result);
        assertEquals(2, result.getContent().size());
    }


    @Test
    void should_annuler_RendezVous() {

        RendezVous rv = new RendezVous();
        rv.setId(1L);
        rv.setStatut(Statut.EN_ATTENTE);

        when(rendezVousRepository.findById(1L)).thenReturn(Optional.of(rv));
        when(rendezVousRepository.save(any())).thenReturn(rv);
        when(mapper.toDto(any())).thenReturn(new RendezVousDto());

        RendezVousDto result = rendezVousService.annulerRendezVous(1L);

        assertNotNull(result);
    }

    @Test
    void should_findByMedecin_RendezVous() {

        Pageable pageable = PageRequest.of(0, 5);

        RendezVous rv = new RendezVous();
        rv.setId(1L);

        Page<RendezVous> page =
                new PageImpl<>(List.of(rv), pageable, 1);

        when(rendezVousRepository.findByMedecinId(1L, pageable))
                .thenReturn(page);

        when(mapper.toDto(rv)).thenReturn(new RendezVousDto());

        Page<RendezVousDto> result =
                rendezVousService.getRendezVousByMedecinById(1L, pageable);

        assertEquals(1, result.getContent().size());
    }
}