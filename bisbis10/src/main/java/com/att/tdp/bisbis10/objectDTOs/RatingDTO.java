package com.att.tdp.bisbis10.objectDTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RatingDTO {

    private UUID restaurantId;
    private float rating;
}