package in.hocg.manager.model.po;

import in.hocg.scaffold.support.basis.parameter.BaseParameter;
import lombok.Data;

/**
 * Created by hocgin on 2019/2/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class CurrentAccountUpdate implements BaseParameter {
    private String avatar;
    private String nickname;
    private Integer gender;
}
