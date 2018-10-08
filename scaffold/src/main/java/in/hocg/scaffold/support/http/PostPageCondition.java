package in.hocg.scaffold.support.http;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.Map;

/**
 * Created by hocgin on 2018/8/26.
 * email: hocgin@gmail.com
 * <p>
 * 请求方式: POST
 * 内容: application/json
 *
 * @author hocgin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PostPageCondition<T> extends PageCondition {
    @Valid
    private T condition;
    protected Map<String, String> sort;
    
    
    @Override
    Map<String, String> getSortMap() {
        return sort;
    }
}
