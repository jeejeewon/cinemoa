package com.cinemoa.repository;

import com.cinemoa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

    // 아이디 중복 체크 메서드
    boolean existsByMemberId(String memberId);

    // 닉네임 중복 체크 메서드
    boolean existsByNickname(String nickname);

    // 로그인 메서드 (아이디+비밀번호로 조회)
    Member findByMemberIdAndPassword(String memberId, String password);
}
