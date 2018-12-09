package in.hocg.basic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hocgin on 2018/12/9.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface NodeMapper<M> extends BaseMapper<M> {
    
    /**
     * 删除节点及其所有子节点
     *
     * @return
     */
    int emptyNode(@Param("id") Serializable id);
    
    /**
     * 删除父节点不删除子节点, 子节点会向上移动
     *
     * @param id
     */
    void deleteNode(@Param("id") Serializable id);
    
    /**
     * 获取当前节点及其一级子节点
     *
     * @param id
     * @return
     */
    List<M> queryNodeAndChildren(@Param("id") Serializable id);
    
    /**
     * 获取当前节点及其所有子节点(检索完整的树)
     *
     * @param id
     * @return
     */
    List<M> queryAllChildren(@Param("id") Serializable id);
    
    /**
     * 检索所有叶子节点
     *
     * @return
     */
    List<M> queryAllLeafNode();
    
    /**
     * 根据叶子节点回溯路径
     *
     * @param id
     * @return
     */
    List<M> queryTreeNodeForLeaf(@Param("id") Serializable id);
    
    /**
     * 检索所有节点的深度
     *
     * @return
     */
    List<M> queryAllNodeDepth();
    
    /**
     * 检索子树深度
     *
     * @param id
     * @return
     */
    List<M> queryTreeNodeDepth(@Param("id") Serializable id);
    
    /**
     * 分析节点
     *
     * @return
     */
    List<String> analysis();
    
    /**
     * 追加子节点
     *
     * @param id
     * @param node
     */
    void appendChild(@Param("id") Serializable id, @Param("node") M node);
    
    /**
     * 添加兄弟节点
     *
     * @param id
     * @param node
     */
    void afterChild(@Param("id") Serializable id, @Param("node") M node);
}
