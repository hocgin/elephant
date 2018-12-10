package in.hocg.manager.service.impl;

import in.hocg.mybatis.basic.BaseService;
import in.hocg.manager.service.RoleService;
import in.hocg.mybatis.entity.RbacRole;
import in.hocg.mybatis.module.system.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * [权限模块] 角色表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2018-10-21
 */
@Service
public class RoleServiceImpl extends BaseService<RoleMapper, RbacRole>
        implements RoleService {
    
}
