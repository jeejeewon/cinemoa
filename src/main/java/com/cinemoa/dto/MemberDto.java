package com.cinemoa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data //@Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor를 한 번에 생성
@Builder //빌더 패턴 적용
@NoArgsConstructor //파라미터 없는 기본 생성자 생성
@AllArgsConstructor //	모든 필드를 받는 생성자 생성
public class MemberDto {
    private String memberId;
    private String password;
    private String name;
    private String nickname;
    private LocalDate birthDate;
    private String phone;
    private String email;
    private String profileImg; // DB에 저장할 이미지 경로 (또는 파일명)
    private MultipartFile profileImgFile; // 폼에서 넘어오는 실제 업로드 파일
    private LocalDate anniversary;
    private String address;
    private String preferredCinema;
    private String preferredGenres;

}
