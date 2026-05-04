-- 密码MD5加密更新脚本
-- 执行前请先备份数据库！

-- 更新管理员表密码
UPDATE t_admin SET adminPassword = MD5(adminPassword) WHERE adminPassword IS NOT NULL AND adminPassword != '';

-- 更新用户表密码（如果存在）
-- 如果没有 t_user 表，请注释或删除这行
-- UPDATE t_user SET userPassword = MD5(userPassword) WHERE userPassword IS NOT NULL AND userPassword != '';

-- 更新员工表密码（如果存在）
-- 如果没有 t_yonghu 表，请注释或删除这行
-- UPDATE t_yonghu SET yonghuPassword = MD5(yonghuPassword) WHERE yonghuPassword IS NOT NULL AND yonghuPassword != '';

-- 验证更新结果（更新前后对比）
SELECT '更新前admin密码应为明文，更新后应为32位MD5值' AS notice;
SELECT adminId, adminName, adminPassword, LENGTH(adminPassword) AS pwd_length FROM t_admin;
