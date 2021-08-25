

/*
 Navicat Premium Data Transfer

 Source Server         : docker_mysql
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : 192.168.0.210:3306
 Source Schema         : starry_sky_admin

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 25/08/2021 15:45:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_menu`;
CREATE TABLE `sys_admin_menu`  (
  `id` bigint(20) NOT NULL COMMENT '菜单ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单地址',
  `parent` bigint(20) NULL DEFAULT NULL COMMENT '菜单父ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_admin_menu
-- ----------------------------
INSERT INTO `sys_admin_menu` VALUES (1, '测试', 'sssss', NULL);

-- ----------------------------
-- Table structure for sys_admin_operation
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_operation`;
CREATE TABLE `sys_admin_operation`  (
  `id` bigint(20) NOT NULL COMMENT '操作ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作名称',
  `operation_code` int(11) NOT NULL COMMENT '操作CODE',
  `intercept_url_prefix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'URL前缀',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_admin_operation
-- ----------------------------

-- ----------------------------
-- Table structure for sys_admin_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_permission`;
CREATE TABLE `sys_admin_permission`  (
  `id` bigint(20) NOT NULL COMMENT '权限ID',
  `type` int(11) NULL DEFAULT NULL COMMENT '权限类型 0菜单  1操作'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_admin_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sys_admin_permission_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_permission_menu_relation`;
CREATE TABLE `sys_admin_permission_menu_relation`  (
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_admin_permission_menu_relation
-- ----------------------------

-- ----------------------------
-- Table structure for sys_admin_permission_operation_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_permission_operation_relation`;
CREATE TABLE `sys_admin_permission_operation_relation`  (
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  `operation_id` bigint(20) NOT NULL COMMENT '操作ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_admin_permission_operation_relation
-- ----------------------------

-- ----------------------------
-- Table structure for sys_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_role`;
CREATE TABLE `sys_admin_role`  (
  `id` bigint(20) NOT NULL COMMENT '角色ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_admin_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_admin_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_role_permission_relation`;
CREATE TABLE `sys_admin_role_permission_relation`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_admin_role_permission_relation
-- ----------------------------

-- ----------------------------
-- Table structure for sys_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_user`;
CREATE TABLE `sys_admin_user`  (
  `id` bigint(20) NOT NULL COMMENT '后台用户ID',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `password_salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加盐算法,盐值',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '0 正常用户  1 禁止登陆  2 注销用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_admin_user
-- ----------------------------
INSERT INTO `sys_admin_user` VALUES (1, 'wax', '18888888888', '789@qq.com', '123456', NULL, '2021-08-24 09:29:46', '2021-08-24 09:29:50', 0);

-- ----------------------------
-- Table structure for sys_admin_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_user_role_relation`;
CREATE TABLE `sys_admin_user_role_relation`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_admin_user_role_relation
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
