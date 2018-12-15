package in.hocg.mybatis.module.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.mybatis.module.system.entity.RoleResource;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [权限模块] 角色-员工 关联表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2018-10-21
 */
@Mapper
public interface RoleResourceMapper extends BaseMapper<RoleResource> {

}
