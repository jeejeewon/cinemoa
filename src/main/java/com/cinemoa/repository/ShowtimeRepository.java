package com.cinemoa.repository;

import com.cinemoa.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

    // movieId -> movie_movieId로 수정 (Movie 엔티티의 movieId 필드 참조)
    // cinemaId -> cinema_cinemaId로 수정 (Cinema 엔티티의 cinemaId 필드 참조)
    List<Showtime> findByMovie_MovieIdAndScreen_Cinema_CinemaIdOrderByStartTimeAsc(
            Long movieId, Long cinemaId);

    // 마찬가지로 경로 수정
    List<Showtime> findByMovie_MovieIdAndScreen_Cinema_CinemaIdAndStartTimeBetweenOrderByStartTimeAsc(
            Long movieId, Long cinemaId, LocalDateTime startTime, LocalDateTime endTime);
}