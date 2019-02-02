package in.hocg.manager.model.vo;

import in.hocg.mybatis.module.system.entity.Resource;
import in.hocg.mybatis.module.system.entity.Role;
import in.hocg.scaffold.support.basis.parameter.BaseParameter;
import in.hocg.scaffold.util.ClassKit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * @author hocgin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class RoleDetail extends Role
        implements BaseParameter {
    
    /**
     * 角色所分配的资源
     */
    private Resource resources;
    
    @Override
    public Object fill(Object object) {
        BeanUtils.copyProperties(object, this, ClassKit.getNullValueFields(this));
        return this;
    }
}
