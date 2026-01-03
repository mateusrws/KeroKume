package com.example.kerokume.Models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "restaurants")
@Entity(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantModel {
  
  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
  private UUID id;

  private String name;
  private String password;
  private String imgPathProfile;
  private String description;
}
