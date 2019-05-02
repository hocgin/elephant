## 模块
- api
- manager
- generate
- mapper
- scaffold
- util
- 梳理

- mock
- sdk
- component 构件

## 版本号
> https://semver.org/lang/zh-CN/
```shell
A.B.C
A 表示大版本号，一般当软件整体重写，或出现不向后兼容的改变时，增加A，A为零时表示软件还在开发阶段。
B 表示功能更新，出现新功能时增加B
C 表示小修改，如修复bug，只要有修改就增加C

alpha: 内部版本
beta: 测试版
rc: 即将作为正式版发布
lts: 长期维护
```

## TODO
- 定时任务模块拆分
- service 接口 / 业务模块实现(Service Imp)/ 模块控制器 拆分
- Ali 及 相关 sdk 拆分

## 5W1H
why---------为什么要做，是原因；
what--------做什么，做成什么，是目标；
where-------在哪儿做，是地点；
when--------什么时候做，是时间；
who---------谁来做，是执行对象；
how---------怎么做，是方法。

## 3W1H
- what --------做什么
- who --------谁来做
- when --------什么时候做
- how---------怎么做


## 增删改查
> 动词/多条/单条/所有/条件
- insert -> insertMulti -> insertOne -> insertAll -> insertBy
- update -> updateMulti -> updateOne -> updateAll -> updateBy
- delete -> deleteMulti -> deleteOne -> deleteAll -> deleteBy
- select -> selectMulti -> selectOne -> selectAll -> selectBy

> Multi = Multiple

## 参数类名

## 计划

## 目前整合
- Mybatis + Mybatis Plus
- Quartz
- Mail
- IP Util
- Ali OSS
- mail
- el
- Modified Preorder Tree Traversal
- json-ignore.. ~~[JFilter](https://github.com/rkonovalov/jfilter)~~