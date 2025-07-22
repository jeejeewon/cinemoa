package com.cinemoa.repository;

import com.cinemoa.entity.Screen;
import com.cinemoa.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByScreen(Screen screen); // 해당 Screen 객체에 연결된 좌석만 가져옴
    List<Seat> findByScreen_ScreenId(Long screenId); // screen_id 기준으로 좌석 조회
    int countByScreen_ScreenId(Long screenId);
}
