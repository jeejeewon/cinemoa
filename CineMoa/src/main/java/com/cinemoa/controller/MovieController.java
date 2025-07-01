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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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
        MovieDto movie = movieService.getMovieById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid movie Id:" + id));
        model.addAttribute("movie", movie);
        return "movies/view";
    }

}