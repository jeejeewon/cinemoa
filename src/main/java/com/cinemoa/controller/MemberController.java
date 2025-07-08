package com.cinemoa.controller;

import com.cinemoa.dto.MemberDto;
import com.cinemoa.entity.Member;
import com.cinemoa.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    //회원가입 페이지 (GET)
    @GetMapping("/join")
    public String showJoinForm(Model model){
        model.addAttribute("title", "회원가입");
        model.addAttribute("timestamp", System.currentTimeMillis());
        return "member/join";
    }

    //회원가입 처리 (POST)
    @PostMapping("/join")
    public String processJoin(@ModelAttribute MemberDto dto, Model model) {
        if (memberService.isDuplicateId(dto.getMemberId())) {
            model.addAttribute("error", "이미 사용 중인 아이디입니다.");
            return "member/join";
        }
        if (memberService.isDuplicateNickname(dto.getNickname())) {
            model.addAttribute("error", "이미 사용 중인 닉네임입니다.");
            return "member/join";
        }

        MultipartFile file = dto.getProfileImgFile();
        if (file != null && !file.isEmpty()) {
            String uploadDir = "C:/cinemoa/upload/profile/";
            new File(uploadDir).mkdirs();
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            try {
                file.transferTo(new File(uploadDir, fileName));
                dto.setProfileImg(fileName);
            } catch (IOException e) {
                model.addAttribute("error", "이미지 업로드 실패");
                return "member/join";
            }
        }

        memberService.join(dto);
        return "redirect:/member/login";
    }


    //로그인 페이지 (GET)
    @GetMapping("/login")
    public String showLoginForm() {
    return "member/login";
    }

    //로그인 처리 (POST)
    @PostMapping("/login")
    public String processLogin(@RequestParam String memberId, @RequestParam String password, Model model, HttpSession session) {
        Member member = memberService.login(memberId, password);
        if (member == null) {
            model.addAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "member/login";
        }

        // 세션에 로그인 사용자 저장
        session.setAttribute("loginMember", member);

        return "redirect:/"; //로그인 성공 후 메인페이지로 이동
    }

}
