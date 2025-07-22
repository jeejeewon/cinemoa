package com.cinemoa.controller;

import com.cinemoa.dto.MovieDto;
import com.cinemoa.entity.Cinema;
import com.cinemoa.entity.Movie;
import com.cinemoa.entity.Showtime;
import com.cinemoa.repository.ReservationSeatRepository;
import com.cinemoa.repository.SeatRepository;
import com.cinemoa.service.CinemaService;
import com.cinemoa.service.MovieService;
import com.cinemoa.service.ShowtimeService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.HashMap;

@Controller
@RequestMapping("/ticketing")
public class TicketingController {

    private final MovieService movieService;
    private final CinemaService cinemaService;
    private final ShowtimeService showtimeService;

    // ✅추가
    private final SeatRepository seatRepository;
    private final ReservationSeatRepository reservationSeatRepository;

    @Autowired
    public TicketingController(MovieService movieService,
                               CinemaService cinemaService,
                               ShowtimeService showtimeService,
                               SeatRepository seatRepository,//✅추가
                               ReservationSeatRepository reservationSeatRepository) {
        this.movieService = movieService;
        this.cinemaService = cinemaService;
        this.showtimeService = showtimeService;
        //✅추가
        this.seatRepository = seatRepository;
        this.reservationSeatRepository = reservationSeatRepository;
    }

   // 영화 ID로 예매 페이지 진입 (영화 선택, 영화관, 날짜, 시간 선택 단계)
   @GetMapping("/{movieId}")
    public String ticketingByMovie(@PathVariable("movieId") Long movieId, Model model) {
        // 상영 중인 모든 영화 목록 가져오기 (페이징 없이 모든 데이터)
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.by("title").ascending()); // 모든 영화 가져오기
        List<MovieDto> nowShowingMovies = movieService.getMoviesByScreeningStatus(
                        Movie.ScreeningStatus.NOW_SHOWING, pageable)
                .getContent();

       List<MovieDto> allMovies = movieService.getAllMovies();
       model.addAttribute("movies", allMovies);

        model.addAttribute("nowShowingMovies", nowShowingMovies);
        model.addAttribute("selectedMovieId", movieId); // URL에서 넘어온 movieId를 기본 선택으로 설정

        // 모든 영화관 정보 가져오기
        List<Cinema> cinemas = cinemaService.getAllCinemas();
        model.addAttribute("cinemas", cinemas);

        // 지역별 영화관 목록 가져오기
        List<String> regions = cinemas.stream()
                .map(Cinema::getRegion)
                .distinct()
                .sorted()
                .toList();
        model.addAttribute("regions", regions);

        model.addAttribute("title", "영화 예매");
        model.addAttribute("timestamp", System.currentTimeMillis());

        return "ticketing/ticketing"; // ticketing.mustache 템플릿 렌더링
    }

    // AJAX API: 특정 영화, 영화관에 대한 상영 가능한 날짜 목록 조회
    @GetMapping("/api/dates")
    @ResponseBody
    public List<String> getAvailableDates(
            @RequestParam Long movieId,
            @RequestParam Long cinemaId) {
        return showtimeService.getAvailableDatesByMovieAndCinema(movieId, cinemaId);
    }

    // AJAX API: 특정 영화, 영화관, 날짜에 대한 상영 시간 목록 조회
    @GetMapping("/api/showtimes")
    @ResponseBody
    public List<Map<String, Object>> getShowtimes(
            @RequestParam Long movieId,
            @RequestParam Long cinemaId,
            @RequestParam String date) {

        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Showtime> showtimes = showtimeService.getShowtimesByMovieCinemaAndDate(movieId, cinemaId, localDate);

        return showtimes.stream().map(showtime -> {
            Map<String, Object> showtimeMap = new HashMap<>();
            showtimeMap.put("showtimeId", showtime.getShowtimeId());
            showtimeMap.put("startTime", showtime.getStartTime());
            showtimeMap.put("endTime", showtime.getEndTime());
            showtimeMap.put("screenName", showtime.getScreen().getScreenName()); // 상영관 이름

            // ✅ 남은 좌석 수 계산 추가
            int totalSeats = seatRepository.countByScreen_ScreenId(showtime.getScreen().getScreenId());
            int reservedSeats = reservationSeatRepository.countByShowtime_ShowtimeId(showtime.getShowtimeId());
            int availableSeats = totalSeats - reservedSeats;
            showtimeMap.put("availableSeats", availableSeats); // ← 프론트로 전달

            return showtimeMap;
        }).collect(Collectors.toList());
    }

    // 지역별 영화관 목록 조회
    @GetMapping("/api/cinemas")
    @ResponseBody
    public List<Cinema> getCinemasByRegion(@RequestParam String region) {
        return cinemaService.getCinemasByRegion(region);
    }

    // 좌석 선택 페이지로 이동하는 엔드포인트
//    @GetMapping("/showtime/{showtimeId}/seats")
//    public String showSeatSelectionPage(@PathVariable("showtimeId") Long showtimeId, Model model) {
//        Optional<Showtime> showtimeOptional = showtimeService.getShowtimeById(showtimeId);
//
//        if (showtimeOptional.isPresent()) {
//            Showtime showtime = showtimeOptional.get();
//            model.addAttribute("showtime", showtime);
//            model.addAttribute("movie", showtime.getMovie());
//            model.addAttribute("screen", showtime.getScreen());
//            model.addAttribute("title", "좌석 선택");
//
//            return "ticketing/seat-selection";
//        } else {
//            return "redirect:/movies";
//        }
//    }
}