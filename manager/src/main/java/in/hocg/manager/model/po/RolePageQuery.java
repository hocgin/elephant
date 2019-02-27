package in.hocg.manager.model.po;

import in.hocg.scaffold.support.basis.parameter.BaseParameter;
import lombok.Data;
import lombok.ToString;

/**
 * Created by hocgin on 2019/1/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ToString
public class RolePageQuery implements BaseParameter {
    private String name;
    private Boolean status;
}
