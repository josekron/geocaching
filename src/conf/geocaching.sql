SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `geocaching` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `geocaching` ;

-- -----------------------------------------------------
-- Table `geocaching`.`equipo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `geocaching`.`equipo` ;

CREATE  TABLE IF NOT EXISTS `geocaching`.`equipo` (
  `idEquipo` INT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(45) NOT NULL ,
  `foto` VARCHAR(300) NULL ,
  PRIMARY KEY (`idEquipo`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `geocaching`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `geocaching`.`usuario` ;

CREATE  TABLE IF NOT EXISTS `geocaching`.`usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(45) NOT NULL ,
  `apellidos` VARCHAR(100) NOT NULL ,
  `nick` VARCHAR(45) NOT NULL ,
  `pass` VARCHAR(45) NOT NULL ,
  `email` VARCHAR(45) NOT NULL ,
  `fechaNacimiento` DATE NULL ,
  `fechaRegistro` DATE NOT NULL ,
  `foto` VARCHAR(300) NULL ,
  `ciudad` VARCHAR(45) NULL ,
  `rango` VARCHAR(45) NOT NULL ,
  `rol` INT NOT NULL ,
  `equipo_idEquipo` INT NULL ,
  PRIMARY KEY (`idUsuario`) ,
  INDEX `fk_usuario_equipo1_idx` (`equipo_idEquipo` ASC) ,
  CONSTRAINT `fk_usuario_equipo1`
    FOREIGN KEY (`equipo_idEquipo` )
    REFERENCES `geocaching`.`equipo` (`idEquipo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `geocaching`.`tesoro`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `geocaching`.`tesoro` ;

CREATE  TABLE IF NOT EXISTS `geocaching`.`tesoro` (
  `idTesoro` INT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(45) NOT NULL ,
  `descripcion` VARCHAR(500) NOT NULL ,
  `estado` VARCHAR(45) NULL ,
  `latitud` VARCHAR(45) NOT NULL ,
  `longitud` VARCHAR(45) NOT NULL ,
  `dificultad` INT NOT NULL ,
  `codSeguridad` VARCHAR(45) NULL ,
  `fechaEscondido` DATE NULL ,
  `tamanio` VARCHAR(45) NULL ,
  `zona` VARCHAR(100) NULL ,
  `usuario_idUsuario` INT NOT NULL ,
  PRIMARY KEY (`idTesoro`) ,
  INDEX `fk_tesoro_usuario1_idx` (`usuario_idUsuario` ASC) ,
  CONSTRAINT `fk_tesoro_usuario1`
    FOREIGN KEY (`usuario_idUsuario` )
    REFERENCES `geocaching`.`usuario` (`idUsuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `geocaching`.`comentario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `geocaching`.`comentario` ;

CREATE  TABLE IF NOT EXISTS `geocaching`.`comentario` (
  `idComentario` INT NOT NULL AUTO_INCREMENT ,
  `titulo` VARCHAR(50) NOT NULL ,
  `texto` VARCHAR(500) NULL ,
  `fechaPublicacion` DATE NOT NULL ,
  `fechaUltModificacion` DATE NULL ,
  `fechaBorrado` DATE NULL ,
  `tesoro_idTesoro` INT NOT NULL ,
  `usuario_idUsuario` INT NOT NULL ,
  PRIMARY KEY (`idComentario`) ,
  INDEX `fk_comentario_tesoro1_idx` (`tesoro_idTesoro` ASC) ,
  INDEX `fk_comentario_usuario1_idx` (`usuario_idUsuario` ASC) ,
  CONSTRAINT `fk_comentario_tesoro1`
    FOREIGN KEY (`tesoro_idTesoro` )
    REFERENCES `geocaching`.`tesoro` (`idTesoro` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comentario_usuario1`
    FOREIGN KEY (`usuario_idUsuario` )
    REFERENCES `geocaching`.`usuario` (`idUsuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `geocaching`.`descubrimiento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `geocaching`.`descubrimiento` ;

CREATE  TABLE IF NOT EXISTS `geocaching`.`descubrimiento` (
  `usuario_idUsuario` INT NOT NULL AUTO_INCREMENT ,
  `tesoro_idTesoro` INT NOT NULL ,
  `fechaDescubrimiento` DATE NOT NULL ,
  `kmRecorridos` INT NULL ,
  `dificultad` INT NULL ,
  PRIMARY KEY (`usuario_idUsuario`, `tesoro_idTesoro`) ,
  INDEX `fk_usuario_has_tesoro_tesoro1_idx` (`tesoro_idTesoro` ASC) ,
  INDEX `fk_usuario_has_tesoro_usuario_idx` (`usuario_idUsuario` ASC) ,
  CONSTRAINT `fk_usuario_has_tesoro_usuario0`
    FOREIGN KEY (`usuario_idUsuario` )
    REFERENCES `geocaching`.`usuario` (`idUsuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_has_tesoro_tesoro10`
    FOREIGN KEY (`tesoro_idTesoro` )
    REFERENCES `geocaching`.`tesoro` (`idTesoro` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `geocaching`.`aviso`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `geocaching`.`aviso` ;

CREATE  TABLE IF NOT EXISTS `geocaching`.`aviso` (
  `usuario_idUsuarioEmisor` INT NOT NULL ,
  `usuario_idUsuarioReceptor` INT NOT NULL ,
  `idAviso` INT NOT NULL ,
  `fecha_enviado` DATE NOT NULL ,
  `titulo` VARCHAR(45) NOT NULL ,
  `mensaje` VARCHAR(500) NOT NULL ,
  `leido` TINYINT(1) NULL ,
  PRIMARY KEY (`usuario_idUsuarioEmisor`, `usuario_idUsuarioReceptor`, `idAviso`) ,
  INDEX `fk_usuario_has_usuario_usuario2_idx` (`usuario_idUsuarioReceptor` ASC) ,
  INDEX `fk_usuario_has_usuario_usuario1_idx` (`usuario_idUsuarioEmisor` ASC) ,
  CONSTRAINT `fk_usuario_has_usuario_usuario1`
    FOREIGN KEY (`usuario_idUsuarioEmisor` )
    REFERENCES `geocaching`.`usuario` (`idUsuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_has_usuario_usuario2`
    FOREIGN KEY (`usuario_idUsuarioReceptor` )
    REFERENCES `geocaching`.`usuario` (`idUsuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `geocaching` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
