package org.example.healthcare.servicesTest;

import org.example.healthcare.Dto.MedecinDto;
import org.example.healthcare.Entity.Medecin;
import org.example.healthcare.Mapper.MedecinMapper;
import org.example.healthcare.Repository.MedecinRepository;
import org.example.healthcare.Service.ServiceImpl.MedecinServiceImpl;
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
import static org.mockito.ArgumentMatchers.any;
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
    private MedecinServiceImpl service;

    @Test
    void should_save_medecin() {
        MedecinDto dto = new MedecinDto();
        dto.setPassword("rawPassword");
        Medecin medecin = new Medecin();

        when(mapper.toEntity(dto)).thenReturn(medecin);
        when(passwordEncoder.encode("rawPassword")).thenReturn("hashedPassword");
        when(repository.save(medecin)).thenReturn(medecin);
        when(mapper.toDto(medecin)).thenReturn(dto);

        MedecinDto result = service.saveMedecin(dto);

        assertNotNull(result);
        verify(repository).save(medecin);
    }

    @Test
    void should_update_medecin() {
        Medecin existing = new Medecin();
        existing.setId(1L);
        Medecin updated = new Medecin();
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

        // تصحيح كاميرا كيس (camelCase)
        service.DeleteMedecinById(1L);

        verify(repository).delete(medecin);
    }

    @Test
    void should_get_medecins_by_specialite() {
        Pageable pageable = PageRequest.of(0, 5);
        Medecin m = new Medecin();
        Page<Medecin> page = new PageImpl<>(List.of(m));

        when(repository.findBySpecialite("Cardiologue", pageable)).thenReturn(page);
        when(mapper.toDto(m)).thenReturn(new MedecinDto());

        Page<MedecinDto> result = service.getMedecinBySpecialite("Cardiologue", pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
    }
}