package com.cinemoa.service;

import com.cinemoa.dto.MemberDto;
import com.cinemoa.entity.Member;
import com.cinemoa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // final 필드 생성자 자동 생성
public class MemberService {

    private final MemberRepository memberRepository;

    //회원가입
    public Member join(MemberDto dto){
        Member member = Member.builder()
                .memberId(dto.getMemberId())
                .password(dto.getPassword())
                .name(dto.getName())
                .nickname(dto.getNickname())
                .birthDate(dto.getBirthDate())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .profileImg(dto.getProfileImg())
                .anniversary(dto.getAnniversary())
                .address(dto.getAddress())
                .preferredCinema(dto.getPreferredCinema())
                .preferredGenres(dto.getPreferredGenres())
                .build();

        return memberRepository.save(member);
    }

    //아이디 중복 검사
    public boolean isDuplicateId(String id){
        return memberRepository.existsByMemberId(id);
    }

    //닉네임 중복 검사
    public boolean isDuplicateNickname(String nickname){
        return memberRepository.existsByNickname(nickname);
    }

    //로그인 처리
    public Member login(String id, String password){
        return memberRepository.findByMemberIdAndPassword(id, password);
    }


}
