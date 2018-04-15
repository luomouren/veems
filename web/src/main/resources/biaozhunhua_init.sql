/*
SQLyog Professional v12.08 (64 bit)
MySQL - 5.7.17 : Database - biaozhunhua
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`biaozhunhua` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `biaozhunhua`;

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `LOG_ID` varchar(36) NOT NULL,
  `USER_ID` varchar(36) DEFAULT NULL,
  `OPERATION_NAME` varchar(30) NOT NULL,
  `LOG_TIME` datetime NOT NULL,
  `RESULT` tinyint(1) DEFAULT NULL,
  `RESULT_DESCRIPTION` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`LOG_ID`),
  KEY `FK_SYS_LOG_USER` (`USER_ID`),
  CONSTRAINT `FK_SYS_LOG_USER` FOREIGN KEY (`USER_ID`) REFERENCES `sys_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_log` */

/*Table structure for table `sys_map_role_permission` */

DROP TABLE IF EXISTS `sys_map_role_permission`;

CREATE TABLE `sys_map_role_permission` (
  `ROLE_ID` varchar(36) NOT NULL,
  `PERMISSION_ID` varchar(36) NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`PERMISSION_ID`),
  KEY `FK_SYS_MAP_ROLE_PERMISSION` (`PERMISSION_ID`),
  CONSTRAINT `FK_SYS_MAP_ROLE_PERMISSION` FOREIGN KEY (`PERMISSION_ID`) REFERENCES `sys_permission` (`PERMISSION_ID`),
  CONSTRAINT `FK_SYS_MAP_ROME_PERMISSION_ROLE` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_map_role_permission` */

insert  into `sys_map_role_permission`(`ROLE_ID`,`PERMISSION_ID`) values ('admin','111111111112'),('admin','111111111132'),('admin','111111111133'),('admin','111111111134'),('admin','111111111135'),('admin','111111111136'),('admin','111111111137'),('admin','111111111142'),('admin','111111111143'),('admin','111111111144'),('admin','111111111145'),('admin','111111111146'),('admin','111111111147'),('admin','111111111152'),('admin','111111111153'),('admin','111111111154'),('admin','111111111155'),('admin','22222222222220'),('admin','22222222222221');

/*Table structure for table `sys_map_user_role` */

DROP TABLE IF EXISTS `sys_map_user_role`;

CREATE TABLE `sys_map_user_role` (
  `USER_ID` varchar(36) NOT NULL,
  `ROLE_ID` varchar(36) NOT NULL,
  PRIMARY KEY (`USER_ID`,`ROLE_ID`),
  KEY `FK_SYS_USER_ROLE_SYS_ROLE` (`ROLE_ID`),
  CONSTRAINT `FK_SYS_USER_ROLE_SYS_ROLE` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ROLE_ID`),
  CONSTRAINT `FK_SYS_USER_ROLE_SYS_USER` FOREIGN KEY (`USER_ID`) REFERENCES `sys_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_map_user_role` */

insert  into `sys_map_user_role`(`USER_ID`,`ROLE_ID`) values ('admin','admin'),('ceshi','admin');

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `PERMISSION_ID` varchar(36) NOT NULL,
  `PERMISSION_NAME` varchar(128) NOT NULL,
  `PERMISSION_TYPE` int(1) NOT NULL,
  `URL` varchar(128) NOT NULL,
  `PERCODE` varchar(128) NOT NULL,
  `PARENT_PERMISSION_ID` varchar(36) DEFAULT NULL,
  `SORT` int(2) NOT NULL,
  `IS_AVAILABLE` tinyint(1) NOT NULL DEFAULT '1',
  `ICON` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`PERMISSION_ID`),
  KEY `FK_SYS_MAP_PERMISSION_PERMISSION` (`PARENT_PERMISSION_ID`),
  CONSTRAINT `FK_SYS_MAP_PERMISSION_PERMISSION` FOREIGN KEY (`PARENT_PERMISSION_ID`) REFERENCES `sys_permission` (`PERMISSION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_permission` */

insert  into `sys_permission`(`PERMISSION_ID`,`PERMISSION_NAME`,`PERMISSION_TYPE`,`URL`,`PERCODE`,`PARENT_PERMISSION_ID`,`SORT`,`IS_AVAILABLE`,`ICON`) values ('111111111112','配置管理',1,'javascript:void(0);','system1',NULL,1,1,'fa-cog'),('111111111132','用户管理',2,'/user/index','system:user:index','111111111112',10,1,'fa-user-o'),('111111111133','用户新增',3,'/user/add','system:user:add','111111111132',0,1,NULL),('111111111134','用户修改',3,'/user/edit','system:user:edit','111111111132',0,1,NULL),('111111111135','用户删除',3,'/user/delete','system:user:delete','111111111132',0,1,NULL),('111111111136','用户角色分配',3,'/user/grant','system:user:grant','111111111132',0,1,NULL),('111111111137','用户密码修改',3,'/user/updatePwd','system:user:updatePwd','111111111132',0,1,NULL),('111111111142','角色管理',2,'/role/index','system:role:index','111111111112',20,1,'fa-user-circle-o'),('111111111143','角色新增',3,'/role/add','system:role:add','111111111142',0,1,NULL),('111111111144','角色修改',3,'/role/edit','system:role:edit','111111111142',0,1,NULL),('111111111145','角色删除',3,'/role/delete','system:role:delete','111111111142',0,1,NULL),('111111111146','角色角色分配',3,'/role/grant','system:role:grant','111111111142',0,1,NULL),('111111111147','角色密码修改',3,'/role/updatePwd','system:role:updatePwd','111111111142',0,1,NULL),('111111111152','权限管理',2,'/permission/index','system:permission:index','111111111112',30,1,'fa-file-o'),('111111111153','权限新增',3,'/permission/add','system:permission:add','111111111152',0,1,NULL),('111111111154','权限修改',3,'/permission/edit','system:permission:edit','111111111152',0,1,NULL),('111111111155','权限删除',3,'/permission/delete','system:permission:delete','111111111152',0,1,NULL),('22222222222220','数据查询',1,'javascript:void(0);','dataSearch','22333333333331',100,1,'fa-search'),('22222222222221','历史数据查询',2,'/mpcv/index','dataSearch:mpcv:index','22222222222220',110,1,'fa-star');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `ROLE_ID` varchar(36) NOT NULL,
  `ROLE_NAME` varchar(30) DEFAULT NULL,
  `IS_AVAILABLE` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`ROLE_ID`,`ROLE_NAME`,`IS_AVAILABLE`) values ('1a3388b2-6eaa-4c39-b6df-a497ebb8eff1','北京广元科技',1),('6a2e077e-d544-4740-b41e-7556c7027022','呜呜呜',1),('admin','admin',1);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `USER_ID` varchar(36) NOT NULL,
  `CUSTOMER_ID` varchar(36) DEFAULT NULL,
  `USER_NAME` varchar(30) NOT NULL,
  `REAL_NAME` varchar(30) NOT NULL,
  `PASSWORD` varchar(32) NOT NULL,
  `USER_CATEGORY` int(1) NOT NULL,
  `POSITION_JOB` varchar(30) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `CELLPHONE` varchar(15) DEFAULT NULL,
  `TEL` varchar(15) DEFAULT NULL,
  `SALT` varchar(24) NOT NULL,
  `IS_LOCKED` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`USER_ID`),
  KEY `FK_SYS_USER_CUSTOMER_USER` (`CUSTOMER_ID`),
  CONSTRAINT `FK_SYS_USER_CUSTOMER_USER` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `bzh_customer_user` (`CUSTOMER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`USER_ID`,`CUSTOMER_ID`,`USER_NAME`,`REAL_NAME`,`PASSWORD`,`USER_CATEGORY`,`POSITION_JOB`,`EMAIL`,`CELLPHONE`,`TEL`,`SALT`,`IS_LOCKED`) values ('83930a16-f610-4707-9217-701291311e18',NULL,'guangyuan','广元科技','204514a72406943d960dc00f471455b6',1,'测试账号','guangyuan@guangyuanbj.com','100010','18810378647','YoGpm/yje1xmw7EFQo3UUg==',0),('admin',NULL,'lisi','李四','cb571f7bd7a6f73ab004a70322b963d5',1,'经理','test@qq.com','100865','18810378647','eteokues',0),('b6750c5e-c2ca-4954-9fa6-43d960f7823c',NULL,'zhangsan','张三','f63c861a8adfef47f5064993d73779c3',1,'测试人员','11','11','11','VJIessf8PKAteb2VFHInhA==',0),('c9848dd9-acd7-4a84-9548-5c3016e14486',NULL,'liming','李明','0e2c4189fbc769ce528ba09763088d23',1,'','','','','R/iZWeiLFGILxnAVEiZAeg==',0),('ceshi',NULL,'ceshi','测试test','cb571f7bd7a6f73ab004a70322b963d5',1,'测试经理','test@163.com','100865','18810378647','eteokues',0),('d014c03e-7c09-471e-ac21-4fe4577549d0',NULL,'cesaaa','测试账号','23798a0002015eed63a366003fc4d47f',1,'','','','','suJsNaqGBGX6fUga502hIA==',0),('d6922280-fbb6-4aca-b5c8-d823555e0788',NULL,'wangwu','王五','4ed126748f829c370d9fe4ff9023ce72',1,'测试','','','','VJlqjxnoLFz891XETB/0bg==',0),('test',NULL,'test','测试','cb571f7bd7a6f73ab004a70322b963d5',1,'初级测试用户','test@foxmail.com','100865','18810378647','eteokues',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
