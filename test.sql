/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50168
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50168
File Encoding         : 65001

Date: 2017-02-07 14:11:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `clsId` varchar(36) NOT NULL,
  `clsName` varchar(50) NOT NULL,
  PRIMARY KEY (`clsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('001', '一班');
INSERT INTO `class` VALUES ('002', '二班');
INSERT INTO `class` VALUES ('003', '三班');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `stuId` varchar(36) NOT NULL,
  `stuName` varchar(50) NOT NULL,
  `stuGender` varchar(2) DEFAULT NULL,
  `clsId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`stuId`),
  KEY `STU_CLS` (`clsId`),
  CONSTRAINT `STU_CLS` FOREIGN KEY (`clsId`) REFERENCES `class` (`clsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('168996842711810049', '章科', '女', '001');
INSERT INTO `student` VALUES ('168996842711810051', '章科3', '男', '002');
INSERT INTO `student` VALUES ('168996842711810052', '聂伟', '男', '003');
INSERT INTO `student` VALUES ('168996842711810053', '聂伟1', '男', '003');
INSERT INTO `student` VALUES ('168996842711810054', '聂伟2', '男', '003');
INSERT INTO `student` VALUES ('168996842711810055', '聂伟3', '男', '003');
INSERT INTO `student` VALUES ('168996842711810056', '章科4', '男', null);
INSERT INTO `student` VALUES ('169053462074490880', '我是帅哥', '男', '001');

-- ----------------------------
-- View structure for testview
-- ----------------------------
DROP VIEW IF EXISTS `testview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `testview` AS (select * from testtable) ;

-- ----------------------------
-- Procedure structure for proc_
-- ----------------------------
DROP PROCEDURE IF EXISTS `proc_`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_`(in id int)
BEGIN
	DECLARE aid int;
	set aid=id;
	insert into testview VALUES(aid,'章傻逼1111','shabi1');
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for proc_add
-- ----------------------------
DROP PROCEDURE IF EXISTS `proc_add`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_add`(in a int,in b int,out ab int)
BEGIN
	set ab=a+b;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for proc_test
-- ----------------------------
DROP PROCEDURE IF EXISTS `proc_test`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_test`()
BEGIN
		insert into testview VALUES(7,'章傻逼1','shabi1');
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for proc_test1
-- ----------------------------
DROP PROCEDURE IF EXISTS `proc_test1`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_test1`(a int,name varchar(50),gender varchar(50))
BEGIN
		insert into testview VALUES(a,name,gender);
end
;;
DELIMITER ;
