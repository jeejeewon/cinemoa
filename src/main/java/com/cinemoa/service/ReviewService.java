package com.cinemoa.service;

import com.cinemoa.dto.ReviewDto;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    ReviewDto saveReview(ReviewDto reviewDto);
    List<ReviewDto> getReviewsByMovieId(Long movieId);
    Optional<ReviewDto> getReviewById(Long reviewId);
    void deleteReview(Long reviewId);
    int getPositivePercentage(Long movieId);
}