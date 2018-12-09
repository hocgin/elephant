package in.hocg.sample.database;

import in.hocg.basic.NodeMapper;
import in.hocg.basic.model.NodeModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hocgin on 2018/12/9.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@AllArgsConstructor
@Transactional
public class TreeService<M extends NodeModel, T extends NodeMapper<M>> {
    private T mapper;
    
    /**
     * 追加子节点
     *
     * @param parentId
     * @param newNode
     */
    public void appendChild(String parentId, M newNode) {
        mapper.appendChild(parentId, newNode);
    }
    
    /**
     * 添加兄弟节点
     *
     * @param id
     * @param newNode
     */
    public void afterChild(String id, M newNode) {
        mapper.afterChild(id, newNode);
    }
    
    /**
     * 获取当前节点及其一级子节点
     *
     * @param id
     * @return
     */
    public List<M> queryNodeAndChildren(String id) {
        return mapper.queryNodeAndChildren(id);
    }
    
    /**
     * 获取当前节点及其所有子节点(检索完整的树)
     *
     * @param id
     * @return
     */
    public List<M> queryAllChildren(String id) {
        return mapper.queryAllChildren(id);
    }
    
    /**
     * 检索所有叶子节点
     *
     * @return
     */
    public List<M> queryAllLeafNode() {
        return mapper.queryAllLeafNode();
    }
    
    /**
     * 根据叶子节点回溯路径
     *
     * @return
     */
    public List<M> queryTreeNodeForLeaf(String leafNodeId) {
        return mapper.queryTreeNodeForLeaf(leafNodeId);
    }
    
    /**
     * 检索所有节点的深度
     *
     * @return
     */
    public List<M> queryAllNodeDepth() {
        return mapper.queryAllNodeDepth();
    }
    
    /**
     * 检索子树深度
     *
     * @param id
     * @return
     */
    public List<M> queryTreeNodeDepth(String id) {
        return mapper.queryTreeNodeDepth(id);
    }
    
    /**
     * 删除节点及其子节点
     */
    public void emptyNode(String id) {
        mapper.emptyNode(id);
    }
    
    /**
     * 删除父节点不删除子节点, 子节点会向上移动
     *
     * @param id
     */
    public void deleteNode(String id) {
        mapper.deleteNode(id);
    }
    
    /**
     * 分析节点
     *
     * @return
     */
    public List<String> analysis() {
        return mapper.analysis();
    }
}
