package com.votaya.votingservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtTokenProvider {

    // IMPORTANT: This key MUST be the same as the one in AuthService.
    // It should be stored securely and not hardcoded.
    private final SecretKey jwtSecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecretKey).build().parseClaimsJws(authToken);
            return true;
        } catch (Exception ex) {
            // Can be more specific with exception handling (e.g., SignatureException, ExpiredJwtException)
            System.out.println("Invalid JWT token: " + ex.getMessage());
        }
        return false;
    }
}
