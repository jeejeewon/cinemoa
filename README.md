# 🎬 CineMoa | 영화 예매 웹사이트

Spring Boot 기반으로 개발 중인 영화 예매 플랫폼입니다.  
사용자는 영화 목록을 조회하고, 상세 페이지에서 정보를 확인한 뒤, 예매까지 진행할 수 있도록 설계되었습니다.

> 🔧 본 프로젝트는 현재 **개발 중**입니다. 지속적으로 기능이 추가될 예정입니다.

---

> ⚠️ **안내**  
> 이 리드미는 본 프로젝트 저장소 [cinemoa](https://github.com/YOON-J11/cinemoa)에서 일부 내용을 복사해
> 팀원 작업 내용은 제외하고 본인 작업 내용 위주로 정리한 파일입니다.  
> 본 프로젝트는 팀원과 함께 진행 중이며, 이 저장소는 개인 작업 및 보여주기용으로 사용되고 있습니다.

---

## 📝 프로젝트 개요

- **프로젝트명**: CineMoa  
- **개발 기간**: 2025.06.30 ~ (진행 중)  
- **개발 인원**: 2명
  
---

## 🛠 사용 기술

### 🧑‍💻 Backend
![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring MVC](https://img.shields.io/badge/Spring_MVC-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![JPA](https://img.shields.io/badge/JPA-FF6F61?style=for-the-badge)

### 🧑‍🎨 Frontend
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![Mustache](https://img.shields.io/badge/Mustache-000000?style=for-the-badge&logo=mustache&logoColor=white)

### 🛢 Database
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

### ⚙ 개발 환경 및 도구
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000?style=for-the-badge&logo=intellijidea&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)

---

## 📦 DB 구성

아래는 프로젝트에서 사용된 테이블 간의 ERD(Entity Relationship Diagram)입니다.  
<img width="1319" height="859" alt="ERD" src="https://github.com/user-attachments/assets/069a7c38-4b86-48a1-8051-0a2880222938" />

---

## 📝 담당 업무 (윤지원)

### 🖥 UI / 레이아웃
- 헤더, 푸터 디자인 및 레이아웃 구조 설계 (HTML, CSS, JS 포함)  
  <img width="947" height="145" alt="헤더" src="https://github.com/user-attachments/assets/1fbb09db-6f64-41d0-a025-6ee824f6a0fd" />  
  
- 메뉴 내 JS 기능 적용  
  <img width="949" height="63" alt="헤더2" src="https://github.com/user-attachments/assets/a93397cc-a28c-42f0-b421-227a4ef887a5" />
  
- 스크롤 탑 기능 구현  
  <img width="223" height="89" alt="스크롤탑고정버튼" src="https://github.com/user-attachments/assets/23048f2d-ebdf-44e3-83db-f93087ddfefd" />  

---

### 🔑 인증 및 회원 관리
- 로그인 / 로그아웃 기능 및 비회원 로그인 구현  
  <img width="414" height="635" alt="비회원로그인" src="https://github.com/user-attachments/assets/9412d621-0549-46e1-a648-0a452372ba75" />
  <img width="455" height="517" alt="로그인" src="https://github.com/user-attachments/assets/dcf4c20a-8b7d-40d3-b6fb-33d186e60cec" />
  
- 회원가입 기능 구현  
  - 이메일 인증 기능 포함  
  <img width="582" height="460" alt="회원가입_이메일인증" src="https://github.com/user-attachments/assets/e58e9f33-378c-4a01-a699-16e0e345bc42" />  
  <img width="760" height="592" alt="인증번호" src="https://github.com/user-attachments/assets/9edea297-d096-43d7-9523-18e6fc972c59" />  

  - 기존 DB와 중복 아이디 확인 기능  
  <img width="591" height="738" alt="회원정보입력페이지2" src="https://github.com/user-attachments/assets/f497e037-1776-41f6-8e46-d7c2e42a830b" />

---

### 🎟 예매 관련
- 예매 시 좌석 선택 페이지 개발  
  <img width="1186" height="920" alt="좌석선택페이지" src="https://github.com/user-attachments/assets/98f751e2-028d-436c-91d5-3d551510c830" />

- 결제 시스템 개발 (진행 중)  

---

### 👤 마이페이지  
<img width="1194" height="916" alt="마이페이지홈" src="https://github.com/user-attachments/assets/fc695730-6af9-44ec-89ee-d50be7bea648" />

- 예매 내역 및 영화 내역 리스트 출력  
  <img width="1192" height="845" alt="예매내역" src="https://github.com/user-attachments/assets/64e0c13b-27f3-4879-9407-d67ec2836341" />

- 회원 정보 관리 (프로필 수정, 개인정보 수정, 선호 관람 정보 설정, 회원 탈퇴)  
  <img width="794" height="721" alt="프로필수정팝업" src="https://github.com/user-attachments/assets/03d35120-5cf6-4848-82bd-69d151a4e500" />
  <img width="1174" height="909" alt="회원탈퇴" src="https://github.com/user-attachments/assets/341c3ead-936b-4b55-9978-c2e08e9beff7" />

---

### 📝 고객센터
- 1:1 문의 구현  
- 공지사항, 자주 묻는 질문은 추후 구현 예정  
  <img width="1177" height="887" alt="고객센터문의하" src="https://github.com/user-attachments/assets/e9eb7a8d-a4a0-4f89-97ce-0b25c0978b2a" />

---
