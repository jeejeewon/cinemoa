package com.cinemoa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private Long movieId;
    private String title;
    private String content;
    private BigDecimal reservationRate;
    private BigDecimal rating;
    private String director;
    private String actors;
    private String genre;
    private LocalDate releaseDate;
    private Integer likesCount;
    private Integer reviewCount;
    private String mainImageUrl;
    private String detailImageUrls;
    private String videoUrl;
    private BigInteger audienceCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

// 목록 조회용 간소화된 DTO
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class MovieListDto {
    private Long movieId;
    private String title;
    private BigDecimal reservationRate;
    private BigDecimal rating;
    private String director;
    private String genre;
    private LocalDate releaseDate;
    private Integer likesCount;
    private Integer reviewCount;
    private String mainImageUrl;
    private BigInteger audienceCount;
}