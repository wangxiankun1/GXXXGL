-- 添加数据分析模块导航配置
-- 在数据看板下添加数据分析子菜单

USE db_vs263xianzhaopins;

-- 插入数据分析导航记录
-- jcdaohangId: 36 (新的ID)
-- jcdaohangName: 数据分析
-- jcdaohangNeirong: fenxitu (对应前端页面文件名)
-- jcdaohangPaixu: 3 (排序，在数据看板下显示)
-- jcbiaotiId: 8 (数据看板的ID)
INSERT INTO `t_jcdaohang` VALUES (36, '数据分析', 'fenxitu', NULL, NULL, NULL, 3, 1, 0, NULL, 8, NULL);

-- 验证插入结果
SELECT * FROM `t_jcdaohang` WHERE `jcbiaotiId` = 8 ORDER BY `jcdaohangPaixu`;