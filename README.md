# CineMoa | 영화 예매 플랫폼
**Spring Boot 및 JPA 기반의 영화 정보 제공 및 예매 시스템**

본 프로젝트는 영화 목록 조회부터 상세 정보 확인, 실시간 예매 프로세스까지 통합한 영화 예매 플랫폼입니다. Spring Boot와 JPA를 활용하여 데이터 영속성을 관리하고, 효율적인 검색 및 랭킹 알고리즘을 구현하는 데 초점을 맞추어 개발 중인 프로젝트입니다.

> **Notice** > 본 저장소는 팀 프로젝트 [cinemoa](https://github.com/YOON-J11/cinemoa)의 전체 내용 중 본인의 작업 파트를 중심으로 재구성한 개인 포트폴리오용 저장소입니다.

---

## 1. 프로젝트 개요

- **프로젝트명**: CineMoa  
- **개발 기간**: 2025.06.30 ~ (진행 중)  
- **참여 인원**: 2명  
- **핵심 목표**: Spring Boot 기반의 안정적인 백엔드 구조 설계 및 사용자 중심의 예매 인터페이스 구현  

---

## 2. 기술 스택

### Backend & Database
- Java / Spring Boot / Spring MVC / Spring Data JPA
- MySQL

### Frontend
- HTML5 / CSS3 / JavaScript / Mustache

### Tools & Environment
- IntelliJ IDEA / Gradle
- Git / GitHub

---

## 3. 데이터베이스 설계 (ERD)

![ERD](https://github.com/user-attachments/assets/069a7c38-4b86-48a1-8051-0a2880222938)

---

## 4. 담당 업무 및 기능 구현 상세

### 영화 정보 및 콘텐츠 관리 유닛
- **데이터 관리 로직**: 영화 등록, 수정, 삭제 및 상영 상태 전환 등 영화 정보 전반에 대한 관리 로직을 구현했습니다.
- **인터랙션 및 평가 시스템**: 영화별 좋아요, 댓글 및 평점 기능을 구축해서 사용자 참여형 콘텐츠 서비스를 설계했습니다.
- **다각도 검색 로직**: 영화명, 출연 배우, 감독명 등 다양한 속성 기반의 검색 기능을 구현했습니다.

### 예매 프로세스 및 랭킹 시스템
- **데이터 기반 랭킹**: 예매율 데이터를 실시간으로 집계하고 순위를 산출해서 메인 화면에 반영하는 기능을 구현했습니다.
- **예매 인터페이스**: 영화 선택부터 일정 확인까지 이어지는 예매 흐름을 직관적으로 구성했습니다.

---

## 5. 주요 구현 화면

| 영화 관리 및 정보 등록 | 콘텐츠 인터랙션 (좋아요/댓글) |
| :--- | :--- |
| ![영화관리](https://github.com/user-attachments/assets/b089c524-7295-4fb7-86e8-767d38babac1) | ![인터랙션](https://github.com/user-attachments/assets/efd806f4-610b-4616-8d75-e4851629fae7) |

| 멀티 조건 검색 시스템 | 예매율 기반 랭킹 및 예매 페이지 |
| :--- | :--- |
| ![검색](https://github.com/user-attachments/assets/092625ca-3fe2-4cff-bcce-ce64f069636f) | ![예매](https://github.com/user-attachments/assets/9d5d9f5d-7f75-49a2-a962-cdd3f8d4cc50) |

---

## 6. 업데이트 안내

본 프로젝트는 현재 개발이 진행 중인 상태이며, 다음 기능들을 추가로 구현할 예정입니다.
- 사용자 인증 보안 강화
- 좌석 선택 및 결제 모듈 통합
- 관리자 통계 대시보드 구축
