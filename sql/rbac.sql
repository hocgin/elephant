/**
* ============================
*           资源表
* ============================
*/
DROP TABLE IF EXISTS `rbac_resource`;
CREATE TABLE `rbac_resource` (
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
  `uri`         VARCHAR(20)  NOT NULL
  COMMENT 'URI',
  `icon`        VARCHAR(20)  NOT NULL
  COMMENT '图标',
  `sort`        INT(5)       NOT NULL
  COMMENT '排序',
  `showed`      TINYINT(1)   NOT NULL                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          DEFAULT '0'
  COMMENT '链接显示状态(0:不显示; 1:显示)',
  `enabled`     TINYINT(1)   NOT NULL                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                DEFAULT '0'
  COMMENT '是否开启(0:不开启; 1:开启)',
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
DROP TABLE IF EXISTS `rbac_role`;
CREATE TABLE `rbac_role` (
  `id`          CHAR(32)
  COMMENT 'UUID',
  --
  `tree_path`   VARCHAR(255) NOT NULL
  COMMENT '树级路径',
  `name`        VARCHAR(10)  NOT NULL
  COMMENT '角色名称',
  `alias`        VARCHAR(10)  NOT NULL
  COMMENT '角色别称',
  `description` VARCHAR(10)  NOT NULL
  COMMENT '角色描述',
  `sort`        INT(5)       NOT NULL
  COMMENT '排序',
  `enabled`     TINYINT(1)   NOT NULL                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                DEFAULT '0'
  COMMENT '是否开启(0:不开启; 1:开启)',
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
DROP TABLE IF EXISTS `rbac_role_resource`;
CREATE TABLE `rbac_role_resource` (
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
