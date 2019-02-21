package in.hocg.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import in.hocg.manager.service.RoleStaffService;
import in.hocg.mybatis.basic.BaseService;
import in.hocg.mybatis.module.system.entity.Role;
import in.hocg.mybatis.module.system.entity.RoleStaff;
import in.hocg.mybatis.module.system.mapper.RoleStaffMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class RoleStaffServiceImpl extends BaseService<RoleStaffMapper, RoleStaff>
        implements RoleStaffService {
    
    @Override
    public Collection<Role> findByAllRoleUseStaffId(String id) {
        return baseMapper.selectMultiByStaffId(id);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAllWithStaffId(Serializable id) {
        LambdaQueryWrapper<RoleStaff> wrapper = new LambdaQueryWrapper<RoleStaff>().eq(RoleStaff::getStaffId, id);
        return baseMapper.delete(wrapper);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertRoles(String staffId, String[] rolesId) {
        RoleStaff entity;
        for (String roleId : rolesId) {
            entity = new RoleStaff();
            baseMapper.insert(entity.setRoleId(roleId).setStaffId(staffId));
        }
    }
}
