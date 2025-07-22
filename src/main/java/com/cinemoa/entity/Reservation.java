package com.cinemoa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity // JPA 엔티티 클래스임을 명시 (DB 테이블과 매핑됨)
@Table(name = "reservation") // 매핑할 테이블 이름을 명시적으로 지정 (member 테이블과 연결됨)
@Getter
@Setter
@NoArgsConstructor //파라미터 없는 기본 생성자 생성
@AllArgsConstructor //모든 필드를 파라미터로 받는 생성자 생성
@Builder //객체 생성시 빌더 패턴 사용가능
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId; // 예매 고유번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 예매한 회원

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie; // 영화

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema; // 영화관

    @Column(name = "screen_id")
    private Integer screenId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id", insertable = false, updatable = false)
    private Screen screen;

    private String seatInfo; // 예매 좌석 정보 (예: A5,A6)

    private LocalDateTime reservationTime; // 예매 시간

    @OneToOne(mappedBy = "reservation", fetch = FetchType.LAZY)
    private Payment payment;

    private String paymentMethod; // 결제 수단 (계좌이체 / 신용카드)

    private String status; // 예매 상태 (예약완료 / 취소됨)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "showtime_id", nullable = false)
    private Showtime showtime;

}
