package in.hocg.manager.model.parameter;

import in.hocg.scaffold.support.basis.parameter.BaseParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * Created by hocgin on 2018/12/30.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class Login extends BaseParameter {
    @NotBlank(message = "请输入用户名")
    private String username;
    @NotBlank(message = "请输入密码")
    private String password;
}
