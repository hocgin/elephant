package in.hocg.scaffold.support.basis.parameter;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Created by hocgin on 2018/12/18.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ToString
public class ID implements BaseParameter {
    @NotEmpty(message = "数据项不存在")
    private Serializable id;
}
