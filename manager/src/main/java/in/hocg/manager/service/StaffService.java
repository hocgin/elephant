package in.hocg.manager.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import in.hocg.mybatis.basic.condition.GetCondition;
import in.hocg.mybatis.module.system.entity.Staff;

import java.util.Optional;

/**
 * <p>
 * [用户模块] 员工表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2018-10-19
 */
public interface StaffService extends IService<Staff> {
    
    /**
     * 根据`username`查找员工信息
     * @param username
     * @return
     */
    Optional<Staff> findByUsername(String username);
    
    /**
     * 查询所有员工
     * @return
     * @param condition
     */
    IPage<Staff> findAll(GetCondition condition);
}
