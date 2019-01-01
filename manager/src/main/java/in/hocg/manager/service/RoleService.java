package in.hocg.manager.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import in.hocg.mybatis.basic.condition.GetCondition;
import in.hocg.mybatis.module.system.entity.Role;

/**
 * <p>
 * [权限模块] 角色表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2018-10-21
 */
public interface RoleService extends IService<Role> {
    
    /**
     * 查找所有角色
     *
     * @param condition
     * @return
     */
    IPage<Role> page(GetCondition condition);
}
