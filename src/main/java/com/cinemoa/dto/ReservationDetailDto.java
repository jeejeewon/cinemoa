package com.cinemoa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDetailDto {

    private Long reservationId;
    private String movieTitle;
    private String mainImageUrl;
    private String cinemaName;
    private String screenName;
    private String paymentMethod;
    private String status;
    private LocalDateTime reservationTime;
    private String showtimeStart;   // 예: "15:30"
    private String showtimeEnd;     // 예: "17:31"
    private List<SeatDto> seats;
    private String formattedPaymentDate;
    private int totalPrice;      // 총 좌석가
    private int discount;        // 할인 금액
    private int finalPayment;    // 결제 금액
    private String salesNumber;  // 판매번호 예: 2024-0720-0001
    private String formattedReservationTime;

}
