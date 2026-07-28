package org.example.healthcare.Users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/me")
    public User getCurrentUser(Authentication authentication) {

        return userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Utilisateur introuvable"
                ));
    }
    @PutMapping("/me")
    public User updateCurrentUser(Authentication authentication, @RequestBody UserDto dto) {

        return userService.updateCurrentUser(authentication.getName(), dto);
    }
}
