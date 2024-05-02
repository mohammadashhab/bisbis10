package com.att.tdp.bisbis10.objectDTOs;


import com.att.tdp.bisbis10.entities.Dish;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DishDTO {

    private UUID id;

    private String name;

    private String description;

    private float price;

    public void set(Dish dish){
        this.id = dish.getId();
        this.name = dish.getName();
        this.description = dish.getDescription();
        this.price = dish.getPrice();
    }

}
