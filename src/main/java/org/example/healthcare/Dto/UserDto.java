package org.example.healthcare.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.healthcare.Enum.Role;


@Data
public class UserDto {

    @NotBlank(message = "Le champ username est invalide")
    private String username;

    @NotBlank(message = "Le champ email est invalide")
    @Email(message = "Le format email est incorrect")
    private String email;

    @Size(min= 6, message = "Le password doit contenir minimum 6 caractere ")
    private String password;

    private Role role;


}
