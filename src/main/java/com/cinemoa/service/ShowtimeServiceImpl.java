package com.cinemoa.service;

import com.cinemoa.entity.Showtime;
import com.cinemoa.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShowtimeServiceImpl implements ShowtimeService {

    private final ShowtimeRepository showtimeRepository;

    @Autowired
    public ShowtimeServiceImpl(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }

    @Override
    public List<String> getAvailableDatesByMovieAndCinema(Long movieId, Long cinemaId) {
        // 해당 영화와 영화관에 대한 모든 상영 시간 조회
        // movie_movieId로 수정 (movie 엔티티의 movieId 필드 참조)
        List<Showtime> showtimes = showtimeRepository.findByMovie_MovieIdAndScreen_Cinema_CinemaIdOrderByStartTimeAsc(movieId, cinemaId);

        // 날짜만 추출하여 중복 제거 후 반환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return showtimes.stream()
                .map(showtime -> showtime.getStartTime().toLocalDate().format(formatter))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Showtime> getShowtimesByMovieCinemaAndDate(Long movieId, Long cinemaId, LocalDate date) {
        // 해당 날짜의 시작과 끝 시간 설정
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        // 해당 영화, 영화관, 날짜에 대한 상영 시간 조회
        // movie_movieId와 screen_cinema_cinemaId로 수정 (엔티티 관계 경로 명확히 표현)
        return showtimeRepository.findByMovie_MovieIdAndScreen_Cinema_CinemaIdAndStartTimeBetweenOrderByStartTimeAsc(
                movieId, cinemaId, startOfDay, endOfDay);
    }

    @Override
    public Optional<Showtime> getShowtimeById(Long showtimeId) {
        return showtimeRepository.findById(showtimeId);
    }
}