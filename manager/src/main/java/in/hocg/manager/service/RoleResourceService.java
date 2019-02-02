package in.hocg.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import in.hocg.mybatis.module.system.entity.Resource;
import in.hocg.mybatis.module.system.entity.RoleResource;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * [权限模块] 角色-员工 关联表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2018-10-21
 */
public interface RoleResourceService extends IService<RoleResource> {
    
    /**
     * 查询角色所具有的资源列表
     *
     * @param id
     * @return
     */
    List<Resource> selectMultiByRoleId(Serializable id);
}
