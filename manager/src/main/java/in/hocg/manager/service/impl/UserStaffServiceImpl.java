package in.hocg.manager.service.impl;

import in.hocg.manager.service.UserStaffService;
import in.hocg.module.system.entity.UserStaff;
import in.hocg.module.system.mapper.UserStaffMapper;
import in.hocg.basic.BaseService;
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
public class UserStaffServiceImpl extends BaseService<UserStaffMapper, UserStaff>
        implements UserStaffService {
    
    @Override
    public Optional<UserStaff> findByUsername(String username) {
        UserStaff userStaff = baseMapper.selectOne(queryWrapper().eq(UserStaff.USERNAME, username));
        return Optional.ofNullable(userStaff);
    }
}
