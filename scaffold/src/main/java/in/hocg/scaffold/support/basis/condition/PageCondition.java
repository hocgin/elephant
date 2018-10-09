package in.hocg.scaffold.support.basis.condition;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

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
}
