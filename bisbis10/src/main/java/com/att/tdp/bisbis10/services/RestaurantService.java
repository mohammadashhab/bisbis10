package com.att.tdp.bisbis10.services;


import com.att.tdp.bisbis10.entities.Restaurant;
import com.att.tdp.bisbis10.objectDTOs.RestaurantDTO;
import com.att.tdp.bisbis10.repositories.DishRepository;
import com.att.tdp.bisbis10.repositories.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RestaurantService {


    @Autowired
    private RestaurantRepository restaurantRepository;



    public void saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    public void addRestaurant(RestaurantDTO restaurantDTO){
        Restaurant restaurant = Restaurant.builder().build();
        restaurant.setKosher(restaurantDTO.isKosher());
        restaurant.setName(restaurantDTO.getName());
        restaurant.setCuisines(restaurantDTO.getCuisines());
        saveRestaurant(restaurant);
    }

    public Iterable<RestaurantDTO> getAllRestaurants(){
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return getRestaurantDTOS(restaurants);
    }

    public Iterable<RestaurantDTO> getRestaurantsByCuisine(String cuisine) {
        List<Restaurant> restaurants = restaurantRepository.findByCuisine(cuisine);

        return getRestaurantDTOS(restaurants);
    }

    public RestaurantDTO getRestaurantDTO(UUID restaurantID) throws EntityNotFoundException {
        try {
        Restaurant restaurant = this.getRestaurant(restaurantID);
        RestaurantDTO restaurantDTO = RestaurantDTO.builder().build();
        restaurantDTO.set(restaurant);
        return restaurantDTO;
        } catch (Exception e) {
            throw new EntityNotFoundException();
        }
    }

    protected Restaurant getRestaurant(UUID restaurantID) throws EntityNotFoundException {
        Optional<Restaurant> searchResult =restaurantRepository.findById(restaurantID);
        if(searchResult.isEmpty()){
            throw new EntityNotFoundException();
        }
        return searchResult.get();
    }


    public void updateRestaurant(UUID restaurantID, RestaurantDTO restaurantDTO) throws EntityNotFoundException {
        Optional<Restaurant> searchResult = restaurantRepository.findById(restaurantID);
        if(searchResult.isEmpty()){
            throw new EntityNotFoundException();
        }
        Restaurant restaurant = searchResult.get();
        if(restaurantDTO.getCuisines() != null){
            restaurant.setCuisines(restaurantDTO.getCuisines());
        }
        if(restaurantDTO.getName() != null){
            restaurant.setName(restaurantDTO.getName());
        }

        this.saveRestaurant(restaurant);
    }

    public void deleteRestaurant(UUID restaurantID) throws EntityNotFoundException {
        Optional<Restaurant> searchResult = restaurantRepository.findById(restaurantID);
        if(searchResult.isEmpty()){
            throw new EntityNotFoundException();
        }
        Restaurant restaurant = searchResult.get();
        restaurantRepository.delete(restaurant);
    }


    private Iterable<RestaurantDTO> getRestaurantDTOS(List<Restaurant> restaurants) {
        List<RestaurantDTO> restaurantDTOS = new ArrayList<>();
        for(Restaurant restaurant : restaurants){
            RestaurantDTO restaurantDTO = RestaurantDTO.builder().build();
            restaurantDTO.set(restaurant);
            restaurantDTOS.add(restaurantDTO);
        }
        return restaurantDTOS;
    }


}
