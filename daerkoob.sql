-- MySQL dump 10.13  Distrib 8.0.26, for macos11 (x86_64)
--
-- Host: localhost    Database: daerkoob
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `book_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `publisher` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pubdate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `isbn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `description` longtext,
  `transcription_count` int NOT NULL,
  `review_count` int NOT NULL,
  `star` double NOT NULL,
  `star_count` varchar(45) NOT NULL,
  PRIMARY KEY (`book_id`),
  UNIQUE KEY `book_id_UNIQUE` (`book_id`),
  UNIQUE KEY `ibsn_UNIQUE` (`isbn`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'김이나의 작사법 (우리의 감정을 사로잡는 일상의 언어들)','김이나','문학동네','20150318','8954635601 9788954635608','https://bookthumb-phinf.pstatic.net/cover/088/824/08882431.jpg?type=m1&udate=20220101','아이유 윤상 허지웅의 감정을 두드린 이 책!이선희 〈그중에 그대를 만나〉, 조용필 〈걷고 싶다〉, 아이유 〈좋은 날〉, 브라운아이드걸스 〈아브라카다브라〉, 성시경 〈10월에 눈이 내리면〉, 엑소 〈LUCKY〉,가인 〈APPLE〉 〈PARADISE LOST〉 등 300여 곡\n\n대한민국 작사가 저작권료 수입 1위!\n대중을... ',6,12,3.67,'6'),(2,'마블 스파이더맨 백과사전','매슈 K. 매닝|톰 데팔코','아르누보','20170707','1187824054 9791187824053','https://bookthumb-phinf.pstatic.net/cover/121/832/12183284.jpg?type=m1&udate=20220405','우리의 다정한 이웃, 가장 사랑받는 히어로\n스파이더맨의 모든 것을 한 권에!마블 최고의 인기 히어로, 스파이더맨을 낱낱이 파헤친다! 《스파이더맨 백과사전》은 1962년 8월에 첫선을 보인 후 오늘날까지 히어로의 대명사로 사랑받는 스파이더맨의 모든 것을 담은 가이드북이다. 시대에 따라 다양하게... ',2,2,5,'1'),(3,'동물농장 (열린책들 세계문학 53)','조지 오웰','열린책들','20091130','8932909709 9788932909707','https://bookthumb-phinf.pstatic.net/cover/061/870/06187070.jpg?type=m1&udate=20200418','인간은 평등하다, 그러나 어떤 인간은 특별히 더 평등하다!&#x0D;&#x0D;당시 영국의 동맹이던 스탈린을 가차 없이 공격하는 내용 때문에 출판사로부터 출간을 거부당하여 1943년에 써놓고도 출간하지 못할 뻔 하였던 조지 오웰의 소설이다. 동물들이 존스 씨를 몰아내고 스스로 농장을 경영해 나간다는... ',1,2,4.5,'1'),(4,'Hello Coding 한입에 쏙 파이썬 (크리에이터 김왼손의 쉽고 빠른 파이썬 강의)','김왼손|김태간','한빛미디어','20180620','1162240857 9791162240854','https://bookthumb-phinf.pstatic.net/cover/136/736/13673648.jpg?type=m1&udate=20180728','프로그래밍이 두려운 모든 이를 위한 안내서&#x0D;&#x0D;프로그래밍이 처음인가요? 혹은 프.포.자(프로그래밍을 포기한 사람)인가요? 여기 100만이 이미 검증한 파이썬 입문서가 있습니다. 유튜브와 유데미를 통해 강의한 엑기스를 모아 담은 이 책을 펼치면 프포자도, 처음 프로그래밍을 배우는 사람도... ',1,0,0,'0'),(5,'명품 HTML5+CSS3+Javascript 웹 프로그래밍','황기태','생능출판','20220210','8970505458 9788970505459','https://bookthumb-phinf.pstatic.net/cover/214/880/21488092.jpg?type=m1&udate=20220208','1997년, 전기의 광명을 처음 본 사람처럼 저자는 넷스케이프를 사용하면서 정말 즐거웠다. 그때 이미 웹의 전쟁이 시작되고 있었고, 웹의 존재를 일반인에게 전파한 넷스케이프는 윈도우 운영체제에 끼워 배포한 익스플로러에 웹 땅을 내어주고 말았다. 2000년대 초 대한민국은 온통 홈페이지 만들기에... ',1,0,0,'0'),(6,'해리 포터와 비밀의 방 1 (해리포터 20주년)','J.K. 롤링','문학수첩','20191119','898392764X 9788983927644','https://bookthumb-phinf.pstatic.net/cover/157/688/15768820.jpg?type=m1&udate=20200111','해리 포터 세대의, 해리 포터 세대를 위한, 해리 포터 세대에 의한 새 번역!&#x0D;‘21세기 대표 아이콘’에 걸맞은 완성도 높은 작품으로 재탄생하다!&#x0D;&#x0D;1997년 영국에서 출간된 이래 『해리 포터』 시리즈는 지금까지 200개국 이상 80개의 언어로 번역되고 출간되어 5억 부 이상을 판매했다. 국내에서도... ',1,2,4.5,'1'),(7,'[최신판 정품새책]쿵쿵붕붕슝슝재미북스[뉴씽씽펜 포함]최다공룡수록 동화책/공룡자동차동화 ([뉴씽씽펜 포함]  전34권  [인기유아동전집판매/정품등록/빠른배송])','다수','톨스토이','20150306','6000822987 9786000822989','https://bookthumb-phinf.pstatic.net/cover/088/926/08892633.jpg?type=m1&udate=20150321','',1,0,0,'0'),(8,'CHRONOX 고등 지구과학1(하) (2022 수능대비,2021)','김재연','시대인재북스','20210823','1166760766 9791166760761','https://bookthumb-phinf.pstatic.net/cover/208/672/20867263.jpg?type=m1&udate=20220208','정말로 필요한 것만.1. 기출 문항 번호 읽는 방법(지구과학Ⅱ 영역은 별도 표기)예시) 문항 번호 14예비2014학년도 수능 지구과학Ⅰ 예비 시행 20번(교육과정 개정으로 2013년 배포)문항 번호 16091216학년도 09월 모의평가 지구과학Ⅰ 12번(15년 09월 시행)문항 번호 180610(Ⅱ)18학년도 06월 모의평가 지구과학Ⅱ... ',1,2,5,'1'),(9,'헬로우 로봇 (500여 장의 사진으로 보는 로봇의 세계)','로버트 말론','을파소','20051115','8950991209 9788950991203','https://bookthumb-phinf.pstatic.net/cover/019/344/01934430.jpg?type=m1&udate=20191009','어린이가 좋아하는 로봇에 대한 모든 것을 소개한 로봇 백과사전!『헬로우, 로봇』은 초창기 태엽으로 감는 장난감 로봇부터 두 발로 걷는 최첨단 휴머노이드 로봇까지 로봇의 변천사를 설명한다. 무엇보다 딱딱한 백과사전과 달리, 어린이가 보기 편하도록 쉽고 명료한 글과 500여 장의 올컬러 사진을 적절하게... ',1,0,0,'0'),(10,'Chrome','Blade|Cg','Lulu.com','20180525','1387590332 9781387590339','https://bookthumb-phinf.pstatic.net/cover/137/072/13707243.jpg?type=m1&udate=20210119','In this sixth chilling novel in the Pseudoverse, Detective Lori Lynn Gutierrez, and former journalist Amanda Sese, both poisoned in Onyx and &quot;recruited&quot; by the NIB, have their work cut out for them as they are pulled into the biggest gun battle the nation has ever witnessed.... ',1,2,5,'1'),(11,'하우스 인테리어 (300만 ‘하우스앱’ 유저들이 인정한 살고 싶은 집)','하우스앱','길벗','20190715','1160508429 9791160508420','https://bookthumb-phinf.pstatic.net/cover/152/025/15202561.jpg?type=m1&udate=20211207','나만의 취향과 스토리가 담긴 집 꾸미기!\n\n인테리어 커뮤니티 커머스 하우스앱 300만 유저들이 인정한 살고 싶은 집을 소개하는 『하우스 인테리어』. 단순히 큰 집, 멋진 집이 아니라 내 취향에 맞는, 우리 가족의 라이프스타일에 맞는 집, 그 공간을 만들어 낸 우리 주변 사람들의 스토리와 인테리어 팁을 풀어낸... ',1,4,2.5,'2'),(12,'Hello IT 프론트엔드 개발을 시작하려고 해 입문편 (HTML, CSS, JS 기본기부터  Git을 활용한 버전 관리와 클론 코딩까지)','박영웅','패스트캠퍼스랭귀지','20220217','1197641718 9791197641718','https://bookthumb-phinf.pstatic.net/cover/215/099/21509915.jpg?type=m1&udate=20220408','패스트캠퍼스 프론트엔드 분야 1등 강의!〈초격차 패키지 ONLINE: 한 번에 끝내는 프론트엔드 개발〉에서 입문자를 위한 내용만 골라 담았습니다많은 수강생이 선택한 온라인 강의 〈초격차 패키지 ONLINE: 한 번에 끝내는 프론트엔드 개발〉이 책으로 만들어졌습니다. 온라인 강의의 장점은 그대로 살리고, 긴... ',1,2,5,'1'),(13,'감성필사 매일, 시 한 잔 두 번째 (오늘도 시를 읽고, 쓰고, 가슴에 새기다)','윤동주','북로그컴퍼니','20210222','1190224690 9791190224697','https://bookthumb-phinf.pstatic.net/cover/180/302/18030222.jpg?type=m1&udate=20210318','음미할수록 깊은 맛이 나는 명시 70편,\n캘리그라피와 함께하는 감성 라이팅북누적 판매부수 6만을 기록 중인 북로그컴퍼니의 감성 필사 시리즈가 새 시집 《매일, 시 한 잔 _두 번째》를 선보인다.\n\n제비꽃같이 조그마한 그 계집애가\n꽃잎같이 하늘거리는 그 계집애가\n지구보다 더 큰 질량으로 나를 끌어당긴다... ',4,0,0,'0');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `review_id` int DEFAULT NULL,
  `nested_comment` int DEFAULT NULL,
  `content` longtext NOT NULL,
  `user_id` int DEFAULT NULL,
  `thumb_count` int NOT NULL,
  `register_date` datetime NOT NULL,
  PRIMARY KEY (`comment_id`),
  UNIQUE KEY `comment_id_UNIQUE` (`comment_id`),
  KEY `review_id` (`review_id`),
  KEY `user_id` (`user_id`),
  KEY `nested_comment` (`nested_comment`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`review_id`) REFERENCES `review` (`review_id`) ON DELETE CASCADE,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_index`) ON DELETE CASCADE,
  CONSTRAINT `comment_ibfk_3` FOREIGN KEY (`nested_comment`) REFERENCES `comment` (`comment_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,6,NULL,'댓글1',1,0,'2022-04-15 00:51:26'),(2,NULL,1,'댓글2',1,0,'2022-04-15 00:51:32'),(3,NULL,1,'댓글3',1,0,'2022-04-15 00:51:34'),(4,NULL,1,'댓글4',1,0,'2022-04-15 00:51:36'),(5,NULL,1,'댓글5',1,0,'2022-04-15 00:51:39'),(6,6,NULL,'댓글 3',1,0,'2022-04-15 00:51:47'),(7,6,NULL,'댓글 2',1,0,'2022-04-15 00:51:50'),(8,NULL,7,'댓글2',1,0,'2022-04-15 00:51:56'),(9,NULL,7,'댓글3',1,0,'2022-04-15 00:51:59'),(10,NULL,6,'댓글 2',1,0,'2022-04-15 00:52:08'),(11,NULL,6,'댓글3',1,0,'2022-04-15 00:52:11'),(12,12,NULL,'댓글',2,0,'2022-04-15 00:59:34'),(13,12,NULL,'댓글 너무 신기하다',2,0,'2022-04-15 00:59:38'),(14,NULL,13,'댓글 너무 좋아',2,0,'2022-04-15 00:59:44'),(15,NULL,12,'댓글 신기해',2,0,'2022-04-15 00:59:50'),(16,NULL,12,'댓글 엄청나다.',2,0,'2022-04-15 01:00:01'),(17,6,NULL,'댓글',16,0,'2022-04-15 01:10:52'),(18,10,NULL,'지구과학',16,0,'2022-04-15 01:11:03'),(19,NULL,18,'지구과학1',16,0,'2022-04-15 01:11:08'),(20,NULL,18,'지구과학2',16,0,'2022-04-15 01:11:10'),(21,NULL,18,'지구과학3',16,0,'2022-04-15 01:11:12');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend`
--

DROP TABLE IF EXISTS `friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friend` (
  `id` int NOT NULL AUTO_INCREMENT,
  `friend_index` int NOT NULL,
  `user_index` int NOT NULL,
  `friend_nick_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `friend_id_UNIQUE` (`id`),
  KEY `user_index` (`user_index`),
  CONSTRAINT `friend_ibfk_1` FOREIGN KEY (`user_index`) REFERENCES `user` (`user_index`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend`
--

LOCK TABLES `friend` WRITE;
/*!40000 ALTER TABLE `friend` DISABLE KEYS */;
INSERT INTO `friend` VALUES (1,2,1,'2'),(2,3,1,'3'),(3,4,1,'4'),(4,1,2,'1'),(5,3,2,'3'),(6,4,2,'4'),(7,1,16,'1'),(8,2,16,'2'),(9,3,16,'3'),(10,4,16,'4'),(11,5,16,'5');
/*!40000 ALTER TABLE `friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `notice_id` int unsigned NOT NULL AUTO_INCREMENT,
  `content` longtext NOT NULL,
  `register_date` datetime NOT NULL,
  `title` longtext NOT NULL,
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (1,'공지1','2022-04-15 01:07:53','공지1'),(2,'공지2\n','2022-04-15 01:07:59','공지2'),(3,'공지3','2022-04-15 01:08:09','공지3'),(4,'공지4\n','2022-04-15 01:08:17','공지4'),(5,'공지5','2022-04-15 01:08:25','공지5'),(6,'공지6','2022-04-15 01:08:31','공지6'),(7,'공지7\n','2022-04-15 01:08:37','공지7'),(8,'공지8','2022-04-15 01:08:44','공지8'),(9,'공지9','2022-04-15 01:08:49','공지9'),(10,'공지10','2022-04-15 01:08:54','공지10'),(11,'공지11','2022-04-15 01:09:00','공지11'),(12,'공지12\n','2022-04-15 01:09:08','공지12'),(13,'공지13\n','2022-04-15 01:09:21','공지13');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `user_id` int NOT NULL,
  `book_id` int NOT NULL,
  `content` longtext NOT NULL,
  `thumb_count` int NOT NULL,
  `review_id` int NOT NULL AUTO_INCREMENT,
  `score` double NOT NULL,
  `register_date` datetime NOT NULL,
  PRIMARY KEY (`review_id`),
  UNIQUE KEY `review_id_UNIQUE` (`review_id`),
  KEY `user_id` (`user_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_index`) ON DELETE CASCADE,
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,1,'별점 4.5\n',0,1,4.5,'2022-04-15 00:50:21'),(1,1,'별점 2.5',0,2,2.5,'2022-04-15 00:50:32'),(1,1,'별점5.0',0,3,5,'2022-04-15 00:50:41'),(1,1,'별점 1',0,4,0.5,'2022-04-15 00:50:50'),(1,1,'별점 4.5',0,5,4.5,'2022-04-15 00:51:00'),(1,1,'별점',2,6,5,'2022-04-15 00:51:16'),(1,2,'5점',1,7,5,'2022-04-15 00:53:01'),(1,3,'고기먹고싶다..',1,8,4.5,'2022-04-15 00:53:26'),(1,6,'해리해리\n',0,9,4.5,'2022-04-15 00:54:38'),(1,8,'김재연이니까 별점 5점',1,10,5,'2022-04-15 00:55:55'),(1,10,'무서우니까 5점',1,11,5,'2022-04-15 00:56:42'),(2,11,'헬로우',2,12,0,'2022-04-15 00:58:48'),(2,11,'별점을 5점을 줘보자.',2,13,5,'2022-04-15 00:59:21'),(16,12,'5점',1,14,5,'2022-04-15 01:11:40');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thumb`
--

DROP TABLE IF EXISTS `thumb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thumb` (
  `thumb_id` int NOT NULL AUTO_INCREMENT,
  `review_id` int DEFAULT NULL,
  `transcription_id` int DEFAULT NULL,
  `given_user_id` int NOT NULL,
  `comment_id` int DEFAULT NULL,
  PRIMARY KEY (`thumb_id`),
  UNIQUE KEY `thumb_id_UNIQUE` (`thumb_id`),
  KEY `comment_id` (`comment_id`),
  KEY `transcription_id` (`transcription_id`),
  KEY `review_id` (`review_id`),
  KEY `given_user_id` (`given_user_id`),
  CONSTRAINT `thumb_ibfk_1` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`comment_id`) ON DELETE CASCADE,
  CONSTRAINT `thumb_ibfk_2` FOREIGN KEY (`transcription_id`) REFERENCES `transcription` (`transcription_id`) ON DELETE CASCADE,
  CONSTRAINT `thumb_ibfk_3` FOREIGN KEY (`review_id`) REFERENCES `review` (`review_id`) ON DELETE CASCADE,
  CONSTRAINT `thumb_ibfk_4` FOREIGN KEY (`given_user_id`) REFERENCES `user` (`user_index`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thumb`
--

LOCK TABLES `thumb` WRITE;
/*!40000 ALTER TABLE `thumb` DISABLE KEYS */;
INSERT INTO `thumb` VALUES (1,NULL,2,1,NULL),(2,6,NULL,1,NULL),(3,NULL,17,2,NULL),(4,12,NULL,2,NULL),(5,13,NULL,2,NULL),(6,NULL,17,16,NULL),(7,NULL,5,16,NULL),(8,7,NULL,16,NULL),(9,6,NULL,16,NULL),(10,10,NULL,16,NULL),(11,NULL,12,16,NULL),(12,14,NULL,16,NULL),(13,NULL,18,16,NULL),(14,8,NULL,16,NULL),(15,NULL,8,16,NULL),(16,13,NULL,16,NULL),(17,12,NULL,16,NULL),(18,11,NULL,16,NULL),(19,NULL,11,16,NULL);
/*!40000 ALTER TABLE `thumb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transcription`
--

DROP TABLE IF EXISTS `transcription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transcription` (
  `book_id` int NOT NULL,
  `user_id` int NOT NULL,
  `transcription_id` int NOT NULL AUTO_INCREMENT,
  `content` longtext NOT NULL,
  `thumb_count` int NOT NULL,
  `register_date` datetime NOT NULL,
  PRIMARY KEY (`transcription_id`),
  UNIQUE KEY `transcription_id_UNIQUE` (`transcription_id`),
  KEY `book_id` (`book_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `transcription_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE CASCADE,
  CONSTRAINT `transcription_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_index`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transcription`
--

LOCK TABLES `transcription` WRITE;
/*!40000 ALTER TABLE `transcription` DISABLE KEYS */;
INSERT INTO `transcription` VALUES (1,1,1,'필사 1\n',0,'2022-04-15 00:48:30'),(1,1,2,'필사2\n',1,'2022-04-10 00:48:37'),(1,1,3,'필사3',0,'2022-04-11 00:48:44'),(1,1,4,'필사4',0,'2022-04-12 00:48:53'),(1,1,5,'필사 5\n',1,'2022-04-15 00:49:02'),(1,1,6,'필사 6\n',0,'2022-04-15 00:51:07'),(2,1,7,'헬로우',0,'2022-04-01 00:52:41'),(2,1,8,'다정한 이웃 스파이더맨',1,'2022-04-15 00:52:52'),(3,1,9,'동물',0,'2022-04-15 00:53:18'),(4,1,10,'파이썬',0,'2022-04-15 00:53:45'),(5,1,11,'웹 프로그래밍 입문',1,'2022-04-15 00:54:07'),(6,1,12,'해리포터\n',1,'2022-04-15 00:54:30'),(7,1,13,'쿵쿵붕붕슝슝',0,'2022-03-15 00:55:05'),(8,1,14,'김재연',0,'2022-02-15 00:55:42'),(9,1,15,'헬로우 로봇\n',0,'2022-01-15 00:56:11'),(10,1,16,'조금 무서운 사진이다',0,'2022-04-09 00:56:33'),(11,2,17,'인테리어',2,'2022-04-15 00:58:41'),(12,16,18,'프론트엔드 재밌지',1,'2022-04-15 01:11:32'),(13,16,19,'필사1\n',0,'2022-04-21 01:13:37'),(13,16,20,'필사2',0,'2022-01-15 01:13:43'),(13,16,21,'필사3',0,'2022-02-15 01:13:49'),(13,16,22,'필사4\n',0,'2022-03-15 01:13:57');
/*!40000 ALTER TABLE `transcription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_index` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `nick_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `birth` datetime NOT NULL,
  `friend_count` int NOT NULL,
  `review_count` int NOT NULL,
  `transcription_count` int NOT NULL,
  PRIMARY KEY (`user_index`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `nick_name_UNIQUE` (`nick_name`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'kpeel5839','김재연','1','sksk5839','1998-06-05 00:00:00',3,11,16),(2,'kpeel1','김재연','2','sksk5839','2003-01-06 00:00:00',3,2,1),(3,'kpeel2','김재연','3','sksk5839','1992-02-21 00:00:00',0,0,0),(4,'kpeel3','김재연','4','sksk5839','2012-06-10 00:00:00',0,0,0),(5,'kpeel4','김재연','5','sksk5839','1998-06-05 00:00:00',0,0,0),(6,'kpeel5','김재연','6','sksk5839','1998-06-05 00:00:00',0,0,0),(7,'kpeel6','김재연','7','sksk5839','1998-06-05 00:00:00',0,0,0),(8,'kpeel7','김재연','8','sksk5839','1998-06-05 00:00:00',0,0,0),(10,'kpeel8','김재연','9','sksk5839','1998-06-05 00:00:00',0,0,0),(11,'kpeel9','김재연','10','sksk5839','1998-06-05 00:00:00',0,0,0),(12,'kpeel10','김재연','11','sksk5839','1998-06-05 00:00:00',0,0,0),(13,'kpeel11','김재연','12','sksk5839','1998-06-05 00:00:00',0,0,0),(15,'kpeel12','김재연','13','sksk5839','1998-06-05 00:00:00',0,0,0),(16,'kpeel13','김재연','14','sksk5839','1998-06-05 00:00:00',5,1,5),(17,'kpeel15','김재연','15','sksk5839','1998-06-05 00:00:00',0,0,0),(18,'kpeel16','김재연','16','sksk5839','1998-06-05 00:00:00',0,0,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-15  1:16:40
