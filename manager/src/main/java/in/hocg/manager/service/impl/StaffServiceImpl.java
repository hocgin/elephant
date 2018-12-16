package in.hocg.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.manager.service.StaffService;
import in.hocg.mybatis.basic.BaseService;
import in.hocg.mybatis.basic.condition.GetCondition;
import in.hocg.mybatis.module.system.entity.Staff;
import in.hocg.mybatis.module.system.mapper.StaffMapper;
import org.springframework.stereotype.Service;

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
        QueryWrapper<Staff> wrapper = queryWrapper().eq(Staff.USERNAME, username);
        Staff userStaff = baseMapper.selectOne(wrapper);
        return Optional.ofNullable(userStaff);
    }
    
    @Override
    public IPage<Staff> findAll(GetCondition condition) {
        Page<Staff> page = condition.page();
        QueryWrapper<Staff> wrapper = condition.wrapper();
        return baseMapper.selectPage(page, wrapper);
    }
}
