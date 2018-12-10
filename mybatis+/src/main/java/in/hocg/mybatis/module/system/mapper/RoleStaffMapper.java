package in.hocg.mybatis.module.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.mybatis.module.system.entity.RbacRole;
import in.hocg.mybatis.module.system.entity.RbacRoleStaff;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <p>
 * [权限模块] 角色-员工 关联表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2018-10-21
 */
public interface RoleStaffMapper extends BaseMapper<RbacRoleStaff> {
    Collection<RbacRole> findByAllRoleUseStaffId(@Param("id") String id);
}
