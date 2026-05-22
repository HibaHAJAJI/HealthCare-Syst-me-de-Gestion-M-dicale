package org.example.healthcare.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.Dto.AuthResponse;
import org.example.healthcare.Dto.LoginRequest;
import org.example.healthcare.Dto.UserDto;
import org.example.healthcare.Service.AuthService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody LoginRequest login){
            return ResponseEntity.ok(authService.login(login));
    }


}
