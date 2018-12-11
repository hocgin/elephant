package in.hocg.mybatis.module.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.mybatis.module.system.entity.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * [权限模块] 资源表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2018-10-21
 */
@Mapper
@Component
public interface ResourceMapper extends BaseMapper<Resource> {

}
