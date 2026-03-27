package com.backend.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    @Value("${jwt.expiration}")
    private long expirationDate;

    @Value("${jwt.secret}")
    private String secretKey;

    public JWTService() {
        try{
//            KeyGenerator kg = KeyGenerator.getInstance("HmacSHA256");
//            SecretKey sk=kg.generateKey();
//            secretKey= Base64.getEncoder().encodeToString(sk.getEncoded());
//
//            //5 min
//            expirationDate = new Date(System.currentTimeMillis() + 5*60*1000);

        }catch(Exception e){
            throw new  RuntimeException(e);
        }
    }

    public String generateToken(String email) {
        Map<String,Object> claims = new HashMap<>();
        //claims.put("role",userRequest.)
        return Jwts.builder().claims()
                .add(claims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationDate))
                .and().signWith(SignatureAlgorithm.HS256, getSecretKey()).compact();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
