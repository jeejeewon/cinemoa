package com.cinemoa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class SeatsController {

    @GetMapping("/seats")
    public String showMain(Model model) {
        return "seats/seatReservation"; // templates/seats/seatReservation.mustache
    }

}
