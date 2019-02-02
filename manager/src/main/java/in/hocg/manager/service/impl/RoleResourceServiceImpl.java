package in.hocg.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import in.hocg.manager.service.RoleResourceService;
import in.hocg.mybatis.basic.BaseService;
import in.hocg.mybatis.module.system.entity.Resource;
import in.hocg.mybatis.module.system.entity.RoleResource;
import in.hocg.mybatis.module.system.mapper.RoleResourceMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * [权限模块] 角色-员工 关联表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2018-10-21
 */
@Service
public class RoleResourceServiceImpl extends BaseService<RoleResourceMapper, RoleResource>
        implements RoleResourceService {
    
    @Override
    public List<Resource> selectMultiByRoleId(Serializable id) {
        return baseMapper.selectMultiByRoleId(id);
    }
}
