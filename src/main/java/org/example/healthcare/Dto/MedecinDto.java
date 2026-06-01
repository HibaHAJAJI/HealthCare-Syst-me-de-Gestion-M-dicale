package org.example.healthcare.Dto;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedecinDto {


    @NotBlank(message = "Le champs username est invalide")
    private String username;

    @NotBlank(message = "Le champs email est invalide !")
    @Email(message = "Le format email est invalide !")
    private String email;

    @NotBlank(message = "Le champs password est invalide")
    private String password;


    private String role;

    @NotBlank(message = "Le champs specialite est invalide !")
    private String specialite;

    @NotBlank(message = "Le champs de telephone est invalide !" )
    @Pattern(regexp = "^[0-9]+$",message = "Le champs doit contenir uniquement chiffres")
    @Size( min= 4, max = 16, message = "Le numero doit contenir entre 4 et 16 chiffre")
    private String telephone;
}
