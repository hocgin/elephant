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