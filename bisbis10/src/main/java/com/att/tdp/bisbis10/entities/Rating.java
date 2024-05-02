package com.att.tdp.bisbis10.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.UUID;


@Entity
@Table(name = "rating")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class Rating {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "UUID", type = org.hibernate.id.uuid.UuidGenerator.class)
    @Column(nullable = false, name = "rating_id")
    private UUID id;

    @Column
    private float rating;

    @Column
    private Instant creationTime;


    @ManyToOne()
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;




}


