package org.example.healthcare.Dto;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class PatientDto extends UserDto implements Serializable {


    @NotBlank(message = "Le champ téléphone est obligatoire !")
    @Size(min = 4, max = 16, message = "Le numéro doit contenir entre 4 et 16 chiffres !")
    @Pattern(regexp = "^[0-9]+$" ,message = "Le champs entrer uniquement les chiffres")
    private String telephone;

    @NotNull(message = "La date de naissance est obligatoire !")
    private LocalDate dateNaissance;

}
