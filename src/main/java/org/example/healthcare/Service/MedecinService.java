package org.example.healthcare.Service;

import org.example.healthcare.Dto.MedecinDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MedecinService {

    MedecinDto saveMedecin(MedecinDto dto);

    void DeleteMedecinById(Long id);

    Page<MedecinDto> getAllMedecins(Pageable pageable);

    MedecinDto updateMedecinById(Long id,MedecinDto dto);

    Page<MedecinDto>getMedecinBySpecialite(String specialite, Pageable pageable);


}
