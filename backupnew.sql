CREATE DATABASE  IF NOT EXISTS `springlivro` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `springlivro`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: springlivro
-- ------------------------------------------------------
-- Server version	5.6.19-log

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
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `senha` varchar(50) NOT NULL,
  `tipoacesso` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'douglasgerente','123','ROLE_GERENTE'),(2,'juliane','321','ROLE_VENDEDOR'),(4,'douglas','123','admin'),(6,'douglas','123','admin');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'springlivro'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-10-02  1:16:39
CREATE DATABASE  IF NOT EXISTS `sicis` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sicis`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: sicis
-- ------------------------------------------------------
-- Server version	5.6.19-log

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
-- Table structure for table `bairro`
--

DROP TABLE IF EXISTS `bairro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bairro` (
  `bai_id` int(11) NOT NULL AUTO_INCREMENT,
  `bai_cid_id` int(11) NOT NULL,
  `bai_descricao` varchar(45) NOT NULL,
  PRIMARY KEY (`bai_id`),
  KEY `fk_bai_cid_id_idx` (`bai_cid_id`),
  CONSTRAINT `fk_bai_cid_id` FOREIGN KEY (`bai_cid_id`) REFERENCES `cidade` (`cid_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cidade`
--

DROP TABLE IF EXISTS `cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cidade` (
  `cid_id` int(11) NOT NULL AUTO_INCREMENT,
  `cid_descricao` varchar(45) NOT NULL,
  `cid_estado` varchar(5) NOT NULL,
  PRIMARY KEY (`cid_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `cli_id` int(11) NOT NULL AUTO_INCREMENT,
  `cli_log_id` int(11) NOT NULL,
  `cli_numeroresidencial` varchar(50) NOT NULL,
  `cli_nome` varchar(45) NOT NULL,
  `cli_nasc` date NOT NULL,
  `cli_cor` varchar(20) NOT NULL,
  `cli_RG` varchar(10) DEFAULT NULL,
  `cli_CPF` varchar(50) DEFAULT NULL,
  `cli_CNS` varchar(50) NOT NULL,
  `cli_nomedamae` varchar(45) NOT NULL,
  `cli_nomedopai` varchar(45) NOT NULL,
  `cli_sexo` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`cli_id`),
  UNIQUE KEY `cli_CNS_UNIQUE` (`cli_CNS`),
  UNIQUE KEY `cli_CPF_UNIQUE` (`cli_CPF`),
  KEY `fk_cli_logadouro_id_idx` (`cli_log_id`),
  CONSTRAINT `fk_cli_log_id` FOREIGN KEY (`cli_log_id`) REFERENCES `logradouro` (`log_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `cliente_telefone`
--

DROP TABLE IF EXISTS `cliente_telefone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente_telefone` (
  `cli_tel_id` int(11) NOT NULL AUTO_INCREMENT,
  `cli_tel_cliente` int(11) NOT NULL,
  `cli_tel_telefone` varchar(50) NOT NULL,
  PRIMARY KEY (`cli_tel_id`),
  KEY `fk_cli_tel_cliente_idx` (`cli_tel_cliente`),
  KEY `fk_cli_tel_telefone_idx` (`cli_tel_telefone`),
  CONSTRAINT `fk_cli_tel_cliente` FOREIGN KEY (`cli_tel_cliente`) REFERENCES `cliente` (`cli_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `controle`
--

DROP TABLE IF EXISTS `controle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `controle` (
  `ctrl_id` int(11) NOT NULL AUTO_INCREMENT,
  `ctrl_des_Exa_id` int(11) NOT NULL,
  `ctrol_litimeUnitario` int(11) DEFAULT NULL,
  `ctrl_limiteValor` float DEFAULT NULL,
  PRIMARY KEY (`ctrl_id`),
  KEY `fk_ctrl_des_Exa_id_idx` (`ctrl_des_Exa_id`),
  CONSTRAINT `fk_ctrl_des_Exa_id` FOREIGN KEY (`ctrl_des_Exa_id`) REFERENCES `descricaoexames` (`des_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `descricaoexames`
--

DROP TABLE IF EXISTS `descricaoexames`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `descricaoexames` (
  `des_id` int(11) NOT NULL AUTO_INCREMENT,
  `des_nomeexame` varchar(45) NOT NULL,
  `des_categoria` varchar(45) NOT NULL,
  `des_codigoProcedimento` int(11) DEFAULT NULL,
  `des_valor` float DEFAULT NULL,
  PRIMARY KEY (`des_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `encaminhamento`
--

DROP TABLE IF EXISTS `encaminhamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `encaminhamento` (
  `enc_id` int(11) NOT NULL AUTO_INCREMENT,
  `enc_sol_id` int(11) NOT NULL,
  `enc_especialidade` varchar(25) NOT NULL,
  `enc_motivo` varchar(45) NOT NULL,
  PRIMARY KEY (`enc_id`),
  KEY `fk_enc_sol_id_idx` (`enc_sol_id`),
  CONSTRAINT `fk_enc_sol_id` FOREIGN KEY (`enc_sol_id`) REFERENCES `solicitacoes` (`sol_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `encaminhamento_examesrealizados`
--

DROP TABLE IF EXISTS `encaminhamento_examesrealizados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `encaminhamento_examesrealizados` (
  `real_id` int(11) NOT NULL AUTO_INCREMENT,
  `real_enc_id` int(11) NOT NULL,
  `real_exa_id` int(11) NOT NULL,
  PRIMARY KEY (`real_id`),
  KEY `fk_real_enc_id_idx` (`real_enc_id`),
  KEY `fk_real_exa_id_idx` (`real_exa_id`),
  CONSTRAINT `fk_real_enc_id` FOREIGN KEY (`real_enc_id`) REFERENCES `encaminhamento` (`enc_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_real_exa_id` FOREIGN KEY (`real_exa_id`) REFERENCES `descricaoexames` (`des_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `examesautorizados`
--

DROP TABLE IF EXISTS `examesautorizados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examesautorizados` (
  `aut_id` int(11) NOT NULL AUTO_INCREMENT,
  `aut_solExa_id` int(11) NOT NULL,
  `aut_profAgendamento` int(11) NOT NULL,
  `aut_localAgendado` int(11) DEFAULT NULL,
  `aut_dataRecebimento` date NOT NULL,
  `aut_data_horaAgendada` datetime NOT NULL,
  `aut_data_horaAtual` datetime NOT NULL,
  PRIMARY KEY (`aut_id`),
  KEY `fk_aut_solExa_id_idx` (`aut_solExa_id`),
  KEY `fk_aut_profAgendamento_idx` (`aut_profAgendamento`),
  KEY `fk_localAtendimento_id_idx` (`aut_localAgendado`),
  CONSTRAINT `fk_aut_profAgendamento` FOREIGN KEY (`aut_profAgendamento`) REFERENCES `profissional` (`pro_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_aut_solExa_id` FOREIGN KEY (`aut_solExa_id`) REFERENCES `solicitacaoexames` (`solExa_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_localAtendimento_id` FOREIGN KEY (`aut_localAgendado`) REFERENCES `localatendimento` (`local_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `localatendimento`
--

DROP TABLE IF EXISTS `localatendimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localatendimento` (
  `local_id` int(11) NOT NULL AUTO_INCREMENT,
  `local_log_id` int(11) NOT NULL,
  `local_descricao` varchar(45) NOT NULL,
  `local_numero` varchar(10) NOT NULL,
  `local_telefone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`local_id`),
  KEY `fk_local_log_id_idx` (`local_log_id`),
  CONSTRAINT `fk_local_log_id` FOREIGN KEY (`local_log_id`) REFERENCES `logradouro` (`log_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `logradouro`
--

DROP TABLE IF EXISTS `logradouro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logradouro` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `log_bai_id` int(11) NOT NULL,
  `log_CEP` int(11) DEFAULT NULL,
  `log_descricao` varchar(45) NOT NULL,
  PRIMARY KEY (`log_id`),
  KEY `fk_log_bai_id_idx` (`log_bai_id`),
  CONSTRAINT `fk_log_bai_id` FOREIGN KEY (`log_bai_id`) REFERENCES `bairro` (`bai_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `permissao`
--

DROP TABLE IF EXISTS `permissao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissao` (
  `permi_id` int(11) NOT NULL AUTO_INCREMENT,
  `permi_descricao` varchar(45) NOT NULL,
  `permi_roles` varchar(45) NOT NULL,
  `permi_titulo` varchar(50) NOT NULL,
  PRIMARY KEY (`permi_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissao`
--

LOCK TABLES `permissao` WRITE;
/*!40000 ALTER TABLE `permissao` DISABLE KEYS */;
INSERT INTO `permissao` VALUES (1,'Acesso Gerencial. Permissões Totais','ROLE_GERENTE','Gerente'),(2,'Perfil Escriturario. Permissões Limitadas','ROLE_ESCRITURARIO','Escriturario');
/*!40000 ALTER TABLE `permissao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profissional`
--

DROP TABLE IF EXISTS `profissional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profissional` (
  `pro_id` int(11) NOT NULL AUTO_INCREMENT,
  `pro_cli_id` int(11) NOT NULL,
  `pro_localatendimento_id` int(11) NOT NULL,
  `pro_permissoes_id` int(11) DEFAULT NULL,
  `pro_cargo` varchar(30) NOT NULL,
  `pro_documentacao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pro_id`),
  KEY `fk_pro_cli_id_idx` (`pro_cli_id`),
  KEY `fk_pro_permissoes_idx` (`pro_permissoes_id`),
  KEY `fk_pro_atendimento_id` (`pro_localatendimento_id`),
  CONSTRAINT `fk_pro_atendimento_id` FOREIGN KEY (`pro_localatendimento_id`) REFERENCES `localatendimento` (`local_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pro_cli_id` FOREIGN KEY (`pro_cli_id`) REFERENCES `cliente` (`cli_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pro_permissoes` FOREIGN KEY (`pro_permissoes_id`) REFERENCES `permissao` (`permi_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `prontuario`
--

DROP TABLE IF EXISTS `prontuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prontuario` (
  `pront_id` int(11) NOT NULL AUTO_INCREMENT,
  `pront_cliente_id` int(11) NOT NULL,
  `pront_ubs_id` int(11) NOT NULL,
  `pront_numeracao` varchar(50) NOT NULL,
  PRIMARY KEY (`pront_id`),
  KEY `fk_pront_cliente_id` (`pront_cliente_id`),
  KEY `fk_pront_usb_id` (`pront_ubs_id`),
  CONSTRAINT `fk_pront_cliente_id` FOREIGN KEY (`pront_cliente_id`) REFERENCES `cliente` (`cli_id`),
  CONSTRAINT `fk_pront_usb_id` FOREIGN KEY (`pront_ubs_id`) REFERENCES `localatendimento` (`local_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `solicitacaoexames`
--

DROP TABLE IF EXISTS `solicitacaoexames`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `solicitacaoexames` (
  `solExa_id` int(11) NOT NULL AUTO_INCREMENT,
  `solExa_numero_sol` int(11) NOT NULL,
  `solExa_des_id` int(11) NOT NULL,
  PRIMARY KEY (`solExa_id`),
  KEY `fk_solExa_numero_sol_idx` (`solExa_numero_sol`),
  KEY `fk_solExa_des_id_idx` (`solExa_des_id`),
  CONSTRAINT `fk_solExa_des_id` FOREIGN KEY (`solExa_des_id`) REFERENCES `descricaoexames` (`des_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_solExa_numero_sol` FOREIGN KEY (`solExa_numero_sol`) REFERENCES `solicitacoes` (`sol_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `solicitacoes`
--

DROP TABLE IF EXISTS `solicitacoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `solicitacoes` (
  `sol_id` int(11) NOT NULL AUTO_INCREMENT,
  `sol_cliente` int(11) NOT NULL,
  `sol_profissional` int(11) NOT NULL,
  `sol_datePedido` date DEFAULT NULL,
  PRIMARY KEY (`sol_id`),
  KEY `fk_exa_profissional_idx` (`sol_profissional`),
  KEY `fk_exa_cliente_idx` (`sol_cliente`),
  CONSTRAINT `fk_sol_cliente` FOREIGN KEY (`sol_cliente`) REFERENCES `cliente` (`cli_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sol_profissional` FOREIGN KEY (`sol_profissional`) REFERENCES `profissional` (`pro_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'sicis'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-10-02  1:16:39
