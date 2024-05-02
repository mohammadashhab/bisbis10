package com.att.tdp.bisbis10.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.*;


@Entity
@Table(name = "restaurants")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class Restaurant {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "UUID", type = org.hibernate.id.uuid.UuidGenerator.class)
    @Column(nullable = false, name = "restaurant_id")
    private UUID id;

    @Column
    private String name;

    @Column
    private float rating;

    @Column
    private boolean isKosher;

    @ElementCollection
    private List<String> cuisines;




    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Set<Dish> dishes;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    private List<Rating> ratings;

    public void addCuisine(String cuisine) {
        if(cuisines == null){
            this.cuisines = new ArrayList<>();
        }
        this.cuisines.add(cuisine);
    }

    public void addDish(Dish dish) {
        if(dishes == null){
            this.dishes =new HashSet<>();
        }
        this.dishes.add(dish);

    }

    public void addOrder(Order order) {
        if(orders == null){
            this.orders =new ArrayList<>();
        }
        this.orders.add(order);
    }
    public void removeDish(Dish dish) {
        this.dishes.remove(dish);

    }

    public void addRating(Rating rating) {
        if(ratings == null){
            this.ratings =new ArrayList<>();
        }
        this.ratings.add(rating);
        int ratingListSize = this.ratings.size();
        float newRating = this.rating * (ratingListSize - 1);
        newRating =  (newRating + rating.getRating())/ ratingListSize;
        this.rating = newRating;

    }

}
