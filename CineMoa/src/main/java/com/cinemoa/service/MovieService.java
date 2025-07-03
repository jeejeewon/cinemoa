package com.cinemoa.service;

import com.cinemoa.dto.MovieDto;
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
}