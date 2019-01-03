/*
 Navicat Premium Data Transfer

 Source Server         : Local_MySQL
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : myim

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 01/01/2019 21:43:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for contact
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact`  (
  `contactId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `friendId` int(11) NOT NULL,
  `remark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `becomeFriendDate` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `becomeFriendWay` int(11) NOT NULL,
  PRIMARY KEY (`contactId`) USING BTREE,
  INDEX `user`(`userId`) USING BTREE,
  INDEX `friend`(`friendId`) USING BTREE,
  CONSTRAINT `friend` FOREIGN KEY (`friendId`) REFERENCES `user` (`usersystemid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user` FOREIGN KEY (`userId`) REFERENCES `user` (`usersystemid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of contact
-- ----------------------------
INSERT INTO `contact` VALUES (1, 1, 2, '', '2018-12-31 00:42:06', 1);
INSERT INTO `contact` VALUES (2, 2, 1, '', '2018-12-31 00:42:10', 1);

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`  (
  `groupId` int(11) NOT NULL AUTO_INCREMENT,
  `groupName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createDate` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `ownerId` int(11) NOT NULL,
  `groupPost` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `groupInfo` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `groupAvatarTODO` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`groupId`) USING BTREE,
  INDEX `ownerId`(`ownerId`) USING BTREE,
  CONSTRAINT `ownerId` FOREIGN KEY (`ownerId`) REFERENCES `user` (`usersystemid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_message
-- ----------------------------
DROP TABLE IF EXISTS `group_message`;
CREATE TABLE `group_message`  (
  `messageId` int(11) NOT NULL,
  `groupId` int(11) NOT NULL,
  `isCalledBack` tinyint(4) NOT NULL,
  `sendTime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `messageContent` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ImagePath` varchar(600) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`messageId`) USING BTREE,
  INDEX `messageGroupId`(`groupId`) USING BTREE,
  CONSTRAINT `messageGroupId` FOREIGN KEY (`groupId`) REFERENCES `group` (`groupid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `messageId` FOREIGN KEY (`messageId`) REFERENCES `message_info` (`messageid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_user_info
-- ----------------------------
DROP TABLE IF EXISTS `group_user_info`;
CREATE TABLE `group_user_info`  (
  `groupId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `userRight` int(11) NOT NULL,
  `userCustomGroupNickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enterDate` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`groupId`, `userId`) USING BTREE,
  INDEX `userRight`(`userRight`) USING BTREE,
  INDEX `userId`(`userId`) USING BTREE,
  CONSTRAINT `groupId` FOREIGN KEY (`groupId`) REFERENCES `group` (`groupid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `user` (`usersystemid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message_info
-- ----------------------------
DROP TABLE IF EXISTS `message_info`;
CREATE TABLE `message_info`  (
  `messageId` int(11) NOT NULL AUTO_INCREMENT,
  `messageType` int(11) NULL DEFAULT NULL,
  `isCalledBack` int(11) NULL DEFAULT NULL,
  `isImage` tinyint(4) NULL DEFAULT NULL,
  `senderId` int(11) NOT NULL,
  `receiverId` int(11) NOT NULL,
  `messageStatus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`messageId`) USING BTREE,
  INDEX `messageSenderId`(`senderId`) USING BTREE,
  INDEX `messageReceiverId`(`receiverId`) USING BTREE,
  INDEX `messageId`(`messageId`) USING BTREE,
  INDEX `messageId_2`(`messageId`) USING BTREE,
  INDEX `messageId_3`(`messageId`) USING BTREE,
  CONSTRAINT `messageReceiverId` FOREIGN KEY (`receiverId`) REFERENCES `user` (`usersystemid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `messageSenderId` FOREIGN KEY (`senderId`) REFERENCES `user` (`usersystemid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message_info
-- ----------------------------
INSERT INTO `message_info` VALUES (1, 1, 0, 0, 1, 2, 'IN_SERVER');
INSERT INTO `message_info` VALUES (2, 1, 0, 0, 2, 1, 'DELIVERED');

-- ----------------------------
-- Table structure for normal_user_message
-- ----------------------------
DROP TABLE IF EXISTS `normal_user_message`;
CREATE TABLE `normal_user_message`  (
  `messageId` int(11) NOT NULL,
  `readTime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `sendTime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `messageContent` varchar(600) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `imagePath` varchar(600) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`messageId`) USING BTREE,
  UNIQUE INDEX `unique_message_id`(`messageId`) USING BTREE,
  CONSTRAINT `normal_messageId` FOREIGN KEY (`messageId`) REFERENCES `message_info` (`messageid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of normal_user_message
-- ----------------------------
INSERT INTO `normal_user_message` VALUES (1, '2018-12-24 13:10:03', '2018-12-19 13:10:05', '你好，我是xx', NULL);
INSERT INTO `normal_user_message` VALUES (2, '2018-12-24 13:11:00', '2018-12-22 13:11:02', '我不好。', NULL);

-- ----------------------------
-- Table structure for p2p_user_message
-- ----------------------------
DROP TABLE IF EXISTS `p2p_user_message`;
CREATE TABLE `p2p_user_message`  (
  `senderId` int(11) NOT NULL,
  `receiverId` int(11) NOT NULL,
  PRIMARY KEY (`senderId`, `receiverId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userSystemId` int(20) NOT NULL AUTO_INCREMENT,
  `userTel` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userMail` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `userCustomID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `registrationDate` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `userPassword` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userNickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userCustomInfo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userBirthday` date NULL DEFAULT NULL,
  `userAvatarPath` varchar(600) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isEnable` tinyint(4) NOT NULL,
  PRIMARY KEY (`userSystemId`) USING BTREE,
  UNIQUE INDEX `userCustomID`(`userCustomID`) USING BTREE,
  UNIQUE INDEX `userTel`(`userTel`) USING BTREE,
  INDEX `userSystemId`(`userSystemId`) USING BTREE,
  INDEX `userSystemId_2`(`userSystemId`) USING BTREE,
  INDEX `userSystemId_3`(`userSystemId`) USING BTREE,
  INDEX `userSystemId_4`(`userSystemId`) USING BTREE,
  INDEX `userSystemId_5`(`userSystemId`) USING BTREE,
  INDEX `userSystemId_6`(`userSystemId`) USING BTREE,
  INDEX `userSystemId_7`(`userSystemId`) USING BTREE,
  INDEX `userSystemId_8`(`userSystemId`) USING BTREE,
  INDEX `userSystemId_9`(`userSystemId`) USING BTREE,
  INDEX `userSystemId_10`(`userSystemId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 71 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '18573105923', 'tblsyx@outlook.com', 'bolitao', '男', '2018-12-24 13:02:09', '7d057bd8c08467f96c5fa3d503d90e4e', 'Boli Tao', 'rua', '2018-12-24', NULL, 1);
INSERT INTO `user` VALUES (2, '12345', 'test@mail.com', 'test', '女', '2018-12-28 20:34:07', '098f6bcd4621d373cade4e832627b4f6', 'Test Account', 'test account', '2018-12-24', NULL, 1);
INSERT INTO `user` VALUES (47, NULL, '', 'bolit', NULL, '2018-12-30 10:22:19', 'abd513c7c8d78e3ecddb496227098eaa', NULL, NULL, NULL, NULL, 1);
INSERT INTO `user` VALUES (66, NULL, '', 'test1', NULL, '2018-12-30 10:25:57', '202cb962ac59075b964b07152d234b70', NULL, NULL, NULL, NULL, 1);
INSERT INTO `user` VALUES (67, NULL, '', 'hello', NULL, '2018-12-30 11:29:24', '5d41402abc4b2a76b9719d911017c592', NULL, NULL, NULL, NULL, 1);
INSERT INTO `user` VALUES (68, NULL, '', 'testhhg', NULL, '2018-12-30 11:52:07', '2072f3818493604fc6e6a08cd749b477', NULL, NULL, NULL, NULL, 1);
INSERT INTO `user` VALUES (69, NULL, '', 'jjfgbnjkk', NULL, '2018-12-30 11:58:07', '848fe2418c7bde3b160473ec359956d3', NULL, NULL, NULL, NULL, 1);
INSERT INTO `user` VALUES (70, NULL, '', 'jnfghjk', NULL, '2018-12-31 00:54:52', '5fd9beda973645fc9141ae3b36578bed', NULL, NULL, NULL, NULL, 1);

SET FOREIGN_KEY_CHECKS = 1;
