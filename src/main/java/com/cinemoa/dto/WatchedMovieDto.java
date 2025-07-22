package com.cinemoa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WatchedMovieDto {
    private String title;           // 영화 제목
    private String poster;          // 포스터 파일명
    private String grade;           // 관람 등급 (예: 15, 전체, 청불)
    private String date;            // 상영 날짜 (yyyy.MM.dd)
    private String day;             // 요일 (예: 월, 화, 수)
    private String time;            // 상영 시간 (예: 14:00~16:30)
    private String cinemaName;      // 영화관 이름
    private String screenName;      // 상영관 이름
    private String seatType;        // 좌석 타입 (일반, 리클라이너 등)
    private int personCount;        // 관람 인원 수
    private boolean hasReview;      // 관람평 작성 여부
    private Long movieId;            // 리뷰 작성 페이지 링크용
    private String reviewContent;   // 관람평 내용 (null 가능)

}
