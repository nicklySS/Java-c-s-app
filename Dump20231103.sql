-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: furniture
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `furniture`
--

DROP TABLE IF EXISTS `furniture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `furniture` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` char(100) NOT NULL,
  `furType_id` int DEFAULT NULL,
  `material` char(100) NOT NULL,
  `color` char(100) NOT NULL,
  `doors` int DEFAULT NULL,
  `installation_type` char(100) NOT NULL,
  `width` int NOT NULL,
  `height` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `furType_id` (`furType_id`),
  CONSTRAINT `furniture_ibfk_1` FOREIGN KEY (`furType_id`) REFERENCES `furtype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `furniture`
--

LOCK TABLES `furniture` WRITE;
/*!40000 ALTER TABLE `furniture` DISABLE KEYS */;
INSERT INTO `furniture` VALUES (1,'Горизонт мебель лайт 900',3,'ЛДСП','Белый с черным',2,'Напольный',90,205),(2,'Обеденный стол NN мебель СО 1',1,'ЛДСП','Темное дерево',NULL,'Напольный',60,74),(3,'Обеденный стол Артём-Мебель СН-005.011',1,'ДСП','Светлое дерево(песочный)',NULL,'Напольный',60,75),(4,'Стул Mio Tesoro Бари SC-001',2,'Пластик/Сталь/Дерево','Черный',NULL,'Напольный',46,83),(5,'Стендмебель Гармония KP-601',7,'ЛДСП/ДВП','Светлое дерево',NULL,'Напольный',165,203);
/*!40000 ALTER TABLE `furniture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `furtype`
--

DROP TABLE IF EXISTS `furtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `furtype` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` char(100) NOT NULL,
  `descr` char(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `furtype`
--

LOCK TABLES `furtype` WRITE;
/*!40000 ALTER TABLE `furtype` DISABLE KEYS */;
INSERT INTO `furtype` VALUES (1,'Стол','Здесь идет описание данного типа мебели'),(2,'Стул','Здесь идет описание данного типа мебели'),(3,'Шкаф','Здесь идет описание данного типа мебели'),(7,'Кровать','Здесь идет описание данного типа мебели');
/*!40000 ALTER TABLE `furtype` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-03 11:03:00
