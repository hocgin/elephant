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
VALUE ('root', 1, 62, '根', '描述信息', 0, 'GET', '/', 'warning', 1),

      ('resource1', 2, 29, '访问控制', '描述信息', 0, 'GET', '/access', 'warning', 1),
          ('resource101', 3, 14, '资源管理', '描述信息', 0, 'GET', '/access/resource', 'warning', 1),
            ('resource10101', 4, 5, '查询所有', '描述信息', 1, 'GET', '/api/v1/resources', 'warning', 1),
            ('resource10102', 6, 7, '详情', '描述信息', 1, 'GET', '/api/v1/resources/*', 'warning', 1),
            ('resource10103', 8, 9, '增加', '描述信息', 1, 'POST', '/api/v1/resources', 'warning', 1),
            ('resource10104', 10, 11, '删除', '描述信息', 1, 'DELETE', '/api/v1/resources', 'warning', 1),
            ('resource10105', 12, 13, '修改', '描述信息', 1, 'PUT', '/api/v1/resources', 'warning', 1),
          ('resource102', 15, 28, '角色管理', '描述信息', 0, 'GET', '/access/role', 'warning', 1),
            ('resource10201', 16, 17, '查询所有', '描述信息', 1, 'GET', '/api/v1/roles', 'warning', 1),
            ('resource10202', 18, 19, '分页查询', '描述信息', 1, 'POST', '/api/v1/roles/_paging', 'warning', 1),
            ('resource10203', 20, 21, '详情', '描述信息', 1, 'GET', '/api/v1/roles/*', 'warning', 1),
            ('resource10204', 22, 23, '增加', '描述信息', 1, 'POST', '/api/v1/roles', 'warning', 1),
            ('resource10205', 24, 25, '删除', '描述信息', 1, 'DELETE', '/api/v1/roles', 'warning', 1),
            ('resource10206', 26, 27, '修改', '描述信息', 1, 'PUT', '/api/v1/roles', 'warning', 1),

      ('resource2', 30, 45, '账号管理', '描述信息', 0, 'GET', '/account', 'warning', 1),
          ('resource201', 31, 44, '员工管理', '描述信息', 0, 'GET', '/account/staff', 'warning', 1),
            ('resource20101', 32, 33, '查询所有', '描述信息', 1, 'GET', '/api/v1/staff', 'warning', 1),
            ('resource20102', 34, 35, '分页查询', '描述信息', 1, 'POST', '/api/v1/staff/_paging', 'warning', 1),
            ('resource20103', 36, 37, '详情', '描述信息', 1, 'GET', '/api/v1/staff/*', 'warning', 1),
            ('resource20104', 38, 39, '增加', '描述信息', 1, 'POST', '/api/v1/staff', 'warning', 1),
            ('resource20105', 40, 41, '删除', '描述信息', 1, 'DELETE', '/api/v1/staff', 'warning', 1),
            ('resource20106', 42, 43, '修改', '描述信息', 1, 'PUT', '/api/v1/staff', 'warning', 1),

      ('resource3', 46, 61, '日志监控', '描述信息', 0, 'GET', '/log', 'warning', 1),
          ('resource301', 47, 60, '访问日志', '描述信息', 0, 'GET', '/log/access-log', 'warning', 1),
            ('resource30101', 48, 49, '查询所有', '描述信息', 1, 'GET', '/api/v1/access-log', 'warning', 1),
            ('resource30102', 50, 51, '分页查询', '描述信息', 1, 'POST', '/api/v1/access-log/_paging', 'warning', 1),
            ('resource30103', 52, 53, '详情', '描述信息', 1, 'GET', '/api/v1/access-log/*', 'warning', 1),
            ('resource30104', 54, 55, '增加', '描述信息', 1, 'POST', '/api/v1/access-log', 'warning', 1),
            ('resource30105', 56, 57, '删除', '描述信息', 1, 'DELETE', '/api/v1/access-log', 'warning', 1),
            ('resource30106', 58, 59, '修改', '描述信息', 1, 'PUT', '/api/v1/access-log', 'warning', 1)
;
-- @formatter:on

INSERT INTO `role` (`id`, `mark`, `name`, `description`, `created_at`)
VALUE ('role1', 'ADMIN', '管理员', '系统管理员角色', now());

INSERT INTO `role_resource` (`id`, `role_id`, `resource_id`)
VALUE ('role_resource1', 'role1', 'root'),

      ('role_resource2', 'role1', 'resource1'),
      ('role_resource3', 'role1', 'resource101'),
      ('role_resource4', 'role1', 'resource10101'),
      ('role_resource5', 'role1', 'resource10102'),
      ('role_resource6', 'role1', 'resource10103'),
      ('role_resource7', 'role1', 'resource10104'),
      ('role_resource8', 'role1', 'resource10105'),
      ('role_resource9', 'role1', 'resource102'),
      ('role_resource10', 'role1', 'resource10201'),
      ('role_resource11', 'role1', 'resource10202'),
      ('role_resource12', 'role1', 'resource10203'),
      ('role_resource13', 'role1', 'resource10204'),
      ('role_resource14', 'role1', 'resource10205'),
      ('role_resource15', 'role1', 'resource10206'),

      ('role_resource16', 'role1', 'resource2'),
      ('role_resource17', 'role1', 'resource201'),
      ('role_resource18', 'role1', 'resource20101'),
      ('role_resource19', 'role1', 'resource20102'),
      ('role_resource20', 'role1', 'resource20103'),
      ('role_resource21', 'role1', 'resource20104'),
      ('role_resource22', 'role1', 'resource20105'),
      ('role_resource23', 'role1', 'resource20106'),

      ('role_resource24', 'role1', 'resource3'),
      ('role_resource25', 'role1', 'resource301'),
      ('role_resource26', 'role1', 'resource30101'),
      ('role_resource27', 'role1', 'resource30102'),
      ('role_resource28', 'role1', 'resource30103'),
      ('role_resource29', 'role1', 'resource30104'),
      ('role_resource30', 'role1', 'resource30105'),
      ('role_resource31', 'role1', 'resource30106')
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
