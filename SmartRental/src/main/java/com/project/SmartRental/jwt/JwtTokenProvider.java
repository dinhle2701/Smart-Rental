package com.project.SmartRental.jwt;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final String SECRET_KEY = "rRYy7wzJDqFQuvMJ8BFUd9eiND9Y6pHtvrJmJQCeQ9oA6/5OtEKWIOCmIHrhrZk6uFfTbxtnr/GfqvJY9GSP1A==";
    private static final long EXPIRATION_TIME = 86400000; // 1 ngày
    private static final long REFRESH_EXPIRATION = 7 * 24 * 60 * 60 * 1000; // 7 ngày
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateAccessToken(Long userId, String accountName, String email, String role) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .claim("user id: ", userId)
                .claim("username", accountName)
                .claim("role: ", role)
                .claim("token_type", "access")
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Tạo Refresh Token
    public String generateRefreshToken(Long userId, String accountName, String email, String role) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + REFRESH_EXPIRATION);

        return Jwts.builder()
                .setSubject(email)
                .claim("user id: ", userId)
                .claim("username", accountName)
                .claim("role: ", role)
                .claim("token_type", "refresh")
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
