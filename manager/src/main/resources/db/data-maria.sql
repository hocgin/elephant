-- ADMIN
INSERT INTO `account` (`id`, `type`)
VALUE ('id0admin', '1');
INSERT INTO `staff` (`id`, `username`, `nickname`, `password`, `created_at`)
VALUE ('id0admin', 'admin', 'admin', '{noop}admin', NOW());

-- 权限 & 角色
/**
访问资源
  |-访问控制
  |  |-角色管理
  |  |-资源管理
  |-系统配置
  |  |-数据字典
  |-账号管理
  |  |-员工管理
  |  |-用户管理
 */
-- @formatter:off
INSERT INTO `resource` (`id`, `lft`, `rgt`, `name`, `description`, `type`, `method`, `path`, `icon`, `enabled`)
VALUE ('root', 1, 22, '根', '描述信息', 0, 'GET', '/', 'warning', 1),
        ('resource2', 2, 7, '访问控制', '描述信息', 0, 'GET', '/access', 'warning', 1),
          ('resource3', 3, 4, '资源管理', '描述信息', 1, 'GET', '/access/resource', 'warning', 1),
          ('resource4', 5, 6, '角色管理', '描述信息', 1, 'GET', '/access/role', 'warning', 1),
        ('resource5', 8, 11, '系统配置', '描述信息', 0, 'GET', '/system', 'warning', 1),
          ('resource6', 9, 10, '数据字典', '描述信息', 1, 'GET', '/system/dictionary', 'warning', 1),
        ('resource7', 12, 17, '账号管理', '描述信息', 0, 'GET', '/account', 'warning', 1),
          ('resource8', 13, 14, '员工管理', '描述信息', 1, 'GET', '/account/staff', 'warning', 1),
          ('resource9', 15, 16, '用户管理', '描述信息', 1, 'GET', '/account/user', 'warning', 1),
        ('resource10', 18, 21, '日志监控', '描述信息', 0, 'GET', '/log', 'warning', 1),
          ('resource11', 19, 20, '访问日志', '描述信息', 1, 'GET', '/log/access-log', 'warning', 1)
;
-- @formatter:on

INSERT INTO `role` (`id`, `mark`, `name`, `description`, `created_at`)
VALUE ('role1', 'ADMIN', '管理员', '系统管理员角色', now());

INSERT INTO `role_resource` (`id`, `role_id`, `resource_id`)
VALUE ('role_resource1', 'role1', 'root'),
      ('role_resource2', 'role1', 'resource2'),
      ('role_resource3', 'role1', 'resource3'),
      ('role_resource4', 'role1', 'resource4'),
      ('role_resource5', 'role1', 'resource5'),
      ('role_resource6', 'role1', 'resource6'),
      ('role_resource7', 'role1', 'resource7'),
      ('role_resource8', 'role1', 'resource8'),
      ('role_resource9', 'role1', 'resource9'),
      ('role_resource10', 'role1', 'resource10'),
      ('role_resource11', 'role1', 'resource11')
;

INSERT INTO `role_staff` (`id`, `role_id`, `staff_id`)
VALUE ('id0admin', 'role1', 'id0admin');

-- 添加一张图片
INSERT INTO file_record (id, origin_name, storage_name, type, size, md5, oss, uploader, publicity, deleted, created_at)
VALUES ('952bad3efe0542519c39db761525489c',
        '\345\261\217\345\271\225\345\277\253\347\205\247 2017-01-12 19.55.12.png',
        'da82a5bcba2836340e8c5857b92204aa.png',
        'image/png',
        902996,
        'da82a5bcba2836340e8c5857b92204aa',
        'AliYun',
        'id0admin',
        true,
        false,
        '2018-12-23 16:52:04.033');
