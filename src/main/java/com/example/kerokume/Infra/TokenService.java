package com.example.kerokume.Infra;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.kerokume.Models.RestaurantModel;

@Service
public class TokenService {

  @Value("${api.security.token.secret}")
  private String secret;
  public String generateToken(RestaurantModel restaurant){
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      String token = JWT.create().withIssuer("kerokume")
                                 .withSubject(restaurant.getName())
                                 .withExpiresAt(getExpirationTime())
                                 .sign(algorithm);
      return token;
    } catch (JWTCreationException e) {
      throw new RuntimeException("Error while generetion Token", e);
    }
  }
  private Instant getExpirationTime(){
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }


  public String validateToken(String token){
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.require(algorithm)
           .withIssuer("kerokume")
           .build()
           .verify(token)
           .getSubject();
    } catch (JWTCreationException e) {
      return "";
    }      
  }
}
