package com.example.kerokume.Repositorys;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.kerokume.Models.Restaurant.RestaurantModel;

public interface RestaurantRepo extends JpaRepository<RestaurantModel, UUID> {
  UserDetails findByName(String name);

  Optional<RestaurantModel> findResByName(String name);
}
