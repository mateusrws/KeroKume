package com.example.kerokume.Models.Food;

import com.example.kerokume.Enums.FoodCategory;
import com.example.kerokume.Models.Menu.MenuModel;

public record CreateFoodRequest(
  String name,
  String description,
  double price,
  String imgPath,
  FoodCategory foodCategory,
  boolean isAvailable,
  MenuModel menuFather) {}
