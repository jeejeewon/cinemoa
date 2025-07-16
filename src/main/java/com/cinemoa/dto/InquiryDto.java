package com.cinemoa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InquiryDto {
    private Long inquiryId;           // 문의 고유번호
    private String memberId;          // 작성한 회원 ID
    private String title;             // 문의 제목
    private String content;           // 문의 내용
    private String replyContent;      // 관리자 답변 내용
    private LocalDateTime regDate;    // 문의 등록일
    private LocalDateTime replyDate;  // 답변 등록일
}
