/*
 Navicat Premium Data Transfer

 Source Server         : 我的数据库
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : bookstore

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 12/11/2019 20:56:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books`  (
  `BookID` int(5) NOT NULL AUTO_INCREMENT,
  `BookName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ImgPath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CategoryID` char(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Author` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Press` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Price` float(7, 2) NOT NULL,
  `Isbn` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`BookID`) USING BTREE,
  INDEX `CategoryID`(`CategoryID`) USING BTREE,
  CONSTRAINT `books_ibfk_1` FOREIGN KEY (`CategoryID`) REFERENCES `categories` (`CategoryID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10023 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES (10001, '爱在原野尽头', 'img/10001.jpg', 'xs001', '渡边淳一', '青岛出版社', 26.70, '9787555263654');
INSERT INTO `books` VALUES (10002, '雪落香杉树', 'img/10002.jpg', 'xs001', '戴维·伽特森', '作家出版社', 43.30, '9787506387873');
INSERT INTO `books` VALUES (10003, '小王子', 'img/10003.jpg', 'xs001', '安东尼', '湖南文艺出版社', 29.60, '9787540489021');
INSERT INTO `books` VALUES (10004, '大冰“江湖故事”系列', 'img/10004.jpg', 'xs001', '大冰', '湖南文艺出版社', 159.70, '25347907');
INSERT INTO `books` VALUES (10005, '失乐园', 'img/10005.jpg', 'xs001', '渡边淳一', '青岛出版社', 22.50, '9787555257035');
INSERT INTO `books` VALUES (10006, '东野圭吾作品：假面', 'img/10006.jpg', 'xs001', '东野圭吾', '南海出版公司', 108.00, '25286485');
INSERT INTO `books` VALUES (10007, '张嘉佳签名作品集', 'img/10007.jpg', 'xs001', '张嘉佳', '湖南文艺出版社', 65.00, '25347906');
INSERT INTO `books` VALUES (10008, '通宵小说大师肯·福', 'img/10008.jpg', 'xs001', '肯·福莱特', '江苏文艺出版社', 309.50, '9787539989891');
INSERT INTO `books` VALUES (10009, '唐宫奇案.上', 'img/10009.jpg', 'xs001', '森林鹿', '江苏凤凰文艺出版社', 34.50, '9787559428578');
INSERT INTO `books` VALUES (10010, '祖与占：夏日之恋', 'img/10010.jpg', 'xs001', '亨利—皮埃尔', '江苏凤凰文艺出版社', 32.30, '9787559428035');
INSERT INTO `books` VALUES (10012, '月亮与六便士', NULL, 'xs001', '毛姆', '浙江文艺出版社', 39.80, '9787533936020');
INSERT INTO `books` VALUES (10013, '百年孤独', NULL, 'xs001', '加西亚·马尔克斯', '南海出版公司', 53.90, '9787544291170');
INSERT INTO `books` VALUES (10014, '活着', NULL, 'xs001', '余华', '作家出版社', 28.00, '9787506365437');
INSERT INTO `books` VALUES (10015, 'Python编程 从入门到实践', NULL, 'kx001', '埃里克·马瑟斯', '人民邮电出版社', 89.00, '9787115428028');
INSERT INTO `books` VALUES (10016, '墨菲定律', NULL, 'kx001', '张文成', '古吴轩出版社', 20.50, '9787554609491');
INSERT INTO `books` VALUES (10017, '数学之美', NULL, 'kx001', '吴军', '人民邮电出版社', 41.10, '9787115373557');
INSERT INTO `books` VALUES (10018, '鬼谷子', NULL, 'wx001', '鬼谷子', '吉林美术出版社', 25.20, '9787538693010');
INSERT INTO `books` VALUES (10019, '观山海', NULL, 'wx001', '杉泽 ', '湖南文艺出版社', 149.50, '9787540485696');
INSERT INTO `books` VALUES (10020, '明朝那些事儿', NULL, 'wx001', '当年明月 ', '北京联合出版有限公司', 240.00, '9787559602152');
INSERT INTO `books` VALUES (10022, '历史书', 'img/d7df536981244e318b37eccd75af75a8.jpg', 'kx001', '作者', '出版社', 19.12, '123123');

-- ----------------------------
-- Table structure for categories
-- ----------------------------
DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories`  (
  `CategoryID` char(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CategoryName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`CategoryID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of categories
-- ----------------------------
INSERT INTO `categories` VALUES ('kx001', '科学');
INSERT INTO `categories` VALUES ('ls001', '历史');
INSERT INTO `categories` VALUES ('wx001', '文学');
INSERT INTO `categories` VALUES ('xs001', '小说');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `UserID` int(5) NOT NULL AUTO_INCREMENT,
  `Name` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Gender` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Age` int(3) NOT NULL,
  `UserName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Admin` bit(1) NULL DEFAULT NULL,
  `Phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Adress` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`UserID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10036 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (10001, '张三', '男', 18, 'zhangsan', '1234', b'0', '18767859876', '武汉软件工程职业学院');
INSERT INTO `users` VALUES (10002, '李四', '女', 22, 'lisi', 'lisi123', b'0', '17689876789', '武汉');
INSERT INTO `users` VALUES (10035, '马安程', '男', 20, 'admin', 'admin', b'1', '1771278957', '武汉软件工程职业学院');

SET FOREIGN_KEY_CHECKS = 1;
