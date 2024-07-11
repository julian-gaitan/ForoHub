package com.challengeone.forohub.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.challengeone.forohub.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class JWTService {

    @Value("${api.security.secret}")
    private String secret;

    public String generateToken(UserEntity user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("ForoHub")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(getPlusHours(3))
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Optional<String> getSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer("ForoHub")
                    .build()
                    .verify(token);
            return Optional.of(verifier.getSubject());
        } catch (JWTVerificationException ex) {
            System.err.println(ex.getMessage());
        }
        return Optional.empty();
    }

    private Instant getPlusHours(int hours) {
        return LocalDateTime.now().plusHours(hours).toInstant(TimeZone.getDefault().toZoneId().getRules().getOffset(Instant.now()));
    }
}
