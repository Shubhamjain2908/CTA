/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.0.37-community-nt : Database - cta
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cta` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `cta`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `dob` varchar(255) default NULL,
  `email` varchar(250) NOT NULL,
  `mobile_no` varchar(255) default NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `authentication` int(11) default NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`username`,`dob`,`email`,`mobile_no`,`name`,`password`,`authentication`) values ('Divyansh','2222-12-12','20981@j.co','1','qw','$2a$12$VNQ6j7lOxKlyMOtXTPkM6eiMfaZAkCk.hnH59ZaXcTWYycBVVBuGm',1),('Shree','2018-04-14','suparshvnath@gmail.com','8912891891','supar','$2a$12$rwetWvkWFdGM/sJEih96AuCk8LnKcoTmF6k5ekYdjviWZzFxhHKBa',1),('Shubham','2018-04-06','suparshvnath@gmail.com','123809','as','$2a$12$MyrTYKGdDJnV9f.HnY6zTOkbc2LFjF6/xVqHRagP1pfaBbT1f1DQi',1),('sjain','1231-01-12','sjain88715@gmail.com','9812098130','shubham jain','$2a$12$bhhIt7B/pvv.OqjlkkcufuG5b9nQouXp/lypTCCg1KROVn5AOwd2G',1);

/*Table structure for table `user_favorite_currency` */

DROP TABLE IF EXISTS `user_favorite_currency`;

CREATE TABLE `user_favorite_currency` (
  `user_username` varchar(255) NOT NULL,
  `favorite_currency` varchar(255) default NULL,
  KEY `FKat7wmglfas0c5jhneo9r1oq2s` (`user_username`),
  CONSTRAINT `FKat7wmglfas0c5jhneo9r1oq2s` FOREIGN KEY (`user_username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_favorite_currency` */

insert  into `user_favorite_currency`(`user_username`,`favorite_currency`) values ('Shree','Ripple'),('sjain','VeChain'),('sjain','Dash'),('sjain','Bitcoin'),('sjain','EOS');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
