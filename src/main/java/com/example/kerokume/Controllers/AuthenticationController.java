package com.example.kerokume.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kerokume.Infra.TokenService;
import com.example.kerokume.Models.AuthenticationDto;
import com.example.kerokume.Models.LoginResponseDto;
import com.example.kerokume.Models.RestaurantModel;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AuthenticationDto data) {
      var token = new UsernamePasswordAuthenticationToken(
          data.login(),
          data.password()
      );

      var auth = authenticationManager.authenticate(token);

      RestaurantModel user = (RestaurantModel) auth.getPrincipal();

      var JWTtoken = tokenService.generateToken(user);

      return ResponseEntity.ok(new LoginResponseDto(JWTtoken));
  }

}
