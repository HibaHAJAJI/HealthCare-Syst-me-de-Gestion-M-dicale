package org.example.healthcare.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientDto {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private Long telephone;
    private LocalDate dateNaissance;

}
