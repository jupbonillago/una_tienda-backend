package com.ingesoft2.services;

import java.util.List;

import com.ingesoft2.models.Review;
import com.ingesoft2.repositories.ReviewRepository;

import org.springframework.stereotype.Service;

@Service
public class ReviewService {
  
  private final ReviewRepository reviewRepository;


  public ReviewService(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }


  public List<Review> getReviews(){
    return reviewRepository.findAll();
  }
}
