package com.cinemoa.service;

import com.cinemoa.dto.MovieDto;
import com.cinemoa.entity.Movie;
import com.cinemoa.repository.MovieRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<MovieDto> getMoviesPaginated(Pageable pageable) {
        return movieRepository.findAll(pageable)
                .map(this::convertToDto);
    }

    @Override
    public Optional<MovieDto> getMovieById(Long id) {
        return movieRepository.findById(id)
                .map(this::convertToDto);
    }

    @Override
    @Transactional
    public MovieDto saveMovie(MovieDto movieDto) {
        Movie movie = convertToEntity(movieDto);
        Movie savedMovie = movieRepository.save(movie);
        return convertToDto(savedMovie);
    }

    @Override
    @Transactional
    public MovieDto updateMovie(Long id, MovieDto movieDto) {
        Optional<Movie> existingMovieOpt = movieRepository.findById(id);

        if (existingMovieOpt.isPresent()) {
            Movie existingMovie = existingMovieOpt.get();

            // ID는 변경하지 않고 나머지 필드만 업데이트
            movieDto.setMovieId(id);
            Movie movie = convertToEntity(movieDto);
            movie.setMovieId(id);

            Movie updatedMovie = movieRepository.save(movie);
            return convertToDto(updatedMovie);
        }

        return null; // 또는 예외 처리
    }

    @Override
    @Transactional
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    // Entity -> DTO 변환
    private MovieDto convertToDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        BeanUtils.copyProperties(movie, movieDto);
        return movieDto;
    }

    // DTO -> Entity 변환
    private Movie convertToEntity(MovieDto movieDto) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieDto, movie);
        return movie;
    }
}