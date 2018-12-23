package in.hocg.mybatis.module.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.mybatis.module.basic.entity.Organization;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [基础模块] 组织结构表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2018-10-21
 */
@Mapper
public interface OrganizationMapper extends BaseMapper<Organization> {
    
}
