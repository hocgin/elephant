package in.hocg.sample.mybatis.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.sample.mybatis.example.entity.TestExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

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
