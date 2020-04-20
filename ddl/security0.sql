/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : security0

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-04-20 16:23:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for authorities
-- ----------------------------
DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `ix_auth_username` (`username`,`authority`),
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authorities
-- ----------------------------
INSERT INTO `authorities` VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO `authorities` VALUES ('user', 'ROLE_USER');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `PASSWORD` varchar(500) NOT NULL,
  `enabled` int(11) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('admin', '$2a$10$e3SFH9ZbwdS36kJBmBWU..aLHMcaxPe6cIGJsZwVbJY2f5Fqstqz6', '1');
INSERT INTO `users` VALUES ('user', '$2a$10$6QCLSw10NJyVYncz9FHKBuNOwx/qUBNos0GHGxH00HNO14gHjsi3m', '1');
