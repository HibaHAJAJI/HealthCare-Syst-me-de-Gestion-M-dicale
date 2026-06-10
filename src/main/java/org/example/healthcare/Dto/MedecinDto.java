package org.example.healthcare.Dto;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MedecinDto extends UserDto implements Serializable {

    @NotBlank(message = "Le champs specialite est invalide !")
    private String specialite;

    @NotBlank(message = "Le champs de telephone est invalide !" )
    @Pattern(regexp = "^[0-9]+$",message = "Le champs doit contenir uniquement chiffres")
    @Size( min= 4, max = 16, message = "Le numero doit contenir entre 4 et 16 chiffre")
    private String telephone;
}
