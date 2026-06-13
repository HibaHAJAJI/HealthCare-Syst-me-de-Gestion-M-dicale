package org.example.healthcare.Dto;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.example.healthcare.Users.UserDto;

import java.io.Serializable;

@Getter
@Setter
public class MedecinDto extends UserDto implements Serializable {

    @NotBlank(message = "Le champ spécialité est obligatoire !")
    private String specialite;

    @NotBlank(message = "Le champ téléphone est obligatoire !")
    @Pattern(regexp = "^[0-9]+$",message = "Le champs doit contenir uniquement des chiffres")
    @Size(min = 4, max = 16, message = "Le numéro doit contenir entre 4 et 16 chiffres !")
    private String telephone;
}
