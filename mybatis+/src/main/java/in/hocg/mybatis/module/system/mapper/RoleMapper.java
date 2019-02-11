package in.hocg.mybatis.module.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.mybatis.module.system.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [权限模块] 角色表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2018-10-21
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
