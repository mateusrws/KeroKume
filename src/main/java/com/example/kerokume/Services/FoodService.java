package com.example.kerokume.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kerokume.Repositorys.FoodRepo;
import com.example.kerokume.Models.FoodModel;


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

  public String create(FoodModel food){

    Optional<FoodModel> foodPresents = foodRepo.findById(food.getId());


    if(food.getMenuFather() != null){
      if (foodPresents.isPresent() && foodPresents.get().getMenuFather().equals(food.getMenuFather()) || !foodPresents.isPresent()) 
      {
        foodRepo.save(food);
        return "Create food succefully";
      }
      else
      {
        return "This product already exist";
      }
    }
    else 
    {
      return "MenuFather is empty";
    }
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