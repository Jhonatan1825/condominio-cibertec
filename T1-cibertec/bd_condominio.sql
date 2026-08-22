CREATE DATABASE  IF NOT EXISTS `db_condominio` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_condominio`;
-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: db_condominio
-- ------------------------------------------------------
-- Server version	8.4.10

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
-- Table structure for table `cuota_mensual`
--

DROP TABLE IF EXISTS `cuota_mensual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuota_mensual` (
  `id_cuota_mensual` int NOT NULL AUTO_INCREMENT,
  `id_departamento` int NOT NULL,
  `monto_base` decimal(10,2) NOT NULL,
  `monto_mora` decimal(10,2) DEFAULT '0.00',
  `monto_total` decimal(10,2) NOT NULL,
  `fecha_emision` date NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  `estado` enum('PENDIENTE','PAGADO','VENCIDO') DEFAULT 'PENDIENTE',
  PRIMARY KEY (`id_cuota_mensual`),
  KEY `fk_cuota_departamento` (`id_departamento`),
  CONSTRAINT `fk_cuota_departamento` FOREIGN KEY (`id_departamento`) REFERENCES `departamento` (`id_departamento`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuota_mensual`
--

LOCK TABLES `cuota_mensual` WRITE;
/*!40000 ALTER TABLE `cuota_mensual` DISABLE KEYS */;
INSERT INTO `cuota_mensual` VALUES (1,1,250.00,0.00,250.00,'2026-06-01','2026-06-15','PENDIENTE'),(2,2,250.00,20.00,270.00,'2026-06-01','2026-06-15','VENCIDO'),(3,3,300.00,0.00,300.00,'2026-06-01','2026-06-15','PAGADO'),(4,4,280.00,0.00,280.00,'2026-06-01','2026-06-15','PENDIENTE');
/*!40000 ALTER TABLE `cuota_mensual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamento`
--

DROP TABLE IF EXISTS `departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departamento` (
  `id_departamento` int NOT NULL AUTO_INCREMENT,
  `numero` varchar(20) NOT NULL,
  `piso` int NOT NULL,
  `estado` enum('LIBRE','OCUPADO','INACTIVO') DEFAULT 'LIBRE',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_departamento`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamento`
--

LOCK TABLES `departamento` WRITE;
/*!40000 ALTER TABLE `departamento` DISABLE KEYS */;
INSERT INTO `departamento` VALUES (1,'301',3,'OCUPADO','2026-08-13 04:43:51'),(2,'302',3,'OCUPADO','2026-08-13 04:43:51'),(3,'303',3,'OCUPADO','2026-08-13 04:43:51'),(4,'304',3,'OCUPADO','2026-08-13 04:43:51'),(5,'401',4,'OCUPADO','2026-08-13 04:43:51'),(6,'402',4,'OCUPADO','2026-08-13 04:43:51'),(7,'403',4,'OCUPADO','2026-08-13 04:43:51'),(8,'404',4,'OCUPADO','2026-08-13 04:43:51'),(9,'501',5,'OCUPADO','2026-08-13 04:43:51'),(10,'502',5,'OCUPADO','2026-08-13 04:43:51'),(11,'101',1,'OCUPADO','2026-08-13 04:43:51'),(12,'102',1,'OCUPADO','2026-08-13 04:43:51'),(13,'103',1,'OCUPADO','2026-08-13 04:43:51'),(14,'104',1,'OCUPADO','2026-08-13 04:43:51'),(15,'201',2,'OCUPADO','2026-08-13 04:43:51'),(16,'202',2,'OCUPADO','2026-08-13 04:43:51'),(17,'203',2,'OCUPADO','2026-08-13 04:43:51'),(18,'204',2,'OCUPADO','2026-08-13 04:43:51'),(19,'301',3,'OCUPADO','2026-08-13 04:43:51'),(20,'302',3,'OCUPADO','2026-08-13 04:43:51'),(21,'101',1,'OCUPADO','2026-08-13 04:43:51'),(22,'102',1,'OCUPADO','2026-08-13 04:43:51'),(23,'103',1,'OCUPADO','2026-08-13 04:43:51'),(24,'104',1,'OCUPADO','2026-08-13 04:43:51'),(25,'201',2,'OCUPADO','2026-08-13 04:43:51'),(26,'202',2,'OCUPADO','2026-08-13 04:43:51'),(27,'203',2,'OCUPADO','2026-08-13 04:43:51'),(28,'204',2,'OCUPADO','2026-08-13 04:43:51'),(29,'301',3,'OCUPADO','2026-08-13 04:43:51'),(30,'302',3,'OCUPADO','2026-08-13 04:43:51'),(31,'303',3,'OCUPADO','2026-08-13 04:43:51'),(32,'304',3,'OCUPADO','2026-08-13 04:43:51'),(33,'401',4,'OCUPADO','2026-08-13 04:43:51'),(34,'402',4,'OCUPADO','2026-08-13 04:43:51'),(35,'403',4,'OCUPADO','2026-08-13 04:43:51'),(36,'404',4,'OCUPADO','2026-08-13 04:43:51'),(37,'501',5,'OCUPADO','2026-08-13 04:43:51'),(38,'502',5,'OCUPADO','2026-08-13 04:43:51'),(39,'503',5,'OCUPADO','2026-08-13 04:43:51'),(40,'504',5,'OCUPADO','2026-08-13 04:43:51');
/*!40000 ALTER TABLE `departamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamento_propietario`
--

DROP TABLE IF EXISTS `departamento_propietario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departamento_propietario` (
  `id_departamento_propietario` int NOT NULL AUTO_INCREMENT,
  `id_departamento` int NOT NULL,
  `id_propietario` int NOT NULL,
  `fecha_adquisicion` date NOT NULL,
  `estado` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id_departamento_propietario`),
  KEY `fk_dep_prop_departamento` (`id_departamento`),
  KEY `fk_dep_prop_usuario` (`id_propietario`),
  CONSTRAINT `fk_dep_prop_departamento` FOREIGN KEY (`id_departamento`) REFERENCES `departamento` (`id_departamento`),
  CONSTRAINT `fk_dep_prop_usuario` FOREIGN KEY (`id_propietario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamento_propietario`
--

LOCK TABLES `departamento_propietario` WRITE;
/*!40000 ALTER TABLE `departamento_propietario` DISABLE KEYS */;
INSERT INTO `departamento_propietario` VALUES (1,1,2,'2025-01-10',1),(2,2,2,'2025-02-12',1),(3,3,3,'2025-03-15',1),(4,4,3,'2025-04-20',1);
/*!40000 ALTER TABLE `departamento_propietario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pago_mantenimiento`
--

DROP TABLE IF EXISTS `pago_mantenimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pago_mantenimiento` (
  `id_pago_mantenimiento` int NOT NULL AUTO_INCREMENT,
  `id_cuota_mensual` int NOT NULL,
  `id_usuario` int NOT NULL,
  `metodo_pago` varchar(30) NOT NULL,
  `monto` decimal(10,2) NOT NULL,
  `fecha_pago` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` varchar(20) NOT NULL DEFAULT 'REGISTRADO',
  PRIMARY KEY (`id_pago_mantenimiento`),
  UNIQUE KEY `uk_pago_cuota` (`id_cuota_mensual`),
  KEY `fk_pago_usuario` (`id_usuario`),
  CONSTRAINT `fk_pago_cuota` FOREIGN KEY (`id_cuota_mensual`) REFERENCES `cuota_mensual` (`id_cuota_mensual`),
  CONSTRAINT `fk_pago_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `chk_pago_estado` CHECK ((`estado` in (_utf8mb4'REGISTRADO',_utf8mb4'ANULADO'))),
  CONSTRAINT `chk_pago_metodo_pago` CHECK ((`metodo_pago` in (_utf8mb4'EFECTIVO',_utf8mb4'TRANSFERENCIA',_utf8mb4'YAPE',_utf8mb4'PLIN')))
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pago_mantenimiento`
--

LOCK TABLES `pago_mantenimiento` WRITE;
/*!40000 ALTER TABLE `pago_mantenimiento` DISABLE KEYS */;
INSERT INTO `pago_mantenimiento` VALUES (1,3,3,'TRANSFERENCIA',300.00,'2026-08-13 04:43:51','REGISTRADO');
/*!40000 ALTER TABLE `pago_mantenimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registro_acceso`
--

DROP TABLE IF EXISTS `registro_acceso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registro_acceso` (
  `id_registro` int NOT NULL AUTO_INCREMENT,
  `id_visitante` int NOT NULL,
  `id_departamento` int NOT NULL,
  `id_trabajador` int NOT NULL,
  `fecha_hora_ingreso` datetime NOT NULL,
  `fecha_hora_salida` datetime DEFAULT NULL,
  PRIMARY KEY (`id_registro`),
  KEY `fk_registro_visitante` (`id_visitante`),
  KEY `fk_registro_departamento` (`id_departamento`),
  KEY `fk_registro_trabajador` (`id_trabajador`),
  CONSTRAINT `fk_registro_departamento` FOREIGN KEY (`id_departamento`) REFERENCES `departamento` (`id_departamento`),
  CONSTRAINT `fk_registro_trabajador` FOREIGN KEY (`id_trabajador`) REFERENCES `trabajador` (`id_trabajador`),
  CONSTRAINT `fk_registro_visitante` FOREIGN KEY (`id_visitante`) REFERENCES `visitante` (`id_visitante`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registro_acceso`
--

LOCK TABLES `registro_acceso` WRITE;
/*!40000 ALTER TABLE `registro_acceso` DISABLE KEYS */;
/*!40000 ALTER TABLE `registro_acceso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva` (
  `id_reserva` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int NOT NULL,
  `fecha_reserva` date NOT NULL,
  `hora_inicio` time NOT NULL,
  `hora_fin` time NOT NULL,
  `estado` enum('PENDIENTE','APROBADA','RECHAZADA','FINALIZADA') DEFAULT 'PENDIENTE',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_reserva`),
  KEY `fk_reserva_usuario` (`id_usuario`),
  CONSTRAINT `fk_reserva_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
INSERT INTO `reserva` VALUES (1,2,'2026-08-13','10:00:00','12:00:00','APROBADA','2026-08-13 04:43:51'),(2,4,'2026-08-13','11:00:00','13:00:00','PENDIENTE','2026-08-13 04:43:51'),(3,3,'2026-08-14','18:00:00','21:00:00','PENDIENTE','2026-08-13 04:43:51'),(4,5,'2026-08-15','12:00:00','14:00:00','RECHAZADA','2026-08-13 04:43:51');
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id_rol` int NOT NULL AUTO_INCREMENT,
  `nombre_rol` varchar(50) NOT NULL,
  `estado` tinyint(1) DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_rol`),
  UNIQUE KEY `nombre_rol` (`nombre_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'ADMINISTRADOR',1,'2026-08-13 04:43:50'),(2,'PROPIETARIO',1,'2026-08-13 04:43:50'),(3,'INQUILINO',1,'2026-08-13 04:43:50'),(4,'VIGILANTE',1,'2026-08-13 04:43:50');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trabajador`
--

DROP TABLE IF EXISTS `trabajador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trabajador` (
  `id_trabajador` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `dni` char(8) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `correo` varchar(150) NOT NULL,
  `cargo` varchar(50) NOT NULL,
  `turno` varchar(20) NOT NULL,
  `estado` tinyint(1) DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_trabajador`),
  UNIQUE KEY `dni` (`dni`),
  UNIQUE KEY `correo` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trabajador`
--

LOCK TABLES `trabajador` WRITE;
/*!40000 ALTER TABLE `trabajador` DISABLE KEYS */;
INSERT INTO `trabajador` VALUES (1,'Luis','Ramirez','73322111','944888777','vigilante@correo.com','VIGILANTE','NOCHE',1,'2026-08-13 04:43:50','2026-08-13 04:43:50');
/*!40000 ALTER TABLE `trabajador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `id_rol` int NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `dni` char(8) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `correo` varchar(150) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `estado` tinyint(1) DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `dni` (`dni`),
  UNIQUE KEY `correo` (`correo`),
  KEY `fk_usuario_rol` (`id_rol`),
  CONSTRAINT `fk_usuario_rol` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,1,'Jean','Angoma','71234567','999111222','admin@condominio.com','123456',1,'2026-08-13 04:43:50','2026-08-13 04:43:50'),(2,2,'Carlos','Perez','74561234','988777666','carlos@correo.com','123456',1,'2026-08-13 04:43:50','2026-08-13 04:43:50'),(3,2,'Maria','Torres','76543210','977555444','maria@correo.com','123456',1,'2026-08-13 04:43:50','2026-08-13 04:43:50'),(4,3,'Ana','Lopez','70112233','955444333','ana@correo.com','123456',1,'2026-08-13 04:43:50','2026-08-13 04:43:50'),(5,3,'Pedro','Castillo','70998877','944333222','pedro@correo.com','123456',1,'2026-08-13 04:43:50','2026-08-13 04:43:50'),(6,4,'Luis','Ramirez','73322111','944888777','vigilante@correo.com','123456',1,'2026-08-13 04:43:50','2026-08-13 04:43:50'),(7,3,'Juan','Perez','12345678','999888777','prueba@condominio.com','$2a$10$35fOFuW40f10c3uMl6yBGuZ.eQLCeGQyCK9oQmURnOt14bu7ll5/e',1,'2026-08-14 01:29:21','2026-08-14 01:29:21'),(8,4,'Luis','Ramirez','87654321','988777666','vigilante@condominio.com','$2a$10$qCA/83I5nrPcmv/GlvlgS.0NpGoHLkGa.0i.IGtMD2UY9.lF3SDMm',1,'2026-08-14 01:58:32','2026-08-14 01:58:32');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visitante`
--

DROP TABLE IF EXISTS `visitante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `visitante` (
  `id_visitante` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `dni` varchar(20) NOT NULL,
  `motivo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_visitante`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visitante`
--

LOCK TABLES `visitante` WRITE;
/*!40000 ALTER TABLE `visitante` DISABLE KEYS */;
/*!40000 ALTER TABLE `visitante` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-08-21 22:31:55
