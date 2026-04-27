package org.example.healthcare.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.healthcare.Enum.Statut;

import java.time.LocalDateTime;

@Getter
@Setter
public class RendezVousDto {
    private Long id;
    private LocalDateTime dateRendezVous;
    private Statut statut;

    private Long medecinId;
    private Long patientId ;
}
