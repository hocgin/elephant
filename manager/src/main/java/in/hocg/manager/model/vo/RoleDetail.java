package in.hocg.manager.model.vo;

import in.hocg.mybatis.module.system.entity.Resource;
import in.hocg.mybatis.module.system.entity.Role;
import in.hocg.scaffold.support.basis.parameter.BaseParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
}
