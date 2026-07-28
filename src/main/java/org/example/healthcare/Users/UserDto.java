package org.example.healthcare.Users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.healthcare.Enum.Role;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    private Long id;

    @NotBlank(message = "Le champ username est obligatoire !")
    private String username;

    @NotBlank(message = "Le champ email est obligatoire !")
    @Email(message = "Le format de l'email est incorrect !")
    private String email;

    @NotBlank(message = "Le champ password est obligatoire !")
    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères !")
    private String password;

    private Role role;


}
