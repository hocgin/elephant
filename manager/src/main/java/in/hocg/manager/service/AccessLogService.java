package in.hocg.manager.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import in.hocg.manager.model.po.AccessLogBody;
import in.hocg.manager.model.po.AccessLogInsert;
import in.hocg.manager.model.po.AccessLogUpdate;
import in.hocg.manager.model.vo.AccessLogDetailVO;
import in.hocg.mybatis.basic.condition.PostCondition;
import in.hocg.mybatis.module.basic.entity.AccessLog;
import in.hocg.scaffold.support.basis.parameter.IDs;

/**
 * <p>
 * [基础模块] 访问日志 服务类
 * </p>
 *
 * @author hocgin
 * @since 2018-12-23
 */
public interface AccessLogService extends IService<AccessLog> {
    
    /**
     * 分页查询
     * @param condition
     * @return
     */
    IPage<AccessLog> paging(PostCondition<AccessLogBody, AccessLog> condition);
    
    /**
     * 查询详情
     * @param id
     * @return
     */
    AccessLogDetailVO detail(String id);
    
    /**
     * 删除
     * @param parameter
     * @return
     */
    boolean delete(IDs parameter);
    
    /**
     * 更新
     * @param id
     * @param parameter
     * @return
     */
    boolean update(String id, AccessLogUpdate parameter);
    
    /**
     * 新增
     * @param parameter
     * @return
     */
    boolean insert(AccessLogInsert parameter);
}
