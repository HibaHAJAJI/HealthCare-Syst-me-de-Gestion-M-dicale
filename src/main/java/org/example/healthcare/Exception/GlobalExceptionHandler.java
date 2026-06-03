package org.example.healthcare.Exception;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handlerValidationErrors(MethodArgumentNotValidException exception){

      Map<String,String>erreurs=new HashMap<>();

      for(FieldError error : exception.getBindingResult().getFieldErrors()){
          erreurs.put(error.getField(),error.getDefaultMessage());
      }

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erreurs);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<Map<String, String>> handleJwtException(JwtException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error","UNAUTHORIZED","message", "invalid jwt token"));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUsernameNotFound(UsernameNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", ex.getMessage()));
    }
}
