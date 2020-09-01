/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : admin

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-09-09 10:02:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '资源名称',
  `type` varchar(20) DEFAULT NULL COMMENT '资源类型：0,1,2(目录,菜单or按钮)',
  `url` varchar(100) DEFAULT NULL COMMENT '访问url地址',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限代码字符串',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父ID',
  `parent_name` varchar(50) DEFAULT NULL COMMENT 'parent_ids',
  `parent_ids` varchar(100) DEFAULT NULL COMMENT '父节点id列表串，用/分割',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `sort` bigint(20) DEFAULT NULL COMMENT '排序号',
  `is_lock` bit(1) DEFAULT NULL COMMENT '账号是否锁定，1：锁定，0未锁定',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '系统管理', '0', null, null, '0', null, '0/', '&#xe620;', '1', '\0', '2017-09-29 10:23:55', '2017-09-29 10:23:58');
INSERT INTO `permission` VALUES ('2', '管理员管理', '1', '/admin/user', 'user:list', '1', '系统管理', '1/', null, '1', '\0', '2017-09-29 10:25:08', '2017-09-29 10:25:10');
INSERT INTO `permission` VALUES ('3', '新增', '2', null, 'user:create', '2', '管理员管理', null, null, '1', '\0', '2017-09-29 10:27:42', '2017-09-29 10:27:49');
INSERT INTO `permission` VALUES ('4', '修改', '2', null, 'user:update', '2', '管理员管理', null, null, '2', '\0', '2017-09-29 10:27:42', '2017-09-29 10:27:49');
INSERT INTO `permission` VALUES ('5', '删除', '2', null, 'user:delete', '2', '管理员管理', null, null, '3', '\0', '2017-09-29 10:27:42', '2017-09-29 10:27:49');
INSERT INTO `permission` VALUES ('6', '查看', '2', null, 'user:view', '2', '管理员管理', null, null, '4', '\0', '2017-09-29 10:27:42', '2017-09-29 10:27:49');
INSERT INTO `permission` VALUES ('7', '禁用|启用', '2', null, 'user:status', '2', '管理员管理', null, null, '4', '\0', '2017-09-29 10:27:42', '2017-09-29 10:27:49');
INSERT INTO `permission` VALUES ('8', '角色管理', '1', '/admin/role', 'role:list', '1', '系统管理', '1/', null, '2', '\0', '2017-09-29 10:27:42', '2017-09-29 10:27:49');
INSERT INTO `permission` VALUES ('9', '新增', '2', null, 'role:create', '8', '角色管理', null, null, '1', '\0', '2017-09-29 10:27:42', '2017-09-29 10:27:49');
INSERT INTO `permission` VALUES ('10', '修改', '2', null, 'role:update', '8', '角色管理', null, null, '2', '\0', '2017-09-29 10:27:42', '2017-09-29 10:27:49');
INSERT INTO `permission` VALUES ('11', '删除', '2', null, 'role:delete', '8', '角色管理', null, null, '3', '\0', '2017-09-29 10:27:42', '2017-09-29 10:27:49');
INSERT INTO `permission` VALUES ('12', '查看', '2', null, 'role:view', '8', '角色管理', null, null, '4', '\0', '2017-09-29 10:27:42', '2017-09-29 10:27:49');
INSERT INTO `permission` VALUES ('13', '授权', '2', null, 'role:permission', '8', '角色管理', null, null, '5', '\0', '2017-09-29 10:27:42', '2017-09-29 10:27:49');
INSERT INTO `permission` VALUES ('14', '权限管理', '1', '/admin/permission', 'permission:list', '1', '系统管理', '1/', null, '3', '\0', '2017-09-29 10:27:42', '2017-09-29 10:27:49');
INSERT INTO `permission` VALUES ('15', '新增', '2', null, 'permission:create', '14', '权限管理', null, null, '1', '\0', '2017-09-29 10:27:42', '2017-09-29 10:27:49');
INSERT INTO `permission` VALUES ('16', '修改', '2', null, 'permission:update', '14', '权限管理', null, null, '2', '\0', '2017-09-29 10:27:42', '2017-09-29 10:27:49');
INSERT INTO `permission` VALUES ('17', '删除', '2', null, 'permission:delete', '14', '权限管理', null, null, '3', '\0', '2017-09-29 10:27:42', '2017-09-29 10:27:49');
INSERT INTO `permission` VALUES ('18', '查看', '2', null, 'permission:view', '14', '权限管理', null, null, '4', '\0', '2017-09-29 10:27:42', '2017-09-29 10:27:49');
INSERT INTO `permission` VALUES ('19', '日志管理', '0', '', null, '0', null, '0/', '&#xe629;', '2', '\0', '2017-09-29 10:27:42', '2017-09-29 10:27:49');
INSERT INTO `permission` VALUES ('20', '操作日志', '1', '/admin/log', 'log:list', '19', '日志管理', '19/', null, '1', '\0', '2017-09-29 10:27:42', '2017-09-29 10:27:49');
INSERT INTO `permission` VALUES ('21', '删除', '2', null, 'log:delete', '20', '操作日志', null, null, '1', '\0', '2017-09-29 10:27:42', '2017-09-29 10:27:49');
INSERT INTO `permission` VALUES ('22', '查看', '2', null, 'log:view', '20', '操作日志', null, null, '2', '\0', '2017-09-29 10:27:42', '2017-09-29 10:27:49');
INSERT INTO `permission` VALUES ('23', '公共管理', '0', null, null, '0', null, '0/', '&#xe609;', '3', '\0', null, null);
INSERT INTO `permission` VALUES ('24', '新闻咨询', '1', '/admin/news/list', 'news:list', '23', '公共管理', '23/', null, '1', '\0', null, null);
INSERT INTO `permission` VALUES ('25', '新增', '2', null, 'news:create', '24', '新闻咨询', null, null, '1', '\0', null, null);
INSERT INTO `permission` VALUES ('26', '修改', '2', null, 'news:update', '24', '新闻咨询', null, null, '2', '\0', null, null);
INSERT INTO `permission` VALUES ('27', '删除', '2', null, 'news:delete', '24', '新闻咨询', null, null, '3', '\0', null, null);
INSERT INTO `permission` VALUES ('28', '查看', '2', null, 'news:view', '24', '新闻咨询', null, null, '4', '\0', null, null);
INSERT INTO `permission` VALUES ('29', '项目管理', '1', '/admin/project/list', 'project:list', '23', '公共管理', '23/', null, '2', '\0', null, null);
INSERT INTO `permission` VALUES ('30', '新增', '2', null, 'project:create', '29', '项目管理', null, null, '1', '\0', null, null);
INSERT INTO `permission` VALUES ('31', '修改', '2', null, 'project:update', '29', '项目管理', null, null, '2', '\0', null, null);
INSERT INTO `permission` VALUES ('32', '查看', '2', null, 'project:view', '29', '项目管理', null, null, '3', '\0', null, null);
INSERT INTO `permission` VALUES ('33', '事件', '2', null, 'event:list', '29', '项目管理', null, null, '4', '\0', null, null);
INSERT INTO `permission` VALUES ('34', '新增事件', '2', null, 'event:create', '29', '项目管理', null, null, '5', '\0', null, null);
INSERT INTO `permission` VALUES ('35', '修改事件', '2', null, 'event:update', '29', '项目管理', null, null, '6', '\0', null, null);
INSERT INTO `permission` VALUES ('36', '删除事件', '2', null, 'event:delete', '29', '项目管理', null, null, '7', '\0', null, null);
INSERT INTO `permission` VALUES ('37', '查看事件', '2', null, 'event:view', '29', '项目管理', null, null, '8', '\0', null, null);
INSERT INTO `permission` VALUES ('38', '一张纸项目管理', '0', null, null, '0', null, '0/', '&#xe61d;', '4', '\0', '2017-10-18 11:32:33', '2017-10-18 11:32:42');
INSERT INTO `permission` VALUES ('39', '导航管理', '1', '/nav/goNavs', 'topNav:list', '38', '一张纸项目管理', '38/', null, '1', '\0', '2017-10-18 11:47:39', '2017-10-18 11:47:42');
INSERT INTO `permission` VALUES ('40', 'Banner管理', '1', '/banner/golist', 'banner:list', '38', '一张纸项目管理', '38/', null, '2', '\0', '2017-10-18 16:34:19', '2017-10-18 16:34:22');
INSERT INTO `permission` VALUES ('41', '新增', '2', null, 'topNav:create', '39', '导航管理', '39/', null, '1', '\0', null, null);
INSERT INTO `permission` VALUES ('42', '修改', '2', null, 'topNav:update', '39', '导航管理', '39/', null, '2', '\0', null, null);
INSERT INTO `permission` VALUES ('43', '删除', '2', null, 'topNav:delete', '39', '导航管理', '39/', null, '3', '\0', null, null);
INSERT INTO `permission` VALUES ('47', '新增', '2', null, 'banner:create', '40', 'Banner管理', '40/', null, '1', '\0', null, null);
INSERT INTO `permission` VALUES ('48', '修改', '2', null, 'banner:update', '40', 'Banner管理', '40/', null, '2', '\0', null, null);
INSERT INTO `permission` VALUES ('49', '删除', '2', null, 'banner:delete', '40', 'Banner管理', '40/', null, '3', '\0', null, null);
INSERT INTO `permission` VALUES ('53', '爱心网络管理', '1', '/network/golist', 'network:list', '38', '一张纸项目管理', '38/', '', '3', '\0', '2017-10-18 18:04:07', '2017-10-18 18:04:10');
INSERT INTO `permission` VALUES ('54', '政策法规管理', '1', '/policies/gopoliciesList', 'policies:list', '38', '一张纸项目管理', '38/', null, '4', '\0', '2017-10-19 10:09:16', '2017-10-19 10:09:18');
INSERT INTO `permission` VALUES ('55', '行动规范管理', '1', '/standard/gostandardList', 'standard:list', '38', '一张纸项目管理', '38/', null, '5', '\0', '2017-10-19 10:48:46', '2017-10-19 10:48:51');
INSERT INTO `permission` VALUES ('56', '新增', '2', null, 'network:create', '53', '爱心网络管理', '53/', null, '1', '\0', null, null);
INSERT INTO `permission` VALUES ('57', '修改', '2', null, 'network:update', '53', '爱心网络管理', '53/', null, '2', '\0', null, null);
INSERT INTO `permission` VALUES ('58', '删除', '2', null, 'network:delete', '53', '爱心网络管理', '53/', null, '3', '\0', null, null);
INSERT INTO `permission` VALUES ('62', '新增', '2', null, 'policies:create', '54', '政策法规管理', '54/', null, '1', '\0', null, null);
INSERT INTO `permission` VALUES ('63', '修改', '2', null, 'policies:update', '54', '政策法规管理', '54/', null, '2', '\0', null, null);
INSERT INTO `permission` VALUES ('64', '查看', '2', null, 'policies:view', '54', '政策法规管理', '54/', null, '3', '\0', null, null);
INSERT INTO `permission` VALUES ('65', '删除', '2', null, 'policies:delete', '54', '政策法规管理', '54/', null, '4', '\0', null, null);
INSERT INTO `permission` VALUES ('70', '新增', '2', null, 'standard:create', '55', '行动规范管理', '55/', null, '1', '\0', null, null);
INSERT INTO `permission` VALUES ('71', '修改', '2', null, 'standard:update', '55', '行动规范管理', '55/', null, '2', '\0', null, null);
INSERT INTO `permission` VALUES ('72', '查看', '2', null, 'standard:view', '55', '行动规范管理', '55/', null, '3', '\0', null, null);
INSERT INTO `permission` VALUES ('73', '删除', '2', null, 'standard:delete', '55', '行动规范管理', '55/', null, '4', '\0', null, null);
INSERT INTO `permission` VALUES ('78', '是否展示', '2', null, 'banner:status', '40', 'Banner管理', '40/', null, '4', '\0', null, null);
INSERT INTO `permission` VALUES ('79', '是否展示', '2', null, 'policies:status', '54', '政策法规管理', '54/', null, '5', '\0', null, null);
INSERT INTO `permission` VALUES ('80', '是否展示', '2', null, 'standard:status', '55', '行动规范管理', '55/', null, '5', '\0', null, null);
INSERT INTO `permission` VALUES ('81', '房山产业园', '0', null, null, '0', null, '0/', '&#xe857;', '5', '\0', '2017-11-28 14:06:18', '2017-11-28 14:06:21');
INSERT INTO `permission` VALUES ('82', '新闻管理', '1', '/tpcnews/golist', 'tpcnews:list', '81', '房山产业园', '81/', null, '1', '\0', '2017-11-28 16:47:59', '2017-11-28 16:48:01');
INSERT INTO `permission` VALUES ('83', '新增', '2', null, 'tpcnews:create', '82', '新闻管理', '82/', null, '1', '\0', null, null);
INSERT INTO `permission` VALUES ('84', '删除', '2', null, 'tpcnews:delete', '82', '新闻管理', '82/', null, '2', '\0', null, null);
INSERT INTO `permission` VALUES ('85', '修改', '2', null, 'tpcnews:update', '82', '新闻管理', '82/', null, '3', '\0', null, null);
INSERT INTO `permission` VALUES ('86', '查看', '2', null, 'tpcnews:view', '82', '新闻管理', '82/', null, '4', '\0', null, null);
INSERT INTO `permission` VALUES ('87', '审核', '2', null, 'tpcnews:check', '82', '新闻管理', '82/', null, '5', '\0', null, null);
INSERT INTO `permission` VALUES ('88', '园区政策管理', '1', '/tpcpolicies/golist', 'tpcpolicies:list', '81', '房山产业园', '81/', null, '2', '\0', '2017-12-01 17:17:40', '2017-12-01 17:17:43');
INSERT INTO `permission` VALUES ('89', '新增', '2', null, 'tpcpolicies:create', '88', '园区政策管理', '88/', null, '1', '\0', null, null);
INSERT INTO `permission` VALUES ('90', '删除', '2', null, 'tpcpolicies:delete', '88', '园区政策管理', '88/', null, '2', '\0', null, null);
INSERT INTO `permission` VALUES ('91', '修改', '2', null, 'tpcpolicies:update', '88', '园区政策管理', '88/', null, '3', '\0', null, null);
INSERT INTO `permission` VALUES ('92', '查看', '2', null, 'tpcpolicies:view', '88', '园区政策管理', '88/', null, '4', '\0', null, null);
INSERT INTO `permission` VALUES ('93', '审核', '2', null, 'tpcpolicies:check', '88', '园区政策管理', '88/', null, '5', '\0', null, null);
INSERT INTO `permission` VALUES ('94', '优惠政策管理', '1', '/tpcstandard/golist', 'tpcstandard:list', '81', '房山产业园', '81/', null, '3', '\0', '2017-12-01 17:23:11', '2017-12-01 17:23:14');
INSERT INTO `permission` VALUES ('95', '新增', '2', null, 'tpcstandard:create', '94', '优惠政策管理', '94/', null, '1', '\0', null, null);
INSERT INTO `permission` VALUES ('96', '删除', '2', null, 'tpcstandard:delete', '94', '优惠政策管理', '94/', null, '2', '\0', null, null);
INSERT INTO `permission` VALUES ('97', '修改', '2', null, 'tpcstandard:update', '94', '优惠政策管理', '94/', null, '3', '\0', null, null);
INSERT INTO `permission` VALUES ('98', '查看', '2', null, 'tpcstandard:view', '94', '优惠政策管理', '94/', null, '4', '\0', null, null);
INSERT INTO `permission` VALUES ('99', '审核', '2', null, 'tpcstandard:check', '94', '优惠政策管理', '94/', null, '5', '\0', null, null);
INSERT INTO `permission` VALUES ('100', '园区服务管理', '0', null, null, '0', '', '0/', '&#xe62e;', '6', '\0', '2017-12-08 10:49:30', '2017-12-08 10:49:33');
INSERT INTO `permission` VALUES ('101', '房屋出租', '1', '/tpcrental/golist', 'tpcrental:list', '100', '园区服务管理', '100/', '', '1', '\0', '2017-12-08 11:05:57', '2017-12-08 11:05:59');
INSERT INTO `permission` VALUES ('102', '新增', '2', '', 'tpcrental:create', '101', '房屋出租', '101/', null, '1', '\0', null, null);
INSERT INTO `permission` VALUES ('103', '删除', '2', null, 'tpcrental:delete', '101', '房屋出租', '101/', null, '2', '\0', null, null);
INSERT INTO `permission` VALUES ('104', '修改', '2', null, 'tpcrental:update', '101', '房屋出租', '101/', null, '3', '\0', null, null);
INSERT INTO `permission` VALUES ('105', '查看', '2', null, 'tpcrental:view', '101', '房屋出租', '101/', null, '4', '\0', null, null);
INSERT INTO `permission` VALUES ('106', '审核', '2', null, 'tpcrental:check', '101', '房屋出租', '101/', null, '5', '\0', null, null);
INSERT INTO `permission` VALUES ('107', '推荐', '2', null, 'tpcrental:isUp', '101', '房屋出租', '101/', null, '6', '\0', null, null);
INSERT INTO `permission` VALUES ('108', '服务审核', '1', '/tpcreview/golist', 'tpcreview:list', '100', '园区服务管理', '100/', null, '4', '\0', '2018-01-30 11:54:57', '2018-01-30 11:55:00');
INSERT INTO `permission` VALUES ('109', '查看', '2', null, 'tpcreview:view', '108', '服务审核', '108/', null, '1', '\0', null, null);
INSERT INTO `permission` VALUES ('110', '审核', '2', null, 'tpcreview:check', '108', '服务审核', '108/', null, '2', '\0', null, null);
INSERT INTO `permission` VALUES ('111', '驳回', '2', null, 'tpcreview:check', '108', '服务审核', '108/', null, '3', '\0', null, null);
INSERT INTO `permission` VALUES ('112', '用户权限管理', '1', '/tpcuserpermission/golist', 'tpcuserpermission:list', '81', '房山产业园', '81/', null, '4', '\0', '2018-03-05 17:09:11', '2018-03-05 17:09:15');
INSERT INTO `permission` VALUES ('113', '赋权/夺权', '2', null, 'tpcuserpermission:create', '112', '用户权限管理', '112/', null, '1', '\0', null, null);
INSERT INTO `permission` VALUES ('114', '招聘广告审核', '1', '/tpccompany/golist', 'tpccompany:list', '81', '房山产业园', '81/', null, '5', '\0', '2018-03-07 10:16:29', '2018-03-07 10:16:32');
INSERT INTO `permission` VALUES ('115', '审核/驳回', '2', null, 'tpccompany:check', '114', '房山产业园', '114/', null, '1', '\0', null, null);
INSERT INTO `permission` VALUES ('116', '推荐/还原', '2', null, 'tpccompany:isUp', '114', '房山产业园', '114/', null, '2', '\0', null, null);
INSERT INTO `permission` VALUES ('117', '查看', '2', null, 'tpccompany:view', '114', '房山产业园', '114/', null, '3', '\0', null, null);
INSERT INTO `permission` VALUES ('118', '培训服务审核', '1', '/tpctrain/golist', 'tpctrain:list', '81', '房山产业园', '81/', null, '6', '\0', '2018-03-12 10:15:00', '2018-03-12 10:15:03');
INSERT INTO `permission` VALUES ('119', '审核/驳回', '2', null, 'tpctrain:check', '118', '培训服务审核', '118/', null, '1', '\0', null, null);
INSERT INTO `permission` VALUES ('120', '今日菜单管理', '1', '/tpctodayfood/golist', 'tpctodayfood:list', '81', '房山产业园', '81/', null, '7', '\0', '2018-05-15 09:33:53', '2018-05-15 09:33:56');
INSERT INTO `permission` VALUES ('121', '新增', '2', null, 'tpctodayfood:create', '120', '今日菜单管理', '120/', null, '1', '\0', null, null);
INSERT INTO `permission` VALUES ('122', '删除', '2', null, 'tpctodayfood:delete', '120', '今日菜单管理', '120/', null, '2', '\0', null, null);
INSERT INTO `permission` VALUES ('123', '修改', '2', null, 'tpctodayfood:update', '120', '今日菜单管理', '120/', null, '3', '\0', null, null);
INSERT INTO `permission` VALUES ('124', '是否展示', '2', null, 'tpctodayfood:status', '120', '今日菜单管理', '120/', null, '4', '\0', null, null);
INSERT INTO `permission` VALUES ('125', '到期房屋统计', '1', '/tpcrental/golist2', 'tpcrental:list2', '100', '园区服务管理', '100/', null, '2', '\0', '2018-06-12 16:54:08', '2018-06-12 16:54:11');
INSERT INTO `permission` VALUES ('126', '已租房屋统计', '1', '/tpcrental/golist3', 'tpcrental:list3', '100', '园区服务管理', '100/', null, '3', '\0', '2018-06-14 10:12:29', '2018-06-14 10:12:32');
INSERT INTO `permission` VALUES ('127', '添加', '2', null, 'tpcrental:create3', '126', '已租房屋统计', '126/', null, '1', '\0', null, null);
INSERT INTO `permission` VALUES ('128', '编辑', '2', null, 'tpcrental:update3', '126', '已租房屋统计', '126/', null, '2', '\0', null, null);
INSERT INTO `permission` VALUES ('129', '删除', '2', null, 'tpcrental:delete3', '126', '已租房屋统计', '126/', null, '3', '\0', null, null);
INSERT INTO `permission` VALUES ('130', '餐厅服务', '0', null, null, '0', '', '0/', '&#xe62e;', '7', '\0', '2017-12-08 10:49:30', '2017-12-08 10:49:33');
INSERT INTO `permission` VALUES ('131', '今日订单', '1', '/canteen/toTodayOrderList', 'canteen:list', '130', '餐厅服务', '100/', '', '1', '\0', '2017-12-08 11:05:57', '2017-12-08 11:05:59');
INSERT INTO `permission` VALUES ('132', '订单列表', '1', '/canteen/toOrderList', 'canteen:list', '130', '餐厅服务', '100/', '', '2', '\0', '2017-12-08 11:05:57', '2017-12-08 11:05:59');
INSERT INTO `permission` VALUES ('133', '菜单管理', '1', '/canteen/toCommodityList', 'canteen:list', '130', '餐厅服务', '100/', '', '3', '\0', '2017-12-08 11:05:57', '2017-12-08 11:05:59');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL,
  `perms` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', '至高无上的存在', null, '2017-09-29 10:09:17', '2018-09-04 16:56:30');
INSERT INTO `role` VALUES ('2', '系统管理员', '一人之下万人之上', null, '2017-09-29 10:11:12', '2017-11-10 10:26:48');
INSERT INTO `role` VALUES ('3', '管理员', '一般一般世界第三', null, '2017-09-29 10:11:24', '2017-11-30 14:30:52');
INSERT INTO `role` VALUES ('4', '房山文案', '房山产业园的编辑', null, '2017-11-30 10:34:16', '2018-03-21 11:20:50');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`role_id`,`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2897 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('466', '3', '23', '2017-11-10 10:26:29', '2017-11-10 10:26:29');
INSERT INTO `role_permission` VALUES ('467', '3', '24', '2017-11-10 10:26:29', '2017-11-10 10:26:29');
INSERT INTO `role_permission` VALUES ('468', '3', '28', '2017-11-10 10:26:29', '2017-11-10 10:26:29');
INSERT INTO `role_permission` VALUES ('469', '3', '29', '2017-11-10 10:26:29', '2017-11-10 10:26:29');
INSERT INTO `role_permission` VALUES ('470', '3', '32', '2017-11-10 10:26:29', '2017-11-10 10:26:29');
INSERT INTO `role_permission` VALUES ('471', '3', '33', '2017-11-10 10:26:29', '2017-11-10 10:26:29');
INSERT INTO `role_permission` VALUES ('472', '3', '37', '2017-11-10 10:26:29', '2017-11-10 10:26:29');
INSERT INTO `role_permission` VALUES ('473', '3', '38', '2017-11-10 10:26:29', '2017-11-10 10:26:29');
INSERT INTO `role_permission` VALUES ('474', '3', '39', '2017-11-10 10:26:29', '2017-11-10 10:26:29');
INSERT INTO `role_permission` VALUES ('475', '3', '40', '2017-11-10 10:26:29', '2017-11-10 10:26:29');
INSERT INTO `role_permission` VALUES ('476', '3', '53', '2017-11-10 10:26:29', '2017-11-10 10:26:29');
INSERT INTO `role_permission` VALUES ('477', '3', '54', '2017-11-10 10:26:29', '2017-11-10 10:26:29');
INSERT INTO `role_permission` VALUES ('478', '3', '64', '2017-11-10 10:26:29', '2017-11-10 10:26:29');
INSERT INTO `role_permission` VALUES ('479', '3', '55', '2017-11-10 10:26:29', '2017-11-10 10:26:29');
INSERT INTO `role_permission` VALUES ('480', '3', '72', '2017-11-10 10:26:29', '2017-11-10 10:26:29');
INSERT INTO `role_permission` VALUES ('2173', '4', '1', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2174', '4', '2', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2175', '4', '3', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2176', '4', '4', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2177', '4', '5', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2178', '4', '6', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2179', '4', '7', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2180', '4', '8', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2181', '4', '9', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2182', '4', '10', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2183', '4', '11', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2184', '4', '12', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2185', '4', '13', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2186', '4', '14', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2187', '4', '15', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2188', '4', '16', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2189', '4', '17', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2190', '4', '18', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2191', '4', '19', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2192', '4', '20', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2193', '4', '21', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2194', '4', '22', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2195', '4', '81', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2196', '4', '82', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2197', '4', '83', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2198', '4', '84', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2199', '4', '85', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2200', '4', '86', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2201', '4', '87', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2202', '4', '88', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2203', '4', '89', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2204', '4', '90', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2205', '4', '91', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2206', '4', '92', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2207', '4', '93', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2208', '4', '94', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2209', '4', '95', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2210', '4', '97', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2211', '4', '98', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2212', '4', '99', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2213', '4', '112', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2214', '4', '113', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2215', '4', '114', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2216', '4', '115', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2217', '4', '116', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2218', '4', '117', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2219', '4', '118', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2220', '4', '119', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2221', '4', '100', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2222', '4', '101', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2223', '4', '102', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2224', '4', '103', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2225', '4', '104', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2226', '4', '105', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2227', '4', '106', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2228', '4', '107', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2229', '4', '108', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2230', '4', '109', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2231', '4', '110', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2232', '4', '111', '2018-03-21 11:20:50', '2018-03-21 11:20:50');
INSERT INTO `role_permission` VALUES ('2781', '1', '1', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2782', '1', '2', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2783', '1', '3', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2784', '1', '4', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2785', '1', '5', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2786', '1', '6', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2787', '1', '7', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2788', '1', '8', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2789', '1', '9', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2790', '1', '10', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2791', '1', '11', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2792', '1', '12', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2793', '1', '13', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2794', '1', '14', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2795', '1', '15', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2796', '1', '16', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2797', '1', '17', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2798', '1', '18', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2799', '1', '19', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2800', '1', '20', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2801', '1', '21', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2802', '1', '22', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2803', '1', '23', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2804', '1', '24', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2805', '1', '25', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2806', '1', '26', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2807', '1', '27', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2808', '1', '28', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2809', '1', '29', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2810', '1', '30', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2811', '1', '31', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2812', '1', '32', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2813', '1', '33', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2814', '1', '34', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2815', '1', '35', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2816', '1', '36', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2817', '1', '37', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2818', '1', '38', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2819', '1', '39', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2820', '1', '41', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2821', '1', '42', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2822', '1', '43', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2823', '1', '40', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2824', '1', '47', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2825', '1', '48', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2826', '1', '49', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2827', '1', '78', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2828', '1', '53', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2829', '1', '56', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2830', '1', '57', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2831', '1', '58', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2832', '1', '54', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2833', '1', '62', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2834', '1', '63', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2835', '1', '64', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2836', '1', '65', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2837', '1', '79', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2838', '1', '55', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2839', '1', '70', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2840', '1', '71', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2841', '1', '72', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2842', '1', '73', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2843', '1', '80', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2844', '1', '81', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2845', '1', '82', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2846', '1', '83', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2847', '1', '84', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2848', '1', '85', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2849', '1', '86', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2850', '1', '87', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2851', '1', '88', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2852', '1', '89', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2853', '1', '90', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2854', '1', '91', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2855', '1', '92', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2856', '1', '93', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2857', '1', '94', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2858', '1', '95', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2859', '1', '96', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2860', '1', '97', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2861', '1', '98', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2862', '1', '99', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2863', '1', '112', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2864', '1', '113', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2865', '1', '114', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2866', '1', '115', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2867', '1', '116', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2868', '1', '117', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2869', '1', '118', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2870', '1', '119', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2871', '1', '120', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2872', '1', '121', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2873', '1', '122', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2874', '1', '123', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2875', '1', '124', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2876', '1', '100', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2877', '1', '101', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2878', '1', '102', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2879', '1', '103', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2880', '1', '104', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2881', '1', '105', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2882', '1', '106', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2883', '1', '107', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2884', '1', '125', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2885', '1', '126', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2886', '1', '127', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2887', '1', '128', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2888', '1', '129', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2889', '1', '108', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2890', '1', '109', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2891', '1', '110', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2892', '1', '111', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2893', '1', '130', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2894', '1', '131', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2895', '1', '132', '2018-09-04 16:56:31', '2018-09-04 16:56:31');
INSERT INTO `role_permission` VALUES ('2896', '1', '133', '2018-09-04 16:56:31', '2018-09-04 16:56:31');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `fraction` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '1', '11');
INSERT INTO `student` VALUES ('2', '2', '23');
INSERT INTO `student` VALUES ('3', '3', '33');
INSERT INTO `student` VALUES ('4', '4', '44');
INSERT INTO `student` VALUES ('5', '5', '55');
INSERT INTO `student` VALUES ('6', '6', '66');
INSERT INTO `student` VALUES ('7', '7', '77');
INSERT INTO `student` VALUES ('8', '8', '88');
INSERT INTO `student` VALUES ('9', '9', '99');

-- ----------------------------
-- Table structure for s_commodity
-- ----------------------------
DROP TABLE IF EXISTS `s_commodity`;
CREATE TABLE `s_commodity` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) NOT NULL COMMENT '商品名称',
  `picture` varchar(225) DEFAULT NULL COMMENT '商品名称',
  `box_fee` decimal(11,2) NOT NULL,
  `price` decimal(11,2) NOT NULL COMMENT '价格',
  `status` int(11) unsigned zerofill NOT NULL,
  `description` varchar(100) DEFAULT NULL COMMENT '备注',
  `weight` int(1) NOT NULL DEFAULT '1' COMMENT '显示顺序',
  `type` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COMMENT='外卖订单详情表';

-- ----------------------------
-- Records of s_commodity
-- ----------------------------
INSERT INTO `s_commodity` VALUES ('00000000001', '14214', 'https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=2be641f13b01213fdb3e468e358e5db4/9f510fb30f2442a77fc5888dd343ad4bd01302ca.jpg', '1.24', '123.00', '00000000001', '12412', '4', '1', null, '2018-10-24 13:34:28');
INSERT INTO `s_commodity` VALUES ('00000000002', '米饭', null, '123.00', '1.20', '00000000001', '米饭', '4', '2', '2018-09-18 10:41:00', '2018-10-24 13:34:34');
INSERT INTO `s_commodity` VALUES ('00000000003', '小炒肉', null, '1.20', '6.50', '00000000001', '123', '2', '2', '2018-09-18 10:43:31', '2018-09-25 09:38:19');
INSERT INTO `s_commodity` VALUES ('00000000004', '青菜', null, '1.00', '1.20', '00000000001', '213', '2', '1', '2018-09-18 10:47:02', '2018-09-25 09:38:23');
INSERT INTO `s_commodity` VALUES ('00000000005', '红烧肉', null, '1.00', '32.20', '00000000001', '23', '3', '1', '2018-09-18 10:47:14', '2018-09-18 10:47:14');
INSERT INTO `s_commodity` VALUES ('00000000006', '红烧肉', null, '1.01', '1.26', '00000000001', '请问王企鹅', '4', '1', '2018-09-21 15:40:35', '2018-09-21 15:40:35');
INSERT INTO `s_commodity` VALUES ('00000000007', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');
INSERT INTO `s_commodity` VALUES ('00000000008', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');
INSERT INTO `s_commodity` VALUES ('00000000009', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');
INSERT INTO `s_commodity` VALUES ('00000000010', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');
INSERT INTO `s_commodity` VALUES ('00000000011', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');
INSERT INTO `s_commodity` VALUES ('00000000012', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');
INSERT INTO `s_commodity` VALUES ('00000000013', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');
INSERT INTO `s_commodity` VALUES ('00000000014', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');
INSERT INTO `s_commodity` VALUES ('00000000015', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');
INSERT INTO `s_commodity` VALUES ('00000000016', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');
INSERT INTO `s_commodity` VALUES ('00000000017', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');
INSERT INTO `s_commodity` VALUES ('00000000018', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');
INSERT INTO `s_commodity` VALUES ('00000000019', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');
INSERT INTO `s_commodity` VALUES ('00000000020', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');
INSERT INTO `s_commodity` VALUES ('00000000021', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');
INSERT INTO `s_commodity` VALUES ('00000000022', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');
INSERT INTO `s_commodity` VALUES ('00000000023', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');
INSERT INTO `s_commodity` VALUES ('00000000024', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');
INSERT INTO `s_commodity` VALUES ('00000000025', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');
INSERT INTO `s_commodity` VALUES ('00000000026', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');
INSERT INTO `s_commodity` VALUES ('00000000027', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');
INSERT INTO `s_commodity` VALUES ('00000000028', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');
INSERT INTO `s_commodity` VALUES ('00000000029', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');
INSERT INTO `s_commodity` VALUES ('00000000030', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');
INSERT INTO `s_commodity` VALUES ('00000000031', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');
INSERT INTO `s_commodity` VALUES ('00000000032', '丁丁炒饭', null, '1.00', '123.00', '00000000001', '1', '2', '1', '2018-10-24 13:35:32', '2018-10-24 13:35:32');

-- ----------------------------
-- Table structure for s_delivery_info
-- ----------------------------
DROP TABLE IF EXISTS `s_delivery_info`;
CREATE TABLE `s_delivery_info` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `wx` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sort` int(11) unsigned zerofill NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of s_delivery_info
-- ----------------------------
INSERT INTO `s_delivery_info` VALUES ('0000000001', '111', '石帆11', '3楼316', '13717925050', '00000000001', '2018-09-13 14:17:10', '2018-09-18 19:22:43');
INSERT INTO `s_delivery_info` VALUES ('0000000005', '!23', '132123', '123123', '17610650809', '00000000001', '2018-09-18 19:39:34', '2018-09-18 19:39:34');

-- ----------------------------
-- Table structure for s_order
-- ----------------------------
DROP TABLE IF EXISTS `s_order`;
CREATE TABLE `s_order` (
  `id` bigint(100) NOT NULL COMMENT '主键ID',
  `address` varchar(100) NOT NULL COMMENT '订单名称',
  `box_fee` decimal(11,2) NOT NULL COMMENT '餐盒费',
  `cutlery_fee` decimal(11,2) NOT NULL COMMENT '餐具费',
  `transport_fee` decimal(11,2) NOT NULL COMMENT '配送费',
  `amt` decimal(11,2) NOT NULL COMMENT '金额',
  `user_name` varchar(100) NOT NULL COMMENT '订餐人姓名',
  `phone` varchar(100) NOT NULL COMMENT '订餐人电话',
  `star` int(1) DEFAULT NULL,
  `evaluate` varchar(100) DEFAULT NULL COMMENT '评论',
  `description` varchar(100) NOT NULL COMMENT '备注',
  `status` int(1) NOT NULL COMMENT '订单状态',
  `wx` varchar(100) NOT NULL COMMENT '微信openId',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='外卖订单表';

-- ----------------------------
-- Records of s_order
-- ----------------------------
INSERT INTO `s_order` VALUES ('1', '11', '2.00', '1.00', '2.00', '11.00', '112', '1111', null, null, '1213', '3', '111', '2018-10-12 10:18:21', '2018-09-10 16:04:29');
INSERT INTO `s_order` VALUES ('1536906110460129', 'address', '0.00', '0.00', '0.00', '123.00', 'username', '123124213213', null, null, '��ע', '2', '111', '2018-09-14 14:22:08', '2018-09-14 15:10:58');
INSERT INTO `s_order` VALUES ('1536906759130899', 'address', '0.00', '0.00', '0.00', '1845.00', 'username', '123124213213', null, null, '��ע', '-1', '111', '2018-09-14 14:32:51', '2018-09-15 17:19:20');
INSERT INTO `s_order` VALUES ('1536996575119357', 'address', '0.00', '0.00', '0.00', '3690.00', 'username', '123124213213', null, null, '123123123123', '-1', '111', '2018-09-15 15:29:39', '2018-09-15 17:20:05');
INSERT INTO `s_order` VALUES ('1536996787686152', 'address', '0.00', '0.00', '0.00', '3690.00', 'username', '123124213213', null, null, '123123123123', '-1', '111', '2018-09-15 15:33:08', '2018-09-15 17:20:45');
INSERT INTO `s_order` VALUES ('1536996795183401', 'address', '0.00', '0.00', '0.00', '3690.00', 'username', '123124213213', null, null, '123123123123', '-1', '111', '2018-09-15 15:33:15', '2018-10-24 13:43:21');
INSERT INTO `s_order` VALUES ('1536996989841282', 'address', '0.00', '0.00', '0.00', '3690.00', 'username', '123124213213', null, null, '123123123123', '2', '111', '2018-09-15 15:36:30', '2018-09-17 16:02:48');
INSERT INTO `s_order` VALUES ('1536997000733108', 'address', '0.00', '0.00', '0.00', '3690.00', 'username', '123124213213', null, null, '123123123123', '-1', '111', '2018-09-15 15:36:41', '2018-10-24 13:43:21');
INSERT INTO `s_order` VALUES ('1536997012489219', 'address', '0.00', '0.00', '0.00', '3690.00', 'username', '123124213213', null, null, '123123123123', '-1', '111', '2018-09-15 15:36:53', '2018-10-24 13:43:21');
INSERT INTO `s_order` VALUES ('1536997073953116', 'address', '0.00', '0.00', '0.00', '3690.00', 'username', '123124213213', null, null, '123123123123', '-1', '111', '2018-09-15 15:37:54', '2018-10-24 13:43:21');
INSERT INTO `s_order` VALUES ('1536997092587348', 'address', '0.00', '0.00', '0.00', '3690.00', 'username', '123124213213', null, null, '123123123123', '-1', '111', '2018-09-15 15:38:13', '2018-10-24 13:43:21');
INSERT INTO `s_order` VALUES ('1536997134193584', 'address', '0.00', '0.00', '0.00', '3690.00', 'username', '123124213213', null, null, '123123123123', '-1', '111', '2018-09-15 15:38:54', '2018-10-24 13:43:21');
INSERT INTO `s_order` VALUES ('1536997165424440', 'address', '0.00', '0.00', '0.00', '3690.00', 'username', '123124213213', null, null, '123123123123', '-1', '111', '2018-09-15 15:39:25', '2018-10-24 13:43:21');
INSERT INTO `s_order` VALUES ('1536997315079850', 'address', '0.00', '0.00', '0.00', '3690.00', 'username', '123124213213', null, null, '123123123123', '-1', '111', '2018-09-15 15:41:55', '2018-09-17 16:02:25');
INSERT INTO `s_order` VALUES ('1536998730588241', 'address', '0.00', '0.00', '0.00', '3690.00', 'username', '123124213213', null, null, '123123123123', '-1', '111', '2018-09-15 16:05:31', '2018-10-24 13:43:21');
INSERT INTO `s_order` VALUES ('1536998746898866', 'address', '0.00', '0.00', '0.00', '3690.00', 'username', '123124213213', null, null, '123123123123', '-1', '111', '2018-09-15 16:05:47', '2018-09-15 17:22:13');
INSERT INTO `s_order` VALUES ('1536998798555179', 'address', '0.00', '0.00', '0.00', '3690.00', 'username', '123124213213', null, null, '123123123123', '-1', '111', '2018-09-15 16:06:39', '2018-09-15 17:21:34');
INSERT INTO `s_order` VALUES ('1537155221782175', 'address', '0.00', '0.00', '0.00', '123.00', 'username', '123124213213', null, null, '', '-1', '111', '2018-09-17 11:33:42', '2018-10-24 13:43:20');
INSERT INTO `s_order` VALUES ('1537155469068627', 'address', '0.00', '0.00', '0.00', '246.00', 'username', '123124213213', null, null, '', '-1', '111', '2018-09-17 11:37:49', '2018-10-24 13:43:20');
INSERT INTO `s_order` VALUES ('1537163256437451', 'address', '0.00', '0.00', '0.00', '123.00', 'username', '123124213213', null, null, '', '-1', '111', '2018-09-17 13:47:36', '2018-10-24 13:43:20');
INSERT INTO `s_order` VALUES ('1537163930421301', 'address', '0.00', '0.00', '0.00', '123.00', 'username', '123124213213', null, null, '', '2', '111', '2018-09-17 13:58:50', '2018-09-17 14:20:53');
INSERT INTO `s_order` VALUES ('1537163982532259', 'address', '0.00', '0.00', '0.00', '246.00', 'username', '123124213213', null, null, '', '-1', '111', '2018-09-17 13:59:43', '2018-09-17 14:20:39');
INSERT INTO `s_order` VALUES ('1537165050687703', 'address', '0.00', '0.00', '0.00', '246.00', 'username', '123124213213', null, null, '', '2', '111', '2018-09-17 14:17:31', '2018-09-17 14:17:41');
INSERT INTO `s_order` VALUES ('1537165137151889', 'address', '0.00', '0.00', '0.00', '246.00', 'username', '123124213213', null, null, '', '2', '111', '2018-09-17 14:18:57', '2018-09-17 14:19:04');
INSERT INTO `s_order` VALUES ('1537165326143826', 'address', '0.00', '0.00', '0.00', '2952.00', 'username', '123124213213', null, null, '', '3', '111', '2018-09-17 14:22:06', '2018-09-17 15:10:42');
INSERT INTO `s_order` VALUES ('1537170760110505', '2楼694室', '0.00', '0.00', '0.00', '615.00', '鲁迅', '17610650809', null, null, '', '2', '111', '2018-09-17 15:52:40', '2018-09-17 15:52:43');
INSERT INTO `s_order` VALUES ('1537170788571563', '2楼694室', '0.00', '0.00', '0.00', '615.00', '鲁迅', '17610650809', null, null, '', '2', '111', '2018-09-17 15:53:09', '2018-09-17 15:53:10');
INSERT INTO `s_order` VALUES ('1537170857062832', '2楼694室', '0.00', '0.00', '0.00', '615.00', '鲁迅', '17610650809', null, null, '', '2', '111', '2018-09-17 15:54:17', '2018-09-17 15:54:19');
INSERT INTO `s_order` VALUES ('1537171267775479', '2楼694室', '0.00', '0.00', '0.00', '1353.00', '鲁迅', '17610650809', null, null, '', '2', '111', '2018-09-17 16:01:08', '2018-09-17 16:01:09');
INSERT INTO `s_order` VALUES ('1537240281775902', '3楼316', '0.00', '0.00', '0.00', '284.20', '石帆', '13717925050', null, null, '', '2', '111', '2018-09-18 11:11:22', '2018-09-18 11:11:23');
INSERT INTO `s_order` VALUES ('1537240761065844', '3楼316', '0.00', '0.00', '0.00', '189.80', '石帆', '13717925050', null, null, '%E8%A6%81%E7%83%AD%E7%9A%84', '2', '111', '2018-09-18 11:19:21', '2018-09-18 11:19:26');
INSERT INTO `s_order` VALUES ('1537240852258254', '3楼316', '0.00', '0.00', '0.00', '0.00', '石帆', '13717925050', null, null, '%E9%A3%9E%E9%A3%9E%E9%A3%9E', '2', '111', '2018-09-18 11:20:52', '2018-09-18 11:20:55');
INSERT INTO `s_order` VALUES ('1537241448643994', '3楼316', '0.00', '0.00', '0.00', '1.20', '石帆', '13717925050', null, null, '要热的', '2', '111', '2018-09-18 11:30:49', '2018-09-18 11:30:56');
INSERT INTO `s_order` VALUES ('1537241778069997', '3楼316', '0.00', '0.00', '0.00', '156.40', '石帆', '13717925050', null, null, '热乎的', '2', '111', '2018-09-18 11:36:18', '2018-09-18 11:36:20');
INSERT INTO `s_order` VALUES ('1537241823755748', '3楼316', '0.00', '0.00', '0.00', '312.80', '石帆', '13717925050', null, null, 'undefined', '2', '111', '2018-09-18 11:37:04', '2018-09-18 11:37:06');
INSERT INTO `s_order` VALUES ('1537254314892196', '3楼316', '0.00', '0.00', '0.00', '315.20', '石帆', '13717925050', null, null, '', '2', '111', '2018-09-18 15:05:15', '2018-09-18 15:05:17');
INSERT INTO `s_order` VALUES ('1537255580400895', '3楼316', '0.00', '0.00', '0.00', '3.60', '石帆', '13717925050', null, null, '双打', '3', '111', '2018-09-18 15:26:20', '2018-09-18 15:41:59');
INSERT INTO `s_order` VALUES ('1537255962177156', '3楼316', '0.00', '0.00', '0.00', '119.70', '石帆', '13717925050', null, null, '', '-1', '111', '2018-09-18 15:32:42', '2018-10-24 13:43:20');
INSERT INTO `s_order` VALUES ('1537255983237785', '3楼316', '0.00', '0.00', '0.00', '119.70', '石帆', '13717925050', null, null, '', '2', '111', '2018-09-18 15:33:03', '2018-09-18 16:04:49');
INSERT INTO `s_order` VALUES ('1537256048853390', '3楼316', '0.00', '0.00', '0.00', '119.70', '石帆', '13717925050', null, null, '', '3', '111', '2018-09-18 15:34:09', '2018-09-18 15:41:04');
INSERT INTO `s_order` VALUES ('1537257773235874', '3楼316', '0.00', '0.00', '0.00', '734.70', '石帆11', '13717925050', null, null, '而我却', '2', '111', '2018-09-18 16:02:53', '2018-09-18 16:02:55');
INSERT INTO `s_order` VALUES ('1537259146943166', '3楼316', '0.00', '0.00', '0.00', '312.80', '石帆11', '13717925050', null, null, '', '2', '111', '2018-09-18 16:25:47', '2018-09-18 16:25:49');
INSERT INTO `s_order` VALUES ('1537840062548128', '3楼316', '3.72', '0.00', '1.50', '372.72', '石帆11', '13717925050', null, null, '', '-1', '111', '2018-09-25 09:47:43', '2018-10-24 13:43:20');
INSERT INTO `s_order` VALUES ('1537840131499737', '3楼316', '3.72', '0.00', '1.50', '372.72', '石帆11', '13717925050', null, null, '', '-1', '111', '2018-09-25 09:49:39', '2018-10-24 13:43:20');
INSERT INTO `s_order` VALUES ('1537840286909434', '3楼316', '3.72', '0.00', '1.50', '372.72', '石帆11', '13717925050', null, null, '', '-1', '111', '2018-09-25 09:51:49', '2018-10-24 13:43:20');
INSERT INTO `s_order` VALUES ('1537840613324691', '3楼316', '3.72', '0.00', '1.50', '372.72', '石帆11', '13717925050', null, null, '', '-1', '111', '2018-09-25 09:56:56', '2018-10-24 13:43:20');
INSERT INTO `s_order` VALUES ('1537840613350347', '3楼316', '3.72', '0.00', '1.50', '372.72', '石帆11', '13717925050', null, null, '', '-1', '111', '2018-09-25 09:56:56', '2018-10-24 13:43:20');
INSERT INTO `s_order` VALUES ('1537840613351142', '3楼316', '3.72', '0.00', '1.50', '372.72', '石帆11', '13717925050', null, null, '', '-1', '111', '2018-09-25 09:56:56', '2018-10-24 13:43:20');
INSERT INTO `s_order` VALUES ('1537840613351239', '3楼316', '3.72', '0.00', '1.50', '372.72', '石帆11', '13717925050', null, null, '', '-1', '111', '2018-09-25 09:56:56', '2018-10-24 13:43:20');
INSERT INTO `s_order` VALUES ('1537840613351775', '3楼316', '3.72', '0.00', '1.50', '372.72', '石帆11', '13717925050', null, null, '', '-1', '111', '2018-09-25 09:56:56', '2018-10-24 13:43:20');
INSERT INTO `s_order` VALUES ('1537840615714477', '3楼316', '3.72', '0.00', '1.50', '372.72', '石帆11', '13717925050', null, null, '', '-1', '111', '2018-09-25 09:56:56', '2018-10-24 13:43:20');

-- ----------------------------
-- Table structure for s_orderdetail
-- ----------------------------
DROP TABLE IF EXISTS `s_orderdetail`;
CREATE TABLE `s_orderdetail` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `oid` bigint(100) NOT NULL COMMENT '订单ID',
  `cid` int(11) NOT NULL COMMENT '商品ID',
  `picture` varchar(255) DEFAULT NULL,
  `cname` varchar(100) NOT NULL COMMENT '商品名称',
  `price` decimal(11,2) NOT NULL COMMENT '价格',
  `num` int(11) DEFAULT '0' COMMENT '购买数量',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COMMENT='外卖订单表';

-- ----------------------------
-- Records of s_orderdetail
-- ----------------------------
INSERT INTO `s_orderdetail` VALUES ('00000000001', '1', '1', null, '123', '12.00', '2', null, null);
INSERT INTO `s_orderdetail` VALUES ('00000000002', '1536906110460129', '1', null, '14214', '123.00', '15', '2018-09-14 14:22:05', '2018-09-14 14:22:05');
INSERT INTO `s_orderdetail` VALUES ('00000000003', '1536906759130899', '1', null, '14214', '123.00', '15', '2018-09-14 14:32:49', '2018-09-14 14:32:49');
INSERT INTO `s_orderdetail` VALUES ('00000000004', '1536996575119357', '1', null, '14214', '123.00', '30', '2018-09-15 15:29:39', '2018-09-15 15:29:39');
INSERT INTO `s_orderdetail` VALUES ('00000000005', '1536996787686152', '1', null, '14214', '123.00', '30', '2018-09-15 15:33:08', '2018-09-15 15:33:08');
INSERT INTO `s_orderdetail` VALUES ('00000000006', '1536996795183401', '1', null, '14214', '123.00', '30', '2018-09-15 15:33:15', '2018-09-15 15:33:15');
INSERT INTO `s_orderdetail` VALUES ('00000000007', '1536996989841282', '1', null, '14214', '123.00', '30', '2018-09-15 15:36:30', '2018-09-15 15:36:30');
INSERT INTO `s_orderdetail` VALUES ('00000000008', '1536997000733108', '1', null, '14214', '123.00', '30', '2018-09-15 15:36:41', '2018-09-15 15:36:41');
INSERT INTO `s_orderdetail` VALUES ('00000000009', '1536997012489219', '1', null, '14214', '123.00', '30', '2018-09-15 15:36:52', '2018-09-15 15:36:52');
INSERT INTO `s_orderdetail` VALUES ('00000000010', '1536997073953116', '1', null, '14214', '123.00', '30', '2018-09-15 15:37:54', '2018-09-15 15:37:54');
INSERT INTO `s_orderdetail` VALUES ('00000000011', '1536997092587348', '1', null, '14214', '123.00', '30', '2018-09-15 15:38:13', '2018-09-15 15:38:13');
INSERT INTO `s_orderdetail` VALUES ('00000000012', '1536997134193584', '1', null, '14214', '123.00', '30', '2018-09-15 15:38:54', '2018-09-15 15:38:54');
INSERT INTO `s_orderdetail` VALUES ('00000000013', '1536997165424440', '1', null, '14214', '123.00', '30', '2018-09-15 15:39:25', '2018-09-15 15:39:25');
INSERT INTO `s_orderdetail` VALUES ('00000000014', '1536997315079850', '1', null, '14214', '123.00', '30', '2018-09-15 15:41:55', '2018-09-15 15:41:55');
INSERT INTO `s_orderdetail` VALUES ('00000000015', '1536998730588241', '1', null, '14214', '123.00', '30', '2018-09-15 16:05:31', '2018-09-15 16:05:31');
INSERT INTO `s_orderdetail` VALUES ('00000000016', '1536998746898866', '1', null, '14214', '123.00', '30', '2018-09-15 16:05:47', '2018-09-15 16:05:47');
INSERT INTO `s_orderdetail` VALUES ('00000000017', '1536998798555179', '1', null, '14214', '123.00', '30', '2018-09-15 16:06:39', '2018-09-15 16:06:39');
INSERT INTO `s_orderdetail` VALUES ('00000000018', '1537155221782175', '1', null, '14214', '123.00', '1', '2018-09-17 11:33:42', '2018-09-17 11:33:42');
INSERT INTO `s_orderdetail` VALUES ('00000000019', '1537155469068627', '1', null, '14214', '123.00', '2', '2018-09-17 11:37:49', '2018-09-17 11:37:49');
INSERT INTO `s_orderdetail` VALUES ('00000000020', '1537163256437451', '1', null, '14214', '123.00', '1', '2018-09-17 13:47:36', '2018-09-17 13:47:36');
INSERT INTO `s_orderdetail` VALUES ('00000000021', '1537163930421301', '1', null, '14214', '123.00', '1', '2018-09-17 13:58:50', '2018-09-17 13:58:50');
INSERT INTO `s_orderdetail` VALUES ('00000000022', '1537163982532259', '1', null, '14214', '123.00', '2', '2018-09-17 13:59:43', '2018-09-17 13:59:43');
INSERT INTO `s_orderdetail` VALUES ('00000000023', '1537165050687703', '1', null, '14214', '123.00', '2', '2018-09-17 14:17:31', '2018-09-17 14:17:31');
INSERT INTO `s_orderdetail` VALUES ('00000000024', '1537165137151889', '1', null, '14214', '123.00', '2', '2018-09-17 14:18:57', '2018-09-17 14:18:57');
INSERT INTO `s_orderdetail` VALUES ('00000000025', '1537165326143826', '1', null, '14214', '123.00', '24', '2018-09-17 14:22:06', '2018-09-17 14:22:06');
INSERT INTO `s_orderdetail` VALUES ('00000000026', '1537170760110505', '1', null, '14214', '123.00', '5', '2018-09-17 15:52:40', '2018-09-17 15:52:40');
INSERT INTO `s_orderdetail` VALUES ('00000000027', '1537170788571563', '1', null, '14214', '123.00', '5', '2018-09-17 15:53:09', '2018-09-17 15:53:09');
INSERT INTO `s_orderdetail` VALUES ('00000000028', '1537170857062832', '1', null, '14214', '123.00', '5', '2018-09-17 15:54:17', '2018-09-17 15:54:17');
INSERT INTO `s_orderdetail` VALUES ('00000000029', '1537171267775479', '1', null, '14214', '123.00', '11', '2018-09-17 16:01:08', '2018-09-17 16:01:08');
INSERT INTO `s_orderdetail` VALUES ('00000000030', '1537240281775902', '1', null, '14214', '123.00', '2', '2018-09-18 11:11:22', '2018-09-18 11:11:22');
INSERT INTO `s_orderdetail` VALUES ('00000000031', '1537240281775902', '4', null, '青菜', '1.20', '5', '2018-09-18 11:11:22', '2018-09-18 11:11:22');
INSERT INTO `s_orderdetail` VALUES ('00000000032', '1537240281775902', '5', null, '红烧肉', '32.20', '1', '2018-09-18 11:11:22', '2018-09-18 11:11:22');
INSERT INTO `s_orderdetail` VALUES ('00000000033', '1537240761065844', '1', null, '14214', '123.00', '1', '2018-09-18 11:19:21', '2018-09-18 11:19:21');
INSERT INTO `s_orderdetail` VALUES ('00000000034', '1537240761065844', '4', null, '青菜', '1.20', '2', '2018-09-18 11:19:21', '2018-09-18 11:19:21');
INSERT INTO `s_orderdetail` VALUES ('00000000035', '1537240761065844', '5', null, '红烧肉', '32.20', '2', '2018-09-18 11:19:21', '2018-09-18 11:19:21');
INSERT INTO `s_orderdetail` VALUES ('00000000036', '1537241448643994', '4', null, '青菜', '1.20', '1', '2018-09-18 11:30:49', '2018-09-18 11:30:49');
INSERT INTO `s_orderdetail` VALUES ('00000000037', '1537241778069997', '1', null, '14214', '123.00', '1', '2018-09-18 11:36:18', '2018-09-18 11:36:18');
INSERT INTO `s_orderdetail` VALUES ('00000000038', '1537241778069997', '4', null, '青菜', '1.20', '1', '2018-09-18 11:36:18', '2018-09-18 11:36:18');
INSERT INTO `s_orderdetail` VALUES ('00000000039', '1537241778069997', '5', null, '红烧肉', '32.20', '1', '2018-09-18 11:36:18', '2018-09-18 11:36:18');
INSERT INTO `s_orderdetail` VALUES ('00000000040', '1537241823755748', '1', null, '14214', '123.00', '2', '2018-09-18 11:37:04', '2018-09-18 11:37:04');
INSERT INTO `s_orderdetail` VALUES ('00000000041', '1537241823755748', '2', null, '米饭', '1.20', '2', '2018-09-18 11:37:04', '2018-09-18 11:37:04');
INSERT INTO `s_orderdetail` VALUES ('00000000042', '1537241823755748', '5', null, '红烧肉', '32.20', '2', '2018-09-18 11:37:04', '2018-09-18 11:37:04');
INSERT INTO `s_orderdetail` VALUES ('00000000043', '1537254314892196', '1', null, '14214', '123.00', '2', '2018-09-18 15:05:15', '2018-09-18 15:05:15');
INSERT INTO `s_orderdetail` VALUES ('00000000044', '1537254314892196', '2', null, '米饭', '1.20', '4', '2018-09-18 15:05:15', '2018-09-18 15:05:15');
INSERT INTO `s_orderdetail` VALUES ('00000000045', '1537254314892196', '5', null, '红烧肉', '32.20', '2', '2018-09-18 15:05:15', '2018-09-18 15:05:15');
INSERT INTO `s_orderdetail` VALUES ('00000000046', '1537255580400895', '4', null, '青菜', '1.20', '3', '2018-09-18 15:26:20', '2018-09-18 15:26:20');
INSERT INTO `s_orderdetail` VALUES ('00000000047', '1537255962177156', '3', null, '小炒肉', '6.50', '3', '2018-09-18 15:32:42', '2018-09-18 15:32:42');
INSERT INTO `s_orderdetail` VALUES ('00000000048', '1537255962177156', '4', null, '青菜', '1.20', '3', '2018-09-18 15:32:42', '2018-09-18 15:32:42');
INSERT INTO `s_orderdetail` VALUES ('00000000049', '1537255962177156', '5', null, '红烧肉', '32.20', '3', '2018-09-18 15:32:42', '2018-09-18 15:32:42');
INSERT INTO `s_orderdetail` VALUES ('00000000050', '1537255983237785', '3', null, '小炒肉', '6.50', '3', '2018-09-18 15:33:03', '2018-09-18 15:33:03');
INSERT INTO `s_orderdetail` VALUES ('00000000051', '1537255983237785', '4', null, '青菜', '1.20', '3', '2018-09-18 15:33:03', '2018-09-18 15:33:03');
INSERT INTO `s_orderdetail` VALUES ('00000000052', '1537255983237785', '5', null, '红烧肉', '32.20', '3', '2018-09-18 15:33:03', '2018-09-18 15:33:03');
INSERT INTO `s_orderdetail` VALUES ('00000000053', '1537256048853390', '3', null, '小炒肉', '6.50', '3', '2018-09-18 15:34:09', '2018-09-18 15:34:09');
INSERT INTO `s_orderdetail` VALUES ('00000000054', '1537256048853390', '4', null, '青菜', '1.20', '3', '2018-09-18 15:34:09', '2018-09-18 15:34:09');
INSERT INTO `s_orderdetail` VALUES ('00000000055', '1537256048853390', '5', null, '红烧肉', '32.20', '3', '2018-09-18 15:34:09', '2018-09-18 15:34:09');
INSERT INTO `s_orderdetail` VALUES ('00000000056', '1537257773235874', '1', null, '14214', '123.00', '5', '2018-09-18 16:02:53', '2018-09-18 16:02:53');
INSERT INTO `s_orderdetail` VALUES ('00000000057', '1537257773235874', '3', null, '小炒肉', '6.50', '3', '2018-09-18 16:02:53', '2018-09-18 16:02:53');
INSERT INTO `s_orderdetail` VALUES ('00000000058', '1537257773235874', '4', null, '青菜', '1.20', '3', '2018-09-18 16:02:53', '2018-09-18 16:02:53');
INSERT INTO `s_orderdetail` VALUES ('00000000059', '1537257773235874', '5', null, '红烧肉', '32.20', '3', '2018-09-18 16:02:53', '2018-09-18 16:02:53');
INSERT INTO `s_orderdetail` VALUES ('00000000060', '1537259146943166', '1', null, '14214', '123.00', '2', '2018-09-18 16:25:47', '2018-09-18 16:25:47');
INSERT INTO `s_orderdetail` VALUES ('00000000061', '1537259146943166', '4', null, '青菜', '1.20', '2', '2018-09-18 16:25:47', '2018-09-18 16:25:47');
INSERT INTO `s_orderdetail` VALUES ('00000000062', '1537259146943166', '5', null, '红烧肉', '32.20', '2', '2018-09-18 16:25:47', '2018-09-18 16:25:47');
INSERT INTO `s_orderdetail` VALUES ('00000000063', '1537840062548128', '1', null, '14214', '123.00', '3', '2018-09-25 09:47:43', '2018-09-25 09:47:43');
INSERT INTO `s_orderdetail` VALUES ('00000000064', '1537840131499737', '1', null, '14214', '123.00', '3', '2018-09-25 09:49:10', '2018-09-25 09:49:10');
INSERT INTO `s_orderdetail` VALUES ('00000000065', '1537840286909434', '1', null, '14214', '123.00', '3', '2018-09-25 09:51:42', '2018-09-25 09:51:42');
INSERT INTO `s_orderdetail` VALUES ('00000000066', '1537840613351142', '1', null, '14214', '123.00', '3', '2018-09-25 09:56:56', '2018-09-25 09:56:56');
INSERT INTO `s_orderdetail` VALUES ('00000000067', '1537840615714477', '1', null, '14214', '123.00', '3', '2018-09-25 09:56:56', '2018-09-25 09:56:56');
INSERT INTO `s_orderdetail` VALUES ('00000000068', '1537840613324691', '1', null, '14214', '123.00', '3', '2018-09-25 09:56:56', '2018-09-25 09:56:56');
INSERT INTO `s_orderdetail` VALUES ('00000000069', '1537840613350347', '1', null, '14214', '123.00', '3', '2018-09-25 09:56:56', '2018-09-25 09:56:56');
INSERT INTO `s_orderdetail` VALUES ('00000000070', '1537840613351775', '1', null, '14214', '123.00', '3', '2018-09-25 09:56:56', '2018-09-25 09:56:56');
INSERT INTO `s_orderdetail` VALUES ('00000000071', '1537840613351239', '1', null, '14214', '123.00', '3', '2018-09-25 09:56:56', '2018-09-25 09:56:56');

-- ----------------------------
-- Table structure for tb_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_log`;
CREATE TABLE `tb_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `app_name` char(36) NOT NULL COMMENT '所属应用',
  `log_type` int(11) DEFAULT NULL COMMENT '日志类型，0为操作日志，1为异常日志',
  `username` varchar(100) DEFAULT NULL COMMENT '访问者/请求者',
  `operation` varchar(100) DEFAULT NULL COMMENT '方法描述',
  `method_name` varchar(100) DEFAULT NULL COMMENT '请求方法名称(全路径)',
  `request_method` varchar(20) DEFAULT NULL COMMENT '请求方式(GET,POST,DELETE,PUT)',
  `request_params` varchar(500) DEFAULT NULL COMMENT '请求参数',
  `request_ip` varchar(50) DEFAULT NULL COMMENT '访问者IP',
  `request_uri` varchar(200) DEFAULT NULL COMMENT '请求URI',
  `exception_code` varchar(100) DEFAULT NULL COMMENT '异常码',
  `exception_detail` varchar(2000) DEFAULT NULL COMMENT '异常描述',
  `time_consuming` bigint(20) DEFAULT NULL COMMENT '请求耗时',
  `user_agent` varchar(500) DEFAULT NULL COMMENT '客户端信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=575 DEFAULT CHARSET=utf8 COMMENT='系统日志表';

-- ----------------------------
-- Records of tb_log
-- ----------------------------
INSERT INTO `tb_log` VALUES ('479', '', '0', 'admin', '禁用|启用管理员', 'com.charity.controller.UserController.updateStatus()', 'PUT', '', '127.0.0.1', '/admin/manager/user/status/2', null, null, '40', 'Apache-HttpClient/4.4.1 (Java/1.8.0_51)', '2017-09-29 15:39:38', '2017-09-29 15:39:38');
INSERT INTO `tb_log` VALUES ('480', '', '0', 'admin', '禁用|启用管理员', 'com.charity.controller.UserController.updateStatus()', 'PUT', '', '127.0.0.1', '/admin/manager/user/status/2', null, null, '41', 'Apache-HttpClient/4.4.1 (Java/1.8.0_51)', '2017-09-29 15:39:47', '2017-09-29 15:39:47');
INSERT INTO `tb_log` VALUES ('481', '', '0', 'admin', '禁用|启用管理员', 'com.charity.controller.UserController.updateStatus()', 'PUT', '', '127.0.0.1', '/admin/manager/user/status/1', null, null, '7', 'Apache-HttpClient/4.4.1 (Java/1.8.0_51)', '2017-09-29 15:40:09', '2017-09-29 15:40:09');
INSERT INTO `tb_log` VALUES ('482', '', '0', 'admin', '禁用|启用管理员', 'com.charity.controller.UserController.updateStatus()', 'PUT', '', '127.0.0.1', '/admin/manager/user/status/2', null, null, '48', 'Apache-HttpClient/4.4.1 (Java/1.8.0_51)', '2017-09-29 15:40:22', '2017-09-29 15:40:22');
INSERT INTO `tb_log` VALUES ('483', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '104', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '2017-10-26 14:37:36', '2017-10-26 14:37:36');
INSERT INTO `tb_log` VALUES ('484', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=   的地方&remark=至高无上的存在', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '44', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '2017-10-26 15:03:34', '2017-10-26 15:03:34');
INSERT INTO `tb_log` VALUES ('485', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '33', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '2017-10-26 15:04:25', '2017-10-26 15:04:25');
INSERT INTO `tb_log` VALUES ('486', '', '0', 'admin', '添加管理员', 'com.charity.controller.UserController.saveUser()', 'POST', 'username=admin111&password=&roleName=1&realName=徐文达&sex=1&mobilePhone=18707159107&email=13263160502@163.com', '0:0:0:0:0:0:0:1', '/admin/user', null, null, '155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0', '2017-10-30 16:23:10', '2017-10-30 16:23:10');
INSERT INTO `tb_log` VALUES ('487', '', '0', 'admin', '添加管理员', 'com.charity.controller.UserController.saveUser()', 'POST', 'username=xuwenda&password=&roleName=1&realName=徐文达&sex=1&mobilePhone=18707159107&email=13263160502@163.com', '0:0:0:0:0:0:0:1', '/admin/user', null, null, '55', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0', '2017-10-30 16:35:16', '2017-10-30 16:35:16');
INSERT INTO `tb_log` VALUES ('488', '', '0', 'admin', '添加管理员', 'com.charity.controller.UserController.saveUser()', 'POST', 'username=xuwenda&password=&roleName=2&realName=徐文达&sex=1&mobilePhone=18707159107&email=13263160502@163.com', '0:0:0:0:0:0:0:1', '/admin/user', null, null, '39', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0', '2017-10-30 16:39:15', '2017-10-30 16:39:15');
INSERT INTO `tb_log` VALUES ('489', '', '0', 'admin', '编辑管理员', 'com.charity.controller.UserController.updateUser()', 'PUT', 'username=admin&password=&realName=王思峰&sex=1&mobilePhone=13699211479&email=13699211479@163.com&oldRoleId=1&roleId=1', '0:0:0:0:0:0:0:1', '/admin/user/1', null, null, '9', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0', '2017-10-30 17:18:16', '2017-10-30 17:18:16');
INSERT INTO `tb_log` VALUES ('490', '', '0', 'admin', '添加新闻', 'com.charity.controller.api.NewsController.save()', 'POST', 'id=87&isShow=1&title=中华慈善总会副会长李宏塔：捐一张废纸 献一片爱心 &newsAuthor=中华慈善总会&digest=&content=<div id=\"meta\"> \n <p style=\"text-align: center\"><br></p>\n <p style=\"text-align: center\"><embed type=&imgUrl=http://file.chinacharityfederation.org/Image/20150423/20150423092413_5561_227170.jpg', '0:0:0:0:0:0:0:1', '/admin/news/save', null, null, '168', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0', '2017-10-31 14:00:05', '2017-10-31 14:00:05');
INSERT INTO `tb_log` VALUES ('491', '', '0', 'admin', '禁用|启用管理员', 'com.charity.controller.UserController.updateStatus()', 'PUT', 'timestamp=1509439918692', '0:0:0:0:0:0:0:1', '/admin/user/status/2', null, null, '94', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0', '2017-10-31 16:51:59', '2017-10-31 16:51:59');
INSERT INTO `tb_log` VALUES ('492', '', '0', 'admin', '禁用|启用管理员', 'com.charity.controller.UserController.updateStatus()', 'PUT', 'timestamp=1509439922968', '0:0:0:0:0:0:0:1', '/admin/user/status/2', null, null, '37', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0', '2017-10-31 16:52:03', '2017-10-31 16:52:03');
INSERT INTO `tb_log` VALUES ('493', '', '1', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=管理员&remark=haha &ids=haha ,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,41,42,43,44,45,46,40,47,48,49,50,51,52,53,5', '0:0:0:0:0:0:0:1', '/admin/role/3', 'java.lang.NumberFormatException', 'For input string: \"haha \"', '30', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0', '2017-10-31 17:06:07', '2017-10-31 17:06:07');
INSERT INTO `tb_log` VALUES ('494', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=管理员&remark=haha &ids=38,39,41,42,43,44,45,46,40,47,48,49,50,51,52,53,56,57,58,59,60,61,54,62,63,64,65,66,67,68,69,55,70,7', '0:0:0:0:0:0:0:1', '/admin/role/3', null, null, '208', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0', '2017-10-31 17:07:19', '2017-10-31 17:07:19');
INSERT INTO `tb_log` VALUES ('495', '', '0', 'admin', '添加管理员', 'com.charity.controller.UserController.saveUser()', 'POST', 'username=zhangsan&password=&roleName=3&realName=zs&sex=1&mobilePhone=18718701870&email=18718701870@163.com', '0:0:0:0:0:0:0:1', '/admin/user', null, null, '58', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0', '2017-10-31 17:08:19', '2017-10-31 17:08:19');
INSERT INTO `tb_log` VALUES ('496', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=管理员&remark=haha &ids=23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,41,42,43,44,45,46,40,47,48,49,50,51,52,53,56,57,5', '0:0:0:0:0:0:0:1', '/admin/role/3', null, null, '177', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0', '2017-10-31 17:09:48', '2017-10-31 17:09:48');
INSERT INTO `tb_log` VALUES ('497', '', '0', 'zhangsan', '编辑管理员', 'com.charity.controller.UserController.updateUser()', 'PUT', 'username=zhangsan&password=&realName=zs&sex=1&mobilePhone=18718701870&email=18718701870@163.com&oldRoleId=3&roleId=3', '0:0:0:0:0:0:0:1', '/admin/user/6', null, null, '90', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36 Edge/15.15063', '2017-11-01 11:10:22', '2017-11-01 11:10:22');
INSERT INTO `tb_log` VALUES ('498', '', '0', 'zhangsan', '编辑管理员', 'com.charity.controller.UserController.updateUser()', 'PUT', 'username=zhangsan&password=&realName=zs&sex=1&mobilePhone=18718701870&email=18718701870@163.com&oldRoleId=3&roleId=3', '0:0:0:0:0:0:0:1', '/admin/user/6', null, null, '62', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36 Edge/15.15063', '2017-11-01 11:11:25', '2017-11-01 11:11:25');
INSERT INTO `tb_log` VALUES ('499', '', '0', 'admin', '编辑管理员', 'com.charity.controller.UserController.updateUser()', 'PUT', 'username=zhangsan&password=&realName=zs&sex=1&mobilePhone=18718701870&email=18718701870@163.com&oldRoleId=3&roleId=3', '0:0:0:0:0:0:0:1', '/admin/user/6', null, null, '51', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0', '2017-11-01 11:14:15', '2017-11-01 11:14:15');
INSERT INTO `tb_log` VALUES ('500', '', '0', 'zhangsan', '编辑管理员', 'com.charity.controller.UserController.updateUser()', 'PUT', 'username=zhangsan&password=&realName=zs&sex=1&mobilePhone=18718701870&email=18718701870@163.com&oldRoleId=3&roleId=3', '0:0:0:0:0:0:0:1', '/admin/user/6', null, null, '35', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36 Edge/15.15063', '2017-11-01 11:15:15', '2017-11-01 11:15:15');
INSERT INTO `tb_log` VALUES ('501', '', '0', 'zhangsan', '编辑管理员', 'com.charity.controller.UserController.updateUser()', 'PUT', 'username=zhangsan&password=&realName=xuwenda&sex=1&mobilePhone=18718701870&email=18718701870@163.com&oldRoleId=3&roleId=3', '0:0:0:0:0:0:0:1', '/admin/user/6', null, null, '58', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36 Edge/15.15063', '2017-11-01 11:15:30', '2017-11-01 11:15:30');
INSERT INTO `tb_log` VALUES ('502', '', '0', 'zhangsan', '编辑管理员', 'com.charity.controller.UserController.updateUser()', 'PUT', 'username=zhangsan&password=&realName=11&sex=1&mobilePhone=18718701870&email=18718701870@163.com&oldRoleId=3&roleId=3', '0:0:0:0:0:0:0:1', '/admin/user/6', null, null, '42', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36 Edge/15.15063', '2017-11-01 11:17:31', '2017-11-01 11:17:31');
INSERT INTO `tb_log` VALUES ('503', '', '0', 'zhangsan', '编辑管理员', 'com.charity.controller.UserController.updateUser()', 'PUT', 'username=zhangsan&password=&realName=11&sex=1&mobilePhone=18718701870&email=18718701870@163.com&oldRoleId=3&roleId=3', '0:0:0:0:0:0:0:1', '/admin/user/6', null, null, '34', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36 Edge/15.15063', '2017-11-01 11:20:47', '2017-11-01 11:20:47');
INSERT INTO `tb_log` VALUES ('504', '', '0', 'zhangsan', '编辑管理员', 'com.charity.controller.UserController.updateUser()', 'PUT', 'username=zhangsan&password=&realName=11&sex=1&mobilePhone=18718701870&email=18718701870@163.com&oldRoleId=3&roleId=3', '0:0:0:0:0:0:0:1', '/admin/user/6', null, null, '45', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36 Edge/15.15063', '2017-11-01 11:20:53', '2017-11-01 11:20:53');
INSERT INTO `tb_log` VALUES ('505', '', '0', 'zhangsan', '编辑管理员', 'com.charity.controller.UserController.updateUser()', 'PUT', 'username=zhangsan&password=&realName=11&sex=1&mobilePhone=18718701870&email=18718701870@163.com&oldRoleId=3&roleId=3', '0:0:0:0:0:0:0:1', '/admin/user/6', null, null, '53', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36 Edge/15.15063', '2017-11-01 11:23:18', '2017-11-01 11:23:18');
INSERT INTO `tb_log` VALUES ('506', '', '0', 'zhangsan', '编辑管理员', 'com.charity.controller.UserController.updateUser()', 'PUT', 'username=zhangsan&password=&realName=xwd&sex=1&mobilePhone=18718701870&email=18718701870@163.com&oldRoleId=3&roleId=3', '0:0:0:0:0:0:0:1', '/admin/user/6', null, null, '106', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36 Edge/15.15063', '2017-11-01 11:24:55', '2017-11-01 11:24:55');
INSERT INTO `tb_log` VALUES ('507', '', '0', 'zhangsan', '编辑管理员', 'com.charity.controller.UserController.updateUser()', 'PUT', 'username=zhangsan&password=&realName=xwd&sex=1&mobilePhone=18718701870&email=18718701870@163.com&oldRoleId=3&roleId=3', '0:0:0:0:0:0:0:1', '/admin/user/6', null, null, '43', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36 Edge/15.15063', '2017-11-01 11:30:43', '2017-11-01 11:30:43');
INSERT INTO `tb_log` VALUES ('508', '', '0', 'zhangsan', '编辑管理员', 'com.charity.controller.UserController.updateUser()', 'PUT', 'username=zhangsan&password=&realName=xwd&sex=1&mobilePhone=18718701870&email=18718701870@163.com&oldRoleId=3&roleId=3', '0:0:0:0:0:0:0:1', '/admin/user/6', null, null, '22869', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36 Edge/15.15063', '2017-11-01 11:34:04', '2017-11-01 11:34:04');
INSERT INTO `tb_log` VALUES ('509', '', '0', 'zhangsan', '编辑管理员', 'com.charity.controller.UserController.updateUser()', 'PUT', 'username=zhangsan&password=&realName=xwd&sex=1&mobilePhone=18718701870&email=18718701870@163.com&oldRoleId=3&roleId=3', '0:0:0:0:0:0:0:1', '/admin/user/6', null, null, '92854', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36 Edge/15.15063', '2017-11-01 11:35:54', '2017-11-01 11:35:54');
INSERT INTO `tb_log` VALUES ('510', '', '0', 'admin', '编辑管理员', 'com.charity.controller.UserController.updateUser()', 'PUT', 'username=admin&password=&realName=王思峰&sex=1&mobilePhone=13699211479&email=13699211479@163.com&oldRoleId=1&roleId=1', '0:0:0:0:0:0:0:1', '/admin/user/1', null, null, '5137', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0', '2017-11-01 11:39:01', '2017-11-01 11:39:01');
INSERT INTO `tb_log` VALUES ('511', '', '0', 'zhangsan', '编辑管理员', 'com.charity.controller.UserController.updateUser()', 'PUT', 'username=zhangsan&password=&realName=xwd&sex=1&mobilePhone=18718701870&email=18718701870@163.com&oldRoleId=3&roleId=3', '0:0:0:0:0:0:0:1', '/admin/user/6', null, null, '45', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36 Edge/15.15063', '2017-11-01 11:41:49', '2017-11-01 11:41:49');
INSERT INTO `tb_log` VALUES ('512', '', '0', 'zhangsan', '编辑管理员', 'com.charity.controller.UserController.updateUser()', 'PUT', 'username=zhangsan&password=&realName=xwd&sex=1&mobilePhone=18718701870&email=18718701870@163.com&oldRoleId=3&roleId=3', '0:0:0:0:0:0:0:1', '/admin/user/6', null, null, '37', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36 Edge/15.15063', '2017-11-01 11:42:00', '2017-11-01 11:42:00');
INSERT INTO `tb_log` VALUES ('513', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,23,24,25,26,27,28', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '239', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0', '2017-11-02 18:10:12', '2017-11-02 18:10:12');
INSERT INTO `tb_log` VALUES ('514', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,4', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '251', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0', '2017-11-02 18:21:16', '2017-11-02 18:21:16');
INSERT INTO `tb_log` VALUES ('515', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '215', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0', '2017-11-02 18:22:03', '2017-11-02 18:22:03');
INSERT INTO `tb_log` VALUES ('516', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,27,28,29,30,31,32,33,34,35,36,37,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '222', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0', '2017-11-02 18:23:17', '2017-11-02 18:23:17');
INSERT INTO `tb_log` VALUES ('517', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '210', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0', '2017-11-02 18:24:08', '2017-11-02 18:24:08');
INSERT INTO `tb_log` VALUES ('518', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=管理员&remark=haha &ids=23,24,28,29,32,33,37,38,39,40,78,53,54,64,79,55,72,80', '0:0:0:0:0:0:0:1', '/admin/role/3', null, null, '156', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36 Edge/15.15063', '2017-11-03 09:55:53', '2017-11-03 09:55:53');
INSERT INTO `tb_log` VALUES ('519', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=管理员&remark=haha &ids=23,24,28,29,32,33,37,38,39,40,53,54,64,55,72', '0:0:0:0:0:0:0:1', '/admin/role/3', null, null, '163', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36 Edge/15.15063', '2017-11-03 11:31:13', '2017-11-03 11:31:13');
INSERT INTO `tb_log` VALUES ('520', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '306', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0', '2017-11-07 10:01:36', '2017-11-07 10:01:36');
INSERT INTO `tb_log` VALUES ('521', '', '0', 'admin', '禁用|启用管理员', 'com.charity.controller.UserController.updateStatus()', 'PUT', 'timestamp=1510049474083', '0:0:0:0:0:0:0:1', '/admin/user/status/6', null, null, '53', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0', '2017-11-07 18:11:14', '2017-11-07 18:11:14');
INSERT INTO `tb_log` VALUES ('522', '', '0', 'admin', '禁用|启用管理员', 'com.charity.controller.UserController.updateStatus()', 'PUT', 'timestamp=1510049475534', '0:0:0:0:0:0:0:1', '/admin/user/status/6', null, null, '43', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0', '2017-11-07 18:11:16', '2017-11-07 18:11:16');
INSERT INTO `tb_log` VALUES ('523', '', '0', 'admin', '禁用|启用管理员', 'com.charity.controller.UserController.updateStatus()', 'PUT', 'timestamp=1510049476862', '0:0:0:0:0:0:0:1', '/admin/user/status/6', null, null, '36', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0', '2017-11-07 18:11:17', '2017-11-07 18:11:17');
INSERT INTO `tb_log` VALUES ('524', '', '0', 'admin', '禁用|启用管理员', 'com.charity.controller.UserController.updateStatus()', 'PUT', 'timestamp=1510049478471', '0:0:0:0:0:0:0:1', '/admin/user/status/6', null, null, '44', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0', '2017-11-07 18:11:19', '2017-11-07 18:11:19');
INSERT INTO `tb_log` VALUES ('525', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=管理员&remark=一般一般世界第三&ids=23,24,28,29,32,33,37,38,39,40,53,54,64,55,72', '0:0:0:0:0:0:0:1', '/admin/role/3', null, null, '181', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36 Edge/15.15063', '2017-11-10 10:26:29', '2017-11-10 10:26:29');
INSERT INTO `tb_log` VALUES ('526', '', '1', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=系统管理员&remark=一人之下万人之上&ids=一人之下万人之上', '0:0:0:0:0:0:0:1', '/admin/role/2', 'java.lang.NumberFormatException', 'For input string: \"一人之下万人之上\"', '42', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36 Edge/15.15063', '2017-11-10 10:26:48', '2017-11-10 10:26:48');
INSERT INTO `tb_log` VALUES ('527', '', '0', 'admin', '禁用|启用管理员', 'com.charity.controller.UserController.updateStatus()', 'PUT', 'timestamp=1511843206330', '0:0:0:0:0:0:0:1', '/admin/user/status/6', null, null, '64', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0', '2017-11-28 12:26:46', '2017-11-28 12:26:46');
INSERT INTO `tb_log` VALUES ('528', '', '0', 'admin', '禁用|启用管理员', 'com.charity.controller.UserController.updateStatus()', 'PUT', 'timestamp=1511848240693', '0:0:0:0:0:0:0:1', '/admin/user/status/6', null, null, '73', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0', '2017-11-28 13:50:41', '2017-11-28 13:50:41');
INSERT INTO `tb_log` VALUES ('529', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '247', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0', '2017-11-28 14:10:59', '2017-11-28 14:10:59');
INSERT INTO `tb_log` VALUES ('530', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '240', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0', '2017-11-28 16:49:00', '2017-11-28 16:49:00');
INSERT INTO `tb_log` VALUES ('531', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '239', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0', '2017-11-28 17:17:46', '2017-11-28 17:17:46');
INSERT INTO `tb_log` VALUES ('532', '', '0', 'admin', '添加新闻', 'com.charity.controller.api.NewsController.save()', 'POST', 'id=8&isShow=', '0:0:0:0:0:0:0:1', '/admin/news/save', null, null, '162', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0', '2017-11-29 11:44:33', '2017-11-29 11:44:33');
INSERT INTO `tb_log` VALUES ('533', '', '0', 'admin', '添加新闻', 'com.charity.controller.api.NewsController.save()', 'POST', 'id=8&isShow=', '0:0:0:0:0:0:0:1', '/admin/news/save', null, null, '37', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0', '2017-11-29 11:45:20', '2017-11-29 11:45:20');
INSERT INTO `tb_log` VALUES ('534', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '127.0.0.1', '/admin/role/1', null, null, '259', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0', '2017-11-29 14:25:24', '2017-11-29 14:25:24');
INSERT INTO `tb_log` VALUES ('535', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '127.0.0.1', '/admin/role/1', null, null, '244', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0', '2017-11-29 14:40:22', '2017-11-29 14:40:22');
INSERT INTO `tb_log` VALUES ('536', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '127.0.0.1', '/admin/role/1', null, null, '199', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0', '2017-11-29 14:45:04', '2017-11-29 14:45:04');
INSERT INTO `tb_log` VALUES ('537', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '127.0.0.1', '/admin/role/1', null, null, '281', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0', '2017-11-29 17:49:08', '2017-11-29 17:49:08');
INSERT INTO `tb_log` VALUES ('538', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '332', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0', '2017-11-30 10:31:06', '2017-11-30 10:31:06');
INSERT INTO `tb_log` VALUES ('539', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '210', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0', '2017-11-30 10:33:07', '2017-11-30 10:33:07');
INSERT INTO `tb_log` VALUES ('540', '', '0', 'admin', '添加角色', 'com.charity.controller.RoleController.saveRole()', 'POST', 'name=房山文案&remark=房山产业园的编辑&ids=81,82,83,85,86', '0:0:0:0:0:0:0:1', '/admin/role', null, null, '77', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0', '2017-11-30 10:34:16', '2017-11-30 10:34:16');
INSERT INTO `tb_log` VALUES ('541', '', '0', 'admin', '添加管理员', 'com.charity.controller.UserController.saveUser()', 'POST', 'username=fangshan&password=&roleName=4&realName=文案&sex=0&mobilePhone=13263160502&email=13263160502@163.com', '0:0:0:0:0:0:0:1', '/admin/user', null, null, '47', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0', '2017-11-30 10:35:29', '2017-11-30 10:35:29');
INSERT INTO `tb_log` VALUES ('542', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=管理员&remark=一般一般世界第三&ids=', '0:0:0:0:0:0:0:1', '/admin/role/3', null, null, '157', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0', '2017-11-30 14:30:52', '2017-11-30 14:30:52');
INSERT INTO `tb_log` VALUES ('543', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '369', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0', '2017-12-01 17:38:25', '2017-12-01 17:38:25');
INSERT INTO `tb_log` VALUES ('544', '', '0', 'admin', '添加新闻', 'com.charity.controller.api.NewsController.save()', 'POST', 'id=37&isShow=&websiteUrl=http://localhost:8082/', '127.0.0.1', '/admin/news/save', null, null, '72', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0', '2017-12-04 17:22:54', '2017-12-04 17:22:54');
INSERT INTO `tb_log` VALUES ('545', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=房山文案&remark=房山产业园的编辑&ids=81,82,83,85,86,88,89,91,92,94,95,97,98', '0:0:0:0:0:0:0:1', '/admin/role/4', null, null, '165', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0', '2017-12-06 11:43:28', '2017-12-06 11:43:28');
INSERT INTO `tb_log` VALUES ('546', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '362', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0', '2017-12-08 10:51:24', '2017-12-08 10:51:24');
INSERT INTO `tb_log` VALUES ('547', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '258', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0', '2017-12-08 11:15:21', '2017-12-08 11:15:21');
INSERT INTO `tb_log` VALUES ('548', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=房山文案&remark=房山产业园的编辑&ids=81,82,83,85,86,88,89,91,92,94,95,97,98,100,101,102,104,105', '0:0:0:0:0:0:0:1', '/admin/role/4', null, null, '401', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0', '2017-12-18 13:59:44', '2017-12-18 13:59:44');
INSERT INTO `tb_log` VALUES ('549', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '374', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0', '2018-01-10 16:02:43', '2018-01-10 16:02:43');
INSERT INTO `tb_log` VALUES ('550', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '312', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0', '2018-01-30 12:02:24', '2018-01-30 12:02:24');
INSERT INTO `tb_log` VALUES ('551', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=房山文案&remark=房山产业园的编辑&ids=81,82,83,85,86,88,89,91,92,94,95,97,98,100,101,102,104,105,108,109', '0:0:0:0:0:0:0:1', '/admin/role/4', null, null, '118', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0', '2018-01-30 12:02:43', '2018-01-30 12:02:43');
INSERT INTO `tb_log` VALUES ('552', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '345', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0', '2018-02-01 10:50:33', '2018-02-01 10:50:33');
INSERT INTO `tb_log` VALUES ('553', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '356', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0', '2018-03-05 17:15:19', '2018-03-05 17:15:19');
INSERT INTO `tb_log` VALUES ('554', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '377', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0', '2018-03-07 10:18:32', '2018-03-07 10:18:32');
INSERT INTO `tb_log` VALUES ('555', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '278', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0', '2018-03-07 10:37:57', '2018-03-07 10:37:57');
INSERT INTO `tb_log` VALUES ('556', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '403', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0', '2018-03-07 15:06:23', '2018-03-07 15:06:23');
INSERT INTO `tb_log` VALUES ('557', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '365', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0', '2018-03-12 10:16:33', '2018-03-12 10:16:33');
INSERT INTO `tb_log` VALUES ('558', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=房山文案&remark=房山产业园的编辑&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,81,82,83,84,85,86,87,88,89,90,91,92,93,94,9', '0:0:0:0:0:0:0:1', '/admin/role/4', null, null, '286', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0', '2018-03-21 11:20:51', '2018-03-21 11:20:51');
INSERT INTO `tb_log` VALUES ('559', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '404', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0', '2018-05-15 09:41:54', '2018-05-15 09:41:54');
INSERT INTO `tb_log` VALUES ('560', '', '0', 'admin', '添加新闻', 'com.charity.controller.api.NewsController.save()', 'POST', 'id=1&status=1', '0:0:0:0:0:0:0:1', '/admin/news/save', null, null, '4102', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0', '2018-05-15 12:00:44', '2018-05-15 12:00:44');
INSERT INTO `tb_log` VALUES ('561', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '404', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0', '2018-06-12 16:54:56', '2018-06-12 16:54:56');
INSERT INTO `tb_log` VALUES ('562', '', '0', 'admin', '禁用|启用管理员', 'com.charity.controller.UserController.updateStatus()', 'PUT', 'timestamp=1528877450228', '0:0:0:0:0:0:0:1', '/admin/user/status/7', null, null, '71', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0', '2018-06-13 16:10:50', '2018-06-13 16:10:50');
INSERT INTO `tb_log` VALUES ('563', '', '0', 'admin', '禁用|启用管理员', 'com.charity.controller.UserController.updateStatus()', 'PUT', 'timestamp=1528877456887', '0:0:0:0:0:0:0:1', '/admin/user/status/7', null, null, '35', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0', '2018-06-13 16:10:57', '2018-06-13 16:10:57');
INSERT INTO `tb_log` VALUES ('564', '', '0', 'admin', '禁用|启用管理员', 'com.charity.controller.UserController.updateStatus()', 'PUT', 'timestamp=1528877458376', '0:0:0:0:0:0:0:1', '/admin/user/status/7', null, null, '62', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0', '2018-06-13 16:10:58', '2018-06-13 16:10:58');
INSERT INTO `tb_log` VALUES ('565', '', '0', 'admin', '禁用|启用管理员', 'com.charity.controller.UserController.updateStatus()', 'PUT', 'timestamp=1528877459847', '0:0:0:0:0:0:0:1', '/admin/user/status/7', null, null, '76', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0', '2018-06-13 16:11:00', '2018-06-13 16:11:00');
INSERT INTO `tb_log` VALUES ('566', '', '0', 'admin', '禁用|启用管理员', 'com.charity.controller.UserController.updateStatus()', 'PUT', 'timestamp=1528877466425', '0:0:0:0:0:0:0:1', '/admin/user/status/7', null, null, '32', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0', '2018-06-13 16:11:06', '2018-06-13 16:11:06');
INSERT INTO `tb_log` VALUES ('567', '', '0', 'admin', '禁用|启用管理员', 'com.charity.controller.UserController.updateStatus()', 'PUT', 'timestamp=1528877501753', '0:0:0:0:0:0:0:1', '/admin/user/status/7', null, null, '46', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0', '2018-06-13 16:11:42', '2018-06-13 16:11:42');
INSERT INTO `tb_log` VALUES ('568', '', '0', 'admin', '禁用|启用管理员', 'com.charity.controller.UserController.updateStatus()', 'PUT', 'timestamp=1528877513489', '0:0:0:0:0:0:0:1', '/admin/user/status/7', null, null, '37', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0', '2018-06-13 16:11:54', '2018-06-13 16:11:54');
INSERT INTO `tb_log` VALUES ('569', '', '0', 'admin', '禁用|启用管理员', 'com.charity.controller.UserController.updateStatus()', 'PUT', 'timestamp=1528878371364', '0:0:0:0:0:0:0:1', '/admin/user/status/7', null, null, '40', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0', '2018-06-13 16:26:11', '2018-06-13 16:26:11');
INSERT INTO `tb_log` VALUES ('570', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '378', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0', '2018-06-14 11:59:35', '2018-06-14 11:59:35');
INSERT INTO `tb_log` VALUES ('571', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '411', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0', '2018-06-15 10:01:14', '2018-06-15 10:01:14');
INSERT INTO `tb_log` VALUES ('572', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '394', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0', '2018-06-15 11:58:43', '2018-06-15 11:58:43');
INSERT INTO `tb_log` VALUES ('573', '', '0', 'admin', '编辑管理员', 'com.charity.controller.UserController.updateUser()', 'PUT', 'username=admin111111&password=&realName=王思峰&sex=1&mobilePhone=13699211479&email=13699211479@163.com&oldRoleId=1&roleId=1', '0:0:0:0:0:0:0:1', '/admin/user/1', null, null, '18', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0', '2018-06-19 15:29:09', '2018-06-19 15:29:09');
INSERT INTO `tb_log` VALUES ('574', '', '0', 'admin', '编辑角色', 'com.charity.controller.RoleController.updateRole()', 'PUT', 'name=超级管理员&remark=至高无上的存在&ids=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,3', '0:0:0:0:0:0:0:1', '/admin/role/1', null, null, '515', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36', '2018-09-04 16:56:31', '2018-09-04 16:56:31');

-- ----------------------------
-- Table structure for tb_news_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_news_type`;
CREATE TABLE `tb_news_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_news_type
-- ----------------------------
INSERT INTO `tb_news_type` VALUES ('1', '慈善分会', '1', null, null);
INSERT INTO `tb_news_type` VALUES ('2', '合作媒体', '1', null, null);
INSERT INTO `tb_news_type` VALUES ('3', '合作伙伴', '1', null, null);
INSERT INTO `tb_news_type` VALUES ('4', '政府机构', '1', null, null);
INSERT INTO `tb_news_type` VALUES ('5', '手拉手', '1', null, null);
INSERT INTO `tb_news_type` VALUES ('6', '媒体报道', '2', null, null);
INSERT INTO `tb_news_type` VALUES ('7', '通知公告', '2', null, null);
INSERT INTO `tb_news_type` VALUES ('8', '慈善动态', '2', null, null);
INSERT INTO `tb_news_type` VALUES ('9', '慈善人物', '2', null, null);
INSERT INTO `tb_news_type` VALUES ('10', '爱心视频', '2', null, null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(70) DEFAULT NULL,
  `sex` bit(1) DEFAULT NULL COMMENT '性别  1 男  0 女',
  `real_name` varchar(100) DEFAULT NULL COMMENT '真实姓名',
  `mobile_phone` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `is_lock` bit(1) DEFAULT NULL COMMENT '账号是否锁定，1：锁定，0未锁定',
  `is_delete` bit(1) DEFAULT NULL COMMENT '账号是否删除，1：删除，0未删除',
  `is_admin` bit(1) DEFAULT NULL COMMENT '是否是超级管理员',
  `last_login_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '111111', null, '', '王思峰', '13699211479', '13699211479@163.com', '\0', '\0', '', '2017-09-29 14:32:45', '2017-09-29 14:32:47', '2017-09-29 14:32:50');
INSERT INTO `user` VALUES ('2', 'administrative', 'administrative', null, '', '王思聪', '17610152170', '2320388660@qq.com', '\0', '\0', '\0', '2017-09-29 14:33:05', '2017-09-29 14:32:56', '2017-10-31 16:52:03');
INSERT INTO `user` VALUES ('6', 'zhangsan', '123', '/s*@5s#4$-%s7^3&ol.sa?*/', '', 'xwd', '18718701870', '18718701870@163.com', '\0', '\0', '\0', null, '2017-10-31 17:08:19', '2017-11-28 13:50:41');
INSERT INTO `user` VALUES ('7', 'fangshan', 'fangshan', '/s*@5s#4$-%s7^3&ol.sa?*/', '\0', '文案', '13263160502', '13263160502@163.com', '\0', '\0', '\0', null, '2017-11-30 10:35:29', '2018-06-13 16:26:11');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1', '2017-09-29 15:11:20', '2017-09-29 15:11:27');
INSERT INTO `user_role` VALUES ('2', '2', '2', '2017-09-29 15:11:20', '2017-09-29 15:11:27');
INSERT INTO `user_role` VALUES ('3', '3', '2', '2017-09-29 15:11:20', '2017-09-29 15:11:27');
INSERT INTO `user_role` VALUES ('4', '4', '3', '2017-09-29 15:11:20', '2017-09-29 15:11:27');
INSERT INTO `user_role` VALUES ('5', '5', '3', '2017-09-29 15:11:20', '2017-09-29 15:11:27');
INSERT INTO `user_role` VALUES ('6', '3', '1', '2017-10-30 16:23:10', '2017-10-30 16:23:10');
INSERT INTO `user_role` VALUES ('7', '4', '1', '2017-10-30 16:35:16', '2017-10-30 16:35:16');
INSERT INTO `user_role` VALUES ('8', '5', '2', '2017-10-30 16:39:15', '2017-10-30 16:39:15');
INSERT INTO `user_role` VALUES ('9', '6', '3', '2017-10-31 17:08:19', '2017-10-31 17:08:19');
INSERT INTO `user_role` VALUES ('10', '7', '4', '2017-11-30 10:35:29', '2017-11-30 10:35:29');

-- ----------------------------
-- Table structure for website
-- ----------------------------
DROP TABLE IF EXISTS `website`;
CREATE TABLE `website` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of website
-- ----------------------------
INSERT INTO `website` VALUES ('1', '手拉手献爱心行动', 'http://localhost:8082/', '2017-10-12 12:15:53', '2017-10-12 12:15:56');
INSERT INTO `website` VALUES ('2', '一张纸心献爱心行动', 'http://localhost:8082/', '2017-10-12 12:16:38', '2017-10-12 12:16:40');
