package org.example.healthcare.Dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.example.healthcare.Enum.Role;

import java.time.LocalDate;

@Getter
@Setter
public class PatientDto {

    @NotBlank(message = "Le champs username est invalide")
    private String username;
    @Email
    @NotBlank
    private String email;

    @NotBlank(message = "Le champs password est invalide")
    private String password;

    @NotNull
    private String role;

    @NotBlank(message = "Le champs telephone est invalide")
    @Size(min = 4 , max = 16,message = "Le numero doit contenir entre 4 et 16 chiffres")
    @Pattern(regexp = "^[0-9]+$" ,message = "Le champs entrer uniquement les chiffres")
    private String telephone;

    @NotNull(message = "Date de naissance est obligatoire !")
    private LocalDate dateNaissance;

}
