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

import com.example.kerokume.Models.Food.CreateFoodRequest;
import com.example.kerokume.Models.Food.FoodModel;
import com.example.kerokume.Services.FoodService;


@RestController
@RequestMapping("foods")
public class FoodController {
  @Autowired
  private FoodService foodService;

  @GetMapping
  public List<FoodModel> get(){
    return foodService.getAll();
  }

  @GetMapping("/available")
  public List<FoodModel> getAllAvailableTrue(){
    return foodService.getAllAvailableTrue();
  }

  @PostMapping
  public FoodModel create(@Validated @RequestBody CreateFoodRequest food){
    return foodService.create(food);
  }

  @PutMapping
  public String update(@Validated @RequestBody FoodModel food){
    return foodService.update(food);
  }

  @DeleteMapping("{id}")
  public String delete(@Validated @RequestParam UUID id){
    return foodService.delete(id);
  }
}
