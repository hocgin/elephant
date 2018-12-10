package in.hocg.manager.service;

import in.hocg.mybatis.entity.UserStaff;
import com.baomidou.mybatisplus.extension.service.IService;

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
