package com.example.kerokume.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.kerokume.Models.Menu.MenuModel;
import com.example.kerokume.Repositorys.MenuRepo;

@Service
public class MenuService {

  private final MenuRepo menuRepo;

  public MenuService(MenuRepo menuRepo) {
    this.menuRepo = menuRepo;
  }

  public List<MenuModel> getAll() {
    return menuRepo.findAll();
  }

  public MenuModel create(MenuModel menuModel) {

    // Garante criação
    menuModel.setId(null);
    return menuRepo.save(menuModel);
  }

  public MenuModel update(MenuModel menuModel) {

    if (!menuRepo.existsById(menuModel.getId())) {
      throw new IllegalStateException("Menu not found");
    }

    return menuRepo.save(menuModel);
  }

  public String delete(UUID id) {

    if (!menuRepo.existsById(id)) {
      throw new IllegalStateException("Menu not found");
    }
    menuRepo.deleteById(id);
    return "Menu deleted successfully";
  }
}
