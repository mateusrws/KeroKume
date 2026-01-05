package com.example.kerokume.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kerokume.Models.Food.CreateFoodRequest;
import com.example.kerokume.Models.Food.FoodModel;
import com.example.kerokume.Repositorys.FoodRepo;


@Service
public class FoodService {
  @Autowired
  private FoodRepo foodRepo;


  @Autowired

  public List<FoodModel> getAll(){
    return foodRepo.findAll();
  }

  public List<FoodModel> getAllAvailableTrue(){
    return foodRepo.findByIsAvailableTrue();
  }

  public FoodModel create(CreateFoodRequest foodDto) {

      if (foodDto.menuFather() == null) {
          throw new IllegalArgumentException("MenuFather is required");
      }

      boolean exists = foodRepo.existsByNameAndMenuFather(
        foodDto.name(),
        foodDto.menuFather()
      );

      if (exists) {
          throw new IllegalStateException("This product already exists in this menu");
      }

      FoodModel food = new FoodModel(
        foodDto.name(),
        foodDto.description(),
        foodDto.price(),
        foodDto.imgPath(),
        foodDto.foodCategory(),
        foodDto.isAvailable(),
        foodDto.menuFather()
      );    
    

      return foodRepo.save(food);
  }

  public String update(FoodModel food){
    if(foodRepo.findById(food.getId()).isPresent())
    {
      foodRepo.save(food);
      return "Food updated succefully";
    }else
    {
      return "Food not exist";
    }
  }

  public String delete(UUID id){
    if(foodRepo.findById(id).isPresent())
    {
      foodRepo.deleteById(id);
      return "Product deleted succesfully";
    }else
    {
      return "This Product not exist";
    }
  }
}