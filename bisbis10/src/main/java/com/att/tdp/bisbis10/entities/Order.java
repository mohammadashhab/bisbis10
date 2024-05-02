package com.att.tdp.bisbis10.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class Order {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "UUID", type = org.hibernate.id.uuid.UuidGenerator.class)
    @Column(nullable = false, name = "order_id")
    private UUID id;

    @Column
    private float totalPrice;

    @ManyToMany
    @JoinTable(name = "order_dish",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id"))
    private List<Dish> dishes;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;


    public void addDish(Dish dish) {
        if(dishes == null){
            this.dishes = new ArrayList<>();
        }
        dishes.add(dish);
    }



}
