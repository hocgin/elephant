package in.hocg.scaffold.support.basis.condition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.HashMultimap;
import in.hocg.scaffold.constant.Charset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by hocgin on 2018/8/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 *
 * <p>
 * 请求的格式例子:
 * URL编码：example/page?limit=10&page=1&condition=is%3Apr+is%3Aopen&sort=created%3Aasc
 * URL解码：example/page?limit=10&page=1&condition=is:pr is:open&sort=created:asc
 * - limit: 请求数量，默认为10。
 * - page: 请求页码，默认为1。
 * - condition: 自定义条件, 条件字段和值以:分割, 多条件以" "分割, 最后需经过 URLEncoder。
 * - sort: 排序方式, 排序字段和值以:分割，多排序以" "分割, 最后需经过 URLEncoder。
 * <p>
 * 注意点:
 * - 请求方式为 GET。
 * - 排序可以直接通过前端设置。
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetPageCondition extends PageCondition {
    /**
     * 查询条件:
     * - 字段:值
     */
    private String condition = "";
    @JsonIgnore
    private HashMultimap<String, String> conditionMap = HashMultimap.create();
    /**
     * 排序条件
     * - 字段:asc
     */
    private String sort = "";
    @JsonIgnore
    private Map<String, String> sortMap = new HashMap<>();
    
    /**
     * 获取所有过滤条件
     *
     * @return
     */
    public HashMultimap<String, String> getConditionMap() {
        if (!conditionMap.isEmpty()
                || condition.trim().length() == 0) {
            return conditionMap;
        }
        split(condition).forEach(string -> {
            String[] s = string.split(":");
            if (s.length == 2) {
                conditionMap.put(s[0], s[1]);
            }
        });
        return conditionMap;
    }
    
    /**
     * 获取所有排序条件
     *
     * @return
     */
    @Override
    public Map<String, String> getSortMap() {
        if (!sortMap.isEmpty()
                || sort.trim().length() == 0) {
            return sortMap;
        }
        split(sort).forEach(string -> {
            String[] s = string.split(":");
            if (s.length == 2) {
                sortMap.put(s[0], s[1]);
            }
        });
        return sortMap;
    }
    
    public Optional<Set<String>> getConditionValue(String key) {
        return Optional.ofNullable(getConditionMap().get(key));
    }
    
    public Optional<String> getConditionOneValue(String key) {
        Set<String> values = getConditionMap().get(key);
        if (values != null
                && !values.isEmpty()) {
            return Optional.ofNullable(values.iterator().next());
        }
        return Optional.empty();
    }
    
    public Optional<String> getSortedValue(String key) {
        return Optional.ofNullable(getSortMap().get(key));
    }
    
    public Optional<String> getSortedOneValue(String key) {
        String value = getSortMap().get(key);
        return Optional.ofNullable(value);
    }
    
    
    /**
     * 解码和切割
     *
     * @param url
     * @return
     */
    private Stream<String> split(String url) {
        String decode = "";
        try {
            decode = URLDecoder.decode(url, Charset.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Arrays.stream(decode.split(" "))
                .map(String::trim);
    }
    
}
