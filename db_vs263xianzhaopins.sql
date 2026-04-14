/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50087
Source Host           : localhost:3306
Source Database       : db_vs263xianzhaopins

Target Server Type    : MYSQL
Target Server Version : 50087
File Encoding         : 65001

Date: 2026-04-02 00:47:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_admin`
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `adminId` int(11) NOT NULL auto_increment,
  `adminName` varchar(255) default NULL,
  `adminPassword` varchar(255) default NULL,
  `adminXingming` varchar(255) default NULL,
  `adminSex` int(11) default NULL,
  `adminAge` int(11) default NULL,
  `adminPhone` varchar(255) default NULL,
  `adminMark` varchar(255) default NULL,
  `adminMark1` varchar(255) default NULL,
  `adminMark2` varchar(255) default NULL,
  `adminMark3` varchar(255) default NULL,
  `adminMark4` varchar(255) default NULL,
  `adminDate` datetime default NULL,
  `adminDate1` datetime default NULL,
  `adminDate2` datetime default NULL,
  `adminType` int(11) default NULL,
  `adminType1` int(11) default NULL,
  `adminType2` int(11) default NULL,
  `adminDouble` double default NULL,
  `adminDouble1` double default NULL,
  `adminDouble2` double default NULL,
  `adminZong` int(11) default NULL,
  `adminZong1` int(11) default NULL,
  `adminZong2` int(11) default NULL,
  `adminImg` varchar(255) default NULL,
  `adminImgName` varchar(255) default NULL,
  PRIMARY KEY  (`adminId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', 'admin', 'admin', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `t_bumen`
-- ----------------------------
DROP TABLE IF EXISTS `t_bumen`;
CREATE TABLE `t_bumen` (
  `bumenId` int(11) NOT NULL auto_increment,
  `bumenName` varchar(255) default NULL,
  `bumenMark` varchar(255) default NULL,
  `bumenMark1` varchar(255) default NULL,
  `bumenMark2` varchar(255) default NULL,
  `bumenType` int(11) default NULL,
  `bumenType1` int(11) default NULL,
  `bumenType2` int(11) default NULL,
  `bumenDouble` double default NULL,
  `bumenDouble1` double default NULL,
  `bumenDouble2` double default NULL,
  `buyuanId` int(11) default NULL,
  `buyuanName` varchar(255) default NULL,
  PRIMARY KEY  (`bumenId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bumen
-- ----------------------------
INSERT INTO `t_bumen` VALUES ('1', '职位信息14', '职位信息14', '职位信息14', '职位信息14', null, '1', '14', '6000', null, null, null, null);
INSERT INTO `t_bumen` VALUES ('2', '职位信息13', '职位信息13', '职位信息13', '职位信息13', null, '7', '13', '5500', null, null, null, null);
INSERT INTO `t_bumen` VALUES ('3', '职位信息16', '职位信息16', '职位信息16', '职位信息16', null, '5', '16', '7000', null, null, null, null);
INSERT INTO `t_bumen` VALUES ('4', '职位信息2', '职位信息2', '职位信息2', '职位信息2', null, '3', '2', '5000', null, null, null, null);
INSERT INTO `t_bumen` VALUES ('5', '职位信息1', '职位信息1', '职位信息1', '职位信息1', null, '2', '1', '4500', null, null, null, null);
INSERT INTO `t_bumen` VALUES ('6', '职位信息2', '职位信息2', '职位信息2', '职位信息2', null, '1', '2', '5000', null, null, null, null);
INSERT INTO `t_bumen` VALUES ('7', '职位信息9', '职位信息9', '职位信息9', '职位信息9', null, '3', '9', '8500', null, null, null, null);

-- ----------------------------
-- Table structure for `t_buyuan`
-- ----------------------------
DROP TABLE IF EXISTS `t_buyuan`;
CREATE TABLE `t_buyuan` (
  `buyuanId` int(11) NOT NULL auto_increment,
  `buyuanName` varchar(255) default NULL,
  `buyuanMark` varchar(255) default NULL,
  `buyuanMark1` varchar(255) default NULL,
  `buyuanMark2` varchar(255) default NULL,
  `buyuanType` int(11) default NULL,
  `buyuanType1` int(11) default NULL,
  `buyuanType2` int(11) default NULL,
  `buyuanDouble` double default NULL,
  `buyuanDouble1` double default NULL,
  `buyuanDouble2` double default NULL,
  PRIMARY KEY  (`buyuanId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_buyuan
-- ----------------------------
INSERT INTO `t_buyuan` VALUES ('1', null, 'yonghu1', '职场经验1', null, '1', '7', null, null, null, null);

-- ----------------------------
-- Table structure for `t_buzhi`
-- ----------------------------
DROP TABLE IF EXISTS `t_buzhi`;
CREATE TABLE `t_buzhi` (
  `buzhiId` int(11) NOT NULL auto_increment,
  `buzhiName` varchar(255) default NULL,
  `buzhiMark` varchar(255) default NULL,
  `buzhiMark1` varchar(255) default NULL,
  `buzhiMark2` varchar(255) default NULL,
  `buzhiType` int(11) default NULL,
  `buzhiType1` int(11) default NULL,
  `buzhiType2` int(11) default NULL,
  `buzhiDouble` double default NULL,
  `buzhiDouble1` double default NULL,
  `buzhiDouble2` double default NULL,
  `buyuanId` int(11) default NULL,
  `buyuanName` varchar(255) default NULL,
  `bumenId` int(11) default NULL,
  `bumenName` varchar(255) default NULL,
  PRIMARY KEY  (`buzhiId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_buzhi
-- ----------------------------
INSERT INTO `t_buzhi` VALUES ('1', '国企信息', '国企信息国企信息国企信息国企信息国企信息国企信息国企信息国企信息国企信息', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_buzhi` VALUES ('2', '外企信息', '外企信息外企信息外企信息外企信息外企信息外企信息外企信息外企信息外企信息外企信息外企信息', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_buzhi` VALUES ('3', '私营企业', '私营企业私营企业私营企业私营企业私营企业私营企业私营企业私营企业私营企业私营企业', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_buzhi` VALUES ('4', '股份公司', '股份公司股份公司股份公司股份公司股份公司股份公司股份公司股份公司股份公司股份公司股份公司股份公司股份公司', null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `t_ggtype`
-- ----------------------------
DROP TABLE IF EXISTS `t_ggtype`;
CREATE TABLE `t_ggtype` (
  `ggtypeId` int(11) NOT NULL auto_increment,
  `ggtypeName` varchar(255) default NULL,
  `ggtypeMark` varchar(255) default NULL,
  PRIMARY KEY  (`ggtypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_ggtype
-- ----------------------------
INSERT INTO `t_ggtype` VALUES ('1', '网站公告', '网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告');
INSERT INTO `t_ggtype` VALUES ('2', '新闻信息', '新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息');
INSERT INTO `t_ggtype` VALUES ('3', '就业资讯', '就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯');

-- ----------------------------
-- Table structure for `t_gonggao`
-- ----------------------------
DROP TABLE IF EXISTS `t_gonggao`;
CREATE TABLE `t_gonggao` (
  `gonggaoId` int(11) NOT NULL auto_increment,
  `gonggaoName` varchar(255) default NULL,
  `gonggaoMark` longtext,
  `gonggaoImg` varchar(255) default NULL,
  `gonggaoImgName` varchar(255) default NULL,
  `gonggaoDate` datetime default NULL,
  `ggtypeId` int(11) default NULL,
  `ggtypeName` varchar(255) default NULL,
  PRIMARY KEY  (`gonggaoId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_gonggao
-- ----------------------------
INSERT INTO `t_gonggao` VALUES ('1', '就业资讯1', '<p>&nbsp;&nbsp;&nbsp;&nbsp;就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯<br/>&nbsp;&nbsp;&nbsp;&nbsp;就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯<br/>&nbsp;&nbsp;&nbsp;&nbsp;就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/21.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', 'http://127.0.0.1:8080/vs263xianzhaopins/file/21.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=21.jpg', '2026-04-02 10:37:45', '3', '就业资讯');
INSERT INTO `t_gonggao` VALUES ('2', '就业资讯2', '<p>&nbsp; &nbsp; 就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯<br/>&nbsp;&nbsp;&nbsp;&nbsp;就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯<br/>&nbsp;&nbsp;&nbsp;&nbsp;就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/22.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', 'http://127.0.0.1:8080/vs263xianzhaopins/file/22.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=22.jpg', '2026-04-02 10:38:24', '3', '就业资讯');
INSERT INTO `t_gonggao` VALUES ('3', '就业资讯3', '<p>&nbsp; &nbsp; 就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯<br/>&nbsp;&nbsp;&nbsp;&nbsp;就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯<br/>&nbsp;&nbsp;&nbsp;&nbsp;就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯就业资讯</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/23.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', 'http://127.0.0.1:8080/vs263xianzhaopins/file/23.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=23.jpg', '2026-04-02 10:39:40', '3', '就业资讯');
INSERT INTO `t_gonggao` VALUES ('4', '新闻信息1', '<p>&nbsp; &nbsp; 新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/24.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', 'http://127.0.0.1:8080/vs263xianzhaopins/file/24.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=24.jpg', '2026-04-02 10:37:45', '2', '新闻信息');
INSERT INTO `t_gonggao` VALUES ('5', '新闻信息2', '<p>&nbsp; &nbsp; 新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/25.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', 'http://127.0.0.1:8080/vs263xianzhaopins/file/25.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=25.jpg', '2026-04-02 10:38:24', '2', '新闻信息');
INSERT INTO `t_gonggao` VALUES ('6', '新闻信息3', '<p>&nbsp; &nbsp; 新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息新闻信息</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/26.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', 'http://127.0.0.1:8080/vs263xianzhaopins/file/26.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=26.jpg', '2026-04-02 10:39:40', '2', '新闻信息');
INSERT INTO `t_gonggao` VALUES ('7', '网站公告1', '<p>&nbsp; &nbsp; 网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告<br/>&nbsp;&nbsp;&nbsp;&nbsp;网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告<br/>&nbsp;&nbsp;&nbsp;&nbsp;网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/27.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', 'http://127.0.0.1:8080/vs263xianzhaopins/file/27.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=27.jpg', '2026-04-02 10:37:45', '1', '网站公告');
INSERT INTO `t_gonggao` VALUES ('8', '网站公告2', '<p>&nbsp; &nbsp; 网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告<br/>&nbsp;&nbsp;&nbsp;&nbsp;网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告<br/>&nbsp;&nbsp;&nbsp;&nbsp;网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/28.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', 'http://127.0.0.1:8080/vs263xianzhaopins/file/28.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=28.jpg', '2026-04-02 10:38:24', '1', '网站公告');
INSERT INTO `t_gonggao` VALUES ('9', '网站公告3', '<p>&nbsp; &nbsp; 网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告<br/>&nbsp;&nbsp;&nbsp;&nbsp;网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告<br/>&nbsp;&nbsp;&nbsp;&nbsp;网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告网站公告</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/29.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', 'http://127.0.0.1:8080/vs263xianzhaopins/file/29.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=29.jpg', '2026-04-02 10:39:40', '1', '网站公告');

-- ----------------------------
-- Table structure for `t_jcbiaoti`
-- ----------------------------
DROP TABLE IF EXISTS `t_jcbiaoti`;
CREATE TABLE `t_jcbiaoti` (
  `jcbiaotiId` int(11) NOT NULL auto_increment,
  `jcbiaotiName` varchar(255) default NULL COMMENT '汉字',
  `jcbiaotiNeirong` varchar(255) default NULL COMMENT 'url',
  `jcbiaotiMark` varchar(255) default NULL,
  `jcbiaotiMark1` varchar(255) default NULL,
  `jcbiaotiMark2` varchar(255) default NULL,
  `jcbiaotiPaixu` int(11) default NULL,
  `jcbiaotiType` int(11) default NULL COMMENT '0网页导航1admin导航2user导航3yonghu导航4juese导航',
  `jcbiaotiType1` int(11) default NULL,
  `jcbiaotiType2` int(11) default NULL,
  PRIMARY KEY  (`jcbiaotiId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_jcbiaoti
-- ----------------------------
INSERT INTO `t_jcbiaoti` VALUES ('1', '网站信息', null, null, null, null, '1', '1', '0', null);
INSERT INTO `t_jcbiaoti` VALUES ('2', '新闻公告', null, null, null, null, '2', '1', '0', null);
INSERT INTO `t_jcbiaoti` VALUES ('3', '企业信息', null, null, null, null, '3', '1', '0', null);
INSERT INTO `t_jcbiaoti` VALUES ('4', '职位信息', null, null, null, null, '4', '1', '0', null);
INSERT INTO `t_jcbiaoti` VALUES ('5', '用户信息', null, null, null, null, '5', '1', '0', null);
INSERT INTO `t_jcbiaoti` VALUES ('6', '求职信息', null, null, null, null, '6', '1', '0', null);
INSERT INTO `t_jcbiaoti` VALUES ('7', '讨论交流', null, null, null, null, '7', '1', '0', null);
INSERT INTO `t_jcbiaoti` VALUES ('8', '数据看板', null, null, null, null, '8', '1', '0', null);
INSERT INTO `t_jcbiaoti` VALUES ('9', '个人消息', null, null, null, null, '1', '2', '0', null);
INSERT INTO `t_jcbiaoti` VALUES ('10', '求职评论', null, null, null, null, '2', '2', '0', null);
INSERT INTO `t_jcbiaoti` VALUES ('11', '讨论交流', null, null, null, null, '3', '2', '0', null);
INSERT INTO `t_jcbiaoti` VALUES ('12', '个人消息', null, null, null, null, '1', '3', '0', null);
INSERT INTO `t_jcbiaoti` VALUES ('13', '职位信息', null, null, null, null, '2', '3', '0', null);
INSERT INTO `t_jcbiaoti` VALUES ('14', '求职评论', null, null, null, null, '3', '3', '0', null);
INSERT INTO `t_jcbiaoti` VALUES ('15', '就业信息', null, null, null, null, '5', '1', '0', null);
INSERT INTO `t_jcbiaoti` VALUES ('16', '就业信息', null, null, null, null, '2', '2', '0', null);

-- ----------------------------
-- Table structure for `t_jcdaohang`
-- ----------------------------
DROP TABLE IF EXISTS `t_jcdaohang`;
CREATE TABLE `t_jcdaohang` (
  `jcdaohangId` int(11) NOT NULL auto_increment,
  `jcdaohangName` varchar(255) default NULL COMMENT '汉字',
  `jcdaohangNeirong` varchar(255) default NULL COMMENT 'url',
  `jcdaohangMark` varchar(255) default NULL,
  `jcdaohangMark1` varchar(255) default NULL,
  `jcdaohangMark2` varchar(255) default NULL,
  `jcdaohangPaixu` int(11) default NULL,
  `jcdaohangType` int(11) default NULL COMMENT '0网页导航1admin导航2user导航3yonghu导航4juese导航',
  `jcdaohangType1` int(11) default NULL,
  `jcdaohangType2` int(11) default NULL,
  `jcbiaotiId` int(11) default NULL,
  `jcbiaotiName` varchar(255) default NULL,
  PRIMARY KEY  (`jcdaohangId`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_jcdaohang
-- ----------------------------
INSERT INTO `t_jcdaohang` VALUES ('1', '基础信息', 'sjxiaoxi', null, null, null, '1', '1', '0', null, '1', null);
INSERT INTO `t_jcdaohang` VALUES ('2', '外链信息', 'sjlianjie', null, null, null, '2', '1', '0', null, '1', null);
INSERT INTO `t_jcdaohang` VALUES ('3', '公告类型', 'ggtype', null, null, null, '1', '1', '0', null, '2', null);
INSERT INTO `t_jcdaohang` VALUES ('4', '新闻公告', 'gonggao', null, null, null, '2', '1', '0', null, '2', null);
INSERT INTO `t_jcdaohang` VALUES ('5', '企业类型', 'buzhi', null, null, null, '1', '1', '0', null, '3', null);
INSERT INTO `t_jcdaohang` VALUES ('6', '企业信息', 'yonghu', null, null, null, '2', '1', '0', null, '3', null);
INSERT INTO `t_jcdaohang` VALUES ('7', '企业反馈', 'yyijian', null, null, null, '3', '1', '0', null, '3', null);
INSERT INTO `t_jcdaohang` VALUES ('8', '职位类型', 'sjleixing', null, null, null, '1', '1', '0', null, '4', null);
INSERT INTO `t_jcdaohang` VALUES ('9', '职位信息', 'shuju', null, null, null, '2', '1', '0', null, '4', null);
INSERT INTO `t_jcdaohang` VALUES ('10', '用户信息', 'user', null, null, null, '1', '1', '0', null, '5', null);
INSERT INTO `t_jcdaohang` VALUES ('11', '用户反馈', 'uyijian', null, null, null, '2', '1', '0', null, '5', null);
INSERT INTO `t_jcdaohang` VALUES ('12', '求职信息', 'sjshaochu', null, null, null, '1', '1', '0', null, '6', null);
INSERT INTO `t_jcdaohang` VALUES ('13', '评论信息', 'sjpinglun', null, null, null, '2', '1', '0', null, '6', null);
INSERT INTO `t_jcdaohang` VALUES ('14', '讨论类型', 'sjxingtai', null, null, null, '1', '1', '0', null, '7', null);
INSERT INTO `t_jcdaohang` VALUES ('15', '讨论交流', 'sjduochu', null, null, null, '2', '1', '0', null, '7', null);
INSERT INTO `t_jcdaohang` VALUES ('16', '回复信息', 'sjqita', null, null, null, '3', '1', '0', null, '7', null);
INSERT INTO `t_jcdaohang` VALUES ('18', '数据看板', 'tongjitu', null, null, null, '2', '1', '0', null, '8', null);
INSERT INTO `t_jcdaohang` VALUES ('19', '反馈信息', 'uyijian', null, null, null, '1', '2', '0', null, '9', null);
INSERT INTO `t_jcdaohang` VALUES ('20', '职位收藏', 'bumen', null, null, null, '2', '2', '0', null, '9', null);
INSERT INTO `t_jcdaohang` VALUES ('21', '通知信息', 'yxinxi', null, null, null, '3', '2', '0', null, '9', null);
INSERT INTO `t_jcdaohang` VALUES ('22', '求职信息', 'sjshaochu', null, null, null, '1', '2', '0', null, '10', null);
INSERT INTO `t_jcdaohang` VALUES ('23', '咨询信息', 'sjjianchu', null, null, null, '2', '2', '0', null, '10', null);
INSERT INTO `t_jcdaohang` VALUES ('24', '评论信息', 'sjpinglun', null, null, null, '3', '2', '0', null, '10', null);
INSERT INTO `t_jcdaohang` VALUES ('25', '发布交流', 'sjduochu', null, null, null, '1', '2', '0', null, '11', null);
INSERT INTO `t_jcdaohang` VALUES ('26', '回复交流', 'sjqita', null, null, null, '2', '2', '0', null, '11', null);
INSERT INTO `t_jcdaohang` VALUES ('27', '反馈信息', 'yyijian', null, null, null, '1', '3', '0', null, '12', null);
INSERT INTO `t_jcdaohang` VALUES ('28', '职位信息', 'shuju', null, null, null, '1', '3', '0', null, '13', null);
INSERT INTO `t_jcdaohang` VALUES ('29', '咨询信息', 'sjjianchu', null, null, null, '2', '3', '0', null, '13', null);
INSERT INTO `t_jcdaohang` VALUES ('30', '求职信息', 'sjshaochu', null, null, null, '1', '3', '0', null, '14', null);
INSERT INTO `t_jcdaohang` VALUES ('31', '通知信息', 'yxinxi', null, null, null, '2', '3', '0', null, '14', null);
INSERT INTO `t_jcdaohang` VALUES ('32', '评论信息', 'sjpinglun', null, null, null, '3', '3', '0', null, '14', null);
INSERT INTO `t_jcdaohang` VALUES ('33', '就业类型', 'uxtype', null, null, null, '1', '1', '0', null, '15', null);
INSERT INTO `t_jcdaohang` VALUES ('34', '就业信息', 'uxinxi', null, null, null, '2', '1', '0', null, '15', null);
INSERT INTO `t_jcdaohang` VALUES ('35', '就业信息', 'uxinxi', null, null, null, '1', '2', '0', null, '16', null);

-- ----------------------------
-- Table structure for `t_jcpeizhi`
-- ----------------------------
DROP TABLE IF EXISTS `t_jcpeizhi`;
CREATE TABLE `t_jcpeizhi` (
  `jcpeizhiId` int(11) NOT NULL auto_increment,
  `jcpeizhiName` varchar(255) default NULL,
  `jcpeizhiNeirong` varchar(255) default NULL,
  `jcpeizhiMark` varchar(255) default NULL,
  `bumenBieming` varchar(255) default NULL,
  `buyuanBieming` varchar(255) default NULL,
  `buzhiBieming` varchar(255) default NULL,
  `userBieming` varchar(255) default NULL COMMENT 'userBieming',
  `uxtypeBieming` varchar(255) default NULL,
  `uxinxiBieming` varchar(255) default NULL,
  `uyijianBieming` varchar(255) default NULL,
  `roleBieming` varchar(255) default NULL,
  `byumenBieming` varchar(255) default NULL,
  `byuyuanBieming` varchar(255) default NULL,
  `byuzhiBieming` varchar(255) default NULL,
  `yonghuBieming` varchar(255) default NULL COMMENT 'yonghuBieming',
  `yxtypeBieming` varchar(255) default NULL,
  `yxinxiBieming` varchar(255) default NULL,
  `yyijianBieming` varchar(255) default NULL,
  `yhroleBieming` varchar(255) default NULL,
  `ggtypeBieming` varchar(255) default NULL,
  `gonggaoBieming` varchar(255) default NULL,
  `ggpinglunBieming` varchar(255) default NULL,
  `shujuBieming` varchar(255) default NULL,
  `sjduochuBieming` varchar(255) default NULL,
  `sjjianchuBieming` varchar(255) default NULL,
  `sjlaiyuanBieming` varchar(255) default NULL,
  `sjleixingBieming` varchar(255) default NULL,
  `sjpinglunBieming` varchar(255) default NULL,
  `sjqitaBieming` varchar(255) default NULL,
  `sjshaochuBieming` varchar(255) default NULL,
  `sjxingtaiBieming` varchar(255) default NULL,
  `jcpeizhiType` int(11) default NULL,
  `jcpeizhiType1` int(11) default NULL,
  `jcpeizhiType2` int(11) default NULL,
  PRIMARY KEY  (`jcpeizhiId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_jcpeizhi
-- ----------------------------
INSERT INTO `t_jcpeizhi` VALUES ('1', '求职招聘', null, null, '收藏', null, '类型', '用户', '类型', '就业', '反馈', null, null, null, null, '企业', null, '通知', '反馈', null, '类型', '公告', null, '职位', '讨论', '咨询', null, '类型', '评论', '回复', '求职', '类型', null, null, null);

-- ----------------------------
-- Table structure for `t_rizhi`
-- ----------------------------
DROP TABLE IF EXISTS `t_rizhi`;
CREATE TABLE `t_rizhi` (
  `rizhiId` int(11) NOT NULL auto_increment,
  `rizhiName` varchar(255) NOT NULL,
  `dengluIp` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY  (`rizhiId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_rizhi
-- ----------------------------
INSERT INTO `t_rizhi` VALUES ('1', 'yonghu2', '127.0.0.1', '2026-04-02 10:10:32');
INSERT INTO `t_rizhi` VALUES ('2', 'yonghu2', '127.0.0.1', '2026-04-02 10:13:39');
INSERT INTO `t_rizhi` VALUES ('3', 'admin', '127.0.0.1', '2026-04-02 10:14:43');
INSERT INTO `t_rizhi` VALUES ('4', 'yonghu7', '127.0.0.1', '2026-04-02 10:17:32');
INSERT INTO `t_rizhi` VALUES ('5', 'admin', '127.0.0.1', '2026-04-02 10:18:00');
INSERT INTO `t_rizhi` VALUES ('6', 'admin', '127.0.0.1', '2026-04-02 23:31:51');
INSERT INTO `t_rizhi` VALUES ('7', 'admin', '127.0.0.1', '2026-04-02 23:33:54');
INSERT INTO `t_rizhi` VALUES ('8', 'admin', '127.0.0.1', '2026-04-02 00:15:45');
INSERT INTO `t_rizhi` VALUES ('9', 'admin', '127.0.0.1', '2026-04-02 00:32:58');
INSERT INTO `t_rizhi` VALUES ('10', 'admin', '127.0.0.1', '2026-04-02 00:45:44');

-- ----------------------------
-- Table structure for `t_shuju`
-- ----------------------------
DROP TABLE IF EXISTS `t_shuju`;
CREATE TABLE `t_shuju` (
  `shujuId` int(11) NOT NULL auto_increment,
  `shujuName` varchar(255) default NULL,
  `shujuMark` longtext,
  `shujuMark1` longtext,
  `shujuMark2` longtext,
  `shujuMark3` longtext,
  `shujuDate` datetime default NULL,
  `shujuDate1` datetime default NULL,
  `shujuZong` int(11) default NULL,
  `shujuZong1` int(11) default NULL,
  `shujuZong2` int(11) default NULL,
  `shujuDouble` double default NULL,
  `shujuDouble1` double default NULL,
  `shujuDouble2` double default NULL,
  `shujuDouble3` double default NULL,
  `shujuDouble4` double default NULL,
  `shujuType` int(11) default NULL,
  `shujuType1` int(11) default NULL,
  `shujuType2` int(11) default NULL,
  `shujuImg` varchar(255) default NULL,
  `shujuImgName` varchar(255) default NULL,
  `sjleixingId` int(11) default NULL,
  `sjleixingName` varchar(255) default NULL,
  `sjxingtaiId` int(11) default NULL,
  `sjxingtaiName` varchar(255) default NULL,
  `userId` int(11) default NULL,
  `userName` varchar(255) default NULL,
  `bumenId` int(11) default NULL,
  `bumenName` varchar(255) default NULL,
  `buyuanId` int(11) default NULL,
  `buyuanName` varchar(255) default NULL,
  `yonghuId` int(11) default NULL,
  `yonghuName` varchar(255) default NULL,
  `buzhiId` int(11) default NULL,
  `buzhiName` varchar(255) default NULL,
  `roleId` int(11) default NULL,
  `roleName` varchar(255) default NULL,
  PRIMARY KEY  (`shujuId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_shuju
-- ----------------------------
INSERT INTO `t_shuju` VALUES ('1', '职位信息1', '<p>&nbsp; &nbsp; 职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/0.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', '职位信息1', '职位信息1', '职位信息1', '2026-04-02 11:06:22', null, '2', null, '1', '4500', null, null, null, null, '0', null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/0.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=0.jpg', '1', '市场工作', null, null, null, null, null, null, null, null, '1', '企业1', '1', '国企信息', null, null);
INSERT INTO `t_shuju` VALUES ('2', '职位信息2', '<p>&nbsp; &nbsp; 职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/1.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', '职位信息2', '职位信息2', '职位信息2', '2026-04-02 11:06:22', null, '4', null, '2', '5000', null, null, null, null, '0', null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/1.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=1.jpg', '2', '开发工作', null, null, null, null, null, null, null, null, '2', '企业2', '2', '外企信息', null, null);
INSERT INTO `t_shuju` VALUES ('3', '职位信息3', '<p>&nbsp; &nbsp; 职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/2.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', '职位信息3', '职位信息3', '职位信息3', '2026-04-02 11:06:22', null, '5', null, '6', '5500', null, null, null, null, '0', null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/2.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=2.jpg', '3', '销售工作', null, null, null, null, null, null, null, null, '3', '企业3', '3', '私营企业', null, null);
INSERT INTO `t_shuju` VALUES ('4', '职位信息4', '<p>&nbsp; &nbsp; 职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/3.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', '职位信息4', '职位信息4', '职位信息4', '2026-04-02 11:06:22', null, '6', null, '6', '6000', null, null, null, null, '0', null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/3.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=3.jpg', '4', '文职工作', null, null, null, null, null, null, null, null, '4', '企业4', '4', '股份公司', null, null);
INSERT INTO `t_shuju` VALUES ('5', '职位信息5', '<p>&nbsp; &nbsp; 职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/4.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', '职位信息5', '职位信息5', '职位信息5', '2026-04-02 11:06:22', null, '6', null, '8', '6500', null, null, null, null, '0', null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/4.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=4.jpg', '5', '其他工作', null, null, null, null, null, null, null, null, '1', '企业1', '1', '国企信息', null, null);
INSERT INTO `t_shuju` VALUES ('6', '职位信息6', '<p>&nbsp; &nbsp; 职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/5.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', '职位信息6', '职位信息6', '职位信息6', '2026-04-02 11:06:22', null, '8', null, '9', '7000', null, null, null, null, '0', null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/5.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=5.jpg', '1', '市场工作', null, null, null, null, null, null, null, null, '2', '企业2', '2', '外企信息', null, null);
INSERT INTO `t_shuju` VALUES ('7', '职位信息7', '<p>&nbsp; &nbsp; 职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/6.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', '职位信息7', '职位信息7', '职位信息7', '2026-04-02 11:06:22', null, '9', null, '10', '7500', null, null, null, null, '0', null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/6.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=6.jpg', '2', '开发工作', null, null, null, null, null, null, null, null, '3', '企业3', '3', '私营企业', null, null);
INSERT INTO `t_shuju` VALUES ('8', '职位信息8', '<p>&nbsp; &nbsp; 职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/7.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', '职位信息8', '职位信息8', '职位信息8', '2026-04-02 11:06:22', null, '10', null, '2', '8000', null, null, null, null, '0', null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/7.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=7.jpg', '3', '销售工作', null, null, null, null, null, null, null, null, '4', '企业4', '4', '股份公司', null, null);
INSERT INTO `t_shuju` VALUES ('9', '职位信息9', '<p>&nbsp; &nbsp; 职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/8.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', '职位信息9', '职位信息9', '职位信息9', '2026-04-02 11:06:22', null, '11', null, '23', '8500', null, null, null, null, '0', null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/8.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=8.jpg', '4', '文职工作', null, null, null, null, null, null, null, null, '1', '企业1', '1', '国企信息', null, null);
INSERT INTO `t_shuju` VALUES ('10', '职位信息10', '<p>&nbsp; &nbsp; 职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/9.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', '职位信息10', '职位信息10', '职位信息10', '2026-04-02 11:06:22', null, '12', null, '34', '9000', null, null, null, null, '0', null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/9.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=9.jpg', '1', '市场工作', null, null, null, null, null, null, null, null, '2', '企业2', '2', '外企信息', null, null);
INSERT INTO `t_shuju` VALUES ('11', '职位信息11', '<p>&nbsp; &nbsp; 职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/10.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', '职位信息11', '职位信息11', '职位信息11', '2026-04-02 11:06:22', null, '13', null, '6', '4500', null, null, null, null, '0', null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/10.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=10.jpg', '2', '开发工作', null, null, null, null, null, null, null, null, '3', '企业3', '3', '私营企业', null, null);
INSERT INTO `t_shuju` VALUES ('12', '职位信息12', '<p>&nbsp; &nbsp; 职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/11.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', '职位信息12', '职位信息12', '职位信息12', '2026-04-02 11:06:22', null, '14', null, '2', '5000', null, null, null, null, '0', null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/11.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=11.jpg', '1', '市场工作', null, null, null, null, null, null, null, null, '4', '企业4', '4', '股份公司', null, null);
INSERT INTO `t_shuju` VALUES ('13', '职位信息13', '<p>&nbsp; &nbsp; 职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/12.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', '职位信息13', '职位信息13', '职位信息13', '2026-04-02 11:06:22', null, '14', null, '45', '5500', null, null, null, null, '0', null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/12.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=12.jpg', '2', '开发工作', null, null, null, null, null, null, null, null, '1', '企业1', '1', '国企信息', null, null);
INSERT INTO `t_shuju` VALUES ('14', '职位信息14', '<p>&nbsp; &nbsp; 职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/13.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', '职位信息14', '职位信息14', '职位信息14', '2026-04-02 11:06:22', null, '16', null, '12', '6000', null, null, null, null, '0', null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/13.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=13.jpg', '3', '销售工作', null, null, null, null, null, null, null, null, '2', '企业2', '2', '外企信息', null, null);
INSERT INTO `t_shuju` VALUES ('15', '职位信息15', '<p>&nbsp; &nbsp; 职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/14.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', '职位信息15', '职位信息15', '职位信息15', '2026-04-02 11:06:22', null, '11', null, '55', '6500', null, null, null, null, '0', null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/14.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=14.jpg', '4', '文职工作', null, null, null, null, null, null, null, null, '3', '企业3', '3', '私营企业', null, null);
INSERT INTO `t_shuju` VALUES ('16', '职位信息16', '<p>&nbsp; &nbsp; 职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息职位信息</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/15.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', '职位信息16', '职位信息16', '职位信息16', '2026-04-02 11:06:22', null, '12', null, '9', '7000', null, null, null, null, '0', null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/15.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=15.jpg', '5', '其他工作', null, null, null, null, null, null, null, null, '4', '企业4', '4', '股份公司', null, null);

-- ----------------------------
-- Table structure for `t_sjduochu`
-- ----------------------------
DROP TABLE IF EXISTS `t_sjduochu`;
CREATE TABLE `t_sjduochu` (
  `sjduochuId` int(11) NOT NULL auto_increment,
  `sjduochuName` varchar(255) default NULL,
  `sjduochuMark` longtext,
  `sjduochuMark1` longtext,
  `sjduochuMark2` longtext,
  `sjduochuMark3` longtext,
  `sjduochuDate` datetime default NULL,
  `sjduochuDate1` datetime default NULL,
  `sjduochuZong` int(11) default NULL,
  `sjduochuZong1` int(11) default NULL,
  `sjduochuZong2` int(11) default NULL,
  `sjduochuDouble` double default NULL,
  `sjduochuDouble1` double default NULL,
  `sjduochuDouble2` double default NULL,
  `sjduochuType` int(11) default NULL,
  `sjduochuType1` int(11) default NULL,
  `sjduochuType2` int(11) default NULL,
  `sjqitaId` int(11) default NULL,
  `sjqitaName` varchar(255) default NULL,
  `sjlaiyuanId` int(11) default NULL,
  `sjlaiyuanName` varchar(255) default NULL,
  `sjduochuImg` varchar(255) default NULL,
  `sjduochuImgName` varchar(255) default NULL,
  `shujuId` int(11) default NULL,
  `shujuName` varchar(255) default NULL,
  `sjleixingId` int(11) default NULL,
  `sjleixingName` varchar(255) default NULL,
  `sjxingtaiId` int(11) default NULL,
  `sjxingtaiName` varchar(255) default NULL,
  `userId` int(11) default NULL,
  `userName` varchar(255) default NULL,
  `bumenId` int(11) default NULL,
  `bumenName` varchar(255) default NULL,
  `buyuanId` int(11) default NULL,
  `buyuanName` varchar(255) default NULL,
  `yonghuId` int(11) default NULL,
  `yonghuName` varchar(255) default NULL,
  `buzhiId` int(11) default NULL,
  `buzhiName` varchar(255) default NULL,
  `roleId` int(11) default NULL,
  `roleName` varchar(255) default NULL,
  PRIMARY KEY  (`sjduochuId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sjduochu
-- ----------------------------
INSERT INTO `t_sjduochu` VALUES ('1', '其他交流1', '<p>&nbsp; &nbsp; 其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流<br/>&nbsp;&nbsp;&nbsp;&nbsp;其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流<br/>&nbsp;&nbsp;&nbsp;&nbsp;其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/16.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', null, null, null, '2026-04-02 11:55:25', null, '0', null, null, null, null, null, '0', null, null, null, null, null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/4.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=4.jpg', null, null, null, null, '1', '其他交流', '1', 'yonghu1', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_sjduochu` VALUES ('2', '其他交流2', '<p>&nbsp; &nbsp; 其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流<br/>&nbsp;&nbsp;&nbsp;&nbsp;其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流<br/>&nbsp;&nbsp;&nbsp;&nbsp;其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/17.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', null, null, null, '2026-04-02 11:55:25', null, '0', null, null, null, null, null, '0', null, null, null, null, null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/5.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=5.jpg', null, null, null, null, '1', '其他交流', '2', 'yonghu2', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_sjduochu` VALUES ('3', '技巧分享1', '<p>&nbsp; &nbsp; 技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享<br/>&nbsp;&nbsp;&nbsp;&nbsp;技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享<br/>&nbsp;&nbsp;&nbsp;&nbsp;技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/18.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', null, null, null, '2026-04-02 11:55:25', null, '0', null, null, null, null, null, '0', null, null, null, null, null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/6.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=6.jpg', null, null, null, null, '2', '技巧分享', '3', 'yonghu3', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_sjduochu` VALUES ('4', '技巧分享2', '<p>&nbsp; &nbsp; 技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享<br/>&nbsp;&nbsp;&nbsp;&nbsp;技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享<br/>&nbsp;&nbsp;&nbsp;&nbsp;技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/8.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', null, null, null, '2026-04-02 11:55:25', null, '0', null, null, null, null, null, '0', null, null, null, null, null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/7.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=7.jpg', null, null, null, null, '2', '技巧分享', '4', 'yonghu4', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_sjduochu` VALUES ('5', '面试经验1', '<p>&nbsp; &nbsp; 面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验<br/>&nbsp;&nbsp;&nbsp;&nbsp;面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验<br/>&nbsp;&nbsp;&nbsp;&nbsp;面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/9.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', null, null, null, '2026-04-02 11:55:25', null, '0', null, null, null, null, null, '0', null, null, null, null, null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/8.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=8.jpg', null, null, null, null, '3', '面试经验', '5', 'yonghu5', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_sjduochu` VALUES ('6', '面试经验2', '<p>&nbsp; &nbsp; 面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验<br/>&nbsp;&nbsp;&nbsp;&nbsp;面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验<br/>&nbsp;&nbsp;&nbsp;&nbsp;面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/10.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', null, null, null, '2026-04-02 11:55:25', null, '0', null, null, null, null, null, '0', null, null, null, null, null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/9.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=9.jpg', null, null, null, null, '3', '面试经验', '6', 'yonghu6', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_sjduochu` VALUES ('7', '职场经验1', '<p>&nbsp; &nbsp; 职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验<br/>&nbsp;&nbsp;&nbsp;&nbsp;职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验<br/>&nbsp;&nbsp;&nbsp;&nbsp;职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/11.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', null, null, null, '2026-04-02 11:55:25', null, '1', null, null, null, null, null, '0', null, null, null, null, null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/10.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=10.jpg', null, null, null, null, '4', '职场经验', '7', 'yonghu7', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_sjduochu` VALUES ('8', '职场经验2', '<p>&nbsp; &nbsp; 职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验<br/>&nbsp;&nbsp;&nbsp;&nbsp;职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验<br/>&nbsp;&nbsp;&nbsp;&nbsp;职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/12.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', null, null, null, '2026-04-02 11:55:25', null, '0', null, null, null, null, null, '0', null, null, null, null, null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/11.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=11.jpg', null, null, null, null, '4', '职场经验', '7', 'yonghu7', null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `t_sjjianchu`
-- ----------------------------
DROP TABLE IF EXISTS `t_sjjianchu`;
CREATE TABLE `t_sjjianchu` (
  `sjjianchuId` int(11) NOT NULL auto_increment,
  `sjjianchuName` varchar(255) default NULL,
  `sjjianchuMark` varchar(2550) default NULL,
  `sjjianchuMark1` varchar(2550) default NULL,
  `sjjianchuMark2` varchar(2550) default NULL,
  `sjjianchuMark3` varchar(2550) default NULL,
  `sjjianchuDate` datetime default NULL,
  `sjjianchuDate1` datetime default NULL,
  `sjjianchuZong` int(11) default NULL,
  `sjjianchuZong1` int(11) default NULL,
  `sjjianchuZong2` int(11) default NULL,
  `sjjianchuDouble` double default NULL,
  `sjjianchuDouble1` double default NULL,
  `sjjianchuDouble2` double default NULL,
  `sjjianchuType` int(11) default NULL,
  `sjjianchuType1` int(11) default NULL,
  `sjjianchuType2` int(11) default NULL,
  `sjqitaId` int(11) default NULL,
  `sjqitaName` varchar(255) default NULL,
  `sjlaiyuanId` int(11) default NULL,
  `sjlaiyuanName` varchar(255) default NULL,
  `sjjianchuImg` varchar(255) default NULL,
  `sjjianchuImgName` varchar(255) default NULL,
  `shujuId` int(11) default NULL,
  `shujuName` varchar(255) default NULL,
  `sjleixingId` int(11) default NULL,
  `sjleixingName` varchar(255) default NULL,
  `sjxingtaiId` int(11) default NULL,
  `sjxingtaiName` varchar(255) default NULL,
  `userId` int(11) default NULL,
  `userName` varchar(255) default NULL,
  `bumenId` int(11) default NULL,
  `bumenName` varchar(255) default NULL,
  `buyuanId` int(11) default NULL,
  `buyuanName` varchar(255) default NULL,
  `yonghuId` int(11) default NULL,
  `yonghuName` varchar(255) default NULL,
  `buzhiId` int(11) default NULL,
  `buzhiName` varchar(255) default NULL,
  `roleId` int(11) default NULL,
  `roleName` varchar(255) default NULL,
  PRIMARY KEY  (`sjjianchuId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sjjianchu
-- ----------------------------
INSERT INTO `t_sjjianchu` VALUES ('1', '阿道夫水水水水水水水水水水水水水水水水水水水水水水水水水水水水水水水水水水水水', null, null, null, null, '2026-04-02 11:39:02', null, null, null, null, null, null, null, '0', null, null, null, null, null, null, null, null, '5', '职位信息5', '5', '其他工作', null, null, '6', 'yonghu6', null, null, null, null, '1', '企业1', null, null, null, null);

-- ----------------------------
-- Table structure for `t_sjleixing`
-- ----------------------------
DROP TABLE IF EXISTS `t_sjleixing`;
CREATE TABLE `t_sjleixing` (
  `sjleixingId` int(11) NOT NULL auto_increment,
  `sjleixingName` varchar(255) default NULL,
  `sjleixingMark` varchar(255) default NULL,
  `sjleixingMark1` varchar(255) default NULL,
  `sjleixingMark2` varchar(255) default NULL,
  `sjleixingPhone` varchar(255) default NULL,
  `sjleixingDizhi` varchar(255) default NULL,
  `sjleixingDate` datetime default NULL,
  `sjleixingDate1` datetime default NULL,
  `sjleixingType` int(11) default NULL,
  `sjleixingType1` int(11) default NULL,
  `sjleixingDouble` double default NULL,
  `sjleixingDouble1` double default NULL,
  PRIMARY KEY  (`sjleixingId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sjleixing
-- ----------------------------
INSERT INTO `t_sjleixing` VALUES ('1', '市场工作', '市场工作市场工作市场工作市场工作市场工作市场工作市场工作市场工作市场工作市场工作市场工作市场工作', null, null, null, null, '2026-04-02 10:59:22', null, null, null, null, null);
INSERT INTO `t_sjleixing` VALUES ('2', '开发工作', '开发工作开发工作开发工作开发工作开发工作开发工作开发工作开发工作开发工作开发工作开发工作开发工作开发工作', null, null, null, null, '2026-04-02 10:59:29', null, null, null, null, null);
INSERT INTO `t_sjleixing` VALUES ('3', '销售工作', '销售工作销售工作销售工作销售工作销售工作销售工作销售工作销售工作销售工作销售工作', null, null, null, null, '2026-04-02 10:59:37', null, null, null, null, null);
INSERT INTO `t_sjleixing` VALUES ('4', '文职工作', '文职工作文职工作文职工作文职工作文职工作文职工作文职工作文职工作文职工作文职工作文职工作文职工作文职工作', null, null, null, null, '2026-04-02 10:59:44', null, null, null, null, null);
INSERT INTO `t_sjleixing` VALUES ('5', '其他工作', '其他工作其他工作其他工作其他工作其他工作其他工作其他工作其他工作其他工作其他工作其他工作其他工作其他工作其他工作其他工作其他工作其他工作其他工作其他工作', null, null, null, null, '2026-04-02 10:59:50', null, null, null, null, null);

-- ----------------------------
-- Table structure for `t_sjlianjie`
-- ----------------------------
DROP TABLE IF EXISTS `t_sjlianjie`;
CREATE TABLE `t_sjlianjie` (
  `sjlianjieId` int(11) NOT NULL auto_increment,
  `sjlianjieName` varchar(255) default NULL,
  `sjlianjieMark` varchar(255) default NULL,
  `sjlianjieMark1` varchar(255) default NULL,
  `sjlianjieMark2` varchar(255) default NULL,
  `sjlianjiePhone` varchar(255) default NULL,
  `sjlianjieDizhi` varchar(255) default NULL,
  `sjlianjieDate` datetime default NULL,
  `sjlianjieDate1` datetime default NULL,
  `sjlianjieType` int(11) default NULL,
  `sjlianjieType1` int(11) default NULL,
  `sjlianjieDouble` double default NULL,
  `sjlianjieDouble1` double default NULL,
  PRIMARY KEY  (`sjlianjieId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sjlianjie
-- ----------------------------
INSERT INTO `t_sjlianjie` VALUES ('1', '淘宝', 'http://www.taobao.com', null, null, null, null, '2026-04-02 10:34:11', null, null, null, null, null);
INSERT INTO `t_sjlianjie` VALUES ('2', '腾讯', 'http://www.qq.com', null, null, null, null, '2026-04-02 10:34:28', null, null, null, null, null);
INSERT INTO `t_sjlianjie` VALUES ('3', '百度', 'http://www.baidu.com', null, null, null, null, '2026-04-02 10:34:38', null, null, null, null, null);
INSERT INTO `t_sjlianjie` VALUES ('4', '京东', 'http://www.jd.com', null, null, null, null, '2026-04-02 10:34:52', null, null, null, null, null);

-- ----------------------------
-- Table structure for `t_sjpinglun`
-- ----------------------------
DROP TABLE IF EXISTS `t_sjpinglun`;
CREATE TABLE `t_sjpinglun` (
  `sjpinglunId` int(11) NOT NULL auto_increment,
  `sjpinglunName` varchar(255) default NULL,
  `sjpinglunMark` varchar(2550) default NULL,
  `sjpinglunMark1` varchar(2550) default NULL,
  `sjpinglunMark2` varchar(2550) default NULL,
  `sjpinglunMark3` varchar(2550) default NULL,
  `sjpinglunDate` datetime default NULL,
  `sjpinglunDate1` datetime default NULL,
  `sjpinglunZong` int(11) default NULL,
  `sjpinglunZong1` int(11) default NULL,
  `sjpinglunZong2` int(11) default NULL,
  `sjpinglunDouble` double default NULL,
  `sjpinglunDouble1` double default NULL,
  `sjpinglunDouble2` double default NULL,
  `sjpinglunType` int(11) default NULL,
  `sjpinglunType1` int(11) default NULL,
  `sjpinglunType2` int(11) default NULL,
  `sjqitaId` int(11) default NULL,
  `sjqitaName` varchar(255) default NULL,
  `sjlaiyuanId` int(11) default NULL,
  `sjlaiyuanName` varchar(255) default NULL,
  `sjpinglunImg` varchar(255) default NULL,
  `sjpinglunImgName` varchar(255) default NULL,
  `shujuId` int(11) default NULL,
  `shujuName` varchar(255) default NULL,
  `sjleixingId` int(11) default NULL,
  `sjleixingName` varchar(255) default NULL,
  `sjxingtaiId` int(11) default NULL,
  `sjxingtaiName` varchar(255) default NULL,
  `userId` int(11) default NULL,
  `userName` varchar(255) default NULL,
  `bumenId` int(11) default NULL,
  `bumenName` varchar(255) default NULL,
  `buyuanId` int(11) default NULL,
  `buyuanName` varchar(255) default NULL,
  `yonghuId` int(11) default NULL,
  `yonghuName` varchar(255) default NULL,
  `buzhiId` int(11) default NULL,
  `buzhiName` varchar(255) default NULL,
  `roleId` int(11) default NULL,
  `roleName` varchar(255) default NULL,
  PRIMARY KEY  (`sjpinglunId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sjpinglun
-- ----------------------------

-- ----------------------------
-- Table structure for `t_sjqita`
-- ----------------------------
DROP TABLE IF EXISTS `t_sjqita`;
CREATE TABLE `t_sjqita` (
  `sjqitaId` int(11) NOT NULL auto_increment,
  `sjqitaName` varchar(255) default NULL,
  `sjqitaMark` longtext,
  `sjqitaMark1` longtext,
  `sjqitaMark2` longtext,
  `sjqitaPhone` varchar(255) default NULL,
  `sjqitaDizhi` varchar(255) default NULL,
  `sjqitaDate` datetime default NULL,
  `sjqitaDate1` datetime default NULL,
  `sjqitaType` int(11) default NULL,
  `sjqitaType1` int(11) default NULL,
  `sjqitaDouble` double default NULL,
  `sjqitaDouble1` double default NULL,
  PRIMARY KEY  (`sjqitaId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sjqita
-- ----------------------------
INSERT INTO `t_sjqita` VALUES ('1', '官方发达的发生发的发的撒啊发的啥地方地方地方的发生', null, null, null, 'yonghu1', '职场经验1', '2026-04-02 12:00:17', null, '1', '7', null, null);

-- ----------------------------
-- Table structure for `t_sjshaochu`
-- ----------------------------
DROP TABLE IF EXISTS `t_sjshaochu`;
CREATE TABLE `t_sjshaochu` (
  `sjshaochuId` int(11) NOT NULL auto_increment,
  `sjshaochuName` varchar(255) default NULL,
  `sjshaochuMark` varchar(2550) default NULL,
  `sjshaochuMark1` varchar(2550) default NULL,
  `sjshaochuMark2` varchar(2550) default NULL,
  `sjshaochuMark3` varchar(2550) default NULL,
  `sjshaochuDate` datetime default NULL,
  `sjshaochuDate1` datetime default NULL,
  `sjshaochuZong` int(11) default NULL,
  `sjshaochuZong1` int(11) default NULL,
  `sjshaochuZong2` int(11) default NULL,
  `sjshaochuDouble` double default NULL,
  `sjshaochuDouble1` double default NULL,
  `sjshaochuDouble2` double default NULL,
  `sjshaochuType` int(11) default NULL,
  `sjshaochuType1` int(11) default NULL,
  `sjshaochuType2` int(11) default NULL,
  `sjqitaId` int(11) default NULL,
  `sjqitaName` varchar(255) default NULL,
  `sjlaiyuanId` int(11) default NULL,
  `sjlaiyuanName` varchar(255) default NULL,
  `sjshaochuImg` varchar(255) default NULL,
  `sjshaochuImgName` varchar(255) default NULL,
  `shujuId` int(11) default NULL,
  `shujuName` varchar(255) default NULL,
  `sjleixingId` int(11) default NULL,
  `sjleixingName` varchar(255) default NULL,
  `sjxingtaiId` int(11) default NULL,
  `sjxingtaiName` varchar(255) default NULL,
  `userId` int(11) default NULL,
  `userName` varchar(255) default NULL,
  `bumenId` int(11) default NULL,
  `bumenName` varchar(255) default NULL,
  `buyuanId` int(11) default NULL,
  `buyuanName` varchar(255) default NULL,
  `yonghuId` int(11) default NULL,
  `yonghuName` varchar(255) default NULL,
  `buzhiId` int(11) default NULL,
  `buzhiName` varchar(255) default NULL,
  `roleId` int(11) default NULL,
  `roleName` varchar(255) default NULL,
  PRIMARY KEY  (`sjshaochuId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sjshaochu
-- ----------------------------
INSERT INTO `t_sjshaochu` VALUES ('1', '13112345675', '学校信息7', '专科', '化学', null, '2026-04-02 11:38:34', null, null, null, null, '5500', null, null, '1', null, null, null, null, null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/shangchuan.docx', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=shangchuan.docx', '13', '职位信息13', '2', '开发工作', null, null, '7', 'yonghu7', null, null, null, null, '1', '企业1', null, null, null, null);
INSERT INTO `t_sjshaochu` VALUES ('2', '13112345674', '学校信息6', '博士', '生物', null, '2026-04-02 11:38:33', null, null, null, null, '6500', null, null, '1', null, null, null, null, null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/shangchuan.docx', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=shangchuan.docx', '5', '职位信息5', '5', '其他工作', null, null, '6', 'yonghu6', null, null, null, null, '1', '企业1', null, null, null, null);
INSERT INTO `t_sjshaochu` VALUES ('3', '13112345673', '学校信息5', '硕士', '地理', null, '2026-04-02 11:36:26', null, null, null, null, '7000', null, null, '1', null, null, null, null, null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/shangchuan.docx', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=shangchuan.docx', '16', '职位信息16', '5', '其他工作', null, null, '5', 'yonghu5', null, null, null, null, '4', '企业4', null, null, null, null);
INSERT INTO `t_sjshaochu` VALUES ('4', '13112345672', '学校信息4', '本科', '历史', null, '2026-04-02 11:36:41', null, null, null, null, '9000', null, null, '1', null, null, null, null, null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/shangchuan.docx', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=shangchuan.docx', '10', '职位信息10', '1', '市场工作', null, null, '4', 'yonghu4', null, null, null, null, '2', '企业2', null, null, null, null);
INSERT INTO `t_sjshaochu` VALUES ('6', '13112345670', '学校信息2', '硕士', '英语', null, '2026-04-02 11:38:33', null, null, null, null, '4500', null, null, '1', null, null, null, null, null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/shangchuan.docx', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=shangchuan.docx', '1', '职位信息1', '1', '市场工作', null, null, '2', 'yonghu2', null, null, null, null, '1', '企业1', null, null, null, null);
INSERT INTO `t_sjshaochu` VALUES ('7', '13112345678', '学校信息1', '本科', '数学', null, '2026-04-02 11:37:44', null, null, null, null, '5000', null, null, '1', null, null, null, null, null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/shangchuan.docx', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=shangchuan.docx', '2', '职位信息2', '2', '开发工作', null, null, '1', 'yonghu1', null, null, null, null, '2', '企业2', null, null, null, null);

-- ----------------------------
-- Table structure for `t_sjxiaoxi`
-- ----------------------------
DROP TABLE IF EXISTS `t_sjxiaoxi`;
CREATE TABLE `t_sjxiaoxi` (
  `sjxiaoxiId` int(11) NOT NULL auto_increment,
  `sjxiaoxiName` varchar(255) default NULL,
  `sjxiaoxiMark` longtext,
  `sjxiaoxiMark1` varchar(255) default NULL,
  `sjxiaoxiMark2` varchar(255) default NULL,
  `sjxiaoxiPhone` varchar(255) default NULL,
  `sjxiaoxiDizhi` varchar(255) default NULL,
  `sjxiaoxiDate` datetime default NULL,
  `sjxiaoxiDate1` datetime default NULL,
  `sjxiaoxiType` int(11) default NULL,
  `sjxiaoxiType1` int(11) default NULL,
  `sjxiaoxiDouble` double default NULL,
  `sjxiaoxiDouble1` double default NULL,
  PRIMARY KEY  (`sjxiaoxiId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sjxiaoxi
-- ----------------------------
INSERT INTO `t_sjxiaoxi` VALUES ('1', '关于我们', '<p>&nbsp; &nbsp; 关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们<br/>&nbsp;&nbsp;&nbsp;&nbsp;关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们<br/>&nbsp;&nbsp;&nbsp;&nbsp;关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/20.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', null, null, null, null, '2026-04-02 10:33:47', null, null, null, null, null);
INSERT INTO `t_sjxiaoxi` VALUES ('2', '联系我们', '<p>&nbsp; &nbsp; 联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们<br/>&nbsp;&nbsp;&nbsp;&nbsp;联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们<br/>&nbsp;&nbsp;&nbsp;&nbsp;联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/19.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', null, null, null, null, '2026-04-02 10:33:28', null, null, null, null, null);

-- ----------------------------
-- Table structure for `t_sjxingtai`
-- ----------------------------
DROP TABLE IF EXISTS `t_sjxingtai`;
CREATE TABLE `t_sjxingtai` (
  `sjxingtaiId` int(11) NOT NULL auto_increment,
  `sjxingtaiName` varchar(255) default NULL,
  `sjxingtaiMark` varchar(255) default NULL,
  `sjxingtaiMark1` varchar(255) default NULL,
  `sjxingtaiMark2` varchar(255) default NULL,
  `sjxingtaiPhone` varchar(255) default NULL,
  `sjxingtaiDizhi` varchar(255) default NULL,
  `sjxingtaiDate` datetime default NULL,
  `sjxingtaiDate1` datetime default NULL,
  `sjxingtaiType` int(11) default NULL,
  `sjxingtaiType1` int(11) default NULL,
  `sjxingtaiDouble` double default NULL,
  `sjxingtaiDouble1` double default NULL,
  PRIMARY KEY  (`sjxingtaiId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sjxingtai
-- ----------------------------
INSERT INTO `t_sjxingtai` VALUES ('1', '其他交流', '其他交流其他交流其他交流其他交流其他交流其他交流', null, null, null, null, '2026-04-02 11:50:59', null, null, null, null, null);
INSERT INTO `t_sjxingtai` VALUES ('2', '技巧分享', '技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享技巧分享', null, null, null, null, '2026-04-02 11:51:13', null, null, null, null, null);
INSERT INTO `t_sjxingtai` VALUES ('3', '面试经验', '面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验面试经验', null, null, null, null, '2026-04-02 11:51:24', null, null, null, null, null);
INSERT INTO `t_sjxingtai` VALUES ('4', '职场经验', '职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验职场经验', null, null, null, null, '2026-04-02 11:51:35', null, null, null, null, null);

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userId` int(11) NOT NULL auto_increment,
  `userName` varchar(255) default NULL,
  `userPassword` varchar(255) default NULL,
  `userXingming` varchar(255) default NULL,
  `userSex` int(11) default NULL,
  `userAge` int(11) default NULL,
  `userMinzu` varchar(255) default NULL,
  `userPhone` varchar(255) default NULL,
  `userMark` varchar(2550) default NULL,
  `userMark1` varchar(255) default NULL,
  `userMark2` varchar(255) default NULL,
  `userMark3` varchar(255) default NULL,
  `userMark4` varchar(255) default NULL,
  `userDate` datetime default NULL,
  `userDate1` datetime default NULL,
  `userDate2` datetime default NULL,
  `userType` int(11) default NULL,
  `userType1` int(11) default NULL,
  `userType2` int(11) default NULL,
  `userDouble` double default NULL,
  `userDouble1` double default NULL,
  `userDouble2` double default NULL,
  `userZong` int(11) default NULL,
  `userZong1` int(11) default NULL,
  `userZong2` int(11) default NULL,
  `userImg` varchar(255) default NULL,
  `userImgName` varchar(255) default NULL,
  `roleId` int(11) default NULL,
  `roleName` varchar(255) default NULL,
  `bumenId` int(11) default NULL,
  `bumenName` varchar(255) default NULL,
  `buyuanId` int(11) default NULL,
  `buyuanName` varchar(255) default NULL,
  `buzhiId` int(11) default NULL,
  `buzhiName` varchar(255) default NULL,
  PRIMARY KEY  (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'yonghu1', 'yonghu1', 'yonghu1', '0', '22', null, '13112345678', '学校信息1', '本科', '数学', null, '1,14,13,12,2', '2026-04-02 11:19:57', null, null, '0', null, '1', null, null, null, null, null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/shangchuan.docx', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=shangchuan.docx', null, null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('2', 'yonghu2', 'yonghu2', 'yonghu2', '1', '21', null, '13112345670', '学校信息2', '硕士', '英语', null, '2,1', '2026-04-02 11:19:57', null, null, '0', null, '1', null, null, null, null, null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/shangchuan.docx', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=shangchuan.docx', null, null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('3', 'yonghu3', 'yonghu3', 'yonghu3', '0', '22', null, '13112345671', '学校信息3', '博士', '语文', null, '3,2,9', '2026-04-02 11:19:57', null, null, '0', null, '1', null, null, null, null, null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/shangchuan.docx', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=shangchuan.docx', null, null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('4', 'yonghu4', 'yonghu4', 'yonghu4', '1', '23', null, '13112345672', '学校信息4', '本科', '历史', null, '1,14,13,12,11', '2026-04-02 11:19:57', null, null, '0', null, '1', null, null, null, null, null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/shangchuan.docx', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=shangchuan.docx', null, null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('5', 'yonghu5', 'yonghu5', 'yonghu5', '0', '21', null, '13112345673', '学校信息5', '硕士', '地理', null, '5,16', '2026-04-02 11:19:57', null, null, '0', null, '1', null, null, null, null, null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/shangchuan.docx', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=shangchuan.docx', null, null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('6', 'yonghu6', 'yonghu6', 'yonghu6', '1', '22', null, '13112345674', '学校信息6', '博士', '生物', null, '1,14,13,12,1', '2026-04-02 11:19:57', null, null, '0', null, '1', null, null, null, null, null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/shangchuan.docx', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=shangchuan.docx', null, null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('7', 'yonghu7', 'yonghu7', 'yonghu7', '1', '22', null, '13112345675', '学校信息7', '专科', '化学', null, '7,13', '2026-04-02 11:19:57', null, null, '0', null, '1', null, null, null, null, null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/shangchuan.docx', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=shangchuan.docx', null, null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('16', 'user11', 'user11', 'user11', '0', '22', null, '13012345678', '学校信息1', '学历信息1', '专业信息1', null, null, '2026-04-02 00:45:57', null, null, '0', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('17', 'user12', 'user12', 'user12', '1', '22', null, '13012345678', '学校信息2', '学历信息2', '专业信息2', null, null, '2026-04-02 00:45:57', null, null, '0', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('18', 'user13', 'user13', 'user13', '0', '22', null, '13012345678', '学校信息3', '学历信息3', '专业信息3', null, null, '2026-04-02 00:45:57', null, null, '0', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('19', 'user14', 'user14', 'user14', '1', '22', null, '13012345678', '学校信息4', '学历信息4', '专业信息4', null, null, '2026-04-02 00:45:57', null, null, '0', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('20', 'user15', 'user15', 'user15', '0', '22', null, '13012345678', '学校信息5', '学历信息5', '专业信息5', null, null, '2026-04-02 00:45:57', null, null, '0', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('21', 'user16', 'user16', 'user16', '1', '22', null, '13012345678', '学校信息6', '学历信息6', '专业信息6', null, null, '2026-04-02 00:45:57', null, null, '0', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('22', 'user17', 'user17', 'user17', '0', '22', null, '13012345678', '学校信息7', '学历信息7', '专业信息7', null, null, '2026-04-02 00:45:57', null, null, '0', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('23', 'user18', 'user18', 'user18', '1', '22', null, '13012345678', '学校信息8', '学历信息8', '专业信息8', null, null, '2026-04-02 00:45:57', null, null, '0', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `t_uxinxi`
-- ----------------------------
DROP TABLE IF EXISTS `t_uxinxi`;
CREATE TABLE `t_uxinxi` (
  `uxinxiId` int(11) NOT NULL auto_increment,
  `uxinxiName` varchar(255) default NULL,
  `uxinxiMark` varchar(2550) default NULL,
  `uxinxiMark1` varchar(2550) default NULL,
  `uxinxiMark2` varchar(2550) default NULL,
  `uxinxiImg` varchar(255) default NULL,
  `uxinxiImgName` varchar(255) default NULL,
  `uxinxiDate` datetime default NULL,
  `uxinxiZong` int(11) default NULL,
  `uxinxiZong1` int(11) default NULL,
  `uxinxiZong2` int(11) default NULL,
  `uxinxiDouble` double default NULL,
  `uxinxiDouble1` double default NULL,
  `uxinxiDouble2` double default NULL,
  `uxinxiType` int(11) default NULL,
  `uxinxiType1` int(11) default NULL,
  `uxinxiType2` int(11) default NULL,
  `uxtypeId` int(11) default NULL,
  `uxtypeName` varchar(255) default NULL,
  `userId` int(11) default NULL,
  `userName` varchar(255) default NULL,
  `bumenId` int(11) default NULL,
  `bumenName` varchar(255) default NULL,
  `buyuanId` int(11) default NULL,
  `buyuanName` varchar(255) default NULL,
  `buzhiId` int(11) default NULL,
  `buzhiName` varchar(255) default NULL,
  `roleId` int(11) default NULL,
  `roleName` varchar(255) default NULL,
  PRIMARY KEY  (`uxinxiId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_uxinxi
-- ----------------------------
INSERT INTO `t_uxinxi` VALUES ('1', '就业信息1', '就业信息就业信息就业信息', '就业信息就业信息就业信息就业信息就业信息就业信息', '就业信息就业信息就业信息就业信息就业信息就业信息', 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260324101405_695.docx', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260324101405_695.docx', '2026-04-02 10:14:07', null, null, null, '6000', null, null, '1', null, null, '4', '外企', '2', 'yonghu2', null, null, null, null, null, null, null, null);
INSERT INTO `t_uxinxi` VALUES ('2', '就业信息2', '就业信息就业信息就业信息', '就业信息就业信息就业信息就业信息就业信息就业信息', '就业信息就业信息就业信息就业信息就业信息就业信息', 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260324101405_695.docx', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260324101405_695.docx', '2026-04-02 10:14:07', null, null, null, '8000', null, null, '1', null, null, '4', '外企', '7', 'yonghu7', null, null, null, null, null, null, null, null);
INSERT INTO `t_uxinxi` VALUES ('3', '就业信息3', '就业信息就业信息就业信息', '就业信息就业信息就业信息就业信息就业信息就业信息', '就业信息就业信息就业信息就业信息就业信息就业信息', 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260324101405_695.docx', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260324101405_695.docx', '2026-04-02 10:14:07', null, null, null, '6000', null, null, '1', null, null, '1', '创业', '1', 'yonghu1', null, null, null, null, null, null, null, null);
INSERT INTO `t_uxinxi` VALUES ('4', '就业信息4', '就业信息就业信息就业信息', '就业信息就业信息就业信息就业信息就业信息就业信息', '就业信息就业信息就业信息就业信息就业信息就业信息', 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260324101405_695.docx', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260324101405_695.docx', '2026-04-02 10:14:07', null, null, null, '8000', null, null, '1', null, null, '2', '私企', '2', 'yonghu2', null, null, null, null, null, null, null, null);
INSERT INTO `t_uxinxi` VALUES ('5', '就业信息5', '就业信息就业信息就业信息', '就业信息就业信息就业信息就业信息就业信息就业信息', '就业信息就业信息就业信息就业信息就业信息就业信息', 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260324101405_695.docx', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260324101405_695.docx', '2026-04-02 10:14:07', null, null, null, '6000', null, null, '1', null, null, '3', '国企', '3', 'yonghu3', null, null, null, null, null, null, null, null);
INSERT INTO `t_uxinxi` VALUES ('6', '就业信息6', '就业信息就业信息就业信息', '就业信息就业信息就业信息就业信息就业信息就业信息', '就业信息就业信息就业信息就业信息就业信息就业信息', 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260324101405_695.docx', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260324101405_695.docx', '2026-04-02 10:14:07', null, null, null, '8000', null, null, '1', null, null, '4', '外企', '4', 'yonghu4', null, null, null, null, null, null, null, null);
INSERT INTO `t_uxinxi` VALUES ('7', '就业信息7', '就业信息就业信息就业信息', '就业信息就业信息就业信息就业信息就业信息就业信息', '就业信息就业信息就业信息就业信息就业信息就业信息', 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260324101405_695.docx', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260324101405_695.docx', '2026-04-02 10:14:07', null, null, null, '6000', null, null, '1', null, null, '1', '创业', '5', 'yonghu5', null, null, null, null, null, null, null, null);
INSERT INTO `t_uxinxi` VALUES ('8', '就业信息8', '就业信息就业信息就业信息', '就业信息就业信息就业信息就业信息就业信息就业信息', '就业信息就业信息就业信息就业信息就业信息就业信息', 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260324101405_695.docx', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260324101405_695.docx', '2026-04-02 10:14:07', null, null, null, '6000', null, null, '1', null, null, '1', '创业', '6', 'yonghu6', null, null, null, null, null, null, null, null);
INSERT INTO `t_uxinxi` VALUES ('9', '就业信息9', '就业信息就业信息就业信息', '就业信息就业信息就业信息就业信息就业信息就业信息', '就业信息就业信息就业信息就业信息就业信息就业信息', 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260324101405_695.docx', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260324101405_695.docx', '2026-04-02 10:14:07', null, null, null, '8000', null, null, '1', null, null, '1', '创业', '7', 'yonghu7', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `t_uxtype`
-- ----------------------------
DROP TABLE IF EXISTS `t_uxtype`;
CREATE TABLE `t_uxtype` (
  `uxtypeId` int(11) NOT NULL auto_increment,
  `uxtypeName` varchar(255) default NULL,
  `uxtypeMark` varchar(255) default NULL,
  PRIMARY KEY  (`uxtypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_uxtype
-- ----------------------------
INSERT INTO `t_uxtype` VALUES ('1', '创业', '创业');
INSERT INTO `t_uxtype` VALUES ('2', '私企', '私企');
INSERT INTO `t_uxtype` VALUES ('3', '国企', '国企');
INSERT INTO `t_uxtype` VALUES ('4', '外企', '外企');

-- ----------------------------
-- Table structure for `t_uyijian`
-- ----------------------------
DROP TABLE IF EXISTS `t_uyijian`;
CREATE TABLE `t_uyijian` (
  `uyijianId` int(11) NOT NULL auto_increment,
  `uyijianName` varchar(255) default NULL,
  `uyijianMark` varchar(2550) default NULL,
  `uyijianMark1` varchar(2550) default NULL,
  `uyijianMark2` varchar(2550) default NULL,
  `uyijianImg` varchar(255) default NULL,
  `uyijianImgName` varchar(255) default NULL,
  `uyijianDate` datetime default NULL,
  `uyijianZong` int(11) default NULL,
  `uyijianZong1` int(11) default NULL,
  `uyijianZong2` int(11) default NULL,
  `uyijianDouble` double default NULL,
  `uyijianDouble1` double default NULL,
  `uyijianDouble2` double default NULL,
  `uyijianType` int(11) default NULL,
  `uyijianType1` int(11) default NULL,
  `uyijianType2` int(11) default NULL,
  `uxtypeId` int(11) default NULL,
  `uxtypeName` varchar(255) default NULL,
  `userId` int(11) default NULL,
  `userName` varchar(255) default NULL,
  `bumenId` int(11) default NULL,
  `bumenName` varchar(255) default NULL,
  `buyuanId` int(11) default NULL,
  `buyuanName` varchar(255) default NULL,
  `buzhiId` int(11) default NULL,
  `buzhiName` varchar(255) default NULL,
  `roleId` int(11) default NULL,
  `roleName` varchar(255) default NULL,
  PRIMARY KEY  (`uyijianId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_uyijian
-- ----------------------------
INSERT INTO `t_uyijian` VALUES ('1', '反馈信息', '反馈信息反馈信息反馈信息反馈信息反馈信息反馈信息反馈信息反馈信息反馈信息反馈信息反馈信息', null, null, null, null, '2026-04-02 11:31:17', null, null, null, null, null, null, null, null, null, null, null, '6', 'yonghu6', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `t_yonghu`
-- ----------------------------
DROP TABLE IF EXISTS `t_yonghu`;
CREATE TABLE `t_yonghu` (
  `yonghuId` int(11) NOT NULL auto_increment,
  `yonghuName` varchar(255) default NULL,
  `yonghuPassword` varchar(255) default NULL,
  `yonghuXingming` varchar(255) default NULL,
  `yonghuSex` int(11) default NULL,
  `yonghuAge` int(11) default NULL,
  `yonghuPhone` varchar(255) default NULL,
  `yonghuMinzu` varchar(255) default NULL,
  `yonghuMark` longtext,
  `yonghuMark1` varchar(255) default NULL,
  `yonghuMark2` varchar(255) default NULL,
  `yonghuMark3` varchar(255) default NULL,
  `yonghuMark4` varchar(255) default NULL,
  `yonghuDate` datetime default NULL,
  `yonghuDate1` datetime default NULL,
  `yonghuDate2` datetime default NULL,
  `yonghuType` int(11) default NULL,
  `yonghuType1` int(11) default NULL,
  `yonghuType2` int(11) default NULL,
  `yonghuDouble` double default NULL,
  `yonghuDouble1` double default NULL,
  `yonghuDouble2` double default NULL,
  `yonghuZong` int(11) default NULL,
  `yonghuZong1` int(11) default NULL,
  `yonghuZong2` int(11) default NULL,
  `yonghuImg` varchar(255) default NULL,
  `yonghuImgName` varchar(255) default NULL,
  `userId` int(11) default NULL,
  `userName` varchar(255) default NULL,
  `bumenId` int(11) default NULL,
  `bumenName` varchar(255) default NULL,
  `buyuanId` int(11) default NULL,
  `buyuanName` varchar(255) default NULL,
  `buzhiId` int(11) default NULL,
  `buzhiName` varchar(255) default NULL,
  PRIMARY KEY  (`yonghuId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_yonghu
-- ----------------------------
INSERT INTO `t_yonghu` VALUES ('1', 'qiyexinxi1', 'qiyexinxi1', '企业1', '0', '33', '13012345678', null, '<p>&nbsp; &nbsp; 企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/18.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', '企业1', '企业1', null, null, '2026-04-02 10:51:38', null, null, '0', null, null, null, null, null, '0', null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/18.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=18.jpg', null, null, null, null, null, null, '1', '国企信息');
INSERT INTO `t_yonghu` VALUES ('2', 'qiyexinxi2', 'qiyexinxi2', '企业2', '1', '34', '13012345679', null, '<p>&nbsp; &nbsp; 企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/17.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', '企业2', '企业2', null, null, '2026-04-02 10:51:38', null, null, '1', null, null, null, null, null, '0', null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/17.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=17.jpg', null, null, null, null, null, null, '2', '外企信息');
INSERT INTO `t_yonghu` VALUES ('3', 'qiyexinxi3', 'qiyexinxi3', '企业3', '0', '35', '13012345676', null, '<p>&nbsp; &nbsp; 企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/16.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', '企业3', '企业3', null, null, '2026-04-02 10:51:38', null, null, '1', null, null, null, null, null, '0', null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/16.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=16.jpg', null, null, null, null, null, null, '3', '私营企业');
INSERT INTO `t_yonghu` VALUES ('4', 'qiyexinxi4', 'qiyexinxi4', '企业4', '1', '36', '13012345677', null, '<p>&nbsp; &nbsp; 企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息<br/>&nbsp;&nbsp;&nbsp;&nbsp;企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息企业信息</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/15.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', '企业4', '企业4', null, null, '2026-04-02 10:51:38', null, null, '1', null, null, null, null, null, '0', null, null, 'http://127.0.0.1:8080/vs263xianzhaopins/file/15.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=15.jpg', null, null, null, null, null, null, '4', '股份公司');

-- ----------------------------
-- Table structure for `t_yxinxi`
-- ----------------------------
DROP TABLE IF EXISTS `t_yxinxi`;
CREATE TABLE `t_yxinxi` (
  `yxinxiId` int(11) NOT NULL auto_increment,
  `yxinxiName` varchar(255) default NULL,
  `yxinxiMark` varchar(2550) default NULL,
  `yxinxiMark1` varchar(2550) default NULL,
  `yxinxiMark2` varchar(2550) default NULL,
  `yxinxiImg` varchar(255) default NULL,
  `yxinxiImgName` varchar(255) default NULL,
  `yxinxiDate` datetime default NULL,
  `yxinxiZong` int(11) default NULL,
  `yxinxiZong1` int(11) default NULL,
  `yxinxiZong2` int(11) default NULL,
  `yxinxiDouble` double default NULL,
  `yxinxiDouble1` double default NULL,
  `yxinxiDouble2` double default NULL,
  `yxinxiType` int(11) default NULL,
  `yxinxiType1` int(11) default NULL,
  `yxinxiType2` int(11) default NULL,
  `yxtypeId` int(11) default NULL,
  `yxtypeName` varchar(255) default NULL,
  `yonghuId` int(11) default NULL,
  `yonghuName` varchar(255) default NULL,
  `userId` int(11) default NULL,
  `userName` varchar(255) default NULL,
  `bumenId` int(11) default NULL,
  `bumenName` varchar(255) default NULL,
  `buyuanId` int(11) default NULL,
  `buyuanName` varchar(255) default NULL,
  `buzhiId` int(11) default NULL,
  `buzhiName` varchar(255) default NULL,
  `roleId` int(11) default NULL,
  `roleName` varchar(255) default NULL,
  PRIMARY KEY  (`yxinxiId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_yxinxi
-- ----------------------------
INSERT INTO `t_yxinxi` VALUES ('1', '大发撒飒飒飒飒飒飒飒飒飒飒飒飒啊啊撒阿萨飒飒飒飒撒阿萨飒飒撒飒飒啊飒飒', '发的谁谁谁谁谁谁谁谁谁水水水水水水水水水水水水水水水水水水水水谁谁谁水水水水水水水水水水水水', null, null, null, null, '2026-04-02 11:38:39', null, null, null, null, null, null, '0', null, null, null, null, '1', '企业1', '6', 'yonghu6', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `t_yyijian`
-- ----------------------------
DROP TABLE IF EXISTS `t_yyijian`;
CREATE TABLE `t_yyijian` (
  `yyijianId` int(11) NOT NULL auto_increment,
  `yyijianName` varchar(255) default NULL,
  `yyijianMark` varchar(2550) default NULL,
  `yyijianMark1` varchar(2550) default NULL,
  `yyijianMark2` varchar(2550) default NULL,
  `yyijianImg` varchar(255) default NULL,
  `yyijianImgName` varchar(255) default NULL,
  `yyijianDate` datetime default NULL,
  `yyijianZong` int(11) default NULL,
  `yyijianZong1` int(11) default NULL,
  `yyijianZong2` int(11) default NULL,
  `yyijianDouble` double default NULL,
  `yyijianDouble1` double default NULL,
  `yyijianDouble2` double default NULL,
  `yyijianType` int(11) default NULL,
  `yyijianType1` int(11) default NULL,
  `yyijianType2` int(11) default NULL,
  `yxtypeId` int(11) default NULL,
  `yxtypeName` varchar(255) default NULL,
  `yonghuId` int(11) default NULL,
  `yonghuName` varchar(255) default NULL,
  `userId` int(11) default NULL,
  `userName` varchar(255) default NULL,
  `bumenId` int(11) default NULL,
  `bumenName` varchar(255) default NULL,
  `buyuanId` int(11) default NULL,
  `buyuanName` varchar(255) default NULL,
  `buzhiId` int(11) default NULL,
  `buzhiName` varchar(255) default NULL,
  `roleId` int(11) default NULL,
  `roleName` varchar(255) default NULL,
  PRIMARY KEY  (`yyijianId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_yyijian
-- ----------------------------
