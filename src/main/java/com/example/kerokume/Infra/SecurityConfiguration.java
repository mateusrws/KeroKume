package com.example.kerokume.Infra;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
  @Autowired
  SecurityFilter securityFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(csrf -> csrf.disable())
        .cors(cors -> {}) // 👈 IMPORTANTE
        .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(OPTIONS, "/**").permitAll() // 👈 ESSENCIAL
            .requestMatchers(GET, "/foods/**").permitAll()
            .requestMatchers(POST, "/restaurants").permitAll()
            .requestMatchers(POST, "/auth/login").permitAll()
            .anyRequest().authenticated()
        )
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
}

  @Bean
  public AuthenticationManager authenticationManager(
          AuthenticationConfiguration authenticationConfiguration
  ) throws Exception {
      return authenticationConfiguration.getAuthenticationManager();
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration config = new CorsConfiguration();
      config.setAllowedOrigins(List.of("*")); // ajuste depois
      config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
      config.setAllowedHeaders(List.of("*"));
      config.setAllowCredentials(false);

      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", config);
      return source;
  }
}
