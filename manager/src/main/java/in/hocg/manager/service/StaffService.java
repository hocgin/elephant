package in.hocg.manager.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import in.hocg.manager.model.po.CurrentAccountUpdate;
import in.hocg.manager.model.po.StaffBody;
import in.hocg.manager.model.po.StaffInsert;
import in.hocg.manager.model.po.StaffUpdate;
import in.hocg.manager.model.vo.StaffDetailVO;
import in.hocg.mybatis.basic.condition.PostCondition;
import in.hocg.mybatis.module.user.entity.Staff;
import in.hocg.scaffold.lang.exception.NotRollbackException;
import in.hocg.scaffold.support.basis.parameter.IDs;

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
     * POST 方式分页查询所有员工
     *
     * @param condition
     * @return
     */
    IPage<Staff> paging(PostCondition<StaffBody, Staff> condition);
    
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
    boolean update(String id, StaffUpdate parameter) throws NotRollbackException;
    
    /**
     * 新增
     * @param parameter
     * @return
     */
    boolean insert(StaffInsert parameter) throws NotRollbackException;
    
    /**
     * 删除
     * @param parameter
     * @return
     */
    boolean delete(IDs parameter);
    
    /**
     * 查看详情
     * @param id
     * @return
     */
    StaffDetailVO detail(String id);
    
    /**
     * 更新当前账号信息
     * @param name
     * @return
     */
    void updateCurrentAccount(String name, CurrentAccountUpdate body) throws NotRollbackException;
}
