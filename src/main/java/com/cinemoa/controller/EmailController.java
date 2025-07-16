package com.cinemoa.controller;

import com.cinemoa.service.EmailService;
import com.cinemoa.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class EmailController {
    private final EmailService emailService;
    private final MemberService memberService;

    // 사용자가 입력한 이메일로 인증번호를 생성하고 전송
    // 생성된 인증번호와 이메일은 세션에 저장, 유효시간은 5분
    @PostMapping("/send-auth-code")
    @ResponseBody
    public Map<String, Object> sendAuthCode(@RequestParam String email, HttpSession session) {
        String authCode = emailService.createAuthCode();
        emailService.sendAuthCode(email, authCode);

        session.setAttribute("authCode", authCode);
        session.setAttribute("authEmail", email);
        session.setMaxInactiveInterval(300); // 5분 유지

        Map<String, Object> result = new HashMap<>();
        result.put("status", "success");
        return result;
    }

    // 사용자가 입력한 인증번호와 세션에 저장된 인증번호를 비교하여 일치 여부 확인
    // 인증 성공 시 이메일 인증 상태(true)와 인증된 이메일을 세션에 저장
    @PostMapping("/verify-auth-code")
    @ResponseBody
    public Map<String, Object> verifyAuthCode(@RequestParam String email, @RequestParam String code, HttpSession session) {
        String savedCode = (String) session.getAttribute("authCode");
        String savedEmail = (String) session.getAttribute("authEmail");

        Map<String, Object> result = new HashMap<>();
        if (savedCode != null && savedEmail != null && savedEmail.equals(email) && savedCode.equals(code)) {
            result.put("status", "verified");
            session.setAttribute("emailVerified", true);
            session.setAttribute("verifiedEmail", email);
        } else {
            result.put("status", "fail");
        }
        return result;
    }

    // 인증된 이메일을 최종 확인하고, 검증 성공 시 회원가입 다음 단계(step2)로 이동
    // 인증 상태나 이메일이 일치하지 않으면 인증 페이지로 다시 이동
    @PostMapping("/join/verify-email")
    public String handleVerifiedEmail(@RequestParam String email, HttpSession session) {
        Boolean verified = (Boolean) session.getAttribute("emailVerified");
        String savedEmail = (String) session.getAttribute("authEmail");

        if (Boolean.TRUE.equals(verified) && email.equals(savedEmail)) {
            // 이메일 인증 성공 후 다음 페이지로 이동
            return "redirect:/member/join/step2"; // 다음 단계 JSP로 연결
        } else {
            // 인증 실패 시 다시 인증 페이지로
            return "redirect:/member/join?error=auth";
        }
    }

    @PostMapping("/email/send-password-auth-code")
    @ResponseBody
    public String sendPasswordAuthCode(@RequestBody Map<String, String> payload, HttpSession session) {
        String memberId = payload.get("memberId");
        String name = payload.get("name");
        String email = payload.get("email");

        if (!memberService.validateMemberInfo(memberId, name, email)) {
            return "fail";  // 정보가 일치하지 않음
        }

        String authCode = emailService.createAuthCode();
        emailService.sendAuthCode(email, authCode);

        session.setAttribute("authCode", authCode);
        session.setAttribute("authEmail", email);
        session.setMaxInactiveInterval(300); // 5분

        return "success";
    }

    @PostMapping("/email/verify-auth-code")
    @ResponseBody
    public String verifyPasswordAuthCode(@RequestBody Map<String, String> payload, HttpSession session) {
        String email = payload.get("email");
        String inputCode = payload.get("authCode");

        String savedCode = (String) session.getAttribute("authCode");
        String savedEmail = (String) session.getAttribute("authEmail");

        if (savedCode != null && savedCode.equals(inputCode) && savedEmail.equals(email)) {
            session.setAttribute("emailVerified", true);
            session.setAttribute("verifiedEmail", email);
            return "success";
        }

        return "fail";
    }



}
