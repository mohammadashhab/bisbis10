package com.att.tdp.bisbis10.services;


import com.att.tdp.bisbis10.entities.Dish;
import com.att.tdp.bisbis10.entities.Restaurant;
import com.att.tdp.bisbis10.objectDTOs.DishDTO;
import com.att.tdp.bisbis10.repositories.DishRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DishService {


    @Autowired
    private DishRepository dishRepo;

    @Autowired
    private RestaurantService restaurantService;

    public void saveDish(Dish dish) {
        this.dishRepo.save(dish);

    }

    public void deleteDish(Dish dish) {
        this.dishRepo.delete(dish);
    }

    public Dish getDish(UUID id) throws Exception {
        Optional<Dish> searchResult = this.dishRepo.findById(id);
        if (searchResult.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return searchResult.get();
    }


    public void addDish(UUID restaurantID, DishDTO dishDTO) throws Exception {
        Dish dish = Dish.builder().build();
        dish.setDescription(dishDTO.getDescription());
        dish.setName(dishDTO.getName());
        dish.setPrice(dishDTO.getPrice());
        Restaurant restaurant = restaurantService.getRestaurant(restaurantID);
        dish.setRestaurant(restaurant);
        this.dishRepo.save(dish);
        restaurant.addDish(dish);
        restaurantService.saveRestaurant(restaurant);
    }

    public void updateDish(UUID restaurantID, UUID dishID, DishDTO dishDTO) throws Exception {
        Dish dish = getDish(dishID);
        dish.setDescription(dishDTO.getDescription());
        dish.setPrice(dishDTO.getPrice());
        if(dishDTO.getName() != null){
            dish.setName(dishDTO.getName());
        }
        this.saveDish(dish);
    }


    public void deleteDish(UUID restaurantID, UUID dishID) throws Exception {
        Dish dish = getDish(dishID);
        Restaurant restaurant = restaurantService.getRestaurant(restaurantID);
        restaurant.removeDish(dish);
        restaurantService.saveRestaurant(restaurant);
        this.deleteDish(dish);

    }


    public Iterable<DishDTO> getRestaurantDishes(UUID restaurantID) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurant(restaurantID);
        Set<Dish> dishes = restaurant.getDishes();

        List<DishDTO> dishDTOs = new ArrayList<>();
        for(Dish dish : dishes){
            DishDTO dishDTO = DishDTO.builder().build();
            dishDTO.set(dish);
            dishDTOs.add(dishDTO);
        }
        return dishDTOs;

    }

}
