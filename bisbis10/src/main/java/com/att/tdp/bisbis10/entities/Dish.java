package com.att.tdp.bisbis10.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "dishes")

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class Dish {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "UUID", type = org.hibernate.id.uuid.UuidGenerator.class)
    @Column(nullable = false, name = "dish_id")
    private UUID id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private float price;

    @ManyToOne()
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;


    @ManyToMany(mappedBy = "dishes")
    private List<Order> orders = new ArrayList<>();


    public void addOrder(Order order) {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        orders.add(order);
    }


}
