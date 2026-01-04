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
import org.springframework.web.bind.annotation.RestController;

import com.example.kerokume.Models.FoodModel;
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

  @GetMapping("available") 
  public List<FoodModel> getAllAvailableTrue(){
    return foodService.getAllAvailableTrue();
  }

  @PostMapping
  public String create(@RequestBody FoodModel food){
    return foodService.create(food);
  }

  @PutMapping
  public String update(@RequestBody FoodModel food){
    return foodService.update(food);
  }

  @DeleteMapping
  public String delete(@RequestBody UUID id){
    return foodService.delete(id);
  }
}
