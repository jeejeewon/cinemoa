# 🎬 CineMoa | 영화 예매 사이트

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

## 📝 담당 업무 (이지원)

### 영화
- 영화 등록, 수정, 삭제 등 관리
- 상영 상태 반영

### 예매
- 예매율 적용 후 순위 출력
- 영화 예매 페이지 작업중

### 좋아요
- 좋아요 수 실시간 반영
- 중복없이 영화 당 한번씩만 가능하도록 구현

### 댓글 및 평점
- 댓글 수 실시간 반영
- 평점 등록 및 반영
