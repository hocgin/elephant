package in.hocg.manager.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import in.hocg.mybatis.basic.condition.GetCondition;
import in.hocg.mybatis.basic.condition.PostCondition;
import in.hocg.mybatis.module.user.entity.Staff;

import java.security.Principal;
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
     *
     * @param username
     * @return
     */
    Optional<Staff> findByUsername(String username);
    
    /**
     * GET 方式分页查询所有员工
     *
     * @param condition
     * @return
     */
    IPage<Staff> page(GetCondition condition);
    
    /**
     * POST 方式分页查询所有员工
     *
     * @param condition
     * @return
     */
    IPage<Staff> page(PostCondition condition);
    
    /**
     * 返回员工的账号ID
     *
     * @param principal
     * @return ID
     * - 是员工, 返回账号id
     * - 非员工, 返回 null
     * @throws Exception 未登陆
     */
    String getAccountIdOfStaff(Principal principal) throws Exception;
}
