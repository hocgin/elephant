DROP TABLE IF EXISTS `test_example`;
create table `test_example`
(
  `id`         varchar(32)
  comment '自增 ID'
    primary key,
  `name`       varchar(10)                     null,
  `type`       varchar(10)                     null,
  `gender`     tinyint(1) unsigned default '0' not null,
  `deleted`    tinyint(1) unsigned default '0' not null
  comment '0:为正常状态;1:为被删除状态',
  `created_at` datetime(6)                     not null
  comment '创建时间',
  `creator`    int(11) unsigned                null
  comment '创建者',
  `updated_at` datetime(6)                     null
  comment '更新时间',
  `updater`    int(11) unsigned                null
  comment '更新者',
  `deleted_at` datetime(6)                     null
  comment '删除时间',
  `deleter`    int(11) unsigned                null
  comment '删除者'
)
  ENGINE = InnoDB
  comment '例子表'
  charset = utf8mb4;


DROP TABLE IF EXISTS `tree`;
create table `tree`
(
  `id`        CHAR(65)
  COMMENT 'UUID',
  --
  `lft`       INT                    NOT NULL
  COMMENT '左侧',
  `rgt`       INT                    NOT NULL
  COMMENT '右侧',
  `name`      VARCHAR(10)            NOT NULL
  COMMENT '菜单名称',
  enabled     tinyint(1) default '0' not null,
  description varchar(10) default ''
  comment '菜单描述',
  type        int(1)
  comment '菜单类型 [菜单,按钮]',
  method      varchar(6)
  comment '请求类型 [GET,POST,DELETE,PUT]',
  path        varchar(20)
  comment 'URI',
  icon        varchar(20)
  comment '图标'
)
  ENGINE = InnoDB
  comment '例子表'
  charset = utf8mb4;

INSERT tree (`id`, `lft`, `rgt`, `name`)
VALUE ('root', 1, 2, 'root');
