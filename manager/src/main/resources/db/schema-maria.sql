/** 备用信息
-- 基础建表语句
DROP TABLE IF EXISTS `file_manager`;
CREATE TABLE `file_manager` (
  `id`          CHAR(32)
  COMMENT 'UUID',
  --
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '[基础模块] 文件管理表';

-- Default Modal 字段
  `created_at`  DATETIME(6) NOT NULL
  COMMENT '创建时间',
  `creator`     CHAR(32)
  COMMENT '创建者',
  `updated_at`  DATETIME(6)
  COMMENT '更新时间',
  `updater`     CHAR(32)
  COMMENT '更新者',

-- Deleted Modal 再扩展字段
  `deleted_at`  DATETIME(6)
  COMMENT '删除时间',
  `deleter`     CHAR(32)
  COMMENT '删除者',
  `deleted`   TINYINT(1)  NOT NULL DEFAULT '0'
  COMMENT '逻辑删除 [正常状态,被删除状态]',


 */

DROP TABLE IF EXISTS `access_log`;
CREATE TABLE `access_log` (
  `id`         CHAR(32)
  COMMENT 'UUID',
  `level`      VARCHAR(6)
  COMMENT '日志级别(INFO, ERROR)',
  `visitor`    CHAR(32)
    DEFAULT 'Unknown'
  COMMENT '访问者账号ID',
  `response`   LONGTEXT
  COMMENT '响应结果(JSON)',
  `parameters` LONGTEXT
  COMMENT '请求参数(JSON)',
  `mapping`    LONGTEXT
  COMMENT 'Class#Method',
  `message`    LONGTEXT
  COMMENT '日志信息',
  `method`     VARCHAR(8)
  COMMENT '发起请求的方式',
  `uri`        VARCHAR(100)
  COMMENT '请求路径',
  `usage_time` INT
    DEFAULT '0'
  COMMENT '耗时 单位:ms',
  `ip`         VARCHAR(20)
  COMMENT '访问者IP地址',
  `operating`  VARCHAR(20)
  COMMENT '操作行为,eg: 注册管理员账号',
  `source`     VARCHAR(10)
  COMMENT '来源,eg: 系统管理后台',

  `created_at` DATETIME(6) NOT NULL
  COMMENT '创建时间',
  `creator`    CHAR(32)
  COMMENT '创建者',
  `updated_at` DATETIME(6)
  COMMENT '更新时间',
  `updater`    CHAR(32)
  COMMENT '更新者',

  --
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '[基础模块] 访问日志';

/**
* ============================
*      文件记录表(file_record)
* ============================
*/
DROP TABLE IF EXISTS `file_record`;
CREATE TABLE `file_record` (
  `id`           CHAR(32)
  COMMENT 'UUID',
  `origin_name`  VARCHAR(255) NOT NULL
    DEFAULT 'Unknown'
  COMMENT '上传时文件名(全名称)',
  `storage_name` VARCHAR(36)  NOT NULL UNIQUE
    DEFAULT 'Unknown'
  COMMENT '存储后文件名(全名称)',
  `type`         VARCHAR(12)  NOT NULL
    DEFAULT 'Unknown'
  COMMENT '文件类型',
  `size`         INT(11)      NOT NULL
    DEFAULT '0'
  COMMENT '文件大小',
  `md5`          VARCHAR(32)  NOT NULL
  COMMENT '文件的MD5校验码',
  `oss`          VARCHAR(6)   NOT NULL
  COMMENT '存储的 OSS 平台',
  `uploader`     VARCHAR(32)  NOT NULL
  COMMENT '上传者',
  `publicity`    TINYINT(1)   NOT NULL
    DEFAULT '0'
  COMMENT '是否公开 [私有, 公开]',
  `created_at`   DATETIME(6)  NOT NULL
  COMMENT '创建时间',
  `creator`      CHAR(32)
  COMMENT '创建者',
  `updated_at`   DATETIME(6)
  COMMENT '更新时间',
  `updater`      CHAR(32)
  COMMENT '更新者',
  `deleted_at`   DATETIME(6)
  COMMENT '删除时间',
  `deleter`      CHAR(32)
  COMMENT '删除者',
  `deleted`      TINYINT(1)   NOT NULL
    DEFAULT '0'
  COMMENT '逻辑删除 [正常状态,被删除状态]',
  --
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '[基础模块] 文件管理表';

/**
* ============================
*          组织结构表
* ============================
*/
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `id`          CHAR(32)
  COMMENT 'UUID',
  --
  `lft`         INT         NOT NULL
  COMMENT '左侧',
  `rgt`         INT         NOT NULL
  COMMENT '右侧',
  `name`        VARCHAR(10) NOT NULL
  COMMENT '组织名称',
  `description` VARCHAR(10) NOT NULL
  COMMENT '描述',
  `created_at`  DATETIME(6) NOT NULL
  COMMENT '创建时间',
  `creator`     CHAR(32)
  COMMENT '创建者',
  `updated_at`  DATETIME(6)
  COMMENT '更新时间',
  `updater`     CHAR(32)
  COMMENT '更新者',
  `enabled`     TINYINT(1)  NOT NULL
    DEFAULT '0'
  COMMENT '是否开启 [不开启,开启]',

  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '[基础模块] 组织结构表';
/**
* ============================
*           账号表
* ============================
*/
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id`   CHAR(32)
  COMMENT 'UUID',
  `type` INT NOT NULL
  COMMENT '账号类型 [用户, 员工]',
  --
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '[用户模块] 账号表';


/**
* ============================
*           员工表
* ============================
*/
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `id`          CHAR(32)
  COMMENT 'UUID',
  --
  `account`     VARCHAR(10)  NOT NULL
    UNIQUE
  COMMENT '关联账号ID',
  `nickname`    VARCHAR(10)  NOT NULL
  COMMENT '昵称',
  `username`    VARCHAR(20)  NOT NULL
    UNIQUE
  COMMENT '用户名',
  `password`    VARCHAR(100) NOT NULL
  COMMENT '密码',
  `avatar`      VARCHAR(32)
  COMMENT '头像',
  `gender`      INT(1)
    DEFAULT '1'
  COMMENT '性别 [女, 男]',
  `non_expired` TINYINT(1) UNSIGNED
    DEFAULT '1'
  COMMENT '过期状态 [过期状态,正常状态]',
  `non_locked`  TINYINT(1) UNSIGNED
    DEFAULT '1'
  COMMENT '锁定状态 [过期状态,正常状态]',
  `enabled`     TINYINT(1) UNSIGNED
    DEFAULT '1'
  COMMENT '启用状态 [关闭状态,开启状态]',

  `created_at`  DATETIME(6)  NOT NULL
  COMMENT '创建时间',
  `creator`     CHAR(32)
  COMMENT '创建者',
  `updated_at`  DATETIME(6)
  COMMENT '更新时间',
  `updater`     CHAR(32)
  COMMENT '更新者',
  --
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '[用户模块] 员工表';


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
  `lft`         INT         NOT NULL
  COMMENT '左侧',
  `rgt`         INT         NOT NULL
  COMMENT '右侧',
  `name`        VARCHAR(10) NOT NULL
  COMMENT '菜单名称',
  `description` VARCHAR(10) NOT NULL
  COMMENT '菜单描述',
  `type`        INT(1)      NOT NULL
  COMMENT '菜单类型 [菜单,按钮]',
  `method`      VARCHAR(6)  NOT NULL
  COMMENT '请求类型 [GET,POST,DELETE,PUT]',
  `path`        VARCHAR(20) NOT NULL
  COMMENT 'URI',
  `icon`        VARCHAR(20) NOT NULL
  COMMENT '图标',
  `enabled`     TINYINT(1)  NOT NULL                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                DEFAULT '0'
  COMMENT '是否开启 [不开启,开启], 相当于不具备权限',
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
  `id`          CHAR(32)
  COMMENT 'UUID',
  --
  `mark`        CHAR(32)    NOT NULL
  COMMENT '角色标识',
  `name`        VARCHAR(10) NOT NULL
  COMMENT '角色名称',
  `description` VARCHAR(10) NOT NULL
  COMMENT '角色描述',
  --
  `created_at`  DATETIME(6) NOT NULL
  COMMENT '创建时间',
  `creator`     CHAR(32)
  COMMENT '创建者',
  `updated_at`  DATETIME(6)
  COMMENT '更新时间',
  `updater`     CHAR(32)
  COMMENT '更新者',
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
  `role_id`     CHAR(32) NOT NULL
  COMMENT '角色 ID',
  `resource_id` CHAR(32) NOT NULL
  COMMENT '资源 ID',
  --

  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '[权限模块] 角色-资源 关联表';

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
  `role_id`  CHAR(32) NOT NULL
  COMMENT '角色 ID',
  `staff_id` CHAR(32) NOT NULL
  COMMENT '员工 ID',
  --

  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '[权限模块] 角色-员工 关联表';