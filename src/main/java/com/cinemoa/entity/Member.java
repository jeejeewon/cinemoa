package com.cinemoa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity // JPA 엔티티 클래스임을 명시 (DB 테이블과 매핑됨)
@Table(name = "member") // 매핑할 테이블 이름을 명시적으로 지정 (member 테이블과 연결됨)
@Getter
@Setter
@ToString(exclude = "password")// 보안상 비밀번호는 출력X
@NoArgsConstructor //파라미터 없는 기본 생성자 생성
@AllArgsConstructor //모든 필드를 파라미터로 받는 생성자 생성
@Builder //객체 생성시 빌더 패턴 사용가능
public class Member {
    @Id
    @Column(name = "member_id", length = 50)
    private String memberId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50, unique = true) //unique = true는 db에서 중복을 허용하지 않는다는 의미
    private String nickname;

    private LocalDate birthDate;

    private String phone;

    private String email;

    private String profileImg;

    private LocalDate anniversary;

    private String address;

    private String preferredCinema;

    private String preferredGenres;

    //insertable = false : JPA가 INSERT 쿼리를 날릴 때 이 컬럼은 포함하지 않음
    //updatable = false : UPDATE 쿼리에도 이 컬럼은 포함하지 않음
    @Column(name = "reg_date", insertable = false, updatable = false)
    private LocalDateTime regDate;
    
    //탈퇴여부
    @Column(name = "is_deleted")
    private boolean isDeleted = false;

}
