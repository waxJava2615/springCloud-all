/*
Navicat MySQL Data Transfer

Source Server         : docker_mysql
Source Server Version : 50732
Source Host           : 192.168.0.210:3306
Source Database       : starry_sky_admin

Target Server Type    : MYSQL
Target Server Version : 50732
File Encoding         : 65001

Date: 2022-01-25 15:59:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_menu`;
CREATE TABLE `sys_admin_menu` (
  `id` bigint(20) NOT NULL COMMENT '菜单ID',
  `name` varchar(255) NOT NULL COMMENT '菜单名称',
  `url` varchar(255) NOT NULL COMMENT '菜单地址',
  `only_key` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '菜单父ID',
  `option` int(11) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL COMMENT 'IP',
  `hide` int(255) DEFAULT '0' COMMENT '显示隐藏',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` bigint(20) DEFAULT NULL,
  `order` bigint(20) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_admin_menu
-- ----------------------------
INSERT INTO `sys_admin_menu` VALUES ('1', '员工管理', '/employ/list.do', 'employ', null, '0', 'el-icon-location', null, '0', null, '2022-01-19 17:01:56', '1', '6000');
INSERT INTO `sys_admin_menu` VALUES ('2', '菜单管理', '/menu/list.do', 'menu', null, '0', 'el-icon-location', null, '0', null, '2022-01-19 17:01:00', '1', '10000');
INSERT INTO `sys_admin_menu` VALUES ('3', '部门管理', '/dept', 'dept', null, '0', 'el-icon-location', null, '0', null, '2022-01-19 17:01:24', '1', '9000');
INSERT INTO `sys_admin_menu` VALUES ('4', '技术部', '/technical.do', 'technical', '3', '0', 'el-icon-location', null, '0', null, '2022-01-19 17:01:35', '1', '8000');
INSERT INTO `sys_admin_menu` VALUES ('5', '人事部', '/personnel.do', 'personnel', '3', '0', 'el-icon-location', null, '0', null, '2022-01-19 17:01:46', '1', '7000');
INSERT INTO `sys_admin_menu` VALUES ('933299029689016320', '权限管理', '/permission/list.do', 'permission', null, '0', 'el-icon-location', null, '0', '2022-01-19 09:57:09', '2022-01-19 09:57:44', '1', '0');
INSERT INTO `sys_admin_menu` VALUES ('933404513439883264', '角色设置', '/permission/role.do', 'permission-type', '933299029689016320', '0', '', '', '0', '2022-01-19 16:56:18', '2022-01-19 17:01:10', '1', '0');
INSERT INTO `sys_admin_menu` VALUES ('933404513439883265', '测试1', '/permission/role.do', 'permission-type', null, '1', '', '', '1', '2022-01-19 16:56:18', '2022-01-21 15:16:41', '1', '0');

-- ----------------------------
-- Table structure for sys_admin_operation
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_operation`;
CREATE TABLE `sys_admin_operation` (
  `id` bigint(20) NOT NULL COMMENT '操作ID',
  `name` varchar(255) NOT NULL COMMENT '操作名称',
  `operation_code` int(11) NOT NULL COMMENT '操作CODE',
  `intercept_url_prefix` varchar(255) NOT NULL COMMENT 'URL前缀',
  `icon` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父ID',
  `ip` varchar(255) DEFAULT NULL COMMENT 'IP',
  `hide` int(10) DEFAULT '0' COMMENT '显示隐藏',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` bigint(20) DEFAULT NULL,
  `order` bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_admin_operation
-- ----------------------------
INSERT INTO `sys_admin_operation` VALUES ('1', '菜单管理', '10000', '/menu', null, null, null, '0', null, null, null, null);
INSERT INTO `sys_admin_operation` VALUES ('2', '菜单新增', '10010', '/push.do', 'el-icon-plus', '1', null, '0', null, null, null, null);
INSERT INTO `sys_admin_operation` VALUES ('3', '菜单修改', '10020', '/edit.do', 'el-icon-edit', '1', null, '0', null, null, null, null);
INSERT INTO `sys_admin_operation` VALUES ('4', '菜单删除', '10030', '/remove.do', 'el-icon-delete', '1', null, '0', null, null, null, null);
INSERT INTO `sys_admin_operation` VALUES ('5', '菜单导出', '10040', '/export.do', 'el-icon-files', '1', null, '0', null, null, null, null);

-- ----------------------------
-- Table structure for sys_admin_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_permission`;
CREATE TABLE `sys_admin_permission` (
  `id` bigint(20) NOT NULL COMMENT '权限ID',
  `title` varchar(255) DEFAULT NULL COMMENT '权限类型',
  `ip` varchar(255) DEFAULT NULL COMMENT 'IP',
  `hide` int(10) DEFAULT '0' COMMENT '显示隐藏',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` bigint(20) DEFAULT NULL,
  `order` bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_admin_permission
-- ----------------------------
INSERT INTO `sys_admin_permission` VALUES ('1', 'menu', null, '0', null, null, null, null);
INSERT INTO `sys_admin_permission` VALUES ('2', 'operation', null, '0', null, null, null, null);

-- ----------------------------
-- Table structure for sys_admin_permission_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_permission_menu_relation`;
CREATE TABLE `sys_admin_permission_menu_relation` (
  `id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  `ip` varchar(255) DEFAULT NULL COMMENT 'IP',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` bigint(20) DEFAULT NULL,
  `order` bigint(20) DEFAULT NULL COMMENT '排序',
  `hide` int(10) DEFAULT '0' COMMENT '显示隐藏',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_admin_permission_menu_relation
-- ----------------------------
INSERT INTO `sys_admin_permission_menu_relation` VALUES ('1', '1', '1', null, null, null, null, null, '0');
INSERT INTO `sys_admin_permission_menu_relation` VALUES ('2', '1', '2', null, null, null, null, null, '0');
INSERT INTO `sys_admin_permission_menu_relation` VALUES ('3', '1', '4', null, null, null, null, null, '0');

-- ----------------------------
-- Table structure for sys_admin_permission_operation_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_permission_operation_relation`;
CREATE TABLE `sys_admin_permission_operation_relation` (
  `id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  `operation_id` bigint(20) NOT NULL COMMENT '操作ID',
  `ip` varchar(255) DEFAULT NULL COMMENT 'ip',
  `hide` int(10) DEFAULT '0' COMMENT '显示隐藏',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` bigint(20) DEFAULT NULL,
  `order` bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_admin_permission_operation_relation
-- ----------------------------
INSERT INTO `sys_admin_permission_operation_relation` VALUES ('1', '2', '1', null, '0', null, null, null, null);
INSERT INTO `sys_admin_permission_operation_relation` VALUES ('2', '2', '2', null, '0', null, null, null, null);
INSERT INTO `sys_admin_permission_operation_relation` VALUES ('3', '2', '3', null, '0', null, null, null, null);
INSERT INTO `sys_admin_permission_operation_relation` VALUES ('4', '2', '4', null, '0', null, null, null, null);
INSERT INTO `sys_admin_permission_operation_relation` VALUES ('5', '2', '9', null, '0', null, null, null, null);

-- ----------------------------
-- Table structure for sys_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_role`;
CREATE TABLE `sys_admin_role` (
  `id` bigint(20) NOT NULL COMMENT '角色ID',
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  `summary` text,
  `ip` varchar(255) DEFAULT NULL COMMENT 'IP',
  `hide` int(10) DEFAULT '0' COMMENT '显示隐藏',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` bigint(20) DEFAULT NULL,
  `order` bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_admin_role
-- ----------------------------
INSERT INTO `sys_admin_role` VALUES ('1', 'admin', null, null, '0', null, null, null, null);
INSERT INTO `sys_admin_role` VALUES ('2', 'user', null, null, '0', null, null, null, null);
INSERT INTO `sys_admin_role` VALUES ('3', 'default', null, null, '0', null, null, null, null);

-- ----------------------------
-- Table structure for sys_admin_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_role_permission_relation`;
CREATE TABLE `sys_admin_role_permission_relation` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  `ip` varchar(255) DEFAULT NULL COMMENT 'ip',
  `hide` int(10) DEFAULT '0' COMMENT '显示隐藏',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` bigint(20) DEFAULT NULL,
  `order` bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_admin_role_permission_relation
-- ----------------------------
INSERT INTO `sys_admin_role_permission_relation` VALUES ('1', '1', '1', null, '0', null, null, null, null);
INSERT INTO `sys_admin_role_permission_relation` VALUES ('2', '1', '2', null, '0', null, null, null, null);
INSERT INTO `sys_admin_role_permission_relation` VALUES ('3', '2', '1', null, '0', null, null, null, null);

-- ----------------------------
-- Table structure for sys_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_user`;
CREATE TABLE `sys_admin_user` (
  `id` bigint(20) NOT NULL COMMENT '后台用户ID',
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `logo` varchar(255) DEFAULT NULL COMMENT '用户图片地址',
  `phone` varchar(20) NOT NULL COMMENT '手机号',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `password_salt` varchar(255) DEFAULT NULL COMMENT '加盐算法,盐值',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `updated_by` bigint(20) DEFAULT NULL,
  `order` bigint(20) DEFAULT NULL COMMENT '排序',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0 正常用户  1 禁止登陆  2 注销用户',
  `ip` varchar(255) DEFAULT NULL COMMENT 'ip',
  `hide` int(10) DEFAULT '0' COMMENT '显示隐藏',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_admin_user
-- ----------------------------
INSERT INTO `sys_admin_user` VALUES ('1', 'wax', null, '18888888888', '789@qq.com', '123456', null, '2021-08-24 09:29:46', '2021-08-24 09:29:50', null, null, '0', null, '0');
INSERT INTO `sys_admin_user` VALUES ('2', 'vip', '', '100000000', 'aaaaaaaaa@qq.com', '123456', '', '2021-08-24 09:29:46', '2021-08-24 09:29:50', null, null, '0', '', '0');

-- ----------------------------
-- Table structure for sys_admin_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_user_role_relation`;
CREATE TABLE `sys_admin_user_role_relation` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `ip` varchar(255) DEFAULT NULL COMMENT 'ip',
  `hide` int(10) DEFAULT '0' COMMENT '显示隐藏',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` bigint(20) DEFAULT NULL,
  `order` bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_admin_user_role_relation
-- ----------------------------
INSERT INTO `sys_admin_user_role_relation` VALUES ('1', '1', '1', null, '0', null, null, null, null);
INSERT INTO `sys_admin_user_role_relation` VALUES ('2', '1', '3', null, '0', null, null, null, null);
