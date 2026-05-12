package org.example.healthcare.Configuration;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.security.Key;


@Service
public class JwtUtils {

    private final static String secret_Key="0946c56071679ffd8a3e403e43e0d97ead315d26aa831d8e";

    private long expirationTime;


    public String extractUsername(String token){
        return null;
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();

    }

    private Key getSignInKey() {
        return null;
    }

  /*  public String generateToken(String username){
        Map<String,Object> claims = new HashMap<>();
        return creatToken(claims,username);
    }

    private String creatToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expirationTime)
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Object getSignKey() {
        byte[] keyBytes = secretKey.getBytes();
        return new SecretKeySpec(keyBytes,SignatureAlgorithm.HS256.getJcaName());
    }
    public Boolean validateToken(String token,Us){

    }
    public String extractUsername(String token){
        return extactClaim(token, Claims::getExpiration)
    }

    private String extactClaim(String token, Object getExpiration) {
    }*/

}
