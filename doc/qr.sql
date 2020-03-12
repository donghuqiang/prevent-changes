/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : qr

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-03-12 09:56:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_news
-- ----------------------------
DROP TABLE IF EXISTS `t_news`;
CREATE TABLE `t_news` (
  `ID` varchar(64) NOT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `CREATEUSER` varchar(50) DEFAULT NULL,
  `SIGNATURE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_news
-- ----------------------------
INSERT INTO `t_news` VALUES ('6fbd9f80f9a74bffa2085f5a538bfbf6', '新冠肺炎情况通报12211', '尽快清除病毒', '张三', 'BBC9BFB075E3A3FAB538B793234586E3');

-- ----------------------------
-- Table structure for t_news_back
-- ----------------------------
DROP TABLE IF EXISTS `t_news_back`;
CREATE TABLE `t_news_back` (
  `ID` varchar(64) NOT NULL,
  `TABLENAME` varchar(255) DEFAULT NULL,
  `FIELDNAME` varchar(255) DEFAULT NULL,
  `ENCRYPTVALUE` text,
  `CREATETIME` datetime DEFAULT NULL,
  `BUSINESSID` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_news_back
-- ----------------------------
INSERT INTO `t_news_back` VALUES ('ef158644cda24ad491096cb6bab237c5', 't_news', 'title', '0459E29E61CDD2B5BF45B543564572B81CCD1066BBE9A9B4D0893644FC081751325E5ADAF028EE286DEB82828215F06E502BA814FBC872FD17EF5B059FB697C3A221E697FBBE38A9A604F1B9552FAD88C09AFFCFA8269C91D3DE554FE67E452346ABA9BAE3F870916677C2D6FD42A57FA4A390700742FEC03A6B565EAF', '2020-03-12 09:49:24', '6fbd9f80f9a74bffa2085f5a538bfbf6');

-- ----------------------------
-- Table structure for t_s_base_user
-- ----------------------------
DROP TABLE IF EXISTS `t_s_base_user`;
CREATE TABLE `t_s_base_user` (
  `ID` varchar(32) NOT NULL COMMENT 'ID',
  `activitiSync` smallint(6) DEFAULT NULL COMMENT '同步流程',
  `browser` varchar(20) DEFAULT NULL COMMENT '浏览器',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `realname` varchar(50) DEFAULT NULL COMMENT '真实名字',
  `signature` blob COMMENT '签名',
  `status` smallint(6) DEFAULT NULL COMMENT '有效状态',
  `userkey` varchar(200) DEFAULT NULL COMMENT '用户KEY',
  `username` varchar(50) NOT NULL COMMENT '用户账号',
  `departid` varchar(32) DEFAULT NULL COMMENT '部门ID',
  `user_name_en` varchar(500) DEFAULT NULL COMMENT '英文名',
  `delete_flag` smallint(6) DEFAULT NULL COMMENT '删除状态',
  PRIMARY KEY (`ID`),
  KEY `FK_15jh1g4iem1857546ggor42et` (`departid`),
  KEY `index_login` (`password`,`username`),
  KEY `idx_deleteflg` (`delete_flag`),
  CONSTRAINT `t_s_base_user_ibfk_1` FOREIGN KEY (`departid`) REFERENCES `t_s_depart` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_base_user
-- ----------------------------
