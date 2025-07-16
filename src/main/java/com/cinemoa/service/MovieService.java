package com.cinemoa.service;

import com.cinemoa.dto.MovieDto;
import com.cinemoa.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<MovieDto> getAllMovies();
    Page<MovieDto> getMoviesPaginated(Pageable pageable);
    Optional<MovieDto> getMovieById(Long id);
    MovieDto saveMovie(MovieDto movieDto);
    MovieDto updateMovie(Long id, MovieDto movieDto);
    void deleteMovie(Long id);

    // 상영 상태별 필터링
    Page<MovieDto> getMoviesByScreeningStatus(Movie.ScreeningStatus status, Pageable pageable);

    // 키워드로 영화 검색
    Page<MovieDto> searchMoviesByKeyword(String keyword, Pageable pageable);

    // 키워드와 상영 상태로 영화 검색
    Page<MovieDto> searchMoviesByKeywordAndStatus(String keyword, Movie.ScreeningStatus status, Pageable pageable);

}