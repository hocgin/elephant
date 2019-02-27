package in.hocg.manager.model.vo;

import in.hocg.mybatis.module.system.entity.Resource;
import in.hocg.mybatis.module.system.entity.Role;
import in.hocg.scaffold.support.basis.parameter.BaseParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.Collection;

/**
 * @author hocgin
 */
@Slf4j
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class RoleDetailVO extends Role
        implements BaseParameter {
    
    /**
     * 角色所分配的资源
     */
    private Collection<Resource> resources;
    
    @Override
    public Object fill(Object object) {
        BeanUtils.copyProperties(object, this);
        return this;
    }
}
