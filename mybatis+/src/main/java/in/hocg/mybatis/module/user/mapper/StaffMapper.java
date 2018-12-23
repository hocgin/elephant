package in.hocg.mybatis.module.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.mybatis.module.user.entity.Staff;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [用户模块] 员工表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2018-10-19
 */
@Mapper
public interface StaffMapper extends BaseMapper<Staff> {

}
