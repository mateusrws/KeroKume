package com.example.kerokume.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.kerokume.Models.RestaurantModel;
import com.example.kerokume.Repositorys.RestaurantRepo;
import com.example.kerokume.Services.RestaurantService;

@RestController
@RequestMapping("restaurant")
public class RestaurantController {
  @Autowired
  private RestaurantService restaurantService;

  @GetMapping
  public List<RestaurantModel> get(){
    return restaurantService.getAllRestaurants();
  }

  @PostMapping
  public String create(@RequestBody RestaurantModel restaurant){
    return restaurantService.createRestaurant(restaurant);
  }

  @PutMapping
  public String update(@RequestBody RestaurantModel restaurant){
    return restaurantService.updateRestaurant(restaurant);
  }

  @DeleteMapping
  public String delete(@RequestBody UUID id){
    return restaurantService.deleteRestaurant(id);
  }
}
