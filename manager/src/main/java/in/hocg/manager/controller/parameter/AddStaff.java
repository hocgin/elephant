package in.hocg.manager.controller.parameter;

import in.hocg.scaffold.support.basis.parameter.BaseParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by hocgin on 2018/12/18.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class AddStaff extends BaseParameter {
    private String nickname;
    private String username;
    private String password;
    private String avatarUri;
    private int gender;
}
