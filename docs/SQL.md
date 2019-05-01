## 关于业务型外键 SQL 写法
```mysql
-- 1
SELECT t1.id,
       case
         when t1.type = 2 then t2.name
         when t1.type = 1 then t3.name
         else '' end AS `name`
FROM t1 AS `t1`
       LEFT JOIN t2 AS `t2` ON t1.uid = t2.id
       LEFT JOIN t3 AS `t3` ON t1.uid = t3.id;
-- 2
SELECT t1.id
FROM t1 AS `t1`
       LEFT JOIN t2 AS `t2` ON (t1.uid = t2.id AND t1.type = 2)
       LEFT JOIN t3 AS `t3` ON (t1.uid = t3.id AND t1.type = 1);
```