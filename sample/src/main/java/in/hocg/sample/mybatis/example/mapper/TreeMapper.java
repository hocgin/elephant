package in.hocg.sample.mybatis.example.mapper;

import in.hocg.mybatis.basic.NodeMapper;
import in.hocg.sample.mybatis.example.entity.Tree;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by hocgin on 2018/12/9.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper
public interface TreeMapper extends NodeMapper<Tree> {
}
