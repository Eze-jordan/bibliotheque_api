package com.esiitech.biblioteque.config;

import com.esiitech.biblioteque.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;
@RequiredArgsConstructor
@Data
@Component
public class JwtUtil {

    private static final String SECRET_KEY = "wFZLk5jR9xS6X7mZUy1L8pPzX5gWJ8z4KTQ1KhO8VjE=";  // Remplace par une clé plus sécurisée
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;  // 1 heure

    public String generateToken(String email, Role role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role.name())  // Ajoute le rôle dans le token
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public String extractRole(String token) {
        return (String) extractClaim(token, claims -> claims.get("role"));
    }

    public boolean validateToken(String token, String email) {
        return (extractEmail(token).equals(email)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }
}
