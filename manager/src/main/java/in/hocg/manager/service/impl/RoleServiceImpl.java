package in.hocg.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.manager.service.RoleService;
import in.hocg.mybatis.basic.BaseService;
import in.hocg.mybatis.basic.condition.GetCondition;
import in.hocg.mybatis.basic.condition.PostCondition;
import in.hocg.mybatis.module.system.entity.Role;
import in.hocg.mybatis.module.system.mapper.RoleMapper;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class RoleServiceImpl extends BaseService<RoleMapper, Role>
        implements RoleService {
    
    @Override
    public IPage<Role> page(GetCondition condition) {
        Page<Role> page = condition.page();
        QueryWrapper<Role> wrapper = condition.wrapper();
        return baseMapper.selectPage(page, wrapper);
    }
    
    @Override
    public IPage<Role> page(PostCondition condition) {
        Page<Role> page = condition.page();
        QueryWrapper<Role> wrapper = condition.wrapper();
        return baseMapper.selectPage(page, wrapper);
    }
}
