package com.example.kerokume.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.kerokume.Models.Restaurant.RestaurantModel;
import com.example.kerokume.Services.RestaurantService;

@RestController
@RequestMapping("restaurants")
public class RestaurantController {
  @Autowired
  private RestaurantService restaurantService;

  @GetMapping
  public List<RestaurantModel> get(){
    return restaurantService.getAllRestaurants();
  }

  @PostMapping
  public String create(@Validated @RequestBody RestaurantModel restaurant){
    return restaurantService.createRestaurant(restaurant);
  }

  @PutMapping
  public String update(@Validated @RequestBody RestaurantModel restaurant){
    return restaurantService.updateRestaurant(restaurant);
  }

  @DeleteMapping("{id}")
  public String delete(@Validated @RequestParam UUID id){
    return restaurantService.deleteRestaurant(id);
  }
}
