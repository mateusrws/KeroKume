package com.example.kerokume.Repositorys;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kerokume.Models.Food.FoodModel;
import com.example.kerokume.Models.Menu.MenuModel;

public interface FoodRepo extends JpaRepository<FoodModel, UUID> {
  List<FoodModel> findByIsAvailableTrue();
  boolean existsByNameAndMenuFather(String name, MenuModel menuFather);
}