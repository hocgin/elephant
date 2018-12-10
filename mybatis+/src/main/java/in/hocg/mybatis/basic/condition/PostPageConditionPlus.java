package in.hocg.mybatis.basic.condition;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.CaseFormat;
import in.hocg.scaffold.support.basis.condition.PostPageCondition;

import java.util.Optional;

/**
 * Created by hocgin on 2018/10/9.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class PostPageConditionPlus<T> extends PostPageCondition<T> {
    
    @JsonIgnore
    public Page<T> page() {
        return new Page<>(page, limit);
    }
    
    @JsonIgnore
    public QueryWrapper<T> wrapper() {
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
