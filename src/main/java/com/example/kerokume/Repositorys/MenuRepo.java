package com.example.kerokume.Repositorys;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kerokume.Models.MenuModel;

public interface MenuRepo extends JpaRepository<MenuModel, UUID>{

}
