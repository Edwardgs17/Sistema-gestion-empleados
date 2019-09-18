/*
SQLyog Community v8.71 
MySQL - 5.5.5-10.1.38-MariaDB : Database - abc_bd
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`abc_bd` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `abc_bd`;

/*Table structure for table `empleado` */

DROP TABLE IF EXISTS `empleado`;

CREATE TABLE `empleado` (
  `documento` varchar(10) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `sexo` varchar(1) DEFAULT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `telefono` varchar(11) DEFAULT NULL,
  `salario` varchar(10) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `tipo` varchar(1) DEFAULT '2',
  PRIMARY KEY (`documento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `empleado` */

insert  into `empleado`(`documento`,`nombre`,`sexo`,`direccion`,`telefono`,`salario`,`password`,`tipo`) values ('1111','Maria Andrea Cardona','F','Armenia','34356','815000.0','1111','1'),('1211','Alexander Perez','M','Salento','23313','509000.0','1211','2'),('12111','Mauricio Perez','M','Salento Q','23313','509000.0','12111','2'),('12121','Juanita Perez','F','Armenia','34356','815000.0','12121','2'),('12367','Ana Milena Arias Giraldo','F','Armenia Quindio','345632','462000.0','12367','2'),('222','Pedro Andrés','M','Calle 2 # 23-34 Armenia','2345','506000.0','222','2'),('2323','Cristian Henao','M','Armenia Barrio las Plamas','34563','5036000.0','2323','1'),('4444','Pedro Perez','M','Armenia','34356','815000.0','4444','2'),('4949','Susan Perez','M','Armenia','23313','509000.0','4949','2'),('543322','Juan Jose Marulanda','M','Armenia Quindio','3234325','412000.0','543322','2'),('9898','Maria Camila Perez zapata','F','Armenia Quindio','3234325','412000.0','9898','2'),('admin','Administrador','M','Armenia','','0.0','admin','1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
