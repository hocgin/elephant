package in.hocg.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.manager.model.po.AccessLogBody;
import in.hocg.manager.model.po.AccessLogInsert;
import in.hocg.manager.model.po.AccessLogUpdate;
import in.hocg.manager.model.vo.AccessLogDetailVO;
import in.hocg.manager.service.AccessLogService;
import in.hocg.mybatis.basic.BaseService;
import in.hocg.mybatis.basic.condition.PostCondition;
import in.hocg.mybatis.basic.model.DefaultModel;
import in.hocg.mybatis.module.basic.entity.AccessLog;
import in.hocg.mybatis.module.basic.mapper.AccessLogMapper;
import in.hocg.scaffold.support.aspect.log.Level;
import in.hocg.scaffold.support.basis.parameter.IDs;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

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
    
    @Override
    public IPage<AccessLog> paging(PostCondition<AccessLogBody, AccessLog> condition) {
        AccessLogBody values = condition.getCondition();
        LambdaQueryWrapper<AccessLog> wrapper = condition.wrapper().lambda();
        if (Objects.nonNull(values)) {
            LocalDateTime[] createdAt = values.getCreatedAt();
            String uri = values.getUri();
            String visitor = values.getVisitor();
            Optional<Level> level = Optional.ofNullable(values.getLevel());
            wrapper.like(Strings.isNotBlank(uri), AccessLog::getUri, uri)
                    .eq(Strings.isNotBlank(visitor), AccessLog::getVisitor, visitor)
                    .eq(level.isPresent(), AccessLog::getLevel, level.orElse(Level.Unknown).name());
            if (Objects.nonNull(createdAt)) {
                wrapper.between(DefaultModel::getCreatedAt, createdAt[0], createdAt[1]);
            }
        }
        return baseMapper.selectPage(condition.page(), wrapper);
    }
    
    @Override
    public AccessLogDetailVO detail(String id) {
        AccessLog accessLog = baseMapper.selectById(id);
        return (AccessLogDetailVO) new AccessLogDetailVO().fill(accessLog);
    }
    
    @Override
    public boolean delete(IDs parameter) {
        throw new UnsupportedOperationException("不能支持该操作");
    }
    
    @Override
    public boolean update(String id, AccessLogUpdate parameter) {
        throw new UnsupportedOperationException("不能支持该操作");
    }
    
    @Override
    public boolean insert(AccessLogInsert parameter) {
        throw new UnsupportedOperationException("不能支持该操作");
    }
}
