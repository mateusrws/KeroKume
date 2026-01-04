package com.example.kerokume.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kerokume.Models.MenuModel;
import com.example.kerokume.Repositorys.MenuRepo;

@Service
public class MenuService {
  @Autowired
  private MenuRepo menuRepo;

  public List<MenuModel> get(){
    return menuRepo.findAll();
  }

  public String create(MenuModel menuModel){
    if(menuRepo.findById(menuModel.getId()).isPresent()){
      menuRepo.save(menuModel);
      return "Menu created successfully";
    }else{
      return "Already exists this Menu";
    }
  }

  public String update(MenuModel menuModel){
    if(menuRepo.findById(menuModel.getId()).isPresent()){
      menuRepo.save(menuModel);
      return "Menu updated successfully";
    }else {
      return "Menu not exist";
    }
  }

  public String delete(UUID id){
    if(menuRepo.findById(id).isPresent()){
      menuRepo.deleteById(id);
      return "Menu deleted successfully";
    }else {
      return "Menu not exist";
    }
  }
}
