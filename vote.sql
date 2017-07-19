-- MySQL dump 10.13  Distrib 5.7.18, for Win64 (x86_64)
--
-- Host: localhost    Database: vote
-- ------------------------------------------------------
-- Server version	5.7.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `options`
--

DROP TABLE IF EXISTS `options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `options` (
  `oid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tid` int(11) NOT NULL,
  `content` varchar(255) NOT NULL,
  `counts` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`oid`),
  KEY `options_tid` (`tid`)
) ENGINE=MyISAM AUTO_INCREMENT=171 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `options`
--

LOCK TABLES `options` WRITE;
/*!40000 ALTER TABLE `options` DISABLE KEYS */;
INSERT INTO `options` VALUES (1,29,'ha1',0),(2,29,'ha2',0),(3,30,'ha11',0),(4,30,'ha22',0),(5,31,'ha22',0),(6,31,'ha11',0),(7,32,'ha11',0),(8,32,'ha2233',0),(9,33,'ha22334',0),(10,33,'ha1123',0),(11,34,'ha22334',0),(12,34,'ha1123',0),(13,35,'ha1123',0),(14,35,'ha22334',0),(15,39,'aa1123',0),(16,39,'aa22334',0),(17,40,'aa1123',0),(18,40,'aa22334',0),(19,41,'aa1123',0),(20,41,'aa22334',0),(27,45,'aa1123',0),(28,45,'aa22334',0),(29,46,'aa1123',0),(30,46,'aa22334',0),(100,65,'aa22334',0),(101,65,'aa1123',0),(102,66,'aa22334',0),(103,66,'aa1123',0);
/*!40000 ALTER TABLE `options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theme`
--

DROP TABLE IF EXISTS `theme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `theme` (
  `tid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `description` varchar(600) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `counts` int(10) unsigned DEFAULT '0',
  `votes_per_user` tinyint(3) unsigned DEFAULT '1',
  `uid` char(28) DEFAULT NULL,
  `anonymous` bit(1) NOT NULL DEFAULT b'0',
  `oid_list` varchar(800) DEFAULT NULL,
  PRIMARY KEY (`tid`),
  KEY `uid` (`uid`),
  FULLTEXT KEY `search` (`title`,`description`) /*!50100 WITH PARSER `ngram` */ 
) ENGINE=MyISAM AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theme`
--

LOCK TABLES `theme` WRITE;
/*!40000 ALTER TABLE `theme` DISABLE KEYS */;
INSERT INTO `theme` VALUES (1,'hello','aaaaaa','2017-07-20 00:00:00','2017-07-30 00:00:00',0,2,'zeffee','\0','[]'),(2,'hello',NULL,'2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,'zeffee234','\0',NULL),(3,'hello',NULL,'2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL,'\0',NULL),(4,'hello',NULL,'2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL,'\0',NULL),(5,'hello',NULL,'2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL,'\0',NULL),(6,'hello',NULL,'2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL,'\0',NULL),(7,'hello',NULL,'2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL,'\0',NULL),(8,'hello',NULL,'2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL,'\0',NULL),(9,'hello',NULL,'2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL,'\0',NULL),(10,'hello',NULL,'2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL,'\0',NULL),(11,'hello',NULL,'2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL,'\0',NULL),(12,'hello',NULL,'2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL,'\0',NULL),(13,'hello',NULL,'2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL,'\0',NULL),(14,'hello',NULL,'2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL,'\0',NULL),(15,'hello',NULL,'2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL,'\0',NULL),(16,'hello',NULL,'2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL,'\0',NULL),(17,'hello','北京信元电信维护有限责任公司','2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL,'\0',NULL),(18,'hello','北京诺华制药有限公司','2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL,'\0',NULL),(19,'hello','北京未来科技城开发建设有限 公司','2017-05-30 00:00:00','2017-07-30 00:00:00',0,1,NULL,'\0',NULL),(20,'hello','北京低碳清洁能源研究所','2017-05-30 00:00:00','2017-07-30 00:00:00',0,2,NULL,'\0',NULL),(21,'hello','阿莫斯特环保科技（北京）有限公司','2017-05-30 00:00:00','2017-07-30 00:00:00',0,1,NULL,'\0',NULL),(22,'hello',' 北京奔驰汽车有限公司','2017-05-30 00:00:00','2017-07-30 00:00:00',0,1,NULL,'\0',NULL),(23,'hello','中新航天科技有限公司','2017-05-30 00:00:00','2017-07-30 00:00:00',0,1,NULL,'\0',NULL),(24,'hello','松下电气机气（北京）有限公司','2017-05-30 00:00:00','2017-07-30 00:00:00',0,1,NULL,'\0',NULL),(25,'hello','北京松下照明光源有限公司','2017-05-30 00:00:00','2017-07-30 00:00:00',0,1,NULL,'\0',NULL),(27,'hello345','北京·松下电子部品有限公司','2017-05-20 00:00:00','2017-07-30 00:00:00',0,33,NULL,'\0',NULL),(28,'hello1','北京·松下彩色显象管有限公司','2017-05-20 00:00:00','2017-07-30 00:00:00',0,33,NULL,'\0',NULL),(29,'hello1','中国航天工业科学技术咨询有限公司','2017-05-20 00:00:00','2017-07-30 00:00:00',0,33,NULL,'\0',NULL),(30,'hello1','北京畅捷科技有限公司','2017-05-20 00:00:00','2017-07-30 00:00:00',0,33,'z','\0','[3,4]'),(31,'hello1','畅捷通信息技术股份有限公司','2017-06-13 12:00:00','2017-07-30 00:00:00',0,3,'zeffee','\0','[5,6]'),(32,'hello1','北京畅捷通讯 有限公司','2017-06-13 08:00:00','2017-07-30 08:00:00',0,3,'z','\0',NULL),(34,'hello1','瞬联讯通科技（北京）有限公司','2016-10-01 23:45:30','2016-10-01 23:45:30',0,3,'z','\0',NULL),(35,'hello134','凡客诚品（北京）科技有限公司','2016-10-01 23:45:30','2016-10-01 23:45:30',0,3,'z','\0',NULL),(65,'aaa1','北京凡客尚品电子商务有限公司','2016-10-01 15:45:30','2016-10-02 15:45:30',0,3,'z','',NULL),(66,'aaa1','芜湖美的厨卫电气制造有限公司','2016-10-01 15:45:30','2016-10-02 15:45:30',0,3,'z','',NULL);
/*!40000 ALTER TABLE `theme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `name` varchar(50) NOT NULL,
  `uid` char(28) NOT NULL,
  `photo` varchar(200) NOT NULL,
  UNIQUE KEY `openid` (`uid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('zeffee chan','zeffee','http://wx.qlogo.cn/mmhead/Mjzdia7evAzwzxichrTjAEwK7BLII2YIfMRiaMudFpyWM9yoNFOaH9KTA/0');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `votes`
--

DROP TABLE IF EXISTS `votes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `votes` (
  `vid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `uid` char(28) NOT NULL,
  `oid` int(10) unsigned NOT NULL,
  `tid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`vid`),
  KEY `show_my_vote` (`uid`,`tid`),
  KEY `oid` (`oid`)
) ENGINE=MyISAM AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `votes`
--

LOCK TABLES `votes` WRITE;
/*!40000 ALTER TABLE `votes` DISABLE KEYS */;
INSERT INTO `votes` VALUES (3,'zeffee',33,29),(4,'zeffee',66,29);
/*!40000 ALTER TABLE `votes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-19 21:43:12
