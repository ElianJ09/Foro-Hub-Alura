package com.alura.foro_hub.infra.security;

import com.alura.foro_hub.domain.usersModels.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
@Service
public class tokenService {
    @Value("${api.security.secret}")
    private String apiSecret;
    public String generateNewToken(User user){
        try {
            var algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("API foro-hub-alura")
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getId())
                    .withExpiresAt(generateExpiration())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException();
        }
        DecodedJWT tokenJWT = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            tokenJWT = JWT.require(algorithm)
                    .withIssuer("API foro-hub-alura")
                    .build()
                    .verify(tokenJWT);
            tokenJWT.getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println("Error: " + exception);
        }
        if (tokenJWT.getSubject() == null) {
            throw new RuntimeException("Invalid token!");
        }
        return tokenJWT.getSubject();
    }

    private Instant generateExpiration() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
