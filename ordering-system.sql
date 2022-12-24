/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : ordering-system

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 24/12/2022 17:21:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `billId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `menuId` int NULL DEFAULT 0,
  `nums` int NULL DEFAULT 0,
  `money` double(10, 2) NULL DEFAULT NULL,
  `diningTableId` int NULL DEFAULT 0,
  `billDate` datetime NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bill
-- ----------------------------
INSERT INTO `bill` VALUES (1, 'ff4667df-7a6a-4a0d-8917-b99df242335a', 1, 2, 34.00, 1, '2022-12-07 14:57:44', 'Cash');
INSERT INTO `bill` VALUES (2, 'bbe21089-08ce-485d-996c-1516affcf4fb', 2, 1, 17.00, 1, '2022-12-07 15:12:53', 'Cash');
INSERT INTO `bill` VALUES (3, 'c871504c-d160-4f66-8d80-9a1e5ef7d07c', 1, 1, 17.00, 1, '2022-12-17 15:59:57', 'Wechat');
INSERT INTO `bill` VALUES (4, 'c9fb3e39-bf2e-4fd9-bc33-29520f910df6', 6, 2, 24.00, 2, '2022-12-17 17:37:58', 'Alipay');
INSERT INTO `bill` VALUES (5, 'b5557e52-e432-40f4-aa12-05ad2b2014c2', 8, 1, 18.00, 2, '2022-12-17 17:43:20', 'Alipay');
INSERT INTO `bill` VALUES (6, 'eb5eb755-50a1-4a86-9bb3-1b48a2fa2608', 3, 1, 6.00, 1, '2022-12-17 18:40:08', 'Wechat');
INSERT INTO `bill` VALUES (7, '5e922512-31c1-42da-b196-9c2969dc1f10', 4, 1, 6.00, 1, '2022-12-17 18:40:12', 'Wechat');
INSERT INTO `bill` VALUES (8, 'f27c0090-bcff-406a-8a2b-e82e5b9b4609', 1, 1, 17.00, 1, '2022-12-17 18:43:54', 'Cash');
INSERT INTO `bill` VALUES (9, '4efd06b3-220e-4766-a2e9-666426843d54', 2, 1, 17.00, 4, '2022-12-17 18:47:05', 'Wechat');

-- ----------------------------
-- Table structure for diningtable
-- ----------------------------
DROP TABLE IF EXISTS `diningtable`;
CREATE TABLE `diningtable`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `state` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `orderName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `orderTel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of diningtable
-- ----------------------------
INSERT INTO `diningtable` VALUES (1, 'empty', '', '');
INSERT INTO `diningtable` VALUES (2, 'empty', '', '');
INSERT INTO `diningtable` VALUES (3, 'empty', '', '');
INSERT INTO `diningtable` VALUES (4, 'empty', '', '');
INSERT INTO `diningtable` VALUES (5, 'empty', NULL, NULL);
INSERT INTO `diningtable` VALUES (6, 'empty', NULL, NULL);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `empId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pwd` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `job` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `empId`(`empId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, '101', '123', 'tom', 'manager');
INSERT INTO `employee` VALUES (2, '102', '123', 'jack', 'waiter');
INSERT INTO `employee` VALUES (3, '103', '123', 'mary', 'waitress');
INSERT INTO `employee` VALUES (4, '104', '123', 'mike', 'manager');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` double(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '瓦香鸡', '热菜', 17.00);
INSERT INTO `menu` VALUES (2, '瓦香酥肉', '热菜', 17.00);
INSERT INTO `menu` VALUES (3, '葱油拌面', '主食', 6.00);
INSERT INTO `menu` VALUES (4, '花生酱面', '主食', 6.00);
INSERT INTO `menu` VALUES (5, '酸菜鱼', '汤类', 18.00);
INSERT INTO `menu` VALUES (6, '烤全腿', '热菜', 12.00);
INSERT INTO `menu` VALUES (7, '凉面/皮', '凉菜', 7.00);
INSERT INTO `menu` VALUES (8, '北京烤鸭', '热菜', 18.00);

SET FOREIGN_KEY_CHECKS = 1;
