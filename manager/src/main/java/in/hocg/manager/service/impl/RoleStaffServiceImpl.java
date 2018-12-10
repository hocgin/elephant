package in.hocg.manager.service.impl;

import in.hocg.mybatis.basic.BaseService;
import in.hocg.manager.service.RoleStaffService;
import in.hocg.mybatis.entity.RbacRole;
import in.hocg.mybatis.entity.RbacRoleStaff;
import in.hocg.mybatis.module.system.mapper.RoleStaffMapper;
import org.springframework.stereotype.Service;

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
public class RoleStaffServiceImpl extends BaseService<RoleStaffMapper, RbacRoleStaff>
        implements RoleStaffService {
    
    @Override
    public Collection<RbacRole> findByAllRoleUseStaffId(String id) {
        return baseMapper.findByAllRoleUseStaffId(id);
    }
}
