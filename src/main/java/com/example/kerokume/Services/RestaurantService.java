package com.example.kerokume.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.kerokume.Models.Restaurant.RestaurantModel;
import com.example.kerokume.Repositorys.RestaurantRepo;

@Service
public class RestaurantService {

  @Autowired
  private RestaurantRepo restaurantRepo;

  @Autowired 
  private PasswordEncoder passwordEncoder;


  public List<RestaurantModel> getAllRestaurants() {
    return restaurantRepo.findAll();
  }

  public String createRestaurant(RestaurantModel restaurant) {
    if (restaurantRepo.findResByName(restaurant.getName()).isPresent()) {
      return "Restaurant already exists";
    }
    restaurant.setPassword(passwordEncoder.encode(restaurant.getPassword()));
    restaurantRepo.save(restaurant);
    return "Restaurant created successfully";
  }

  public String updateRestaurant(RestaurantModel restaurant) {
    Optional<RestaurantModel> existing = restaurantRepo.findById(restaurant.getId());
    if (existing.isPresent()) {
        RestaurantModel existingRestaurant = existing.get();
        
        
        existingRestaurant.setName(restaurant.getName());
        existingRestaurant.setDescription(restaurant.getDescription());
        existingRestaurant.setImgPathProfile(restaurant.getImgPathProfile());
        
        if (restaurant.getPassword() != null && 
            !restaurant.getPassword().isEmpty() &&
            !passwordEncoder.matches(restaurant.getPassword(), existingRestaurant.getPassword())) {
            existingRestaurant.setPassword(passwordEncoder.encode(restaurant.getPassword()));
        }
        
        restaurantRepo.save(existingRestaurant);
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
