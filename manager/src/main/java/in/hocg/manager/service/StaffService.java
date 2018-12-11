package in.hocg.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import in.hocg.mybatis.module.system.entity.UserStaff;

import java.util.Optional;

/**
 * <p>
 * [用户模块] 员工表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2018-10-19
 */
public interface StaffService extends IService<UserStaff> {
    
    Optional<UserStaff> findByUsername(String username);
}
