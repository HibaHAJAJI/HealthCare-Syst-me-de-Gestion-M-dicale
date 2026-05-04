package org.example.healthcare.Service;

import lombok.AllArgsConstructor;
import org.example.healthcare.Dto.MedecinDto;
import org.example.healthcare.Entity.Medecin;
import org.example.healthcare.Mapper.MedecinMapper;
import org.example.healthcare.Repository.MedecinRepository;
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
    public List<MedecinDto> getAllMedecins(){
      return mapper.toDtos(repository.findAll());
    }

    public MedecinDto updateMedecinById(Long id,MedecinDto dto){
        Medecin medecin=repository.findById(id)
                .orElseThrow(()->new RuntimeException("Aucun medecin  !"));
        mapper.updateMedecinDto(dto,medecin);
        return mapper.toDto(repository.save(medecin));
    }
}
