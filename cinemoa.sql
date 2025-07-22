-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: jiwonserver2.mysql.database.azure.com    Database: cinemoa
-- ------------------------------------------------------
-- Server version	8.0.41-azure

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cinemas`
--

DROP TABLE IF EXISTS `cinemas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cinemas` (
  `cinema_id` bigint NOT NULL AUTO_INCREMENT COMMENT '영화관 ID',
  `name` varchar(100) NOT NULL COMMENT '영화관 이름',
  `address` varchar(255) DEFAULT NULL COMMENT '영화관 주소',
  `region` varchar(100) DEFAULT NULL COMMENT '지역 (예: 서울, 부산 등)',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  PRIMARY KEY (`cinema_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='영화관 정보 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cinemas`
--

LOCK TABLES `cinemas` WRITE;
/*!40000 ALTER TABLE `cinemas` DISABLE KEYS */;
INSERT INTO `cinemas` VALUES (1,'CGV 강남','서울 강남구 역삼동 123-45','서울','2025-07-14 06:37:41'),(2,'롯데시네마 잠실','서울 송파구 잠실동 678-90','서울','2025-07-14 06:37:41'),(3,'메가박스 부산대','부산 금정구 장전동 11-22','부산','2025-07-14 06:37:41');
/*!40000 ALTER TABLE `cinemas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faq`
--

DROP TABLE IF EXISTS `faq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faq` (
  `faq_id` bigint NOT NULL AUTO_INCREMENT,
  `category` varchar(100) DEFAULT NULL COMMENT '카테고리 (예: 예매, 결제, 회원)',
  `question` varchar(255) NOT NULL COMMENT '질문',
  `answer` text NOT NULL COMMENT '답변',
  PRIMARY KEY (`faq_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faq`
--

LOCK TABLES `faq` WRITE;
/*!40000 ALTER TABLE `faq` DISABLE KEYS */;
/*!40000 ALTER TABLE `faq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guest_user`
--

DROP TABLE IF EXISTS `guest_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guest_user` (
  `guest_user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '비회원 고유 ID',
  `name` varchar(100) NOT NULL COMMENT '이름',
  `birth_date` date NOT NULL COMMENT '생년월일',
  `phone` varchar(20) NOT NULL COMMENT '휴대전화번호',
  `reservation_password` varchar(255) NOT NULL COMMENT '예매 비밀번호 (해싱 저장)',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '등록 일자',
  PRIMARY KEY (`guest_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='비회원 사용자 정보 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guest_user`
--

LOCK TABLES `guest_user` WRITE;
/*!40000 ALTER TABLE `guest_user` DISABLE KEYS */;
INSERT INTO `guest_user` VALUES (2,'윤지원','1991-11-12','01025565619','1234','2025-07-17 11:25:56');
/*!40000 ALTER TABLE `guest_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inquiry`
--

DROP TABLE IF EXISTS `inquiry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inquiry` (
  `inquiry_id` bigint NOT NULL AUTO_INCREMENT COMMENT '문의 고유번호',
  `member_id` varchar(50) DEFAULT NULL COMMENT '작성한 회원 ID',
  `title` varchar(255) DEFAULT NULL COMMENT '문의 제목',
  `content` text COMMENT '문의 내용',
  `reply_content` text COMMENT '관리자 답변 내용',
  `reg_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '문의 등록일',
  `reply_date` datetime DEFAULT NULL COMMENT '답변 등록일',
  `guest_user_id` bigint DEFAULT NULL COMMENT '비회원 고유 ID',
  PRIMARY KEY (`inquiry_id`),
  KEY `member_id` (`member_id`),
  KEY `fk_inquiry_guest` (`guest_user_id`),
  CONSTRAINT `fk_inquiry_guest` FOREIGN KEY (`guest_user_id`) REFERENCES `guest_user` (`guest_user_id`),
  CONSTRAINT `inquiry_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='1:1 문의 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inquiry`
--

LOCK TABLES `inquiry` WRITE;
/*!40000 ALTER TABLE `inquiry` DISABLE KEYS */;
INSERT INTO `inquiry` VALUES (2,'user01','[예매] 예매 문의드려요.','예매한 영화 취소를 하고싶습니다.',NULL,'2025-07-21 13:09:01',NULL,NULL),(3,'user01','[회원정보] 회원정보 문의','회원정보 문의',NULL,'2025-07-21 17:26:23',NULL,NULL),(4,'user01','[기타] 기타문의','기타문의',NULL,'2025-07-21 17:39:16',NULL,NULL);
/*!40000 ALTER TABLE `inquiry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes` (
  `like_id` bigint NOT NULL AUTO_INCREMENT COMMENT '좋아요 고유 ID',
  `movie_id` bigint NOT NULL COMMENT '좋아요를 받은 영화 게시물 ID',
  `user_id` varchar(50) DEFAULT NULL,
  `liked_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '좋아요를 누른 시간',
  PRIMARY KEY (`like_id`),
  UNIQUE KEY `movie_id` (`movie_id`,`user_id`),
  UNIQUE KEY `UK78ijgkjnnqcs5p4obxffn35ce` (`movie_id`,`user_id`),
  KEY `fk_likes_user` (`user_id`),
  CONSTRAINT `fk_likes_user` FOREIGN KEY (`user_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE,
  CONSTRAINT `likes_ibfk_1` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`movie_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` varchar(50) NOT NULL COMMENT '회원 아이디 (로그인 ID)',
  `password` varchar(255) NOT NULL COMMENT '비밀번호',
  `name` varchar(100) NOT NULL COMMENT '회원 이름 (변경 불가)',
  `nickname` varchar(50) NOT NULL COMMENT '닉네임',
  `birth_date` date DEFAULT NULL COMMENT '생년월일',
  `phone` varchar(20) DEFAULT NULL COMMENT '휴대전화번호',
  `email` varchar(100) DEFAULT NULL COMMENT '이메일',
  `profile_img` varchar(255) DEFAULT NULL COMMENT '프로필 이미지 경로',
  `anniversary` date DEFAULT NULL COMMENT '기념일 (예: 연인과의 기념일)',
  `address` varchar(255) DEFAULT NULL COMMENT '주소',
  `preferred_cinema` varchar(100) DEFAULT NULL COMMENT '자주 가는 영화관',
  `preferred_genres` varchar(255) DEFAULT NULL COMMENT '선호 영화 장르 (콤마 구분 예: 액션,코미디)',
  `reg_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '가입 일자',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '회원 탈퇴 여부',
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `nickname` (`nickname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='회원 정보 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('user01','1234','김유경','경이','1991-11-12','01011111111','user01@mail.com',NULL,NULL,'쥬소1234','2','코미디,로맨스','2025-07-15 01:16:13',0),('user02','1234','한승진','SGnee',NULL,NULL,'user02@mail.com','',NULL,NULL,NULL,NULL,'2025-07-17 08:20:31',0),('user03','1234','윤현정','롤롤',NULL,NULL,'yjw5619@gmail.com',NULL,NULL,NULL,'2','로맨스,스릴러,판타지','2025-07-18 03:16:19',0),('user04','1234','유저사번','USER4',NULL,NULL,'yjw5619@gmail.com',NULL,NULL,NULL,NULL,NULL,'2025-07-22 08:21:30',0),('wlsl369','1234','윤지원','디어니',NULL,NULL,'13toast@naver.com',NULL,NULL,NULL,NULL,NULL,'2025-07-15 13:06:13',0);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movies` (
  `movie_id` bigint NOT NULL AUTO_INCREMENT COMMENT '영화 게시물 고유 ID',
  `title` varchar(255) NOT NULL COMMENT '영화 제목',
  `content` mediumtext,
  `reservation_rate` decimal(38,2) DEFAULT NULL,
  `rating` decimal(38,2) DEFAULT NULL,
  `director` varchar(255) DEFAULT NULL,
  `actors` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `release_date` date DEFAULT NULL COMMENT '개봉일',
  `likes_count` int DEFAULT '0' COMMENT '좋아요 수',
  `review_count` int DEFAULT '0' COMMENT '댓글 수',
  `main_image_url` varchar(255) DEFAULT NULL COMMENT '메인 포스터 이미지 URL',
  `detail_image_urls` varchar(255) DEFAULT NULL,
  `video_url` varchar(255) DEFAULT NULL COMMENT '예고편 또는 관련 영상 URL',
  `audience_count` decimal(38,0) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '게시물 생성 시간',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '게시물 최종 수정 시간',
  `screening_status` enum('COMING_SOON','NOT_SHOWING','NOW_SHOWING') DEFAULT NULL,
  `sub_image_urls` text COMMENT '서브 이미지 URL들 (콤마로 구분)',
  `age_rating` varchar(20) DEFAULT NULL COMMENT '관람등급 (전체관람가, 12세, 15세, 청소년관람불가 등)',
  `running_time` int DEFAULT NULL COMMENT '러닝타임 (분 단위)',
  PRIMARY KEY (`movie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (1,'환상의 모험','신비로운 세계로 떠나는 용감한 모험가들의 이야기',75.20,8.50,'김감독','이배우, 박배우','판타지, 모험','2024-01-15',1200,350,'movie1_main.jpg','movie1_detail.jpg','movie1_video.mp4',1500000,'2025-07-14 06:37:35','2025-07-14 06:37:35','NOW_SHOWING','movie1_sub1.jpg,movie1_sub2.jpg','ALL',120),(2,'시간의 흔적','시간 여행을 통해 과거와 현재를 잇는 감동적인 드라마',68.10,7.90,'박감독','최배우, 정배우','SF, 드라마','2024-08-01',850,210,'movie2_main.jpg','movie2_detail.jpg','movie2_video.mp4',800000,'2025-07-14 06:37:35','2025-07-14 06:37:35','COMING_SOON','movie2_sub1.jpg','12',105),(3,'도시의 그림자','어두운 도시 속에서 펼쳐지는 스릴 넘치는 범죄 스릴러',82.50,9.10,'이감독','김주연, 송강호','스릴러, 범죄','2024-03-20',2500,520,'movie3_main.jpg','movie3_detail.jpg','movie3_video.mp4',2100000,'2025-07-14 06:37:35','2025-07-14 06:37:35','NOW_SHOWING','movie3_sub1.jpg,movie3_sub2.jpg','15',130),(4,'별들의 속삭임','우주를 배경으로 한 아름다운 사랑 이야기',55.00,7.20,'정감독','한소희, 류준열','로맨스, SF','2023-11-10',600,150,'movie4_main.jpg','movie4_detail.jpg','movie4_video.mp4',500000,'2025-07-14 06:37:35','2025-07-14 06:37:35','NOT_SHOWING','movie4_sub1.jpg','ALL',95),(5,'잊혀진 기억','기억 상실증에 걸린 주인공이 자신의 과거를 찾아가는 미스터리',70.30,8.80,'최감독','박서준, 김지원','미스터리, 드라마','2024-04-05',1800,400,'movie5_main.jpg','movie5_detail.jpg','movie5_video.mp4',1700000,'2025-07-14 06:37:35','2025-07-14 06:37:35','NOW_SHOWING','movie5_sub1.jpg,movie5_sub2.jpg','19',110),(6,'푸른 바다의 전설','심해 속 숨겨진 문명을 탐험하는 해양 어드벤처',62.80,7.50,'강감독','이민호, 전지현','어드벤처, 판타지','2024-09-10',700,180,'movie6_main.jpg','movie6_detail.jpg','movie6_video.mp4',650000,'2025-07-14 06:37:35','2025-07-14 06:37:35','COMING_SOON','movie6_sub1.jpg','12',140),(7,'마지막 전쟁','인류의 운명을 건 최후의 전쟁을 그린 액션 블록버스터',88.90,9.30,'윤감독','마동석, 손석구','액션, 전쟁','2024-05-12',3100,600,'movie7_main.jpg','movie7_detail.jpg','movie7_video.mp4',3000000,'2025-07-14 06:37:35','2025-07-14 06:37:35','NOW_SHOWING','movie7_sub1.jpg,movie7_sub2.jpg','15',125),(8,'꿈꾸는 마을','평화로운 시골 마을에 찾아온 기적 같은 이야기',45.60,6.80,'신감독','김유정, 박보검','드라마, 힐링','2023-10-01',400,100,'movie8_main.jpg','movie8_detail.jpg','movie8_video.mp4',350000,'2025-07-14 06:37:35','2025-07-14 06:37:35','NOT_SHOWING','movie8_sub1.jpg','ALL',100),(9,'붉은 달의 비밀','고대 전설 속에 숨겨진 붉은 달의 비밀을 파헤치는 스릴러',79.10,8.70,'오감독','조인성, 한효주','미스터리, 스릴러','2024-06-25',2000,450,'movie9_main.jpg','movie9_detail.jpg','movie9_video.mp4',1900000,'2025-07-14 06:37:35','2025-07-14 06:37:35','NOW_SHOWING','movie9_sub1.jpg,movie9_sub2.jpg','19',115),(10,'희망의 빛','절망 속에서 피어나는 작은 희망을 그린 감동적인 영화',65.40,8.00,'윤감독','이성민, 김혜수','드라마, 휴먼','2024-10-01',950,280,'movie10_main.jpg','movie10_detail.jpg','movie10_video.mp4',900000,'2025-07-14 06:37:35','2025-07-14 06:37:35','COMING_SOON','movie10_sub1.jpg','12',108);
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `notice_id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL COMMENT '공지 제목',
  `content` text NOT NULL COMMENT '공지 내용',
  `reg_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  `view_count` int DEFAULT '0' COMMENT '조회수',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `payment_id` bigint NOT NULL AUTO_INCREMENT COMMENT '결제 고유 ID',
  `reservation_id` bigint NOT NULL COMMENT '연결된 예약 ID',
  `amount` int NOT NULL COMMENT '결제 금액 (원)',
  `method` varchar(50) NOT NULL COMMENT '결제 수단 (예: 카드, 카카오페이)',
  `status` enum('PAID','CANCELLED','FAILED') DEFAULT 'PAID' COMMENT '결제 상태',
  `paid_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '결제 일시',
  `transaction_id` varchar(100) DEFAULT NULL COMMENT 'PG사 트랜잭션 식별자',
  PRIMARY KEY (`payment_id`),
  KEY `reservation_id` (`reservation_id`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`reservation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='결제 정보 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,1,20000,'신용카드','PAID','2025-07-16 12:35:45','TXN12345601'),(2,2,20000,'신용카드','PAID','2025-06-23 14:30:10','TXN12345602'),(3,3,30000,'카카오페이','CANCELLED','2025-07-13 12:00:05','TXN12345603'),(4,4,10000,'현장결제','PAID','2025-07-13 11:10:05','TXN12345604'),(5,5,20000,'신용카드','PAID','2025-07-11 09:00:10','TXN12345605'),(6,6,20000,'카카오페이','CANCELLED','2025-07-01 04:45:15','TXN12345606');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `reservation_id` bigint NOT NULL AUTO_INCREMENT,
  `reservation_time` datetime(6) DEFAULT NULL,
  `screen_id` bigint NOT NULL,
  `seat_info` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `cinema_id` bigint DEFAULT NULL,
  `member_id` varchar(50) DEFAULT NULL,
  `movie_id` bigint DEFAULT NULL,
  `showtime_id` bigint NOT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`reservation_id`),
  KEY `FK9mk9bnl5w9ooeitbf3xqyl0mp` (`cinema_id`),
  KEY `FK68999qe28ym9eqqlowybh9nvn` (`member_id`),
  KEY `FKt5qalkttsvvcumarqxvs5dm4d` (`movie_id`),
  KEY `FK85jof2r60aatpcc1mbl2vwvfx` (`showtime_id`),
  KEY `FK_reservation_screen` (`screen_id`),
  CONSTRAINT `FK68999qe28ym9eqqlowybh9nvn` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FK85jof2r60aatpcc1mbl2vwvfx` FOREIGN KEY (`showtime_id`) REFERENCES `showtimes` (`showtime_id`),
  CONSTRAINT `FK9mk9bnl5w9ooeitbf3xqyl0mp` FOREIGN KEY (`cinema_id`) REFERENCES `cinemas` (`cinema_id`),
  CONSTRAINT `FK_reservation_screen` FOREIGN KEY (`screen_id`) REFERENCES `screens` (`screen_id`),
  CONSTRAINT `FKt5qalkttsvvcumarqxvs5dm4d` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`movie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (1,'2025-07-19 12:35:31.000000',1,'A1,A2','예약완료',1,'user01',1,1,'신용카드'),(2,'2025-07-18 14:30:00.000000',2,'B3,B4','예약완료',1,'user01',2,2,'신용카드'),(3,'2025-07-17 10:00:00.000000',3,'C1,C2,C3','취소됨',1,'user01',3,3,'카카오페이'),(4,'2025-07-15 18:10:00.000000',1,'A1','예약완료',1,'user01',4,4,'현장결제'),(5,'2025-07-14 09:00:00.000000',2,'E5,E6','예약완료',1,'user01',1,1,'신용카드'),(6,'2025-07-13 20:45:00.000000',3,'F7,F8','취소됨',1,'user01',2,2,'카카오페이');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation_seats`
--

DROP TABLE IF EXISTS `reservation_seats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation_seats` (
  `reservation_seat_id` bigint NOT NULL AUTO_INCREMENT,
  `reservation_id` bigint NOT NULL,
  `seat_id` bigint NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `showtime_id` bigint NOT NULL,
  PRIMARY KEY (`reservation_seat_id`),
  UNIQUE KEY `unique_reservation_seat` (`reservation_id`,`seat_id`),
  KEY `seat_id` (`seat_id`),
  KEY `fk_reservationseat_showtime` (`showtime_id`),
  CONSTRAINT `fk_reservationseat_showtime` FOREIGN KEY (`showtime_id`) REFERENCES `showtimes` (`showtime_id`) ON DELETE CASCADE,
  CONSTRAINT `FKnudgxu516ine72mwxxoeu4cc6` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`reservation_id`),
  CONSTRAINT `FKo4tbgy4jg06hfaugqx2gxx9we` FOREIGN KEY (`seat_id`) REFERENCES `seats` (`seat_id`),
  CONSTRAINT `reservation_seats_ibfk_1` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`reservation_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_seats`
--

LOCK TABLES `reservation_seats` WRITE;
/*!40000 ALTER TABLE `reservation_seats` DISABLE KEYS */;
INSERT INTO `reservation_seats` VALUES (1,1,1,'2025-07-19 12:36:49',1),(2,1,2,'2025-07-19 12:36:49',1),(18,2,133,'2025-07-19 17:21:17',2),(19,2,134,'2025-07-19 17:21:17',2),(20,3,197,'2025-07-19 17:21:17',3),(21,3,198,'2025-07-19 17:21:17',3),(22,3,199,'2025-07-19 17:21:17',3),(23,4,1,'2025-07-19 17:21:17',4),(24,5,165,'2025-07-19 17:21:17',1),(25,5,166,'2025-07-19 17:21:17',1),(26,6,277,'2025-07-19 17:21:17',2),(27,6,278,'2025-07-19 17:21:17',2);
/*!40000 ALTER TABLE `reservation_seats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `review_id` bigint NOT NULL AUTO_INCREMENT COMMENT '댓글 고유 ID',
  `movie_id` bigint NOT NULL COMMENT '댓글이 달린 영화 게시물 ID',
  `user_id` varchar(50) NOT NULL,
  `content` text NOT NULL COMMENT '댓글 내용',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '댓글 생성 시간',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '댓글 최종 수정 시간',
  `is_positive` tinyint(1) DEFAULT NULL COMMENT '긍정 평가 여부 (true: 좋았어요, false: 별로였어요)',
  PRIMARY KEY (`review_id`),
  KEY `movie_id` (`movie_id`),
  KEY `fk_reviews_user` (`user_id`),
  CONSTRAINT `fk_reviews_user` FOREIGN KEY (`user_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE,
  CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`movie_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES (13,1,'user01','스토리가 정말 흥미로웠어요!','2025-07-19 15:21:22','2025-07-19 15:21:22',1),(14,3,'user01','ㅈㄷㅇㄱㅈㄹㄴㅇㄹ','2025-07-22 13:41:54','2025-07-22 13:41:54',1);
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `screens`
--

DROP TABLE IF EXISTS `screens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `screens` (
  `screen_id` bigint NOT NULL AUTO_INCREMENT,
  `cinema_id` bigint NOT NULL,
  `screen_name` varchar(50) DEFAULT NULL,
  `row_count` int NOT NULL DEFAULT '10',
  `column_count` int NOT NULL DEFAULT '15',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `total_seats` int NOT NULL,
  `screen_type` varchar(100) DEFAULT 'STANDARD' COMMENT '상영관 타입',
  PRIMARY KEY (`screen_id`),
  KEY `cinema_id` (`cinema_id`),
  CONSTRAINT `screens_ibfk_1` FOREIGN KEY (`cinema_id`) REFERENCES `cinemas` (`cinema_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `screens`
--

LOCK TABLES `screens` WRITE;
/*!40000 ALTER TABLE `screens` DISABLE KEYS */;
INSERT INTO `screens` VALUES (1,1,'1관',8,12,'2025-07-16 06:58:19','2025-07-16 06:58:19',96,'STANDARD'),(2,1,'2관',6,10,'2025-07-16 08:00:00','2025-07-16 08:00:00',60,'STANDARD'),(3,1,'3관',15,20,'2025-07-16 07:12:55','2025-07-16 07:12:55',300,'IMAX');
/*!40000 ALTER TABLE `screens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seats`
--

DROP TABLE IF EXISTS `seats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seats` (
  `seat_id` bigint NOT NULL AUTO_INCREMENT,
  `screen_id` bigint NOT NULL,
  `seat_row` varchar(1) NOT NULL,
  `seat_number` int NOT NULL,
  `seat_type` varchar(100) DEFAULT NULL,
  `price` int NOT NULL DEFAULT '10000' COMMENT '좌석 가격 (원 단위)',
  PRIMARY KEY (`seat_id`),
  UNIQUE KEY `unique_seat` (`screen_id`,`seat_row`,`seat_number`),
  CONSTRAINT `seats_ibfk_1` FOREIGN KEY (`screen_id`) REFERENCES `screens` (`screen_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=457 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seats`
--

LOCK TABLES `seats` WRITE;
/*!40000 ALTER TABLE `seats` DISABLE KEYS */;
INSERT INTO `seats` VALUES (1,1,'A',1,'STANDARD',10000),(2,1,'A',2,'STANDARD',10000),(3,1,'A',3,'ACCESSIBLE',8000),(4,1,'A',4,'ACCESSIBLE',8000),(5,1,'A',5,'STANDARD',10000),(6,1,'A',6,'STANDARD',10000),(7,1,'A',7,'STANDARD',10000),(8,1,'A',8,'STANDARD',10000),(9,1,'A',9,'VIP',15000),(10,1,'A',10,'VIP',15000),(11,1,'A',11,'VIP',15000),(12,1,'A',12,'VIP',15000),(13,1,'B',1,'STANDARD',10000),(14,1,'B',2,'STANDARD',10000),(15,1,'B',3,'ACCESSIBLE',8000),(16,1,'B',4,'ACCESSIBLE',8000),(17,1,'B',5,'STANDARD',10000),(18,1,'B',6,'STANDARD',10000),(19,1,'B',7,'STANDARD',10000),(20,1,'B',8,'STANDARD',10000),(21,1,'B',9,'VIP',15000),(22,1,'B',10,'VIP',15000),(23,1,'B',11,'VIP',15000),(24,1,'B',12,'VIP',15000),(25,1,'C',1,'STANDARD',10000),(26,1,'C',2,'STANDARD',10000),(27,1,'C',3,'STANDARD',10000),(28,1,'C',4,'STANDARD',10000),(29,1,'C',5,'STANDARD',10000),(30,1,'C',6,'STANDARD',10000),(31,1,'C',7,'STANDARD',10000),(32,1,'C',8,'STANDARD',10000),(33,1,'C',9,'VIP',15000),(34,1,'C',10,'VIP',15000),(35,1,'C',11,'VIP',15000),(36,1,'C',12,'VIP',15000),(37,1,'D',1,'STANDARD',10000),(38,1,'D',2,'STANDARD',10000),(39,1,'D',3,'STANDARD',10000),(40,1,'D',4,'STANDARD',10000),(41,1,'D',5,'STANDARD',10000),(42,1,'D',6,'STANDARD',10000),(43,1,'D',7,'STANDARD',10000),(44,1,'D',8,'STANDARD',10000),(45,1,'D',9,'VIP',15000),(46,1,'D',10,'VIP',15000),(47,1,'D',11,'VIP',15000),(48,1,'D',12,'VIP',15000),(49,1,'E',1,'STANDARD',10000),(50,1,'E',2,'STANDARD',10000),(51,1,'E',3,'STANDARD',10000),(52,1,'E',4,'STANDARD',10000),(53,1,'E',5,'STANDARD',10000),(54,1,'E',6,'STANDARD',10000),(55,1,'E',7,'STANDARD',10000),(56,1,'E',8,'STANDARD',10000),(57,1,'E',9,'VIP',15000),(58,1,'E',10,'VIP',15000),(59,1,'E',11,'VIP',15000),(60,1,'E',12,'VIP',15000),(61,1,'F',1,'STANDARD',10000),(62,1,'F',2,'STANDARD',10000),(63,1,'F',3,'STANDARD',10000),(64,1,'F',4,'STANDARD',10000),(65,1,'F',5,'STANDARD',10000),(66,1,'F',6,'STANDARD',10000),(67,1,'F',7,'STANDARD',10000),(68,1,'F',8,'STANDARD',10000),(69,1,'F',9,'VIP',15000),(70,1,'F',10,'VIP',15000),(71,1,'F',11,'VIP',15000),(72,1,'F',12,'VIP',15000),(73,1,'G',1,'STANDARD',10000),(74,1,'G',2,'STANDARD',10000),(75,1,'G',3,'STANDARD',10000),(76,1,'G',4,'STANDARD',10000),(77,1,'G',5,'STANDARD',10000),(78,1,'G',6,'STANDARD',10000),(79,1,'G',7,'STANDARD',10000),(80,1,'G',8,'STANDARD',10000),(81,1,'G',9,'VIP',15000),(82,1,'G',10,'VIP',15000),(83,1,'G',11,'VIP',15000),(84,1,'G',12,'VIP',15000),(85,1,'H',1,'STANDARD',10000),(86,1,'H',2,'STANDARD',10000),(87,1,'H',3,'STANDARD',10000),(88,1,'H',4,'STANDARD',10000),(89,1,'H',5,'STANDARD',10000),(90,1,'H',6,'STANDARD',10000),(91,1,'H',7,'STANDARD',10000),(92,1,'H',8,'STANDARD',10000),(93,1,'H',9,'VIP',15000),(94,1,'H',10,'VIP',15000),(95,1,'H',11,'VIP',15000),(96,1,'H',12,'VIP',15000),(97,2,'A',1,'STANDARD',10000),(98,2,'A',2,'STANDARD',10000),(99,2,'A',3,'ACCESSIBLE',8000),(100,2,'A',4,'ACCESSIBLE',8000),(101,2,'A',5,'STANDARD',10000),(102,2,'A',6,'STANDARD',10000),(103,2,'A',7,'STANDARD',10000),(104,2,'A',8,'STANDARD',10000),(105,2,'A',9,'VIP',15000),(106,2,'A',10,'VIP',15000),(107,2,'B',1,'STANDARD',10000),(108,2,'B',2,'STANDARD',10000),(109,2,'B',3,'ACCESSIBLE',8000),(110,2,'B',4,'ACCESSIBLE',8000),(111,2,'B',5,'STANDARD',10000),(112,2,'B',6,'STANDARD',10000),(113,2,'B',7,'STANDARD',10000),(114,2,'B',8,'STANDARD',10000),(115,2,'B',9,'VIP',15000),(116,2,'B',10,'VIP',15000),(117,2,'C',1,'STANDARD',10000),(118,2,'C',2,'STANDARD',10000),(119,2,'C',3,'STANDARD',10000),(120,2,'C',4,'STANDARD',10000),(121,2,'C',5,'STANDARD',10000),(122,2,'C',6,'STANDARD',10000),(123,2,'C',7,'STANDARD',10000),(124,2,'C',8,'STANDARD',10000),(125,2,'C',9,'VIP',15000),(126,2,'C',10,'VIP',15000),(127,2,'D',1,'STANDARD',10000),(128,2,'D',2,'STANDARD',10000),(129,2,'D',3,'STANDARD',10000),(130,2,'D',4,'STANDARD',10000),(131,2,'D',5,'STANDARD',10000),(132,2,'D',6,'STANDARD',10000),(133,2,'D',7,'STANDARD',10000),(134,2,'D',8,'STANDARD',10000),(135,2,'D',9,'VIP',15000),(136,2,'D',10,'VIP',15000),(137,2,'E',1,'STANDARD',10000),(138,2,'E',2,'STANDARD',10000),(139,2,'E',3,'STANDARD',10000),(140,2,'E',4,'STANDARD',10000),(141,2,'E',5,'STANDARD',10000),(142,2,'E',6,'STANDARD',10000),(143,2,'E',7,'STANDARD',10000),(144,2,'E',8,'STANDARD',10000),(145,2,'E',9,'VIP',15000),(146,2,'E',10,'VIP',15000),(147,2,'F',1,'STANDARD',10000),(148,2,'F',2,'STANDARD',10000),(149,2,'F',3,'STANDARD',10000),(150,2,'F',4,'STANDARD',10000),(151,2,'F',5,'STANDARD',10000),(152,2,'F',6,'STANDARD',10000),(153,2,'F',7,'STANDARD',10000),(154,2,'F',8,'STANDARD',10000),(155,2,'F',9,'VIP',15000),(156,2,'F',10,'VIP',15000),(157,3,'A',1,'ACCESSIBLE',8000),(158,3,'A',2,'ACCESSIBLE',8000),(159,3,'A',3,'ACCESSIBLE',8000),(160,3,'A',4,'ACCESSIBLE',8000),(161,3,'A',5,'STANDARD',10000),(162,3,'A',6,'STANDARD',10000),(163,3,'A',7,'STANDARD',10000),(164,3,'A',8,'STANDARD',10000),(165,3,'A',9,'STANDARD',10000),(166,3,'A',10,'STANDARD',10000),(167,3,'A',11,'STANDARD',10000),(168,3,'A',12,'STANDARD',10000),(169,3,'A',13,'STANDARD',10000),(170,3,'A',14,'STANDARD',10000),(171,3,'A',15,'STANDARD',10000),(172,3,'A',16,'STANDARD',10000),(173,3,'A',17,'STANDARD',10000),(174,3,'A',18,'STANDARD',10000),(175,3,'A',19,'STANDARD',10000),(176,3,'A',20,'STANDARD',10000),(177,3,'B',1,'STANDARD',10000),(178,3,'B',2,'STANDARD',10000),(179,3,'B',3,'STANDARD',10000),(180,3,'B',4,'STANDARD',10000),(181,3,'B',5,'STANDARD',10000),(182,3,'B',6,'STANDARD',10000),(183,3,'B',7,'STANDARD',10000),(184,3,'B',8,'STANDARD',10000),(185,3,'B',9,'STANDARD',10000),(186,3,'B',10,'STANDARD',10000),(187,3,'B',11,'STANDARD',10000),(188,3,'B',12,'STANDARD',10000),(189,3,'B',13,'STANDARD',10000),(190,3,'B',14,'STANDARD',10000),(191,3,'B',15,'STANDARD',10000),(192,3,'B',16,'STANDARD',10000),(193,3,'B',17,'STANDARD',10000),(194,3,'B',18,'STANDARD',10000),(195,3,'B',19,'STANDARD',10000),(196,3,'B',20,'STANDARD',10000),(197,3,'C',1,'STANDARD',10000),(198,3,'C',2,'STANDARD',10000),(199,3,'C',3,'STANDARD',10000),(200,3,'C',4,'STANDARD',10000),(201,3,'C',5,'STANDARD',10000),(202,3,'C',6,'STANDARD',10000),(203,3,'C',7,'STANDARD',10000),(204,3,'C',8,'STANDARD',10000),(205,3,'C',9,'STANDARD',10000),(206,3,'C',10,'STANDARD',10000),(207,3,'C',11,'STANDARD',10000),(208,3,'C',12,'STANDARD',10000),(209,3,'C',13,'STANDARD',10000),(210,3,'C',14,'STANDARD',10000),(211,3,'C',15,'STANDARD',10000),(212,3,'C',16,'STANDARD',10000),(213,3,'C',17,'STANDARD',10000),(214,3,'C',18,'STANDARD',10000),(215,3,'C',19,'STANDARD',10000),(216,3,'C',20,'STANDARD',10000),(217,3,'D',1,'STANDARD',10000),(218,3,'D',2,'STANDARD',10000),(219,3,'D',3,'STANDARD',10000),(220,3,'D',4,'STANDARD',10000),(221,3,'D',5,'STANDARD',10000),(222,3,'D',6,'STANDARD',10000),(223,3,'D',7,'STANDARD',10000),(224,3,'D',8,'STANDARD',10000),(225,3,'D',9,'STANDARD',10000),(226,3,'D',10,'STANDARD',10000),(227,3,'D',11,'STANDARD',10000),(228,3,'D',12,'STANDARD',10000),(229,3,'D',13,'STANDARD',10000),(230,3,'D',14,'STANDARD',10000),(231,3,'D',15,'STANDARD',10000),(232,3,'D',16,'STANDARD',10000),(233,3,'D',17,'STANDARD',10000),(234,3,'D',18,'STANDARD',10000),(235,3,'D',19,'STANDARD',10000),(236,3,'D',20,'STANDARD',10000),(237,3,'E',1,'STANDARD',10000),(238,3,'E',2,'STANDARD',10000),(239,3,'E',3,'STANDARD',10000),(240,3,'E',4,'STANDARD',10000),(241,3,'E',5,'STANDARD',10000),(242,3,'E',6,'STANDARD',10000),(243,3,'E',7,'STANDARD',10000),(244,3,'E',8,'STANDARD',10000),(245,3,'E',9,'STANDARD',10000),(246,3,'E',10,'STANDARD',10000),(247,3,'E',11,'STANDARD',10000),(248,3,'E',12,'STANDARD',10000),(249,3,'E',13,'STANDARD',10000),(250,3,'E',14,'STANDARD',10000),(251,3,'E',15,'STANDARD',10000),(252,3,'E',16,'STANDARD',10000),(253,3,'E',17,'STANDARD',10000),(254,3,'E',18,'STANDARD',10000),(255,3,'E',19,'STANDARD',10000),(256,3,'E',20,'STANDARD',10000),(257,3,'F',1,'STANDARD',10000),(258,3,'F',2,'STANDARD',10000),(259,3,'F',3,'STANDARD',10000),(260,3,'F',4,'STANDARD',10000),(261,3,'F',5,'STANDARD',10000),(262,3,'F',6,'STANDARD',10000),(263,3,'F',7,'STANDARD',10000),(264,3,'F',8,'STANDARD',10000),(265,3,'F',9,'STANDARD',10000),(266,3,'F',10,'STANDARD',10000),(267,3,'F',11,'STANDARD',10000),(268,3,'F',12,'STANDARD',10000),(269,3,'F',13,'STANDARD',10000),(270,3,'F',14,'STANDARD',10000),(271,3,'F',15,'STANDARD',10000),(272,3,'F',16,'STANDARD',10000),(273,3,'F',17,'STANDARD',10000),(274,3,'F',18,'STANDARD',10000),(275,3,'F',19,'STANDARD',10000),(276,3,'F',20,'STANDARD',10000),(277,3,'G',1,'STANDARD',10000),(278,3,'G',2,'STANDARD',10000),(279,3,'G',3,'STANDARD',10000),(280,3,'G',4,'STANDARD',10000),(281,3,'G',5,'STANDARD',10000),(282,3,'G',6,'STANDARD',10000),(283,3,'G',7,'STANDARD',10000),(284,3,'G',8,'STANDARD',10000),(285,3,'G',9,'STANDARD',10000),(286,3,'G',10,'STANDARD',10000),(287,3,'G',11,'STANDARD',10000),(288,3,'G',12,'STANDARD',10000),(289,3,'G',13,'STANDARD',10000),(290,3,'G',14,'STANDARD',10000),(291,3,'G',15,'STANDARD',10000),(292,3,'G',16,'STANDARD',10000),(293,3,'G',17,'STANDARD',10000),(294,3,'G',18,'STANDARD',10000),(295,3,'G',19,'STANDARD',10000),(296,3,'G',20,'STANDARD',10000),(297,3,'H',1,'STANDARD',10000),(298,3,'H',2,'STANDARD',10000),(299,3,'H',3,'STANDARD',10000),(300,3,'H',4,'STANDARD',10000),(301,3,'H',5,'STANDARD',10000),(302,3,'H',6,'STANDARD',10000),(303,3,'H',7,'STANDARD',10000),(304,3,'H',8,'STANDARD',10000),(305,3,'H',9,'STANDARD',10000),(306,3,'H',10,'STANDARD',10000),(307,3,'H',11,'STANDARD',10000),(308,3,'H',12,'STANDARD',10000),(309,3,'H',13,'STANDARD',10000),(310,3,'H',14,'STANDARD',10000),(311,3,'H',15,'STANDARD',10000),(312,3,'H',16,'STANDARD',10000),(313,3,'H',17,'STANDARD',10000),(314,3,'H',18,'STANDARD',10000),(315,3,'H',19,'STANDARD',10000),(316,3,'H',20,'STANDARD',10000),(317,3,'I',1,'STANDARD',10000),(318,3,'I',2,'STANDARD',10000),(319,3,'I',3,'STANDARD',10000),(320,3,'I',4,'STANDARD',10000),(321,3,'I',5,'STANDARD',10000),(322,3,'I',6,'STANDARD',10000),(323,3,'I',7,'STANDARD',10000),(324,3,'I',8,'STANDARD',10000),(325,3,'I',9,'STANDARD',10000),(326,3,'I',10,'STANDARD',10000),(327,3,'I',11,'STANDARD',10000),(328,3,'I',12,'STANDARD',10000),(329,3,'I',13,'STANDARD',10000),(330,3,'I',14,'STANDARD',10000),(331,3,'I',15,'STANDARD',10000),(332,3,'I',16,'STANDARD',10000),(333,3,'I',17,'STANDARD',10000),(334,3,'I',18,'STANDARD',10000),(335,3,'I',19,'STANDARD',10000),(336,3,'I',20,'STANDARD',10000),(337,3,'J',1,'STANDARD',10000),(338,3,'J',2,'STANDARD',10000),(339,3,'J',3,'STANDARD',10000),(340,3,'J',4,'STANDARD',10000),(341,3,'J',5,'STANDARD',10000),(342,3,'J',6,'STANDARD',10000),(343,3,'J',7,'STANDARD',10000),(344,3,'J',8,'STANDARD',10000),(345,3,'J',9,'STANDARD',10000),(346,3,'J',10,'STANDARD',10000),(347,3,'J',11,'STANDARD',10000),(348,3,'J',12,'STANDARD',10000),(349,3,'J',13,'STANDARD',10000),(350,3,'J',14,'STANDARD',10000),(351,3,'J',15,'STANDARD',10000),(352,3,'J',16,'STANDARD',10000),(353,3,'J',17,'STANDARD',10000),(354,3,'J',18,'STANDARD',10000),(355,3,'J',19,'STANDARD',10000),(356,3,'J',20,'STANDARD',10000),(357,3,'K',1,'STANDARD',10000),(358,3,'K',2,'STANDARD',10000),(359,3,'K',3,'STANDARD',10000),(360,3,'K',4,'STANDARD',10000),(361,3,'K',5,'STANDARD',10000),(362,3,'K',6,'STANDARD',10000),(363,3,'K',7,'STANDARD',10000),(364,3,'K',8,'STANDARD',10000),(365,3,'K',9,'STANDARD',10000),(366,3,'K',10,'STANDARD',10000),(367,3,'K',11,'STANDARD',10000),(368,3,'K',12,'STANDARD',10000),(369,3,'K',13,'STANDARD',10000),(370,3,'K',14,'STANDARD',10000),(371,3,'K',15,'STANDARD',10000),(372,3,'K',16,'STANDARD',10000),(373,3,'K',17,'STANDARD',10000),(374,3,'K',18,'STANDARD',10000),(375,3,'K',19,'STANDARD',10000),(376,3,'K',20,'STANDARD',10000),(377,3,'L',1,'STANDARD',10000),(378,3,'L',2,'STANDARD',10000),(379,3,'L',3,'STANDARD',10000),(380,3,'L',4,'STANDARD',10000),(381,3,'L',5,'STANDARD',10000),(382,3,'L',6,'STANDARD',10000),(383,3,'L',7,'STANDARD',10000),(384,3,'L',8,'VIP',15000),(385,3,'L',9,'VIP',15000),(386,3,'L',10,'VIP',15000),(387,3,'L',11,'VIP',15000),(388,3,'L',12,'VIP',15000),(389,3,'L',13,'VIP',15000),(390,3,'L',14,'STANDARD',10000),(391,3,'L',15,'STANDARD',10000),(392,3,'L',16,'STANDARD',10000),(393,3,'L',17,'STANDARD',10000),(394,3,'L',18,'STANDARD',10000),(395,3,'L',19,'STANDARD',10000),(396,3,'L',20,'STANDARD',10000),(397,3,'M',1,'STANDARD',10000),(398,3,'M',2,'STANDARD',10000),(399,3,'M',3,'STANDARD',10000),(400,3,'M',4,'STANDARD',10000),(401,3,'M',5,'STANDARD',10000),(402,3,'M',6,'STANDARD',10000),(403,3,'M',7,'STANDARD',10000),(404,3,'M',8,'VIP',15000),(405,3,'M',9,'VIP',15000),(406,3,'M',10,'VIP',15000),(407,3,'M',11,'VIP',15000),(408,3,'M',12,'VIP',15000),(409,3,'M',13,'VIP',15000),(410,3,'M',14,'STANDARD',10000),(411,3,'M',15,'STANDARD',10000),(412,3,'M',16,'STANDARD',10000),(413,3,'M',17,'STANDARD',10000),(414,3,'M',18,'STANDARD',10000),(415,3,'M',19,'STANDARD',10000),(416,3,'M',20,'STANDARD',10000),(417,3,'N',1,'STANDARD',10000),(418,3,'N',2,'STANDARD',10000),(419,3,'N',3,'STANDARD',10000),(420,3,'N',4,'STANDARD',10000),(421,3,'N',5,'STANDARD',10000),(422,3,'N',6,'STANDARD',10000),(423,3,'N',7,'STANDARD',10000),(424,3,'N',8,'VIP',15000),(425,3,'N',9,'VIP',15000),(426,3,'N',10,'VIP',15000),(427,3,'N',11,'VIP',15000),(428,3,'N',12,'VIP',15000),(429,3,'N',13,'VIP',15000),(430,3,'N',14,'STANDARD',10000),(431,3,'N',15,'STANDARD',10000),(432,3,'N',16,'STANDARD',10000),(433,3,'N',17,'STANDARD',10000),(434,3,'N',18,'STANDARD',10000),(435,3,'N',19,'STANDARD',10000),(436,3,'N',20,'STANDARD',10000),(437,3,'O',1,'STANDARD',10000),(438,3,'O',2,'STANDARD',10000),(439,3,'O',3,'STANDARD',10000),(440,3,'O',4,'STANDARD',10000),(441,3,'O',5,'STANDARD',10000),(442,3,'O',6,'STANDARD',10000),(443,3,'O',7,'STANDARD',10000),(444,3,'O',8,'VIP',15000),(445,3,'O',9,'VIP',15000),(446,3,'O',10,'VIP',15000),(447,3,'O',11,'VIP',15000),(448,3,'O',12,'VIP',15000),(449,3,'O',13,'VIP',15000),(450,3,'O',14,'STANDARD',10000),(451,3,'O',15,'STANDARD',10000),(452,3,'O',16,'STANDARD',10000),(453,3,'O',17,'STANDARD',10000),(454,3,'O',18,'STANDARD',10000),(455,3,'O',19,'STANDARD',10000),(456,3,'O',20,'STANDARD',10000);
/*!40000 ALTER TABLE `seats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `showtimes`
--

DROP TABLE IF EXISTS `showtimes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `showtimes` (
  `showtime_id` bigint NOT NULL AUTO_INCREMENT,
  `movie_id` bigint NOT NULL,
  `screen_id` bigint NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `available_seats` int NOT NULL,
  `price` decimal(8,2) NOT NULL,
  PRIMARY KEY (`showtime_id`),
  KEY `idx_movie_id` (`movie_id`),
  KEY `idx_screen_id` (`screen_id`),
  KEY `idx_start_time` (`start_time`),
  CONSTRAINT `fk_showtimes_screen` FOREIGN KEY (`screen_id`) REFERENCES `screens` (`screen_id`) ON DELETE CASCADE,
  CONSTRAINT `showtimes_ibfk_1` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`movie_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `showtimes`
--

LOCK TABLES `showtimes` WRITE;
/*!40000 ALTER TABLE `showtimes` DISABLE KEYS */;
INSERT INTO `showtimes` VALUES (1,1,1,'2025-07-30 10:00:00','2025-07-30 12:00:00','2025-07-14 06:37:46','2025-07-22 08:01:28',0,0.00),(2,1,1,'2025-07-30 13:00:00','2025-07-30 15:00:00','2025-07-14 06:37:46','2025-07-22 08:01:28',0,0.00),(3,1,3,'2025-07-30 14:00:00','2025-07-30 16:00:00','2025-07-14 06:37:46','2025-07-22 08:01:28',0,0.00),(4,1,3,'2025-07-30 17:00:00','2025-07-30 19:00:00','2025-07-14 06:37:46','2025-07-22 08:01:28',0,0.00),(5,2,2,'2025-07-30 11:00:00','2025-07-30 13:00:00','2025-07-14 06:37:46','2025-07-22 08:01:28',0,0.00),(6,2,2,'2025-07-30 14:00:00','2025-07-30 16:00:00','2025-07-14 06:37:46','2025-07-22 08:01:28',0,0.00),(7,2,3,'2025-07-30 12:30:00','2025-07-30 14:30:00','2025-07-14 06:37:46','2025-07-22 08:01:28',0,0.00),(8,3,3,'2025-07-30 12:00:00','2025-07-30 14:30:00','2025-07-14 06:37:46','2025-07-22 08:01:28',0,0.00),(9,3,3,'2025-07-30 15:30:00','2025-07-30 18:00:00','2025-07-14 06:37:46','2025-07-22 08:01:28',0,0.00),(10,3,3,'2025-07-30 13:00:00','2025-07-30 15:30:00','2025-07-14 06:37:46','2025-07-22 08:02:07',0,0.00),(11,4,1,'2025-07-30 16:00:00','2025-07-30 17:35:00','2025-07-14 06:37:46','2025-07-22 08:02:07',0,0.00),(12,4,3,'2025-07-30 15:00:00','2025-07-30 16:35:00','2025-07-14 06:37:46','2025-07-22 08:02:07',0,0.00),(13,5,2,'2025-07-30 17:00:00','2025-07-30 18:50:00','2025-07-14 06:37:46','2025-07-22 08:02:07',0,0.00),(14,5,3,'2025-07-30 20:00:00','2025-07-30 21:50:00','2025-07-14 06:37:46','2025-07-22 08:02:07',0,0.00),(15,7,3,'2025-07-30 19:00:00','2025-07-30 21:05:00','2025-07-14 06:37:46','2025-07-22 08:02:07',0,0.00),(16,7,3,'2025-07-30 16:00:00','2025-07-30 18:05:00','2025-07-14 06:37:46','2025-07-22 08:02:07',0,0.00);
/*!40000 ALTER TABLE `showtimes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'cinemoa'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-22 18:03:19
