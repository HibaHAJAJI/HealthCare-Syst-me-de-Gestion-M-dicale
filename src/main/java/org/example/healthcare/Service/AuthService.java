package org.example.healthcare.Service;


import lombok.RequiredArgsConstructor;
import org.example.healthcare.Configuration.JwtService;
import org.example.healthcare.Dto.AuthResponse;
import org.example.healthcare.Dto.LoginRequest;
import org.example.healthcare.Dto.UserDto;
import org.example.healthcare.Entity.User;
import org.example.healthcare.Enum.Role;
import org.example.healthcare.Mapper.UserMapper;
import org.example.healthcare.Repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserDto register(UserDto dto){
        if(userRepository.findByUsername(dto.getUsername()).isPresent()){
            throw new RuntimeException("Username déjà exists");
        }
        if(userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new RuntimeException("Email déjà exists");
        }
        User user =userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        if (dto.getRole() != null) {
            user.setRole(dto.getRole());
        } else {
            user.setRole(Role.PATIENT);
        }        User saved =userRepository.save(user);

        String token = jwtService.generateToken(saved);

        UserDto response = userMapper.toDto(saved);
        response.setRole(saved.getRole());
        response.setToken(token);

        return response;
    }

    public AuthResponse login(LoginRequest dto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword()
                )
        );

        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(()->new RuntimeException("User not found"));
        String token = jwtService.generateToken(user);
        return new AuthResponse(token);

    }
}
