package org.example.healthcare.Dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientDto {

    private Long id;

    @NotBlank(message = "Le champs nom est invalide !")
    private String nom;

    @NotBlank(message = "Le champs prenom est invalide !")
    private String prenom;

    @NotBlank(message = "Le champs email est invalide !")
    @Email(message = "Le format email est incorrect !")
    private String email;

    @NotBlank(message = "Le champs telephone est invalide")
    @Size(min = 4 , max = 16,message = "Le numero doit contenir entre 4 et 16 chiffres")
    @Pattern(regexp = "^[0-9]+$" ,message = "Le champs entrer uniquement les chiffres")
    private String telephone;

    @NotNull(message = "Date de naissance est obligatoire !")
    private LocalDate dateNaissance;

}
