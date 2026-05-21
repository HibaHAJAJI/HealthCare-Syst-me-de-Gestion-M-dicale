package org.example.healthcare.Service;

import lombok.AllArgsConstructor;
import org.example.healthcare.Dto.MedecinDto;
import org.example.healthcare.Dto.RendezVousDto;
import org.example.healthcare.Entity.Medecin;
import org.example.healthcare.Mapper.MedecinMapper;
import org.example.healthcare.Repository.MedecinRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MedecinService {

    private final MedecinRepository repository;
    private final MedecinMapper mapper;


    public MedecinDto saveMedecin(MedecinDto dto){
        Medecin medecin=mapper.toEntity(dto);
        return mapper.toDto(repository.save(medecin));
    }

    public void DeleteMedecinById(Long id){
        Medecin medecin=repository.findById(id)
                .orElseThrow(()->new RuntimeException("Aucun medecin !"));
        repository.delete(medecin);
    }
    public Page<MedecinDto> getAllMedecins(Pageable pageable){
      return repository.findAll(pageable).map(mapper::toDto);
    }

    public MedecinDto updateMedecinById(Long id,MedecinDto dto){
        Medecin medecin=repository.findById(id)
                .orElseThrow(()->new RuntimeException("Aucun medecin  !"));
        mapper.updateMedecinDto(dto,medecin);
        return mapper.toDto(repository.save(medecin));
    }

    public Page<MedecinDto>getMedecinBySpecialite(String specialite, Pageable pageable){
        Page<Medecin>medecins=repository.findBySpecialite(specialite, pageable);
        return medecins.map(mapper::toDto);
    }
}
