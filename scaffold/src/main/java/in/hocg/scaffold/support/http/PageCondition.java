package in.hocg.scaffold.support.http;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.CaseFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

/**
 * Created by hocgin on 2018/9/4.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public abstract class PageCondition implements Serializable {
    protected int limit = 10;
    protected int page = 1;
    
    abstract Map<String, String> getSortMap();
    
    
    /**
     * ======================================
     * 结合 Mybatis Plus
     * ======================================
     */
    @JsonIgnore
    public <T> Page<T> page() {
        return new Page<>(page, limit);
    }
    
    @JsonIgnore
    public <T> QueryWrapper<T> wrapper() {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        getSortMap().keySet().forEach(key -> {
            Optional.ofNullable(getSortMap().get(key))
                    .ifPresent(value ->
                            wrapper.orderBy(true,
                                    "ASC".equalsIgnoreCase(value),
                                    CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, key)));
        });
        return wrapper;
    }
}
