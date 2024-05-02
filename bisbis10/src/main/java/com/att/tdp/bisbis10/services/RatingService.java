package com.att.tdp.bisbis10.services;

import com.att.tdp.bisbis10.entities.Rating;
import com.att.tdp.bisbis10.entities.Restaurant;
import com.att.tdp.bisbis10.objectDTOs.RatingDTO;
import com.att.tdp.bisbis10.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RatingService {



    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RestaurantService restaurantService;


    public void saveRating(Rating rating){
        ratingRepository.save(rating);

    }

    public void addRestaurantRating(RatingDTO ratingDTO) throws Exception {
        UUID restaurantID = ratingDTO.getRestaurantId();
        Restaurant restaurant = restaurantService.getRestaurant(restaurantID);
        Rating rating = Rating.builder().build();
        rating.setRating(ratingDTO.getRating());
        rating.setRestaurant(restaurant);
        restaurant.addRating(rating);
        this.saveRating(rating);
        restaurantService.saveRestaurant(restaurant);
    }
}
