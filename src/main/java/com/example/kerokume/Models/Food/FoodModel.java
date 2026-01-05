package com.example.kerokume.Models.Food;

import java.util.UUID;

import org.antlr.v4.runtime.misc.NotNull;

import com.example.kerokume.Enums.FoodCategory;
import com.example.kerokume.Models.Menu.MenuModel;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Foods")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FoodModel {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  
  @Column(nullable = false)  
  private String name;
  @Column(nullable = false)  
  private String description;
  @Column(nullable = false)  
  private double price;
  private String imgPath;
  @Column(nullable = false)  
  private FoodCategory foodCategory;
  
  private boolean isAvailable;
  
  @ManyToOne
  private MenuModel menuFather;

  public boolean getIsAvailable(){
    return this.isAvailable;
  }

  public FoodModel(String name, String description, double price,
    String imgPath, FoodCategory foodCategory,
    boolean isAvailable, MenuModel menuFather) {

    this.name = name;
    this.description = description;
    this.price = price;
    this.imgPath = imgPath;
    this.foodCategory = foodCategory;
    this.isAvailable = isAvailable;
    this.menuFather = menuFather;
    }

}