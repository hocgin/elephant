package in.hocg.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import in.hocg.mybatis.entity.RbacRole;
import in.hocg.mybatis.entity.RbacRoleStaff;

import java.util.Collection;

/**
 * <p>
 * [权限模块] 角色-员工 关联表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2018-10-21
 */
public interface RoleStaffService extends IService<RbacRoleStaff> {
    
    /**
     * 查找 该员工 分配到的所有角色
     * @param id
     * @return
     */
    Collection<RbacRole> findByAllRoleUseStaffId(String id);
}
