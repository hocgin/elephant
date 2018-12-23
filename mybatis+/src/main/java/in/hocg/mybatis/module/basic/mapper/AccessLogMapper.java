package in.hocg.mybatis.module.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.mybatis.module.basic.entity.AccessLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [基础模块] 访问日志 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2018-12-23
 */
@Mapper
public interface AccessLogMapper extends BaseMapper<AccessLog> {

}
