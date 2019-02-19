package in.hocg.manager.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import in.hocg.manager.model.po.AddStaff;
import in.hocg.manager.model.po.QueryStaff;
import in.hocg.manager.model.po.UpdateStaff;
import in.hocg.mybatis.basic.condition.GetCondition;
import in.hocg.mybatis.basic.condition.PostCondition;
import in.hocg.mybatis.module.user.entity.Staff;
import in.hocg.scaffold.lang.exception.NotRollbackException;
import in.hocg.scaffold.support.basis.parameter.IDs;

import java.io.Serializable;
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
    IPage<Staff> page(PostCondition<QueryStaff, Staff> condition);
    
    /**
     * 返回员工的账号ID
     *
     * @param username
     * @return ID
     * - 是员工, 返回账号id
     * - 非员工, 返回 null
     * @throws Exception 未登陆
     */
    String getAccountIdOfStaff(String username);
    
    /**
     * 更新
     * @param id
     * @param parameter
     * @return
     */
    boolean updateOneById(Serializable id, UpdateStaff parameter) throws NotRollbackException;
    
    /**
     * 新增
     * @param parameter
     * @return
     */
    boolean insert(AddStaff parameter) throws NotRollbackException;
    
    /**
     * 删除
     * @param parameter
     * @return
     */
    boolean deletes(IDs parameter);
}
