/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50087
 Source Host           : localhost:3306
 Source Schema         : db_vs263xianzhaopins

 Target Server Type    : MySQL
 Target Server Version : 50087
 File Encoding         : 65001

 Date: 05/05/2026 04:21:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `adminId` int(11) NOT NULL AUTO_INCREMENT,
  `adminName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `adminPassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `adminXingming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `adminSex` int(11) DEFAULT NULL,
  `adminAge` int(11) DEFAULT NULL,
  `adminPhone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `adminMark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `adminMark1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `adminMark2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `adminMark3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `adminMark4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `adminDate` datetime DEFAULT NULL,
  `adminDate1` datetime DEFAULT NULL,
  `adminDate2` datetime DEFAULT NULL,
  `adminType` int(11) DEFAULT NULL,
  `adminType1` int(11) DEFAULT NULL,
  `adminType2` int(11) DEFAULT NULL,
  `adminDouble` double DEFAULT NULL,
  `adminDouble1` double DEFAULT NULL,
  `adminDouble2` double DEFAULT NULL,
  `adminZong` int(11) DEFAULT NULL,
  `adminZong1` int(11) DEFAULT NULL,
  `adminZong2` int(11) DEFAULT NULL,
  `adminImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `adminImgName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY USING BTREE (`adminId`)
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_bumen
-- ----------------------------
DROP TABLE IF EXISTS `t_bumen`;
CREATE TABLE `t_bumen`  (
  `bumenId` int(11) NOT NULL AUTO_INCREMENT,
  `bumenName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bumenMark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bumenMark1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bumenMark2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bumenType` int(11) DEFAULT NULL,
  `bumenType1` int(11) DEFAULT NULL,
  `bumenType2` int(11) DEFAULT NULL,
  `bumenDouble` double DEFAULT NULL,
  `bumenDouble1` double DEFAULT NULL,
  `bumenDouble2` double DEFAULT NULL,
  `buyuanId` int(11) DEFAULT NULL,
  `buyuanName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY USING BTREE (`bumenId`)
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_bumen
-- ----------------------------
INSERT INTO `t_bumen` VALUES (1, '职位信息14', '职位信息14', '职位信息14', '职位信息14', NULL, 1, 14, 6000, NULL, NULL, NULL, NULL);
INSERT INTO `t_bumen` VALUES (2, '职位信息13', '职位信息13', '职位信息13', '职位信息13', NULL, 7, 13, 5500, NULL, NULL, NULL, NULL);
INSERT INTO `t_bumen` VALUES (3, '职位信息16', '职位信息16', '职位信息16', '职位信息16', NULL, 5, 16, 7000, NULL, NULL, NULL, NULL);
INSERT INTO `t_bumen` VALUES (4, '职位信息2', '职位信息2', '职位信息2', '职位信息2', NULL, 3, 2, 5000, NULL, NULL, NULL, NULL);
INSERT INTO `t_bumen` VALUES (5, '职位信息1', '职位信息1', '职位信息1', '职位信息1', NULL, 2, 1, 4500, NULL, NULL, NULL, NULL);
INSERT INTO `t_bumen` VALUES (6, '职位信息2', '职位信息2', '职位信息2', '职位信息2', NULL, 1, 2, 5000, NULL, NULL, NULL, NULL);
INSERT INTO `t_bumen` VALUES (7, '职位信息9', '职位信息9', '职位信息9', '职位信息9', NULL, 3, 9, 8500, NULL, NULL, NULL, NULL);
INSERT INTO `t_bumen` VALUES (8, '职位信息15', '职位信息15', '职位信息15', '职位信息15', NULL, 25, 15, 6500, NULL, NULL, NULL, NULL);
INSERT INTO `t_bumen` VALUES (9, 'Java 后端开发工程师 (应届)', '本科及以上', '西安', '熟悉 Java 基础（集合、IO、多线程）；掌握 Spring Boot 3.x 框架；熟悉 MySQL 索引优化及 Redis 常用数据结构；了解 Linux 常用命令及 OceanBase 或其他分布式数据库者优先。', NULL, 2, 17, 12000, NULL, NULL, NULL, NULL);
INSERT INTO `t_bumen` VALUES (10, '会计助理', '本科', '北京', '初级会计资格证', NULL, 3, 29, 9000, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_buyuan
-- ----------------------------
DROP TABLE IF EXISTS `t_buyuan`;
CREATE TABLE `t_buyuan`  (
  `buyuanId` int(11) NOT NULL AUTO_INCREMENT,
  `buyuanName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buyuanMark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buyuanMark1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buyuanMark2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buyuanType` int(11) DEFAULT NULL,
  `buyuanType1` int(11) DEFAULT NULL,
  `buyuanType2` int(11) DEFAULT NULL,
  `buyuanDouble` double DEFAULT NULL,
  `buyuanDouble1` double DEFAULT NULL,
  `buyuanDouble2` double DEFAULT NULL,
  PRIMARY KEY USING BTREE (`buyuanId`)
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_buyuan
-- ----------------------------
INSERT INTO `t_buyuan` VALUES (1, NULL, 'yonghu1', '职场经验1', NULL, 1, 7, NULL, NULL, NULL, NULL);
INSERT INTO `t_buyuan` VALUES (2, NULL, '王显坤', '职场经验1', NULL, 2, 7, NULL, NULL, NULL, NULL);
INSERT INTO `t_buyuan` VALUES (3, NULL, '王显坤', '用户测试发布交流', NULL, 2, 3, NULL, NULL, NULL, NULL);
INSERT INTO `t_buyuan` VALUES (4, NULL, '张三', '其他交流2', NULL, 3, 2, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_buzhi
-- ----------------------------
DROP TABLE IF EXISTS `t_buzhi`;
CREATE TABLE `t_buzhi`  (
  `buzhiId` int(11) NOT NULL AUTO_INCREMENT,
  `buzhiName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buzhiMark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buzhiMark1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buzhiMark2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buzhiType` int(11) DEFAULT NULL,
  `buzhiType1` int(11) DEFAULT NULL,
  `buzhiType2` int(11) DEFAULT NULL,
  `buzhiDouble` double DEFAULT NULL,
  `buzhiDouble1` double DEFAULT NULL,
  `buzhiDouble2` double DEFAULT NULL,
  `buyuanId` int(11) DEFAULT NULL,
  `buyuanName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bumenId` int(11) DEFAULT NULL,
  `bumenName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY USING BTREE (`buzhiId`)
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_buzhi
-- ----------------------------
INSERT INTO `t_buzhi` VALUES (1, '国企信息', '国企信息', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_buzhi` VALUES (2, '外企信息', '外企信息', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_buzhi` VALUES (3, '私营企业', '私营企业', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_buzhi` VALUES (4, '股份公司', '股份公司', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_ggtype
-- ----------------------------
DROP TABLE IF EXISTS `t_ggtype`;
CREATE TABLE `t_ggtype`  (
  `ggtypeId` int(11) NOT NULL AUTO_INCREMENT,
  `ggtypeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ggtypeMark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ggtypeId`)
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_ggtype
-- ----------------------------
INSERT INTO `t_ggtype` VALUES (1, '网站公告', '用于向用户实时发布系统动态、重要通知及新闻资讯的官方信息展示窗口');
INSERT INTO `t_ggtype` VALUES (2, '新闻信息', '对应届毕业生就业政策、校园招聘动态及行业职场资讯的实时发布与传播');
INSERT INTO `t_ggtype` VALUES (3, '就业资讯', '为毕业生提供职业规划、政策解读、简历面试指导及心理疏导的专业化服务，旨在提升求职技能并帮助学生精准实现高质量就业。');

-- ----------------------------
-- Table structure for t_gonggao
-- ----------------------------
DROP TABLE IF EXISTS `t_gonggao`;
CREATE TABLE `t_gonggao`  (
  `gonggaoId` int(11) NOT NULL AUTO_INCREMENT,
  `gonggaoName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gonggaoMark` longtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `gonggaoImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gonggaoImgName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gonggaoDate` datetime DEFAULT NULL,
  `ggtypeId` int(11) DEFAULT NULL,
  `ggtypeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY USING BTREE (`gonggaoId`)
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_gonggao
-- ----------------------------
INSERT INTO `t_gonggao` VALUES (6, '关于公布毕业生就业工作监督举报电话的通告', '<p>按照《教育部关于做好2025届全国普通高校毕业生就业创业工作的通知》（教就业〔2024〕5号）就业工作要求，切实维护毕业生就业权益，进一步规范学校毕业生就业管理工作，现将教育部就业工作“四不准”要求通告如下：</p><p>&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;不准以各种方式强迫毕业生签订就业协议和劳动合同；</p><p>&nbsp; &nbsp; &nbsp; &nbsp;不准将毕业证书、学位证书发放与毕业生签约挂钩；</p><p>&nbsp; &nbsp; &nbsp; &nbsp;不准以户档托管为由劝说毕业生签订虚假就业协议；</p><p>&nbsp; &nbsp; &nbsp; &nbsp;不准将毕业生顶岗实习、见习证明材料作为就业证明材料。</p><p>&nbsp; &nbsp; &nbsp; &nbsp;学校广大师生进行监督，如发现违规，请实名向就业指导中心反映、举报。</p><p><br/></p><p><strong>一、就业指导中心举报方式：</strong><strong></strong><strong></strong></p><p>&nbsp; &nbsp; &nbsp; &nbsp;举报电话：029-82628219、82628371</p><p>&nbsp; &nbsp; &nbsp; &nbsp;举报邮箱：hqjyjob@163.com</p><p><strong>二、毕业生就业状态查询</strong><strong></strong><strong></strong></p><p>&nbsp; &nbsp; &nbsp; 毕业生可通过新职业网（www.ncss.org.cn）或学信网（www.chsi.com.cn）查询反馈本人就业状况。</p>', 'http://127.0.0.1:8080/vs263xianzhaopins/file/26.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=26.jpg', '2026-04-12 01:37:19', 1, '网站公告');
INSERT INTO `t_gonggao` VALUES (7, '春分之际一日两会，春风启新程！', '<p>3月20日，学校联合西安曲江新区管理委员会、西安市新城区人力资源和社会保障局在学校幸福校区和天坛遗址公园分别举办的“春风启新程”2026届毕业生春季校园招聘会顺利进行。上午，幸福校区汇聚102家用人单位携3600余个岗位进校揽才；下午，曲江专场吸引到50余家用人单位近千余个就业岗位供广大毕业生挑选。全天候就业供给涵盖智能制造、土建机电、互联网信息、文化教育、金融服务等多个重点行业领域，形成了层次丰富、选择多元的岗位全景图，力求为每一位到场的2026届毕业生提供与自身专业契合、与职业规划匹配的机会。</p><p><img src=\"https://o.bysjy.com.cn/new_govern/yxqqnn2600000045/2026/notice/1774312131-4363.jpg\" alt=\"\" width=\"30%\"/></p><p>两地招聘会现场设置了就业服务区、就业政策宣传区等就业服务点，为求职学生与用人单位提供政策咨询、简历指导、岗位匹配等一站式服务。现场解答毕业生关于就业手续办理、基层项目、人才政策等方面的疑问，切实提升毕业生求职效率与就业成功率。</p><p><img src=\"https://o.bysjy.com.cn/new_govern/yxqqnn2600000045/2026/notice/1774312147-1774.jpg\" alt=\"\" width=\"30%\"/></p><p>自2026届毕业生就业工作启动以来，学校已累计举办各类规模招聘会近150场，构建起场次密集、覆盖全面的招聘活动矩阵。同时，学校持续提升就业服务精准度。依托两校区“高校就业服务驿站”，常态化开展简历诊断、面试辅导等一对一服务，并结合毕业生专业特点与求职意向，分类推送岗位信息，实现从“广覆盖”向“精准达”转变。</p><p><br/></p><p>当前，学校2026届毕业生春季就业工作已全面启动，学校将持续深化校企合作、加密就业活动频次、强化精准就业指导、帮扶重点群体等举措，以务实举措全力跑出就业“加速度”，助推2026届毕业生实现更高质量、更充分的就业。</p>', 'http://127.0.0.1:8080/vs263xianzhaopins/file/27.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=27.jpg', '2026-04-12 01:38:00', 2, '新闻信息');
INSERT INTO `t_gonggao` VALUES (8, '“金秋筑梦季 职引新篇章”——西安建筑科技大学华清学院2026届毕业生综合类就业双选会邀请函', '<p>尊敬的用人单位：<br/><br/>衷心感谢贵单位多年来对我校学生就业工作的大力支持。为进一步加强2026届毕业生就业服务工作，搭建毕业生与用人单位双向选择平台，助力毕业生求职择业，我校定于2025年10月31日举办“金秋筑梦季 职引新篇章”——西安建筑科技大学华清学院2026届毕业生综合类就业双选会。现诚挚邀请各界用人单位积极参与本次招聘会活动，再次感谢用人单位对学校建设发展的关心与支持，我们热切期盼您的到来。本次招聘会对企业及学生均为免费，不收取任何费用。<br/><br/>一、活动时间<br/><br/>2025年10月31日（星期五）9:00-14:00<br/><br/>二、活动地点<br/><br/>西安建筑科技大学华清学院幸福校区—大学生活动中心<br/><br/>三、报名方式及流程<br/><br/>   1、在线报名<br/><br/>   进入学校就业信息网<a href=\"http://job.xauat-hqc.com/\" target=\"_blank\">http://job.xauat-hqc.com/</a>，点击单位注册（登录）→进入就业洽谈会模块申请入驻并进行预约。学校将于活动开展前两日确定参会企业名录；<br/><br/>   2、来电报名<br/><br/>   用人单位也可根据招聘工作实际情况，来电进行参会预约（报名截止时间10月29日18：00）；<br/><br/>   3、资格审核<br/><br/>   因展位数量限制，将根据企业人才需求、专业对口度和报名信息完整度等对报名企业进行筛选审核。<br/><br/>四、用人单位进校管理要求<br/><br/><br/>   1.用人单位参会人员应严格遵守校园管理规定，服从现场工作人员的统一安排，维护招聘会活动正常秩序，杜绝一切存在安全隐患的行为；<br/><br/>   2.未经学校批准，严禁用人单位擅自张贴、悬挂和搭建各种宣传用品；<br/><br/>   3.洽谈会为每个展位提供2张单人椅,并统一张贴招聘信息,各参会单位也可自备海报、易拉宝等相关宣传材料以便招聘需要。<br/><br/>五、其他事项<br/><br/><br/>  1.洽谈会不安排接站，请各参会代表自行乘车前往西安建筑科技大学华清学院大学生活动中心报到；<br/><br/>  2.用人单位如想了解我院毕业生生源信息，可登录西安建筑科技大学华清学院就业信息网<a href=\"http://job.xauat-hqc.com/\" target=\"_blank\">http://job.xauat-hqc.com/</a>，服务指南-毕业生信息栏目查阅。<br/><br/> 乘车路线如下：<br/><br/>  线路一：乘坐飞机到达西安机场的参会代表，请乘坐地铁14号线换成8号线抵达万寿南路站即到。<br/><br/>  线路二：乘坐高铁到达西安北站的参会代表，请乘坐地铁2号线换成8号线抵达万寿南路站即到。<br/><br/>  线路三：乘坐火车到达西安站的参会代表，请乘坐地铁4号线换成8号线抵达万寿南路站即到。<br/><br/>  附近公交：602路、700路、208路<br/><br/>六、联系方式<br/><br/><br/>联系人：李老师<br/><br/>联系电话：02911811118219<br/><br/>地址：西安市新城区幸福南路109号<br/><br/>邮编：710043<br/><br/><br/><br/><br/><br/>西安建筑科技大学华清学院<br/><br/>谨  邀<br/></p>', 'http://127.0.0.1:8080/vs263xianzhaopins/file/28.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=28.jpg', '2026-04-12 01:30:39', 3, '就业资讯');
INSERT INTO `t_gonggao` VALUES (9, '才聚楼观 筑梦华清—西安建筑科技大学华清学院2026届毕业生综合类双选会邀请函', '<p>为进一步加强2026届毕业生就业服务工作，搭建毕业生与用人单位双向选择平台，助力毕业生求职择业，定于2025年11月20日举办“才聚楼观 筑梦华清”——西安建筑科技大学华清学院2026届毕业生综合类双选会。现诚挚邀请各界用人单位积极参与本次招聘会活动。本次招聘会对企业及学生均为免费，不收取任何费用。<br/><br/>一、会议时间<br/><br/>2025年11月20日（星期四）14:00-17:00<br/><br/>二、会议地点<br/><br/>西安建筑科技大学华清学院（楼观校区）<br/><br/>三、报名方式及流程<br/><br/>1.本次招聘会采用在线报名进行，参会企业需注册或登录后方可报名(报名入口<a href=\"https://job.xauat-hqc.com/\" target=\"_blank\">https://job.xauat-hqc.com/</a>，点击单位注册（登录）→进入就业洽谈会模块申请入驻并进行预约。学校将于活动开展前两日确定参会企业名录。<br/><br/>2.来电报名。<br/><br/>3.资格审核：因展位数量限制，将根据企业人才需求、专业对口度和报名信息完整度等对报名企业进行筛选审核。<br/><br/>四、用人单位进校管理要求<br/><br/><br/>1.用人单位进校招聘报到时，请务必提交签字盖章的未收费证明、《承诺书》(详见附件)，参会报到时将纸质版材料交于现场工作人员。<br/><br/>2.用人单位参会人员应严格遵守校园管理规定，服从现场工作人员的统一安排，维护招聘会活动正常秩序，杜绝一切存在安全隐患的行为。<br/><br/>3.未经学校批准，严禁用人单位擅自张贴、悬挂和搭建各种宣传用品。<br/><br/>4.洽谈会为每个展位提供2张单人椅,并统一张贴招聘信息,各参会单位也可自备海报、易拉宝等相关宣传材料以便招聘需要。<br/><br/>五、其他事项<br/><br/><br/>1.招聘会参会企业安排接站，请各参会代表前往地铁2号线省体育场站乘坐校车前往西安建筑科技大学华清学院（楼观校区）报到，校车12：30准时发车，过时不候；<br/><br/>2.用人单位如想了解毕业生生源信息，可登陆西安建筑科技大学华清学院就业信息网<a href=\"http://job.xauat-hqc.com/\" target=\"_blank\">http://job.xauat-hqc.com/</a>，服务指南-毕业生信息栏目查阅。<br/><br/>六、联系方式<br/><br/>联 系 人：李老师<br/><br/>联系电话：029-82628219<br/><br/>地  址：西安楼观生态文化旅游度假区学院路<br/><br/>邮  编：710043<br/></p>', 'http://127.0.0.1:8080/vs263xianzhaopins/file/29.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=29.jpg', '2026-04-12 01:38:07', 3, '就业资讯');

-- ----------------------------
-- Table structure for t_jcbiaoti
-- ----------------------------
DROP TABLE IF EXISTS `t_jcbiaoti`;
CREATE TABLE `t_jcbiaoti`  (
  `jcbiaotiId` int(11) NOT NULL AUTO_INCREMENT,
  `jcbiaotiName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '汉字',
  `jcbiaotiNeirong` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'url',
  `jcbiaotiMark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `jcbiaotiMark1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `jcbiaotiMark2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `jcbiaotiPaixu` int(11) DEFAULT NULL,
  `jcbiaotiType` int(11) DEFAULT NULL COMMENT '0网页导航1admin导航2user导航3yonghu导航4juese导航',
  `jcbiaotiType1` int(11) DEFAULT NULL,
  `jcbiaotiType2` int(11) DEFAULT NULL,
  PRIMARY KEY USING BTREE (`jcbiaotiId`)
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_jcbiaoti
-- ----------------------------
INSERT INTO `t_jcbiaoti` VALUES (1, '网站信息', NULL, NULL, NULL, NULL, 1, 1, 0, NULL);
INSERT INTO `t_jcbiaoti` VALUES (2, '新闻公告', NULL, NULL, NULL, NULL, 2, 1, 0, NULL);
INSERT INTO `t_jcbiaoti` VALUES (3, '企业信息', NULL, NULL, NULL, NULL, 3, 1, 0, NULL);
INSERT INTO `t_jcbiaoti` VALUES (4, '职位信息', NULL, NULL, NULL, NULL, 4, 1, 0, NULL);
INSERT INTO `t_jcbiaoti` VALUES (5, '用户信息', NULL, NULL, NULL, NULL, 5, 1, 0, NULL);
INSERT INTO `t_jcbiaoti` VALUES (6, '求职信息', NULL, NULL, NULL, NULL, 6, 1, 0, NULL);
INSERT INTO `t_jcbiaoti` VALUES (7, '讨论交流', NULL, NULL, NULL, NULL, 7, 1, 0, NULL);
INSERT INTO `t_jcbiaoti` VALUES (8, '数据看板', NULL, NULL, NULL, NULL, 8, 1, 0, NULL);
INSERT INTO `t_jcbiaoti` VALUES (9, '个人消息', NULL, NULL, NULL, NULL, 1, 2, 0, NULL);
INSERT INTO `t_jcbiaoti` VALUES (10, '求职评论', NULL, NULL, NULL, NULL, 2, 2, 0, NULL);
INSERT INTO `t_jcbiaoti` VALUES (11, '讨论交流', NULL, NULL, NULL, NULL, 3, 2, 0, NULL);
INSERT INTO `t_jcbiaoti` VALUES (12, '个人消息', NULL, NULL, NULL, NULL, 1, 3, 0, NULL);
INSERT INTO `t_jcbiaoti` VALUES (13, '职位信息', NULL, NULL, NULL, NULL, 2, 3, 0, NULL);
INSERT INTO `t_jcbiaoti` VALUES (14, '求职评论', NULL, NULL, NULL, NULL, 3, 3, 0, NULL);
INSERT INTO `t_jcbiaoti` VALUES (15, '就业信息', NULL, NULL, NULL, NULL, 5, 1, 0, NULL);
INSERT INTO `t_jcbiaoti` VALUES (16, '就业信息', NULL, NULL, NULL, NULL, 2, 2, 0, NULL);

-- ----------------------------
-- Table structure for t_jcdaohang
-- ----------------------------
DROP TABLE IF EXISTS `t_jcdaohang`;
CREATE TABLE `t_jcdaohang`  (
  `jcdaohangId` int(11) NOT NULL AUTO_INCREMENT,
  `jcdaohangName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '汉字',
  `jcdaohangNeirong` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'url',
  `jcdaohangMark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `jcdaohangMark1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `jcdaohangMark2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `jcdaohangPaixu` int(11) DEFAULT NULL,
  `jcdaohangType` int(11) DEFAULT NULL COMMENT '0网页导航1admin导航2user导航3yonghu导航4juese导航',
  `jcdaohangType1` int(11) DEFAULT NULL,
  `jcdaohangType2` int(11) DEFAULT NULL,
  `jcbiaotiId` int(11) DEFAULT NULL,
  `jcbiaotiName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY USING BTREE (`jcdaohangId`)
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_jcdaohang
-- ----------------------------
INSERT INTO `t_jcdaohang` VALUES (1, '基础信息', 'sjxiaoxi', NULL, NULL, NULL, 1, 1, 0, NULL, 1, NULL);
INSERT INTO `t_jcdaohang` VALUES (2, '外链信息', 'sjlianjie', NULL, NULL, NULL, 2, 1, 0, NULL, 1, NULL);
INSERT INTO `t_jcdaohang` VALUES (3, '公告类型', 'ggtype', NULL, NULL, NULL, 1, 1, 0, NULL, 2, NULL);
INSERT INTO `t_jcdaohang` VALUES (4, '新闻公告', 'gonggao', NULL, NULL, NULL, 2, 1, 0, NULL, 2, NULL);
INSERT INTO `t_jcdaohang` VALUES (5, '企业类型', 'buzhi', NULL, NULL, NULL, 1, 1, 0, NULL, 3, NULL);
INSERT INTO `t_jcdaohang` VALUES (6, '企业信息', 'yonghu', NULL, NULL, NULL, 2, 1, 0, NULL, 3, NULL);
INSERT INTO `t_jcdaohang` VALUES (7, '企业反馈', 'yyijian', NULL, NULL, NULL, 3, 1, 0, NULL, 3, NULL);
INSERT INTO `t_jcdaohang` VALUES (8, '职位类型', 'sjleixing', NULL, NULL, NULL, 1, 1, 0, NULL, 4, NULL);
INSERT INTO `t_jcdaohang` VALUES (9, '职位信息', 'shuju', NULL, NULL, NULL, 2, 1, 0, NULL, 4, NULL);
INSERT INTO `t_jcdaohang` VALUES (10, '用户信息', 'user', NULL, NULL, NULL, 1, 1, 0, NULL, 5, NULL);
INSERT INTO `t_jcdaohang` VALUES (11, '用户反馈', 'uyijian', NULL, NULL, NULL, 2, 1, 0, NULL, 5, NULL);
INSERT INTO `t_jcdaohang` VALUES (12, '求职信息', 'sjshaochu', NULL, NULL, NULL, 1, 1, 0, NULL, 6, NULL);
INSERT INTO `t_jcdaohang` VALUES (13, '评论信息', 'sjpinglun', NULL, NULL, NULL, 2, 1, 0, NULL, 6, NULL);
INSERT INTO `t_jcdaohang` VALUES (14, '讨论类型', 'sjxingtai', NULL, NULL, NULL, 1, 1, 0, NULL, 7, NULL);
INSERT INTO `t_jcdaohang` VALUES (15, '讨论交流', 'sjduochu', NULL, NULL, NULL, 2, 1, 0, NULL, 7, NULL);
INSERT INTO `t_jcdaohang` VALUES (16, '回复信息', 'sjqita', NULL, NULL, NULL, 3, 1, 0, NULL, 7, NULL);
INSERT INTO `t_jcdaohang` VALUES (18, '数据看板', 'tongjitu', NULL, NULL, NULL, 2, 1, 0, NULL, 8, NULL);
INSERT INTO `t_jcdaohang` VALUES (19, '反馈信息', 'uyijian', NULL, NULL, NULL, 1, 2, 0, NULL, 9, NULL);
INSERT INTO `t_jcdaohang` VALUES (20, '职位收藏', 'bumen', NULL, NULL, NULL, 2, 2, 0, NULL, 9, NULL);
INSERT INTO `t_jcdaohang` VALUES (21, '通知信息', 'yxinxi', NULL, NULL, NULL, 3, 2, 0, NULL, 9, NULL);
INSERT INTO `t_jcdaohang` VALUES (22, '求职信息', 'sjshaochu', NULL, NULL, NULL, 1, 2, 0, NULL, 10, NULL);
INSERT INTO `t_jcdaohang` VALUES (23, '咨询信息', 'sjjianchu', NULL, NULL, NULL, 2, 2, 0, NULL, 10, NULL);
INSERT INTO `t_jcdaohang` VALUES (24, '评论信息', 'sjpinglun', NULL, NULL, NULL, 3, 2, 0, NULL, 10, NULL);
INSERT INTO `t_jcdaohang` VALUES (25, '发布交流', 'sjduochu', NULL, NULL, NULL, 1, 2, 0, NULL, 11, NULL);
INSERT INTO `t_jcdaohang` VALUES (26, '回复交流', 'sjqita', NULL, NULL, NULL, 2, 2, 0, NULL, 11, NULL);
INSERT INTO `t_jcdaohang` VALUES (27, '反馈信息', 'yyijian', NULL, NULL, NULL, 1, 3, 0, NULL, 12, NULL);
INSERT INTO `t_jcdaohang` VALUES (28, '职位信息', 'shuju', NULL, NULL, NULL, 1, 3, 0, NULL, 13, NULL);
INSERT INTO `t_jcdaohang` VALUES (29, '咨询信息', 'sjjianchu', NULL, NULL, NULL, 2, 3, 0, NULL, 13, NULL);
INSERT INTO `t_jcdaohang` VALUES (30, '求职信息', 'sjshaochu', NULL, NULL, NULL, 1, 3, 0, NULL, 14, NULL);
INSERT INTO `t_jcdaohang` VALUES (31, '通知信息', 'yxinxi', NULL, NULL, NULL, 2, 3, 0, NULL, 14, NULL);
INSERT INTO `t_jcdaohang` VALUES (32, '评论信息', 'sjpinglun', NULL, NULL, NULL, 3, 3, 0, NULL, 14, NULL);
INSERT INTO `t_jcdaohang` VALUES (33, '就业类型', 'uxtype', NULL, NULL, NULL, 1, 1, 0, NULL, 15, NULL);
INSERT INTO `t_jcdaohang` VALUES (34, '就业信息', 'uxinxi', NULL, NULL, NULL, 2, 1, 0, NULL, 15, NULL);
INSERT INTO `t_jcdaohang` VALUES (35, '就业信息', 'uxinxi', NULL, NULL, NULL, 1, 2, 0, NULL, 16, NULL);

-- ----------------------------
-- Table structure for t_jcpeizhi
-- ----------------------------
DROP TABLE IF EXISTS `t_jcpeizhi`;
CREATE TABLE `t_jcpeizhi`  (
  `jcpeizhiId` int(11) NOT NULL AUTO_INCREMENT,
  `jcpeizhiName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `jcpeizhiNeirong` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `jcpeizhiMark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bumenBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buyuanBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buzhiBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'userBieming',
  `uxtypeBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `uxinxiBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `uyijianBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `roleBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `byumenBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `byuyuanBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `byuzhiBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yonghuBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'yonghuBieming',
  `yxtypeBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yxinxiBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yyijianBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yhroleBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ggtypeBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gonggaoBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ggpinglunBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `shujuBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjduochuBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjjianchuBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjlaiyuanBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjleixingBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjpinglunBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjqitaBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjshaochuBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjxingtaiBieming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `jcpeizhiType` int(11) DEFAULT NULL,
  `jcpeizhiType1` int(11) DEFAULT NULL,
  `jcpeizhiType2` int(11) DEFAULT NULL,
  PRIMARY KEY USING BTREE (`jcpeizhiId`)
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_jcpeizhi
-- ----------------------------
INSERT INTO `t_jcpeizhi` VALUES (1, '高校就业信息管理系统', '', NULL, '收藏', NULL, '类型', '用户', '类型', '就业', '反馈', NULL, NULL, NULL, NULL, '企业', NULL, '通知', '反馈', NULL, '类型', '公告', NULL, '职位', '讨论', '咨询', NULL, '类型', '评论', '回复', '求职', '类型', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_rizhi
-- ----------------------------
DROP TABLE IF EXISTS `t_rizhi`;
CREATE TABLE `t_rizhi`  (
  `rizhiId` int(11) NOT NULL AUTO_INCREMENT,
  `rizhiName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dengluIp` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `date` datetime NOT NULL,
  `rizhiType` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `rizhiResult` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `rizhiRemark` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `userType` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY USING BTREE (`rizhiId`)
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_rizhi
-- ----------------------------
INSERT INTO `t_rizhi` VALUES (4, '系统管理员', '127.0.0.1', '2026-05-04 22:41:14', '删除', '成功', '删除了日志ID: 1,3', NULL);
INSERT INTO `t_rizhi` VALUES (5, '关玉华', '127.0.0.1', '2026-05-04 22:41:36', '修改', '成功', '修改了就业信息: 华为', NULL);
INSERT INTO `t_rizhi` VALUES (6, '张经理', '127.0.0.1', '2026-05-04 22:42:33', '发布', '成功', '发布了职位: 测试', NULL);
INSERT INTO `t_rizhi` VALUES (7, '李桂花', '127.0.0.1', '2026-05-04 22:43:54', '提交', '成功', '提交了就业信息: 大米', NULL);
INSERT INTO `t_rizhi` VALUES (8, '王强', '127.0.0.1', '2026-05-04 22:55:14', '修改', '成功', '修改了就业信息: 小米', NULL);
INSERT INTO `t_rizhi` VALUES (9, '王显坤', '127.0.0.1', '2026-05-04 22:55:47', '修改', '成功', '修改了就业信息: 6666', NULL);
INSERT INTO `t_rizhi` VALUES (10, '张三', '127.0.0.1', '2026-05-04 22:56:44', '提交', '成功', '提交了就业信息: eng', NULL);
INSERT INTO `t_rizhi` VALUES (11, 'HR Sarah', '127.0.0.1', '2026-05-05 03:00:38', '发布', '成功', '发布了职位: 英文客服', NULL);
INSERT INTO `t_rizhi` VALUES (12, 'HR Sarah', '127.0.0.1', '2026-05-05 03:02:14', '发布', '成功', '发布了职位: 会计助理', NULL);
INSERT INTO `t_rizhi` VALUES (13, '张经理', '127.0.0.1', '2026-05-05 03:06:20', '修改', '成功', '修改了职位信息: 英文导游', NULL);
INSERT INTO `t_rizhi` VALUES (14, '张经理', '127.0.0.1', '2026-05-05 03:07:44', '发布', '成功', '发布了职位: 财务助理', NULL);
INSERT INTO `t_rizhi` VALUES (15, '张经理', '127.0.0.1', '2026-05-05 03:09:01', '发布', '成功', '发布了职位: 跨境销售专员', NULL);
INSERT INTO `t_rizhi` VALUES (16, '张经理', '127.0.0.1', '2026-05-05 03:10:16', '发布', '成功', '发布了职位: 内容运营专员', NULL);

-- ----------------------------
-- Table structure for t_shuju
-- ----------------------------
DROP TABLE IF EXISTS `t_shuju`;
CREATE TABLE `t_shuju`  (
  `shujuId` int(11) NOT NULL AUTO_INCREMENT,
  `shujuName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `shujuMark` longtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `shujuMark1` longtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `shujuMark2` longtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `shujuMark3` longtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `shujuDate` datetime DEFAULT NULL,
  `shujuDate1` datetime DEFAULT NULL,
  `shujuZong` int(11) DEFAULT NULL,
  `shujuZong1` int(11) DEFAULT NULL,
  `shujuZong2` int(11) DEFAULT NULL,
  `shujuDouble` double DEFAULT NULL,
  `shujuDouble1` double DEFAULT NULL,
  `shujuDouble2` double DEFAULT NULL,
  `shujuDouble3` double DEFAULT NULL,
  `shujuDouble4` double DEFAULT NULL,
  `shujuType` int(11) DEFAULT NULL,
  `shujuType1` int(11) DEFAULT NULL,
  `shujuType2` int(11) DEFAULT NULL,
  `shujuImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `shujuImgName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjleixingId` int(11) DEFAULT NULL,
  `sjleixingName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjxingtaiId` int(11) DEFAULT NULL,
  `sjxingtaiName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bumenId` int(11) DEFAULT NULL,
  `bumenName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buyuanId` int(11) DEFAULT NULL,
  `buyuanName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yonghuId` int(11) DEFAULT NULL,
  `yonghuName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buzhiId` int(11) DEFAULT NULL,
  `buzhiName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `roleName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY USING BTREE (`shujuId`)
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_shuju
-- ----------------------------
INSERT INTO `t_shuju` VALUES (17, 'Java 后端开发工程师 (应届)', '<p>熟悉 Java 基础（集合、IO、多线程）；掌握 Spring Boot 3.x 框架；熟悉 MySQL 索引优化及 Redis 常用数据结构；了解 Linux 常用命令及 OceanBase 或其他分布式数据库者优先。</p>', '本科及以上', '西安', '熟悉 Java 基础（集合、IO、多线程）；掌握 Spring Boot 3.x 框架；熟悉 MySQL 索引优化及 Redis 常用数据结构；了解 Linux 常用命令及 OceanBase 或其他分布式数据库者优先。', '2026-04-12 02:46:37', NULL, 9, NULL, 1, 12000, NULL, NULL, NULL, NULL, 0, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260412144834_506.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260412144834_506.jpg', 5, '技术研发类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5, '张经理', 4, '股份公司', NULL, NULL);
INSERT INTO `t_shuju` VALUES (18, '视觉传达与交互设计师', '<p>熟练使用 Figma 或 Adobe XD 绘图工具；具备良好的审美能力与色彩感；能独立完成移动端/PC端界面设计；了解基本的用户体验（UX）设计原则；需附带个人作品集。</p>', '本科及以上（设计类相关专业）', '上海/成都', '熟练使用 Figma 或 Adobe XD 绘图工具；具备良好的审美能力与色彩感；能独立完成移动端/PC端界面设计；了解基本的用户体验（UX）设计原则；需附带个人作品集。', '2026-04-12 02:48:08', NULL, 10, NULL, 0, 8000, NULL, NULL, NULL, NULL, 0, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260412024806_309.png', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260412024806_309.png', 4, '产品/设计类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, '李部长', 1, '国企信息', NULL, NULL);
INSERT INTO `t_shuju` VALUES (19, '全媒体平台运营专员', '<p>负责抖音、小红书等平台的内容策划与文案撰写；具备基础的视频剪辑能力（剪映/PR）；能根据热点快速产出内容；具备较强的数据敏感度，能分析后台转化数据。</p>', '本科及以上（传媒/文学/市场营销优先）', '广州/西安', '负责抖音、小红书等平台的内容策划与文案撰写；具备基础的视频剪辑能力（剪映/PR）；能根据热点快速产出内容；具备较强的数据敏感度，能分析后台转化数据。', '2026-04-12 02:49:19', NULL, 6, NULL, 0, 6500, NULL, NULL, NULL, NULL, 0, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260412024918_414.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260412024918_414.jpg', 3, '运营/市场类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 7, 'HR Sarah', 2, '外企信息', NULL, NULL);
INSERT INTO `t_shuju` VALUES (20, '售前方案经理 (IT方向)', '<p>配合销售团队为客户讲解技术方案；编写项目投标文件与技术建议书；具备优秀的沟通能力与逻辑表达能力；能够适应短途出差。</p>', '本科及以上（计算机相关背景优先）', '深圳/西安', '配合销售团队为客户讲解技术方案；编写项目投标文件与技术建议书；具备优秀的沟通能力与逻辑表达能力；能够适应短途出差。', '2026-04-12 02:50:47', NULL, 2, NULL, 0, 15000, NULL, NULL, NULL, NULL, 0, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260412025044_3.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260412025044_3.jpg', 2, '销售/商务类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 8, 'HR 招聘组（校招办）', 3, '私营企业', NULL, NULL);
INSERT INTO `t_shuju` VALUES (21, '招聘与企业文化专员', '<p>负责岗位发布、简历筛选及初试邀约；维护企业招聘渠道；组织策划员工入职培训及团建活动；CET4 成绩 425 分以上；沟通亲和力强。</p>', '本科及以上（人力资源管理/心理学优先）', '西安', '负责岗位发布、简历筛选及初试邀约；维护企业招聘渠道；组织策划员工入职培训及团建活动；CET4 成绩 425 分以上；沟通亲和力强。', '2026-04-12 02:55:35', NULL, 3, NULL, 0, 5000, NULL, NULL, NULL, NULL, 0, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260412025533_949.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260412025533_949.jpg', 1, '行政/职能类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 8, 'HR 招聘组（校招办）', 3, '私营企业', NULL, NULL);
INSERT INTO `t_shuju` VALUES (22, '数字化转型助理顾问', '<p>参与企业数字化流程调研；协助项目经理输出行业调研报告；具备极强的 PPT 制作能力与逻辑思考能力；CET6 成绩 500 分以上</p>', '硕士及以上（应届优先）', '北京/上海', '参与企业数字化流程调研；协助项目经理输出行业调研报告；具备极强的 PPT 制作能力与逻辑思考能力；CET6 成绩 500 分以上', '2026-04-12 02:56:41', NULL, 3, NULL, 0, 20000, NULL, NULL, NULL, NULL, 0, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260412025639_60.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260412025639_60.jpg', 2, '销售/商务类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 8, 'HR 招聘组（校招办）', 3, '私营企业', NULL, NULL);
INSERT INTO `t_shuju` VALUES (23, '高中信息技术教师 (校招)', '<p>熟悉 Python/C++ 编程基础；具备良好的语言表达与沟通能力；CET4 &gt; 425分；有支教经历或校内社团讲座经验者优先。</p>', '本科及以上（需持有或承诺入职后取得教师资格证）', '西安/咸阳', '熟悉 Python/C++ 编程基础；具备良好的语言表达与沟通能力；CET4 > 425分；有支教经历或校内社团讲座经验者优先。', '2026-04-12 03:03:20', NULL, 3, NULL, 0, 6000, NULL, NULL, NULL, NULL, 0, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260412030312_739.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260412030312_739.jpg', 6, '教育/咨询类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5, '张经理', 4, '股份公司', NULL, NULL);
INSERT INTO `t_shuju` VALUES (24, '课程顾问/学术规划师', '<p>负责为学生及家长提供升学规划与课程建议；具备较强的逻辑思维和亲和力；能承受一定的工作压力；性格开朗，目标感强。</p>', '本科及以上', '西安', '负责为学生及家长提供升学规划与课程建议；具备较强的逻辑思维和亲和力；能承受一定的工作压力；性格开朗，目标感强。', '2026-04-12 03:04:08', NULL, 1, NULL, 0, 6000, NULL, NULL, NULL, NULL, 0, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260412030403_231.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260412030403_231.jpg', 6, '教育/咨询类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5, '张经理', 4, '股份公司', NULL, NULL);
INSERT INTO `t_shuju` VALUES (25, '222222', '<p>111</p>', '111', '111', '111', '2026-04-25 01:16:39', NULL, 111, NULL, 0, 111, NULL, NULL, NULL, NULL, 0, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260429201138_981.png', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260429201138_981.png', 4, '产品/设计类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5, '张经理', 4, '股份公司', NULL, NULL);
INSERT INTO `t_shuju` VALUES (27, '英文导游', '<p>国际导游</p>', '本科', '北京', 'CET6 > 550', '2026-05-04 22:42:33', NULL, 1, NULL, 0, 10000, NULL, NULL, NULL, NULL, 0, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260504224231_236.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260504224231_236.jpg', 6, '教育/咨询类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5, '张经理', 4, '股份公司', NULL, NULL);
INSERT INTO `t_shuju` VALUES (28, '英文客服', NULL, '专科', '西安', '英文CET6 500分以上', '2026-05-05 03:00:38', NULL, 5, NULL, 0, 8000, NULL, NULL, NULL, NULL, 0, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260505030035_65.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260505030035_65.jpg', 2, '销售/商务类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 7, 'HR Sarah', 2, '外企信息', NULL, NULL);
INSERT INTO `t_shuju` VALUES (29, '会计助理', NULL, '本科', '北京', '初级会计资格证', '2026-05-05 03:02:14', NULL, 3, NULL, 1, 9000, NULL, NULL, NULL, NULL, 0, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260505030212_415.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260505030212_415.jpg', 1, '行政/职能类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 7, 'HR Sarah', 2, '外企信息', NULL, NULL);
INSERT INTO `t_shuju` VALUES (30, '财务助理', '<p>财务助理</p>', '本科', '深证', '中级会计', '2026-05-05 03:07:44', NULL, 2, NULL, 0, 13000, NULL, NULL, NULL, NULL, 0, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260505030734_209.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260505030734_209.jpg', 1, '行政/职能类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5, '张经理', 4, '股份公司', NULL, NULL);
INSERT INTO `t_shuju` VALUES (31, '跨境销售专员', '<p>跨境销售</p>', '本科', '广州', 'CET６　＞　５００', '2026-05-05 03:09:01', NULL, 5, NULL, 0, 8000, NULL, NULL, NULL, NULL, 0, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260505030847_916.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260505030847_916.jpg', 2, '销售/商务类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5, '张经理', 4, '股份公司', NULL, NULL);
INSERT INTO `t_shuju` VALUES (32, '内容运营专员', '<p>内容运营专员，要求英语专业，媒体专业</p>', '本科', '长沙', '英语专业，媒体专业', '2026-05-05 03:10:16', NULL, 3, NULL, 0, 7500, NULL, NULL, NULL, NULL, 0, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260505031003_634.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260505031003_634.jpg', 3, '运营/市场类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5, '张经理', 4, '股份公司', NULL, NULL);

-- ----------------------------
-- Table structure for t_sjduochu
-- ----------------------------
DROP TABLE IF EXISTS `t_sjduochu`;
CREATE TABLE `t_sjduochu`  (
  `sjduochuId` int(11) NOT NULL AUTO_INCREMENT,
  `sjduochuName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjduochuMark` longtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `sjduochuMark1` longtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `sjduochuMark2` longtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `sjduochuMark3` longtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `sjduochuDate` datetime DEFAULT NULL,
  `sjduochuDate1` datetime DEFAULT NULL,
  `sjduochuZong` int(11) DEFAULT NULL,
  `sjduochuZong1` int(11) DEFAULT NULL,
  `sjduochuZong2` int(11) DEFAULT NULL,
  `sjduochuDouble` double DEFAULT NULL,
  `sjduochuDouble1` double DEFAULT NULL,
  `sjduochuDouble2` double DEFAULT NULL,
  `sjduochuType` int(11) DEFAULT NULL,
  `sjduochuType1` int(11) DEFAULT NULL,
  `sjduochuType2` int(11) DEFAULT NULL,
  `sjqitaId` int(11) DEFAULT NULL,
  `sjqitaName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjlaiyuanId` int(11) DEFAULT NULL,
  `sjlaiyuanName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjduochuImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjduochuImgName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `shujuId` int(11) DEFAULT NULL,
  `shujuName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjleixingId` int(11) DEFAULT NULL,
  `sjleixingName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjxingtaiId` int(11) DEFAULT NULL,
  `sjxingtaiName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bumenId` int(11) DEFAULT NULL,
  `bumenName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buyuanId` int(11) DEFAULT NULL,
  `buyuanName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yonghuId` int(11) DEFAULT NULL,
  `yonghuName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buzhiId` int(11) DEFAULT NULL,
  `buzhiName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `roleName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY USING BTREE (`sjduochuId`)
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_sjduochu
-- ----------------------------
INSERT INTO `t_sjduochu` VALUES (1, '其他交流1', '<p>&nbsp; &nbsp; 其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流<br/>&nbsp;&nbsp;&nbsp;&nbsp;其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流<br/>&nbsp;&nbsp;&nbsp;&nbsp;其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流其他交流</p><p><img src=\"http://127.0.0.1:8080/vs263xianzhaopins/file/16.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', NULL, NULL, NULL, '2026-04-19 15:43:45', NULL, 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/4.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=4.jpg', NULL, NULL, NULL, NULL, 1, '其他交流', 1, 'yonghu1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sjduochu` VALUES (2, '其他交流2', '<p>2026年就业形势严峻，计算机行业迎来了寒冬，那我们这些毕业生该何去何从呢？</p>', NULL, NULL, NULL, '2026-04-19 15:51:54', NULL, 1, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/5.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=5.jpg', NULL, NULL, NULL, NULL, 5, '测试', 2, '王显坤', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sjduochu` VALUES (3, '用户测试发布交流', '<p>用户测试发布交流</p>', NULL, NULL, NULL, '2026-04-24 00:32:00', NULL, 1, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260424003239_30.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260424003239_30.jpg', NULL, NULL, NULL, NULL, 4, '职场经验', 2, '王显坤', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sjduochu` VALUES (4, '2026年系统论坛交流测试', '<p>2026年系统论坛交流测试</p>', NULL, NULL, NULL, '2026-05-05 03:12:54', NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260505031246_669.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260505031246_669.jpg', NULL, NULL, NULL, NULL, 1, '其他交流', 3, '张三', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_sjjianchu
-- ----------------------------
DROP TABLE IF EXISTS `t_sjjianchu`;
CREATE TABLE `t_sjjianchu`  (
  `sjjianchuId` int(11) NOT NULL AUTO_INCREMENT,
  `sjjianchuName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjjianchuMark` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjjianchuMark1` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjjianchuMark2` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjjianchuMark3` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjjianchuDate` datetime DEFAULT NULL,
  `sjjianchuDate1` datetime DEFAULT NULL,
  `sjjianchuZong` int(11) DEFAULT NULL,
  `sjjianchuZong1` int(11) DEFAULT NULL,
  `sjjianchuZong2` int(11) DEFAULT NULL,
  `sjjianchuDouble` double DEFAULT NULL,
  `sjjianchuDouble1` double DEFAULT NULL,
  `sjjianchuDouble2` double DEFAULT NULL,
  `sjjianchuType` int(11) DEFAULT NULL,
  `sjjianchuType1` int(11) DEFAULT NULL,
  `sjjianchuType2` int(11) DEFAULT NULL,
  `sjqitaId` int(11) DEFAULT NULL,
  `sjqitaName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjlaiyuanId` int(11) DEFAULT NULL,
  `sjlaiyuanName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjjianchuImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjjianchuImgName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `shujuId` int(11) DEFAULT NULL,
  `shujuName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjleixingId` int(11) DEFAULT NULL,
  `sjleixingName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjxingtaiId` int(11) DEFAULT NULL,
  `sjxingtaiName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bumenId` int(11) DEFAULT NULL,
  `bumenName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buyuanId` int(11) DEFAULT NULL,
  `buyuanName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yonghuId` int(11) DEFAULT NULL,
  `yonghuName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buzhiId` int(11) DEFAULT NULL,
  `buzhiName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `roleName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY USING BTREE (`sjjianchuId`)
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_sjjianchu
-- ----------------------------
INSERT INTO `t_sjjianchu` VALUES (1, '阿道夫水水水水水水水水水水水水水水水水水水水水水水水水水水水水水水水水水水水水', NULL, NULL, NULL, NULL, '2026-04-02 11:39:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5, '职位信息5', 5, '其他工作', NULL, NULL, 6, 'yonghu6', NULL, NULL, NULL, NULL, 1, '企业1', NULL, NULL, NULL, NULL);
INSERT INTO `t_sjjianchu` VALUES (2, '1', NULL, NULL, NULL, NULL, '2026-04-15 20:10:44', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 24, '课程顾问/学术规划师', 6, '教育/咨询类', NULL, NULL, 2, '王显坤', NULL, NULL, NULL, NULL, 5, '张经理', NULL, NULL, NULL, NULL);
INSERT INTO `t_sjjianchu` VALUES (3, '1', NULL, NULL, NULL, NULL, '2026-04-24 00:44:20', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 24, '课程顾问/学术规划师', 6, '教育/咨询类', NULL, NULL, 2, '王显坤', NULL, NULL, NULL, NULL, 5, '张经理', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_sjleixing
-- ----------------------------
DROP TABLE IF EXISTS `t_sjleixing`;
CREATE TABLE `t_sjleixing`  (
  `sjleixingId` int(11) NOT NULL AUTO_INCREMENT,
  `sjleixingName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjleixingMark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjleixingMark1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjleixingMark2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjleixingPhone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjleixingDizhi` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjleixingDate` datetime DEFAULT NULL,
  `sjleixingDate1` datetime DEFAULT NULL,
  `sjleixingType` int(11) DEFAULT NULL,
  `sjleixingType1` int(11) DEFAULT NULL,
  `sjleixingDouble` double DEFAULT NULL,
  `sjleixingDouble1` double DEFAULT NULL,
  PRIMARY KEY USING BTREE (`sjleixingId`)
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_sjleixing
-- ----------------------------
INSERT INTO `t_sjleixing` VALUES (1, '行政/职能类', '行政/职能类', NULL, NULL, NULL, NULL, '2026-04-12 02:39:09', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sjleixing` VALUES (2, '销售/商务类', '销售/商务类', NULL, NULL, NULL, NULL, '2026-04-12 02:38:58', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sjleixing` VALUES (3, '运营/市场类', '运营/市场类', NULL, NULL, NULL, NULL, '2026-04-12 02:38:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sjleixing` VALUES (4, '产品/设计类', '产品/设计类', NULL, NULL, NULL, NULL, '2026-04-12 02:38:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sjleixing` VALUES (5, '技术研发类', '技术研发类', NULL, NULL, NULL, NULL, '2026-04-12 02:38:28', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sjleixing` VALUES (6, '教育/咨询类', '教育/咨询类', NULL, NULL, NULL, NULL, '2026-04-12 02:39:18', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_sjlianjie
-- ----------------------------
DROP TABLE IF EXISTS `t_sjlianjie`;
CREATE TABLE `t_sjlianjie`  (
  `sjlianjieId` int(11) NOT NULL AUTO_INCREMENT,
  `sjlianjieName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjlianjieMark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjlianjieMark1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjlianjieMark2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjlianjiePhone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjlianjieDizhi` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjlianjieDate` datetime DEFAULT NULL,
  `sjlianjieDate1` datetime DEFAULT NULL,
  `sjlianjieType` int(11) DEFAULT NULL,
  `sjlianjieType1` int(11) DEFAULT NULL,
  `sjlianjieDouble` double DEFAULT NULL,
  `sjlianjieDouble1` double DEFAULT NULL,
  PRIMARY KEY USING BTREE (`sjlianjieId`)
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_sjlianjie
-- ----------------------------
INSERT INTO `t_sjlianjie` VALUES (1, '国家大学生就业服务平台', 'https://job.ncss.cn/', NULL, NULL, NULL, NULL, '2026-04-12 01:58:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sjlianjie` VALUES (2, '中国就业网', 'https://chinajob.mohrss.gov.cn/', NULL, NULL, NULL, NULL, '2026-04-12 01:52:12', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sjlianjie` VALUES (3, '智联招聘', 'https://www.zhaopin.com/', NULL, NULL, NULL, NULL, '2026-04-12 01:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sjlianjie` VALUES (4, '中国人才热线', 'https://www.cjol.com/aboutus/index.html', NULL, NULL, NULL, NULL, '2026-04-12 01:50:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sjlianjie` VALUES (5, '陕西省高校毕业生就业网', 'http://jyweb.sneducloud.com/website/index.aspx', NULL, NULL, NULL, NULL, '2026-04-12 02:09:30', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sjlianjie` VALUES (6, 'boss 直聘', 'https://www.zhipin.com/xian/?seoRefer=index', NULL, NULL, NULL, NULL, '2026-04-12 02:10:21', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sjlianjie` VALUES (7, '猎聘', 'https://m.liepin.com/', NULL, NULL, NULL, NULL, '2026-04-12 02:11:54', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_sjpinglun
-- ----------------------------
DROP TABLE IF EXISTS `t_sjpinglun`;
CREATE TABLE `t_sjpinglun`  (
  `sjpinglunId` int(11) NOT NULL AUTO_INCREMENT,
  `sjpinglunName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjpinglunMark` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjpinglunMark1` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjpinglunMark2` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjpinglunMark3` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjpinglunDate` datetime DEFAULT NULL,
  `sjpinglunDate1` datetime DEFAULT NULL,
  `sjpinglunZong` int(11) DEFAULT NULL,
  `sjpinglunZong1` int(11) DEFAULT NULL,
  `sjpinglunZong2` int(11) DEFAULT NULL,
  `sjpinglunDouble` double DEFAULT NULL,
  `sjpinglunDouble1` double DEFAULT NULL,
  `sjpinglunDouble2` double DEFAULT NULL,
  `sjpinglunType` int(11) DEFAULT NULL,
  `sjpinglunType1` int(11) DEFAULT NULL,
  `sjpinglunType2` int(11) DEFAULT NULL,
  `sjqitaId` int(11) DEFAULT NULL,
  `sjqitaName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjlaiyuanId` int(11) DEFAULT NULL,
  `sjlaiyuanName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjpinglunImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjpinglunImgName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `shujuId` int(11) DEFAULT NULL,
  `shujuName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjleixingId` int(11) DEFAULT NULL,
  `sjleixingName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjxingtaiId` int(11) DEFAULT NULL,
  `sjxingtaiName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bumenId` int(11) DEFAULT NULL,
  `bumenName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buyuanId` int(11) DEFAULT NULL,
  `buyuanName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yonghuId` int(11) DEFAULT NULL,
  `yonghuName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buzhiId` int(11) DEFAULT NULL,
  `buzhiName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `roleName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY USING BTREE (`sjpinglunId`)
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_sjpinglun
-- ----------------------------
INSERT INTO `t_sjpinglun` VALUES (1, '哇。好牛啊', '你也挺牛的！', NULL, NULL, NULL, '2026-04-12 03:25:19', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 17, 'Java 后端开发工程师 (应届)', 5, '技术研发类', NULL, NULL, 2, '王显坤', NULL, NULL, NULL, NULL, 5, '张经理', NULL, NULL, NULL, NULL);
INSERT INTO `t_sjpinglun` VALUES (2, '11111', NULL, NULL, NULL, NULL, '2026-04-15 20:12:35', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 24, '课程顾问/学术规划师', 6, '教育/咨询类', NULL, NULL, 2, '王显坤', NULL, NULL, NULL, NULL, 5, '张经理', NULL, NULL, NULL, NULL);
INSERT INTO `t_sjpinglun` VALUES (3, '22226666', NULL, NULL, NULL, NULL, '2026-04-15 20:12:56', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 24, '课程顾问/学术规划师', 6, '教育/咨询类', NULL, NULL, 2, '王显坤', NULL, NULL, NULL, NULL, 5, '张经理', NULL, NULL, NULL, NULL);
INSERT INTO `t_sjpinglun` VALUES (4, '这个有其他要求吗', NULL, NULL, NULL, NULL, '2026-05-05 03:02:59', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 29, '会计助理', 1, '行政/职能类', NULL, NULL, 3, '张三', NULL, NULL, NULL, NULL, 7, 'HR Sarah', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_sjqita
-- ----------------------------
DROP TABLE IF EXISTS `t_sjqita`;
CREATE TABLE `t_sjqita`  (
  `sjqitaId` int(11) NOT NULL AUTO_INCREMENT,
  `sjqitaName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjqitaMark` longtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `sjqitaMark1` longtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `sjqitaMark2` longtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `sjqitaPhone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjqitaDizhi` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjqitaDate` datetime DEFAULT NULL,
  `sjqitaDate1` datetime DEFAULT NULL,
  `sjqitaType` int(11) DEFAULT NULL,
  `sjqitaType1` int(11) DEFAULT NULL,
  `sjqitaDouble` double DEFAULT NULL,
  `sjqitaDouble1` double DEFAULT NULL,
  PRIMARY KEY USING BTREE (`sjqitaId`)
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_sjqita
-- ----------------------------
INSERT INTO `t_sjqita` VALUES (1, '官方发达的发生发的发的撒啊发的啥地方地方地方的发生', NULL, NULL, NULL, 'yonghu1', '职场经验1', '2026-04-02 12:00:17', NULL, 1, 7, NULL, NULL);
INSERT INTO `t_sjqita` VALUES (2, '111111111111111111111111111111111111', NULL, NULL, NULL, '王显坤', '职场经验1', '2026-04-12 15:30:41', NULL, 2, 7, NULL, NULL);
INSERT INTO `t_sjqita` VALUES (3, '222222222222222222222222222222222222222', NULL, NULL, NULL, '王显坤', '职场经验1', '2026-04-12 15:30:45', NULL, 2, 7, NULL, NULL);
INSERT INTO `t_sjqita` VALUES (4, '33333333333333333333333333333333333333333333333333333333', NULL, NULL, NULL, '王显坤', '职场经验1', '2026-04-12 15:30:48', NULL, 2, 7, NULL, NULL);
INSERT INTO `t_sjqita` VALUES (5, '测试评论功能', NULL, NULL, NULL, '王显坤', '用户测试发布交流', '2026-04-24 00:32:59', NULL, 2, 3, NULL, NULL);
INSERT INTO `t_sjqita` VALUES (6, 'hi hi hi i hi hi hi', NULL, NULL, NULL, '王显坤', '用户测试发布交流', '2026-04-25 00:31:15', NULL, 2, 3, NULL, NULL);
INSERT INTO `t_sjqita` VALUES (7, '咦哟咦哟咦哟咦哟', NULL, NULL, NULL, '张三', '其他交流2', '2026-04-29 20:27:10', NULL, 3, 2, NULL, NULL);

-- ----------------------------
-- Table structure for t_sjshaochu
-- ----------------------------
DROP TABLE IF EXISTS `t_sjshaochu`;
CREATE TABLE `t_sjshaochu`  (
  `sjshaochuId` int(11) NOT NULL AUTO_INCREMENT,
  `sjshaochuName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjshaochuMark` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjshaochuMark1` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjshaochuMark2` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjshaochuMark3` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjshaochuDate` datetime DEFAULT NULL,
  `sjshaochuDate1` datetime DEFAULT NULL,
  `sjshaochuZong` int(11) DEFAULT NULL,
  `sjshaochuZong1` int(11) DEFAULT NULL,
  `sjshaochuZong2` int(11) DEFAULT NULL,
  `sjshaochuDouble` double DEFAULT NULL,
  `sjshaochuDouble1` double DEFAULT NULL,
  `sjshaochuDouble2` double DEFAULT NULL,
  `sjshaochuType` int(11) DEFAULT NULL,
  `sjshaochuType1` int(11) DEFAULT NULL,
  `sjshaochuType2` int(11) DEFAULT NULL,
  `sjqitaId` int(11) DEFAULT NULL,
  `sjqitaName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjlaiyuanId` int(11) DEFAULT NULL,
  `sjlaiyuanName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjshaochuImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjshaochuImgName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `shujuId` int(11) DEFAULT NULL,
  `shujuName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjleixingId` int(11) DEFAULT NULL,
  `sjleixingName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjxingtaiId` int(11) DEFAULT NULL,
  `sjxingtaiName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bumenId` int(11) DEFAULT NULL,
  `bumenName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buyuanId` int(11) DEFAULT NULL,
  `buyuanName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yonghuId` int(11) DEFAULT NULL,
  `yonghuName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buzhiId` int(11) DEFAULT NULL,
  `buzhiName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `roleName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY USING BTREE (`sjshaochuId`)
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_sjshaochu
-- ----------------------------
INSERT INTO `t_sjshaochu` VALUES (14, '18888888888', '西安建筑科技大学华清学院', '本科', '计算机科学与技术', NULL, '2026-04-12 03:27:50', NULL, NULL, NULL, NULL, 12000, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260412012040_247.pdf', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260412012040_247.pdf', 17, 'Java 后端开发工程师 (应届)', 5, '技术研发类', NULL, NULL, 2, '王显坤', NULL, NULL, NULL, NULL, 5, '张经理', NULL, NULL, NULL, NULL);
INSERT INTO `t_sjshaochu` VALUES (15, '18888888888', '西安建筑科技大学华清学院', '本科', '计算机科学与技术', NULL, '2026-04-15 20:10:14', NULL, NULL, NULL, NULL, 6000, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260412012040_247.pdf', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260412012040_247.pdf', 24, '课程顾问/学术规划师', 6, '教育/咨询类', NULL, NULL, 2, '王显坤', NULL, NULL, NULL, NULL, 5, '张经理', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_sjxiaoxi
-- ----------------------------
DROP TABLE IF EXISTS `t_sjxiaoxi`;
CREATE TABLE `t_sjxiaoxi`  (
  `sjxiaoxiId` int(11) NOT NULL AUTO_INCREMENT,
  `sjxiaoxiName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjxiaoxiMark` longtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `sjxiaoxiMark1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjxiaoxiMark2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjxiaoxiPhone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjxiaoxiDizhi` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjxiaoxiDate` datetime DEFAULT NULL,
  `sjxiaoxiDate1` datetime DEFAULT NULL,
  `sjxiaoxiType` int(11) DEFAULT NULL,
  `sjxiaoxiType1` int(11) DEFAULT NULL,
  `sjxiaoxiDouble` double DEFAULT NULL,
  `sjxiaoxiDouble1` double DEFAULT NULL,
  PRIMARY KEY USING BTREE (`sjxiaoxiId`)
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_sjxiaoxi
-- ----------------------------
INSERT INTO `t_sjxiaoxi` VALUES (1, '关于我们', '<p>我们是最好的高校就业信息管理网站</p>', NULL, NULL, NULL, NULL, '2026-04-12 02:12:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sjxiaoxi` VALUES (2, '联系我们', '<p>拨打188-8888-8888</p>', NULL, NULL, NULL, NULL, '2026-04-12 01:54:35', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_sjxingtai
-- ----------------------------
DROP TABLE IF EXISTS `t_sjxingtai`;
CREATE TABLE `t_sjxingtai`  (
  `sjxingtaiId` int(11) NOT NULL AUTO_INCREMENT,
  `sjxingtaiName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjxingtaiMark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjxingtaiMark1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjxingtaiMark2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjxingtaiPhone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjxingtaiDizhi` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sjxingtaiDate` datetime DEFAULT NULL,
  `sjxingtaiDate1` datetime DEFAULT NULL,
  `sjxingtaiType` int(11) DEFAULT NULL,
  `sjxingtaiType1` int(11) DEFAULT NULL,
  `sjxingtaiDouble` double DEFAULT NULL,
  `sjxingtaiDouble1` double DEFAULT NULL,
  PRIMARY KEY USING BTREE (`sjxingtaiId`)
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_sjxingtai
-- ----------------------------
INSERT INTO `t_sjxingtai` VALUES (1, '其他交流', '其他交流', NULL, NULL, NULL, NULL, '2026-04-19 15:40:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sjxingtai` VALUES (2, '技巧分享', '技巧分享', NULL, NULL, NULL, NULL, '2026-04-19 15:40:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sjxingtai` VALUES (3, '面试经验', '面试经验', NULL, NULL, NULL, NULL, '2026-04-19 15:40:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sjxingtai` VALUES (4, '职场经验', '职场经验', NULL, NULL, NULL, NULL, '2026-04-19 15:40:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sjxingtai` VALUES (5, '测试类型', '测试类型', NULL, NULL, NULL, NULL, '2026-04-24 00:27:36', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userPassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userXingming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userSex` int(11) DEFAULT NULL,
  `userAge` int(11) DEFAULT NULL,
  `userMinzu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userPhone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userMark` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userMark1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userMark2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userMark3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userMark4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userDate` datetime DEFAULT NULL,
  `userDate1` datetime DEFAULT NULL,
  `userDate2` datetime DEFAULT NULL,
  `userType` int(11) DEFAULT NULL,
  `userType1` int(11) DEFAULT NULL,
  `userType2` int(11) DEFAULT NULL,
  `userDouble` double DEFAULT NULL,
  `userDouble1` double DEFAULT NULL,
  `userDouble2` double DEFAULT NULL,
  `userZong` int(11) DEFAULT NULL,
  `userZong1` int(11) DEFAULT NULL,
  `userZong2` int(11) DEFAULT NULL,
  `userImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userImgName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `roleName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bumenId` int(11) DEFAULT NULL,
  `bumenName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buyuanId` int(11) DEFAULT NULL,
  `buyuanName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buzhiId` int(11) DEFAULT NULL,
  `buzhiName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY USING BTREE (`userId`)
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '202404010101', 'e10adc3949ba59abbe56e057f20f883e', '王强', 0, 21, NULL, '18888888888', '西安建筑科技大学华清学院', '本科', '电气工程及其自动化', NULL, NULL, '2026-04-12 01:19:58', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260412012056_208.pdf', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260412012056_208.pdf', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (2, '202404010102', 'e10adc3949ba59abbe56e057f20f883e', '王显坤', 0, 24, NULL, '18888888888', '西安建筑科技大学华清学院', '本科', '计算机科学与技术', NULL, 'null,17', '2026-04-12 01:19:58', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260412012040_247.pdf', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260412012040_247.pdf', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (3, '202404010103', 'e10adc3949ba59abbe56e057f20f883e', '张三', 1, 22, NULL, '13012345678', '西安建筑科技大学华清学院', '本科', '会计学', NULL, 'null,29', '2026-04-12 01:19:58', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (4, '202404010105', 'e10adc3949ba59abbe56e057f20f883e', '李四', 0, 22, NULL, '13012345678', '西安建筑科技大学华清学院', '本科', '工商管理', NULL, NULL, '2026-04-12 01:19:58', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (5, '202404010106', 'e10adc3949ba59abbe56e057f20f883e', '王五', 1, 22, NULL, '13012345678', '西安建筑科技大学华清学院', '本科', '网络工程', NULL, NULL, '2026-04-12 01:19:58', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (6, '202404010107', 'e10adc3949ba59abbe56e057f20f883e', '刘六', 0, 22, NULL, '13012345678', '西安建筑科技大学华清学院', '本科', '大数据', NULL, NULL, '2026-04-12 01:19:58', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (7, '202404010108', 'e10adc3949ba59abbe56e057f20f883e', '李七', 1, 22, NULL, '13012345678', '西安建筑科技大学华清学院', '本科', '商务英语', NULL, NULL, '2026-04-12 01:19:58', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (8, '202404010109', 'e10adc3949ba59abbe56e057f20f883e', '丈八', 0, 22, NULL, '13012345678', '西安建筑科技大学华清学院', '本科', '工程造假', NULL, NULL, '2026-04-12 01:19:58', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (9, '202404010110', 'e10adc3949ba59abbe56e057f20f883e', '陈九', 1, 22, NULL, '13012345678', '西安建筑科技大学华清学院', '本科', '软件工程', NULL, NULL, '2026-04-12 01:19:58', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260412021711_13.pdf', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260412021711_13.pdf', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (10, '202404010111', 'e10adc3949ba59abbe56e057f20f883e', '叁始', 0, 22, NULL, '13012345678', '西安建筑科技大学华清学院', '本科', '行政管理', NULL, NULL, '2026-04-12 01:19:58', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260412021651_436.pdf', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260412021651_436.pdf', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (11, '20240401141', 'e10adc3949ba59abbe56e057f20f883e', '唐玉珍', 0, 23, NULL, '18901627204', '西安电子科技大学长安学院', '专科', '机械设计制造及其自动化', NULL, NULL, '2026-04-22 10:44:45', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (12, '20240401142', 'e10adc3949ba59abbe56e057f20f883e', '帅秀芳', 1, 22, NULL, '15646417080', '西北工业大学明德学院', '本科', '会计学', NULL, NULL, '2026-04-22 10:44:45', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (13, '20240401143', 'e10adc3949ba59abbe56e057f20f883e', '王秀云', 0, 20, NULL, '14309232719', '西安交通大学城市学院', '专科', '人力资源管理', NULL, NULL, '2026-04-22 10:44:45', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (14, '20240401144', 'e10adc3949ba59abbe56e057f20f883e', '卢健', 1, 21, NULL, '18912419049', '西安电子科技大学长安学院', '专科', '机械设计制造及其自动化', NULL, NULL, '2026-04-22 10:44:45', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (15, '20240401145', 'e10adc3949ba59abbe56e057f20f883e', '李刚', 0, 24, NULL, '14149190586', '西北工业大学明德学院', '本科', '国际经济与贸易', NULL, NULL, '2026-04-22 10:44:45', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (16, '20240401146', 'e10adc3949ba59abbe56e057f20f883e', '陈岩', 1, 20, NULL, '17716572628', '西北工业大学明德学院', '专升本', '法学', NULL, NULL, '2026-04-22 10:44:45', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (17, '20240401147', 'e10adc3949ba59abbe56e057f20f883e', '张霞', 1, 23, NULL, '17945314737', '陕西师范大学镐京学院', '专升本', '自动化', NULL, NULL, '2026-04-22 10:44:45', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (18, '20240401148', 'e10adc3949ba59abbe56e057f20f883e', '胡鑫', 1, 20, NULL, '17527354549', '西北工业大学明德学院', '专升本', '电气工程及其自动化', NULL, NULL, '2026-04-22 10:44:45', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (19, '20240401149', 'e10adc3949ba59abbe56e057f20f883e', '李秀梅', 0, 20, NULL, '14678377701', '西北工业大学明德学院', '本科', '自动化', NULL, NULL, '2026-04-22 10:44:45', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (20, '20240401100', 'e10adc3949ba59abbe56e057f20f883e', '纪桂芝', 0, 20, NULL, '15332181960', '西安建筑科技大学华清学院', '本科', '机械设计制造及其自动化', NULL, NULL, '2026-04-24 00:07:22', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (21, '20240401101', 'e10adc3949ba59abbe56e057f20f883e', '方淑珍', 0, 24, NULL, '18083863794', '西安建筑科技大学华清学院', '本科', '软件工程', NULL, NULL, '2026-04-24 00:07:22', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (22, '20240401102', 'e10adc3949ba59abbe56e057f20f883e', '陈丹', 1, 22, NULL, '14351161559', '西北工业大学明德学院', '本科', '建筑学', NULL, NULL, '2026-04-24 00:07:22', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (23, '20240401103', 'e10adc3949ba59abbe56e057f20f883e', '王利', 0, 23, NULL, '13849593103', '西北工业大学明德学院', '本科', '市场营销', NULL, NULL, '2026-04-24 00:07:22', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (24, '20240401104', 'e10adc3949ba59abbe56e057f20f883e', '刘峰', 0, 23, NULL, '15752553419', '西安交通大学城市学院', '专升本', '市场营销', NULL, NULL, '2026-04-24 00:07:22', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (25, '20240401105', 'e10adc3949ba59abbe56e057f20f883e', '聂金凤', 0, 23, NULL, '17483503056', '西北工业大学明德学院', '本科', '机械设计制造及其自动化', NULL, NULL, '2026-04-24 00:07:22', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (26, '20240401106', '14e1b600b1fd579f47433b88e8d85291', '李桂花', 1, 21, NULL, '17672423884', '陕西师范大学镐京学院', '专科', '商务英语', NULL, NULL, '2026-04-24 00:07:22', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (27, '20240401107', 'e10adc3949ba59abbe56e057f20f883e', '黄建平', 1, 22, NULL, '14287101226', '陕西师范大学镐京学院', '本科', '自动化', NULL, NULL, '2026-04-24 00:07:22', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (28, '20240401108', 'e10adc3949ba59abbe56e057f20f883e', '李玉华', 1, 24, NULL, '17848018451', '西北工业大学明德学院', '专科', '土木工程', NULL, NULL, '2026-04-24 00:07:22', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (29, '20240401109', 'e10adc3949ba59abbe56e057f20f883e', '陈秀华', 1, 20, NULL, '15828148932', '西北工业大学明德学院', '本科', '法学', NULL, NULL, '2026-04-24 00:07:22', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (30, '20240401110', 'e10adc3949ba59abbe56e057f20f883e', '胡帆', 0, 24, NULL, '15701543039', '西安建筑科技大学华清学院', '本科', '财务管理', NULL, NULL, '2026-04-24 00:07:22', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (31, '20240401111', 'e10adc3949ba59abbe56e057f20f883e', '刘淑珍', 0, 24, NULL, '14278248963', '陕西师范大学镐京学院', '专升本', '机械设计制造及其自动化', NULL, NULL, '2026-04-24 00:07:22', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (32, '20240401112', 'e10adc3949ba59abbe56e057f20f883e', '董俊', 1, 23, NULL, '15787133150', '陕西师范大学镐京学院', '专升本', '市场营销', NULL, NULL, '2026-04-24 00:07:22', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (33, '20240401113', 'e10adc3949ba59abbe56e057f20f883e', '郭云', 0, 20, NULL, '13031051834', '西安电子科技大学长安学院', '本科', '法学', NULL, NULL, '2026-04-24 00:07:22', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (34, '20240401114', 'e10adc3949ba59abbe56e057f20f883e', '关玉华', 0, 25, NULL, '18973763116', '西北工业大学明德学院', '专科', '软件工程', NULL, NULL, '2026-04-24 00:07:22', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (35, '20240401115', 'e10adc3949ba59abbe56e057f20f883e', '徐建平', 1, 25, NULL, '13106513338', '西安电子科技大学长安学院', '本科', '软件工程', NULL, NULL, '2026-04-24 00:07:22', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (36, '20240401116', 'e10adc3949ba59abbe56e057f20f883e', '卓杰', 0, 22, NULL, '17317810801', '西安交通大学城市学院', '本科', '软件工程', NULL, NULL, '2026-04-24 00:07:22', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (37, '20240401117', 'e10adc3949ba59abbe56e057f20f883e', '苏红', 1, 23, NULL, '14602606474', '西安电子科技大学长安学院', '专升本', '法学', NULL, NULL, '2026-04-24 00:07:22', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (40, '2026050401', 'e10adc3949ba59abbe56e057f20f883e', '测试', 0, 21, NULL, '18888888888', '西安建筑科技大学华清', '本科', '软件设计', NULL, NULL, '2026-05-04 21:26:26', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_uxinxi
-- ----------------------------
DROP TABLE IF EXISTS `t_uxinxi`;
CREATE TABLE `t_uxinxi`  (
  `uxinxiId` int(11) NOT NULL AUTO_INCREMENT,
  `uxinxiName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `uxinxiMark` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `uxinxiMark1` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `uxinxiMark2` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `uxinxiImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `uxinxiImgName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `uxinxiDate` datetime DEFAULT NULL,
  `uxinxiZong` int(11) DEFAULT NULL,
  `uxinxiZong1` int(11) DEFAULT NULL,
  `uxinxiZong2` int(11) DEFAULT NULL,
  `uxinxiDouble` double DEFAULT NULL,
  `uxinxiDouble1` double DEFAULT NULL,
  `uxinxiDouble2` double DEFAULT NULL,
  `uxinxiType` int(11) DEFAULT NULL,
  `uxinxiType1` int(11) DEFAULT NULL,
  `uxinxiType2` int(11) DEFAULT NULL,
  `uxtypeId` int(11) DEFAULT NULL,
  `uxtypeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bumenId` int(11) DEFAULT NULL,
  `bumenName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buyuanId` int(11) DEFAULT NULL,
  `buyuanName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buzhiId` int(11) DEFAULT NULL,
  `buzhiName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `roleName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY USING BTREE (`uxinxiId`)
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_uxinxi
-- ----------------------------
INSERT INTO `t_uxinxi` VALUES (2, '白科技云技术有限公司', '软件开发', '高校就业协议', '通过', 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260423221720_141.pdf', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260423221720_141.pdf', '2026-04-23 22:17:32', NULL, NULL, NULL, 8000, NULL, NULL, 1, NULL, NULL, 2, '私企', 2, '王显坤', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_uxinxi` VALUES (5, '6666', '111', '1111', '', 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260504225546_166.pdf', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260504225546_166.pdf', '2026-04-25 01:01:46', NULL, NULL, NULL, 1111, NULL, NULL, 0, NULL, NULL, 4, '外企', 2, '王显坤', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_uxinxi` VALUES (8, '小米', 'ui设计工程师', '已上传附件', '', 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260429201407_306.pdf', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260429201407_306.pdf', '2026-04-29 20:14:34', NULL, NULL, NULL, 30000, NULL, NULL, 0, NULL, NULL, 3, '国企', 1, '王强', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_uxinxi` VALUES (9, '创业公司', '董', '已修改', '协议上传错误', 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260504201713_859.pdf', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260504201713_859.pdf', '2026-05-04 20:17:18', NULL, NULL, NULL, 1, NULL, NULL, 2, NULL, NULL, 1, '创业', 4, '李四', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_uxinxi` VALUES (10, '华为', '前端工程师', '11', '', 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260504223807_90.pdf', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260504223807_90.pdf', '2026-05-04 22:38:09', NULL, NULL, NULL, 100000, NULL, NULL, 0, NULL, NULL, 3, '国企', 34, '关玉华', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_uxinxi` VALUES (11, '大米', '项目经理', '1', NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260504224351_979.pdf', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260504224351_979.pdf', '2026-05-04 22:43:54', NULL, NULL, NULL, 2000000, NULL, NULL, 0, NULL, NULL, 2, '私企', 26, '李桂花', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_uxinxi` VALUES (12, 'eng', '测试', '测试', NULL, NULL, NULL, '2026-05-04 22:56:44', NULL, NULL, NULL, 1000000, NULL, NULL, 0, NULL, NULL, 4, '外企', 3, '张三', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_uxtype
-- ----------------------------
DROP TABLE IF EXISTS `t_uxtype`;
CREATE TABLE `t_uxtype`  (
  `uxtypeId` int(11) NOT NULL AUTO_INCREMENT,
  `uxtypeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `uxtypeMark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY USING BTREE (`uxtypeId`)
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_uxtype
-- ----------------------------
INSERT INTO `t_uxtype` VALUES (1, '创业', '创业');
INSERT INTO `t_uxtype` VALUES (2, '私企', '私企');
INSERT INTO `t_uxtype` VALUES (3, '国企', '国企');
INSERT INTO `t_uxtype` VALUES (4, '外企', '外企');

-- ----------------------------
-- Table structure for t_uyijian
-- ----------------------------
DROP TABLE IF EXISTS `t_uyijian`;
CREATE TABLE `t_uyijian`  (
  `uyijianId` int(11) NOT NULL AUTO_INCREMENT,
  `uyijianName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `uyijianMark` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `uyijianMark1` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `uyijianMark2` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `uyijianImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `uyijianImgName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `uyijianDate` datetime DEFAULT NULL,
  `uyijianZong` int(11) DEFAULT NULL,
  `uyijianZong1` int(11) DEFAULT NULL,
  `uyijianZong2` int(11) DEFAULT NULL,
  `uyijianDouble` double DEFAULT NULL,
  `uyijianDouble1` double DEFAULT NULL,
  `uyijianDouble2` double DEFAULT NULL,
  `uyijianType` int(11) DEFAULT NULL,
  `uyijianType1` int(11) DEFAULT NULL,
  `uyijianType2` int(11) DEFAULT NULL,
  `uxtypeId` int(11) DEFAULT NULL,
  `uxtypeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bumenId` int(11) DEFAULT NULL,
  `bumenName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buyuanId` int(11) DEFAULT NULL,
  `buyuanName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buzhiId` int(11) DEFAULT NULL,
  `buzhiName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `roleName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY USING BTREE (`uyijianId`)
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_uyijian
-- ----------------------------
INSERT INTO `t_uyijian` VALUES (1, '管理员大大', '我找不到工作', '找不到工作你怪我？', NULL, NULL, NULL, '2026-04-20 00:00:44', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, '王显坤', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_yonghu
-- ----------------------------
DROP TABLE IF EXISTS `t_yonghu`;
CREATE TABLE `t_yonghu`  (
  `yonghuId` int(11) NOT NULL AUTO_INCREMENT,
  `yonghuName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yonghuPassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yonghuXingming` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yonghuSex` int(11) DEFAULT NULL,
  `yonghuAge` int(11) DEFAULT NULL,
  `yonghuPhone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yonghuMinzu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yonghuMark` longtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `yonghuMark1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yonghuMark2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yonghuMark3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yonghuMark4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yonghuDate` datetime DEFAULT NULL,
  `yonghuDate1` datetime DEFAULT NULL,
  `yonghuDate2` datetime DEFAULT NULL,
  `yonghuType` int(11) DEFAULT NULL,
  `yonghuType1` int(11) DEFAULT NULL,
  `yonghuType2` int(11) DEFAULT NULL,
  `yonghuDouble` double DEFAULT NULL,
  `yonghuDouble1` double DEFAULT NULL,
  `yonghuDouble2` double DEFAULT NULL,
  `yonghuZong` int(11) DEFAULT NULL,
  `yonghuZong1` int(11) DEFAULT NULL,
  `yonghuZong2` int(11) DEFAULT NULL,
  `yonghuImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yonghuImgName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bumenId` int(11) DEFAULT NULL,
  `bumenName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buyuanId` int(11) DEFAULT NULL,
  `buyuanName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buzhiId` int(11) DEFAULT NULL,
  `buzhiName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY USING BTREE (`yonghuId`)
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_yonghu
-- ----------------------------
INSERT INTO `t_yonghu` VALUES (5, 'qiye1', 'e10adc3949ba59abbe56e057f20f883e', '张经理', 0, 30, '12988888888', NULL, NULL, '西安某某科技股份有限公司', '西安市高新区软件新城二期 A 座 xxx', NULL, NULL, '2026-04-12 02:24:32', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260412022910_635.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260412022910_635.jpg', NULL, NULL, NULL, NULL, NULL, NULL, 4, '股份公司');
INSERT INTO `t_yonghu` VALUES (6, 'qiye2', 'e10adc3949ba59abbe56e057f20f883e', '李部长', 1, 40, '18888888888', NULL, NULL, '陕西XX精密机械工程有限公司', '西安市经开区未央路中段 x 号', NULL, NULL, '2026-04-12 02:31:09', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260412023204_571.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260412023204_571.jpg', NULL, NULL, NULL, NULL, NULL, NULL, 1, '国企信息');
INSERT INTO `t_yonghu` VALUES (7, 'qiye3', 'e10adc3949ba59abbe56e057f20f883e', 'HR Sarah', 1, 30, '18888888888', NULL, NULL, '思特xx（西安）技术有限公司', '西安市碑林区长安北路 x 号', NULL, NULL, '2026-04-12 02:33:27', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260412023350_195.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260412023350_195.jpg', NULL, NULL, NULL, NULL, NULL, NULL, 2, '外企信息');
INSERT INTO `t_yonghu` VALUES (8, 'qiye4', 'e10adc3949ba59abbe56e057f20f883e', 'HR 招聘组（校招办）', 0, 32, '18888888888', NULL, NULL, '小米通讯技术有限公司', '西安市高新区环普科技产业园 E 座', NULL, NULL, '2026-04-12 02:35:18', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, 'http://127.0.0.1:8080/vs263xianzhaopins/file/20260412024023_446.jpg', 'http://127.0.0.1:8080/vs263xianzhaopins/downFile?filename=20260412024023_446.jpg', NULL, NULL, NULL, NULL, NULL, NULL, 3, '私营企业');

-- ----------------------------
-- Table structure for t_yxinxi
-- ----------------------------
DROP TABLE IF EXISTS `t_yxinxi`;
CREATE TABLE `t_yxinxi`  (
  `yxinxiId` int(11) NOT NULL AUTO_INCREMENT,
  `yxinxiName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yxinxiMark` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yxinxiMark1` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yxinxiMark2` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yxinxiImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yxinxiImgName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yxinxiDate` datetime DEFAULT NULL,
  `yxinxiZong` int(11) DEFAULT NULL,
  `yxinxiZong1` int(11) DEFAULT NULL,
  `yxinxiZong2` int(11) DEFAULT NULL,
  `yxinxiDouble` double DEFAULT NULL,
  `yxinxiDouble1` double DEFAULT NULL,
  `yxinxiDouble2` double DEFAULT NULL,
  `yxinxiType` int(11) DEFAULT NULL,
  `yxinxiType1` int(11) DEFAULT NULL,
  `yxinxiType2` int(11) DEFAULT NULL,
  `yxtypeId` int(11) DEFAULT NULL,
  `yxtypeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yonghuId` int(11) DEFAULT NULL,
  `yonghuName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bumenId` int(11) DEFAULT NULL,
  `bumenName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buyuanId` int(11) DEFAULT NULL,
  `buyuanName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buzhiId` int(11) DEFAULT NULL,
  `buzhiName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `roleName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY USING BTREE (`yxinxiId`)
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_yxinxi
-- ----------------------------
INSERT INTO `t_yxinxi` VALUES (1, '大发撒飒飒飒飒飒飒飒飒飒飒飒飒啊啊撒阿萨飒飒飒飒撒阿萨飒飒撒飒飒啊飒飒', '发的谁谁谁谁谁谁谁谁谁水水水水水水水水水水水水水水水水水水水水谁谁谁水水水水水水水水水水水水', NULL, NULL, NULL, NULL, '2026-04-02 11:38:39', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 1, '企业1', 6, 'yonghu6', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_yxinxi` VALUES (2, '明早8点线上笔试', '哦', NULL, NULL, NULL, NULL, '2026-04-12 03:26:21', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 5, '张经理', 2, '王显坤', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_yxinxi` VALUES (3, '恭喜入职', NULL, NULL, NULL, NULL, NULL, '2026-04-12 03:28:00', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 5, '张经理', 2, '王显坤', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_yyijian
-- ----------------------------
DROP TABLE IF EXISTS `t_yyijian`;
CREATE TABLE `t_yyijian`  (
  `yyijianId` int(11) NOT NULL AUTO_INCREMENT,
  `yyijianName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yyijianMark` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yyijianMark1` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yyijianMark2` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yyijianImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yyijianImgName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yyijianDate` datetime DEFAULT NULL,
  `yyijianZong` int(11) DEFAULT NULL,
  `yyijianZong1` int(11) DEFAULT NULL,
  `yyijianZong2` int(11) DEFAULT NULL,
  `yyijianDouble` double DEFAULT NULL,
  `yyijianDouble1` double DEFAULT NULL,
  `yyijianDouble2` double DEFAULT NULL,
  `yyijianType` int(11) DEFAULT NULL,
  `yyijianType1` int(11) DEFAULT NULL,
  `yyijianType2` int(11) DEFAULT NULL,
  `yxtypeId` int(11) DEFAULT NULL,
  `yxtypeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `yonghuId` int(11) DEFAULT NULL,
  `yonghuName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bumenId` int(11) DEFAULT NULL,
  `bumenName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buyuanId` int(11) DEFAULT NULL,
  `buyuanName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `buzhiId` int(11) DEFAULT NULL,
  `buzhiName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `roleName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY USING BTREE (`yyijianId`)
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_yyijian
-- ----------------------------
INSERT INTO `t_yyijian` VALUES (1, '无法上传职位', '管理员您好，目前无法上传职位信息，请排查处理！', '收到，我们正在处理！', NULL, NULL, NULL, '2026-05-05 03:03:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 5, '张经理', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
