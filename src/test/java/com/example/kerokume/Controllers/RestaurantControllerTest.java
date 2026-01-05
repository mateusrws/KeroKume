package com.example.kerokume.Controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import com.example.kerokume.Models.Restaurant.RestaurantModel;
import com.example.kerokume.Services.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private RestaurantService restaurantService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void testGet() throws Exception {
    when(restaurantService.getAllRestaurants())
        .thenReturn(List.of(new RestaurantModel()));

    mockMvc.perform(get("/restaurants"))
        .andExpect(status().isOk());
  }

  @Test
  void testCreate() throws Exception {
    RestaurantModel restaurant = new RestaurantModel();
    restaurant.setName("Test Restaurant");
    restaurant.setPassword("123");

    when(restaurantService.createRestaurant(restaurant))
        .thenReturn("Restaurant created successfully");

    mockMvc.perform(post("/restaurants")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(restaurant)))
        .andExpect(status().isOk());
  }

  @Test
  void testUpdate() throws Exception {
    RestaurantModel restaurant = new RestaurantModel();
    restaurant.setId(UUID.randomUUID());
    restaurant.setName("Updated Restaurant");

    when(restaurantService.updateRestaurant(restaurant))
        .thenReturn("Restaurant updated successfully");

    mockMvc.perform(put("/restaurants")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(restaurant)))
        .andExpect(status().isOk());
  }

  @Test
  void testDelete() throws Exception {
    UUID id = UUID.randomUUID();

    when(restaurantService.deleteRestaurant(id))
        .thenReturn("Restaurant deleted successfully");

    mockMvc.perform(delete("/restaurants")
        .param("id", id.toString()))
        .andExpect(status().isOk());
  }
}
