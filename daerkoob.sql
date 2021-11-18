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
  PRIMARY KEY (`book_id`),
  UNIQUE KEY `book_id_UNIQUE` (`book_id`),
  UNIQUE KEY `ibsn_UNIQUE` (`isbn`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'1','1','1','1','1','1','1'),(2,'2','2','2','2','2','2','2'),(3,'슬픔의 해석','리사 슐먼','일므디','20210711','1197031758 9791197031755','naver.com','누구누구'),(4,'<b>해리</b> 포터와 마법사의 돌 (<b>해리</b>포터 20주년)','J.K. 롤링','문학수첩','20191119','8983927747 9788983927743','https://bookthumb-phinf.pstatic.net/cover/157/688/15768828.jpg?type=m1&udate=20200109','<b>해리</b> 포터 세대의, <b>해리</b> 포터 세대를 위한, <b>해리</b> 포터 세대에 의한 새 번역!&#x0D;‘21세기 대표 아이콘’에 걸맞은 완성도 높은 작품으로 재탄생하다!&#x0D;&#x0D;1997년 영국에서 출간된 이래 『<b>해리</b> 포터』 시리즈는 지금까지 200개국 이상 80개의 언어로 번역되고 출간되어 5억 부 이상을 판매했다. 국내에서도... ');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
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
  `nick_name` varchar(255) NOT NULL,
  `user_index` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `friend_id_UNIQUE` (`id`),
  KEY `user_index` (`user_index`),
  CONSTRAINT `friend_ibfk_1` FOREIGN KEY (`user_index`) REFERENCES `user` (`user_index`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend`
--

LOCK TABLES `friend` WRITE;
/*!40000 ALTER TABLE `friend` DISABLE KEYS */;
INSERT INTO `friend` VALUES (1,12,'재연사랑',11),(2,14,'봉봉알러뷰',11);
/*!40000 ALTER TABLE `friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (3);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
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
  `review` varchar(255) NOT NULL,
  `thumb` int NOT NULL,
  `star` double NOT NULL,
  `review_id` int NOT NULL AUTO_INCREMENT,
  `star_count` int NOT NULL,
  `book_title` varchar(255) NOT NULL,
  `user_nick_name` varchar(255) NOT NULL,
  `register_date` datetime NOT NULL,
  PRIMARY KEY (`review_id`),
  UNIQUE KEY `review_id_UNIQUE` (`review_id`),
  KEY `book_id` (`book_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`),
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `star`
--

DROP TABLE IF EXISTS `star`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `star` (
  `book_id` int NOT NULL,
  `user_id` int NOT NULL,
  `review_id` int DEFAULT NULL,
  `transcription_id` int DEFAULT NULL,
  `star_id` int NOT NULL AUTO_INCREMENT,
  `score` int NOT NULL,
  `given_user_id` int NOT NULL,
  PRIMARY KEY (`star_id`),
  UNIQUE KEY `star_id_UNIQUE` (`star_id`),
  KEY `transcription_id` (`transcription_id`),
  KEY `review_id` (`review_id`),
  CONSTRAINT `star_ibfk_1` FOREIGN KEY (`transcription_id`) REFERENCES `transcription` (`transcription_id`),
  CONSTRAINT `star_ibfk_2` FOREIGN KEY (`review_id`) REFERENCES `review` (`review_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `star`
--

LOCK TABLES `star` WRITE;
/*!40000 ALTER TABLE `star` DISABLE KEYS */;
/*!40000 ALTER TABLE `star` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thumb`
--

DROP TABLE IF EXISTS `thumb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thumb` (
  `user_id` int NOT NULL,
  `book_id` int NOT NULL,
  `thumb_id` int NOT NULL AUTO_INCREMENT,
  `review_id` int DEFAULT NULL,
  `transcription_id` int DEFAULT NULL,
  `given_user_id` int NOT NULL,
  PRIMARY KEY (`thumb_id`),
  UNIQUE KEY `thumb_id_UNIQUE` (`thumb_id`),
  KEY `review_id` (`review_id`),
  KEY `transcription_id` (`transcription_id`),
  CONSTRAINT `thumb_ibfk_1` FOREIGN KEY (`review_id`) REFERENCES `review` (`review_id`),
  CONSTRAINT `thumb_ibfk_2` FOREIGN KEY (`transcription_id`) REFERENCES `transcription` (`transcription_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thumb`
--

LOCK TABLES `thumb` WRITE;
/*!40000 ALTER TABLE `thumb` DISABLE KEYS */;
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
  `transcription` varchar(255) NOT NULL,
  `thumb` int NOT NULL,
  `star` double NOT NULL,
  `star_count` int NOT NULL,
  `user_nick_name` varchar(255) NOT NULL,
  `book_title` varchar(255) NOT NULL,
  `register_date` datetime NOT NULL,
  PRIMARY KEY (`transcription_id`),
  UNIQUE KEY `transcription_id_UNIQUE` (`transcription_id`),
  KEY `user_id` (`user_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `transcription_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_index`),
  CONSTRAINT `transcription_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transcription`
--

LOCK TABLES `transcription` WRITE;
/*!40000 ALTER TABLE `transcription` DISABLE KEYS */;
INSERT INTO `transcription` VALUES (2,11,11,'2',0,0,0,'수희사랑','2','2021-11-18 00:00:00');
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
  `friend` int NOT NULL,
  `review_count` int NOT NULL,
  `transcription_count` int NOT NULL,
  PRIMARY KEY (`user_index`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `nick_name_UNIQUE` (`nick_name`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (11,'kpeel5839','김재연','수희사랑','sksk5839!','2021-11-15 15:00:00',2,0,1),(12,'suheelove','황수희','재연사랑','sksk5839!','2021-11-15 15:00:00',0,0,0),(13,'hello','김김김','숲세권주민','1234','2021-11-16 15:00:00',0,0,0),(14,'suhee831','황수희','봉봉알러뷰','wodustkfkd486','1998-08-28 15:00:00',0,0,0);
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

-- Dump completed on 2021-11-18 12:50:32
