/*
 Navicat Premium Data Transfer

 Source Server         : 39.108.57.4
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 39.108.57.4:53306
 Source Schema         : linq

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 08/09/2020 14:45:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for linq_collect
-- ----------------------------
DROP TABLE IF EXISTS `linq_collect`;
CREATE TABLE `linq_collect` (
  `collection_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '收藏id',
  `news_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '新闻id',
  `user_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '用户id',
  `collect_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '收藏时间',
  PRIMARY KEY (`collection_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='新闻收藏表';

-- ----------------------------
-- Table structure for linq_comment
-- ----------------------------
DROP TABLE IF EXISTS `linq_comment`;
CREATE TABLE `linq_comment` (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '新闻评论id',
  `news_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '新闻id',
  `comment_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '新闻评论内容',
  `thumbs` bigint(20) DEFAULT '0' COMMENT '点赞数',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` date DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(511) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='新闻评论表';

-- ----------------------------
-- Records of linq_comment
-- ----------------------------
BEGIN;
INSERT INTO `linq_comment` VALUES (42, 5087, '<iframe class=\"ql-video\" frameborder=\"0\" allowfullscreen=\"true\" src=\"https://v.qq.com/x/page/i08308eq8i4.html\"></iframe><p><br></p>', 2, '0', 'admin', '2020-09-04 08:53:34', 'admin', '2020-09-04', '');
COMMIT;

-- ----------------------------
-- Table structure for linq_link
-- ----------------------------
DROP TABLE IF EXISTS `linq_link`;
CREATE TABLE `linq_link` (
  `linq_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '友情链接id',
  `link_name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '友情链接名称',
  `link_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '友情链接地址',
  `email` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '联系人邮件',
  `order_num` int(4) NOT NULL DEFAULT '0' COMMENT '链接显示顺序',
  `create_by` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(511) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`linq_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='友情链接表';

-- ----------------------------
-- Records of linq_link
-- ----------------------------
BEGIN;
INSERT INTO `linq_link` VALUES (30, '百度一下', 'https://www.baidu.com', '18061877017@163.com', 9, 'admin', '2020-08-29 10:29:20', 'admin', '2020-08-29 10:57:53', '百度一下');
INSERT INTO `linq_link` VALUES (31, '正则表达式详解', 'https://www.cnblogs.com/zxin/archive/2013/01/26/2877765.html', '18061877017@163.com', 1, 'admin', '2020-08-29 10:47:35', 'admin', '2020-08-29 10:47:35', '正则表达式详解');
INSERT INTO `linq_link` VALUES (32, 'CSDN', 'https://so.csdn.net/', '18888@qq.com', 2, 'admin', '2020-08-29 10:49:43', 'admin', '2020-08-29 10:49:43', 'CSDN');
INSERT INTO `linq_link` VALUES (33, 'Jquery插件库', 'http://www.jq22.com', '66@jq22.com', 3, 'admin', '2020-08-29 10:51:21', 'admin', '2020-08-29 10:51:21', 'Jquery插件库');
INSERT INTO `linq_link` VALUES (34, 'Github', 'https://github.com/', '9@qq.com', 4, 'admin', '2020-08-29 10:52:08', 'admin', '2020-08-29 10:52:08', 'Github');
INSERT INTO `linq_link` VALUES (35, '码云', 'https://gitee.com/', '5@qq.com', 4, 'admin', '2020-08-29 10:52:42', 'admin', '2020-08-29 10:52:42', '码云');
INSERT INTO `linq_link` VALUES (36, 'Mac动态壁纸', 'https://dynamicwallpaper.club/wallpaper/hqzkn6nai0f', 'mac@qqq.com', 5, 'admin', '2020-08-29 10:53:25', 'admin', '2020-08-29 10:53:25', 'Mac动态壁纸');
INSERT INTO `linq_link` VALUES (37, '哔哩哔哩', 'https://www.bilibili.com/', 'bilibili@163.com', 6, 'admin', '2020-08-29 10:54:17', 'admin', '2020-08-29 10:54:17', '哔哩哔哩');
INSERT INTO `linq_link` VALUES (38, 'Vuex', 'https://vuex.vuejs.org/zh/', 'vue@163.com', 7, 'admin', '2020-08-29 10:55:15', 'admin', '2020-08-29 10:55:15', 'Vuex');
INSERT INTO `linq_link` VALUES (39, 'vue-element-admin', 'https://panjiachen.gitee.io/vue-element-admin-site/zh/guide/', 'admin@163.com', 0, 'admin', '2020-08-29 10:55:54', 'admin', '2020-08-29 11:02:52', 'vue-element-admin');
INSERT INTO `linq_link` VALUES (40, '若依后台管理里系统', 'https://ruoyi.vip/', 'ruoyi@vip.com', 0, 'admin', '2020-08-29 10:56:49', 'admin', '2020-08-29 10:56:49', '若依后台管理里系统');
COMMIT;

-- ----------------------------
-- Table structure for linq_news
-- ----------------------------
DROP TABLE IF EXISTS `linq_news`;
CREATE TABLE `linq_news` (
  `news_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '新闻id',
  `user_id` bigint(20) NOT NULL COMMENT '作者id',
  `news_type_id` bigint(20) NOT NULL COMMENT '新闻类别id',
  `news_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '新闻标题',
  `news_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '新闻内容',
  `news_attr` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '新闻属性 0.头条区新闻 1.幻灯片区新闻 2.热点区新闻',
  `news_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '新闻封面',
  `order_num` int(4) DEFAULT '0' COMMENT '新闻显示顺序',
  `is_public` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '新闻是否公开（0.公开 1.私有）',
  `thumbs` bigint(20) DEFAULT '0' COMMENT '新闻点赞数',
  `visits` bigint(20) DEFAULT '0' COMMENT '新闻浏览量',
  `comments` bigint(20) DEFAULT '0' COMMENT '新闻评论数',
  `collects` bigint(20) DEFAULT '0' COMMENT '新闻收藏数',
  `news_source_author` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '新闻来源原创作者(爬虫使用)',
  `news_source_tags` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '新闻博客分类标签',
  `news_source` varchar(511) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '新闻来源(爬虫使用)',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '审核状态（0.审核中 1.审核成功 2.审核失败）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(511) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8664 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='新闻表';

-- ----------------------------
-- Table structure for linq_news_type
-- ----------------------------
DROP TABLE IF EXISTS `linq_news_type`;
CREATE TABLE `linq_news_type` (
  `news_type_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '新闻类别id',
  `news_type_name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '新闻类别名称',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '新闻类别状态（0正常 1关闭）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(511) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`news_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='新闻类型表';

-- ----------------------------
-- Records of linq_news_type
-- ----------------------------
BEGIN;
INSERT INTO `linq_news_type` VALUES (1, '国际新闻', '0', '0', 'admin', '2020-08-30 12:57:57', 'admin', '2020-08-30 12:57:57', '国际新闻');
INSERT INTO `linq_news_type` VALUES (2, '经济新闻', '0', '0', 'admin', '2020-08-30 12:58:11', 'admin', '2020-08-30 12:58:11', '经济新闻');
INSERT INTO `linq_news_type` VALUES (3, '文教卫生新闻', '0', '0', 'admin', '2020-08-30 12:58:43', 'admin', '2020-08-30 12:58:43', '文教卫生新闻');
INSERT INTO `linq_news_type` VALUES (4, '体育新闻', '0', '0', 'admin', '2020-08-30 12:58:49', 'admin', '2020-08-30 12:58:49', '体育新闻');
INSERT INTO `linq_news_type` VALUES (5, '社会新闻', '0', '0', 'admin', '2020-08-30 12:58:58', 'admin', '2020-08-30 12:58:58', '社会新闻');
INSERT INTO `linq_news_type` VALUES (6, '突发性新闻', '0', '0', 'admin', '2020-08-30 12:59:10', 'admin', '2020-08-30 12:59:31', '对出乎人们预料而突然爆发的事件的报道');
INSERT INTO `linq_news_type` VALUES (7, '延缓性新闻', '0', '0', 'admin', '2020-08-30 12:59:22', 'admin', '2020-08-30 12:59:22', '对逐步发生变化的时间的报道');
INSERT INTO `linq_news_type` VALUES (8, '硬新闻', '0', '0', 'admin', '2020-08-30 12:59:39', 'admin', '2020-08-30 12:59:39', '硬新闻');
INSERT INTO `linq_news_type` VALUES (9, '软新闻', '0', '0', 'admin', '2020-08-30 12:59:45', 'admin', '2020-08-30 12:59:45', '软新闻');
INSERT INTO `linq_news_type` VALUES (10, '科技新闻', '0', '0', 'admin', '2020-08-30 13:00:45', 'admin', '2020-08-30 13:00:45', '科技新闻');
INSERT INTO `linq_news_type` VALUES (11, '军事新闻', '0', '0', 'admin', '2020-08-30 13:00:54', 'admin', '2020-08-30 13:00:54', '军事新闻');
INSERT INTO `linq_news_type` VALUES (12, '文艺新闻', '0', '0', 'admin', '2020-08-30 13:01:01', 'admin', '2020-08-30 13:01:01', '文艺新闻');
INSERT INTO `linq_news_type` VALUES (13, '会议新闻', '0', '0', 'admin', '2020-08-30 13:01:18', 'admin', '2020-08-30 13:01:18', '会议新闻');
INSERT INTO `linq_news_type` VALUES (14, '八卦新闻', '0', '0', 'admin', '2020-08-30 13:01:25', 'admin', '2020-08-30 13:01:25', '八卦新闻');
INSERT INTO `linq_news_type` VALUES (15, '述评新闻', '0', '0', 'admin', '2020-08-30 13:01:48', 'admin', '2020-08-30 13:01:48', '述评新闻');
INSERT INTO `linq_news_type` VALUES (16, '娱乐新闻', '0', '0', 'admin', '2020-08-30 13:19:54', 'admin', '2020-08-30 13:19:54', '娱乐新闻');
INSERT INTO `linq_news_type` VALUES (17, '时报原创新闻	', '0', '0', 'admin', '2020-08-30 13:58:22', 'admin', '2020-08-30 13:58:22', '时报原创新闻	');
INSERT INTO `linq_news_type` VALUES (18, '技术博客周刊', '0', '0', 'admin', '2020-09-01 09:32:23', 'admin', '2020-09-01 09:32:23', '技术博客周刊');
COMMIT;

-- ----------------------------
-- Table structure for linq_user_comment
-- ----------------------------
DROP TABLE IF EXISTS `linq_user_comment`;
CREATE TABLE `linq_user_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `comment_id` bigint(15) NOT NULL COMMENT '评论id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户评论中间表';

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(511) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='参数配置表';

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '联系电话',
  `email` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(511) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES (100, 0, '0', 'Linq新闻', 0, 'linq', '15888888888', 'lyq@qq.com', '0', '0', 'admin', '2020-08-27 20:54:57', 'admin', '2020-08-27 20:54:57', NULL);
INSERT INTO `sys_dept` VALUES (203, 100, '0,100', '研发部门', 1, '林义清 ', '18061877017', '18061877017@163.com', '0', '0', 'admin', '2020-08-28 10:49:53', 'admin', '2020-08-28 10:49:53', '');
INSERT INTO `sys_dept` VALUES (204, 100, '0,100', '运维部门', 2, '林义清', '18061877017', '1888888888@qq.com', '0', '0', 'admin', '2020-08-28 15:36:09', 'admin', '2020-08-28 15:36:09', '');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(511) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='字典数据表';

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '新增操作');
INSERT INTO `sys_dict_data` VALUES (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '修改操作');
INSERT INTO `sys_dict_data` VALUES (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '删除操作');
INSERT INTO `sys_dict_data` VALUES (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '授权操作');
INSERT INTO `sys_dict_data` VALUES (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '强退操作');
INSERT INTO `sys_dict_data` VALUES (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '清空操作');
INSERT INTO `sys_dict_data` VALUES (27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(511) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='字典类型表';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录状态列表');
COMMIT;

-- ----------------------------
-- Table structure for sys_logininfo
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfo`;
CREATE TABLE `sys_logininfo` (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `username` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '登录地点',
  `browser` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '提示消息',
  `login_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '访问时间',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=296 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统访问记录';

-- ----------------------------
-- Records of sys_logininfo
-- ----------------------------
BEGIN;
INSERT INTO `sys_logininfo` VALUES (2, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-29 11:11:51');
INSERT INTO `sys_logininfo` VALUES (3, 'lyq', '127.0.0.1', ' 本机地址', 'Safari', 'Mac OS X', '1', '验证码错误', '2020-08-29 11:27:25');
INSERT INTO `sys_logininfo` VALUES (4, 'lyq', '127.0.0.1', ' 本机地址', 'Safari', 'Mac OS X', '1', '验证码错误', '2020-08-29 11:27:25');
INSERT INTO `sys_logininfo` VALUES (5, 'lyq', '127.0.0.1', ' 本机地址', 'Safari', 'Mac OS X', '1', '验证码错误', '2020-08-29 11:27:25');
INSERT INTO `sys_logininfo` VALUES (6, 'lyq', '127.0.0.1', ' 本机地址', 'Safari', 'Mac OS X', '1', '验证码错误', '2020-08-29 11:27:28');
INSERT INTO `sys_logininfo` VALUES (7, 'lyq', '127.0.0.1', ' 本机地址', 'Safari', 'Mac OS X', '1', '验证码错误', '2020-08-29 11:27:29');
INSERT INTO `sys_logininfo` VALUES (8, 'lyq', '127.0.0.1', ' 本机地址', 'Safari', 'Mac OS X', '1', '验证码错误', '2020-08-29 11:27:29');
INSERT INTO `sys_logininfo` VALUES (9, 'lyq', '127.0.0.1', ' 本机地址', 'Safari', 'Mac OS X', '1', '验证码错误', '2020-08-29 11:27:29');
INSERT INTO `sys_logininfo` VALUES (10, 'lyq', '127.0.0.1', ' 本机地址', 'Safari', 'Mac OS X', '1', '验证码错误', '2020-08-29 11:27:32');
INSERT INTO `sys_logininfo` VALUES (11, 'lyq', '127.0.0.1', ' 本机地址', 'Safari', 'Mac OS X', '1', '验证码已失效', '2020-08-29 11:27:32');
INSERT INTO `sys_logininfo` VALUES (12, 'lyq', '127.0.0.1', ' 本机地址', 'Safari', 'Mac OS X', '1', '验证码错误', '2020-08-29 11:27:32');
INSERT INTO `sys_logininfo` VALUES (13, 'lyq', '127.0.0.1', ' 本机地址', 'Safari', 'Mac OS X', '1', '验证码已失效', '2020-08-29 11:27:32');
INSERT INTO `sys_logininfo` VALUES (14, 'lyq', '127.0.0.1', ' 本机地址', 'Safari', 'Mac OS X', '1', '用户不存在/密码错误', '2020-08-29 11:27:36');
INSERT INTO `sys_logininfo` VALUES (15, 'lyq', '127.0.0.1', ' 本机地址', 'Safari', 'Mac OS X', '1', '用户不存在/密码错误', '2020-08-29 11:27:47');
INSERT INTO `sys_logininfo` VALUES (16, 'lyq', '127.0.0.1', ' 本机地址', 'Safari', 'Mac OS X', '0', '登录成功', '2020-08-29 11:28:01');
INSERT INTO `sys_logininfo` VALUES (17, 'demo', '127.0.0.1', ' 本机地址', 'Firefox 7', 'Mac OS X', '1', '对不起，您的账号：demo 已停用', '2020-08-29 11:31:29');
INSERT INTO `sys_logininfo` VALUES (18, 'demo', '127.0.0.1', ' 本机地址', 'Firefox 7', 'Mac OS X', '1', '用户不存在/密码错误', '2020-08-29 11:31:34');
INSERT INTO `sys_logininfo` VALUES (19, 'demo', '127.0.0.1', ' 本机地址', 'Firefox 7', 'Mac OS X', '1', '用户不存在/密码错误', '2020-08-29 11:31:42');
INSERT INTO `sys_logininfo` VALUES (20, 'demo', '127.0.0.1', ' 本机地址', 'Firefox 7', 'Mac OS X', '0', '登录成功', '2020-08-29 11:31:57');
INSERT INTO `sys_logininfo` VALUES (21, 'lyq', '127.0.0.1', ' 本机地址', 'Safari', 'Mac OS X', '0', '退出成功', '2020-08-29 11:38:50');
INSERT INTO `sys_logininfo` VALUES (22, 'lyq', '127.0.0.1', ' 本机地址', 'Safari', 'Mac OS X', '0', '登录成功', '2020-08-29 11:49:31');
INSERT INTO `sys_logininfo` VALUES (23, 'lyq', '127.0.0.1', ' 本机地址', 'Safari', 'Mac OS X', '0', '退出成功', '2020-08-29 11:53:50');
INSERT INTO `sys_logininfo` VALUES (24, 'lyq', '127.0.0.1', ' 本机地址', 'Safari', 'Mac OS X', '0', '登录成功', '2020-08-29 11:53:55');
INSERT INTO `sys_logininfo` VALUES (25, 'lyq', '127.0.0.1', ' 本机地址', 'Safari', 'Mac OS X', '0', '登录成功', '2020-08-29 11:55:28');
INSERT INTO `sys_logininfo` VALUES (26, 'lyq', '127.0.0.1', ' 本机地址', 'Safari', 'Mac OS X', '0', '登录成功', '2020-08-29 11:58:31');
INSERT INTO `sys_logininfo` VALUES (27, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-29 14:48:10');
INSERT INTO `sys_logininfo` VALUES (28, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2020-08-29 14:51:16');
INSERT INTO `sys_logininfo` VALUES (29, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-29 14:51:28');
INSERT INTO `sys_logininfo` VALUES (30, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-29 15:30:27');
INSERT INTO `sys_logininfo` VALUES (31, 'admin', '0:0:0:0:0:0:0:1', '江西省宜春市 电信', 'Unknown', 'Unknown', '0', '登录成功', '2020-08-29 15:31:26');
INSERT INTO `sys_logininfo` VALUES (32, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-29 16:16:32');
INSERT INTO `sys_logininfo` VALUES (33, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-29 16:16:32');
INSERT INTO `sys_logininfo` VALUES (34, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-29 16:32:30');
INSERT INTO `sys_logininfo` VALUES (35, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-29 17:31:06');
INSERT INTO `sys_logininfo` VALUES (36, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-29 18:24:18');
INSERT INTO `sys_logininfo` VALUES (37, 'admin', '0:0:0:0:0:0:0:1', '江西省宜春市 电信', 'Unknown', 'Unknown', '0', '登录成功', '2020-08-29 20:21:33');
INSERT INTO `sys_logininfo` VALUES (38, 'lyq', '0:0:0:0:0:0:0:1', '江西省宜春市 电信', 'Unknown', 'Unknown', '1', '验证码已失效', '2020-08-29 20:23:40');
INSERT INTO `sys_logininfo` VALUES (39, 'lyq', '0:0:0:0:0:0:0:1', '江西省宜春市 电信', 'Unknown', 'Unknown', '0', '登录成功', '2020-08-29 20:23:54');
INSERT INTO `sys_logininfo` VALUES (40, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-29 21:25:44');
INSERT INTO `sys_logininfo` VALUES (41, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2020-08-29 21:58:43');
INSERT INTO `sys_logininfo` VALUES (42, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-29 21:58:58');
INSERT INTO `sys_logininfo` VALUES (43, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-29 22:09:49');
INSERT INTO `sys_logininfo` VALUES (44, 'admin', '172.16.18.58', ' 局域网', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-08-29 22:11:33');
INSERT INTO `sys_logininfo` VALUES (45, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-30 11:14:36');
INSERT INTO `sys_logininfo` VALUES (46, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2020-08-30 11:55:12');
INSERT INTO `sys_logininfo` VALUES (47, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-30 11:55:15');
INSERT INTO `sys_logininfo` VALUES (48, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2020-08-30 11:58:28');
INSERT INTO `sys_logininfo` VALUES (49, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-30 11:58:41');
INSERT INTO `sys_logininfo` VALUES (50, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-30 12:33:24');
INSERT INTO `sys_logininfo` VALUES (51, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-30 18:23:16');
INSERT INTO `sys_logininfo` VALUES (52, 'lyq', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '1', '用户不存在/密码错误', '2020-08-30 18:24:23');
INSERT INTO `sys_logininfo` VALUES (53, 'lyq', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-30 18:24:47');
INSERT INTO `sys_logininfo` VALUES (54, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-30 19:25:09');
INSERT INTO `sys_logininfo` VALUES (55, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-30 21:58:31');
INSERT INTO `sys_logininfo` VALUES (56, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-30 22:00:07');
INSERT INTO `sys_logininfo` VALUES (57, 'admin', '172.16.18.58', ' 局域网', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-08-30 22:01:09');
INSERT INTO `sys_logininfo` VALUES (58, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-31 08:14:19');
INSERT INTO `sys_logininfo` VALUES (59, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-31 08:31:18');
INSERT INTO `sys_logininfo` VALUES (60, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-31 09:07:04');
INSERT INTO `sys_logininfo` VALUES (61, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-31 11:01:26');
INSERT INTO `sys_logininfo` VALUES (62, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-31 21:42:56');
INSERT INTO `sys_logininfo` VALUES (63, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2020-08-31 21:45:03');
INSERT INTO `sys_logininfo` VALUES (64, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-31 21:54:38');
INSERT INTO `sys_logininfo` VALUES (65, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-31 22:00:07');
INSERT INTO `sys_logininfo` VALUES (66, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-08-31 22:15:03');
INSERT INTO `sys_logininfo` VALUES (67, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-01 08:20:38');
INSERT INTO `sys_logininfo` VALUES (68, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-01 08:40:25');
INSERT INTO `sys_logininfo` VALUES (69, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2020-09-01 09:27:28');
INSERT INTO `sys_logininfo` VALUES (70, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-01 09:27:29');
INSERT INTO `sys_logininfo` VALUES (71, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2020-09-01 15:11:02');
INSERT INTO `sys_logininfo` VALUES (72, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-01 15:11:16');
INSERT INTO `sys_logininfo` VALUES (73, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-01 18:30:10');
INSERT INTO `sys_logininfo` VALUES (74, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-01 19:57:38');
INSERT INTO `sys_logininfo` VALUES (75, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-02 13:21:54');
INSERT INTO `sys_logininfo` VALUES (76, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-02 15:10:52');
INSERT INTO `sys_logininfo` VALUES (77, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-02 16:47:44');
INSERT INTO `sys_logininfo` VALUES (78, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '1', '验证码已失效', '2020-09-02 21:42:42');
INSERT INTO `sys_logininfo` VALUES (79, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-02 21:42:46');
INSERT INTO `sys_logininfo` VALUES (80, 'lyq', '0:0:0:0:0:0:0:1', '江西省宜春市 电信', 'Unknown', 'Unknown', '1', '验证码已失效', '2020-09-02 21:43:47');
INSERT INTO `sys_logininfo` VALUES (81, 'admin', '0:0:0:0:0:0:0:1', '江西省宜春市 电信', 'Unknown', 'Unknown', '0', '登录成功', '2020-09-02 21:45:04');
INSERT INTO `sys_logininfo` VALUES (82, 'admin', '0:0:0:0:0:0:0:1', '江西省宜春市 电信', 'Unknown', 'Unknown', '0', '登录成功', '2020-09-03 08:23:58');
INSERT INTO `sys_logininfo` VALUES (83, 'admin', '0:0:0:0:0:0:0:1', '江西省宜春市 电信', 'Unknown', 'Unknown', '0', '登录成功', '2020-09-03 09:39:53');
INSERT INTO `sys_logininfo` VALUES (84, 'admin', '0:0:0:0:0:0:0:1', '江西省宜春市 电信', 'Unknown', 'Unknown', '0', '登录成功', '2020-09-03 14:13:50');
INSERT INTO `sys_logininfo` VALUES (85, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-03 14:37:25');
INSERT INTO `sys_logininfo` VALUES (86, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-03 15:31:13');
INSERT INTO `sys_logininfo` VALUES (87, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-03 15:36:24');
INSERT INTO `sys_logininfo` VALUES (88, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-03 15:36:29');
INSERT INTO `sys_logininfo` VALUES (89, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-03 15:36:53');
INSERT INTO `sys_logininfo` VALUES (90, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-03 16:25:36');
INSERT INTO `sys_logininfo` VALUES (91, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-03 16:43:37');
INSERT INTO `sys_logininfo` VALUES (92, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-03 16:56:05');
INSERT INTO `sys_logininfo` VALUES (93, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-03 17:16:08');
INSERT INTO `sys_logininfo` VALUES (94, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-03 18:22:17');
INSERT INTO `sys_logininfo` VALUES (95, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-03 19:10:14');
INSERT INTO `sys_logininfo` VALUES (96, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-03 19:16:09');
INSERT INTO `sys_logininfo` VALUES (97, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 02:39:41');
INSERT INTO `sys_logininfo` VALUES (98, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 02:43:51');
INSERT INTO `sys_logininfo` VALUES (99, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2020-09-04 02:43:57');
INSERT INTO `sys_logininfo` VALUES (100, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 02:44:00');
INSERT INTO `sys_logininfo` VALUES (101, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 02:44:18');
INSERT INTO `sys_logininfo` VALUES (102, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2020-09-04 02:44:23');
INSERT INTO `sys_logininfo` VALUES (103, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 02:44:35');
INSERT INTO `sys_logininfo` VALUES (104, 'admin', '0:0:0:0:0:0:0:1', '江西省宜春市 电信', 'Unknown', 'Unknown', '0', '登录成功', '2020-09-04 02:46:49');
INSERT INTO `sys_logininfo` VALUES (105, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 02:56:37');
INSERT INTO `sys_logininfo` VALUES (106, 'admin', '127.0.0.1', ' 本机地址', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 03:05:34');
INSERT INTO `sys_logininfo` VALUES (107, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 03:10:24');
INSERT INTO `sys_logininfo` VALUES (108, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2020-09-04 03:16:27');
INSERT INTO `sys_logininfo` VALUES (109, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2020-09-04 03:16:52');
INSERT INTO `sys_logininfo` VALUES (110, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 03:16:56');
INSERT INTO `sys_logininfo` VALUES (111, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2020-09-04 03:19:31');
INSERT INTO `sys_logininfo` VALUES (112, 'demo', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 03:19:37');
INSERT INTO `sys_logininfo` VALUES (113, 'demo', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2020-09-04 03:22:32');
INSERT INTO `sys_logininfo` VALUES (114, 'demo', '219.140.227.227', '湖北省武汉市 电信ADSL', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-09-04 03:22:38');
INSERT INTO `sys_logininfo` VALUES (115, 'demo', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 03:22:49');
INSERT INTO `sys_logininfo` VALUES (116, 'demo', '112.30.57.134', '安徽省合肥市 移通', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-09-04 03:23:38');
INSERT INTO `sys_logininfo` VALUES (117, 'admin', '223.104.20.103', '湖北省武汉市 移通', 'Chrome Mobile', 'Android 1.x', '1', '验证码错误', '2020-09-04 03:24:19');
INSERT INTO `sys_logininfo` VALUES (118, 'admin', '223.104.20.103', '湖北省武汉市 移通', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 03:24:24');
INSERT INTO `sys_logininfo` VALUES (119, 'demo', '183.48.32.12', '广东省广州市 电信', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-09-04 03:26:23');
INSERT INTO `sys_logininfo` VALUES (120, 'demo', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2020-09-04 03:26:34');
INSERT INTO `sys_logininfo` VALUES (121, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 03:26:40');
INSERT INTO `sys_logininfo` VALUES (122, 'demo', '117.136.55.116', '天津市 移通', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 03:29:00');
INSERT INTO `sys_logininfo` VALUES (123, 'admin', '117.136.50.199', '陕西省西安市 移通', 'Chrome Mobile', 'Android 1.x', '1', '用户不存在/密码错误', '2020-09-04 03:29:38');
INSERT INTO `sys_logininfo` VALUES (124, 'admin', '117.136.50.199', '陕西省西安市 移通', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 03:29:52');
INSERT INTO `sys_logininfo` VALUES (125, 'demo', '112.30.57.134', '安徽省合肥市 移通', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-09-04 03:30:19');
INSERT INTO `sys_logininfo` VALUES (126, 'demo', '220.202.152.65', '湖南省长沙市 联通', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 03:30:22');
INSERT INTO `sys_logininfo` VALUES (127, 'demo', '112.30.57.134', '安徽省合肥市 移通', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-09-04 03:30:42');
INSERT INTO `sys_logininfo` VALUES (128, 'demo', '112.30.57.134', '安徽省合肥市 移通', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-09-04 03:31:10');
INSERT INTO `sys_logininfo` VALUES (129, 'admin', '182.97.151.136', '江西省南昌市 电信', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 03:31:19');
INSERT INTO `sys_logininfo` VALUES (130, 'admin', '182.97.151.136', '江西省南昌市 电信', 'Chrome Mobile', 'Android 1.x', '0', '退出成功', '2020-09-04 03:32:16');
INSERT INTO `sys_logininfo` VALUES (131, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2020-09-04 03:36:47');
INSERT INTO `sys_logininfo` VALUES (132, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 03:36:53');
INSERT INTO `sys_logininfo` VALUES (133, 'admin', '117.136.49.36', '江西省南昌市 移通', 'Chrome Mobile', 'Android 1.x', '1', '验证码错误', '2020-09-04 03:39:09');
INSERT INTO `sys_logininfo` VALUES (134, 'admin', '117.136.49.36', '江西省南昌市 移通', 'Chrome Mobile', 'Android 1.x', '1', '验证码错误', '2020-09-04 03:39:13');
INSERT INTO `sys_logininfo` VALUES (135, 'admin', '117.136.49.36', '江西省南昌市 移通', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 03:39:16');
INSERT INTO `sys_logininfo` VALUES (136, 'admin', '59.49.30.24', '山西省太原市 电信', 'Chrome Mobile', 'Android 1.x', '1', '验证码错误', '2020-09-04 03:41:41');
INSERT INTO `sys_logininfo` VALUES (137, 'admin', '59.49.30.24', '山西省太原市 电信', 'Chrome Mobile', 'Android 1.x', '1', '验证码错误', '2020-09-04 03:41:47');
INSERT INTO `sys_logininfo` VALUES (138, 'admin', '59.49.30.24', '山西省太原市 电信', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 03:41:59');
INSERT INTO `sys_logininfo` VALUES (139, 'admin', '58.213.23.61', '江苏省南京市 电信ADSL', 'Chrome', 'Windows 10', '1', '验证码已失效', '2020-09-04 03:44:36');
INSERT INTO `sys_logininfo` VALUES (140, 'admin', '58.213.23.61', '江苏省南京市 电信ADSL', 'Chrome', 'Windows 10', '0', '登录成功', '2020-09-04 03:44:40');
INSERT INTO `sys_logininfo` VALUES (141, 'admin', '120.239.168.31', '广东省中山市 移通', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 03:49:58');
INSERT INTO `sys_logininfo` VALUES (142, 'admin', '218.87.18.19', '江西省南昌市 电信', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 03:51:20');
INSERT INTO `sys_logininfo` VALUES (143, 'admin', '120.239.168.31', '广东省中山市 移通', 'Chrome 8', 'Windows 7', '0', '登录成功', '2020-09-04 03:51:44');
INSERT INTO `sys_logininfo` VALUES (144, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 03:54:24');
INSERT INTO `sys_logininfo` VALUES (145, 'admin', '36.108.3.38', '新疆乌鲁木齐市 电信', 'Apple WebKit', 'Mac OS X (iPhone)', '0', '登录成功', '2020-09-04 03:55:55');
INSERT INTO `sys_logininfo` VALUES (146, 'admin', '117.136.109.70', '江西省南昌市 移通', 'Chrome Mobile', 'Android 7.x', '0', '登录成功', '2020-09-04 03:57:35');
INSERT INTO `sys_logininfo` VALUES (147, 'admin', '117.136.109.70', '江西省南昌市 移通', 'Firefox Mobile', 'Android 7.x', '0', '登录成功', '2020-09-04 03:59:03');
INSERT INTO `sys_logininfo` VALUES (148, 'admin', '113.246.48.175', '湖南省长沙市 电信', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 03:59:15');
INSERT INTO `sys_logininfo` VALUES (149, 'admin', '112.224.20.191', '山东省济南市 联通', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 04:00:05');
INSERT INTO `sys_logininfo` VALUES (150, 'admin', '124.17.12.33', '北京市 中国科学院', 'Chrome 8', 'Windows 7', '0', '登录成功', '2020-09-04 04:00:39');
INSERT INTO `sys_logininfo` VALUES (151, 'admin', '182.104.120.55', '江西省南昌市 电信', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 04:01:25');
INSERT INTO `sys_logininfo` VALUES (152, 'admin', '117.136.87.53', '陕西省西安市 移通', 'Chrome Mobile', 'Android Mobile', '0', '登录成功', '2020-09-04 04:05:33');
INSERT INTO `sys_logininfo` VALUES (153, 'admin', '122.96.40.60', '江苏省南京市 联通', 'Chrome Mobile', 'Android 8.x', '0', '登录成功', '2020-09-04 04:06:05');
INSERT INTO `sys_logininfo` VALUES (154, 'admin', '125.116.127.245', '浙江省宁波市宁海县 电信', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 04:07:02');
INSERT INTO `sys_logininfo` VALUES (155, 'admin', '39.144.34.26', ' 中国移动', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 04:07:46');
INSERT INTO `sys_logininfo` VALUES (156, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 04:08:02');
INSERT INTO `sys_logininfo` VALUES (157, 'admin', '113.116.132.223', '广东省深圳市 电信', 'Chrome Mobile', 'Android Mobile', '0', '登录成功', '2020-09-04 04:08:58');
INSERT INTO `sys_logininfo` VALUES (158, 'admin', '117.136.87.190', '陕西省西安市 移通', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 04:09:07');
INSERT INTO `sys_logininfo` VALUES (159, 'admin', '49.77.183.245', '江苏省南京市 电信', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 04:09:58');
INSERT INTO `sys_logininfo` VALUES (160, 'admin', '111.26.36.98', '吉林省长春市 移通', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-09-04 04:10:21');
INSERT INTO `sys_logininfo` VALUES (161, 'admin', '113.116.132.223', '广东省深圳市 电信', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-09-04 04:10:47');
INSERT INTO `sys_logininfo` VALUES (162, 'admin', '112.28.229.177', '安徽省合肥市 移动', 'Chrome Mobile', 'Android 8.x', '0', '登录成功', '2020-09-04 04:13:57');
INSERT INTO `sys_logininfo` VALUES (163, 'admin', '112.80.33.146', '江苏省南京市 联通', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 04:13:58');
INSERT INTO `sys_logininfo` VALUES (164, 'admin', '218.87.18.19', '江西省南昌市 电信', 'Apple WebKit', 'Mac OS X (iPhone)', '0', '登录成功', '2020-09-04 04:15:25');
INSERT INTO `sys_logininfo` VALUES (165, 'admin', '223.104.3.166', '北京市 移通', 'Apple WebKit', 'Mac OS X (iPhone)', '1', '验证码已失效', '2020-09-04 04:16:26');
INSERT INTO `sys_logininfo` VALUES (166, 'admin', '223.104.3.166', '北京市 移通', 'Apple WebKit', 'Mac OS X (iPhone)', '0', '登录成功', '2020-09-04 04:16:33');
INSERT INTO `sys_logininfo` VALUES (167, 'admin', '49.94.20.22', '江苏省南京市 电信', 'Chrome Mobile', 'Android Mobile', '0', '登录成功', '2020-09-04 04:17:13');
INSERT INTO `sys_logininfo` VALUES (168, 'admin', '171.95.8.225', '四川省广安市 电信', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 04:18:26');
INSERT INTO `sys_logininfo` VALUES (169, 'admin', '112.80.33.146', '江苏省南京市 联通', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-09-04 04:19:46');
INSERT INTO `sys_logininfo` VALUES (170, 'admin', '171.95.10.23', '四川省广安市 电信', 'Chrome 55', 'Windows 7', '0', '登录成功', '2020-09-04 04:20:51');
INSERT INTO `sys_logininfo` VALUES (171, 'admin', '124.160.212.231', '浙江省嘉兴市 联通', 'Chrome Mobile', 'Android Mobile', '0', '登录成功', '2020-09-04 04:21:47');
INSERT INTO `sys_logininfo` VALUES (172, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 04:22:05');
INSERT INTO `sys_logininfo` VALUES (173, 'admin', '36.57.161.17', '安徽省合肥市 电信', 'Firefox 8', 'Windows 10', '0', '登录成功', '2020-09-04 04:23:39');
INSERT INTO `sys_logininfo` VALUES (174, 'admin', '113.242.198.86', '湖南省益阳市 电信', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 04:26:23');
INSERT INTO `sys_logininfo` VALUES (175, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2020-09-04 04:32:49');
INSERT INTO `sys_logininfo` VALUES (176, 'admin', '218.87.18.19', '江西省南昌市 电信', 'Chrome Mobile', 'Android Mobile', '0', '登录成功', '2020-09-04 04:32:50');
INSERT INTO `sys_logininfo` VALUES (177, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '1', '验证码已失效', '2020-09-04 04:35:12');
INSERT INTO `sys_logininfo` VALUES (178, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 04:35:16');
INSERT INTO `sys_logininfo` VALUES (179, 'admin', '106.47.224.27', '天津市 电信', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 04:42:46');
INSERT INTO `sys_logininfo` VALUES (180, 'admin', '112.12.53.93', '浙江省金华市 移动', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 04:43:46');
INSERT INTO `sys_logininfo` VALUES (181, 'admin', '123.161.116.206', '河南省南阳市 电信ADSL', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 04:46:13');
INSERT INTO `sys_logininfo` VALUES (182, 'admin', '139.207.164.215', '四川省成都市 电信', 'Chrome Mobile', 'Android Mobile', '0', '登录成功', '2020-09-04 04:48:31');
INSERT INTO `sys_logininfo` VALUES (183, 'admin', '125.69.171.168', '四川省成都市 电信', 'Mobile Safari', 'iOS 10 (iPhone)', '0', '登录成功', '2020-09-04 04:49:05');
INSERT INTO `sys_logininfo` VALUES (184, 'admin', '218.87.18.19', '江西省南昌市 电信', 'Chrome Mobile', 'Android 1.x', '1', '验证码错误', '2020-09-04 04:53:32');
INSERT INTO `sys_logininfo` VALUES (185, 'admin', '218.87.18.19', '江西省南昌市 电信', 'Chrome Mobile', 'Android 1.x', '1', '验证码错误', '2020-09-04 04:53:41');
INSERT INTO `sys_logininfo` VALUES (186, 'admin', '218.87.18.19', '江西省南昌市 电信', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 04:53:45');
INSERT INTO `sys_logininfo` VALUES (187, 'admin', '117.183.73.27', '广西南宁市 移通', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 04:54:58');
INSERT INTO `sys_logininfo` VALUES (188, 'admin', '223.104.173.129', '江西省上饶市 移动', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 04:55:06');
INSERT INTO `sys_logininfo` VALUES (189, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2020-09-04 04:55:35');
INSERT INTO `sys_logininfo` VALUES (190, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 04:55:38');
INSERT INTO `sys_logininfo` VALUES (191, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2020-09-04 04:56:12');
INSERT INTO `sys_logininfo` VALUES (192, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 04:57:15');
INSERT INTO `sys_logininfo` VALUES (193, 'admin', '114.218.81.191', '江苏省苏州市 电信', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 04:58:57');
INSERT INTO `sys_logininfo` VALUES (194, 'admin', '183.17.231.143', '广东省深圳市 电信', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-09-04 04:59:24');
INSERT INTO `sys_logininfo` VALUES (195, 'admin', '223.72.91.158', '北京市 移动', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 05:01:03');
INSERT INTO `sys_logininfo` VALUES (196, 'admin', '61.177.71.2', '江苏省常州市 电信', 'Chrome', 'Windows 10', '1', '验证码已失效', '2020-09-04 05:03:42');
INSERT INTO `sys_logininfo` VALUES (197, 'admin', '61.177.71.2', '江苏省常州市 电信', 'Chrome', 'Windows 10', '0', '登录成功', '2020-09-04 05:03:48');
INSERT INTO `sys_logininfo` VALUES (198, 'admin', '117.136.40.185', '广东省深圳市 移动', 'Chrome Mobile', 'Android 6.x', '1', '验证码已失效', '2020-09-04 05:04:54');
INSERT INTO `sys_logininfo` VALUES (199, 'admin', '106.127.255.193', '广西南宁市 电信', 'Chrome Mobile', 'Android 8.x', '1', '验证码错误', '2020-09-04 05:05:04');
INSERT INTO `sys_logininfo` VALUES (200, 'admin', '117.136.40.185', '广东省深圳市 移动', 'Chrome Mobile', 'Android 6.x', '0', '登录成功', '2020-09-04 05:05:04');
INSERT INTO `sys_logininfo` VALUES (201, 'admin', '106.127.255.193', '广西南宁市 电信', 'Chrome Mobile', 'Android 8.x', '1', '验证码错误', '2020-09-04 05:05:30');
INSERT INTO `sys_logininfo` VALUES (202, 'admin', '106.127.255.193', '广西南宁市 电信', 'Chrome Mobile', 'Android 8.x', '0', '登录成功', '2020-09-04 05:05:40');
INSERT INTO `sys_logininfo` VALUES (203, 'admin', '120.42.169.107', '福建省泉州市 电信', 'Safari', 'Mac OS X', '0', '登录成功', '2020-09-04 05:08:49');
INSERT INTO `sys_logininfo` VALUES (204, 'admin', '120.235.17.234', '广东省广州市 移动', 'Chrome', 'Windows 10', '0', '登录成功', '2020-09-04 05:12:26');
INSERT INTO `sys_logininfo` VALUES (205, 'admin', '180.142.199.109', '广西南宁市 电信', 'Chrome Mobile', 'Android Mobile', '0', '登录成功', '2020-09-04 05:12:29');
INSERT INTO `sys_logininfo` VALUES (206, 'admin', '221.130.134.203', '安徽省合肥市 移通', 'Chrome 8', 'Windows 7', '0', '登录成功', '2020-09-04 05:13:35');
INSERT INTO `sys_logininfo` VALUES (207, 'admin', '110.53.0.7', '湖南省娄底市 联通', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-09-04 05:14:39');
INSERT INTO `sys_logininfo` VALUES (208, 'admin', '117.136.110.151', '江西省九江市 移动', 'Chrome Mobile', 'Android 1.x', '1', '验证码错误', '2020-09-04 05:16:04');
INSERT INTO `sys_logininfo` VALUES (209, 'admin', '117.136.110.151', '江西省九江市 移动', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 05:16:08');
INSERT INTO `sys_logininfo` VALUES (210, 'admin', '117.136.110.110', '江西省九江市 移动', 'Chrome Mobile', 'Android 8.x', '0', '登录成功', '2020-09-04 05:16:32');
INSERT INTO `sys_logininfo` VALUES (211, 'admin', '117.29.50.204', '福建省福州市 电信', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-09-04 05:16:37');
INSERT INTO `sys_logininfo` VALUES (212, 'admin', '115.54.27.243', '河南省新乡市 联通', 'Chrome 8', 'Windows 10', '1', '验证码已失效', '2020-09-04 05:16:37');
INSERT INTO `sys_logininfo` VALUES (213, 'admin', '115.54.27.243', '河南省新乡市 联通', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-09-04 05:16:40');
INSERT INTO `sys_logininfo` VALUES (214, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2020-09-04 05:16:59');
INSERT INTO `sys_logininfo` VALUES (215, 'admin', '117.136.110.151', '江西省九江市 移动', 'Chrome Mobile', 'Android 1.x', '0', '退出成功', '2020-09-04 05:17:47');
INSERT INTO `sys_logininfo` VALUES (216, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 05:18:06');
INSERT INTO `sys_logininfo` VALUES (217, 'admin', '112.224.19.113', '山东省济南市 联通', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 05:20:15');
INSERT INTO `sys_logininfo` VALUES (218, 'admin', '117.157.179.229', '甘肃省天水市 移通', 'Chrome Mobile', 'Android 1.x', '1', '验证码已失效', '2020-09-04 05:20:27');
INSERT INTO `sys_logininfo` VALUES (219, 'admin', '117.157.179.229', '甘肃省天水市 移通', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 05:20:34');
INSERT INTO `sys_logininfo` VALUES (220, 'admin', '223.246.174.181', '安徽省宣城市广德县 电信', 'Chrome Mobile', 'Android 8.x', '1', '验证码错误', '2020-09-04 05:21:06');
INSERT INTO `sys_logininfo` VALUES (221, 'admin', '223.246.174.181', '安徽省宣城市广德县 电信', 'Chrome Mobile', 'Android 8.x', '1', '验证码错误', '2020-09-04 05:21:13');
INSERT INTO `sys_logininfo` VALUES (222, 'admin', '223.246.174.181', '安徽省宣城市广德县 电信', 'Chrome Mobile', 'Android 8.x', '0', '登录成功', '2020-09-04 05:21:17');
INSERT INTO `sys_logininfo` VALUES (223, 'admin', '117.136.32.192', '广东省广州市 移通', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 05:21:38');
INSERT INTO `sys_logininfo` VALUES (224, 'admin', '120.235.17.234', '广东省广州市 移动', 'Chrome', 'Windows 10', '0', '退出成功', '2020-09-04 05:22:06');
INSERT INTO `sys_logininfo` VALUES (225, 'admin', '124.64.19.173', '北京市丰台区 联通ADSL', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 05:30:21');
INSERT INTO `sys_logininfo` VALUES (226, 'admin', '223.209.230.169', '湖北省武汉市 长城宽带', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-09-04 05:34:10');
INSERT INTO `sys_logininfo` VALUES (227, 'admin', '219.217.149.10', '辽宁省大连市 教育网', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-09-04 05:35:12');
INSERT INTO `sys_logininfo` VALUES (228, 'admin', '114.87.180.116', '上海市 电信', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 05:41:12');
INSERT INTO `sys_logininfo` VALUES (229, 'admin', '112.96.161.118', '广东省广州市 联通', 'Chrome Mobile', 'Android 7.x', '0', '登录成功', '2020-09-04 05:42:25');
INSERT INTO `sys_logininfo` VALUES (230, 'admin', '113.57.182.44', '湖北省武汉市 联通', 'Chrome Mobile', 'Android Mobile', '0', '登录成功', '2020-09-04 05:51:31');
INSERT INTO `sys_logininfo` VALUES (231, 'admin', '101.206.171.197', '四川省成都市 联通', 'Chrome Mobile', 'Android 7.x', '1', '验证码错误', '2020-09-04 05:53:58');
INSERT INTO `sys_logininfo` VALUES (232, 'admin', '101.206.171.197', '四川省成都市 联通', 'Chrome Mobile', 'Android 7.x', '0', '登录成功', '2020-09-04 05:54:04');
INSERT INTO `sys_logininfo` VALUES (233, 'admin', '183.218.58.132', '江西省赣州市 移通', 'Chrome Mobile', 'Android Mobile', '0', '登录成功', '2020-09-04 05:54:21');
INSERT INTO `sys_logininfo` VALUES (234, 'admin', '113.57.183.28', '湖北省武汉市 联通', 'Chrome Mobile', 'Android 1.x', '1', '验证码错误', '2020-09-04 06:09:39');
INSERT INTO `sys_logininfo` VALUES (235, 'admin', '113.57.183.28', '湖北省武汉市 联通', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 06:09:44');
INSERT INTO `sys_logininfo` VALUES (236, 'admin', '120.228.2.61', '湖南省长沙市 移通', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-09-04 06:09:48');
INSERT INTO `sys_logininfo` VALUES (237, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2020-09-04 06:10:56');
INSERT INTO `sys_logininfo` VALUES (238, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 06:11:08');
INSERT INTO `sys_logininfo` VALUES (239, 'admin', '120.228.2.61', '湖南省长沙市 移通', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-09-04 06:11:13');
INSERT INTO `sys_logininfo` VALUES (240, 'admin', '59.173.165.141', '湖北省武汉市 电信', 'Internet Explorer 11', 'Windows 10', '0', '登录成功', '2020-09-04 06:11:19');
INSERT INTO `sys_logininfo` VALUES (241, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 06:14:40');
INSERT INTO `sys_logininfo` VALUES (242, 'admin', '175.9.232.178', '湖南省长沙市 电信', 'Internet Explorer 11', 'Windows 10', '0', '登录成功', '2020-09-04 06:15:18');
INSERT INTO `sys_logininfo` VALUES (243, 'admin', '180.169.38.125', '上海市 电信', 'Firefox 7', 'Windows 10', '0', '登录成功', '2020-09-04 06:25:42');
INSERT INTO `sys_logininfo` VALUES (244, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 06:35:03');
INSERT INTO `sys_logininfo` VALUES (245, 'admin', '60.160.29.194', '云南省昭通市 电信', 'Chrome Mobile', 'Android Mobile', '0', '登录成功', '2020-09-04 06:37:00');
INSERT INTO `sys_logininfo` VALUES (246, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 06:45:27');
INSERT INTO `sys_logininfo` VALUES (247, 'admin', '120.230.116.171', '广东省广州市 移通', 'Chrome Mobile', 'Android Mobile', '0', '登录成功', '2020-09-04 06:47:31');
INSERT INTO `sys_logininfo` VALUES (248, 'admin', '120.230.116.171', '广东省广州市 移通', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-09-04 06:49:24');
INSERT INTO `sys_logininfo` VALUES (249, 'admin', '120.230.116.171', '广东省广州市 移通', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-09-04 06:55:29');
INSERT INTO `sys_logininfo` VALUES (250, 'admin', '218.87.18.19', '江西省南昌市 电信', 'Chrome', 'Windows 10', '1', '验证码已失效', '2020-09-04 07:05:22');
INSERT INTO `sys_logininfo` VALUES (251, 'admin', '218.87.18.19', '江西省南昌市 电信', 'Chrome', 'Windows 10', '0', '登录成功', '2020-09-04 07:05:27');
INSERT INTO `sys_logininfo` VALUES (252, 'admin', '218.87.18.19', '江西省南昌市 电信', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 07:18:35');
INSERT INTO `sys_logininfo` VALUES (253, 'demo', '42.92.154.193', '甘肃省兰州市 电信', 'Chrome', 'Windows 10', '0', '登录成功', '2020-09-04 07:20:27');
INSERT INTO `sys_logininfo` VALUES (254, 'demo', '42.92.154.193', '甘肃省兰州市 电信', 'Chrome', 'Windows 10', '0', '退出成功', '2020-09-04 07:23:07');
INSERT INTO `sys_logininfo` VALUES (255, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 07:24:15');
INSERT INTO `sys_logininfo` VALUES (256, 'admin', '183.162.63.106', '安徽省合肥市 电信', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 07:27:57');
INSERT INTO `sys_logininfo` VALUES (257, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2020-09-04 07:54:05');
INSERT INTO `sys_logininfo` VALUES (258, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 07:54:09');
INSERT INTO `sys_logininfo` VALUES (259, 'admin', '218.87.18.19', '江西省南昌市 电信', 'Chrome', 'Windows 10', '0', '登录成功', '2020-09-04 07:58:32');
INSERT INTO `sys_logininfo` VALUES (260, 'admin', '106.224.192.15', '江西省南昌市 电信', 'Chrome Mobile', 'Android 1.x', '1', '验证码错误', '2020-09-04 08:05:32');
INSERT INTO `sys_logininfo` VALUES (261, 'admin', '106.224.192.15', '江西省南昌市 电信', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 08:05:39');
INSERT INTO `sys_logininfo` VALUES (262, 'admin', '218.87.18.19', '江西省南昌市 电信', 'Chrome', 'Windows 10', '0', '退出成功', '2020-09-04 08:14:42');
INSERT INTO `sys_logininfo` VALUES (263, 'admin', '180.160.71.250', '上海市 电信', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 08:17:09');
INSERT INTO `sys_logininfo` VALUES (264, 'admin', '116.230.24.149', '上海市杨浦区 /虹口区电信', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-09-04 08:17:38');
INSERT INTO `sys_logininfo` VALUES (265, 'admin', '180.169.251.2', '上海市 电信', 'Firefox 7', 'Windows 10', '0', '登录成功', '2020-09-04 08:23:47');
INSERT INTO `sys_logininfo` VALUES (266, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 08:31:22');
INSERT INTO `sys_logininfo` VALUES (267, 'admin', '223.104.65.168', '广东省东莞市 移动', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 08:32:40');
INSERT INTO `sys_logininfo` VALUES (268, 'admin', '218.87.18.19', '江西省南昌市 电信', 'Chrome', 'Windows 10', '0', '登录成功', '2020-09-04 08:32:53');
INSERT INTO `sys_logininfo` VALUES (269, 'admin', '223.104.65.168', '广东省东莞市 移动', 'Chrome Mobile', 'Android 1.x', '0', '退出成功', '2020-09-04 08:33:02');
INSERT INTO `sys_logininfo` VALUES (270, 'admin', '180.169.38.125', '上海市 电信', 'Firefox 7', 'Windows 10', '0', '登录成功', '2020-09-04 08:34:15');
INSERT INTO `sys_logininfo` VALUES (271, 'admin', '172.18.251.160', ' 局域网', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 08:45:42');
INSERT INTO `sys_logininfo` VALUES (272, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 08:50:53');
INSERT INTO `sys_logininfo` VALUES (273, 'admin', '171.34.71.68', '江西省南昌市 联通', 'Internet Explorer 11', 'Windows 10', '0', '登录成功', '2020-09-04 09:02:01');
INSERT INTO `sys_logininfo` VALUES (274, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 09:25:42');
INSERT INTO `sys_logininfo` VALUES (275, 'admin', '175.9.232.178', '湖南省长沙市 电信', 'Internet Explorer 11', 'Windows 10', '0', '登录成功', '2020-09-04 09:26:54');
INSERT INTO `sys_logininfo` VALUES (276, 'admin', '114.242.248.201', '北京市通州区 联通', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-04 10:27:56');
INSERT INTO `sys_logininfo` VALUES (277, 'admin', '223.104.10.191', '江西省南昌市 移通', 'Chrome Mobile', 'Android Mobile', '0', '登录成功', '2020-09-04 10:43:25');
INSERT INTO `sys_logininfo` VALUES (278, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-04 11:13:17');
INSERT INTO `sys_logininfo` VALUES (279, 'admin', '117.75.19.246', '云南省昆明市 长城宽带', 'Chrome Mobile', 'Android 8.x', '0', '登录成功', '2020-09-04 12:10:24');
INSERT INTO `sys_logininfo` VALUES (280, 'admin', '117.75.19.246', '云南省昆明市 长城宽带', 'Chrome', 'Windows 10', '0', '登录成功', '2020-09-04 12:12:24');
INSERT INTO `sys_logininfo` VALUES (281, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-09-04 12:27:37');
INSERT INTO `sys_logininfo` VALUES (282, 'admin', '171.114.111.178', '湖北省宜昌市 电信', 'Chrome', 'Windows 10', '1', '验证码已失效', '2020-09-04 14:50:55');
INSERT INTO `sys_logininfo` VALUES (283, 'admin', '171.114.111.178', '湖北省宜昌市 电信', 'Chrome', 'Windows 10', '1', '验证码错误', '2020-09-04 14:50:56');
INSERT INTO `sys_logininfo` VALUES (284, 'admin', '171.114.111.178', '湖北省宜昌市 电信', 'Chrome', 'Windows 10', '0', '登录成功', '2020-09-04 14:51:01');
INSERT INTO `sys_logininfo` VALUES (285, 'admin', '223.104.131.250', '湖南省长沙市 移通', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-05 00:37:47');
INSERT INTO `sys_logininfo` VALUES (286, 'admin', '223.104.212.241', '上海市 移通', 'Chrome Mobile', 'Android Mobile', '0', '登录成功', '2020-09-05 00:42:24');
INSERT INTO `sys_logininfo` VALUES (287, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-05 00:45:47');
INSERT INTO `sys_logininfo` VALUES (288, 'admin', '112.224.19.103', '山东省济南市 联通', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-05 01:18:50');
INSERT INTO `sys_logininfo` VALUES (289, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-09-05 02:28:11');
INSERT INTO `sys_logininfo` VALUES (290, 'admin', '111.75.189.148', '江西省宜春市 电信', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-09-05 03:05:04');
INSERT INTO `sys_logininfo` VALUES (291, 'admin', '117.136.49.36', '江西省南昌市 移通', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-05 05:27:04');
INSERT INTO `sys_logininfo` VALUES (292, 'admin', '117.136.49.36', '江西省南昌市 移通', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2020-09-05 05:28:14');
INSERT INTO `sys_logininfo` VALUES (293, 'admin', '43.250.200.114', '湖南省长沙市 联通', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-09-05 08:28:36');
INSERT INTO `sys_logininfo` VALUES (294, 'admin', '43.250.200.114', '湖南省长沙市 联通', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-09-05 11:10:16');
INSERT INTO `sys_logininfo` VALUES (295, 'admin', '180.116.142.126', '江苏省常州市 电信', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2020-09-08 06:40:11');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '组件路径',
  `is_frame` int(1) DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '权限标识',
  `icon` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(511) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2098 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, 'system', NULL, 1, 'M', '0', '0', '', 'system', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '系统管理目录');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', 1, 'C', '0', '0', 'system:user:list', 'user', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', 1, 'C', '0', '0', 'system:role:list', 'peoples', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', 1, 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, 'dept', 'system/dept/index', 1, 'C', '0', '0', 'system:dept:list', 'tree', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '部门管理菜单');
INSERT INTO `sys_menu` VALUES (2004, '系统监控', 0, 2, 'monitor', '', 1, 'M', '0', '0', '', 'monitor', 'admin', '2020-08-27 14:06:15', 'admin', '2020-08-27 14:08:49', '');
INSERT INTO `sys_menu` VALUES (2005, '数据监控', 2004, 0, 'druid', 'monitor/druid/index', 1, 'C', '0', '0', 'monitor:druid:list', 'druid', 'admin', '2020-08-27 15:21:40', 'admin', '2020-08-27 15:22:59', '');
INSERT INTO `sys_menu` VALUES (2006, '日志管理', 1, 5, 'log', 'system/log/index', 1, 'M', '0', '0', '', 'log', 'admin', '2020-08-27 16:02:59', 'admin', '2020-08-28 09:40:26', '');
INSERT INTO `sys_menu` VALUES (2007, '操作日志', 2006, 1, 'operlog', 'monitor/operlog/index', 1, 'C', '0', '0', 'monitor:operlog:list', 'form', 'admin', '2020-08-27 16:14:17', 'admin', '2020-08-27 16:14:17', '');
INSERT INTO `sys_menu` VALUES (2008, '登录日志', 2006, 2, 'logininfo', 'monitor/logininfo/index', 1, 'C', '0', '0', 'monitor:logininfo:list', 'logininfor', 'admin', '2020-08-27 16:15:06', 'admin', '2020-08-30 11:57:19', '');
INSERT INTO `sys_menu` VALUES (2009, '系统工具', 0, 3, 'tool', '', 1, 'M', '0', '0', '', 'tool', 'admin', '2020-08-27 16:17:46', 'admin', '2020-08-27 16:17:46', '');
INSERT INTO `sys_menu` VALUES (2010, '表单构建', 2009, 1, 'build', 'tool/build/index', 1, 'C', '0', '0', 'tool:build:list', 'build', 'admin', '2020-08-27 16:18:29', 'admin', '2020-08-27 16:18:29', '');
INSERT INTO `sys_menu` VALUES (2011, '服务监控', 2004, 4, 'server', 'monitor/server/index', 1, 'C', '0', '0', 'monitor:server:list', 'server', 'admin', '2020-08-27 19:26:30', 'admin', '2020-08-27 19:26:30', '');
INSERT INTO `sys_menu` VALUES (2012, '在线用户', 2004, 1, 'online', 'monitor/online/index', 1, 'C', '0', '0', 'monitor:online:list', 'online', 'admin', '2020-08-27 20:24:29', 'admin', '2020-08-27 20:24:29', '');
INSERT INTO `sys_menu` VALUES (2013, '系统接口', 2009, 3, 'swagger', 'tool/swagger/index', 1, 'C', '0', '0', 'tool:swagger:list', 'swagger', 'admin', '2020-08-27 20:40:13', 'admin', '2020-08-27 20:40:13', '');
INSERT INTO `sys_menu` VALUES (2014, '用户查询', 100, 1, '', '', 1, 'F', '0', '0', 'system:user:query', '#', 'admin', '2020-08-27 20:40:56', 'admin', '2020-08-27 20:40:56', '');
INSERT INTO `sys_menu` VALUES (2015, '用户新增', 100, 2, '', '', 1, 'F', '0', '0', 'system:user:add', '#', 'admin', '2020-08-27 20:41:25', 'admin', '2020-08-27 20:41:33', '');
INSERT INTO `sys_menu` VALUES (2016, '用户修改', 100, 3, '', '', 1, 'F', '0', '0', 'system:user:edit', '#', 'admin', '2020-08-27 20:41:51', 'admin', '2020-08-27 20:41:51', '');
INSERT INTO `sys_menu` VALUES (2017, '用户删除', 100, 4, '', '', 1, 'F', '0', '0', 'system:user:remove', '#', 'admin', '2020-08-27 20:42:13', 'admin', '2020-08-27 20:42:13', '');
INSERT INTO `sys_menu` VALUES (2018, '重置密码', 100, 5, '', '', 1, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2020-08-27 20:42:39', 'admin', '2020-08-27 20:42:39', '');
INSERT INTO `sys_menu` VALUES (2019, '角色查询', 101, 1, '', '', 1, 'F', '0', '0', 'system:role:query', '#', 'admin', '2020-08-27 20:44:13', 'admin', '2020-08-27 20:44:13', '');
INSERT INTO `sys_menu` VALUES (2020, '角色新增', 101, 2, '', '', 1, 'F', '0', '0', 'system:role:add', '#', 'admin', '2020-08-27 20:44:27', 'admin', '2020-08-27 20:44:27', '');
INSERT INTO `sys_menu` VALUES (2021, '角色修改', 101, 3, '', '', 1, 'F', '0', '0', 'system:role:edit', '#', 'admin', '2020-08-27 20:44:52', 'admin', '2020-08-27 20:44:52', '');
INSERT INTO `sys_menu` VALUES (2022, '角色删除', 101, 4, '', '', 1, 'F', '0', '0', 'system:role:remove', '#', 'admin', '2020-08-27 20:45:12', 'admin', '2020-08-27 20:45:12', '');
INSERT INTO `sys_menu` VALUES (2023, '菜单查询', 102, 1, '', '', 1, 'F', '0', '0', 'system:menu:query', '#', 'admin', '2020-08-27 20:45:36', 'admin', '2020-08-27 20:45:36', '');
INSERT INTO `sys_menu` VALUES (2024, '菜单新增', 102, 2, '', '', 1, 'F', '0', '0', 'system:menu:add', '#', 'admin', '2020-08-27 20:45:53', 'admin', '2020-08-27 20:46:04', '');
INSERT INTO `sys_menu` VALUES (2025, '菜单修改', 102, 3, '', '', 1, 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2020-08-27 20:46:22', 'admin', '2020-08-27 20:46:22', '');
INSERT INTO `sys_menu` VALUES (2026, '菜单删除', 102, 4, '', '', 1, 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2020-08-27 20:46:38', 'admin', '2020-08-27 20:46:38', '');
INSERT INTO `sys_menu` VALUES (2027, '部门查询', 103, 1, '', '', 1, 'F', '0', '0', 'system:dept:query', '#', 'admin', '2020-08-27 20:47:02', 'admin', '2020-08-27 20:47:02', '');
INSERT INTO `sys_menu` VALUES (2028, '部门新增', 103, 2, '', '', 1, 'F', '0', '0', 'system:dept:add', '#', 'admin', '2020-08-27 20:47:23', 'admin', '2020-08-27 20:47:23', '');
INSERT INTO `sys_menu` VALUES (2029, '部门修改', 103, 3, '', '', 1, 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2020-08-27 20:47:41', 'admin', '2020-08-27 20:47:41', '');
INSERT INTO `sys_menu` VALUES (2030, '部门删除', 103, 4, '', '', 1, 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2020-08-27 20:47:54', 'admin', '2020-08-27 20:47:54', '');
INSERT INTO `sys_menu` VALUES (2031, '操作查询', 2007, 1, '', '', 1, 'F', '0', '0', 'monitor:operlog:query', '#', 'admin', '2020-08-27 20:48:40', 'admin', '2020-08-27 20:48:40', '');
INSERT INTO `sys_menu` VALUES (2032, '操作删除', 2007, 2, '', '', 1, 'F', '0', '0', 'monitor:operlog:remove', '#', 'admin', '2020-08-27 20:49:00', 'admin', '2020-08-27 20:49:00', '');
INSERT INTO `sys_menu` VALUES (2033, '登录查询', 2008, 1, '', '', 1, 'F', '0', '0', 'monitor:logininfo:query', '#', 'admin', '2020-08-27 20:49:30', 'admin', '2020-08-30 11:57:31', '');
INSERT INTO `sys_menu` VALUES (2034, '登录删除', 2008, 2, '', '', 1, 'F', '0', '0', 'monitor:logininfo:remove', '#', 'admin', '2020-08-27 20:49:48', 'admin', '2020-08-30 11:57:36', '');
INSERT INTO `sys_menu` VALUES (2035, '字典管理', 1, 6, 'dict', 'system/dict/index', 1, 'C', '0', '0', 'system:dict:list', 'dict', 'admin', '2020-08-28 09:36:01', 'admin', '2020-08-28 09:36:01', '');
INSERT INTO `sys_menu` VALUES (2036, '参数设置', 1, 7, 'config', 'system/config/index', 1, 'C', '0', '0', 'system:config:list', 'edit', 'admin', '2020-08-28 09:37:10', 'admin', '2020-08-28 09:37:10', '');
INSERT INTO `sys_menu` VALUES (2037, '字典查询', 2035, 1, '', '', 1, 'F', '0', '0', 'system:dict:query', '#', 'admin', '2020-08-28 09:38:05', 'admin', '2020-08-28 09:38:05', '');
INSERT INTO `sys_menu` VALUES (2038, '字典新增', 2035, 2, '', '', 1, 'F', '0', '0', ' system:dict:add', '#', 'admin', '2020-08-28 09:38:35', 'admin', '2020-08-28 09:38:35', '');
INSERT INTO `sys_menu` VALUES (2039, '字典修改', 2035, 3, '', '', 1, 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2020-08-28 09:38:54', 'admin', '2020-08-28 09:38:54', '');
INSERT INTO `sys_menu` VALUES (2040, '字典删除', 2035, 4, '', '', 1, 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2020-08-28 09:39:09', 'admin', '2020-08-28 09:39:09', '');
INSERT INTO `sys_menu` VALUES (2041, '参数查询', 2036, 1, '', '', 1, 'F', '0', '0', 'system:config:query', '#', 'admin', '2020-08-28 09:39:26', 'admin', '2020-08-28 09:39:26', '');
INSERT INTO `sys_menu` VALUES (2042, '参数新增', 2036, 2, '', '', 1, 'F', '0', '0', 'system:config:add', '#', 'admin', '2020-08-28 09:39:39', 'admin', '2020-08-28 09:39:39', '');
INSERT INTO `sys_menu` VALUES (2043, '参数修改', 2036, 3, '', '', 1, 'F', '0', '0', 'system:config:edit', '#', 'admin', '2020-08-28 09:39:56', 'admin', '2020-08-28 09:39:56', '');
INSERT INTO `sys_menu` VALUES (2044, '参数删除', 2036, 4, '', '', 1, 'F', '0', '0', 'system:config:remove', '#', 'admin', '2020-08-28 09:40:09', 'admin', '2020-08-28 09:40:09', '');
INSERT INTO `sys_menu` VALUES (2045, '通知公告', 1, 8, 'notice', 'system/notice/index', 1, 'C', '0', '0', 'system:notice:list', 'message', 'admin', '2020-08-28 11:48:30', 'admin', '2020-08-28 11:48:30', '');
INSERT INTO `sys_menu` VALUES (2046, '公告查询', 2045, 1, '', '', 1, 'F', '0', '0', 'system:notice:query', '#', 'admin', '2020-08-28 11:50:35', 'admin', '2020-08-28 11:50:35', '');
INSERT INTO `sys_menu` VALUES (2047, '公告新增', 2045, 2, '', '', 1, 'F', '0', '0', '	 system:notice:add', '#', 'admin', '2020-08-28 11:50:51', 'admin', '2020-08-28 11:50:51', '');
INSERT INTO `sys_menu` VALUES (2048, '公告修改', 2045, 3, '', '', 1, 'F', '0', '0', 'system:notice:edit', '#', 'admin', '2020-08-28 11:51:07', 'admin', '2020-08-28 11:51:07', '');
INSERT INTO `sys_menu` VALUES (2049, '公告删除', 2045, 4, '', '', 1, 'F', '0', '0', 'system:notice:remove', '#', 'admin', '2020-08-28 11:51:31', 'admin', '2020-08-28 11:51:31', '');
INSERT INTO `sys_menu` VALUES (2050, '新闻类别', 2061, 1, 'type', 'news/type/index', 1, 'C', '0', '0', 'news:type:list', 'documentation', 'admin', '2020-08-28 11:50:35', 'admin', '2020-08-28 21:55:00', '新闻类型菜单');
INSERT INTO `sys_menu` VALUES (2053, '新闻类型修改', 2050, 3, '#', '', 1, 'F', '0', '0', 'news:type:edit', '#', 'admin', '2020-08-28 11:50:35', 'admin', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2054, '新闻类型删除', 2050, 4, '#', '', 1, 'F', '0', '0', 'news:type:remove', '#', 'admin', '2020-08-28 11:50:35', 'admin', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2056, '新闻类型查询', 2050, 1, '#', '', 1, 'F', '0', '0', 'news:type:query', '#', 'admin', '2020-08-28 11:50:35', 'admin', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2057, '新闻类型新增', 2050, 2, '#', '', 1, 'F', '0', '0', 'news:type:add', '#', 'admin', '2020-08-28 11:50:35', 'admin', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2061, '新闻管理', 0, 4, 'news', '', 1, 'M', '0', '0', '', 'build', 'admin', '2020-08-28 21:26:08', 'admin', '2020-08-28 21:26:08', '');
INSERT INTO `sys_menu` VALUES (2062, '友情链接', 2061, 1, 'link', 'news/link/index', 1, 'C', '0', '0', 'news:link:list', 'link', 'admin', '2020-08-28 11:50:35', 'admin', '2020-08-29 10:11:23', '友情链接菜单');
INSERT INTO `sys_menu` VALUES (2063, '友情链接查询', 2062, 1, '#', '', 1, 'F', '0', '0', 'news:link:query', '#', 'admin', '2020-08-28 11:50:35', 'admin', '2020-08-28 11:50:35', '');
INSERT INTO `sys_menu` VALUES (2064, '友情链接新增', 2062, 2, '#', '', 1, 'F', '0', '0', 'news:link:add', '#', 'admin', '2020-08-28 11:50:35', 'admin', '2020-08-28 11:50:35', '');
INSERT INTO `sys_menu` VALUES (2065, '友情链接修改', 2062, 3, '#', '', 1, 'F', '0', '0', 'news:link:edit', '#', 'admin', '2020-08-28 11:50:35', 'admin', '2020-08-28 11:50:35', '');
INSERT INTO `sys_menu` VALUES (2066, '友情链接删除', 2062, 4, '#', '', 1, 'F', '0', '0', 'news:link:remove', '#', 'admin', '2020-08-28 11:50:35', 'admin', '2020-08-28 11:50:35', '');
INSERT INTO `sys_menu` VALUES (2084, '新闻发布', 2061, 1, 'news', 'news/news/index', 1, 'C', '0', '0', 'news:news:list', 'international', 'admin', '2020-08-28 11:50:35', 'admin', '2020-08-29 16:33:37', '新闻菜单');
INSERT INTO `sys_menu` VALUES (2085, '新闻查询', 2084, 1, '#', '', 1, 'F', '0', '0', 'news:news:query', '#', 'admin', '2020-08-28 11:50:35', 'admin', '2020-08-28 11:50:35', '');
INSERT INTO `sys_menu` VALUES (2086, '新闻新增', 2084, 2, '#', '', 1, 'F', '0', '0', 'news:news:add', '#', 'admin', '2020-08-28 11:50:35', 'admin', '2020-08-28 11:50:35', '');
INSERT INTO `sys_menu` VALUES (2087, '新闻修改', 2084, 3, '#', '', 1, 'F', '0', '0', 'news:news:edit', '#', 'admin', '2020-08-28 11:50:35', 'admin', '2020-08-28 11:50:35', '');
INSERT INTO `sys_menu` VALUES (2088, '新闻删除', 2084, 4, '#', '', 1, 'F', '0', '0', 'news:news:remove', '#', 'admin', '2020-08-28 11:50:35', 'admin', '2020-08-28 11:50:35', '');
INSERT INTO `sys_menu` VALUES (2089, '新闻审核', 2084, 5, '', '', 1, 'F', '0', '0', 'news:news:inspect', '#', 'admin', '2020-08-30 15:41:52', 'admin', '2020-08-30 15:41:52', '');
INSERT INTO `sys_menu` VALUES (2090, '新闻评论', 2061, 1, 'comment', 'news/comment/index', 1, 'C', '0', '0', 'news:comment:list', 'cascader', 'admin', '2018-03-01 00:00:00', 'admin', '2020-08-31 08:43:27', '新闻评论菜单');
INSERT INTO `sys_menu` VALUES (2091, '新闻评论查询', 2090, 1, '#', '', 1, 'F', '0', '0', 'news:comment:query', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2092, '新闻评论新增', 2090, 2, '#', '', 1, 'F', '0', '0', 'news:comment:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2093, '新闻评论修改', 2090, 3, '#', '', 1, 'F', '0', '0', 'news:comment:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2094, '新闻评论删除', 2090, 4, '#', '', 1, 'F', '0', '0', 'news:comment:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2095, '全局检索', 2004, 4, 'kibana', 'monitor/kibana/index', 1, 'C', '0', '0', 'monitor:kibana:list', 'chart', 'admin', '2020-09-03 16:42:38', 'admin', '2020-09-03 16:42:38', '');
INSERT INTO `sys_menu` VALUES (2096, '消息队列', 2004, 5, 'http://120.79.43.16:15672', 'monitor/rabbit/index', 0, 'C', '0', '0', 'monitor:rabbit:list', 'link', 'admin', '2020-09-03 16:53:53', 'admin', '2020-09-03 16:59:52', '');
INSERT INTO `sys_menu` VALUES (2097, '数据库文档', 2004, 6, 'sql', 'monitor/sql/index', 1, 'C', '0', '0', 'monitor:sql:list', 'excel', 'admin', '2020-09-03 19:17:11', 'admin', '2020-09-03 19:17:11', '');
COMMIT;

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` varchar(2047) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(511) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='通知公告表';

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
BEGIN;
INSERT INTO `sys_notice` VALUES (10, 'Linq新闻发布平台马上开启', '1', '<p>image</p>', '0', 'admin', '2020-08-28 12:39:44', 'admin', '2020-08-28 12:39:44', '');
INSERT INTO `sys_notice` VALUES (11, '特朗普炮轰NBA', '2', '<p class=\"ql-indent-1\"><strong style=\"color: rgb(51, 51, 51);\">炮轰球员罢赛行动　#特朗普称NBA像一个政治组织#！ 】网页链接 针对#NBA罢赛# 事件，特朗普在28日受访时​​做出回应，公开批评了NBA球员罢赛的行为，「他们已经变得像一个政治组织，这不是一件好事。特朗普指出，联盟的收视率比过去几个赛季还要低，</strong></p>', '0', 'admin', '2020-08-28 12:39:44', 'admin', '2020-08-28 12:39:44', '');
INSERT INTO `sys_notice` VALUES (12, '国防部新闻发言', '1', '<p><span class=\"ql-size-large\">国防部新闻发言人吴谦今日就美军机擅闯中国演习禁飞区发表谈话. 国防部,禁飞区,挑衅,军机,中方 据韩联社25日报道,因为韩国疫情近期出现反弹,韩...</span><span class=\"ql-cursor\">﻿</span></p>', '0', 'admin', '2020-08-28 12:39:44', 'admin', '2020-08-28 12:39:44', '');
INSERT INTO `sys_notice` VALUES (13, '哈尔滨市最新疫情通报', '2', '<p><strong style=\"color: rgb(51, 51, 51);\">哈尔滨市</strong><strong style=\"color: rgb(204, 0, 0);\">最新</strong><strong style=\"color: rgb(51, 51, 51);\">疫情通报 2020年8月27日0-24时,哈尔滨市无新冠肺炎疫情报告,现有境外输入确诊病例1例. 国内外新冠肺炎疫情依然复杂严峻,大家要紧绷...<span class=\"ql-cursor\">﻿</span></strong></p>', '0', 'admin', '2020-08-28 12:40:49', 'admin', '2020-08-28 12:40:49', '');
INSERT INTO `sys_notice` VALUES (14, ' 民航新闻', '2', '<p><strong style=\"color: rgb(51, 51, 51);\" class=\"ql-size-large\">&nbsp;民航</strong><strong style=\"color: rgb(204, 0, 0);\" class=\"ql-size-large\">新闻</strong><strong style=\"color: rgb(51, 51, 51);\" class=\"ql-size-large\">&nbsp;-&gt; 近期热点 根据《公共航空运输企业... 据业内消息人士,波音和加拿大交通部的飞行员将于当地时间周三上午对波音737MAX客机进行飞行测试,这标志...</strong></p>', '0', 'admin', '2020-08-28 12:41:05', 'admin', '2020-08-28 12:41:05', '');
INSERT INTO `sys_notice` VALUES (15, '动新闻', '2', '<p><strong style=\"color: rgb(51, 51, 51);\">动</strong><strong style=\"color: rgb(204, 0, 0);\">新闻</strong><strong style=\"color: rgb(51, 51, 51);\">列表展示</strong><strong style=\"color: rgb(204, 0, 0);\">最新</strong><strong style=\"color: rgb(51, 51, 51);\">的国内</strong><strong style=\"color: rgb(204, 0, 0);\">新闻</strong><strong style=\"color: rgb(51, 51, 51);\">、国际</strong><strong style=\"color: rgb(204, 0, 0);\">新闻</strong><strong style=\"color: rgb(51, 51, 51);\">,需要了解</strong><strong style=\"color: rgb(204, 0, 0);\">最新最近</strong><strong style=\"color: rgb(51, 51, 51);\">最热的国内国际</strong><strong style=\"color: rgb(204, 0, 0);\">新闻</strong><strong style=\"color: rgb(51, 51, 51);\">事就在东方资讯网</strong></p>', '0', 'admin', '2020-08-28 12:41:24', 'admin', '2020-08-28 12:41:24', '');
INSERT INTO `sys_notice` VALUES (16, '最新数据', '2', '<h3><a href=\"https://www.sogou.com/link?url=hedJjaC291Ode25g20YjoBT5WIwiJVZ9DuYMEj1UoyuIYShr_PIt6xo7AN2e9HwDuOIs__z5_nwFpuhAvSbGSxcDhJaKAzpYfX62bwFHedQ.\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(204, 0, 0);\">最新</a><a href=\"https://www.sogou.com/link?url=hedJjaC291Ode25g20YjoBT5WIwiJVZ9DuYMEj1UoyuIYShr_PIt6xo7AN2e9HwDuOIs__z5_nwFpuhAvSbGSxcDhJaKAzpYfX62bwFHedQ.\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(116, 18, 116);\">数据!合肥夏季求职期平均薪酬有这么多!丨</a><a href=\"https://www.sogou.com/link?url=hedJjaC291Ode25g20YjoBT5WIwiJVZ9DuYMEj1UoyuIYShr_PIt6xo7AN2e9HwDuOIs__z5_nwFpuhAvSbGSxcDhJaKAzpYfX62bwFHedQ.\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(204, 0, 0);\">新闻</a><a href=\"https://www.sogou.com/link?url=hedJjaC291Ode25g20YjoBT5WIwiJVZ9DuYMEj1UoyuIYShr_PIt6xo7AN2e9HwDuOIs__z5_nwFpuhAvSbGSxcDhJaKAzpYfX62bwFHedQ.\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(116, 18, 116);\">早班车</a></h3><p><br></p>', '0', 'admin', '2020-08-28 12:41:36', 'admin', '2020-08-28 12:41:36', '');
INSERT INTO `sys_notice` VALUES (17, ' 新浪看点平台4小时前', '1', '<p><br></p><p><strong>新浪看点平台4小时前</strong></p><p><strong>星期五 8月27日,在黄山徽州区呈坎八卦村的永兴湖广场上,村民将收获归仓的玉米、黄豆、稻谷、荞麦、南瓜和红辣椒等,因地制宜进行晾晒.呈坎晒秋的习俗已延...</strong></p>', '0', 'admin', '2020-08-28 12:41:48', 'admin', '2020-08-28 12:41:48', '');
INSERT INTO `sys_notice` VALUES (18, '今天下午', '2', '<p><span style=\"color: rgb(51, 51, 51);\">(原标题:</span><span style=\"color: rgb(204, 0, 0);\">最新</span><span style=\"color: rgb(51, 51, 51);\">|陈通任上海市副市长) 今天下午,上海市十五届人大常委会第二十四次会议审议并表决通过了有关人事任免事项,决定任命陈通为上海市人民...</span></p>', '0', 'admin', '2020-08-28 12:42:02', 'admin', '2020-08-28 12:42:02', '');
INSERT INTO `sys_notice` VALUES (19, '新浪网2020年7月2日', '2', '<p><br></p><p><strong>新浪网2020年7月2日</strong></p><p><strong>来源:国是直通车 美国取消香港特殊相关待遇,印度禁止中国59款app,中国要怎么应对? 2日举行的</strong><strong style=\"color: rgb(204, 0, 0);\">新闻</strong><strong>发布会上,中国商务部对此做出了回应. 坚定支持香...</strong></p>', '0', 'admin', '2020-08-28 12:42:15', 'admin', '2020-08-28 12:42:15', '');
INSERT INTO `sys_notice` VALUES (20, ' 澎湃新闻', '2', '<p><img src=\"http://120.79.43.16:80/group1/M00/00/00/rBL7oF9SACWAMepiAABCPtxygcM722.jpg\"></p>', '0', 'admin', '2020-08-28 12:42:29', 'admin', '2020-09-04 08:51:50', '');
COMMIT;

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log` (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '模块标题',
  `business_type` int(1) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2047) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2047) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '返回参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2047) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '错误消息',
  `oper_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='操作日志记录';

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_oper_log` VALUES (1, '操作日志', 9, 'com.linq.web.controller.monitor.SysOperlogController.clean()', 'DELETE', 1, 'admin', '', '/monitor/operlog/clean', '171.95.10.23', '四川省广安市 电信', '{}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 04:30:22');
INSERT INTO `sys_oper_log` VALUES (2, '角色管理', 2, 'com.linq.web.controller.system.SysRoleController.changeStatus()', 'PUT', 1, 'admin', '', '/system/role/change/status', '171.95.10.23', '四川省广安市 电信', '{\"flag\":false,\"roleId\":2,\"admin\":false,\"updateTime\":1599193846870,\"params\":{},\"updateBy\":\"admin\",\"status\":\"0\"}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 04:30:46');
INSERT INTO `sys_oper_log` VALUES (3, '角色管理', 2, 'com.linq.web.controller.system.SysRoleController.dataScope()', 'PUT', 1, 'admin', '', '/system/role/data/scope', '123.161.116.206', '河南省南阳市 电信ADSL', '{\"flag\":false,\"roleId\":1,\"admin\":true,\"remark\":\"系统管理员\",\"updateTime\":1598617672000,\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":1,\"createBy\":\"admin\",\"createTime\":1598617672000,\"updateBy\":\"linq\",\"roleKey\":\"admin\",\"roleName\":\"系统管理员\",\"deptIds\":[],\"status\":\"0\"}', 'null', 1, '不允许操作超级管理员角色', '2020-09-04 04:47:06');
INSERT INTO `sys_oper_log` VALUES (4, '在线用户', 3, 'com.linq.web.controller.monitor.SysUserOnlineController.forceLogout()', 'DELETE', 1, 'admin', '', '/monitor/online/996aa1e9-f1a3-4189-9a30-3ecf529dc791', '123.161.116.206', '河南省南阳市 电信ADSL', '{tokenId=996aa1e9-f1a3-4189-9a30-3ecf529dc791}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 04:47:26');
INSERT INTO `sys_oper_log` VALUES (5, '角色管理', 2, 'com.linq.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', '', '/system/role', '111.75.189.148', '江西省宜春市 电信', '{\"flag\":false,\"roleId\":1,\"admin\":true,\"remark\":\"系统管理员\",\"updateTime\":1598617672000,\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":1,\"createBy\":\"admin\",\"createTime\":1598617672000,\"updateBy\":\"linq\",\"roleKey\":\"admin\",\"roleName\":\"系统管理员\",\"menuIds\":[1,100,2014,2015,2016,2017,2018,101,2019,2020,2021,2022,102,2023,2024,2025,2026,103,2027,2028,2029,2030,2006,2007,2031,2032,2008,2033,2034,2035,2037,2038,2039,2040,2036,2041,2042,2043,2044,2045,2046,2047,2048,2049,2004,2005,2012,2095,2011,2096,2097,2009,2010,2013,2061,2062,2063,2064,2065,2066,2050,2056,2057,2053,2054,2090,2091,2092,2093,2094,2084,2085,2086,2087,2088,2089],\"status\":\"0\"}', 'null', 1, '不允许操作超级管理员角色', '2020-09-04 04:58:08');
INSERT INTO `sys_oper_log` VALUES (6, '角色管理', 2, 'com.linq.web.controller.system.SysRoleController.changeStatus()', 'PUT', 1, 'admin', '', '/system/role/change/status', '183.17.231.143', '广东省深圳市 电信', '{\"flag\":false,\"roleId\":1,\"admin\":true,\"params\":{},\"status\":\"1\"}', 'null', 1, '不允许操作超级管理员角色', '2020-09-04 04:59:59');
INSERT INTO `sys_oper_log` VALUES (7, '角色管理', 2, 'com.linq.web.controller.system.SysRoleController.changeStatus()', 'PUT', 1, 'admin', '', '/system/role/change/status', '183.17.231.143', '广东省深圳市 电信', '{\"flag\":false,\"roleId\":110,\"admin\":false,\"updateTime\":1599195602273,\"params\":{},\"updateBy\":\"admin\",\"status\":\"1\"}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 05:00:02');
INSERT INTO `sys_oper_log` VALUES (8, '角色管理', 2, 'com.linq.web.controller.system.SysRoleController.changeStatus()', 'PUT', 1, 'admin', '', '/system/role/change/status', '183.17.231.143', '广东省深圳市 电信', '{\"flag\":false,\"roleId\":110,\"admin\":false,\"updateTime\":1599195604306,\"params\":{},\"updateBy\":\"admin\",\"status\":\"0\"}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 05:00:04');
INSERT INTO `sys_oper_log` VALUES (9, '用户管理', 2, 'com.linq.web.controller.system.SysUserController.edit()', 'PUT', 1, 'admin', '', '/system/user', '223.72.91.158', '北京市 移动', '{\"roles\":[{\"flag\":false,\"roleId\":1,\"admin\":true,\"dataScope\":\"1\",\"params\":{},\"roleSort\":1,\"roleKey\":\"admin\",\"roleName\":\"系统管理员\",\"status\":\"0\"}],\"admin\":true,\"loginDate\":1521199980000,\"remark\":\"管理员\",\"delFlag\":\"0\",\"password\":\"\",\"loginIp\":\"183.17.231.143\",\"email\":\"18061877017@163.com\",\"nickName\":\"林义清\",\"sex\":\"0\",\"deptId\":100,\"avatar\":\"/profile/avatar/2020/09/04/819d44b5-ac28-4fd7-b6a2-b03bfe3f20e5.jpeg\",\"dept\":{\"deptName\":\"Linq新闻\",\"leader\":\"linq\",\"deptId\":100,\"orderNum\":0,\"params\":{},\"parentId\":0,\"children\":[],\"status\":\"0\"},\"params\":{},\"userId\":1,\"createBy\":\"admin\",\"roleIds\":[1],\"createTime\":1598522011000,\"phone\":\"18061877017\",\"status\":\"1\",\"username\":\"admin\"}', 'null', 1, '不允许操作超级管理员用户', '2020-09-04 05:02:52');
INSERT INTO `sys_oper_log` VALUES (10, '用户管理', 2, 'com.linq.web.controller.system.SysUserController.resetPwd()', 'PUT', 1, 'admin', '', '/system/user/reset/pwd', '223.72.91.158', '北京市 移动', '{\"admin\":true,\"password\":\"000000\",\"params\":{},\"userId\":1}', 'null', 1, '不允许操作超级管理员用户', '2020-09-04 05:03:03');
INSERT INTO `sys_oper_log` VALUES (11, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeIsPublic()', 'PUT', 1, 'admin', '', '/news/news/change/isPublic', '111.75.189.148', '江西省宜春市 电信', '{\"updateBy\":\"admin\",\"isPublic\":\"1\",\"updateTime\":1599195819957,\"params\":{},\"newsId\":3492}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 05:03:40');
INSERT INTO `sys_oper_log` VALUES (12, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeIsPublic()', 'PUT', 1, 'admin', '', '/news/news/change/isPublic', '111.75.189.148', '江西省宜春市 电信', '{\"isPublic\":\"0\",\"params\":{},\"newsId\":3492}', 'null', 1, '', '2020-09-04 05:03:42');
INSERT INTO `sys_oper_log` VALUES (13, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeIsPublic()', 'PUT', 1, 'admin', '', '/news/news/change/isPublic', '111.75.189.148', '江西省宜春市 电信', '{\"isPublic\":\"0\",\"params\":{},\"newsId\":3492}', 'null', 1, '', '2020-09-04 05:03:45');
INSERT INTO `sys_oper_log` VALUES (14, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeIsPublic()', 'PUT', 1, 'admin', '', '/news/news/change/isPublic', '111.75.189.148', '江西省宜春市 电信', '{\"isPublic\":\"0\",\"params\":{},\"newsId\":3492}', 'null', 1, '', '2020-09-04 05:03:50');
INSERT INTO `sys_oper_log` VALUES (15, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeStatus()', 'PUT', 1, 'admin', '', '/news/news/change/status', '111.75.189.148', '江西省宜春市 电信', '{\"newsContent\":\"<div id=\\\"article_content\\\" class=\\\"article_content clearfix\\\"> \\n <link rel=\\\"stylesheet\\\" href=\\\"https://csdnimg.cn/release/phoenix/template/css/ck_htmledit_views-3d4dc5c1de.css\\\"> \\n <div id=\\\"content_views\\\" class=\\\"markdown_views prism-dracula\\\"> \\n  <!-- flowchart 箭头图标 勿删 --> \\n  <svg xmlns=\\\"http://www.w3.org/2000/svg\\\" style=\\\"display: none;\\\"> \\n   <path stroke-linecap=\\\"round\\\" d=\\\"M5,0 0,2.5 5,5z\\\" id=\\\"raphael-marker-block\\\" style=\\\"-webkit-tap-highlight-color: rgba(0, 0, 0, 0);\\\"></path> \\n  </svg> \\n  <p>本文介绍一种安卓卸载系统应用的方法，如果你和我一样喜欢瞎折腾，有那么一点强迫症，希望对你有用。作为一名安卓开发者，大学以前总喜欢刷不同刷机包（ROM）、爱装不同的Launcher桌面体验，不过工作了没以前爱折腾了。安卓提供的adb工具不用多介绍，网上都有很多教程。</p> \\n  <h4><a id=\\\"__1\\\"></a>一. 实验条件</h4> \\n  <ul> \\n   <li>LG V30 （Android 9.0）</li> \\n   <li>较新adb驱动</li> \\n   <li>PC一台</li> \\n   <li>对应的USB调试线一根</li> \\n  </ul> \\n  <h4><a id=\\\"_adb_8\\\"></a>二. 安装adb驱动</h4> \\n  <p>下载360手机助手，安装USB连接手机，360手机助手对手机adb驱动支持很好，直接连接就好了。确定adb驱动安装好后，任务管理器将360Mobile相关的进程干掉。重新连接adb，最后使用cmd或者AS自带的Terminal进入命令行，熟悉我们的adb命令就行了。</p> \\n  <h4><a id=\\\"_adb_10\\\"></a>三. adb卸载预装应用步骤</h4> \\n  <h5><a id=\\\"1adb_11\\\"></a>1、查看adb版本</h5> \\n  <p>确认adb版本较新，我自己亲自试了在自己笔记本电脑adb工具执行命令用不了，总是提示如下打印：</p> \\n  <blockquote> \\n   <p>F:\\\\mypc&gt;adb devices<br> adb server is out of date. killing…<br> daemon started successfully *<br> List of devices attached<br> 810e909e offline</p> \\n  </blockquote> \\n  <p>尝试了网上的方法，还是没有解决，但想到公司电脑好像没有问题的。于是对比了两台电脑adb的版本，还是存在差异，或许是adb安装路径有问题！</p> \\n  <blockquote> \\n   <p>F:\\\\mypc&gt;adb version<br> Android Debug Bridge version 1.0.29</p> \\n  </blockquote> \\n  <blockquote> \\n   <p>E:\\\\other&gt;adb version<br> Android Debug Bridge version 1.0.41<br> Version 29.0.1-5644136<br> Installed as C:\\\\Windows\\\\system32\\\\adb.exe</p> \\n  </blockquote> \\n  <h5><a id=\\\"2_28\\\"></a>2、查看系统中所有包</h5> \\n  <p>执行下面命令，将打印复制到记事本中，方便后面查找。</p> \\n  <blockquote> \\n   <p>adb shell pm list packages</p> \\n  </blockquote> \\n  <h5><a id=\\\"3_32\\\"></a>3、确认不知名的应用包名（较低安卓版本可直接查看应用包名）</h5> \\n  <p>有的应用我们可以通过包名就可以判断哪一个应用，', 'null', 1, 'No value present', '2020-09-04 05:04:02');
INSERT INTO `sys_oper_log` VALUES (16, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeIsPublic()', 'PUT', 1, 'admin', '', '/news/news/change/isPublic', '111.75.189.148', '江西省宜春市 电信', '{\"updateBy\":\"admin\",\"isPublic\":\"1\",\"updateTime\":1599195850424,\"params\":{},\"newsId\":4589}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 05:04:10');
INSERT INTO `sys_oper_log` VALUES (17, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeIsPublic()', 'PUT', 1, 'admin', '', '/news/news/change/isPublic', '111.75.189.148', '江西省宜春市 电信', '{\"isPublic\":\"0\",\"params\":{},\"newsId\":4589}', 'null', 1, '', '2020-09-04 05:04:13');
INSERT INTO `sys_oper_log` VALUES (18, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeIsPublic()', 'PUT', 1, 'admin', '', '/news/news/change/isPublic', '111.75.189.148', '江西省宜春市 电信', '{\"isPublic\":\"0\",\"params\":{},\"newsId\":4589}', 'null', 1, '', '2020-09-04 05:04:16');
INSERT INTO `sys_oper_log` VALUES (19, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeIsPublic()', 'PUT', 1, 'admin', '', '/news/news/change/isPublic', '111.75.189.148', '江西省宜春市 电信', '{\"isPublic\":\"0\",\"params\":{},\"newsId\":4589}', 'null', 1, '', '2020-09-04 05:05:05');
INSERT INTO `sys_oper_log` VALUES (20, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeIsPublic()', 'PUT', 1, 'admin', '', '/news/news/change/isPublic', '111.75.189.148', '江西省宜春市 电信', '{\"updateBy\":\"admin\",\"isPublic\":\"1\",\"updateTime\":1599195947682,\"params\":{},\"newsId\":4619}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 05:05:47');
INSERT INTO `sys_oper_log` VALUES (21, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeIsPublic()', 'PUT', 1, 'admin', '', '/news/news/change/isPublic', '111.75.189.148', '江西省宜春市 电信', '{\"isPublic\":\"0\",\"params\":{},\"newsId\":4619}', 'null', 1, '', '2020-09-04 05:05:50');
INSERT INTO `sys_oper_log` VALUES (22, '用户管理', 1, 'com.linq.web.controller.system.SysUserController.add()', 'POST', 1, 'admin', '', '/system/user', '117.136.40.185', '广东省深圳市 移动', '{\"admin\":false,\"password\":\"$2a$10$yhhrQhoFuqR1eg8j6XAkZ.pxmX64Gay0c6Ps5cheEgs3iwDnxXmgG\",\"updateBy\":\"admin\",\"email\":\"2560295278@qq.com\",\"nickName\":\"二哥\",\"sex\":\"1\",\"deptId\":204,\"updateTime\":1599196167045,\"params\":{},\"userId\":16,\"createBy\":\"admin\",\"roleIds\":[110],\"createTime\":1599196167045,\"phone\":\"13979256550\",\"status\":\"0\",\"username\":\"gjh\"}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 05:09:27');
INSERT INTO `sys_oper_log` VALUES (23, '用户管理', 3, 'com.linq.web.controller.system.SysUserController.remove()', 'DELETE', 1, 'admin', '', '/system/user/16', '111.75.189.148', '江西省宜春市 电信', '{ids=16}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 05:27:36');
INSERT INTO `sys_oper_log` VALUES (24, '用户管理', 2, 'com.linq.web.controller.system.SysUserController.changeStatus()', 'PUT', 1, 'admin', '', '/system/user/change/status', '111.75.189.148', '江西省宜春市 电信', '{\"admin\":false,\"updateBy\":\"admin\",\"updateTime\":1599198003112,\"params\":{},\"userId\":14,\"status\":\"0\"}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 05:40:03');
INSERT INTO `sys_oper_log` VALUES (25, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeIsPublic()', 'PUT', 1, 'admin', '', '/news/news/change/isPublic', '111.75.189.148', '江西省宜春市 电信', '{\"updateBy\":\"admin\",\"isPublic\":\"1\",\"updateTime\":1599198015709,\"params\":{},\"newsId\":5070}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 05:40:15');
INSERT INTO `sys_oper_log` VALUES (26, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeIsPublic()', 'PUT', 1, 'admin', '', '/news/news/change/isPublic', '111.75.189.148', '江西省宜春市 电信', '{\"isPublic\":\"0\",\"params\":{},\"newsId\":5070}', 'null', 1, '', '2020-09-04 05:40:18');
INSERT INTO `sys_oper_log` VALUES (27, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeIsPublic()', 'PUT', 1, 'admin', '', '/news/news/change/isPublic', '111.75.189.148', '江西省宜春市 电信', '{\"isPublic\":\"1\",\"params\":{},\"newsId\":5065}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 06:11:27');
INSERT INTO `sys_oper_log` VALUES (28, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeIsPublic()', 'PUT', 1, 'admin', '', '/news/news/change/isPublic', '111.75.189.148', '江西省宜春市 电信', '{\"isPublic\":\"1\",\"params\":{},\"newsId\":5085}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 06:11:28');
INSERT INTO `sys_oper_log` VALUES (29, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeIsPublic()', 'PUT', 1, 'admin', '', '/news/news/change/isPublic', '111.75.189.148', '江西省宜春市 电信', '{\"isPublic\":\"0\",\"params\":{},\"newsId\":5085}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 06:11:34');
INSERT INTO `sys_oper_log` VALUES (30, '在线用户', 3, 'com.linq.web.controller.monitor.SysUserOnlineController.forceLogout()', 'DELETE', 1, 'admin', '', '/monitor/online/277b5f4a-b8d5-4d80-bffb-fb9c13222699', '59.173.165.141', '湖北省武汉市 电信', '{tokenId=277b5f4a-b8d5-4d80-bffb-fb9c13222699}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 06:14:30');
INSERT INTO `sys_oper_log` VALUES (31, '在线用户', 3, 'com.linq.web.controller.monitor.SysUserOnlineController.forceLogout()', 'DELETE', 1, 'admin', '', '/monitor/online/f24ae7ff-1a01-4aa9-b501-2ded24ad8f2a', '59.173.165.141', '湖北省武汉市 电信', '{tokenId=f24ae7ff-1a01-4aa9-b501-2ded24ad8f2a}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 06:20:38');
INSERT INTO `sys_oper_log` VALUES (32, '新闻', 1, 'com.linq.web.controller.news.LinkNewsController.add()', 'POST', 1, 'admin', '', '/news/news', '120.228.2.61', '湖南省长沙市 移通', '{\"newsContent\":\"<p>j jhj&nbsp;</p>\",\"orderNum\":0,\"remark\":\"jh \",\"newsTitle\":\"hgh \",\"isPublic\":\"0\",\"updateTime\":1599201094510,\"params\":{},\"userId\":1,\"newsImage\":\"http://120.79.43.16:80/group1/M00/00/00/rBL7oF9R3z-ADgVvAAEWrNvBxe8684.png\",\"createBy\":\"admin\",\"newsId\":5086,\"newsTypeId\":1,\"createTime\":1599201094510,\"newsAttr\":\"0\"}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 06:31:34');
INSERT INTO `sys_oper_log` VALUES (33, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeStatus()', 'PUT', 1, 'admin', '', '/news/news/change/status', '120.228.2.61', '湖南省长沙市 移通', '{\"newsContent\":\"<p>j jhj&nbsp;</p>\",\"newsSourceAuthor\":\"\",\"orderNum\":0,\"remark\":\"jh \",\"delFlag\":\"0\",\"visits\":0,\"newsSource\":\"\",\"updateBy\":\"\",\"newsTitle\":\"hgh \",\"isPublic\":\"0\",\"collects\":0,\"comments\":0,\"newsSourceTags\":\"\",\"updateTime\":1599201095000,\"params\":{},\"userId\":1,\"newsImage\":\"http://120.79.43.16:80/group1/M00/00/00/rBL7oF9R3z-ADgVvAAEWrNvBxe8684.png\",\"createBy\":\"admin\",\"newsId\":5086,\"newsTypeId\":1,\"createTime\":1599201095000,\"newsAttr\":\"0\",\"status\":\"1\",\"thumbs\":0}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 06:31:59');
INSERT INTO `sys_oper_log` VALUES (34, '新闻', 3, 'com.linq.web.controller.news.LinkNewsController.remove()', 'DELETE', 1, 'admin', '', '/news/news/5086', '111.75.189.148', '江西省宜春市 电信', '{newsIds=5086}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 06:36:53');
INSERT INTO `sys_oper_log` VALUES (35, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeIsPublic()', 'PUT', 1, 'admin', '', '/news/news/change/isPublic', '111.75.189.148', '江西省宜春市 电信', '{\"isPublic\":\"1\",\"params\":{},\"newsId\":5114}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 06:47:32');
INSERT INTO `sys_oper_log` VALUES (36, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeIsPublic()', 'PUT', 1, 'admin', '', '/news/news/change/isPublic', '111.75.189.148', '江西省宜春市 电信', '{\"isPublic\":\"0\",\"params\":{},\"newsId\":5114}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 06:47:34');
INSERT INTO `sys_oper_log` VALUES (37, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeIsPublic()', 'PUT', 1, 'admin', '', '/news/news/change/isPublic', '111.75.189.148', '江西省宜春市 电信', '{\"isPublic\":\"1\",\"params\":{},\"newsId\":5340}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 07:21:12');
INSERT INTO `sys_oper_log` VALUES (38, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeIsPublic()', 'PUT', 1, 'admin', '', '/news/news/change/isPublic', '111.75.189.148', '江西省宜春市 电信', '{\"isPublic\":\"0\",\"params\":{},\"newsId\":5340}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 07:21:13');
INSERT INTO `sys_oper_log` VALUES (39, '在线用户', 3, 'com.linq.web.controller.monitor.SysUserOnlineController.forceLogout()', 'DELETE', 1, '', '', '/monitor/online/b8652403-71f6-4733-9153-138067f973d7', '183.162.63.106', '安徽省合肥市 电信', '{tokenId=b8652403-71f6-4733-9153-138067f973d7}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 07:29:03');
INSERT INTO `sys_oper_log` VALUES (40, '新闻', 2, 'com.linq.web.controller.news.LinkNewsController.edit()', 'PUT', 1, 'admin', '', '/news/news', '111.75.189.148', '江西省宜春市 电信', '{\"newsContent\":\"<div class=\\\"article-content-left\\\"> \\n <!-- 正文广告top start --> \\n <div class=\\\"ad top-ad\\\"> \\n  <!--标题下方640*90通栏  17/09  wenjing  begin--> \\n  <ins class=\\\"sinaads\\\" data-ad-pdps=\\\"PDPS000000060748\\\"></ins> \\n  <script>(sinaads = window.sinaads || []).push({})</script> \\n  <!--标题下方640*90通栏  end--> \\n </div> \\n <!-- 正文广告top end --> \\n <!-- 引文 start --> \\n <div class=\\\"quotation\\\"> \\n  <span class=\\\"icon quotation-right\\\"></span> \\n  <p>袁咏琳透露刚开始不习惯真人秀24小时录影，曾发生在房间换衣服没发现隐藏镜头的糗事。</p> \\n  <span class=\\\"icon quotation-left\\\"></span> \\n </div> \\n <!-- 引文 end --> \\n <!-- 正文 start --> \\n <div class=\\\"article\\\" id=\\\"artibody\\\"> \\n  <script type=\\\"text/javascript\\\">\\n\\t\\t\\t\\t\\twindow.sina_survey_config = {\\n    \\t\\t\\t\\t\\tsurveyCss: true,               //调查问答样式true, false, http （不使用默认样式配置false或者不传此参数）\\n    \\t\\t\\t\\t\\tresultCss: true,               //调查结果样式true, false, http （不使用默认样式配置false或者不传此参数）\\n    \\t\\t\\t\\t\\tsource: \'vote\'               //通过来源设置图片宽高 sina(手浪), vote(默认)\\n\\t\\t\\t\\t\\t}\\n\\t\\t\\t\\t</script> \\n  <div class=\\\"img_wrapper\\\">\\n   <img src=\\\"//n.sinaimg.cn/ent/transform/218/w630h388/20200904/0f34-iytwsaz8690543.jpg\\\" alt=\\\"袁咏琳和经纪人\\\" data-link=\\\"\\\">\\n   <span class=\\\"img_descr\\\">袁咏琳和经纪人</span>\\n  </div> \\n  <p>　　新浪娱乐讯 9月4日，据台湾媒体报道，近日袁咏琳<a target=\\\"_blank\\\" href=\\\"http://weibo.com/u/1735695412?zw=ent\\\" onmouseover=\\\"WeiboCard.show(1735695412, \'ent\' , this)\\\" class=\\\"wt_article_link\\\">[微博]</a>在一访谈中与陪伴她超过10年的经纪人回忆成名路，袁咏琳坦言之前有想要离开演艺圈或转幕后做音乐的想法，曾向经纪人toto谈及这些想法，却看到从没哭过的toto眼眶泛泪。袁咏琳回忆过往忍不住落泪，感性感谢老板杨峻荣与toto一直相信她、从未放弃，不断告诉她“你的能力跟实力只是还没有对的时机被看见。”参加《乘风破浪的姐姐》后，袁咏琳的事业180度翻转，1个月拍的杂志比过去10年加总还多、活动商演邀约不断，忙到没时间好好吃饭睡觉，但自己与团队都乐在其中。</p> \\n  <p>　　谈到选秀节目的难忘趣事，袁咏琳透露刚开始不习惯真人秀24小时录影，曾发生在房间换衣服没发现隐藏镜头的糗事，直接站在镜头前换衣服，toto笑说当时听到吓死了，立刻找节目组冲去监播室删掉影像。袁咏琳还分享跟前辈宁静、阿朵同宿舍共用厕所，她表示：“其实我蛮保守”，但后来也习惯回宿舍大家在同个空间洗澡、卸妆、上厕所，“那时就感觉真的像女团，团魂从这培养”，让toto忍不住笑说：“团魂是从坦诚相见开始。”</p> \\n  <p>　　节目初期因担心袁咏琳遭淘汰，在第二次公演时，经纪人toto还祈求顺利过关，“发愿如果袁咏琳晋级就吃素1个月”，后来袁咏琳顺利晋级团队还去吃烧肉庆功，爱吃肉的toto在旁只能吃香菇豆腐，虽然很煎熬仍守约，让袁咏琳直呼感动。袁咏琳指出过去得失心重、给自己', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 08:34:44');
INSERT INTO `sys_oper_log` VALUES (41, '通知公告', 2, 'com.linq.web.controller.system.SysNoticeController.edit()', 'PUT', 1, 'admin', '', '/system/notice', '111.75.189.148', '江西省宜春市 电信', '{\"noticeType\":\"2\",\"remark\":\"\",\"updateTime\":1599208529788,\"params\":{},\"noticeId\":20,\"noticeTitle\":\" 澎湃新闻\",\"noticeContent\":\"<p><strong style=\\\"color: rgb(51, 51, 51);\\\">0 澎湃</strong><strong style=\\\"color: rgb(204, 0, 0);\\\">新闻</strong><strong style=\\\"color: rgb(51, 51, 51);\\\">&nbsp;咸宁高中10月23日发布对本校高三女生罗某的处罚通告.咸宁当地人士提供针对“女生恋爱引两男生冲突被罚抄规范百遍”一事,11月1日下午,湖...</strong></p>\",\"createBy\":\"admin\",\"createTime\":1598618549000,\"updateBy\":\"admin\",\"status\":\"0\"}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 08:35:29');
INSERT INTO `sys_oper_log` VALUES (42, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeIsPublic()', 'PUT', 1, 'admin', '', '/news/news/change/isPublic', '180.169.38.125', '上海市 电信', '{\"isPublic\":\"1\",\"params\":{},\"newsId\":5446}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 08:35:59');
INSERT INTO `sys_oper_log` VALUES (43, '用户头像', 2, 'com.linq.web.controller.system.SysProfileController.avatar()', 'POST', 1, 'admin', '', '/system/user/profile/avatar', '111.75.189.148', '江西省宜春市 电信', '', '{\"code\":200,\"data\":{\"imgUrl\":\"/profile/avatar/2020/09/04/6080c7af-b993-401b-b33d-d73feab2c8cd.jpeg\"},\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 08:36:22');
INSERT INTO `sys_oper_log` VALUES (44, '通知公告', 2, 'com.linq.web.controller.system.SysNoticeController.edit()', 'PUT', 1, 'admin', '', '/system/notice', '111.75.189.148', '江西省宜春市 电信', '{\"noticeType\":\"2\",\"remark\":\"\",\"updateTime\":1599208680508,\"params\":{},\"noticeId\":20,\"noticeTitle\":\" 澎湃新闻\",\"noticeContent\":\"<p><strong style=\\\"color: rgb(51, 51, 51);\\\">0 澎湃</strong><strong style=\\\"color: rgb(204, 0, 0);\\\">新闻</strong><strong style=\\\"color: rgb(51, 51, 51);\\\">&nbsp;咸宁高中10月23日发布对本校高三女生罗某的处罚通告.咸宁当地人士提供针对“女生恋爱引两男生冲突被罚抄规范百遍”一事,11月1日下午,湖...</strong><img src=\\\"http://120.79.43.16:1081/profile/upload/2020/09/04/e21b202a-2a25-4dca-b166-d2a9d0dacf2b.jpg\\\"></p>\",\"createBy\":\"admin\",\"createTime\":1598618549000,\"updateBy\":\"admin\",\"status\":\"0\"}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 08:38:00');
INSERT INTO `sys_oper_log` VALUES (45, '个人信息', 2, 'com.linq.web.controller.system.SysProfileController.updateProfile()', 'PUT', 1, 'admin', '', '/system/user/profile', '180.169.38.125', '上海市 电信', '{\"roles\":[{\"flag\":false,\"roleId\":1,\"admin\":true,\"dataScope\":\"1\",\"params\":{},\"roleSort\":1,\"roleKey\":\"admin\",\"roleName\":\"系统管理员\",\"status\":\"0\"}],\"admin\":true,\"loginDate\":1521199980000,\"remark\":\"管理员\",\"delFlag\":\"0\",\"password\":\"$2a$10$zAtEk0Ok/99LhFG.T9LG8OLo0ui.8UB7yF6CbupFFg7AbuNrlEyqu\",\"updateBy\":\"admin\",\"loginIp\":\"111.75.189.148\",\"email\":\"18061877017@163.com\",\"nickName\":\"林义清\",\"sex\":\"1\",\"deptId\":100,\"updateTime\":1599208950994,\"avatar\":\"/profile/avatar/2020/09/04/6080c7af-b993-401b-b33d-d73feab2c8cd.jpeg\",\"dept\":{\"deptName\":\"Linq新闻\",\"leader\":\"linq\",\"deptId\":100,\"orderNum\":0,\"params\":{},\"parentId\":0,\"children\":[],\"status\":\"0\"},\"params\":{},\"userId\":1,\"createBy\":\"admin\",\"createTime\":1598522011000,\"phone\":\"18061877017\",\"status\":\"0\",\"username\":\"admin\"}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 08:42:31');
INSERT INTO `sys_oper_log` VALUES (46, '个人信息', 2, 'com.linq.web.controller.system.SysProfileController.updateProfile()', 'PUT', 1, 'admin', '', '/system/user/profile', '180.169.38.125', '上海市 电信', '{\"roles\":[{\"flag\":false,\"roleId\":1,\"admin\":true,\"dataScope\":\"1\",\"params\":{},\"roleSort\":1,\"roleKey\":\"admin\",\"roleName\":\"系统管理员\",\"status\":\"0\"}],\"admin\":true,\"loginDate\":1521199980000,\"remark\":\"管理员\",\"delFlag\":\"0\",\"password\":\"$2a$10$zAtEk0Ok/99LhFG.T9LG8OLo0ui.8UB7yF6CbupFFg7AbuNrlEyqu\",\"updateBy\":\"admin\",\"loginIp\":\"111.75.189.148\",\"email\":\"18061877017@163.com\",\"nickName\":\"林义清\",\"sex\":\"1\",\"deptId\":100,\"updateTime\":1599208955623,\"avatar\":\"/profile/avatar/2020/09/04/6080c7af-b993-401b-b33d-d73feab2c8cd.jpeg\",\"dept\":{\"deptName\":\"Linq新闻\",\"leader\":\"linq\",\"deptId\":100,\"orderNum\":0,\"params\":{},\"parentId\":0,\"children\":[],\"status\":\"0\"},\"params\":{},\"userId\":1,\"createBy\":\"admin\",\"createTime\":1598522011000,\"phone\":\"18061877017\",\"status\":\"0\",\"username\":\"admin\"}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 08:42:35');
INSERT INTO `sys_oper_log` VALUES (47, '通知公告', 2, 'com.linq.web.controller.system.SysNoticeController.edit()', 'PUT', 1, 'admin', '', '/system/notice', '172.18.251.160', ' 局域网', '{\"noticeType\":\"2\",\"remark\":\"\",\"updateTime\":1599209164027,\"params\":{},\"noticeId\":20,\"noticeTitle\":\" 澎湃新闻\",\"noticeContent\":\"<p><strong style=\\\"color: rgb(51, 51, 51);\\\"><img src=\\\"http://120.79.43.16:80/group1/M00/00/00/rBL7oF9R_sqAY7wJAABCPtxygcM602.jpg\\\">0 澎湃</strong><strong style=\\\"color: rgb(204, 0, 0);\\\">新闻</strong><strong style=\\\"color: rgb(51, 51, 51);\\\">&nbsp;咸宁高中10月23日发布对本校高三女生罗某的处罚通告.咸宁当地人士提供针对“女生恋爱引两男生冲突被罚抄规范百遍”一事,11月1日下午,湖...</strong><img src=\\\"http://120.79.43.16:1081/profile/upload/2020/09/04/e21b202a-2a25-4dca-b166-d2a9d0dacf2b.jpg\\\"></p>\",\"createBy\":\"admin\",\"createTime\":1598618549000,\"updateBy\":\"admin\",\"status\":\"0\"}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 08:46:04');
INSERT INTO `sys_oper_log` VALUES (48, '通知公告', 2, 'com.linq.web.controller.system.SysNoticeController.edit()', 'PUT', 1, 'admin', '', '/system/notice', '111.75.189.148', '江西省宜春市 电信', '{\"noticeType\":\"2\",\"remark\":\"\",\"updateTime\":1599209510495,\"params\":{},\"noticeId\":20,\"noticeTitle\":\" 澎湃新闻\",\"noticeContent\":\"<p><img src=\\\"http://120.79.43.16:80/group1/M00/00/00/rBL7oF9SACWAMepiAABCPtxygcM722.jpg\\\"></p>\",\"createBy\":\"admin\",\"createTime\":1598618549000,\"updateBy\":\"admin\",\"status\":\"0\"}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 08:51:50');
INSERT INTO `sys_oper_log` VALUES (49, '新闻评论', 1, 'com.linq.web.controller.news.LinqCommentController.add()', 'POST', 1, 'admin', '', '/news/comment', '111.75.189.148', '江西省宜春市 电信', '{\"updateTime\":1599209613784,\"commentContent\":\"<iframe class=\\\"ql-video\\\" frameborder=\\\"0\\\" allowfullscreen=\\\"true\\\" src=\\\"https://v.qq.com/x/page/i08308eq8i4.html\\\"></iframe><p><br></p>\",\"params\":{},\"createBy\":\"admin\",\"newsId\":5087,\"createTime\":1599209613784,\"updateBy\":\"admin\",\"commentId\":42,\"thumbs\":2}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 08:53:33');
INSERT INTO `sys_oper_log` VALUES (50, '新闻', 1, 'com.linq.web.controller.news.LinkNewsController.add()', 'POST', 1, 'admin', '', '/news/news', '111.75.189.148', '江西省宜春市 电信', '{\"newsContent\":\"<p><video controls=\\\"controls\\\" width=\\\"300\\\" height=\\\"150\\\">\\n<source src=\\\"https://v.qq.com/x/page/q0897fvyjg9.html\\\" /></video></p>\",\"orderNum\":0,\"newsTitle\":\"视频你值得拥有\",\"isPublic\":\"0\",\"updateTime\":1599209712637,\"params\":{},\"userId\":1,\"newsImage\":\"http://120.79.43.16:80/group1/M00/00/00/rBL7oF9SAOCAYW6TAABCPtxygcM205.jpg\",\"createBy\":\"admin\",\"newsId\":5447,\"newsTypeId\":5,\"createTime\":1599209712637,\"newsAttr\":\"0\"}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 08:55:12');
INSERT INTO `sys_oper_log` VALUES (51, '新闻', 2, 'com.linq.web.controller.news.LinkNewsController.edit()', 'PUT', 1, 'admin', '', '/news/news', '111.75.189.148', '江西省宜春市 电信', '{\"newsContent\":\"<p><video style=\\\"display: block; margin-left: auto; margin-right: auto;\\\" controls=\\\"controls\\\" width=\\\"600\\\" height=\\\"300\\\">\\n<source src=\\\"https://v.qq.com/x/page/q0897fvyjg9.html\\\" /></video></p>\",\"newsSourceAuthor\":\"\",\"orderNum\":0,\"remark\":\"\",\"delFlag\":\"0\",\"visits\":0,\"newsSource\":\"\",\"updateBy\":\"admin\",\"newsTitle\":\"视频你值得拥有\",\"isPublic\":\"0\",\"collects\":0,\"comments\":0,\"newsSourceTags\":\"\",\"updateTime\":1599209791553,\"params\":{},\"userId\":1,\"newsImage\":\"http://120.79.43.16:80/group1/M00/00/00/rBL7oF9SAOCAYW6TAABCPtxygcM205.jpg\",\"createBy\":\"admin\",\"newsId\":5447,\"newsTypeId\":5,\"createTime\":1599209713000,\"newsAttr\":\"0\",\"status\":\"0\",\"thumbs\":0}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 08:56:31');
INSERT INTO `sys_oper_log` VALUES (52, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeIsPublic()', 'PUT', 1, 'admin', '', '/news/news/change/isPublic', '171.34.71.68', '江西省南昌市 联通', '{\"isPublic\":\"1\",\"params\":{},\"newsId\":5480}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 09:03:33');
INSERT INTO `sys_oper_log` VALUES (53, '新闻', 1, 'com.linq.web.controller.news.LinkNewsController.add()', 'POST', 1, 'admin', '', '/news/news', '111.75.189.148', '江西省宜春市 电信', '{\"newsContent\":\"<p><video style=\\\"display: block; margin-left: auto; margin-right: auto;\\\" controls=\\\"controls\\\" width=\\\"1000\\\" height=\\\"500\\\">\\n<source src=\\\"https://ugcsjy.qq.com/uwMROfz2r57BIaQXGdGnC2deB3dUr_IkisDvBob9yrMGODlR/szg_87720880_50001_007d90b6e5074153b6e9a2804f1e382c.f622.mp4?sdtfrom=v1010&amp;guid=063bbce471d0f400fd3d74a11325a3a5&amp;vkey=1291E61367272535799624716297FE2884A52C2090ECA265C8EBF52FBC390B445AAD9B2512E147FDE57B4A6F4ADC5BD13528E85028083A7FBD13730BA730A45884050A799D30032533346B2CC4F45B31C88545EC5EA3E01D51A5551381C896CED92C0F99D994CE9421711907B26EA750950A2AB8A389DE633BB05FA8F668950B#t=277\\\" /></video></p>\\n<p><strong>你值得拥有的世界,你明白的</strong></p>\",\"orderNum\":0,\"newsTitle\":\"你值得拥有的世界,你明白的\",\"isPublic\":\"0\",\"updateTime\":1599212141466,\"params\":{},\"userId\":1,\"newsImage\":\"http://120.79.43.16:80/group1/M00/00/00/rBL7oF9SCmuAODBjAABCPtxygcM013.jpg\",\"createBy\":\"admin\",\"newsId\":5481,\"newsTypeId\":6,\"createTime\":1599212141466,\"newsAttr\":\"0\"}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 09:35:41');
INSERT INTO `sys_oper_log` VALUES (54, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeIsPublic()', 'PUT', 1, 'admin', '', '/news/news/change/isPublic', '111.75.189.148', '江西省宜春市 电信', '{\"isPublic\":\"1\",\"params\":{},\"newsId\":5447}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 09:44:00');
INSERT INTO `sys_oper_log` VALUES (55, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeIsPublic()', 'PUT', 1, 'admin', '', '/news/news/change/isPublic', '111.75.189.148', '江西省宜春市 电信', '{\"isPublic\":\"0\",\"params\":{},\"newsId\":5447}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 09:44:02');
INSERT INTO `sys_oper_log` VALUES (56, '新闻管理', 2, 'com.linq.web.controller.news.LinkNewsController.changeStatus()', 'PUT', 1, 'admin', '', '/news/news/change/status', '111.75.189.148', '江西省宜春市 电信', '{\"newsContent\":\"<p><video style=\\\"display: block; margin-left: auto; margin-right: auto;\\\" controls=\\\"controls\\\" width=\\\"1000\\\" height=\\\"500\\\">\\n<source src=\\\"https://ugcsjy.qq.com/uwMROfz2r57BIaQXGdGnC2deB3dUr_IkisDvBob9yrMGODlR/szg_87720880_50001_007d90b6e5074153b6e9a2804f1e382c.f622.mp4?sdtfrom=v1010&amp;guid=063bbce471d0f400fd3d74a11325a3a5&amp;vkey=1291E61367272535799624716297FE2884A52C2090ECA265C8EBF52FBC390B445AAD9B2512E147FDE57B4A6F4ADC5BD13528E85028083A7FBD13730BA730A45884050A799D30032533346B2CC4F45B31C88545EC5EA3E01D51A5551381C896CED92C0F99D994CE9421711907B26EA750950A2AB8A389DE633BB05FA8F668950B#t=277\\\" /></video></p>\\n<p><strong>你值得拥有的世界,你明白的</strong></p>\",\"newsSourceAuthor\":\"\",\"orderNum\":0,\"remark\":\"\",\"delFlag\":\"0\",\"visits\":0,\"newsSource\":\"\",\"updateBy\":\"\",\"newsTitle\":\"你值得拥有的世界,你明白的\",\"isPublic\":\"0\",\"collects\":0,\"comments\":0,\"newsSourceTags\":\"\",\"updateTime\":1599212141000,\"params\":{},\"userId\":1,\"newsImage\":\"http://120.79.43.16:80/group1/M00/00/00/rBL7oF9SCmuAODBjAABCPtxygcM013.jpg\",\"createBy\":\"admin\",\"newsId\":5481,\"newsTypeId\":6,\"createTime\":1599212141000,\"newsAttr\":\"0\",\"status\":\"1\",\"thumbs\":0}', '{\"code\":200,\"flag\":true,\"msg\":\"操作成功\"}', 0, '', '2020-09-04 09:48:07');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(511) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '系统管理员', 'admin', 1, '1', '0', '0', 'admin', '2020-08-28 12:27:52', 'linq', '2020-08-28 12:27:52', '系统管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 6, '3', '0', '0', 'admin', '2020-08-28 12:27:52', 'admin', '2020-09-04 04:30:47', '普通角色');
INSERT INTO `sys_role` VALUES (110, '游客', 'passers-by', 3, '1', '0', '0', 'admin', '2020-08-29 11:15:32', 'admin', '2020-09-04 05:00:04', '');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和部门关联表';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_dept` VALUES (10, 100, 104);
INSERT INTO `sys_role_dept` VALUES (11, 100, 100);
INSERT INTO `sys_role_dept` VALUES (12, 100, 101);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=193 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (123, 2, 100);
INSERT INTO `sys_role_menu` VALUES (124, 2, 1);
INSERT INTO `sys_role_menu` VALUES (130, 109, 2014);
INSERT INTO `sys_role_menu` VALUES (131, 109, 2019);
INSERT INTO `sys_role_menu` VALUES (132, 109, 1);
INSERT INTO `sys_role_menu` VALUES (133, 109, 100);
INSERT INTO `sys_role_menu` VALUES (134, 109, 101);
INSERT INTO `sys_role_menu` VALUES (155, 110, 2014);
INSERT INTO `sys_role_menu` VALUES (156, 110, 2019);
INSERT INTO `sys_role_menu` VALUES (157, 110, 2023);
INSERT INTO `sys_role_menu` VALUES (158, 110, 2027);
INSERT INTO `sys_role_menu` VALUES (159, 110, 2031);
INSERT INTO `sys_role_menu` VALUES (160, 110, 2033);
INSERT INTO `sys_role_menu` VALUES (161, 110, 2037);
INSERT INTO `sys_role_menu` VALUES (162, 110, 2041);
INSERT INTO `sys_role_menu` VALUES (163, 110, 2046);
INSERT INTO `sys_role_menu` VALUES (164, 110, 2004);
INSERT INTO `sys_role_menu` VALUES (165, 110, 2005);
INSERT INTO `sys_role_menu` VALUES (166, 110, 2012);
INSERT INTO `sys_role_menu` VALUES (167, 110, 2095);
INSERT INTO `sys_role_menu` VALUES (168, 110, 2011);
INSERT INTO `sys_role_menu` VALUES (169, 110, 2096);
INSERT INTO `sys_role_menu` VALUES (170, 110, 2097);
INSERT INTO `sys_role_menu` VALUES (171, 110, 2013);
INSERT INTO `sys_role_menu` VALUES (172, 110, 2063);
INSERT INTO `sys_role_menu` VALUES (173, 110, 2056);
INSERT INTO `sys_role_menu` VALUES (174, 110, 2091);
INSERT INTO `sys_role_menu` VALUES (175, 110, 2085);
INSERT INTO `sys_role_menu` VALUES (176, 110, 1);
INSERT INTO `sys_role_menu` VALUES (177, 110, 100);
INSERT INTO `sys_role_menu` VALUES (178, 110, 101);
INSERT INTO `sys_role_menu` VALUES (179, 110, 102);
INSERT INTO `sys_role_menu` VALUES (180, 110, 103);
INSERT INTO `sys_role_menu` VALUES (181, 110, 2006);
INSERT INTO `sys_role_menu` VALUES (182, 110, 2007);
INSERT INTO `sys_role_menu` VALUES (183, 110, 2008);
INSERT INTO `sys_role_menu` VALUES (184, 110, 2035);
INSERT INTO `sys_role_menu` VALUES (185, 110, 2036);
INSERT INTO `sys_role_menu` VALUES (186, 110, 2045);
INSERT INTO `sys_role_menu` VALUES (187, 110, 2009);
INSERT INTO `sys_role_menu` VALUES (188, 110, 2061);
INSERT INTO `sys_role_menu` VALUES (189, 110, 2062);
INSERT INTO `sys_role_menu` VALUES (190, 110, 2050);
INSERT INTO `sys_role_menu` VALUES (191, 110, 2090);
INSERT INTO `sys_role_menu` VALUES (192, 110, 2084);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `username` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '用户邮箱',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '头像地址',
  `password` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(511) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 100, 'admin', '林义清', '00', '18061877017@163.com', '18061877017', '0', '/profile/avatar/2020/09/04/6080c7af-b993-401b-b33d-d73feab2c8cd.jpeg', '$2a$10$zAtEk0Ok/99LhFG.T9LG8OLo0ui.8UB7yF6CbupFFg7AbuNrlEyqu', '0', '0', '180.116.142.126', '2018-03-16 11:33:00', 'admin', '2020-08-27 09:53:31', 'admin', '2020-09-08 06:40:12', '管理员');
INSERT INTO `sys_user` VALUES (14, 204, 'demo', '测试角色', '00', '9@99.com', '19999999990', '1', '/profile/avatar/2020/09/04/819d44b5-ac28-4fd7-b6a2-b03bfe3f20e5.jpeg', '$2a$10$PTe73CwSK14H/3LVJbiqzO/hzAPakc2LKsRtnDheRJyTN7qC.IGBC', '0', '0', '42.92.154.193', NULL, 'admin', '2020-08-28 12:47:54', 'demo', '2020-09-04 07:20:28', '测试使用');
INSERT INTO `sys_user` VALUES (15, 203, 'lyq', '测试角色', '00', '9@sss.cm', '15622133113', '2', '/profile/avatar/2020/09/04/819d44b5-ac28-4fd7-b6a2-b03bfe3f20e5.jpeg', '$2a$10$Wneq0HYerNq7tlnOQL2rxu9xwX5X7V5bt1EukMRuqJNhIAMv6gJY6', '0', '0', '127.0.0.1', NULL, 'admin', '2020-08-28 15:34:52', 'lyq', '2020-08-30 18:57:50', '测试角色');
INSERT INTO `sys_user` VALUES (16, 204, 'gjh', '二哥', '00', '2560295278@qq.com', '13979256550', '1', '', '$2a$10$yhhrQhoFuqR1eg8j6XAkZ.pxmX64Gay0c6Ps5cheEgs3iwDnxXmgG', '0', '2', '', NULL, 'admin', '2020-09-04 05:09:27', 'admin', '2020-09-04 05:09:27', '');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户和角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2, 2);
INSERT INTO `sys_user_role` VALUES (29, 15, 110);
INSERT INTO `sys_user_role` VALUES (30, 14, 110);
INSERT INTO `sys_user_role` VALUES (31, 16, 110);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
