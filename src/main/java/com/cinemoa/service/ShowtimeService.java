package com.cinemoa.service;

import com.cinemoa.entity.Showtime;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ShowtimeService {
    List<String> getAvailableDatesByMovieAndCinema(Long movieId, Long cinemaId);
    List<Showtime> getShowtimesByMovieCinemaAndDate(Long movieId, Long cinemaId, LocalDate date);
    Optional<Showtime> getShowtimeById(Long showtimeId); // 추가
}