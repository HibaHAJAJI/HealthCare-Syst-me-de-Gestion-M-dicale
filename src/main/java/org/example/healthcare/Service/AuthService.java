package org.example.healthcare.Service;


import org.example.healthcare.Dto.AuthResponse;
import org.example.healthcare.Dto.LoginRequest;
import org.example.healthcare.Dto.UserDto;


public interface AuthService {


    AuthResponse login(LoginRequest dto);

    AuthResponse register(UserDto dto);

}
