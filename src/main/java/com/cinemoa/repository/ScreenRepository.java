package com.cinemoa.repository;

import com.cinemoa.entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {
    // cinema_id -> cinema_cinemaId로 수정
    List<Screen> findByCinema_CinemaId(Long cinemaId);
}