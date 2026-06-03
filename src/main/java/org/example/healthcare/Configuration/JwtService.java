package org.example.healthcare.Configuration;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService {

    private final static String secret_Key="my-healthcare-secret-key-1234567890";


    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims =new HashMap<>();
        String role = userDetails.getAuthorities()
                .stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("ROLE_PATIENT");

        claims.put("role",role);
        return generateToken(claims,userDetails);
    }

    public String generateToken(Map<String,Object>extraClaims,
                                UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .signWith(getSignInKey(),SignatureAlgorithm.HS256)
                .compact();

    }

    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims,T>claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public Boolean isTokenValid(String token , UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }



    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(secret_Key.getBytes());
    }


}
