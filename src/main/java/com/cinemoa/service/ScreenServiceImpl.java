package com.cinemoa.service;

import com.cinemoa.entity.Screen;
import com.cinemoa.repository.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenServiceImpl implements ScreenService {

    private final ScreenRepository screenRepository;

    @Autowired
    public ScreenServiceImpl(ScreenRepository screenRepository) {
        this.screenRepository = screenRepository;
    }

    @Override
    public List<Screen> getScreensByCinemaId(Long cinemaId) {
        // findByCinemaId -> findByCinema_CinemaId로 수정
        return screenRepository.findByCinema_CinemaId(cinemaId);
    }
}