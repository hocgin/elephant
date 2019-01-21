package in.hocg.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.manager.service.StaffService;
import in.hocg.mybatis.basic.BaseService;
import in.hocg.mybatis.basic.condition.GetCondition;
import in.hocg.mybatis.basic.condition.PostCondition;
import in.hocg.mybatis.module.user.entity.Staff;
import in.hocg.mybatis.module.user.mapper.StaffMapper;
import in.hocg.scaffold.lang.exception.NotRollbackException;
import in.hocg.scaffold.lang.exception.ResponseException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

/**
 * <p>
 * [用户模块] 员工表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2018-10-19
 */
@Service
public class StaffServiceImpl extends BaseService<StaffMapper, Staff>
        implements StaffService {
    
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
    public IPage<Staff> page(PostCondition condition) {
        Page<Staff> page = condition.page();
        QueryWrapper<Staff> wrapper = condition.wrapper();
        return baseMapper.selectPage(page, wrapper);
    }
    
    @Override
    public String getAccountIdOfStaff(Principal principal) throws Exception {
        String username = principal.getName();
        if (Strings.isBlank(username)) {
            throw ResponseException.wrap(NotRollbackException.class, "请先进行登陆");
        }
        Optional<Staff> staff = findByUsername(username);
        return staff.map(Staff::getAccount)
                .orElse(null);
    }
}
