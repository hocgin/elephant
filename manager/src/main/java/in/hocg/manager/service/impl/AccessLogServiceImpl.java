package in.hocg.manager.service.impl;

import in.hocg.manager.service.AccessLogService;
import in.hocg.mybatis.basic.BaseService;
import in.hocg.mybatis.module.basic.entity.AccessLog;
import in.hocg.mybatis.module.basic.mapper.AccessLogMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * [基础模块] 访问日志 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2018-12-23
 */
@Service
public class AccessLogServiceImpl extends BaseService<AccessLogMapper, AccessLog>
        implements AccessLogService {

}
