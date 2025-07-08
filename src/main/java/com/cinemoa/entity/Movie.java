package com.cinemoa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "movies")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "reservation_rate")
    private BigDecimal reservationRate;

    @Column(name = "rating")
    private BigDecimal rating;

    @Column(name = "director")
    private String director;

    @Column(name = "actors")
    private String actors;

    @Column(name = "genre")
    private String genre;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "likes_count")
    private Integer likesCount;

    @Column(name = "review_count")
    private Integer reviewCount;

    @Column(name = "main_image_url")
    private String mainImageUrl;

    @Column(name = "detail_image_urls")
    private String detailImageUrls;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "audience_count")
    private BigInteger audienceCount;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}