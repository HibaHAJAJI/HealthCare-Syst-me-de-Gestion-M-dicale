package org.example.healthcare.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.healthcare.Enum.Statut;

import java.time.LocalDate;

@Getter
@Setter
public class RendezVousDto {
    private Long id;
    private LocalDate dateRendezVous;
    private Statut statut;

}
