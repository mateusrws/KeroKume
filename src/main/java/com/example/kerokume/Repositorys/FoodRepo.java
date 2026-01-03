package com.example.kerokume.Repositorys;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kerokume.Models.FoodModel;

public interface FoodRepo extends JpaRepository<FoodModel, UUID> {
  default List<FoodModel> findByIsAvailableTrue(){

    List<FoodModel> list = findAll();
    List<FoodModel> listIsAvailable = new ArrayList<FoodModel>();

    list.forEach(i -> {
      if(i.getIsAvailable()){
        listIsAvailable.add(i);
      }else{return;}
    });

    return listIsAvailable;
  }
}
