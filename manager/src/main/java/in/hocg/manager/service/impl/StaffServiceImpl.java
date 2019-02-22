package in.hocg.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.manager.model.po.AddStaff;
import in.hocg.manager.model.po.QueryStaff;
import in.hocg.manager.model.po.UpdateStaff;
import in.hocg.manager.model.vo.StaffDetailVO;
import in.hocg.manager.service.AccountService;
import in.hocg.manager.service.RoleStaffService;
import in.hocg.manager.service.StaffService;
import in.hocg.mybatis.basic.BaseService;
import in.hocg.mybatis.basic.condition.GetCondition;
import in.hocg.mybatis.basic.condition.PostCondition;
import in.hocg.mybatis.module.ModelConstant;
import in.hocg.mybatis.module.system.entity.Role;
import in.hocg.mybatis.module.user.entity.Account;
import in.hocg.mybatis.module.user.entity.Staff;
import in.hocg.mybatis.module.user.mapper.StaffMapper;
import in.hocg.scaffold.lang.exception.NotRollbackException;
import in.hocg.scaffold.lang.exception.ResponseException;
import in.hocg.scaffold.support.basis.parameter.IDs;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * [用户模块] 员工表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2018-10-19
 */
@Service
@AllArgsConstructor
public class StaffServiceImpl extends BaseService<StaffMapper, Staff>
        implements StaffService {
    private final AccountService accountService;
    private final RoleStaffService roleStaffService;
    
    @Override
    public Optional<Staff> findByUsername(String username) {
        Wrapper<Staff> queryWrapper = new LambdaQueryWrapper<Staff>()
                .eq(Staff::getUsername, username);
        Staff userStaff = baseMapper.selectOne(queryWrapper);
        return Optional.ofNullable(userStaff);
    }
    
    @Override
    public IPage<Staff> page(GetCondition condition) {
        Page<Staff> page = condition.page();
        QueryWrapper<Staff> wrapper = condition.wrapper();
        return baseMapper.selectPage(page, wrapper);
    }
    
    @Override
    public IPage<Staff> page(PostCondition<QueryStaff, Staff> condition) {
        Page<Staff> page = condition.page();
        QueryStaff values = condition.getCondition();
        LambdaQueryWrapper<Staff> queryWrapper = condition.wrapper().lambda();
        if (Objects.nonNull(values)) {
            Optional<String> username = Optional.ofNullable(values.getUsername());
            Optional<String> nickname = Optional.ofNullable(values.getNickname());
            Optional<Integer> gender = Optional.ofNullable(values.getGender());
            queryWrapper.eq(gender.isPresent(), Staff::getGender, gender.orElse(null))
                    .like(username.isPresent(), Staff::getUsername, username.orElse(null))
                    .like(nickname.isPresent(), Staff::getNickname, nickname.orElse(null));
        }
        return baseMapper.selectPage(page, queryWrapper);
    }
    
    @Override
    public String getAccountIdOfStaff(String username) {
        Optional<Staff> staff = findByUsername(username);
        return staff.map(Staff::getId)
                .orElse(null);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateOneById(String id, UpdateStaff parameter) throws NotRollbackException {
        Staff staff = baseMapper.selectById(id);
        if (Objects.isNull(staff)) {
            throw ResponseException.wrap(NotRollbackException.class, "员工不存在");
        }
        parameter.copyNotNullTo(staff);
        baseMapper.updateById(staff);
    
        String[] rolesId = parameter.getRoles();
        if (Objects.nonNull(rolesId)) {
            // 清除旧角色
            roleStaffService.deleteAllWithStaffId(id);
            // 设置新角色
            roleStaffService.insertRoles(id, rolesId);
        }
        return true;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(AddStaff parameter) throws NotRollbackException {
        String username = parameter.getUsername();
        LambdaQueryWrapper<Staff> wrapper = new LambdaQueryWrapper<Staff>()
                .eq(Staff::getUsername, username);
        if (baseMapper.selectCount(wrapper) > 0) {
            throw ResponseException.wrap(NotRollbackException.class, "用户名已经存在");
        }
        
        Account account = new Account();
        account.setType(ModelConstant.ACCOUNT_TYPE_STAFF);
        accountService.save(account);
        Staff staff = parameter.copyTo(Staff.class);
        staff.setId(account.getId());
        int change = baseMapper.insert(staff);
        return change > 0;
    }
    
    @Override
    public boolean deletes(IDs parameter) {
        List<Serializable> ids = Arrays.asList(parameter.getId());
        // 删除账号表信息
        accountService.removeByIds(ids);
        // 删除员工表信息
        return baseMapper.deleteBatchIds(ids) > 0;
    }
    
    @Override
    public StaffDetailVO selectById(String id) {
        Staff result = baseMapper.selectById(id);
        result.setPassword(null);
        Collection<Role> roles = roleStaffService.findByAllRoleUseStaffId(id);
        return (StaffDetailVO) new StaffDetailVO()
                .setRoles(roles)
                .fill(roles);
    }
    
}
