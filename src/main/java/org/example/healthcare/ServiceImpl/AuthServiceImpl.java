package org.example.healthcare.ServiceImpl;


import lombok.RequiredArgsConstructor;
import org.example.healthcare.Configuration.JwtService;
import org.example.healthcare.Dto.AuthResponse;
import org.example.healthcare.Dto.LoginRequest;
import org.example.healthcare.Dto.UserDto;
import org.example.healthcare.Entity.User;
import org.example.healthcare.Mapper.UserMapper;
import org.example.healthcare.Repository.UserRepository;
import org.example.healthcare.Service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse register(UserDto dto){
        if(userRepository.findByUsername(dto.getUsername()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT,("Username déjà exists"));
        }
        if(userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT,("Email déjà exists"));
        }
        User user =userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());

        User saved =userRepository.save(user);

        String token = jwtService.generateToken(saved);

        return new AuthResponse(token);
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
