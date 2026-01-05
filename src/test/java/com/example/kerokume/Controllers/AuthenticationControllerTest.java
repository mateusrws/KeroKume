package com.example.kerokume.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.kerokume.Infra.TokenService;
import com.example.kerokume.Models.AuthenticationDto;
import com.example.kerokume.Models.Restaurant.RestaurantModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AuthenticationController.class)
class AuthenticationControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AuthenticationManager authenticationManager;

  @MockBean
  private TokenService tokenService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void testLoginSuccess() throws Exception {
    // DTO de login
    AuthenticationDto dto = new AuthenticationDto("restaurant@test.com", "123456");

    // Usuário fake autenticado
    RestaurantModel restaurant = new RestaurantModel();
    restaurant.setName("Test Restaurant");

    // Authentication mockado
    Authentication authentication =
        new UsernamePasswordAuthenticationToken(restaurant, null);

    // comportamento esperado
    when(authenticationManager.authenticate(any()))
        .thenReturn(authentication);

    when(tokenService.generateToken(restaurant))
        .thenReturn("fake-jwt-token");

    mockMvc.perform(post("/auth/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(dto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.token").value("fake-jwt-token"));
  }
}
