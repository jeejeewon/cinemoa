package com.cinemoa.controller;

import com.cinemoa.dto.ReviewDto;
import com.cinemoa.service.MovieService;
import com.cinemoa.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final MovieService movieService; // MovieService도 필요하다면 final로 선언하여 주입받습니다.

    // 리뷰 작성 폼 표시 (풀 페이지 반환)
    // @GetMapping("/new")
    // public String showNewReviewForm(@RequestParam("movieId") Long movieId, Model model) {
    //     model.addAttribute("movieId", movieId);
    //     model.addAttribute("review", new ReviewDto());
    //     return "reviews/new"; // 풀 페이지 템플릿 반환
    // }

    //리뷰 작성 폼 Fragment 반환 (AJAX 모달용)
    @GetMapping("/new-fragment")
    public String showNewReviewFormFragment(@RequestParam("movieId") Long movieId, Model model) {
        model.addAttribute("movieId", movieId);
        model.addAttribute("review", new ReviewDto());
        // "reviews/new" 템플릿의 내용 중 폼 부분만 반환
        return "reviews/new";
    }

    // 리뷰 저장
    @PostMapping("/new")
    public String saveReview(@ModelAttribute ReviewDto reviewDto, RedirectAttributes redirectAttributes) {
        // 현재는 임시로 userId를 1로 설정 (실제로는 로그인한 사용자 ID를 사용해야 합니다.)
        // TODO: 실제 사용자 인증 시스템과 연동하여 userId를 설정해야 합니다.
        reviewDto.setUserId(1L);

        ReviewDto savedReview = reviewService.saveReview(reviewDto);
        redirectAttributes.addFlashAttribute("message", "리뷰가 성공적으로 등록되었습니다.");

        return "redirect:/movies/" + reviewDto.getMovieId();
    }

    // 리뷰 삭제
    @GetMapping("/delete/{reviewId}")
    public String deleteReview(@PathVariable Long reviewId, @RequestParam Long movieId, RedirectAttributes redirectAttributes) {
        reviewService.deleteReview(reviewId);
        redirectAttributes.addFlashAttribute("message", "리뷰가 삭제되었습니다.");

        return "redirect:/movies/" + movieId;
    }
}