package com.cinemoa.service.impl;

import com.cinemoa.dto.ReviewDto;
import com.cinemoa.entity.Review;
import com.cinemoa.repository.ReviewRepository;
import com.cinemoa.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public ReviewDto saveReview(ReviewDto reviewDto) {
        Review review = convertToEntity(reviewDto);
        Review savedReview = reviewRepository.save(review);
        return convertToDto(savedReview);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDto> getReviewsByMovieId(Long movieId) {
        List<Review> reviews = reviewRepository.findByMovieIdOrderByCreatedAtDesc(movieId);
        return reviews.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReviewDto> getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .map(this::convertToDto);
    }

    @Override
    @Transactional
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    @Transactional(readOnly = true)
    public int getPositivePercentage(Long movieId) {
        long totalReviews = reviewRepository.countByMovieId(movieId);
        if (totalReviews == 0) {
            return 0;
        }

        long positiveReviews = reviewRepository.countPositiveReviewsByMovieId(movieId);
        return (int) ((positiveReviews * 100) / totalReviews);
    }

    // Entity -> DTO 변환
    private ReviewDto convertToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        BeanUtils.copyProperties(review, reviewDto);
        return reviewDto;
    }

    // DTO -> Entity 변환
    private Review convertToEntity(ReviewDto reviewDto) {
        Review review = new Review();
        BeanUtils.copyProperties(reviewDto, review);
        return review;
    }
}