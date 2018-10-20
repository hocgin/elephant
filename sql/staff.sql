/**
* ============================
*     员工表
* ============================
*/
DROP TABLE IF EXISTS `user_staff`;
CREATE TABLE `user_staff` (
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