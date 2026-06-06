-- 更新就业类型表，添加完整的七大去向
-- 先清空现有数据（如果需要保留可跳过此步）
DELETE FROM `t_uxtype`;

-- 插入完整的七大去向
INSERT INTO `t_uxtype` VALUES (1, '签约就业', '签约就业');
INSERT INTO `t_uxtype` VALUES (2, '国内升学', '国内升学');
INSERT INTO `t_uxtype` VALUES (3, '留学', '留学');
INSERT INTO `t_uxtype` VALUES (4, '考公事业单位', '考公事业单位');
INSERT INTO `t_uxtype` VALUES (5, '灵活就业', '灵活就业');
INSERT INTO `t_uxtype` VALUES (6, '自主创业', '自主创业');
INSERT INTO `t_uxtype` VALUES (7, '未就业', '未就业');

-- 如果只想添加缺失的类型，可以使用以下语句
-- INSERT INTO `t_uxtype` (`uxtypeName`, `uxtypeMark`) SELECT '签约就业', '签约就业' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM `t_uxtype` WHERE `uxtypeName` = '签约就业');
-- INSERT INTO `t_uxtype` (`uxtypeName`, `uxtypeMark`) SELECT '国内升学', '国内升学' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM `t_uxtype` WHERE `uxtypeName` = '国内升学');
-- INSERT INTO `t_uxtype` (`uxtypeName`, `uxtypeMark`) SELECT '留学', '留学' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM `t_uxtype` WHERE `uxtypeName` = '留学');
-- INSERT INTO `t_uxtype` (`uxtypeName`, `uxtypeMark`) SELECT '考公事业单位', '考公事业单位' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM `t_uxtype` WHERE `uxtypeName` = '考公事业单位');
-- INSERT INTO `t_uxtype` (`uxtypeName`, `uxtypeMark`) SELECT '灵活就业', '灵活就业' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM `t_uxtype` WHERE `uxtypeName` = '灵活就业');
-- INSERT INTO `t_uxtype` (`uxtypeName`, `uxtypeMark`) SELECT '自主创业', '自主创业' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM `t_uxtype` WHERE `uxtypeName` = '自主创业');
-- INSERT INTO `t_uxtype` (`uxtypeName`, `uxtypeMark`) SELECT '未就业', '未就业' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM `t_uxtype` WHERE `uxtypeName` = '未就业');
