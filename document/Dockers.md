## 该项目所需服务
### Redis 服务
docker run -d -p 6379:6379 redis

### MariaDB

```shell
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=hocgin mariadb

# 配置
# 1. 配置`varchar`可自增, 去除`STRICT_TRANS_TABLES`
# 2. 配置`Group By`不必全字段, 去除`ONLY_FULL_GROUP_BY`
# SET GLOBAL sql_mode = 'NO_ENGINE_SUBSTITUTION,NO_ZERO_DATE,NO_ZERO_IN_DATE';
```




# docker run -d -p 8081:8081 --name nexus -v nexus-data:/Users/hocgin/Docker/Nexus sonatype/nexus3
