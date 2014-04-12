-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.6.10


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema geocaching
--

CREATE DATABASE IF NOT EXISTS geocaching;
USE geocaching;

--
-- Definition of table `aviso`
--

DROP TABLE IF EXISTS `aviso`;
CREATE TABLE `aviso` (
  `usuario_idUsuarioEmisor` int(11) NOT NULL,
  `usuario_idUsuarioReceptor` int(11) NOT NULL,
  `titulo` varchar(45) NOT NULL,
  `mensaje` varchar(500) NOT NULL,
  `fecha_enviado` date NOT NULL,
  PRIMARY KEY (`usuario_idUsuarioEmisor`,`usuario_idUsuarioReceptor`),
  KEY `fk_usuario_has_usuario_usuario2_idx` (`usuario_idUsuarioReceptor`),
  KEY `fk_usuario_has_usuario_usuario1_idx` (`usuario_idUsuarioEmisor`),
  CONSTRAINT `fk_usuario_has_usuario_usuario1` FOREIGN KEY (`usuario_idUsuarioEmisor`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_has_usuario_usuario2` FOREIGN KEY (`usuario_idUsuarioReceptor`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `aviso`
--

/*!40000 ALTER TABLE `aviso` DISABLE KEYS */;
/*!40000 ALTER TABLE `aviso` ENABLE KEYS */;


--
-- Definition of table `comentario`
--

DROP TABLE IF EXISTS `comentario`;
CREATE TABLE `comentario` (
  `idComentario` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(50) NOT NULL,
  `texto` varchar(500) DEFAULT NULL,
  `fechaPublicacion` date NOT NULL,
  `fechaUltModificacion` date DEFAULT NULL,
  `fechaBorrado` date DEFAULT NULL,
  `tesoro_idTesoro` int(11) NOT NULL,
  `usuario_idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idComentario`),
  KEY `fk_comentario_tesoro1_idx` (`tesoro_idTesoro`),
  KEY `fk_comentario_usuario1_idx` (`usuario_idUsuario`),
  CONSTRAINT `fk_comentario_tesoro1` FOREIGN KEY (`tesoro_idTesoro`) REFERENCES `tesoro` (`idTesoro`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comentario_usuario1` FOREIGN KEY (`usuario_idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comentario`
--

/*!40000 ALTER TABLE `comentario` DISABLE KEYS */;
/*!40000 ALTER TABLE `comentario` ENABLE KEYS */;


--
-- Definition of table `descubrimiento`
--

DROP TABLE IF EXISTS `descubrimiento`;
CREATE TABLE `descubrimiento` (
  `usuario_idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `tesoro_idTesoro` int(11) NOT NULL,
  `fechaDescubrimiento` date NOT NULL,
  `kmRecorridos` int(11) DEFAULT NULL,
  `dificultad` int(11) DEFAULT NULL,
  PRIMARY KEY (`usuario_idUsuario`,`tesoro_idTesoro`),
  KEY `fk_usuario_has_tesoro_tesoro1_idx` (`tesoro_idTesoro`),
  KEY `fk_usuario_has_tesoro_usuario_idx` (`usuario_idUsuario`),
  CONSTRAINT `fk_usuario_has_tesoro_tesoro10` FOREIGN KEY (`tesoro_idTesoro`) REFERENCES `tesoro` (`idTesoro`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_has_tesoro_usuario0` FOREIGN KEY (`usuario_idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `descubrimiento`
--

/*!40000 ALTER TABLE `descubrimiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `descubrimiento` ENABLE KEYS */;


--
-- Definition of table `equipo`
--

DROP TABLE IF EXISTS `equipo`;
CREATE TABLE `equipo` (
  `idEquipo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `foto` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`idEquipo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `equipo`
--

/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
INSERT INTO `equipo` (`idEquipo`,`nombre`,`foto`) VALUES 
 (1,'Los Arrebuscadores','');
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;


--
-- Definition of table `tesoro`
--

DROP TABLE IF EXISTS `tesoro`;
CREATE TABLE `tesoro` (
  `idTesoro` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(500) NOT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `latitud` varchar(45) NOT NULL,
  `longitud` varchar(45) NOT NULL,
  `dificultad` int(11) NOT NULL,
  `codSeguridad` varchar(45) DEFAULT NULL,
  `fechaEscondido` date DEFAULT NULL,
  `tamanio` varchar(45) DEFAULT NULL,
  `zona` varchar(100) DEFAULT NULL,
  `usuario_idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idTesoro`),
  KEY `fk_tesoro_usuario1_idx` (`usuario_idUsuario`),
  CONSTRAINT `fk_tesoro_usuario1` FOREIGN KEY (`usuario_idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tesoro`
--

/*!40000 ALTER TABLE `tesoro` DISABLE KEYS */;
INSERT INTO `tesoro` (`idTesoro`,`nombre`,`descripcion`,`estado`,`latitud`,`longitud`,`dificultad`,`codSeguridad`,`fechaEscondido`,`tamanio`,`zona`,`usuario_idUsuario`) VALUES 
 (1,'tesoro1','descripciontesoro1','correcto','22','33',2,'224','2013-11-07','',NULL,1);
/*!40000 ALTER TABLE `tesoro` ENABLE KEYS */;


--
-- Definition of table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `nick` varchar(45) NOT NULL,
  `pass` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `fechaNacimiento` date DEFAULT NULL,
  `fechaRegistro` date NOT NULL,
  `foto` varchar(300) DEFAULT NULL,
  `ciudad` varchar(45) DEFAULT NULL,
  `rango` varchar(45) NOT NULL,
  `rol` int(11) NOT NULL,
  `equipo_idEquipo` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `fk_usuario_equipo1_idx` (`equipo_idEquipo`),
  CONSTRAINT `fk_usuario_equipo1` FOREIGN KEY (`equipo_idEquipo`) REFERENCES `equipo` (`idEquipo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`idUsuario`,`nombre`,`apellidos`,`nick`,`pass`,`email`,`fechaNacimiento`,`fechaRegistro`,`foto`,`ciudad`,`rango`,`rol`,`equipo_idEquipo`) VALUES 
 (1,'Pepe','Perez Perrez','peperez','1234asdf','elpeperez@jemeil.com','1982-11-06','2013-10-26',NULL,'Guaro','0',0,NULL),
 (2,'Pedro','Ximenez','pexi','pexi123','sechulo@hotmail.com','1985-10-01','2013-11-04',NULL,'Torre del Mar','0',0,NULL),
 (3,'Carlos','Rodrigues','carrod','12345','carlos.rodrigues@gmail.com','1990-02-08','2013-11-01',NULL,'Málaga','0',1,NULL),
 (4,'Miguel','Gonzalez','admin','admin','admin@geocaching.com','1984-11-06','2013-09-01',NULL,'Estepona','0',2,NULL),
 (5,'Fulanito','Detal','fulano68','1234','fulanito@gmail.com','1991-05-08','2013-11-06',NULL,'Cártama','0',0,1),
 (6,'Ernesto','Garchia','elernes','caballoloco','ernestin25@gmail.com','1974-02-21','2013-10-09',NULL,'Guadiaro','0',0,1),
 (7,'Marcos','Granjero','seloco','olakase','seloco30@hotmail.com','1985-08-23','2013-10-07',NULL,'Cuevas del Becerro','0',0,1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
