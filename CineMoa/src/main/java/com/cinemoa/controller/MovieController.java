package com.cinemoa.controller;

import com.cinemoa.dto.MovieDto;
import com.cinemoa.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("")
    public String listMovies(Model model, @PageableDefault(size = 10) Pageable pageable) {
        Page<MovieDto> movies = movieService.getMoviesPaginated(pageable);
        model.addAttribute("movies", movies);

        // 페이지네이션을 위한 값들 추가
        model.addAttribute("hasPrevious", movies.hasPrevious());
        model.addAttribute("hasNext", movies.hasNext());
        model.addAttribute("prevPage", Math.max(0, movies.getNumber() - 1));
        model.addAttribute("nextPage", Math.min(movies.getTotalPages() - 1, movies.getNumber() + 1));

        // 페이지 번호 목록 생성
        int totalPages = movies.getTotalPages();
        if (totalPages > 0) {
            List<PageItem> pageNumbers = new ArrayList<>();
            int start = Math.max(0, movies.getNumber() - 2);
            int end = Math.min(totalPages - 1, movies.getNumber() + 2);

            for (int i = start; i <= end; i++) {
                pageNumbers.add(new PageItem(i, movies.getNumber() == i));
            }
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "movies/list";
    }

    // 페이지 항목을 위한 내부 클래스
    @Data
    @AllArgsConstructor
    static class PageItem {
        private int number;
        private boolean active;
    }

    // 영화 목록 페이지 (추가 매핑 - /list로 접근할 경우)
    @GetMapping("/list")
    public String moviesList(Model model, @PageableDefault(size = 10) Pageable pageable) {
        return listMovies(model, pageable);  // 위의 메서드 재사용
    }

    @GetMapping("/{id}")
    public String viewMovie(@PathVariable("id") Long id, Model model) {
        Optional<MovieDto> movieDtoOptional = movieService.getMovieById(id);

        if (movieDtoOptional.isPresent()) {
            MovieDto movieDto = movieDtoOptional.get();

            // videoUrl 처리
            if (movieDto.getVideoUrl() != null && !movieDto.getVideoUrl().isEmpty()) {
                String videoUrl = movieDto.getVideoUrl();
                // 이미 전체 URL인 경우 그대로 사용
                if (videoUrl.startsWith("https://www.youtube.com/embed/")) {
                    // 이미 올바른 형식이므로 그대로 사용
                }
                // youtube.com/watch?v= 형식인 경우 embed 형식으로 변환
                else if (videoUrl.contains("youtube.com/watch?v=")) {
                    String videoId = videoUrl.split("v=")[1];
                    // 추가 파라미터가 있는 경우 제거
                    int ampersandPosition = videoId.indexOf('&');
                    if (ampersandPosition != -1) {
                        videoId = videoId.substring(0, ampersandPosition);
                    }
                    movieDto.setVideoUrl("https://www.youtube.com/embed/" + videoId);
                }
                // ID만 있는 경우 embed URL로 변환
                else {
                    movieDto.setVideoUrl("https://www.youtube.com/embed/" + videoUrl);
                }
            }

            model.addAttribute("movie", movieDto);
            return "movies/view";
        } else {
            return "redirect:/movies";
        }
    }

    @GetMapping("/new")
    public String showNewMovieForm(Model model) {
        model.addAttribute("movie", new MovieDto());
        return "movies/new";
    }

    @PostMapping("/new")
    public String saveNewMovie(@ModelAttribute("movie") MovieDto movieDto,
                               RedirectAttributes redirectAttributes) {
        MovieDto savedMovie = movieService.saveMovie(movieDto);
        redirectAttributes.addFlashAttribute("message", "영화가 성공적으로 등록되었습니다.");
        return "redirect:/movies";
    }

    // 영화 수정 폼 보여주기
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<MovieDto> movieDto = movieService.getMovieById(id);

        if (movieDto.isPresent()) {
            model.addAttribute("movie", movieDto.get());
            return "movies/edit";
        } else {
            return "redirect:/movies";
        }
    }

    // 영화 수정 처리
    @PostMapping("/{id}/edit")
    public String updateMovie(@PathVariable Long id,
                              @ModelAttribute("movie") MovieDto movieDto,
                              RedirectAttributes redirectAttributes) {
        MovieDto updatedMovie = movieService.updateMovie(id, movieDto);

        if (updatedMovie != null) {
            redirectAttributes.addFlashAttribute("message", "영화가 성공적으로 수정되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("error", "영화 수정에 실패했습니다.");
        }

        return "redirect:/movies/" + id;
    }



}