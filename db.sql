--
-- Create Database
--
CREATE DATABASE IF NOT EXISTS `ementoring` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `ementoring`;

--
-- Create Users Table
--
CREATE TABLE `Users` (
	`name` VARCHAR(100) NOT NULL DEFAULT '',
	`email` VARCHAR(500) NOT NULL,
	`password` VARCHAR(500) NOT NULL DEFAULT '',
	`role` VARCHAR(500) NOT NULL DEFAULT '',
	`UserDesc` VARCHAR(500) NOT NULL DEFAULT '',
	PRIMARY KEY (`email`)
);


-- 
-- Create Questions Table
--
CREATE TABLE `Questions` (
	`qid` INT NOT NULL AUTO_INCREMENT,
	`userEmail` VARCHAR(500) NOT NULL DEFAULT '',
	`question` VARCHAR(2000) NOT NULL DEFAULT '',
	`createdAt` DATETIME,
	`claps` INT(32) unsigned zerofill NOT NULL DEFAULT '0',
	PRIMARY KEY (`qid`)
);

-- 
-- Create Answers Table
--
CREATE TABLE `Answers` (
	`aid` INT NOT NULL AUTO_INCREMENT,
	`answer` VARCHAR(10000) NOT NULL DEFAULT '',
	`qid` INT NOT NULL DEFAULT '-1',
	`writerEmail` VARCHAR(100) NOT NULL DEFAULT '',
	`writtenAt` DATETIME,
	PRIMARY KEY (`aid`)
);

--
-- Create Connections Table
--
CREATE TABLE `Connections` (
	`senderEmail` VARCHAR(1000) NOT NULL DEFAULT '',
	`senderName` VARCHAR(1000) NOT NULL DEFAULT '',
	`senderRole` VARCHAR(50) NOT NULL DEFAULT '',
	`senderDesc` VARCHAR(1000) NOT NULL DEFAULT '',
	`receiverEmail` VARCHAR(1000) NOT NULL DEFAULT '',
	`receiverName` VARCHAR(1000) NOT NULL DEFAULT '',
	`receiverRole` VARCHAR(50) NOT NULL DEFAULT '',
	`receiverDesc` VARCHAR(1000) NOT NULL DEFAULT '',
	`status` VARCHAR(1000) NOT NULL DEFAULT 'unknown',
	PRIMARY KEY (`senderEmail`,`receiverEmail`)
);

--
-- Create Table Messages
--
CREATE TABLE `Messages` (
	`mid` INT NOT NULL AUTO_INCREMENT,
	`sentBy` VARCHAR(2000) NOT NULL DEFAULT '',
	`message` VARCHAR(2000) NOT NULL DEFAULT '',
	`sentTo` VARCHAR(2000) DEFAULT '',
	PRIMARY KEY (`mid`)
);

--
-- Create University Table
--
CREATE TABLE `Universities` (
	`uid` INT NOT NULL AUTO_INCREMENT,
	`uname` VARCHAR(2000) NOT NULL DEFAULT '',
	PRIMARY KEY (`uid`)
);

--
-- Create Urefers table
--
CREATE TABLE `Urefers` (
	`rid` INT NOT NULL AUTO_INCREMENT,
	`uid` INT NOT NULL DEFAULT 0,
	`referTo` VARCHAR(100) NOT NULL,
	`referByEmail` VARCHAR(100) NOT NULL,
	`referByName` VARCHAR(100) NOT NULL DEFAULT '',
	PRIMARY KEY (`rid`)
);

