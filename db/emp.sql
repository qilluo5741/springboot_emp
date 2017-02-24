/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : emp

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-02-15 09:33:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for gradehistory
-- ----------------------------
DROP TABLE IF EXISTS `gradehistory`;
CREATE TABLE `gradehistory` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `time` datetime DEFAULT NULL COMMENT '记录时间',
  `detail` varchar(255) DEFAULT NULL COMMENT '具体细节',
  `type` varchar(255) DEFAULT NULL COMMENT '变动类型',
  `grade` int(11) DEFAULT NULL COMMENT '得分或减分',
  `membernumber` int(11) DEFAULT NULL COMMENT '员工工号',
  PRIMARY KEY (`id`),
  KEY `membernumber` (`membernumber`),
  CONSTRAINT `gradehistory_ibfk_1` FOREIGN KEY (`membernumber`) REFERENCES `member` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gradehistory
-- ----------------------------
INSERT INTO `gradehistory` VALUES ('asd', '2017-02-09 11:45:31', null, '加班', '1', '1');
INSERT INTO `gradehistory` VALUES ('asd111', '2017-02-09 13:53:17', null, '加班', '1', '1');
INSERT INTO `gradehistory` VALUES ('lkk', '2017-02-09 14:08:51', null, '加班', '1', '1');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `number` int(11) NOT NULL COMMENT '员工工号',
  `name` varchar(255) DEFAULT NULL COMMENT '员工姓名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `nowgrade` int(11) DEFAULT NULL COMMENT '当年分数',
  `levelgrade` int(11) DEFAULT NULL COMMENT '升级所需分数',
  `medal` int(11) DEFAULT NULL COMMENT '勋章',
  `isonjob` tinyint(4) DEFAULT NULL COMMENT '是否在职：0，离职；1，在职',
  `authority` tinyint(4) DEFAULT NULL COMMENT '权限：0，普通员工；1，管理员',
  PRIMARY KEY (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('1', 'zhy', null, '0', '0', '4', '1', '0');
INSERT INTO `member` VALUES ('2', 'zhy2', null, '0', '364', '2', '0', '0');
INSERT INTO `member` VALUES ('3', 'zhy3', null, '0', '2', '1', '0', '0');
INSERT INTO `member` VALUES ('4', 'zhy4', '', '0', '2', '1', '0', '0');
INSERT INTO `member` VALUES ('5', 'zhy4', '', '0', '2', '1', '0', '0');
INSERT INTO `member` VALUES ('6', 'zhy6', 'B8DE9756CE26CDFFF13E5B63D24575EF', '0', '2', '1', '0', '0');
INSERT INTO `member` VALUES ('7', 'zhy6', 'B8DE9756CE26CDFFF13E5B63D24575EF', '0', '2', '1', '0', '0');

-- ----------------------------
-- Table structure for updatehistory
-- ----------------------------
DROP TABLE IF EXISTS `updatehistory`;
CREATE TABLE `updatehistory` (
  `id` varchar(255) NOT NULL,
  `time` datetime DEFAULT NULL COMMENT '升级时间',
  `updatemedal` varchar(255) DEFAULT NULL COMMENT '勋章升级细节',
  `membernumber` int(11) DEFAULT NULL COMMENT '员工工号',
  PRIMARY KEY (`id`),
  KEY `membernumber` (`membernumber`),
  CONSTRAINT `updatehistory_ibfk_1` FOREIGN KEY (`membernumber`) REFERENCES `member` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of updatehistory
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
