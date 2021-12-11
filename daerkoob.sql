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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'김이나의 작사법 (우리의 감정을 사로잡는 일상의 언어들)','김이나','문학동네','20150318','8954635601 9788954635608','https://bookthumb-phinf.pstatic.net/cover/088/824/08882431.jpg?type=m1&udate=20210119','아이유 윤상 허지웅의 감정을 두드린 이 책!이선희 〈그중에 그대를 만나〉, 조용필 〈걷고 싶다〉, 아이유 〈좋은 날〉, 브라운아이드걸스 〈아브라카다브라〉, 성시경 〈10월에 눈이 내리면〉, 엑소 〈LUCKY〉,가인 〈APPLE〉 〈PARADISE LOST〉 등 300여 곡\n\n대한민국 작사가 저작권료 수입 1위!\n대중을... ',3,4,4.5,'2'),(2,'동물농장','조지 오웰','열린책들','20091130','8932909709 9788932909707','https://bookthumb-phinf.pstatic.net/cover/061/870/06187070.jpg?type=m1&udate=20200418','인간은 평등하다, 그러나 어떤 인간은 특별히 더 평등하다!&#x0D;&#x0D;당시 영국의 동맹이던 스탈린을 가차 없이 공격하는 내용 때문에 출판사로부터 출간을 거부당하여 1943년에 써놓고도 출간하지 못할 뻔 하였던 조지 오웰의 소설이다. 동물들이 존스 씨를 몰아내고 스스로 농장을 경영해 나간다는... ',2,9,4.33,'3'),(3,'호꼼 골아봅서 (제주 애월읍 수산리 제주 어르신 그림책)','제주 애월읍 수산리 어르신들 (양순자|강신자|박송자|김영순|송옥자|양인옥)','책여우','20211109','1187860530 9791187860532','https://bookthumb-phinf.pstatic.net/cover/212/981/21298162.jpg?type=m1&udate=20211121','우리들의 젊은 날처럼 어르신들도 젊은 날이 있었다는 사실을 잊고 산다. 긴 시간이지만 찰나처럼 느껴지는 어르신들의 지난날. 그저 허허허, 웃으시는 목소리와 모습에서 저절로 고개가 숙여진다. &lt;호꼼 골아봅서(조금 이야기해 보세요)&gt;는 제주시 애월 수산리 6명 어르신들의 이야기를 모아 한 권에... ',1,4,3.5,'2'),(4,'가면산장 살인사건','히가시노 게이고','재인','20140926','899098257X 9788990982575','https://bookthumb-phinf.pstatic.net/cover/082/730/08273055.jpg?type=m1&udate=20210611','강도와 인질 간의 숨 막히는 줄다리기!히가시노 게이고의 소설 『가면산장 살인사건』. 저자와 독자가 아슬아슬한 두뇌 싸움을 벌이게 되는 이 작품은 외딴 산장에 모인 여덟 명의 남녀와 한밤중에 침입한 은행 강도범의 인질극을 그리고 있다. 잘 짜인 무대에서 벌어지는 연극과도 같은 이 소설은 그 누구도... ',0,0,0,'0'),(5,'베스트! 원조 괴짜가족 4 (코테츠의 여름방학편)','KENJI HAMAOKA','서울미디어코믹스(서울문화사)','20190925','1165015757 9791165015756','https://bookthumb-phinf.pstatic.net/cover/156/584/15658438.jpg?type=m1&udate=20191101','일본 제일의 활기찬 초등학생 오오사와기 코테츠가 더욱 빛을 발하는\n여름방학 에피소드 15편!!\n괴짜가족 시리즈에서 특별히 선별한 폭소 특선 제4탄~!\n\n첫 번째 방학★수영 타임·\n두 번째 방학★끝나가는 여름방학\n세 번째 방학★코테츠의 여름방학 전야제\n네 번째 방학★혼자 노는 아이\n다섯 번째 방학... ',0,1,5,'1'),(6,'Chrome','Blade|Cg','Lulu.com','20180525','1387590332 9781387590339','https://bookthumb-phinf.pstatic.net/cover/137/072/13707243.jpg?type=m1&udate=20210119','In this sixth chilling novel in the Pseudoverse, Detective Lori Lynn Gutierrez, and former journalist Amanda Sese, both poisoned in Onyx and &quot;recruited&quot; by the NIB, have their work cut out for them as they are pulled into the biggest gun battle the nation has ever witnessed.... ',1,2,3,'1'),(7,'고등 지구과학2(2021)(CHRONOX Astra)','김재연','시대인재북스','20200225','1190260573 9791190260572','https://bookthumb-phinf.pstatic.net/cover/162/745/16274578.jpg?type=m1&udate=20210831','실질적으로 필요한 것만을, 지구과학Ⅱ 선택자에게..\n\n이 책은 지구과학2의 \'우리은하와 우주의 구조\'를 제외한 \'천문학 파트만을 다루고 있습니다. 목차와 내용을 확인하고 구매하세요.\'\n\n좌표계와 행성의 시운동은 예전부터 많은 학생들을 괴롭혀왔습니다.\n겉으로 보기엔 단원 하나지만 실제로 수험생이... ',2,0,0,'0'),(8,'동물농장 (최신 버전으로 새롭게 편집한 명작의 백미, 책 읽어드립니다)','조지 오웰','스타북스','20200408','1157955207 9791157955206','https://bookthumb-phinf.pstatic.net/cover/163/240/16324003.jpg?type=m1&udate=20201106','타임지와 뉴스위크, BBC가 주목한 가장 중요한 작가!\n모든 동물은 평등하다? 그러나, 어떤 동물은 더더욱 평등하다!!\n인간이라면 꼭 한 번은 읽어야 할 자유를 위한 예리한 풍자소설\n《동물농장》은 오웰의 작품 중 유일하게 유머가 가득한 작품으로 봐도 좋은데, 간결한 문체와 예리한 풍자가 돋보이는 소설로 사회... ',1,0,0,'0');
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
  CONSTRAINT `friend_ibfk_1` FOREIGN KEY (`user_index`) REFERENCES `user` (`user_index`) ON DELETE CASCADE,
  CONSTRAINT `friend_ibfk_2` FOREIGN KEY (`user_index`) REFERENCES `user` (`user_index`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend`
--

LOCK TABLES `friend` WRITE;
/*!40000 ALTER TABLE `friend` DISABLE KEYS */;
INSERT INTO `friend` VALUES (1,13,11,'숲세권주민');
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
  `score` double NOT NULL,
  `register_date` datetime NOT NULL,
  PRIMARY KEY (`review_id`),
  UNIQUE KEY `review_id_UNIQUE` (`review_id`),
  KEY `user_id` (`user_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_index`) ON DELETE CASCADE,
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (13,1,'이것은!',1,1,5,'2021-12-02 00:00:00'),(13,2,'동물 동물 베베',0,2,5,'2021-12-02 00:00:00'),(13,2,'동물 동물 베베 연타',0,3,4,'2021-12-02 00:00:00'),(13,2,'동물 동물 베베 연타',0,4,4,'2021-12-02 00:00:00'),(13,2,'동물 동물 베베 연타',0,5,4,'2021-12-02 00:00:00'),(13,5,'저기 좀 비켜',0,6,5,'2021-12-02 00:00:00'),(13,1,'헬로헬로',0,7,4,'2021-12-03 00:00:00'),(11,3,'리ㅂ리뷰',0,8,4,'2021-12-10 00:00:00'),(17,3,'호꼼 골아봅서',0,9,3,'2021-12-10 22:56:13'),(17,6,'약간 무서워 보이네요',0,10,3,'2021-12-10 22:56:25');
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thumb`
--

LOCK TABLES `thumb` WRITE;
/*!40000 ALTER TABLE `thumb` DISABLE KEYS */;
INSERT INTO `thumb` VALUES (4,1,NULL,13,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transcription`
--

LOCK TABLES `transcription` WRITE;
/*!40000 ALTER TABLE `transcription` DISABLE KEYS */;
INSERT INTO `transcription` VALUES (1,13,1,'김이나의 작사법을 들어왔는데 살짝 오류가 있네요',0,'2021-12-02 00:00:00'),(1,13,2,'hello',0,'2021-12-02 00:00:00'),(2,13,3,'동물 동물 베베',0,'2021-12-02 00:00:00'),(3,13,4,'그냥 정상적으로 한번만 눌렀을 때',0,'2021-12-02 00:00:00'),(3,13,5,'이거는 한번 더 작성',0,'2021-12-02 00:00:00'),(4,13,6,'가면산장의 살인사건',6,'2021-12-02 00:00:00'),(1,11,7,'김이나',0,'2021-12-10 00:00:00'),(2,11,8,'ang ang ang ...',0,'2021-12-10 00:00:00'),(6,11,9,'앙앙앙 기모찌\n',0,'2021-12-10 00:00:00'),(7,17,10,'고등 지구 과학?',0,'2021-12-10 00:00:00'),(7,17,11,'어쩔어쩔',0,'2021-12-10 22:53:11'),(1,17,12,'김이나이나 지영님이 좋아하시는 이나이나\n',0,'2021-12-10 22:55:33'),(8,17,13,'꿀꿀',0,'2021-12-10 22:55:58');
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (11,'kpeel5839','김재연','수희사랑','sksk5839!','2021-11-15 15:00:00',1,1,3),(12,'suheelove','황수희','재연사랑','sksk5839!','2021-11-15 15:00:00',0,0,0),(13,'hello','김김김','숲세권주민','1234','2021-11-16 15:00:00',0,6,6),(14,'suhee831','황수희','봉봉알러뷰','wodustkfkd486','1998-08-28 15:00:00',0,0,0),(15,'12345','김재연','ㅇㅇㅇ','sksk5839','2021-11-20 15:00:00',0,0,0),(16,'jiyeong','박지영','졍','wldud','2021-12-09 15:00:00',0,0,0),(17,'1234','김재연','수히히히','1234','1998-06-05 00:00:00',0,2,4);
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

-- Dump completed on 2021-12-10 23:02:18
