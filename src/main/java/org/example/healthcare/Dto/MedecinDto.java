package org.example.healthcare.Dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedecinDto {

    private Long id;

    @NotBlank(message = "Le champs nom est invalide !")
    private String nom;

    @NotBlank(message = "Le champs specialite est invalide !")
    private String specialite;

    @NotBlank(message = "Le champs email est invalide !")
    @Email(message = "Le format email est invalide !")
    private String email;

    @NotBlank(message = "Le champs de telephone est invalide !" )
    @Pattern(regexp = "^[0-9]+$",message = "Le champs doit contenir uniquement chiffres")
    @Size( min= 4, max = 16, message = "Le numero doit contenir entre 4 et 16 chiffre")
    private String telephone;
}
