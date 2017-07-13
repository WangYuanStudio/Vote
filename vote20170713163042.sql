-- MySQL dump 10.13  Distrib 5.5.40, for Win64 (x86)
--
-- Host: localhost    Database: vote
-- ------------------------------------------------------
-- Server version	5.5.40

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
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `options`
--

LOCK TABLES `options` WRITE;
/*!40000 ALTER TABLE `options` DISABLE KEYS */;
INSERT INTO `options` VALUES (1,29,'ha1',0),(2,29,'ha2',0),(3,30,'ha11',0),(4,30,'ha22',0),(5,31,'ha22',0),(6,31,'ha11',0),(7,32,'ha11',0),(8,32,'ha2233',0),(9,33,'ha22334',0),(10,33,'ha1123',0),(11,34,'ha22334',0),(12,34,'ha1123',0),(13,35,'ha1123',0),(14,35,'ha22334',0);
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
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `counts` int(10) unsigned DEFAULT '0',
  `votes_per_user` tinyint(3) unsigned DEFAULT '1',
  `uid` char(28) DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=MyISAM AUTO_INCREMENT=36 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theme`
--

LOCK TABLES `theme` WRITE;
/*!40000 ALTER TABLE `theme` DISABLE KEYS */;
INSERT INTO `theme` VALUES (1,'hello','2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL),(2,'hello','2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL),(3,'hello','2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL),(4,'hello','2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL),(5,'hello','2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL),(6,'hello','2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL),(7,'hello','2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL),(8,'hello','2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL),(9,'hello','2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL),(10,'hello','2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL),(11,'hello','2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL),(12,'hello','2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL),(13,'hello','2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL),(14,'hello','2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL),(15,'hello','2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL),(16,'hello','2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL),(17,'hello','2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL),(18,'hello','2017-05-30 00:00:00','2017-07-30 00:00:00',0,0,NULL),(19,'hello','2017-05-30 00:00:00','2017-07-30 00:00:00',0,1,NULL),(20,'hello','2017-05-30 00:00:00','2017-07-30 00:00:00',0,2,NULL),(21,'hello','2017-05-30 00:00:00','2017-07-30 00:00:00',0,1,NULL),(22,'hello','2017-05-30 00:00:00','2017-07-30 00:00:00',0,1,NULL),(23,'hello','2017-05-30 00:00:00','2017-07-30 00:00:00',0,1,NULL),(24,'hello','2017-05-30 00:00:00','2017-07-30 00:00:00',0,1,NULL),(25,'hello','2017-05-30 00:00:00','2017-07-30 00:00:00',0,1,NULL),(28,'hello1','2017-05-20 00:00:00','2017-07-30 00:00:00',0,33,NULL),(27,'hello345','2017-05-20 00:00:00','2017-07-30 00:00:00',0,33,NULL),(29,'hello1','2017-05-20 00:00:00','2017-07-30 00:00:00',0,33,NULL),(30,'hello1','2017-05-20 00:00:00','2017-07-30 00:00:00',0,33,NULL),(31,'hello1','2017-06-13 00:00:00','2017-07-30 00:00:00',0,3,NULL),(32,'hello1','2017-06-13 08:00:00','2017-07-30 08:00:00',0,3,NULL),(34,'hello1','2016-10-01 23:45:30','2016-10-01 23:45:30',0,3,'qwertyui71234567890123456789'),(35,'hello134','2016-10-01 23:45:30','2016-10-01 23:45:30',0,3,'qwertyui71234567890123456789');
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
  UNIQUE KEY `openid` (`uid`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
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
  `uid` int(10) unsigned NOT NULL,
  `oid` int(10) unsigned NOT NULL,
  `tid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`vid`),
  KEY `show_my_vote` (`uid`,`tid`),
  KEY `oid` (`oid`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `votes`
--

LOCK TABLES `votes` WRITE;
/*!40000 ALTER TABLE `votes` DISABLE KEYS */;
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

-- Dump completed on 2017-07-13 16:30:44
