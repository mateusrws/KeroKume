package com.example.kerokume.Infra;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.kerokume.Models.RestaurantModel;
import com.example.kerokume.Repositorys.RestaurantRepo;

import ch.qos.logback.core.subst.Token;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String token = recoverToken(request);

        if (token != null) {
            String name = tokenService.validateToken(token);

            Optional<RestaurantModel> restaurant =
                    restaurantRepo.findResByName(name);

            if (restaurant.isPresent()) {
                var authentication = new UsernamePasswordAuthenticationToken(
                        restaurant.get(),
                        null,
                        restaurant.get().getAuthorities()
                );

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }

        return authHeader.substring(7);
    }
}
