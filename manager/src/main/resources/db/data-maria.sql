-- ADMIN
INSERT INTO `account` (`id`, `type`)
VALUE ('id0admin', '1');
INSERT INTO `staff` (`id`, `account`, `username`, `nickname`, `password`, `created_at`)
VALUE ('id0admin', 'id0admin', 'admin', 'admin', '{noop}admin', NOW());

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
