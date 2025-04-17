package com.factura.facturacion.infraestructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.factura.facturacion.model.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Service
public class TokenService {
  @Value("${jwt.secret}")
  private String apiSecret;

  public String generateToken(User user){
    try {
      Algorithm algorithm = Algorithm.HMAC256(apiSecret);
      return JWT.create()
              .withIssuer("facturacion")
              .withSubject(user.getUsername())
              .withClaim("idUser", user.getIdUser())
              .withExpiresAt(generateDateExpiration())
              .sign(algorithm);
    } catch (JWTVerificationException e) {
      throw new JWTVerificationException(e.toString());
    }
  }

  public String getSubject(String token){
    if(token == null){
      throw new RuntimeException();
    }

    DecodedJWT verifier = null;

    try{
      Algorithm algorithm = Algorithm.HMAC256(apiSecret);
      verifier = JWT.require(algorithm)
              .withIssuer("facturacion")
              .build()
              .verify(token);
      verifier.getSubject();
    } catch (JWTVerificationException e) {
      throw new JWTVerificationException(e.toString());
    }

    if(verifier.getSubject() == null){
      throw  new RuntimeException("Invalid verifier");
    }
    return verifier.getSubject();
  }

  public Instant generateDateExpiration(){
    return LocalDateTime.now()
            .plusHours(2) // Agregar 2 horas
            .atZone(ZoneId.of("America/Bogota"))
            .toInstant();
  }
}
