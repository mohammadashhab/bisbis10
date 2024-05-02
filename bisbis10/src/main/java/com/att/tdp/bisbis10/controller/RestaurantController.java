package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.objectDTOs.DishDTO;
import com.att.tdp.bisbis10.objectDTOs.RestaurantDTO;
import com.att.tdp.bisbis10.services.DishService;
import com.att.tdp.bisbis10.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class RestaurantController {


    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    @GetMapping("/restaurants")
    public ResponseEntity<Iterable<RestaurantDTO>> getAllRestaurants() {
        return new ResponseEntity<>(restaurantService.getAllRestaurants(), HttpStatus.OK);
    }

    @GetMapping(value = "/restaurants", params = "cuisine")
    public ResponseEntity<Iterable<RestaurantDTO>> getRestaurantsByCuisine(@RequestParam("cuisine") String cuisine) {
        return new ResponseEntity<>(restaurantService.getRestaurantsByCuisine(cuisine), HttpStatus.OK);
    }


    @GetMapping("/restaurants/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurant(@PathVariable("id") final String id) throws Exception {
        UUID uuid = UUID.fromString(id);
        return new ResponseEntity<>(restaurantService.getRestaurantDTO(uuid), HttpStatus.OK);
    }


    @PostMapping("/restaurants")
    public ResponseEntity<Void> addRestaurant(@RequestBody final RestaurantDTO restaurantDTO) {
        restaurantService.addRestaurant(restaurantDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping("/restaurants/{id}")
    public ResponseEntity<String> updateRestaurant(@PathVariable("id") final String id,
                                                 @RequestBody final RestaurantDTO restaurantDTO) throws Exception {
        UUID restaurantId = UUID.fromString(id);
        restaurantService.updateRestaurant(restaurantId, restaurantDTO);
        return new ResponseEntity<>(HttpStatus.OK);

}

    @DeleteMapping("/restaurants/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable("id") final String id) throws Exception {
        UUID restaurantID = UUID.fromString(id);
        restaurantService.deleteRestaurant(restaurantID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

    @PostMapping("/restaurants/{id}/dishes")
    public ResponseEntity<Void> addDish(@PathVariable String id, @RequestBody DishDTO dishDTO) throws Exception {
        UUID restaurantID = UUID.fromString(id);
        dishService.addDish(restaurantID, dishDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/restaurants/{id}/dishes/{dishId}")
    public ResponseEntity<Void> updateDish(@PathVariable String id, @PathVariable String dishId,@RequestBody final DishDTO dishDTO) throws Exception {
        UUID dishID = UUID.fromString(dishId);
        UUID restaurantID = UUID.fromString(id);
        dishService.updateDish(restaurantID, dishID, dishDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/restaurants/{id}/dishes/{dishId}")
    public ResponseEntity<Void> deleteDish(@PathVariable String id, @PathVariable String dishId) throws Exception {
        UUID restaurantID = UUID.fromString(id);
        UUID dishID = UUID.fromString(dishId);
        dishService.deleteDish(restaurantID, dishID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/restaurants/{id}/dishes")
    public ResponseEntity<Iterable<DishDTO>> getRestaurantDishes(@PathVariable String id) throws Exception {
        UUID restaurantID = UUID.fromString(id);
        return new ResponseEntity<>(dishService.getRestaurantDishes(restaurantID), HttpStatus.OK);


    }
}
