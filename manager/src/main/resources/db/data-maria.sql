-- ADMIN
INSERT INTO `account` (`id`, `type`)
VALUE ('id0admin', '1');
INSERT INTO `staff` (`id`, `account`, `username`, `nickname`, `password`, `created_at`)
VALUE ('id0admin', 'id0admin', 'admin', 'admin', '{noop}admin', NOW());

-- 权限 & 角色
/**
  |-测试1
  |  |-测试11
  |  |-测试12
 */
INSERT INTO `resource` (`id`, `lft`, `rgt`, `name`, `description`, `type`, `method`, `path`, `icon`, `enabled`)
VALUE ('resource1', 1, 6, '测试1', '描述信息', 0, 'GET', '/', '', 1),
      ('resource2', 2, 3, '测试11', '描述信息', 0, 'GET', '/xx', '', 1),
      ('resource3', 4, 5, '测试12', '描述信息', 0, 'GET', '/xx3', '', 1);

INSERT INTO `role` (`id`, `mark`, `name`, `description`, `created_at`)
VALUE ('role1', 'ADMIN', '管理员', '系统管理员角色', now());

INSERT INTO `role_resource` (`id`, `role_id`, `resource_id`)
VALUE ('role_resource1', 'role1', 'resource1'),
      ('role_resource12', 'role1', 'resource2'),
      ('role_resource123', 'role1', 'resource3');

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
