package com.example.kerokume.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kerokume.Models.RestaurantModel;
import com.example.kerokume.Repositorys.RestaurantRepo;

@Service
public class RestaurantService {

  @Autowired
  private RestaurantRepo restaurantRepo;

  public List<RestaurantModel> getAllRestaurants() {
    return restaurantRepo.findAll();
  }

  public String createRestaurant(RestaurantModel restaurant) {
    if (restaurantRepo.findById(restaurant.getId()).isPresent()) {
      return "Restaurant already exists";
    }
    restaurantRepo.save(restaurant);
    return "Restaurant created successfully";
  }

  public String updateRestaurant(RestaurantModel restaurant) {
    if (restaurantRepo.findById(restaurant.getId()).isPresent()) {
      restaurantRepo.save(restaurant);
      return "Restaurant updated successfully";
    }
    return "Restaurant not found";
  }

  public String deleteRestaurant(UUID id) {
    if (restaurantRepo.findById(id).isPresent()) {
      restaurantRepo.deleteById(id);
      return "Restaurant deleted successfully";
    }
    return "Restaurant not found";
  }

}
