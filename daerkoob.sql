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
  `description` varchar(255) DEFAULT NULL,
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
INSERT INTO `book` VALUES (11,'김이나의 작사법 (우리의 감정을 사로잡는 일상의 언어들)','김이나','문학동네','20150318','8954635601 9788954635608','https://bookthumb-phinf.pstatic.net/cover/088/824/08882431.jpg?type=m1&udate=20210119','아이유 윤상 허지웅의 감정을 두드린 이 책!이선희 〈그중에 그대를 만나〉, 조용필 〈걷고 싶다〉, 아이유 〈좋은 날〉, 브라운아이드걸스 〈아브라카다브라〉, 성시경 〈10월에 눈이 내리면〉, 엑소 〈LUCKY〉,가인 〈APPLE〉 〈PARADISE LOST〉 등 300여 곡\n\n대한민국 작사가 저작권료 수입 1위!\n대중을... ',11,4,3.5,'4'),(12,'해리 포터와 마법사의 돌 (해리포터 20주년)','J.K. 롤링','문학수첩','20191119','8983927747 9788983927743','https://bookthumb-phinf.pstatic.net/cover/157/688/15768828.jpg?type=m1&udate=20200109','해리 포터 세대의, 해리 포터 세대를 위한, 해리 포터 세대에 의한 새 번역!&#x0D;‘21세기 대표 아이콘’에 걸맞은 완성도 높은 작품으로 재탄생하다!&#x0D;&#x0D;1997년 영국에서 출간된 이래 『해리 포터』 시리즈는 지금까지 200개국 이상 80개의 언어로 번역되고 출간되어 5억 부 이상을 판매했다. 국내에서도... ',3,0,0,''),(13,'재즈피플 Jazz People 2021.11','재즈피플 편집부','재즈피플(월간지)','20211028','1975354303 9771975354306','https://bookthumb-phinf.pstatic.net/cover/211/936/21193673.jpg?type=m1&udate=20211101','재즈 관련 전문지 『재즈피플』.',1,0,0,'');
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
  PRIMARY KEY (`comment_id`),
  UNIQUE KEY `comment_id_UNIQUE` (`comment_id`),
  KEY `review_id` (`review_id`),
  KEY `user_id` (`user_id`),
  KEY `nested_comment` (`nested_comment`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`review_id`) REFERENCES `review` (`review_id`) ON DELETE CASCADE,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_index`) ON DELETE CASCADE,
  CONSTRAINT `comment_ibfk_3` FOREIGN KEY (`nested_comment`) REFERENCES `comment` (`comment_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
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
  CONSTRAINT `friend_ibfk_1` FOREIGN KEY (`user_index`) REFERENCES `user` (`user_index`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend`
--

LOCK TABLES `friend` WRITE;
/*!40000 ALTER TABLE `friend` DISABLE KEYS */;
INSERT INTO `friend` VALUES (1,12,11,'재연사랑'),(2,13,11,'숲세권주민'),(3,14,11,'봉봉알러뷰'),(4,15,11,'ㅇㅇㅇ');
/*!40000 ALTER TABLE `friend` ENABLE KEYS */;
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
  `score` int NOT NULL,
  `register_date` datetime NOT NULL,
  PRIMARY KEY (`review_id`),
  UNIQUE KEY `review_id_UNIQUE` (`review_id`),
  KEY `user_id` (`user_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_index`) ON DELETE CASCADE,
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (11,11,'이 책 너무 싫어요',4,1,5,'2021-11-24 00:00:00'),(12,11,'이 책은 별로에요',1,2,3,'2021-11-24 00:00:00'),(13,11,'이 책을 누가 읽어요',0,3,4,'2021-11-24 00:00:00'),(14,11,'지영님 이거 읽고 또 재밌으시겠죠',0,4,2,'2021-11-24 00:00:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thumb`
--

LOCK TABLES `thumb` WRITE;
/*!40000 ALTER TABLE `thumb` DISABLE KEYS */;
INSERT INTO `thumb` VALUES (2,NULL,24,11,NULL),(3,1,NULL,11,NULL),(4,2,NULL,12,NULL),(5,1,NULL,12,NULL),(6,1,NULL,13,NULL),(7,1,NULL,14,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transcription`
--

LOCK TABLES `transcription` WRITE;
/*!40000 ALTER TABLE `transcription` DISABLE KEYS */;
INSERT INTO `transcription` VALUES (11,13,24,'나는 이거를 몇번 작업하냐\n',1,'2021-11-21 00:00:00'),(11,13,25,'나는 이거를 2번 작업한다.',0,'2021-11-21 00:00:00'),(11,13,26,'이번엔 3번째\n',0,'2021-11-21 00:00:00'),(12,14,27,'수희 바보',0,'2021-11-21 00:00:00'),(12,14,28,'수희 엉엉엉',0,'2021-11-21 00:00:00'),(12,14,29,'어엉ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ',0,'2021-11-21 00:00:00'),(11,13,30,'지윤님에게 보여주는 test\n',0,'2021-11-22 00:00:00'),(13,13,31,'jazz jazz',0,'2021-11-22 00:00:00'),(11,11,32,'나는나는나는',0,'2021-11-23 00:00:00'),(11,13,33,'앙앙앙 난 니가 정말 싫어 도라에몽이',0,'2021-11-24 00:00:00'),(11,13,34,'앙앙앙 난 니가 정말 싫어 도라에몽이',0,'2021-11-24 00:00:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (11,'kpeel5839','김재연','수희사랑','sksk5839!','2021-11-15 15:00:00',4,1,1),(12,'suheelove','황수희','재연사랑','sksk5839!','2021-11-15 15:00:00',0,1,0),(13,'hello','김김김','숲세권주민','1234','2021-11-16 15:00:00',0,1,7),(14,'suhee831','황수희','봉봉알러뷰','wodustkfkd486','1998-08-28 15:00:00',0,1,3),(15,'12345','김재연','ㅇㅇㅇ','sksk5839','2021-11-20 15:00:00',0,0,0);
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

-- Dump completed on 2021-11-24 11:34:13
