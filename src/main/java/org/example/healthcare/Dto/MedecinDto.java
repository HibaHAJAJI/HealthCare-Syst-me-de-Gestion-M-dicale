package org.example.healthcare.Dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedecinDto {
    private Long id;
    private String nom;
    private String specialite;
    private String email;
    private Long telephone;
}
