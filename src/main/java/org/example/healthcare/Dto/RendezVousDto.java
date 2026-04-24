package org.example.healthcare.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RendezVousDto {
    private Long id;
    private LocalDate dateRendezVous;
    private String statut;

}
