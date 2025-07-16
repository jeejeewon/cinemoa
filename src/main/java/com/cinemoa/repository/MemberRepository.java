package com.cinemoa.repository;

import com.cinemoa.dto.InquiryDto;
import com.cinemoa.dto.ReservationDto;
import com.cinemoa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    // 아이디 중복 체크 메서드
    boolean existsByMemberId(String memberId);

    // 닉네임 중복 체크 메서드
    boolean existsByNickname(String nickname);

    // 로그인 메서드 (아이디+비밀번호로 조회)
    Member findByMemberIdAndPassword(String memberId, String password);

    // 이메일이 이미 존재하는지 여부
    boolean existsByEmail(String email);

    // 이름과 이메일로 회원을 조회 (아이디 찾기 시 사용)
    Optional<Member> findByNameAndEmail(String name, String email);

    // 회원 아이디로 회원을 조회 (비밀번호 재설정 시 사용)
    Optional<Member> findByMemberId(String memberId);

    // 아이디, 이름, 이메일이 모두 일치하는 회원 조회 (비밀번호 찾기 시 입력 정보 검증용)
    Optional<Member> findByMemberIdAndNameAndEmail(String memberId, String name, String email);

    // 내가 본 영화 수 (중복 제거)
    @Query(value = "SELECT COUNT(DISTINCT r.movie_id) FROM reservation r WHERE r.member_id = :memberId", nativeQuery = true)
    int countWatchedMovies(@Param("memberId") String memberId);

    // 내가 쓴 관람평 수
    @Query(value = """
                    SELECT COUNT(*) FROM reviews r
                    JOIN member m ON CAST(r.user_id AS CHAR) = m.member_id
                    WHERE m.member_id = :memberId
                """, nativeQuery = true)
    int countWrittenReviews(@Param("memberId") String memberId);


    // 최근 예매 내역 5건
    @Query(value = """
                SELECT 
                    m.title AS movieTitle,
                    c.name AS cinemaName,
                    DATE_FORMAT(r.reservation_time, '%Y-%m-%d %H:%i') AS reservationTime
                FROM reservation r
                JOIN movies m ON r.movie_id = m.movie_id
                JOIN cinemas c ON r.cinema_id = c.cinema_id
                WHERE r.member_id = :memberId
                ORDER BY r.reservation_time DESC
                LIMIT 5
                """, nativeQuery = true)
    List<ReservationDto> getRecentReservations(@Param("memberId") String memberId);

    // 최근 문의 내역 5건
    @Query(value = """
                SELECT 
                    title,
                    DATE_FORMAT(reg_date, '%Y-%m-%d') AS regDate,
                    reply_content AS replyContent
                FROM inquiry
                WHERE member_id = :memberId
                ORDER BY reg_date DESC
                LIMIT 5
                """, nativeQuery = true)
    List<InquiryDto> getRecentInquiries(@Param("memberId") String memberId);
}
