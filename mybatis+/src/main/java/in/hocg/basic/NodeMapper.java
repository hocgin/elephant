package in.hocg.basic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.module.example.entity.Tree;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hocgin on 2018/12/9.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface NodeMapper<M> extends BaseMapper<M> {
    
    void appendChild(@Param("lft") int lft);
    
    int emptyNode(@Param("left") int left,
                  @Param("right") int right,
                  @Param("width") int width);
    
    void deleteNode(@Param("left") int lft,
                    @Param("right") int rgt,
                    @Param("width") int width);
    
    void afterChild(@Param("rgt") int rgt);
    
    List<Tree> queryNodeAndChildren(@Param("id") String id);
    
    List<Tree> queryAllChildren(@Param("id") String id);
    
    List<Tree> queryAllLeafNode();
    
    List<Tree> queryTreeNodeForLeaf(@Param("id") String id);
    
    List<Tree> queryAllNodeDepth();
    
    List<Tree> queryTreeNodeDepth(@Param("id") String id);
    
    List<String> analysis();
    
    
    void appendChild2(@Param("id")String id, @Param("node") M node);
}
