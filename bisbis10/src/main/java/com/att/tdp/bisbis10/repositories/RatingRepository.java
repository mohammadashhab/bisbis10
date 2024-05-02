package com.att.tdp.bisbis10.repositories;

import com.att.tdp.bisbis10.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID> {
}
