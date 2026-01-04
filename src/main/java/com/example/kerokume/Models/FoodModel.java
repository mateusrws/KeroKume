package com.example.kerokume.Models;

import java.util.UUID;

import com.example.kerokume.Enums.FoodCategory;

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

  private String name;
  private String description;
  private double price;
  private String imgPath;
  private FoodCategory foodCategory;
  
  private boolean isAvailable;
  
  @ManyToOne
  private MenuModel menuFather;

  public boolean getIsAvailable(){
    return this.isAvailable;
  }
}