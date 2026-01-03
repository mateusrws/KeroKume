package com.example.kerokume.Repositorys;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kerokume.Models.RestaurantModel;

public interface RestaurantRepo extends JpaRepository<RestaurantModel, UUID> {

}
