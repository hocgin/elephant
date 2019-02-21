package in.hocg.mybatis.module.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.mybatis.basic.model.SuperModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2018/12/22.
 * email: hocgin@gmail.com
 * 账号表
 *
 * @author hocgin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("account")
public class Account extends SuperModel<Account> {
    
    /**
     * 账号类型 [用户, 员工]
     */
    private Integer type;
}
