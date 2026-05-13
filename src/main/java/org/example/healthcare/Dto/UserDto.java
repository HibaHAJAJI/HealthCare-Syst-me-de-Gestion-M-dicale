package org.example.healthcare.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UserDto {

    private Long id;
    @NotBlank(message = "Le champ username et invalide")
    private String username;

    @NotBlank(message = "Le champ email et invalide")
    @Email(message = "Le format email est incorrect")
    private String email;

    @Size(min= 6, message = "Le passwoed doit contenir minimum 6 caractere ")
    private String password;

    private String token;


}
