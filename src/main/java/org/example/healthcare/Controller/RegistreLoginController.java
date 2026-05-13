package org.example.healthcare.Controller;


import lombok.RequiredArgsConstructor;
import org.example.healthcare.Dto.LoginRequest;
import org.example.healthcare.Dto.UserDto;
import org.example.healthcare.Entity.User;
import org.example.healthcare.Mapper.UserMapper;
import org.example.healthcare.Repository.UserRepository;
import org.example.healthcare.Service.AuthService;
import org.example.healthcare.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegistreLoginController {

    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest login){

            return ResponseEntity.ok(authService.login(login));
    }


}
