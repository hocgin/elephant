package in.hocg.manager.model.vo;

import in.hocg.mybatis.module.system.entity.Role;
import in.hocg.mybatis.module.user.entity.Staff;
import in.hocg.scaffold.support.basis.parameter.BaseParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.util.Collection;

/**
 * Created by hocgin on 2019/2/22.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class StaffDetailVO extends Staff
        implements BaseParameter {
    private Collection<Role> roles;
    
    
    @Override
    public Object fill(Object object) {
        BeanUtils.copyProperties(object, this);
        return this;
    }
}
