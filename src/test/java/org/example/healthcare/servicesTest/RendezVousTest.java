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
import org.example.healthcare.Service.ServiceImpl.RendezVousServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
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
    private RendezVousMapper mapper;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private MedecinRepository medecinRepository;

    @InjectMocks
    private RendezVousServiceImpl rendezVousService;

    @Test
    void should_add_RendezVous() {
        RendezVousDto dto = new RendezVousDto();
        dto.setPatientId(1L);
        dto.setMedecinId(2L);

        Patient patient = new Patient();
        Medecin medecin = new Medecin();
        RendezVous rv = new RendezVous();

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(medecinRepository.findById(2L)).thenReturn(Optional.of(medecin));
        when(mapper.toEntity(dto)).thenReturn(rv);
        when(rendezVousRepository.save(any(RendezVous.class))).thenReturn(rv);
        when(mapper.toDto(rv)).thenReturn(dto);

        RendezVousDto result = rendezVousService.addRendezVous(dto);

        assertNotNull(result);
        verify(rendezVousRepository).save(any(RendezVous.class));
    }

    @Test
    void should_get_all_RendezVous() {
        Pageable pageable = PageRequest.of(0, 5);
        RendezVous r1 = new RendezVous();
        RendezVous r2 = new RendezVous();
        Page<RendezVous> page = new PageImpl<>(List.of(r1, r2), pageable, 2);

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
        rv.setStatut(Statut.CONFIRME);

        when(rendezVousRepository.findById(1L)).thenReturn(Optional.of(rv));
        when(rendezVousRepository.save(any(RendezVous.class))).thenReturn(rv);
        when(mapper.toDto(any(RendezVous.class))).thenReturn(new RendezVousDto());

        RendezVousDto result = rendezVousService.annulerRendezVous(1L);

        assertNotNull(result);
        assertEquals(Statut.ANNULE, rv.getStatut());
    }

    @Test
    void should_findByMedecin_RendezVous() {
        Pageable pageable = PageRequest.of(0, 5);
        RendezVous rv = new RendezVous();
        Page<RendezVous> page = new PageImpl<>(List.of(rv), pageable, 1);

        when(rendezVousRepository.findByMedecinId(1L, pageable)).thenReturn(page);
        when(mapper.toDto(rv)).thenReturn(new RendezVousDto());

        Page<RendezVousDto> result = rendezVousService.getRendezVousByMedecinById(1L, pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
    }

    @Test
    void should_findByPatient_RendezVous() {
        Pageable pageable = PageRequest.of(0, 5);
        RendezVous rv = new RendezVous();
        Page<RendezVous> page = new PageImpl<>(List.of(rv), pageable, 1);

        when(rendezVousRepository.findByPatientId(1L, pageable)).thenReturn(page);
        when(mapper.toDto(rv)).thenReturn(new RendezVousDto());

        Page<RendezVousDto> result = rendezVousService.getRendezVousByPatientById(1L, pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
    }

    @Test
    void should_findByDate_RendezVous() {
        Pageable pageable = PageRequest.of(0, 5);
        LocalDateTime now = LocalDateTime.now();
        RendezVous rv = new RendezVous();
        Page<RendezVous> page = new PageImpl<>(List.of(rv), pageable, 1);

        when(rendezVousRepository.findByDateRendezVous(now, pageable)).thenReturn(page);
        when(mapper.toDto(rv)).thenReturn(new RendezVousDto());

        Page<RendezVousDto> result = rendezVousService.getByDate(now, pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
    }
}