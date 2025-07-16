package com.cinemoa.repository;

import com.cinemoa.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 영화별 리뷰 목록 조회
    List<Review> findByMovieIdOrderByCreatedAtDesc(Long movieId);

    // 영화별 긍정 리뷰 수 조회
    @Query("SELECT COUNT(r) FROM Review r WHERE r.movieId = :movieId AND r.isPositive = true")
    long countPositiveReviewsByMovieId(@Param("movieId") Long movieId);

    // 영화별 부정 리뷰 수 조회
    @Query("SELECT COUNT(r) FROM Review r WHERE r.movieId = :movieId AND r.isPositive = false")
    long countNegativeReviewsByMovieId(@Param("movieId") Long movieId);

    // 영화별 전체 리뷰 수 조회
    long countByMovieId(Long movieId);
}