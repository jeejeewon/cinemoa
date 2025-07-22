package com.cinemoa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seats", uniqueConstraints = {
        @UniqueConstraint(name = "unique_seat", columnNames = {"screen_id", "seat_row", "seat_number"})
})
//세 개 컬럼의 조합이 유일해야 한다는 제약 조건
//같은 상영관에서 같은 행 + 번호 좌석은 두 번 못 넣게 막음
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {

    @Id // 기본 키(PK) 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT 자동 증가 전략
    private Long seatId; // 좌석 ID (고유 식별자)

    // N:1 관계 매핑 - 좌석은 하나의 상영관(Screen)에 속함
    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩으로 성능 최적화
    @JoinColumn(name = "screen_id", nullable = false) // 외래키 컬럼명 설정
    private Screen screen; // 해당 좌석이 속한 상영관

    @Column(name = "seat_row", nullable = false, length = 1)
    private String seatRow; // 좌석 행 (예: A, B, C...)

    @Column(name = "seat_number", nullable = false)
    private int seatNumber; // 좌석 번호 (예: 1, 2, 3...)

    @Column(name = "seat_type", nullable = false)
    private String seatType; // 좌석 타입 (일반석, VIP석, 장애인석)

    @Column(name = "price", nullable = false)
    private int price; // 해당 좌석의 가격


}
