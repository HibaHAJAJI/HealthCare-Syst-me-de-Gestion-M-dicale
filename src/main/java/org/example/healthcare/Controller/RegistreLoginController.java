package org.example.healthcare.Controller;


import lombok.RequiredArgsConstructor;
import org.example.healthcare.Dto.UserDto;
import org.example.healthcare.Entity.User;
import org.example.healthcare.Mapper.UserMapper;
import org.example.healthcare.Repository.UserRepository;
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

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto dto){

        if(userRepository.findByUsername(dto.getUsername())!=null){
            return ResponseEntity.badRequest().body("username already exists");
        }
        User user = userMapper.toEntity(dto);
             user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return  ResponseEntity.ok(userMapper.toDto(userRepository.save(user)));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDto dto){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword()));
             return ResponseEntity.ok("Login seccusseful");
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalide username !");
        }
    }

}
