package com.cinemoa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long reviewId;
    private Long movieId;
    private Long userId;
    private String content;
    private Boolean isPositive; // true: 좋았어요, false: 별로였어요
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 사용자 이름 표시용 (선택적)
    private String userName;
}