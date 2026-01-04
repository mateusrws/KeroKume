package com.example.kerokume.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import com.example.kerokume.Models.MenuModel;
import com.example.kerokume.Services.MenuService;

@RestController
@RequestMapping("menu")
public class MenuController {
  @Autowired
  private MenuService menuService;

  @GetMapping
  public List<MenuModel> get(){
    return menuService.get();
  }

  @PostMapping
  public MenuModel create(@RequestBody MenuModel menuModel){
    return menuService.create(menuModel);
  }

  @PutMapping
  public String update(@RequestBody MenuModel menuModel){
    return menuService.update(menuModel);
  }

  @DeleteMapping
  public String delete(@RequestBody UUID id){
    return menuService.delete(id);
  }
}
