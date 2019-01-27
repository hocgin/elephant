package in.hocg.manager.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import in.hocg.manager.model.po.AddRole;
import in.hocg.manager.model.po.UpdateRole;
import in.hocg.mybatis.basic.condition.GetCondition;
import in.hocg.mybatis.basic.condition.PostCondition;
import in.hocg.mybatis.module.system.entity.Role;

import java.io.Serializable;
import java.util.Set;

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
    
    /**
     * POST 方式分页查询所有角色
     *
     * @param condition
     * @return
     */
    IPage<Role> page(PostCondition condition);
    
    /**
     * 删除角色
     *
     * @param asSet
     * @return
     */
    boolean removeMultiByIds(Set<Serializable> asSet);
    
    /**
     * 添加角色
     * @param parameter
     * @return
     */
    boolean insertOneRole(AddRole parameter);
    
    /**
     * 更新角色
     * @param id
     * @param parameter
     * @return
     */
    boolean updateOneById(String id, UpdateRole parameter);
}
