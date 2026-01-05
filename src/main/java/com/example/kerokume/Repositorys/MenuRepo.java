package com.example.kerokume.Repositorys;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kerokume.Models.Menu.MenuModel;
import com.example.kerokume.Models.Restaurant.RestaurantModel;

public interface MenuRepo extends JpaRepository<MenuModel, UUID>{
  public List<MenuModel> findAllByRestaurant(RestaurantModel restaurant);
}
