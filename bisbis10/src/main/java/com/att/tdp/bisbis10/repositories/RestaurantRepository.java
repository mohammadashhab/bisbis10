package com.att.tdp.bisbis10.repositories;

import com.att.tdp.bisbis10.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {

    @Query("SELECT r FROM Restaurant r WHERE :cuisine MEMBER OF r.cuisines")
    List<Restaurant> findByCuisine(String cuisine);


}
