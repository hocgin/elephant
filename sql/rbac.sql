/**
* ============================
*           资源表
* ============================
*/
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id`          CHAR(32)
  COMMENT 'UUID',
  --
  `tree_path`   VARCHAR(255) NOT NULL
  COMMENT '树级路径',
  `parent_id`   CHAR(32)
  COMMENT '父级ID',
  `name`        VARCHAR(10)  NOT NULL
  COMMENT '菜单名称',
  `alias`       VARCHAR(10)  NOT NULL
  COMMENT '菜单别称',
  `description` VARCHAR(10)  NOT NULL
  COMMENT '菜单描述',
  `type`        TINYINT(1)   NOT NULL
  COMMENT '菜单类型(0:菜单;1:按钮)',
  `method`      VARCHAR(6)   NOT NULL
  COMMENT '请求类型(GET,POST,DELETE,PUT)',
  `uri`         VARCHAR(20)  NOT NULL
  COMMENT 'URI',
  `icon`        VARCHAR(20)  NOT NULL
  COMMENT '图标',
  `sort`        INT(5)       NOT NULL
  COMMENT '排序',
  `showed`      TINYINT(1)   NOT NULL                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          DEFAULT '0'
  COMMENT '链接显示状态(0:不显示; 1:显示)',
  `enabled`     TINYINT(1)   NOT NULL                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                DEFAULT '0'
  COMMENT '是否开启显示(0:不开启; 1:开启)',
  --

  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '[权限模块] 资源表';

/**
* ============================
*           角色表
* ============================
*/
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id`             CHAR(32)
  COMMENT 'UUID',
  --
  `identification` CHAR(32)
  COMMENT '角色标识',
  `tree_path`      VARCHAR(255) NOT NULL
  COMMENT '树级路径',
  `parent_id`      CHAR(32)
  COMMENT '父级ID',
  `name`           VARCHAR(10)  NOT NULL
  COMMENT '角色名称',
  `alias`          VARCHAR(10)  NOT NULL
  COMMENT '角色别称',
  `description`    VARCHAR(10)  NOT NULL
  COMMENT '角色描述',
  `sort`           INT(5)       NOT NULL
  COMMENT '排序',
  --

  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '[权限模块] 角色表';

/**
* ============================
*     角色-资源 关联表(多对多)
* ============================
*/
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `id`          CHAR(32)
  COMMENT 'UUID',
  --
  `role_id`     CHAR(32)
  COMMENT '角色 ID',
  `resource_id` CHAR(32)
  COMMENT '资源 ID',
  --

  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '[权限模块] 角色-资源 关联表';
/**
* ============================
*     员工表
* ============================
*/
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `id`            CHAR(32)
  COMMENT 'UUID',
  --
  `nick_name`     VARCHAR(10)  NOT NULL
  COMMENT '昵称',
  `username`      VARCHAR(20)  NOT NULL UNIQUE
  COMMENT '用户名',
  `password`      VARCHAR(100) NOT NULL
  COMMENT '密码',
  `avatar_uri`    VARCHAR(129)
  COMMENT '头像地址',
  `gender`        TINYINT(1) UNSIGNED
  COMMENT '性别(0:女, 1:男)',
  `expired`       TINYINT(1) UNSIGNED DEFAULT 0
  COMMENT '过期状态(0:为正常状态;1:为过期状态)',
  `locked`        TINYINT(1) UNSIGNED DEFAULT 0
  COMMENT '锁定状态(0:为正常状态;1:为锁定状态)',
  `enabled`       TINYINT(1) UNSIGNED DEFAULT 0
  COMMENT '启用状态(0:为正常状态;1:为禁用状态)',
  `sign_up_ip`    VARCHAR(15)
  COMMENT '注册时使用的IP',
  `last_login_ip` VARCHAR(15)
  COMMENT '最后登陆时使用的IP',
  --

  `created_at`    DATETIME(6)  NOT NULL
  COMMENT '创建时间',
  `creator`       CHAR(32)
  COMMENT '创建者',
  `updated_at`    DATETIME(6)
  COMMENT '更新时间',
  `updater`       CHAR(32)
  COMMENT '更新者',

  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '[用户模块] 员工表';

/**
* ============================
*     角色-员工 关联表(多对多)
* ============================
*/
DROP TABLE IF EXISTS `role_staff`;
CREATE TABLE `role_staff` (
  `id`       CHAR(32)
  COMMENT 'UUID',
  --
  `role_id`  CHAR(32)
  COMMENT '角色 ID',
  `staff_id` CHAR(32)
  COMMENT '员工 ID',
  --

  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '[权限模块] 角色-员工 关联表';