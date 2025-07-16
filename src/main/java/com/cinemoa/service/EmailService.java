package com.cinemoa.service;

import com.cinemoa.entity.Member;
import com.cinemoa.repository.MemberRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final MemberRepository memberRepository;

    // 인증코드 생성 (숫자 6자리)
    public String createAuthCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // 100000 ~ 999999
        return String.valueOf(code);
    }

    // 이메일 전송
    public void sendAuthCode(String toEmail, String authCode) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

            helper.setTo(toEmail);
            helper.setFrom("desigr.jw@gmail.com");
            helper.setSubject("[CINEMOA] 이메일 인증번호 안내");

            helper.setText(
                    "<div style='font-family: Pretendard, sans-serif; padding: 30px; background: #f4f4f4;'>"
                            + "<div style='max-width: 500px; margin: auto; background: #fff; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.05); overflow: hidden;'>"
                            + "  <div style='background-color: #006f7c; padding: 20px; text-align: center;'>"
                            + "    <strong style='font-size: 28px; color: white; letter-spacing: 1px;'>CINEMOA</strong>"
                            + "  </div>"
                            + "  <div style='padding: 30px; color: #333;'>"
                            + "    <h2 style='color: #006f7c;'>이메일 인증번호</h2>"
                            + "    <p>안녕하세요. CINEMOA 이메일 인증번호입니다.</p>"
                            + "    <p>아래 인증번호를 입력하여 인증을 완료해 주세요.</p>"
                            + "    <div style='text-align: center; margin: 30px 0;'>"
                            + "      <span style='font-size: 32px; font-weight: bold; color: #006f7c;'>" + authCode + "</span>"
                            + "    </div>"
                            + "    <p style='font-size: 13px; color: #888;'>※ 인증번호는 5분간만 유효합니다.</p>"
                            + "  </div>"
                            + "</div>"
                            + "</div>", true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("이메일 전송에 실패했습니다", e);
        }
    }

    //입력한 정보가 DB에 실제 존재하는지 검사하는 메서드
    public boolean validateMemberInfo(String memberId, String name, String email) {
        if (memberId == null || name == null || email == null) return false;

        // 입력값 정리
        String trimmedId = memberId.trim();
        String trimmedName = name.trim();
        String trimmedEmail = email.trim().toLowerCase();

        System.out.println("[입력 검증] memberId: " + trimmedId + ", name: " + trimmedName + ", email: " + trimmedEmail);

        System.out.println("▶ DB 조회 결과: " +
                memberRepository.findByMemberIdAndNameAndEmail(trimmedId, trimmedName, trimmedEmail)
        );

        return memberRepository.findByMemberIdAndNameAndEmail(trimmedId, trimmedName, trimmedEmail).isPresent();
    }

}
