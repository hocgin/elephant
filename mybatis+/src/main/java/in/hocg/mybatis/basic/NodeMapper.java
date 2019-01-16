package in.hocg.mybatis.basic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.mybatis.basic.model.NodeModel;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hocgin on 2018/12/9.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface NodeMapper<M extends NodeModel> extends BaseMapper<M> {
    
    /**
     * 删除节点及其所有子节点
     *
     * @param id
     * @return
     */
    int deleteNodes(@Param("id") Serializable id);
    
    /**
     * 删除指定节点, 并移动其子节点到该节点所在层级
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
     * 添加子节点(在该节点子代的最后)
     *
     * @param id
     * @param node
     */
    void addChildNode(@Param("id") Serializable id, @Param("node") M node);
    
    /**
     * 添加兄弟节点(在该节点之后)
     *
     * @param id
     * @param node
     */
    void addSiblingNode(@Param("id") Serializable id, @Param("node") M node);
    
    /**
     * 查找直属父节点
     * @param id
     * @return
     */
    List<M> selectOneParentById(@Param("id") Serializable id);
}
