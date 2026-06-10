package org.example.healthcare.Service;


import org.example.healthcare.Dto.RendezVousDto;
import org.example.healthcare.Enum.Statut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;



public interface RendezVousService {

    RendezVousDto addRendezVous(RendezVousDto dto);

    Page<RendezVousDto> getAllRendezVous(Pageable pageable);

    RendezVousDto updateRendezVous(Long id,RendezVousDto dto);

    RendezVousDto annulerRendezVous(Long id);

    Page<RendezVousDto> getRendezVousByPatientById(Long id, Pageable pageable);

    Page<RendezVousDto> getRendezVousByMedecinById(Long medecintId,Pageable pageable);

    Page<RendezVousDto> chercherParStatut(Statut statut, Pageable pageable);

    Page<RendezVousDto> getByDate(LocalDateTime dateRendezVous, Pageable pageable);


}
