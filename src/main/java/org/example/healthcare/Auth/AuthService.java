package org.example.healthcare.Auth;


import org.example.healthcare.Users.UserDto;

public interface AuthService {


    AuthResponse login(LoginRequest dto);

    AuthResponse register(UserDto dto);

}
