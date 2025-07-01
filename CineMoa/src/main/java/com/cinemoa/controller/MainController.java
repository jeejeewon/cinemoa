package com.cinemoa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String showMain(Model model) {
        model.addAttribute("title", "CineMoa");// {{title}}에 들어갈 값 적기
        return "main/main"; // templates/main/main.mustache
    }

}
