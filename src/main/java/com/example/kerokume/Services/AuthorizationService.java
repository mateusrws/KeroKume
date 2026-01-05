package com.example.kerokume.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.kerokume.Repositorys.RestaurantRepo;

@Service
public class AuthorizationService implements UserDetailsService{

  @Autowired
  private RestaurantRepo restaurantRepo;

  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    // TODO Auto-generated method stub
    return restaurantRepo.findByName(name);
  }

}
