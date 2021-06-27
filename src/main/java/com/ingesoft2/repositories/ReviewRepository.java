package com.ingesoft2.repositories;

import com.ingesoft2.models.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{
  
}
