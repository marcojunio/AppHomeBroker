CREATE DATABASE  IF NOT EXISTS `home_broker` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `home_broker`;
-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: home_broker
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `carteira`
--

DROP TABLE IF EXISTS `carteira`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carteira` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantidade` int DEFAULT NULL,
  `prefixo_acao` varchar(15) DEFAULT NULL,
  `Conta_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Carteira_Conta1_idx` (`Conta_id`),
  CONSTRAINT `fk_Carteira_Conta1` FOREIGN KEY (`Conta_id`) REFERENCES `conta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carteira`
--

LOCK TABLES `carteira` WRITE;
/*!40000 ALTER TABLE `carteira` DISABLE KEYS */;
INSERT INTO `carteira` VALUES (2,10,'EMBR3',8),(3,15,'VALE3',9);
/*!40000 ALTER TABLE `carteira` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conta`
--

DROP TABLE IF EXISTS `conta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `saldo` double DEFAULT NULL,
  `Usuario_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Conta_Usuario_idx` (`Usuario_id`),
  CONSTRAINT `fk_Conta_Usuario` FOREIGN KEY (`Usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conta`
--

LOCK TABLES `conta` WRITE;
/*!40000 ALTER TABLE `conta` DISABLE KEYS */;
INSERT INTO `conta` VALUES (1,3.4500000000116415,1),(2,0,2),(7,4023.8650000000002,7),(8,802,8),(9,13404,9);
/*!40000 ALTER TABLE `conta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimentacao`
--

DROP TABLE IF EXISTS `movimentacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimentacao` (
  `id` int NOT NULL AUTO_INCREMENT,
  `valor` double DEFAULT NULL,
  `Conta_id` int NOT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `data` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Movimentacao_Conta1_idx` (`Conta_id`),
  CONSTRAINT `fk_Movimentacao_Conta1` FOREIGN KEY (`Conta_id`) REFERENCES `conta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimentacao`
--

LOCK TABLES `movimentacao` WRITE;
/*!40000 ALTER TABLE `movimentacao` DISABLE KEYS */;
INSERT INTO `movimentacao` VALUES (1,50000,1,'DEPOSITO','2021-06-18'),(2,5000,1,'SAQUE','2021-06-18'),(3,5000,1,'SAQUE','2021-06-18'),(4,300,1,'SAQUE','2021-06-18'),(5,200,1,'SAQUE','2021-06-18'),(6,4000,1,'SAQUE','2021-06-18'),(7,2000,1,'DEPÓSITO','2021-06-18'),(8,3000,1,'DEPÓSITO','2021-06-18'),(9,1000,1,'DEPÓSITO','2021-06-18'),(10,5492,1,'SAQUE','2021-06-18'),(11,2000,1,'SAQUE','2021-06-18'),(12,123,1,'SAQUE','2021-06-18'),(13,1090.9,1,'COMPRA DA AÇÃO VALE3','2021-06-23'),(14,546.55,1,'COMPRA DA AÇÃO VALE3','2021-06-23'),(15,20000,1,'SAQUE','2021-06-23'),(16,2000,1,'DEPÓSITO','2021-06-23'),(17,1089,1,'COMPRA DA AÇÃO VALE3','2021-06-23'),(18,15,7,'TAXA DE CORRETAGEM SOB COMPRA','2021-06-23'),(19,2116.3999999999996,1,'VENDA DA AÇÃO VALE3','2021-06-23'),(20,15,7,'TAXA DE CORRETAGEM SOB VENDA','2021-06-23'),(21,500000,1,'DEPÓSITO','2021-06-23'),(22,28037.5,1,'COMPRA DA AÇÃO VALE3','2021-06-23'),(23,1412.085,7,'TAXA DE CORRETAGEM SOB COMPRA','2021-06-23'),(24,10996,1,'COMPRA DA AÇÃO VALE3','2021-06-23'),(25,560.0100000000001,7,'TAXA DE CORRETAGEM SOB COMPRA','2021-06-23'),(26,539.2,1,'COMPRA DA AÇÃO VALE3 QUANTIDADE: 5','2021-06-28'),(27,15,7,'TAXA DE CORRETAGEM SOB COMPRA','2021-06-28'),(28,39031.2,1,'VENDA DA AÇÃO VALE3 QUANTIDADE: 360','2021-06-28'),(29,1961.77,7,'TAXA DE CORRETAGEM SOB VENDA','2021-06-28'),(30,514730,1,'SAQUE','2021-06-28'),(31,20000,8,'DEPÓSITO','2021-06-28'),(32,400,8,'COMPRA DA AÇÃO EMBR3 QUANTIDADE: 20','2021-06-28'),(33,202,8,'VENDA DA AÇÃO EMBR3 QUANTIDADE: 10','2021-06-28'),(34,15,7,'TAXA DE CORRETAGEM SOB VENDA','2021-06-28'),(35,19000,8,'SAQUE','2021-06-28'),(36,20000,9,'DEPÓSITO','2021-06-28'),(37,5000,9,'SAQUE','2021-06-28'),(38,1063.5,9,'COMPRA DA AÇÃO VALE3 QUANTIDADE: 10','2021-06-28'),(39,530.2,9,'VENDA DA AÇÃO VALE3 QUANTIDADE: 5','2021-06-28'),(40,15,7,'TAXA DE CORRETAGEM SOB VENDA','2021-06-28'),(41,1062.7,9,'COMPRA DA AÇÃO VALE3 QUANTIDADE: 10','2021-06-28'),(42,15,7,'TAXA DE CORRETAGEM SOB COMPRA','2021-06-28');
/*!40000 ALTER TABLE `movimentacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operacao`
--

DROP TABLE IF EXISTS `operacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operacao` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantidade` int DEFAULT NULL,
  `data` date DEFAULT NULL,
  `valor_unitario` double DEFAULT NULL,
  `tipo` varchar(15) DEFAULT NULL,
  `Carteira_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Operacao_Carteira1_idx` (`Carteira_id`),
  CONSTRAINT `fk_Operacao_Carteira1` FOREIGN KEY (`Carteira_id`) REFERENCES `carteira` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operacao`
--

LOCK TABLES `operacao` WRITE;
/*!40000 ALTER TABLE `operacao` DISABLE KEYS */;
INSERT INTO `operacao` VALUES (9,20,'2021-06-28',20,'COMPRA',2),(10,10,'2021-06-28',20.2,'VENDA',2),(11,10,'2021-06-28',106.35,'COMPRA',3),(12,5,'2021-06-28',106.04,'VENDA',3),(13,10,'2021-06-28',106.27,'COMPRA',3);
/*!40000 ALTER TABLE `operacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `sobrenome` varchar(45) DEFAULT NULL,
  `profissao` varchar(45) DEFAULT NULL,
  `endereco` varchar(45) DEFAULT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  `login` varchar(45) DEFAULT NULL,
  `senha` varchar(45) DEFAULT NULL,
  `admin` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Gabriel','Sampaio','Estudante','Rua Egberto Hallais Franca','12101447606','gabrielsampaio','202cb962ac59075b964b07152d234b70',0),(2,'Teste','123','123','Rua Egberto Hallais Franca','123','123','202cb962ac59075b964b07152d234b70',0),(7,'Daves','Martins','Professor','Av Andradas','120301230103','daves','202cb962ac59075b964b07152d234b70',1),(8,'Marcos','André','Programador','Av Andradas','12101447608','marcosandre','202cb962ac59075b964b07152d234b70',0),(9,'Zezin','Ze','Estudante','Juiz Fora','12030123','zezin','202cb962ac59075b964b07152d234b70',0);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'home_broker'
--

--
-- Dumping routines for database 'home_broker'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-28 16:25:22
