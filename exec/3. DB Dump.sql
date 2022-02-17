CREATE DATABASE  IF NOT EXISTS `HOME_SNS` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `HOME_SNS`;
-- MySQL dump 10.13  Distrib 8.0.26, for macos11 (x86_64)
--
-- Host: localhost    Database: HOME_SNS
-- ------------------------------------------------------
-- Server version	8.0.28-0ubuntu0.20.04.3

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
-- Table structure for table `CODE`
--

DROP TABLE IF EXISTS `CODE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CODE` (
  `CODE` char(2) NOT NULL,
  `GROUP_CODE` char(3) DEFAULT NULL,
  `CODE_NAME` varchar(20) DEFAULT NULL,
  `USE_YN` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`CODE`),
  UNIQUE KEY `CODE_NAME` (`CODE_NAME`),
  KEY `FK_GROUP_CODE_TO_CODE_1` (`GROUP_CODE`),
  CONSTRAINT `FK_GROUP_CODE_TO_CODE_1` FOREIGN KEY (`GROUP_CODE`) REFERENCES `GROUP_CODE` (`GROUP_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CODE`
--

LOCK TABLES `CODE` WRITE;
/*!40000 ALTER TABLE `CODE` DISABLE KEYS */;
/*!40000 ALTER TABLE `CODE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `COMMENT`
--

DROP TABLE IF EXISTS `COMMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `COMMENT` (
  `COMMENT_ID` int NOT NULL AUTO_INCREMENT,
  `FEED_ID` int NOT NULL,
  `COMMENT_AUTHOR` int DEFAULT NULL,
  `COMMENT_CONTENT` varchar(300) DEFAULT NULL,
  `COMMENT_UPLOAD_DATE` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`COMMENT_ID`),
  KEY `FK_FEED_TO_COMMENT_1` (`FEED_ID`),
  CONSTRAINT `FK_FEED_TO_COMMENT_1` FOREIGN KEY (`FEED_ID`) REFERENCES `FEED` (`FEED_ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `COMMENT`
--

LOCK TABLES `COMMENT` WRITE;
/*!40000 ALTER TABLE `COMMENT` DISABLE KEYS */;
INSERT INTO `COMMENT` VALUES (1,1,1,'원해요?','2022-02-15 16:13:40'),(2,1,1,'나는 원해요!','2022-02-15 16:13:44'),(6,3,1,'댓글을','2022-02-15 17:04:11'),(7,3,1,'무한정','2022-02-15 17:04:13'),(8,3,1,'달아봐야','2022-02-15 17:04:15'),(9,3,1,'되겠어','2022-02-15 17:04:16'),(10,3,1,'카톡이요?','2022-02-15 17:04:25'),(11,3,1,'이거','2022-02-15 17:04:32'),(12,3,1,'이상','2022-02-15 17:04:33'),(13,3,1,'해','2022-02-15 17:04:33'),(14,3,3,'댓글달아봅시다1111','2022-02-15 17:05:13'),(15,3,1,'깔려요오오오~','2022-02-15 17:05:20'),(17,3,1,'키야아아아아아아아~~','2022-02-15 17:05:32'),(18,3,3,'댓글달고','2022-02-15 17:05:50'),(22,3,1,'그렇다고 합니다.','2022-02-15 17:06:04'),(23,3,1,'어쩔 수 없다구요??','2022-02-15 17:06:32'),(24,3,1,'어쩌죵..?','2022-02-15 17:07:04'),(25,3,1,'그냥 달면','2022-02-15 17:07:27'),(26,3,3,'?','2022-02-15 17:07:36'),(27,3,1,'뉑?','2022-02-15 17:07:38'),(28,3,3,'@Eㅐ현 태그태그','2022-02-15 17:07:57'),(29,3,1,'@Eㅐ현 태그','2022-02-15 17:08:07'),(30,3,1,'@testName @임창현 @Eㅐ현 @고오오자아앙 ','2022-02-15 17:09:04'),(35,1,4,'댓글이','2022-02-15 17:10:35'),(49,8,1,'대에엣글?','2022-02-16 08:29:20'),(50,8,1,'대에에엣그을?','2022-02-16 08:29:30'),(56,9,5,'우와앙','2022-02-16 13:07:08'),(58,9,5,'@Eㅐ현 이여여여여열','2022-02-16 13:51:56'),(59,9,1,'축하해','2022-02-16 13:56:48'),(60,8,2,'댓글댓글~','2022-02-16 14:51:18'),(62,9,4,'@곤듀 곤듀 태그할거야 알람가?','2022-02-16 16:00:07'),(63,9,4,'@testName 나 이삭이한테 알람날린다','2022-02-16 16:00:33'),(64,9,4,'@testName 태그할거야','2022-02-16 16:11:11'),(65,9,5,'나도 알람보내         줘','2022-02-16 16:11:23'),(66,9,4,'@testName 시부레 뭐래ㅏ고?','2022-02-16 16:11:31'),(67,9,5,'@Eㅐ현 알람왕 김태현','2022-02-16 16:11:34'),(68,9,4,'@testName 사실이 아닐거야','2022-02-16 16:11:44'),(69,8,4,'@testName 다들 나를 보러와 @왕자 ','2022-02-16 16:19:01'),(70,3,1,'댓글을','2022-02-16 16:25:25'),(71,3,1,'오지게','2022-02-16 16:25:26'),(72,3,1,'더 더','2022-02-16 16:25:27'),(73,3,1,'달아 봐야','2022-02-16 16:25:30'),(74,3,1,'겠다','2022-02-16 16:25:31'),(75,3,1,'그러면','2022-02-16 16:25:33'),(76,3,1,'리미트가','2022-02-16 16:25:34'),(77,3,1,'작동을','2022-02-16 16:25:35'),(78,3,1,'하겠지?','2022-02-16 16:25:37'),(79,3,1,'그렇겠지?/??','2022-02-16 16:25:40'),(80,3,1,'ㅎㅎ','2022-02-16 16:25:40'),(81,3,1,'따로 따로 넣었다~~','2022-02-16 16:25:47'),(82,3,1,'검사~~~','2022-02-16 16:25:49'),(83,3,1,'한다아아아아아~~~','2022-02-16 16:25:52'),(84,3,1,'해!','2022-02-16 16:25:56'),(85,3,1,'해봐~~!!!1','2022-02-16 16:25:59'),(86,3,1,'응?','2022-02-16 16:26:00'),(87,3,1,'자신있숴?','2022-02-16 16:26:03'),(88,3,1,'겟','2022-02-16 16:26:06'),(89,3,1,'요청할때','2022-02-16 16:26:08'),(90,3,1,'보내야','2022-02-16 16:26:11'),(91,3,1,'하자나','2022-02-16 16:26:12'),(92,3,1,'!!!','2022-02-16 16:26:13'),(93,3,1,'태혀어어언','2022-02-16 16:26:15'),(94,3,1,'지금 어디 보고 있어???','2022-02-16 16:26:19'),(95,3,1,'여기요오오','2022-02-16 16:26:21'),(96,3,1,'그러면 디테일로 가야 할꺼야','2022-02-16 16:26:35'),(97,1,1,'여기\'','2022-02-16 16:28:04'),(98,1,1,'댓글을','2022-02-16 16:28:05'),(99,1,1,'미;ㅊ;ㄴ','2022-02-16 16:28:07'),(100,1,1,'듯이','2022-02-16 16:28:08'),(101,1,1,'달아','2022-02-16 16:28:09'),(102,1,1,'보면','2022-02-16 16:28:10'),(103,1,1,'알겠지~~~','2022-02-16 16:28:11'),(104,1,1,'더','2022-02-16 16:29:04'),(105,1,1,'더','2022-02-16 16:29:04'),(106,1,1,'더','2022-02-16 16:29:05'),(107,1,1,'더어어어어','2022-02-16 16:29:06'),(108,1,1,'달아보자아아아','2022-02-16 16:29:09'),(109,13,4,'@곤듀 혼자먹니?...?나는..?','2022-02-17 06:21:09'),(110,14,2,'마싯겟당','2022-02-17 06:28:27'),(111,14,4,'@곤듀 놀러왕','2022-02-17 08:33:29'),(112,14,2,'@Eㅐ현 가즈아ㅏㅏ','2022-02-17 08:34:23'),(113,14,2,'@Eㅐ현 나도줘~','2022-02-17 08:37:10'),(114,14,4,'@곤듀 시롱','2022-02-17 08:38:33'),(115,13,4,'@곤듀 대답좀해줘..','2022-02-17 08:58:39'),(120,14,4,'몰라알람안가','2022-02-17 09:00:56'),(121,13,2,'@Eㅐ현 마시쪙','2022-02-17 09:01:06'),(122,14,2,'정말 안가나...?','2022-02-17 09:01:24'),(123,14,4,'@곤듀 는 들어라','2022-02-17 12:31:09'),(124,13,4,'@곤듀 줄거야 ..','2022-02-17 12:31:41'),(125,16,4,'이야아 친구 멋있어어','2022-02-17 16:34:30'),(126,20,1,'꼬물이 어디갔어..... 큰물인뒈에','2022-02-17 16:36:41'),(127,19,1,'2022년 파이팅!!','2022-02-17 16:41:53'),(128,13,1,'버섯 너무 맛갈나게 담겨있네..','2022-02-17 16:42:14'),(129,17,4,'@임창현 여기오디양','2022-02-17 17:12:27'),(130,19,4,'이거 사진 도용인데','2022-02-17 17:14:30'),(131,21,4,'진짜 너무 귀엽다ㅠㅠ 보리ㅠㅠ','2022-02-17 17:16:06'),(132,21,4,'세번째사진 심쿵..진짜..','2022-02-17 17:16:23'),(133,17,3,'화이트 비치얌','2022-02-17 17:16:53');
/*!40000 ALTER TABLE `COMMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `COMMENT_EMOTION`
--

DROP TABLE IF EXISTS `COMMENT_EMOTION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `COMMENT_EMOTION` (
  `COMMENT_ID` int NOT NULL,
  `GOOD` int DEFAULT NULL,
  `SAD` int DEFAULT NULL,
  `CHECK` int DEFAULT NULL,
  `FUN` int DEFAULT NULL,
  `AMAZE` int DEFAULT NULL,
  KEY `FK_COMMENT_TO_COMMENT_EMOTION_1` (`COMMENT_ID`),
  CONSTRAINT `FK_COMMENT_TO_COMMENT_EMOTION_1` FOREIGN KEY (`COMMENT_ID`) REFERENCES `COMMENT` (`COMMENT_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `COMMENT_EMOTION`
--

LOCK TABLES `COMMENT_EMOTION` WRITE;
/*!40000 ALTER TABLE `COMMENT_EMOTION` DISABLE KEYS */;
/*!40000 ALTER TABLE `COMMENT_EMOTION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `COMMENT_EMOTION_USER_USE`
--

DROP TABLE IF EXISTS `COMMENT_EMOTION_USER_USE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `COMMENT_EMOTION_USER_USE` (
  `COMMENT_ID` int NOT NULL,
  `USER_SEQ` int NOT NULL,
  `EMOTION_CODE` char(5) DEFAULT NULL,
  KEY `FK_COMMENT_TO_COMMENT_EMOTION_USER_USE_1` (`COMMENT_ID`),
  KEY `FK_USER_TO_COMMENT_EMOTION_USER_USE_1` (`USER_SEQ`),
  CONSTRAINT `FK_COMMENT_TO_COMMENT_EMOTION_USER_USE_1` FOREIGN KEY (`COMMENT_ID`) REFERENCES `COMMENT` (`COMMENT_ID`) ON DELETE CASCADE,
  CONSTRAINT `FK_USER_TO_COMMENT_EMOTION_USER_USE_1` FOREIGN KEY (`USER_SEQ`) REFERENCES `USER` (`USER_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `COMMENT_EMOTION_USER_USE`
--

LOCK TABLES `COMMENT_EMOTION_USER_USE` WRITE;
/*!40000 ALTER TABLE `COMMENT_EMOTION_USER_USE` DISABLE KEYS */;
/*!40000 ALTER TABLE `COMMENT_EMOTION_USER_USE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `COMMENT_TAG`
--

DROP TABLE IF EXISTS `COMMENT_TAG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `COMMENT_TAG` (
  `COMMENT_ID` int NOT NULL,
  `COMMENT_TAG` int NOT NULL,
  PRIMARY KEY (`COMMENT_ID`,`COMMENT_TAG`),
  CONSTRAINT `FK_COMMENT_TO_COMMENT_TAG_1` FOREIGN KEY (`COMMENT_ID`) REFERENCES `COMMENT` (`COMMENT_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `COMMENT_TAG`
--

LOCK TABLES `COMMENT_TAG` WRITE;
/*!40000 ALTER TABLE `COMMENT_TAG` DISABLE KEYS */;
INSERT INTO `COMMENT_TAG` VALUES (28,4),(29,4),(30,1),(30,3),(30,4),(58,4),(62,2),(63,1),(64,1),(66,1),(67,4),(68,1),(69,1),(69,5),(109,2),(111,2),(112,4),(113,4),(114,2),(115,2),(121,4),(123,2),(124,2),(129,3);
/*!40000 ALTER TABLE `COMMENT_TAG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EVENT_MEMBER`
--

DROP TABLE IF EXISTS `EVENT_MEMBER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `EVENT_MEMBER` (
  `FEED_ID` int NOT NULL,
  `USER_SEQ` int NOT NULL,
  KEY `FK_FEED_TO_EVENT_MEMBER_1` (`FEED_ID`),
  KEY `FK_USER_TO_EVENT_MEMBER_1` (`USER_SEQ`),
  CONSTRAINT `FK_FEED_TO_EVENT_MEMBER_1` FOREIGN KEY (`FEED_ID`) REFERENCES `FEED` (`FEED_ID`) ON DELETE CASCADE,
  CONSTRAINT `FK_USER_TO_EVENT_MEMBER_1` FOREIGN KEY (`USER_SEQ`) REFERENCES `USER` (`USER_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EVENT_MEMBER`
--

LOCK TABLES `EVENT_MEMBER` WRITE;
/*!40000 ALTER TABLE `EVENT_MEMBER` DISABLE KEYS */;
INSERT INTO `EVENT_MEMBER` VALUES (5,1),(5,2),(5,3),(5,4),(5,5),(8,1),(8,2),(8,3),(8,5),(9,4),(1,1),(1,2),(1,5),(1,3),(1,4),(7,2),(7,1),(7,3),(7,4),(7,5),(11,1),(11,2),(11,3),(11,5),(11,4),(3,2),(3,3),(3,1),(13,2),(14,4),(12,2),(12,1),(15,1),(15,2),(16,1),(18,1),(17,31),(17,4),(17,2),(17,1),(17,3),(19,4),(19,2),(19,31),(20,31),(20,4),(20,2),(20,1),(20,3),(21,1),(22,3);
/*!40000 ALTER TABLE `EVENT_MEMBER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FEED`
--

DROP TABLE IF EXISTS `FEED`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FEED` (
  `FEED_ID` int NOT NULL AUTO_INCREMENT,
  `GROUP_ID` int NOT NULL,
  `FEED_AUTHOR` int DEFAULT NULL,
  `FEED_TITLE` varchar(50) DEFAULT NULL,
  `FEED_CONTENT` varchar(1000) DEFAULT NULL,
  `FEED_UPLOAD_DATE` timestamp NULL DEFAULT NULL,
  `FEED_EVENT_DATE` datetime DEFAULT NULL,
  `FEED_LOCATION_ID` int DEFAULT NULL,
  `FEED_TIMELINE` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`FEED_ID`),
  KEY `FK_GROUP_TO_FEED_1` (`GROUP_ID`),
  KEY `FK_LOCATION_TO_FEED_1` (`FEED_LOCATION_ID`),
  CONSTRAINT `FK_GROUP_TO_FEED_1` FOREIGN KEY (`GROUP_ID`) REFERENCES `GROUP_LIST` (`GROUP_ID`),
  CONSTRAINT `FK_LOCATION_TO_FEED_1` FOREIGN KEY (`FEED_LOCATION_ID`) REFERENCES `LOCATION` (`LOCATION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FEED`
--

LOCK TABLES `FEED` WRITE;
/*!40000 ALTER TABLE `FEED` DISABLE KEYS */;
INSERT INTO `FEED` VALUES (1,1,1,'새복많!!!','새해복 많이 받아랏!','2022-02-15 16:11:53','2022-02-01 00:00:00',1,0),(3,1,3,'2222수정수정','2222222수정수정','2022-02-15 17:03:50','2022-02-02 00:00:00',1,0),(5,1,1,'우리집 고양이 ','고냥이고냥이','2022-02-15 17:31:09','2022-02-02 00:00:00',1,1),(7,1,1,'이런것도 있다구!','이런것도 있다구우~!~!~\r\n신기하지 다들~?','2022-02-15 18:47:10','2022-02-01 00:00:00',1,0),(8,1,1,'나는 숲의아이야','www','2022-02-16 05:59:02','2022-02-08 00:00:00',1,0),(9,1,4,'우리 아들 군대간다!','잘 다녀오렴','2022-02-16 12:26:57','2022-02-16 00:00:00',2,0),(11,1,1,'냐오옹이다옹','냥냥 ','2022-02-16 16:17:05','2022-02-16 00:00:00',1,0),(12,1,1,'1월의 마지막날','은 코딩해야즤~','2022-02-16 17:41:41','2022-01-30 00:00:00',2,1),(13,8,2,'마싯겟지~~~','아주 배부른 식사였읍니다.','2022-02-17 06:14:48','2022-02-13 00:00:00',3,1),(14,8,4,'혈육들아 들어라','혈육들아 잘 지내니\r\n난 너무 잘먹고 잘사는중이야\r\n지금 여기서 살면서 이사도 한번했어 내방도 구경해죠','2022-02-17 06:19:33','2022-02-17 00:00:00',4,0),(15,1,1,'test','asfd','2022-02-17 13:13:13','2022-01-30 00:00:00',5,0),(16,8,1,'친구 공연다녀옴~','공연 넘나 재밌는것','2022-02-17 16:14:50','2022-02-12 00:00:00',6,0),(17,8,3,'바닷가 놀러가자~~','바닷가 놀러가고 싶어~~','2022-02-17 16:19:22','2022-02-18 00:00:00',3,0),(18,8,1,'여기 같이 가자~~','친구랑 갔는데 맛있더라 다음에 같이가자~~~','2022-02-17 16:20:24','2021-11-03 00:00:00',7,1),(19,8,2,'새해복 많이받으세영','새해를 맞아 바다를 가보았읍니다.','2022-02-17 16:30:10','2022-01-04 00:00:00',8,0),(20,8,4,'꼬앵이 확대범 나야나','우리집 꼬앵이 귀엽징?\n둘째 꼬앵이야 첫째 아가는 .. 사랑하지만 원래 아픈 손가락 유노..?\n태어난지 3일된애 냥 줍해서 키웠졍 이름은 꼬물이야 이젠 꼬물이가 아니게되었지만 ,,','2022-02-17 16:32:50','2021-11-25 00:00:00',9,1),(21,8,1,'냥냥 냐아아아아옹','냐옹냐옹 냐아아아아앙 냐오오오옹옹 ','2022-02-17 16:49:31','2022-02-21 00:00:00',10,1),(22,1,3,'임창현','개발 속도가 붙는다는 느낌이 드니까 어느새 프로젝트가 끝날 때가 다되어서 많이 아쉽습니다.','2022-02-17 18:27:05','2022-02-18 00:00:00',11,0);
/*!40000 ALTER TABLE `FEED` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FEED_EMOTION`
--

DROP TABLE IF EXISTS `FEED_EMOTION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FEED_EMOTION` (
  `FEED_ID` int NOT NULL,
  `GOOD` int DEFAULT NULL,
  `SAD` int DEFAULT NULL,
  `CHECK` int DEFAULT NULL,
  `FUN` int DEFAULT NULL,
  `AMAZE` int DEFAULT NULL,
  KEY `FK_FEED_TO_FEED_EMOTION_1` (`FEED_ID`),
  CONSTRAINT `FK_FEED_TO_FEED_EMOTION_1` FOREIGN KEY (`FEED_ID`) REFERENCES `FEED` (`FEED_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FEED_EMOTION`
--

LOCK TABLES `FEED_EMOTION` WRITE;
/*!40000 ALTER TABLE `FEED_EMOTION` DISABLE KEYS */;
INSERT INTO `FEED_EMOTION` VALUES (1,2,0,1,0,0),(3,1,1,1,0,0),(5,1,0,0,0,0),(7,0,3,0,0,1),(8,1,0,1,0,1),(9,2,2,2,2,4),(11,0,0,0,0,0),(12,0,1,1,0,0),(13,0,1,0,0,1),(14,2,3,1,0,1),(15,0,0,0,0,0),(16,0,0,0,0,1),(17,1,0,0,0,2),(18,1,0,0,0,0),(19,2,0,0,1,0),(20,1,0,0,1,0),(21,1,0,0,0,0),(22,0,0,0,0,0);
/*!40000 ALTER TABLE `FEED_EMOTION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FEED_EMOTION_USER_USE`
--

DROP TABLE IF EXISTS `FEED_EMOTION_USER_USE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FEED_EMOTION_USER_USE` (
  `USER_SEQ` int NOT NULL,
  `FEED_ID` int NOT NULL,
  `EMOTION_CODE` char(5) DEFAULT 'null',
  KEY `FK_USER_TO_FEED_EMOTION_USER_USE_1` (`USER_SEQ`),
  KEY `FK_FEED_TO_FEED_EMOTION_USER_USE_1` (`FEED_ID`),
  CONSTRAINT `FK_FEED_TO_FEED_EMOTION_USER_USE_1` FOREIGN KEY (`FEED_ID`) REFERENCES `FEED` (`FEED_ID`) ON DELETE CASCADE,
  CONSTRAINT `FK_USER_TO_FEED_EMOTION_USER_USE_1` FOREIGN KEY (`USER_SEQ`) REFERENCES `USER` (`USER_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FEED_EMOTION_USER_USE`
--

LOCK TABLES `FEED_EMOTION_USER_USE` WRITE;
/*!40000 ALTER TABLE `FEED_EMOTION_USER_USE` DISABLE KEYS */;
INSERT INTO `FEED_EMOTION_USER_USE` VALUES (1,5,'30001'),(1,8,'30005'),(1,1,'30003'),(4,8,'30001'),(1,3,'30003'),(4,9,'30002'),(5,9,'30005'),(2,8,'30003'),(1,9,'30001'),(4,14,'30001'),(2,14,'30005'),(31,14,'30002'),(4,13,'30005'),(1,14,'30003'),(2,18,'30001'),(4,16,'30005'),(1,20,'30001'),(3,17,'30005'),(3,20,'30004'),(1,17,'30005'),(1,19,'30001'),(1,13,'30002'),(4,17,'30001'),(4,19,'30004'),(1,21,'30001');
/*!40000 ALTER TABLE `FEED_EMOTION_USER_USE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FILE`
--

DROP TABLE IF EXISTS `FILE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FILE` (
  `FILE_ID` int NOT NULL AUTO_INCREMENT,
  `FEED_ID` int NOT NULL,
  `FILE_NAME` varchar(100) DEFAULT NULL,
  `FILE_SIZE` float DEFAULT NULL,
  `FILE_CONTENT_TYPE` varchar(50) DEFAULT NULL,
  `FILE_URL` varchar(300) DEFAULT NULL,
  `REG_DT` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`FILE_ID`),
  UNIQUE KEY `FILE_URL` (`FILE_URL`),
  KEY `FK_FEED_TO_FILE_1` (`FEED_ID`),
  CONSTRAINT `FK_FEED_TO_FILE_1` FOREIGN KEY (`FEED_ID`) REFERENCES `FEED` (`FEED_ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FILE`
--

LOCK TABLES `FILE` WRITE;
/*!40000 ALTER TABLE `FILE` DISABLE KEYS */;
INSERT INTO `FILE` VALUES (8,5,'F286C74F-6542-45D7-B09E-ADE214E4D13A.jpeg',5131410,'image/jpeg','upload/2a9d4ee9-1567-4053-89ac-1a1755fd1892.jpeg','2022-02-15 17:31:09'),(9,5,'32D551B2-EDC3-4B9B-A0B4-35DDC7C6E26F.jpeg',4136260,'image/jpeg','upload/6b1aa467-71e5-4a53-8a3d-5e6e206955f6.jpeg','2022-02-15 17:31:09'),(10,5,'4231251D-23FE-4F64-AC82-5A254F0CD0DF.jpeg',1751350,'image/jpeg','upload/90e239a2-264a-4dee-b99d-6250d22d2d95.jpeg','2022-02-15 17:31:09'),(11,5,'C7F17BFB-49BB-45F3-BC54-2577F4B85319.jpeg',657973,'image/jpeg','upload/c846a780-ab03-4236-a311-d632d7f7080d.jpeg','2022-02-15 17:31:09'),(12,5,'C38F44CF-1331-4572-8E33-D9EDA3915EA8.jpeg',2235730,'image/jpeg','upload/4d4318b8-3c6b-4781-ae6c-aa717544a676.jpeg','2022-02-15 17:31:09'),(13,5,'A7420DFF-EB90-4040-97E9-2483A4EF5828.jpeg',2224600,'image/jpeg','upload/a05cc81c-c5ca-4b84-8591-a9f30d3e1f70.jpeg','2022-02-15 17:31:09'),(24,9,'KakaoTalk_20210916_140454077.png',416976,'image/png','upload/41c18d9c-2867-4a63-abc5-1caac5527831.png','2022-02-16 13:52:59'),(25,8,'2.gif',8619640,'image/gif','upload/6e34b88c-0dd2-4bec-bcc3-4a27f9b06f59.gif','2022-02-16 13:55:52'),(26,8,'6.gif',8501500,'image/gif','upload/4acaed03-4868-4878-bb87-8ee60fcbb972.gif','2022-02-16 13:55:52'),(27,8,'17.gif',6439010,'image/gif','upload/48b86d33-ff0c-4971-a3ae-83e7c77623ac.gif','2022-02-16 13:55:52'),(28,8,'24.gif',9709220,'image/gif','upload/b2539b53-6bad-4948-9380-f0fae65e44e4.gif','2022-02-16 13:55:52'),(29,1,'1643694010198-0.jpg',107157,'image/jpeg','upload/bec89213-95a0-4335-9055-97b68ee67cca.jpg','2022-02-16 14:34:08'),(30,1,'1643694010198-1.jpg',118084,'image/jpeg','upload/a4cd38bb-4555-4d53-b375-5f94db52a709.jpg','2022-02-16 14:34:08'),(31,1,'1643694010198-2.jpg',95115,'image/jpeg','upload/7467e7de-5ed6-40e5-9eab-a46af54b180b.jpg','2022-02-16 14:34:08'),(32,1,'1643694010198-3.jpg',188732,'image/jpeg','upload/c2220fe9-c2cf-4a9f-baf4-e45b0e6e183b.jpg','2022-02-16 14:34:08'),(33,1,'1643694010198-4.jpg',186720,'image/jpeg','upload/2fbb4ac4-7e83-4035-b197-8eabb094e888.jpg','2022-02-16 14:34:08'),(34,1,'1643694010198-5.jpg',201728,'image/jpeg','upload/b00d73d0-8ad3-4f7b-b4d2-ad49c4bc6539.jpg','2022-02-16 14:34:08'),(35,7,'1643694006104-0.jpg',17201,'image/jpeg','upload/8cddf941-7e1d-496b-a683-6b9e62d557cc.jpg','2022-02-16 14:35:07'),(36,7,'1643694006104-1.jpg',15347,'image/jpeg','upload/77a473f1-dac9-49b9-8680-d14bd700478c.jpg','2022-02-16 14:35:07'),(37,7,'1643694006104-2.jpg',17278,'image/jpeg','upload/89281266-1397-49b8-8ee7-08ee9259ebdd.jpg','2022-02-16 14:35:07'),(38,7,'1643694006104-3.jpg',16305,'image/jpeg','upload/61f5cedf-9938-4852-b778-182fd6efc507.jpg','2022-02-16 14:35:07'),(39,7,'1643694006104-4.jpg',23852,'image/jpeg','upload/d1b9ddbc-e5c9-4a5a-bb8d-29befd28cccb.jpg','2022-02-16 14:35:07'),(40,7,'1643694006104-5.jpg',15305,'image/jpeg','upload/591b176a-a28e-44f3-bea0-229cee93e37f.jpg','2022-02-16 14:35:07'),(41,7,'1643694006104-6.jpg',20749,'image/jpeg','upload/7e357c62-d8b9-4e04-8ab6-9529b26ec198.jpg','2022-02-16 14:35:07'),(42,7,'1643694006104-7.jpg',25834,'image/jpeg','upload/362fca34-0b02-4711-a7e1-13b4c1a0bf34.jpg','2022-02-16 14:35:07'),(43,7,'1643694006104-8.jpg',20585,'image/jpeg','upload/649b86f0-2cd0-438b-8305-b13503e126f7.jpg','2022-02-16 14:35:07'),(45,11,'EE9F0DD4-DEDD-44B8-89AE-E6301B9DD544.jpeg',3683410,'image/jpeg','upload/2b61101a-7312-412b-b884-ef134f778650.jpeg','2022-02-16 16:17:05'),(46,11,'7B7A5E4A-8C4D-4229-B03D-E8755CAD7C93.jpeg',3434380,'image/jpeg','upload/988c14c0-b016-454e-80dd-5e93517d59de.jpeg','2022-02-16 16:17:05'),(47,11,'35B29659-1406-4259-8C9B-DDD8F76CEFA0.jpeg',3758030,'image/jpeg','upload/670cd56f-4177-403b-8c4f-55756cae5771.jpeg','2022-02-16 16:17:05'),(48,11,'5709BC6E-9920-4FD9-8EAA-178919D19A4D.jpeg',2168020,'image/jpeg','upload/1b798686-f141-4347-a6e7-9f219f11e774.jpeg','2022-02-16 16:17:05'),(50,3,'280621.jpg',46671,'image/jpeg','upload/d4d8a33d-78d9-4173-b721-86c57e1653d1.jpg','2022-02-17 06:10:39'),(51,13,'EB535831-FDB9-4B6B-A953-612829E08715.jpeg',2639580,'image/jpeg','upload/a273d574-5a02-4910-854d-17b08a445fe0.jpeg','2022-02-17 06:14:48'),(52,13,'31FCA51F-174B-45E8-B4CD-C37DFA3381BE.jpeg',2990140,'image/jpeg','upload/c5dfbe22-ecf3-4e8b-adab-0d3f6a46bb16.jpeg','2022-02-17 06:14:48'),(53,14,'20181220_210611.jpg',3481220,'image/jpeg','upload/1363af5d-d3df-4f26-972f-550d75f4ef22.jpg','2022-02-17 06:19:33'),(54,14,'20190202_131405.jpg',6551630,'image/jpeg','upload/610438aa-7a33-4432-8661-cfd6736041df.jpg','2022-02-17 06:19:33'),(55,14,'20190210_130951.jpg',5797160,'image/jpeg','upload/fd65c954-77e3-40c8-a91c-bb41d89df348.jpg','2022-02-17 06:19:33'),(56,14,'20190218_174022.jpg',6086830,'image/jpeg','upload/f26cfa64-8e6f-4d2f-9708-abfd80454b80.jpg','2022-02-17 06:19:33'),(57,14,'20190330_142844.jpg',7504700,'image/jpeg','upload/1133b16d-20b8-4e3d-878d-723e9d41e547.jpg','2022-02-17 06:19:33'),(58,14,'20190330_145946.jpg',7371250,'image/jpeg','upload/9807b8e6-afd4-4cc6-a1e0-118555357c30.jpg','2022-02-17 06:19:33'),(59,14,'20190330_180255.jpg',7368830,'image/jpeg','upload/a8e9e6e4-b61d-41c2-ba7a-89cbb3a96a20.jpg','2022-02-17 06:19:33'),(60,12,'스크린샷 2022-02-17 오후 4.02.38.png',18797,'image/png','upload/3cafe504-f5e0-4e3c-8c29-3745dd952e05.png','2022-02-17 07:02:54'),(61,15,'무제.jpg',16495,'image/jpeg','upload/1023b1cf-de2e-4294-aaa1-c328d3f43483.jpg','2022-02-17 13:13:14'),(62,16,'DSC09557.jpg',365428,'image/jpeg','upload/2445fa93-6c23-4372-ae14-5892fab1fb7e.jpg','2022-02-17 16:14:50'),(63,16,'DSC09610.jpg',566815,'image/jpeg','upload/4c37e13a-aaec-4c0c-ad93-310756345456.jpg','2022-02-17 16:14:50'),(64,16,'DSC09942.jpg',1565180,'image/jpeg','upload/764ac517-e3f4-49df-ab8e-892eabefeb65.jpg','2022-02-17 16:14:50'),(65,16,'DSC09945.jpg',919781,'image/jpeg','upload/6b60102e-5f18-4541-b917-d51ce6e7ac4b.jpg','2022-02-17 16:14:50'),(66,16,'DSC09982.jpg',2319380,'image/jpeg','upload/fe52675a-2507-4901-a0a0-833fdb62e93f.jpg','2022-02-17 16:14:50'),(67,17,'1487492245906.jpg',27944,'image/jpeg','upload/0a7372b6-706f-48bf-b161-d157b4d2b305.jpg','2022-02-17 16:19:22'),(68,17,'1487492247341.jpg',29618,'image/jpeg','upload/fc1b7521-3e77-4ea7-86ba-52bc5847afaf.jpg','2022-02-17 16:19:22'),(69,17,'1487492252614.jpg',23200,'image/jpeg','upload/a3d3107b-30bc-4081-b3aa-add89d1956c8.jpg','2022-02-17 16:19:22'),(70,18,'2DB7503C-17BB-498F-9578-F4CBA61AE658.jpeg',4380020,'image/jpeg','upload/71e21403-d6f6-4474-8d3b-861456c379ed.jpeg','2022-02-17 16:20:24'),(71,18,'F262B26C-F735-4C60-B9AC-152504411BF0.jpeg',2636520,'image/jpeg','upload/249ac609-e805-4385-b5dc-b2693781b9ed.jpeg','2022-02-17 16:20:24'),(72,18,'3009E517-19E3-4F36-AC45-01C0540C7945.jpeg',1991020,'image/jpeg','upload/3fb4b295-cc40-435b-9fb9-e6737e35288f.jpeg','2022-02-17 16:20:24'),(73,18,'0579C78B-B5BB-446D-9C61-8D566ADDD6DE.jpeg',3024400,'image/jpeg','upload/85036e2a-4e11-4712-8d37-272e40319daa.jpeg','2022-02-17 16:20:24'),(74,18,'17738D7D-0DDA-4DD5-B9FA-CB53B83742A3.jpeg',3360350,'image/jpeg','upload/f100de59-12e0-442c-8796-02bc905ef7f9.jpeg','2022-02-17 16:20:24'),(75,19,'AAFD3010-2AE4-4D0D-A9B3-B0DAAACF6F04.jpeg',2918170,'image/jpeg','upload/d7b9df4f-beca-4e80-91e4-e1e143638ab7.jpeg','2022-02-17 16:30:10'),(76,20,'1628159426855-4.jpg',2619180,'image/jpeg','upload/e88c622a-a4bf-4b75-928a-f5c0f7486543.jpg','2022-02-17 16:32:50'),(77,20,'1628875462371-17.jpg',1232560,'image/jpeg','upload/2e8f6758-4a39-4b75-bea1-2659142b34de.jpg','2022-02-17 16:32:50'),(78,20,'1628875545314-3.jpg',2942170,'image/jpeg','upload/abffc217-795e-489d-b141-765662063255.jpg','2022-02-17 16:32:50'),(79,20,'20210905_214003.jpg',1579030,'image/jpeg','upload/0eb74803-f741-4817-86df-408e5aff2b1b.jpg','2022-02-17 16:32:50'),(80,20,'20210906_201034.jpg',2926810,'image/jpeg','upload/724b26cb-957f-453a-9538-b33d4149092e.jpg','2022-02-17 16:32:50'),(81,20,'20210910_192820.jpg',1765080,'image/jpeg','upload/5a617a92-675c-440d-95fe-09cd5b12f226.jpg','2022-02-17 16:32:50'),(82,20,'20211207_121846.jpg',3182560,'image/jpeg','upload/4e7af314-6d20-4fe2-b1dd-3178a6490676.jpg','2022-02-17 16:32:50'),(83,20,'1640850078817-18.jpg',3407430,'image/jpeg','upload/a5f7e866-d9ba-48be-a60e-6a00a5538c26.jpg','2022-02-17 16:32:50'),(84,20,'20220125_121629.jpg',1788150,'image/jpeg','upload/73f29887-9263-4f06-8d4f-a8afe1140033.jpg','2022-02-17 16:32:50'),(85,21,'42349B47-F2C6-404D-88D2-5C25BFA0B5F6.jpeg',4136260,'image/jpeg','upload/83367af7-c747-4469-99f5-fe2848c8526b.jpeg','2022-02-17 16:49:31'),(86,21,'55ECF229-4222-4953-80C0-47F1669D80A9.jpeg',2235730,'image/jpeg','upload/59540b78-6f0e-4ddc-b42f-8be4b2622a34.jpeg','2022-02-17 16:49:31'),(87,21,'7C8719F0-DC4D-419C-8FC1-682502D9B026.jpeg',2400190,'image/jpeg','upload/7c794cb5-078d-4478-966a-d3c1d0ce17c3.jpeg','2022-02-17 16:49:31'),(88,21,'776FB47D-9979-40B8-89B9-345CAF78B130.jpeg',2543840,'image/jpeg','upload/5c31581b-e680-414c-87c4-ba20d19f7571.jpeg','2022-02-17 16:49:31'),(89,21,'B091EE5A-E1CB-4D94-A402-38CCFED9CC37.jpeg',1502100,'image/jpeg','upload/0806272b-5fad-4872-950a-fce6dbcf4b7e.jpeg','2022-02-17 16:49:31'),(90,22,'증명300x400.jpg',36227,'image/jpeg','upload/54972907-1ca7-45f3-bd41-d197beee93ec.jpg','2022-02-17 18:27:05');
/*!40000 ALTER TABLE `FILE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GAME`
--

DROP TABLE IF EXISTS `GAME`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `GAME` (
  `GAME_ID` int NOT NULL AUTO_INCREMENT,
  `GROUP_ID` int NOT NULL,
  `GAME_TITLE` varchar(50) NOT NULL,
  `GAME_AUTHOR` int NOT NULL,
  `GAME_REGISTER_DATE` timestamp NULL DEFAULT NULL,
  `CODE` char(5) NOT NULL,
  `UPDATE_YN` varchar(1) DEFAULT 'Y',
  PRIMARY KEY (`GAME_ID`),
  KEY `FK_GROUP_TO_GAME_1` (`GROUP_ID`),
  CONSTRAINT `FK_GROUP_TO_GAME_1` FOREIGN KEY (`GROUP_ID`) REFERENCES `GROUP_LIST` (`GROUP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GAME`
--

LOCK TABLES `GAME` WRITE;
/*!40000 ALTER TABLE `GAME` DISABLE KEYS */;
INSERT INTO `GAME` VALUES (1,1,'투표',2,'2022-02-15 16:17:38','40002','Y'),(2,1,'투표',2,'2022-02-15 16:17:56','40002','Y'),(3,1,'사다리타기',2,'2022-02-15 16:23:57','40001','Y'),(4,1,'투표오오',1,'2022-02-15 16:23:58','40002','Y'),(5,1,'투표',2,'2022-02-15 16:25:32','40002','Y'),(6,1,'투우우표',1,'2022-02-15 17:03:13','40002','Y'),(7,1,'투표',5,'2022-02-15 17:09:44','40002','Y'),(9,1,'투표',5,'2022-02-16 14:01:12','40002','Y'),(10,1,'투표',1,'2022-02-16 16:18:49','40002','N'),(11,8,'사다리타기',31,'2022-02-17 10:57:52','40001','Y'),(12,8,'투표',31,'2022-02-17 10:58:15','40002','Y'),(13,8,'오늘저녁은?',2,'2022-02-17 13:07:11','40002','Y'),(14,8,'설거지 당번',2,'2022-02-17 13:07:46','40001','Y');
/*!40000 ALTER TABLE `GAME` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GHOST_LEG`
--

DROP TABLE IF EXISTS `GHOST_LEG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `GHOST_LEG` (
  `GAME_ID` int NOT NULL,
  `PLAYER` varchar(100) NOT NULL,
  `PLAYER_NUM` int NOT NULL,
  `RESULT` varchar(100) NOT NULL,
  `MAP` varchar(300) NOT NULL,
  KEY `FK_GAME_TO_GHOST_LEG_1` (`GAME_ID`),
  CONSTRAINT `FK_GAME_TO_GHOST_LEG_1` FOREIGN KEY (`GAME_ID`) REFERENCES `GAME` (`GAME_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GHOST_LEG`
--

LOCK TABLES `GHOST_LEG` WRITE;
/*!40000 ALTER TABLE `GHOST_LEG` DISABLE KEYS */;
INSERT INTO `GHOST_LEG` VALUES (3,'0,강동옥,박상준,김태현,최이삭,임창현,강동옥2,박상준2,김태현2,최이삭2,임창현2',5,'0,결과1,결과2,결과3,결과4,결과5,결과6,결과7,결과8,결과9,결과10',',00001101010,00112202020,00221110000,01112220100,02220000200,00000000000,00000000000,00000000000,00000000000,00000000000,00000000000,00000000000'),(11,'0,강동옥,박상준,김태현,최이삭,임창현,강동옥2,박상준2,김태현2,최이삭2,임창현2',3,'0,결과1,결과2,결과3,결과4,결과5,결과6,결과7,결과8,결과9,결과10',',00011000000,01022000100,02000000200,00000000000,00000000000,00000000000,00000000000,00000000000,00000000000,00000000000,00000000000,00000000000'),(14,'0,강동옥,박상준,김태현,최이삭,임창현,강동옥2,박상준2,김태현2,최이삭2,임창현2',5,'0,꽝,당첨,꽝,꽝,꽝,결과6,결과7,결과8,결과9,결과10',',00001110010,01012220120,02020101200,00100212110,00200020220,00000000000,00000000000,00000000000,00000000000,00000000000,00000000000,00000000000');
/*!40000 ALTER TABLE `GHOST_LEG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GROUP_CODE`
--

DROP TABLE IF EXISTS `GROUP_CODE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `GROUP_CODE` (
  `GROUP_CODE` char(3) NOT NULL,
  `GROUP_CODE_NAME` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`GROUP_CODE`),
  UNIQUE KEY `GROUP_CODE_NAME` (`GROUP_CODE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GROUP_CODE`
--

LOCK TABLES `GROUP_CODE` WRITE;
/*!40000 ALTER TABLE `GROUP_CODE` DISABLE KEYS */;
/*!40000 ALTER TABLE `GROUP_CODE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GROUP_LIST`
--

DROP TABLE IF EXISTS `GROUP_LIST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `GROUP_LIST` (
  `GROUP_ID` int NOT NULL AUTO_INCREMENT,
  `GROUP_NAME` varchar(50) DEFAULT NULL,
  `GROUP_PROFILE_IMAGE_URL` varchar(300) DEFAULT 'img/noimage.png',
  `GROUP_LEADER_SEQ` int NOT NULL,
  `GROUP_PASSWORD` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`GROUP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GROUP_LIST`
--

LOCK TABLES `GROUP_LIST` WRITE;
/*!40000 ALTER TABLE `GROUP_LIST` DISABLE KEYS */;
INSERT INTO `GROUP_LIST` VALUES (1,'멋쟁이 사촌들','upload/92efeebf-42e2-40e2-97ce-51239b9334c0.jpg',1,'$2a$10$P04OvJm1Zgk5VsVmdvW8x.9As1y.P4T9gGWVjs91p3UHFN7fXuL5W'),(3,'1q2w3e4r','img/noimage.png',6,'$2a$10$6zahImdbVuLacgz4QxatOehbezOeQw03lU/hCZ2YLBMVAFAeyyoAG'),(4,'h','img/noimage.png',7,'$2a$10$PNiNLRzdv1.1U26X2oxwyuu71Frc1974oYVHf0LpusMm420JX8GKe'),(5,'a','img/noimage.png',6,'$2a$10$zKk8FAKZPlNkQ7I1dr9YGeXQ2na7asVNAILLnhEo8ZzRaiNal3aBS'),(6,'test','img/noimage.png',8,'$2a$10$Iz3CwKhQbtEBiB.M8dbABusevD7/PzCGGUL3eg4Ka6tuS6Qy3zfb6'),(7,'test123','img/noimage.png',16,'$2a$10$d5dVhyOWLg3kUbgFDwiLEu5kQGFrvU3NdBYvAZwVNI3jGxnB0til.'),(8,'우리집','upload/ce34fe33-0ae1-40d5-9ff6-33b8059fdc73.JPG',31,'$2a$10$t1tcJPQmy6ZoQxO5YMSUZepHkcQlRpC2vrfHoP8vUYIHhwupiARnq');
/*!40000 ALTER TABLE `GROUP_LIST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GROUP_MEMBER`
--

DROP TABLE IF EXISTS `GROUP_MEMBER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `GROUP_MEMBER` (
  `GROUP_ID` int NOT NULL,
  `USER_SEQ` int NOT NULL,
  `MANAGER_YN` char(1) DEFAULT 'N',
  KEY `FK_GROUP_TO_GROUP_MEMBER_1` (`GROUP_ID`),
  KEY `FK_USER_TO_GROUP_MEMBER_1` (`USER_SEQ`),
  CONSTRAINT `FK_GROUP_TO_GROUP_MEMBER_1` FOREIGN KEY (`GROUP_ID`) REFERENCES `GROUP_LIST` (`GROUP_ID`),
  CONSTRAINT `FK_USER_TO_GROUP_MEMBER_1` FOREIGN KEY (`USER_SEQ`) REFERENCES `USER` (`USER_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GROUP_MEMBER`
--

LOCK TABLES `GROUP_MEMBER` WRITE;
/*!40000 ALTER TABLE `GROUP_MEMBER` DISABLE KEYS */;
INSERT INTO `GROUP_MEMBER` VALUES (1,1,'Y'),(1,2,'Y'),(1,3,'Y'),(3,6,'Y'),(4,7,'Y'),(5,6,'Y'),(6,8,'Y'),(1,4,'Y'),(7,16,'Y'),(8,31,'Y'),(8,4,'Y'),(8,2,'Y'),(8,1,'Y'),(8,3,'Y');
/*!40000 ALTER TABLE `GROUP_MEMBER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `HASHTAG`
--

DROP TABLE IF EXISTS `HASHTAG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `HASHTAG` (
  `HASHTAG_SEQ` int NOT NULL AUTO_INCREMENT,
  `FEED_ID` int NOT NULL,
  `HASHTAG_CONTENT` varchar(50) NOT NULL,
  PRIMARY KEY (`HASHTAG_SEQ`),
  KEY `FK_FEED_TO_HASHTAG_1` (`FEED_ID`),
  CONSTRAINT `FK_FEED_TO_HASHTAG_1` FOREIGN KEY (`FEED_ID`) REFERENCES `FEED` (`FEED_ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HASHTAG`
--

LOCK TABLES `HASHTAG` WRITE;
/*!40000 ALTER TABLE `HASHTAG` DISABLE KEYS */;
INSERT INTO `HASHTAG` VALUES (11,5,'보리'),(12,5,'내'),(13,5,'이름은'),(14,5,'보오리'),(32,9,'구경와'),(33,9,'어서어서'),(34,9,'이리로'),(35,9,'커몬'),(36,1,'새복많'),(37,1,'새복많~!~!'),(38,7,'새해'),(39,7,'2022'),(40,7,'대박나자..'),(41,7,'취뽀하자..취뽀..'),(43,11,'고냥이'),(44,11,'이구역'),(45,11,'양아치'),(48,3,'해시태그1'),(49,3,'해시태그추가추가'),(50,13,'소고기전골'),(51,13,'마싯성'),(52,14,'이사'),(53,14,'맛도리'),(54,14,'너무행복함'),(55,14,'다들놀러와'),(56,14,'비행기표는'),(57,14,'알아서사'),(58,12,'무한'),(59,12,'테스트'),(60,16,'멋쟁이'),(61,16,'내친구'),(63,18,'맛집'),(64,18,'스테이크'),(65,18,'혼자못가'),(66,18,'같이가~'),(67,17,'바다'),(68,19,'바다'),(69,19,'추어영'),(70,20,'꼬물이'),(71,20,'귀엽죠?'),(77,21,'내'),(78,21,'이름은'),(79,21,'보리'),(80,21,'보오오오리'),(81,21,'츄르내놔랑'),(82,22,'Back'),(83,22,'DB스키마'),(84,22,'JWT');
/*!40000 ALTER TABLE `HASHTAG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LOCATION`
--

DROP TABLE IF EXISTS `LOCATION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `LOCATION` (
  `LOCATION_ID` int NOT NULL AUTO_INCREMENT,
  `GROUP_ID` int NOT NULL,
  `LOCATION_NAME` varchar(100) DEFAULT NULL,
  `LAT` float DEFAULT NULL,
  `LNG` float DEFAULT NULL,
  PRIMARY KEY (`LOCATION_ID`),
  KEY `FK_GROUP_TO_LOCATION_1` (`GROUP_ID`),
  CONSTRAINT `FK_GROUP_TO_LOCATION_1` FOREIGN KEY (`GROUP_ID`) REFERENCES `GROUP_LIST` (`GROUP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LOCATION`
--

LOCK TABLES `LOCATION` WRITE;
/*!40000 ALTER TABLE `LOCATION` DISABLE KEYS */;
INSERT INTO `LOCATION` VALUES (1,1,'집',37.5571,127.007),(2,1,'여긴바로 서울',37.5625,126.987),(3,8,'여기어딘가',35.2383,129.085),(4,8,'미쿡어딘가',33.8425,-118.385),(5,1,'415 error?????!!!!',37.5727,126.988),(6,8,'진주 공연장',35.1937,128.085),(7,8,'니도',35.153,128.107),(8,8,'다대포',35.0388,128.971),(9,8,'내 집이야',35.9371,128.557),(10,8,'우리집',35.1456,128.104),(11,1,'내집이야',35.1535,129.024);
/*!40000 ALTER TABLE `LOCATION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LOCATION_FAVORITE`
--

DROP TABLE IF EXISTS `LOCATION_FAVORITE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `LOCATION_FAVORITE` (
  `LOCATION_ID` int NOT NULL,
  `USER_SEQ` int NOT NULL,
  KEY `FK_LOCATION_TO_LOCATION_FAVORITE_1` (`LOCATION_ID`),
  KEY `FK_USER_TO_LOCATION_FAVORITE_1` (`USER_SEQ`),
  CONSTRAINT `FK_LOCATION_TO_LOCATION_FAVORITE_1` FOREIGN KEY (`LOCATION_ID`) REFERENCES `LOCATION` (`LOCATION_ID`),
  CONSTRAINT `FK_USER_TO_LOCATION_FAVORITE_1` FOREIGN KEY (`USER_SEQ`) REFERENCES `USER` (`USER_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LOCATION_FAVORITE`
--

LOCK TABLES `LOCATION_FAVORITE` WRITE;
/*!40000 ALTER TABLE `LOCATION_FAVORITE` DISABLE KEYS */;
INSERT INTO `LOCATION_FAVORITE` VALUES (1,2),(2,2),(3,2),(3,1),(5,1),(2,1),(1,1),(9,4);
/*!40000 ALTER TABLE `LOCATION_FAVORITE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NOTICE`
--

DROP TABLE IF EXISTS `NOTICE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `NOTICE` (
  `NOTICE_ID` int NOT NULL AUTO_INCREMENT,
  `USER_SEQ` int DEFAULT NULL,
  `TARGET_USER_SEQ` int DEFAULT NULL,
  `TARGET_USER_GROUP_ID` int DEFAULT NULL,
  `NOTICE_TYPE` varchar(50) DEFAULT NULL,
  `NOTICE_CONTENT_ID` int DEFAULT NULL,
  `NOTICE_READ_YN` char(1) DEFAULT 'N',
  `NOTICE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`NOTICE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=215 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NOTICE`
--

LOCK TABLES `NOTICE` WRITE;
/*!40000 ALTER TABLE `NOTICE` DISABLE KEYS */;
INSERT INTO `NOTICE` VALUES (50,4,1,1,'shareCreate',9,'y','2022-02-16 16:10:57'),(51,4,2,1,'shareCreate',9,'y','2022-02-16 16:10:57'),(52,4,3,1,'shareCreate',9,'y','2022-02-16 16:10:57'),(53,4,5,1,'shareCreate',9,'n','2022-02-16 16:10:57'),(54,4,1,1,'commentTagged',9,'y','2022-02-16 16:11:11'),(55,4,1,1,'commentTagged',9,'y','2022-02-16 16:11:31'),(56,4,1,1,'commentTagged',9,'y','2022-02-16 16:11:44'),(57,4,1,1,'shareCreate',11,'n','2022-02-16 16:17:27'),(58,4,2,1,'shareCreate',11,'y','2022-02-16 16:17:27'),(59,4,3,1,'shareCreate',11,'y','2022-02-16 16:17:27'),(60,4,5,1,'shareCreate',11,'n','2022-02-16 16:17:27'),(61,4,1,1,'shareCreate',11,'n','2022-02-16 16:17:34'),(62,4,2,1,'shareCreate',11,'y','2022-02-16 16:17:34'),(63,4,3,1,'shareCreate',11,'y','2022-02-16 16:17:34'),(64,4,5,1,'shareCreate',11,'n','2022-02-16 16:17:34'),(65,4,1,1,'shareCreate',11,'n','2022-02-16 16:17:48'),(66,4,2,1,'shareCreate',11,'y','2022-02-16 16:17:48'),(67,4,3,1,'shareCreate',11,'y','2022-02-16 16:17:48'),(68,4,5,1,'shareCreate',11,'n','2022-02-16 16:17:48'),(69,4,1,1,'shareCreate',8,'n','2022-02-16 16:18:08'),(70,4,2,1,'shareCreate',8,'y','2022-02-16 16:18:08'),(71,4,3,1,'shareCreate',8,'y','2022-02-16 16:18:08'),(72,4,5,1,'shareCreate',8,'n','2022-02-16 16:18:08'),(73,4,1,1,'shareCreate',8,'y','2022-02-16 16:18:14'),(74,4,2,1,'shareCreate',8,'y','2022-02-16 16:18:14'),(75,4,3,1,'shareCreate',8,'y','2022-02-16 16:18:14'),(76,4,5,1,'shareCreate',8,'n','2022-02-16 16:18:14'),(77,1,2,1,'voteCreate',10,'y','2022-02-16 16:18:49'),(78,1,3,1,'voteCreate',10,'y','2022-02-16 16:18:49'),(79,1,5,1,'voteCreate',10,'n','2022-02-16 16:18:49'),(80,1,4,1,'voteCreate',10,'y','2022-02-16 16:18:49'),(81,4,5,1,'commentTagged',8,'n','2022-02-16 16:19:01'),(82,4,1,1,'commentTagged',8,'y','2022-02-16 16:19:01'),(83,1,2,1,'voteEnd',10,'y','2022-02-16 16:19:06'),(84,1,3,1,'voteEnd',10,'y','2022-02-16 16:19:06'),(85,1,5,1,'voteEnd',10,'n','2022-02-16 16:19:06'),(86,1,4,1,'voteEnd',10,'y','2022-02-16 16:19:06'),(87,1,2,1,'feedCreate',12,'y','2022-02-16 17:41:41'),(88,1,3,1,'feedCreate',12,'y','2022-02-16 17:41:41'),(89,1,5,1,'feedCreate',12,'n','2022-02-16 17:41:41'),(90,1,4,1,'feedCreate',12,'y','2022-02-16 17:41:41'),(91,3,1,1,'shareCreate',12,'y','2022-02-17 05:34:16'),(92,3,2,1,'shareCreate',12,'y','2022-02-17 05:34:16'),(93,3,5,1,'shareCreate',12,'n','2022-02-17 05:34:16'),(94,3,4,1,'shareCreate',12,'n','2022-02-17 05:34:16'),(95,2,1,1,'shareCreate',11,'n','2022-02-17 05:50:38'),(96,2,3,1,'shareCreate',11,'y','2022-02-17 05:50:38'),(97,2,5,1,'shareCreate',11,'n','2022-02-17 05:50:38'),(98,2,4,1,'shareCreate',11,'y','2022-02-17 05:50:38'),(99,4,31,8,'feedCreate',14,'y','2022-02-17 06:19:33'),(100,4,2,8,'feedCreate',14,'y','2022-02-17 06:19:33'),(101,4,31,8,'shareCreate',14,'y','2022-02-17 06:20:03'),(102,4,2,8,'shareCreate',14,'n','2022-02-17 06:20:03'),(103,4,2,8,'commentTagged',13,'y','2022-02-17 06:21:09'),(104,4,1,1,'shareCreate',9,'n','2022-02-17 08:31:55'),(105,4,2,1,'shareCreate',9,'y','2022-02-17 08:31:55'),(106,4,3,1,'shareCreate',9,'y','2022-02-17 08:31:55'),(107,4,5,1,'shareCreate',9,'n','2022-02-17 08:31:55'),(108,4,1,1,'shareCreate',9,'n','2022-02-17 08:32:03'),(109,4,2,1,'shareCreate',9,'y','2022-02-17 08:32:03'),(110,4,3,1,'shareCreate',9,'y','2022-02-17 08:32:03'),(111,4,5,1,'shareCreate',9,'n','2022-02-17 08:32:03'),(112,4,2,8,'commentTagged',14,'n','2022-02-17 08:33:29'),(113,2,4,8,'commentTagged',14,'n','2022-02-17 08:34:23'),(114,2,4,8,'commentTagged',14,'n','2022-02-17 08:37:10'),(115,4,2,8,'commentTagged',14,'n','2022-02-17 08:38:33'),(116,4,2,8,'commentTagged',13,'n','2022-02-17 08:58:39'),(117,2,4,8,'commentTagged',13,'n','2022-02-17 09:01:06'),(118,31,4,8,'shareCreate',14,'y','2022-02-17 10:41:40'),(119,31,2,8,'shareCreate',14,'n','2022-02-17 10:41:40'),(120,31,1,8,'shareCreate',14,'y','2022-02-17 10:41:40'),(121,31,4,8,'ghostLegCreate',11,'y','2022-02-17 10:57:52'),(122,31,2,8,'ghostLegCreate',11,'n','2022-02-17 10:57:52'),(123,31,1,8,'ghostLegCreate',11,'y','2022-02-17 10:57:52'),(124,31,4,8,'voteCreate',12,'y','2022-02-17 10:58:15'),(125,31,2,8,'voteCreate',12,'n','2022-02-17 10:58:15'),(126,31,1,8,'voteCreate',12,'y','2022-02-17 10:58:15'),(127,4,31,8,'shareCreate',13,'n','2022-02-17 12:32:14'),(128,4,2,8,'shareCreate',13,'n','2022-02-17 12:32:14'),(129,4,1,8,'shareCreate',13,'y','2022-02-17 12:32:14'),(130,4,31,8,'shareCreate',13,'n','2022-02-17 12:32:27'),(131,4,2,8,'shareCreate',13,'n','2022-02-17 12:32:27'),(132,4,1,8,'shareCreate',13,'y','2022-02-17 12:32:27'),(133,2,31,8,'voteCreate',13,'n','2022-02-17 13:07:11'),(134,2,4,8,'voteCreate',13,'y','2022-02-17 13:07:11'),(135,2,1,8,'voteCreate',13,'y','2022-02-17 13:07:11'),(136,2,31,8,'ghostLegCreate',14,'n','2022-02-17 13:07:46'),(137,2,4,8,'ghostLegCreate',14,'y','2022-02-17 13:07:46'),(138,2,1,8,'ghostLegCreate',14,'y','2022-02-17 13:07:46'),(139,1,2,1,'feedCreate',15,'n','2022-02-17 13:13:14'),(140,1,3,1,'feedCreate',15,'n','2022-02-17 13:13:14'),(141,1,5,1,'feedCreate',15,'n','2022-02-17 13:13:14'),(142,1,4,1,'feedCreate',15,'n','2022-02-17 13:13:14'),(143,1,31,8,'feedCreate',16,'n','2022-02-17 16:14:50'),(144,1,4,8,'feedCreate',16,'n','2022-02-17 16:14:50'),(145,1,2,8,'feedCreate',16,'n','2022-02-17 16:14:50'),(146,1,3,8,'feedCreate',16,'y','2022-02-17 16:14:50'),(147,2,31,8,'shareCreate',19,'n','2022-02-17 16:32:12'),(148,2,4,8,'shareCreate',19,'n','2022-02-17 16:32:12'),(149,2,1,8,'shareCreate',19,'n','2022-02-17 16:32:12'),(150,2,3,8,'shareCreate',19,'y','2022-02-17 16:32:12'),(151,4,31,8,'feedCreate',20,'n','2022-02-17 16:32:50'),(152,4,2,8,'feedCreate',20,'n','2022-02-17 16:32:50'),(153,4,1,8,'feedCreate',20,'n','2022-02-17 16:32:50'),(154,4,3,8,'feedCreate',20,'y','2022-02-17 16:32:50'),(155,4,1,8,'commentCreate',16,'n','2022-02-17 16:34:30'),(156,4,31,8,'shareCreate',20,'n','2022-02-17 16:35:20'),(157,4,2,8,'shareCreate',20,'n','2022-02-17 16:35:20'),(158,4,1,8,'shareCreate',20,'n','2022-02-17 16:35:20'),(159,4,3,8,'shareCreate',20,'y','2022-02-17 16:35:20'),(160,4,31,8,'shareCreate',17,'n','2022-02-17 16:35:36'),(161,4,2,8,'shareCreate',17,'n','2022-02-17 16:35:36'),(162,4,1,8,'shareCreate',17,'n','2022-02-17 16:35:36'),(163,4,3,8,'shareCreate',17,'y','2022-02-17 16:35:36'),(164,4,31,8,'shareCreate',20,'y','2022-02-17 16:35:48'),(165,4,2,8,'shareCreate',20,'y','2022-02-17 16:35:48'),(166,4,1,8,'shareCreate',20,'n','2022-02-17 16:35:48'),(167,4,3,8,'shareCreate',20,'y','2022-02-17 16:35:48'),(168,1,4,8,'commentCreate',20,'n','2022-02-17 16:36:41'),(169,1,2,8,'commentCreate',19,'n','2022-02-17 16:41:53'),(170,1,2,8,'commentCreate',13,'y','2022-02-17 16:42:14'),(171,31,4,8,'shareCreate',20,'n','2022-02-17 16:44:12'),(172,31,2,8,'shareCreate',20,'n','2022-02-17 16:44:12'),(173,31,1,8,'shareCreate',20,'n','2022-02-17 16:44:12'),(174,31,3,8,'shareCreate',20,'n','2022-02-17 16:44:12'),(175,31,4,8,'shareCreate',20,'n','2022-02-17 16:44:34'),(176,31,2,8,'shareCreate',20,'n','2022-02-17 16:44:34'),(177,31,1,8,'shareCreate',20,'n','2022-02-17 16:44:34'),(178,31,3,8,'shareCreate',20,'n','2022-02-17 16:44:34'),(179,31,4,8,'shareCreate',20,'n','2022-02-17 16:45:53'),(180,31,2,8,'shareCreate',20,'n','2022-02-17 16:45:53'),(181,31,1,8,'shareCreate',20,'n','2022-02-17 16:45:53'),(182,31,3,8,'shareCreate',20,'n','2022-02-17 16:45:53'),(183,31,4,8,'shareCreate',20,'n','2022-02-17 16:46:49'),(184,31,2,8,'shareCreate',20,'n','2022-02-17 16:46:49'),(185,31,1,8,'shareCreate',20,'n','2022-02-17 16:46:49'),(186,31,3,8,'shareCreate',20,'n','2022-02-17 16:46:49'),(187,1,31,8,'feedCreate',21,'n','2022-02-17 16:49:31'),(188,1,4,8,'feedCreate',21,'n','2022-02-17 16:49:31'),(189,1,2,8,'feedCreate',21,'n','2022-02-17 16:49:31'),(190,1,3,8,'feedCreate',21,'y','2022-02-17 16:49:31'),(191,2,31,8,'shareCreate',17,'n','2022-02-17 17:12:02'),(192,2,4,8,'shareCreate',17,'y','2022-02-17 17:12:02'),(193,2,1,8,'shareCreate',17,'n','2022-02-17 17:12:02'),(194,2,3,8,'shareCreate',17,'n','2022-02-17 17:12:02'),(195,4,3,8,'commentCreate',17,'y','2022-02-17 17:12:27'),(196,4,3,8,'commentTagged',17,'n','2022-02-17 17:12:27'),(197,4,2,8,'commentCreate',19,'n','2022-02-17 17:14:30'),(198,4,31,8,'shareCreate',21,'n','2022-02-17 17:15:18'),(199,4,2,8,'shareCreate',21,'n','2022-02-17 17:15:18'),(200,4,1,8,'shareCreate',21,'y','2022-02-17 17:15:18'),(201,4,3,8,'shareCreate',21,'y','2022-02-17 17:15:18'),(202,4,1,8,'commentCreate',21,'n','2022-02-17 17:16:06'),(203,4,1,8,'commentCreate',21,'n','2022-02-17 17:16:23'),(204,3,31,8,'shareCreate',17,'n','2022-02-17 17:33:37'),(205,3,4,8,'shareCreate',17,'n','2022-02-17 17:33:37'),(206,3,2,8,'shareCreate',17,'n','2022-02-17 17:33:37'),(207,3,1,8,'shareCreate',17,'n','2022-02-17 17:33:37'),(208,3,1,1,'feedCreate',22,'n','2022-02-17 18:27:05'),(209,3,2,1,'feedCreate',22,'n','2022-02-17 18:27:05'),(210,3,4,1,'feedCreate',22,'n','2022-02-17 18:27:05'),(211,1,31,8,'shareCreate',21,'n','2022-02-17 19:17:50'),(212,1,4,8,'shareCreate',21,'n','2022-02-17 19:17:50'),(213,1,2,8,'shareCreate',21,'n','2022-02-17 19:17:50'),(214,1,3,8,'shareCreate',21,'n','2022-02-17 19:17:50');
/*!40000 ALTER TABLE `NOTICE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PROFILE_IMAGE`
--

DROP TABLE IF EXISTS `PROFILE_IMAGE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PROFILE_IMAGE` (
  `PROFILE_IMAGE_ID` int NOT NULL AUTO_INCREMENT,
  `USER_SEQ` int DEFAULT NULL,
  `GROUP_ID` int DEFAULT NULL,
  `PROFILE_IMAGE_NAME` varchar(50) DEFAULT NULL,
  `PROFILE_IMAGE_SIZE` float DEFAULT NULL,
  `PROFILE_IMAGE_URL` varchar(300) DEFAULT NULL,
  `REG_DT` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`PROFILE_IMAGE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PROFILE_IMAGE`
--

LOCK TABLES `PROFILE_IMAGE` WRITE;
/*!40000 ALTER TABLE `PROFILE_IMAGE` DISABLE KEYS */;
INSERT INTO `PROFILE_IMAGE` VALUES (1,NULL,1,'스크린샷 2022-02-15 오전 1.14.38.png',189240,'upload/503eadce-142f-477f-9aa8-a7966b06b4fe.png','2022-02-15 16:08:58'),(2,1,NULL,'스크린샷 2022-02-15 오전 1.14.38.png',189240,'upload/683f73e1-4cf3-4dd6-b533-d5fff7c35498.png','2022-02-15 18:14:33'),(3,1,NULL,'스크린샷 2022-02-14 오후 10.53.26.png',95609,'upload/94cdaf12-5de9-45fb-8627-35738c9419d4.png','2022-02-15 18:15:36'),(4,1,NULL,'스크린샷 2022-02-15 오전 1.14.38.png',189240,'upload/e5ea9d6a-fc01-4f82-a12a-68f953bb5e1c.png','2022-02-16 07:36:36'),(5,1,NULL,'스크린샷 2022-02-15 오전 10.18.07.png',170849,'upload/cc6b9c14-51f9-4e71-b739-df009e0006cc.png','2022-02-16 07:37:20'),(6,1,NULL,'스크린샷 2022-02-15 오전 1.14.38.png',189240,'upload/4646ca7f-ef6d-44d9-9512-2eb7f65a5b45.png','2022-02-16 08:27:12'),(7,8,NULL,'그림판.png',1759,'upload/657688a4-2c6e-4ed9-8400-dea0ec6d6eb9.png','2022-02-16 11:33:08'),(8,4,NULL,'20211225_150814.jpg',262276,'upload/86b9413e-d17f-4258-8cd0-56b5105d7951.jpg','2022-02-16 12:23:27'),(9,4,NULL,'6.gif',8501500,'upload/e2fec98a-39e1-478f-8576-29f224912b40.gif','2022-02-16 12:33:04'),(10,5,NULL,'KakaoTalk_20210916_140454077.png',416976,'upload/46f0935b-1ecf-418d-a326-b46ad1728993.png','2022-02-16 13:53:53'),(11,2,NULL,'KakaoTalk_20210916_140454077.png',416976,'upload/ceea570f-a78f-4e46-8d43-c979d355c16e.png','2022-02-17 06:10:39'),(12,4,NULL,'20211225_150800.jpg',275246,'upload/133260f6-e770-40c9-a9e7-b0c5e413691f.jpg','2022-02-17 12:12:38'),(13,4,NULL,'img.gif',8732780,'upload/9cc512b7-6de1-40ff-8002-293aa7799eb0.gif','2022-02-17 12:15:38'),(14,1,NULL,'istockphoto-585522866-170667a.jpg',56865,'upload/011834d8-6105-43f0-b584-41bbc14d9626.jpg','2022-02-17 12:18:58'),(15,3,NULL,'증명300x400.jpg',36227,'upload/279de885-41a8-46e1-bc94-8a25a506b0d3.jpg','2022-02-17 12:19:51'),(16,NULL,1,'istockphoto-585522866-170667a.jpg',56865,'upload/cd876adc-5008-4ebc-9396-dd66dbdc5143.jpg','2022-02-17 12:34:26'),(17,NULL,1,'istockphoto-1209904398-170667a.jpg',65403,'upload/8548d97c-4362-4026-8591-bc1e3443938d.jpg','2022-02-17 12:35:49'),(18,NULL,1,'images.jpeg',6697,'upload/dd7d51fa-fe12-49c2-95b0-a301c4e35411.jpeg','2022-02-17 12:36:48'),(19,NULL,1,'images.png',5355,'upload/22f5d90f-e9c3-4efa-ad7a-9c86a783a6d4.png','2022-02-17 12:37:19'),(20,1,NULL,'무제.jpg',16495,'upload/85acb224-29fc-44a8-8084-d40ae4c3599a.jpg','2022-02-17 12:53:29'),(21,4,NULL,'20181220_210611.jpg',3481220,'upload/638d8832-96e1-46b5-a03b-7836ce76a3a8.jpg','2022-02-17 13:08:39'),(22,NULL,1,'star-half.png',5926,'upload/7b2d3635-9de8-4cd8-be87-7e1fa579541a.png','2022-02-17 13:13:32'),(23,NULL,1,'star-half.png',5926,'upload/9450bd44-98bd-4214-827f-f87dd0bd0a25.png','2022-02-17 13:14:14'),(24,NULL,1,'star.png',7871,'upload/a31d022d-5061-4320-8f57-96638546df19.png','2022-02-17 13:21:21'),(25,NULL,1,'star.png',7871,'upload/008c62cc-7ed5-4d5f-ac5f-27fe1cebff48.png','2022-02-17 13:21:37'),(26,NULL,1,'star-grey.png',12387,'upload/e86c7437-19d7-4da4-b63f-848c806ff240.png','2022-02-17 13:24:27'),(27,NULL,8,'DSC02551.JPG',6422530,'upload/62dbf907-43ab-40eb-9a40-896dc4889a86.JPG','2022-02-17 16:26:41'),(28,NULL,8,'DSC09982.jpg',2319380,'upload/154d8f89-3230-4f0f-a04b-add12fda1da1.jpg','2022-02-17 16:55:34'),(29,NULL,8,'DSC02551.JPG',6422530,'upload/ce34fe33-0ae1-40d5-9ff6-33b8059fdc73.JPG','2022-02-17 16:55:48'),(30,NULL,1,'DSC09982.jpg',2319380,'upload/92efeebf-42e2-40e2-97ce-51239b9334c0.jpg','2022-02-17 17:07:21'),(31,4,NULL,'2021-10-15-21-31-50-237.jpg',4264140,'upload/b205372e-06a9-4646-8eb8-dd1129086f2f.jpg','2022-02-17 17:14:02');
/*!40000 ALTER TABLE `PROFILE_IMAGE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SCHEDULE`
--

DROP TABLE IF EXISTS `SCHEDULE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SCHEDULE` (
  `SCHEDULE_ID` int NOT NULL AUTO_INCREMENT,
  `GROUP_ID` int NOT NULL,
  `SCHEDULE_TITLE` varchar(50) DEFAULT NULL,
  `SCHEDULE_CONTENT` varchar(300) DEFAULT NULL,
  `SCHEDULE_DATE_START` date NOT NULL,
  `SCHEDULE_DATE_END` date NOT NULL,
  `SCHEDULE_TIME_START` time DEFAULT NULL,
  `SCHEDULE_TIME_END` time DEFAULT NULL,
  PRIMARY KEY (`SCHEDULE_ID`),
  KEY `FK_GROUP_TO_SCHEDULE_1` (`GROUP_ID`),
  CONSTRAINT `FK_GROUP_TO_SCHEDULE_1` FOREIGN KEY (`GROUP_ID`) REFERENCES `GROUP_LIST` (`GROUP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SCHEDULE`
--

LOCK TABLES `SCHEDULE` WRITE;
/*!40000 ALTER TABLE `SCHEDULE` DISABLE KEYS */;
INSERT INTO `SCHEDULE` VALUES (1,1,'프로젝트 발표!','발표자는 ㄷㄱㄷㄱㄷㄱㄷㄱㄷㄱㄷㄱㄷㄱㄷㄱㄷㄱㄷㄱㄷㄱㄷㄱ ㅌㅎ','2022-02-18','2022-02-18','15:00:00','17:00:00'),(2,8,'프로젝트 발표!!','','2022-02-18','2022-02-18','13:00:00','17:30:00'),(4,8,'가족여행','여행행','2022-02-03','2022-02-07','00:05:00','00:00:00'),(5,8,'졸업식','','2022-02-25','2022-02-25','09:43:00','00:06:00'),(6,8,'프로젝트 마지막 달리기','프로젝트 마지막 달리기!!','2022-02-14','2022-02-17','08:30:00','18:00:00'),(7,8,'오픽준비기간','진짜진짜 오픽칠거야','2022-02-07','2022-02-12','00:00:00','11:01:00');
/*!40000 ALTER TABLE `SCHEDULE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SCRAP`
--

DROP TABLE IF EXISTS `SCRAP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SCRAP` (
  `USER_SEQ` int NOT NULL,
  `FEED_ID` int NOT NULL,
  KEY `FK_USER_TO_SCRAP_1` (`USER_SEQ`),
  KEY `FK_FEED_TO_SCRAP_1` (`FEED_ID`),
  CONSTRAINT `FK_FEED_TO_SCRAP_1` FOREIGN KEY (`FEED_ID`) REFERENCES `FEED` (`FEED_ID`) ON DELETE CASCADE,
  CONSTRAINT `FK_USER_TO_SCRAP_1` FOREIGN KEY (`USER_SEQ`) REFERENCES `USER` (`USER_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SCRAP`
--

LOCK TABLES `SCRAP` WRITE;
/*!40000 ALTER TABLE `SCRAP` DISABLE KEYS */;
INSERT INTO `SCRAP` VALUES (1,5),(4,8),(1,9),(5,9),(4,12),(2,14),(1,11),(31,14),(1,13),(1,17),(1,20),(1,21),(1,14),(2,17),(3,14),(3,17),(2,21),(3,21);
/*!40000 ALTER TABLE `SCRAP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER`
--

DROP TABLE IF EXISTS `USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `USER` (
  `USER_SEQ` int NOT NULL AUTO_INCREMENT,
  `USER_ID` varchar(50) DEFAULT NULL,
  `USER_PASSWORD` varchar(300) NOT NULL,
  `USER_EMAIL` varchar(50) DEFAULT NULL,
  `USER_NAME` varchar(50) NOT NULL,
  `USER_NICKNAME` varchar(50) DEFAULT NULL,
  `USER_PHONE` varchar(50) DEFAULT NULL,
  `USER_BOD` date DEFAULT NULL,
  `USER_PROFILE_IMAGE_URL` varchar(300) DEFAULT 'img/noimage.png',
  `USER_REGISTER_DATE` timestamp NULL DEFAULT NULL,
  `USER_CLS` char(5) DEFAULT NULL,
  `REFRESH_TOKEN` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`USER_SEQ`),
  UNIQUE KEY `USER_ID` (`USER_ID`),
  UNIQUE KEY `USER_EMAIL` (`USER_EMAIL`),
  UNIQUE KEY `USER_PHONE` (`USER_PHONE`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER`
--

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;
INSERT INTO `USER` VALUES (1,'test11','$2a$10$Vd9tbUe8.oDIBggxflB/PucxSFaErQnF/mJWBwR22Mw72M938vXIG','Sac@sac.com','삭삭',NULL,'010-2343-2342','1995-06-30','upload/85acb224-29fc-44a8-8084-d40ae4c3599a.jpg','2022-02-15 16:08:31',NULL,NULL),(2,'okdong','$2a$10$k0BLNAQ49gKRchLocZv0suYnd0uT1ndm0u9y/1.PqZxOKODIY..q2','okdong@aa.aa','곤듀',NULL,'123123123','2022-02-24','upload/ceea570f-a78f-4e46-8d43-c979d355c16e.png','2022-02-15 16:09:32',NULL,NULL),(3,'lch','$2a$10$ZuubN6MPGxh67WZ4FKna2uf3uTNLs3rjp4BI3GjtIKVjKnzjhA.oa','ckdgus777@naver.com','임창현',NULL,'010-9501-4090','1995-08-29','upload/279de885-41a8-46e1-bc94-8a25a506b0d3.jpg','2022-02-15 16:43:53',NULL,NULL),(4,'nasa0939','$2a$10$bYfrEeeZnqIQZ63OPgFvQ.iujXlsWyi7jfgmRrZY37L1e91Gtq7gC','nasa0939@naver.com','Eㅐ현',NULL,'01027720939','1996-11-20','upload/b205372e-06a9-4646-8eb8-dd1129086f2f.jpg','2022-02-15 17:02:53',NULL,NULL),(5,'test134','$2a$10$zC753EVWyK7MbIjVNF4EAekRRJ2HUdWmYdOOkYqMuH5c1KY4sSA3i','okdong@sxcc.fdf','왕자',NULL,'01000000000','2022-03-04','upload/46f0935b-1ecf-418d-a326-b46ad1728993.png','2022-02-15 17:09:20',NULL,NULL),(6,'1q2w3e4r','$2a$10$kgmC6Jbf0DT.t6YAF.nbH.DXEzk55H.VizIThKlBQ15HXQu2gAwtK','1q2w3e4r@1q2w3e4r.com','1q2w3e4r',NULL,'1q2w3e4r','2022-02-01','img/noimage.png','2022-02-16 06:08:54',NULL,NULL),(7,'h','$2a$10$CjBYn0M0AoDMHLOwImhsCu/x70Q7t6uRgYkzbkMReUnhUInqWQxBy','h@h.h','h',NULL,'h','0001-01-01','img/noimage.png','2022-02-16 06:11:30',NULL,NULL),(8,'qweoiqpo','$2a$10$7m15ac3QAA7pGk9JJtEAqOM6Z7iaaULDIwASzQdRyLc2geC87mtUu','qwoei@naver.com','test',NULL,'01083782111','2022-02-24','upload/657688a4-2c6e-4ed9-8400-dea0ec6d6eb9.png','2022-02-16 11:32:13',NULL,NULL),(16,'test111111','$2a$10$sQ6viL6CwlFC7/frVNTs4OVWizGlDI1eXaJpmLmSf8c.mJf6M4tmO','test1111@test1111.com','박상준',NULL,'0101111111','2022-02-15','img/noimage.png','2022-02-16 17:40:37',NULL,NULL),(24,'test123123','$2a$10$h9ZD5u7xdQAIHi2.YjWoOuijU5Kxr5lpa5KaeMNVorYH6j6MYuaH2','test123@test123.com','박상준',NULL,'01011111112','2022-02-01','img/noimage.png','2022-02-17 05:27:17',NULL,NULL),(31,'test1234','$2a$10$VlsHsMoF6/OXZAdVEfFkvenJCG46SOcFlEXwmN7JbVVZSFE74SMke','12345@gmail.com','박상준',NULL,'01011111113','2022-02-01','img/noimage.png','2022-02-17 05:42:13',NULL,NULL),(32,'qwerqwer','$2a$10$0A1vSzIA69sn4csmaE4e/./5Ka4fRsDPjdzFKaIFpWKYiPVYTXj3G','qwe@naver.com','test',NULL,'0101293101','2022-02-16','img/noimage.png','2022-02-17 06:15:40',NULL,NULL),(39,'qwiepqwope11','$2a$10$upT2k.11uZFNj8.1QybAfuExXeSJDSlqfz2.uHEJr9Zux1lcQHxm6','qwe1@naver.com','test2',NULL,'01078219371','2022-02-15','img/noimage.png','2022-02-17 06:17:04',NULL,NULL);
/*!40000 ALTER TABLE `USER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VOTER`
--

DROP TABLE IF EXISTS `VOTER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `VOTER` (
  `VOTE_ITEM_ID` int DEFAULT NULL,
  `USER_SEQ` int NOT NULL,
  KEY `FK_VOTE_ITEM_TO_VOTER_1` (`VOTE_ITEM_ID`),
  CONSTRAINT `FK_VOTE_ITEM_TO_VOTER_1` FOREIGN KEY (`VOTE_ITEM_ID`) REFERENCES `VOTE_ITEM` (`VOTE_ITEM_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VOTER`
--

LOCK TABLES `VOTER` WRITE;
/*!40000 ALTER TABLE `VOTER` DISABLE KEYS */;
INSERT INTO `VOTER` VALUES (18,2),(17,1),(19,4),(19,2),(23,1),(20,1),(25,31),(21,3),(30,2),(29,4),(29,3),(30,31),(29,1);
/*!40000 ALTER TABLE `VOTER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VOTE_ITEM`
--

DROP TABLE IF EXISTS `VOTE_ITEM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `VOTE_ITEM` (
  `VOTE_ITEM_ID` int NOT NULL AUTO_INCREMENT,
  `VOTE_ITEM_NAME` varchar(50) NOT NULL,
  `GAME_ID` int NOT NULL,
  `COUNT` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`VOTE_ITEM_ID`),
  KEY `FK_GAME_TO_VOTE_ITEM_1` (`GAME_ID`),
  CONSTRAINT `FK_GAME_TO_VOTE_ITEM_1` FOREIGN KEY (`GAME_ID`) REFERENCES `GAME` (`GAME_ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VOTE_ITEM`
--

LOCK TABLES `VOTE_ITEM` WRITE;
/*!40000 ALTER TABLE `VOTE_ITEM` DISABLE KEYS */;
INSERT INTO `VOTE_ITEM` VALUES (1,'항목1',1,0),(2,'항목2',1,0),(3,'항목3',1,0),(4,'항목1',2,0),(5,'항목2',2,0),(6,'항목3',2,0),(7,'항목1',4,0),(8,'항목2',4,0),(9,'항목3',4,0),(10,'항목1',5,0),(11,'항목2',5,0),(12,'항목3',5,0),(13,'항목12',6,0),(14,'항목233',6,0),(15,'항목3444',6,0),(16,'항목1',7,0),(17,'항목2',7,1),(18,'항목3',7,1),(19,'항목1',9,2),(20,'항목2',9,1),(21,'항목3',9,1),(22,'항목1',10,0),(23,'항목2',10,1),(24,'항목3',10,0),(25,'항목1',12,1),(26,'항목2',12,0),(27,'항목3',12,0),(28,'햄버거',13,0),(29,'보쌈',13,3),(30,'김치찌개',13,2),(31,'칼국수',13,0);
/*!40000 ALTER TABLE `VOTE_ITEM` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18  5:12:49
