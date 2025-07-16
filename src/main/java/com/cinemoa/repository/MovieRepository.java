package com.cinemoa.repository;

import com.cinemoa.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    // 상영 상태별 영화 조회
    List<Movie> findByScreeningStatus(Movie.ScreeningStatus status);

    // 페이징을 지원하는 메서드 추가
    Page<Movie> findByScreeningStatus(Movie.ScreeningStatus status, Pageable pageable);

    // 키워드로 영화 검색 (제목, 감독, 배우 중 하나라도 포함)
    Page<Movie> findByTitleContainingOrDirectorContainingOrActorsContaining(
            String titleKeyword, String directorKeyword, String actorsKeyword, Pageable pageable);

    // 키워드와 상영 상태로 영화 검색 (제목, 감독, 배우 중 하나라도 포함하고, 특정 상영 상태인 경우)
    Page<Movie> findByTitleContainingAndScreeningStatusOrDirectorContainingAndScreeningStatusOrActorsContainingAndScreeningStatus(
            String titleKeyword, Movie.ScreeningStatus titleStatus,
            String directorKeyword, Movie.ScreeningStatus directorStatus,
            String actorsKeyword, Movie.ScreeningStatus actorsStatus,
            Pageable pageable);

}