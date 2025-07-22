package com.cinemoa.controller;

import com.cinemoa.dto.InquiryDto;
import com.cinemoa.entity.GuestUser;
import com.cinemoa.entity.Inquiry;
import com.cinemoa.entity.Member;
import com.cinemoa.service.InquiryService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/support")
public class SupportController {

    private final InquiryService inquiryService;

    // 로그인 유저 세션 정보 추가 (공통 처리)
    private void addLoginMember(Model model, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember != null) {
            model.addAttribute("loginMember", loginMember);
        }
    }

    @GetMapping
    public String supportHome(Model model, HttpSession session) {
        model.addAttribute("supportHome", true);
        model.addAttribute("pagePath", "고객센터 홈");
        addLoginMember(model, session);
        return "support/supportLayout";
    }

    @GetMapping("/notice")
    public String noticeList(Model model, HttpSession session) {
        model.addAttribute("noticeList", true);
        model.addAttribute("pagePath", "고객센터 > 공지사항");
        addLoginMember(model, session);
        return "support/supportLayout";
    }

    @GetMapping("/notice/{id}")
    public String noticeDetail(@PathVariable Long id, Model model, HttpSession session) {
        model.addAttribute("noticeDetail", true);
        model.addAttribute("pagePath", "고객센터 > 공지사항");
        addLoginMember(model, session);
        // id는 나중에 서비스에서 조회용으로 사용
        return "support/supportLayout";
    }

    @GetMapping("/faq")
    public String faqList(Model model, HttpSession session) {
        model.addAttribute("faqList", true);
        model.addAttribute("pagePath", "고객센터 > 자주 묻는 질문");
        addLoginMember(model, session);
        return "support/supportLayout";
    }

    // 1:1 문의하기
    @GetMapping("/inquiryForm")
    public String inquiryForm(Model model, HttpSession session) {

        Member loginMember = (Member) session.getAttribute("loginMember");
        GuestUser guestUser = (GuestUser) session.getAttribute("guestUser");

        // 로그인 사용자 또는 비회원이 아니면 접근 불가
        if (loginMember == null && guestUser == null) {
            return "redirect:/member/login";
        }

        model.addAttribute("inquiryForm", true);
        model.addAttribute("pagePath", "고객센터 > 문의 작성");
        addLoginMember(model, session);
        return "support/supportLayout";
    }
    @PostMapping("/inquiryForm")
    public String submitInquiry(@RequestParam String category,
                                @RequestParam String title,
                                @RequestParam String content,
                                @RequestParam(required = false) String agree,
                                HttpSession session) {
        if (agree == null) {
            return "redirect:/support/inquiryForm?error=agree";
        }

        Member loginMember = (Member) session.getAttribute("loginMember");
        GuestUser guestUser = (GuestUser) session.getAttribute("guestUser"); // ★ 이거 꼭 추가

        if (loginMember == null && guestUser == null) {
            return "redirect:/member/login";
        }

        String finalTitle = "[" + category + "] " + title;

        Inquiry inquiry = Inquiry.builder()
                .title(finalTitle)
                .content(content)
                .regDate(LocalDateTime.now())
                .build();

        if (loginMember != null) {
            inquiry.setMember(loginMember);
        } else {
            inquiry.setGuestUser(guestUser);
        }

        inquiryService.saveInquiry(inquiry);
        return "redirect:/support/myinquiry";
    }


    @GetMapping("/myinquiry")
    public String myinquiry(Model model, HttpSession session) {

        Member loginMember = (Member) session.getAttribute("loginMember");
        GuestUser guestUser = (GuestUser) session.getAttribute("guestUser");

        // 로그인 사용자 또는 비회원이 아니면 접근 불가
        if (loginMember == null && guestUser == null) {
            return "redirect:/member/login";
        }

        if (loginMember != null) {
            List<InquiryDto> myInquiries = inquiryService.getMyInquiries(loginMember.getMemberId());
            model.addAttribute("myInquiries", myInquiries);
        } else if (guestUser != null) {
            List<InquiryDto> myInquiries = inquiryService.getMyInquiriesForGuest(guestUser.getGuestUserId());
            model.addAttribute("myInquiries", myInquiries);
        }

        model.addAttribute("myinquiry", true);
        model.addAttribute("pagePath", "고객센터 > 나의 문의 내역");
        addLoginMember(model, session);
        return "support/supportLayout";
    }

    @GetMapping("/inquiry/{id}")
    public String inquiryDetail(@PathVariable Long id, Model model, HttpSession session) {

        Member loginMember = (Member) session.getAttribute("loginMember");
        GuestUser guestUser = (GuestUser) session.getAttribute("guestUser");

        // 로그인 사용자 또는 비회원이 아니면 접근 불가
        if (loginMember == null && guestUser == null) {
            return "redirect:/member/login";
        }

        model.addAttribute("inquiryDetail", true);
        model.addAttribute("pagePath", "고객센터 > 나의 문의 내역");
        addLoginMember(model, session);
        return "support/supportLayout";
    }
}
