package com.att.tdp.bisbis10.objectDTOs;

import com.att.tdp.bisbis10.entities.Restaurant;
import com.att.tdp.bisbis10.entities.Dish;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RestaurantDTO {

    private UUID id;

    private String name;

    private float rating;

    private boolean isKosher;

    private List<String> cuisines;

    private List<DishDTO> dishes;

    public void set(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.rating = restaurant.getRating();
        this.isKosher = restaurant.isKosher();
        this.cuisines = new ArrayList<>();
        this.cuisines.addAll(restaurant.getCuisines());

        this.dishes = new ArrayList<>();
        for (Dish dish : restaurant.getDishes()) {
            DishDTO dishDTO = new DishDTO();
            dishDTO.set(dish);
            this.dishes.add(dishDTO);

        }
    }
}
