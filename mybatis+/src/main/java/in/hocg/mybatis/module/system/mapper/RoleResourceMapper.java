package in.hocg.mybatis.module.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.mybatis.module.system.entity.Resource;
import in.hocg.mybatis.module.system.entity.RoleResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

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
    
    /**
     * 查询角色具有的资源
     * @param id
     * @return
     */
    List<Resource> selectMultiByRoleId(@Param("id") Serializable id);
}
