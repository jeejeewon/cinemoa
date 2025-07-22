package com.cinemoa.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatDto {
    private Long seatId;
    private String seatRow;
    private int seatNumber;
    private String seatType; // 좌석 종류 (STANDARD, VIP, DISABLED). Seat 클래스 내부 enum 사용
    private int price;
}
