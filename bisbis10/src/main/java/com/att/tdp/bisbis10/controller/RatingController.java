package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.objectDTOs.RatingDTO;
import com.att.tdp.bisbis10.services.RatingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RestControllerAdvice
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/ratings")
    public ResponseEntity<String> addRestaurantRating(@RequestBody RatingDTO ratingDTO) throws Exception {
            ratingService.addRestaurantRating(ratingDTO);
            return new ResponseEntity<>(HttpStatus.OK);

    }

    }