package com.cinemoa.repository;

import com.cinemoa.entity.Inquiry;
import com.cinemoa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    // 나의 문의내역
    List<Inquiry> findByMember(Member member);

    List<Inquiry> findByMember_MemberId(String memberId);

    List<Inquiry> findByGuestUser_GuestUserId(Long guestUserId);


}
