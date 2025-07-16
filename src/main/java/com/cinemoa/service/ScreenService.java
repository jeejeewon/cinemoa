package com.cinemoa.service;

import com.cinemoa.entity.Screen;
import java.util.List;

public interface ScreenService {
    List<Screen> getScreensByCinemaId(Long cinemaId);
}