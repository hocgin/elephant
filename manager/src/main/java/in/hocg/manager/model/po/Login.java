package in.hocg.manager.model.po;

import in.hocg.scaffold.support.basis.parameter.BaseParameter;
import lombok.Data;
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
public class Login implements BaseParameter {
    @NotBlank(message = "请输入用户名")
    private String username;
    @NotBlank(message = "请输入密码")
    private String password;
}
