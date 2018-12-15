## 配置说明

### 防重放攻击
```yml
scaffold:
  anti-replay:
    ignore-url:
    - /login
    enabled: true
```

### 阿里云OSS
```yaml
ali-yun:
  oss:
    enabled: true
    endpoint: 'endpoint'
    secret-access: 'secret-access'
    access-key: 'access-key'
```

## 部分接口的使用
### PostCondition<?>
> ? 对应`condition`内容, 可使用Spring校验框架
```cURL
## Request
curl -X "POST" "http://127.0.0.1:8081/post-page?condition.id=admin&password=admin" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -H 'Cookie: JSESSIONID=0D75C918151440D703E767488E37C0D2' \
     -d $'{
  "condition": {
    "id": "ads",
    "name": "sd"
  },
  "sort": {
    "id": "desc"
  }
}'

```
### GetCondition
