package org.example.healthcare.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "Le champ username et invalide")
    private String username;

    @NotBlank(message = "Le champ password et invalide")
    @Size(min= 6, message = "Le passwoed doit contenir minimum 6 caractere ")
    private String password;
}
