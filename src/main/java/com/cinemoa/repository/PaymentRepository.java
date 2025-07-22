package com.cinemoa.repository;

import com.cinemoa.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByReservation_ReservationId(Long reservationId);
}
