package com.cinemoa.api;

import com.cinemoa.entity.Member;
import com.cinemoa.repository.MemberRepository;
import com.cinemoa.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberApiController {

    // 서비스 주입
    private final MemberService memberService;
    // 레포지토리 주입
    private final MemberRepository memberRepository;

    // 아이디 중복 여부를 실시간으로 확인하는 API (true = 사용 가능)
    @PostMapping("/check-id")
    public boolean checkId(@RequestParam String memberId) {
        return !memberService.isDuplicateId(memberId);
    }

    // 닉네임 중복 여부를 실시간으로 확인하는 API (true = 사용 가능)
    @PostMapping("/check-nickname")
    public boolean checkNickname(@RequestParam String nickname) {
        return !memberService.isDuplicateNickname(nickname);
    }

    // 아이디 찾기 API
    @PostMapping("/find-id")
    public ResponseEntity<?> findMemberId(@RequestParam String name,
                                          @RequestParam String email) {
        var dto = memberService.findMemberId(name, email);
        if (dto != null) {
            return ResponseEntity.ok(dto);  // JSON으로 아이디 + 가입일 반환
        } else {
            return ResponseEntity.badRequest().body("회원 정보를 찾을 수 없습니다.");
        }
    }


    // 비밀번호 재설정 처리 API
    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody Map<String, String> request) {
        String memberId = request.get("memberId");
        String newPassword = request.get("newPassword");

        boolean result = memberService.updatePassword(memberId, newPassword);

        if (result) {
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("fail");
        }
    }


}
