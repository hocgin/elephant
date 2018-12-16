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
>  URL编码：example/page?limit=10&page=1&condition=is%3Apr+is%3Aopen&sort=created%3Aasc 
>
>  URL解码：example/page?limit=10&page=1&condition=is:pr is:open&sort=created:asc
>  - limit: 请求数量，默认为10。
>  - page: 请求页码，默认为1。
>  - condition: 自定义条件, 条件字段和值以:分割, 多条件以" "分割, 最后需经过 URLEncoder。
>  - sort: 排序方式, 排序字段和值以:分割，多排序以" "分割, 最后需经过 URLEncoder。
>  <p>
>  注意点:
>  - 请求方式为 GET。
>  - 排序可以直接通过前端设置。
>  - " "/"+" 分隔都是允许的

```cURL
curl "http://127.0.0.1:8081/get-page?limit=10&page=1&condition=is:pr%20is:open&sort=created2:asc%20created:desc" \
     -H 'Cookie: JSESSIONID=0D75C918151440D703E767488E37C0D2'
```

### SpEL 使用

```java
@Slf4j
@SpringBootTest
public class SpEl {
    
    
    @Test
    public void testEl() {
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("var", 1024);
        String text = SpelParser.parser("hi #{#var + 1}", context);
        String text2 = SpelParser.parser("", context);
        log.debug("{} {}", text, text2);
    }
}
```

