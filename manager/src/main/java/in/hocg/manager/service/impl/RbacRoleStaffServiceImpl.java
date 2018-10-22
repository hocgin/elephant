package in.hocg.manager.service.impl;

import in.hocg.basic.BaseService;
import in.hocg.manager.service.RbacRoleStaffService;
import in.hocg.module.system.entity.RbacRole;
import in.hocg.module.system.entity.RbacRoleStaff;
import in.hocg.module.system.mapper.RbacRoleStaffMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 * [权限模块] 角色-员工 关联表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2018-10-21
 */
@Service
public class RbacRoleStaffServiceImpl extends BaseService<RbacRoleStaffMapper, RbacRoleStaff> implements RbacRoleStaffService {
    
    @Override
    public Collection<RbacRole> findByAllRoleUseStaffId(String id) {
        return baseMapper.findByAllRoleUseStaffId(id);
    }
}
