package in.hocg.mybatis.module.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.mybatis.module.example.entity.TestExample;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 例子表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2018-10-09
 */
@Mapper
public interface TestExampleMapper extends BaseMapper<TestExample> {

}
