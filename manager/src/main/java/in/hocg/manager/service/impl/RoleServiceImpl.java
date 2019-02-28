package in.hocg.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.manager.model.po.AddRole;
import in.hocg.manager.model.po.RolePageQuery;
import in.hocg.manager.model.po.UpdateRole;
import in.hocg.manager.model.vo.RoleDetailVO;
import in.hocg.manager.service.ResourceService;
import in.hocg.manager.service.RoleResourceService;
import in.hocg.manager.service.RoleService;
import in.hocg.manager.service.RoleStaffService;
import in.hocg.mybatis.basic.BaseService;
import in.hocg.mybatis.basic.condition.PostCondition;
import in.hocg.mybatis.module.system.entity.Resource;
import in.hocg.mybatis.module.system.entity.Role;
import in.hocg.mybatis.module.system.entity.RoleResource;
import in.hocg.mybatis.module.system.entity.RoleStaff;
import in.hocg.mybatis.module.system.mapper.RoleMapper;
import in.hocg.scaffold.lang.exception.NotRollbackException;
import in.hocg.scaffold.lang.exception.ResponseException;
import in.hocg.scaffold.lang.exception.RollbackException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
    
    private final ResourceService resourceService;
    private final RoleResourceService roleResourceService;
    private final RoleStaffService roleStaffService;
    
    @Override
    public IPage<Role> paging(PostCondition<RolePageQuery, Role> condition) {
        RolePageQuery values = condition.getCondition();
        LambdaQueryWrapper<Role> wrapper = condition.wrapper().lambda();
        if (Objects.nonNull(values)) {
            wrapper.like(Strings.isNotBlank(values.getName()), Role::getName, values.getName())
                    .eq(Objects.nonNull(values.getStatus()), Role::getEnabled, values.getStatus());
        }
        return baseMapper.selectPage(condition.page(), wrapper);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = NotRollbackException.class)
    public boolean deletes(Set<Serializable> ids) throws RollbackException {
        for (Serializable id : ids) {
            LambdaQueryWrapper<RoleStaff> wrapper = new LambdaQueryWrapper<RoleStaff>().eq(RoleStaff::getRoleId, id);
            // 判断是否有用户分配到该角色
            if (roleStaffService.count(wrapper) != 0) {
                // 有 删除失败, 提示信息
                throw ResponseException.wrap(RollbackException.class, "删除失败, 含有已分配到员工的角色");
            }
            // 否 删除角色
            roleStaffService.remove(wrapper);
            baseMapper.deleteById(id);
        }
        return true;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = NotRollbackException.class)
    public boolean insert(AddRole parameter) throws NotRollbackException {
        // 检查 名称 or 标识 是否存在
        Integer count = lambdaQuery().eq(Role::getMark, parameter.getMark())
                .or().eq(Role::getName, parameter.getName()).count();
        if (count != 0) {
            throw ResponseException.wrap(NotRollbackException.class, "该角色已经存在");
        }
        Role role = parameter.copyTo(new Role());
        
        baseMapper.insert(role);
        
        // 回溯路径
        List<RoleResource> roleResources = parameter.getResources().stream()
                .map(resourceService::selectMultiTreePathByLeafId)
                .flatMap(Collection::stream)
                .map(Resource::getId)
                .distinct()
                .map((resourceId) -> new RoleResource()
                        .setRoleId(role.getId())
                        .setResourceId(resourceId))
                .collect(Collectors.toList());
        
        roleResourceService.saveBatch(roleResources);
        
        return true;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = NotRollbackException.class)
    public boolean update(Serializable id, UpdateRole parameter) throws NotRollbackException {
        Role role = getById(id);
        if (role == null) {
            throw ResponseException.wrap(NotRollbackException.class, "角色不存在");
        }
        parameter.copyNotNullTo(role);
        
        // 回溯路径
        List<RoleResource> roleResources = parameter.getResources().stream()
                .map(resourceService::selectMultiTreePathByLeafId)
                .flatMap(Collection::stream)
                .map(Resource::getId)
                .distinct()
                .map((resourceId) -> new RoleResource()
                        .setRoleId(role.getId())
                        .setResourceId(resourceId))
                .collect(Collectors.toList());
        baseMapper.updateById(role);
        
        LambdaQueryWrapper<RoleResource> queryWrapper = new LambdaQueryWrapper<RoleResource>()
                .eq(RoleResource::getRoleId, id);
        roleResourceService.remove(queryWrapper);
        roleResourceService.saveBatch(roleResources);
        return true;
    }
    
    
    @Override
    public RoleDetailVO detail(Serializable id) {
        Role role = baseMapper.selectById(id);
        // 关联资源列表
        List<Resource> resources = roleResourceService.selectMultiResourceByRoleId(id);
        // 填充到 VO
        return (RoleDetailVO) new RoleDetailVO()
                .setResources(resources)
                .fill(role);
    }
    
    @Override
    public Collection<Role> selectAll() {
        return baseMapper.selectList(new QueryWrapper<>());
    }
}
