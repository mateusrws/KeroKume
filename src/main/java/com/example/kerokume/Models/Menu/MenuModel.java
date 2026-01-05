package com.example.kerokume.Models.Menu;

import java.util.List;
import java.util.UUID;

import com.example.kerokume.Models.Food.FoodModel;
import com.example.kerokume.Models.Restaurant.RestaurantModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Menus")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuModel {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(unique = true, nullable = false)  
  private String name;

  @ManyToOne
  private RestaurantModel restaurant;
  
  @OneToMany(mappedBy = "menuFather")
  private List<FoodModel> foods;
}
