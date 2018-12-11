package in.hocg.mybatis.module.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.mybatis.module.system.entity.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * [用户模块] 员工表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2018-10-19
 */
@Mapper
@Component
public interface StaffMapper extends BaseMapper<Staff> {

}
