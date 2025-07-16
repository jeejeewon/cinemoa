package com.cinemoa.api;

import com.cinemoa.dto.ReviewDto;
import com.cinemoa.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewApiController {

    private final ReviewService reviewService;

    // 영화별 리뷰 목록 조회
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<ReviewDto>> getReviewsByMovieId(@PathVariable Long movieId) {
        List<ReviewDto> reviews = reviewService.getReviewsByMovieId(movieId);
        return ResponseEntity.ok(reviews);
    }

    // 영화별 긍정 평가 비율 조회
    @GetMapping("/movie/{movieId}/positive-percentage")
    public ResponseEntity<Integer> getPositivePercentage(@PathVariable Long movieId) {
        int percentage = reviewService.getPositivePercentage(movieId);
        return ResponseEntity.ok(percentage);
    }

    // 리뷰 저장
    @PostMapping
    public ResponseEntity<ReviewDto> saveReview(@RequestBody ReviewDto reviewDto) {
        ReviewDto savedReview = reviewService.saveReview(reviewDto);
        return ResponseEntity.ok(savedReview);
    }

    // 리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}