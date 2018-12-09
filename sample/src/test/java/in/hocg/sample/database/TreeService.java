package in.hocg.sample.database;

import in.hocg.basic.NodeMapper;
import in.hocg.basic.model.NodeModel;
import in.hocg.module.example.entity.Tree;
import lombok.AllArgsConstructor;
import org.apache.ibatis.jdbc.SQL;
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
     * @param parentId
     * @param newNode
     */
    public void appendChild(String parentId, M newNode) {
        M node = mapper.selectById(parentId);
        int lft = node.getLft();
        mapper.appendChild(lft);
        newNode.setLft(lft + 1);
        newNode.setRgt(lft + 2);
        newNode.insert();
    }
    
    /**
     * 添加兄弟节点
     * @param id
     * @param newNode
     */
    public void afterChild(String id, M newNode) {
        M node = mapper.selectById(id);
        int rgt = node.getRgt();
        mapper.afterChild(rgt);
        
        newNode.setLft(rgt + 1);
        newNode.setRgt(rgt + 2);
        newNode.insert();
    }
    
    /**
     * 获取当前节点及其一级子节点
     * @param id
     * @return
     */
    public List<Tree> queryNodeAndChildren(String id) {
        return mapper.queryNodeAndChildren(id);
    }
    
    /**
     * 获取当前节点及其所有子节点(检索完整的树)
     * @param id
     * @return
     */
    public List<Tree> queryAllChildren(String id) {
        return mapper.queryAllChildren(id);
    }
    
    /**
     * 检索所有叶子节点
     * @return
     */
    public List<Tree> queryAllLeafNode() {
        return mapper.queryAllLeafNode();
    }
    
    /**
     * 根据叶子节点回溯路径
     * @return
     */
    public List<Tree> queryTreeNodeForLeaf(String leafNodeId) {
        return mapper.queryTreeNodeForLeaf(leafNodeId);
    }
    
    /**
     * 检索所有节点的深度
     * @return
     */
    public List<Tree> queryAllNodeDepth() {
        return mapper.queryAllNodeDepth();
    }
    
    /**
     * 检索子树深度
     * @param id
     * @return
     */
    public List<Tree> queryTreeNodeDepth(String id) {
        return mapper.queryTreeNodeDepth(id);
    }
    
    /**
     * 删除节点及其子节点
     */
    public void emptyNode(String id) {
        M node = mapper.selectById(id);
        int lft = node.getLft();
        int rgt = node.getRgt();
        int width = rgt - lft + 1;
        mapper.emptyNode(lft, rgt, width);
    }
    
    /**
     * 删除父节点不删除子节点, 子节点会向上移动
     * @param id
     */
    public void deleteNode(String id) {
        M node = mapper.selectById(id);
        int lft = node.getLft();
        int rgt = node.getRgt();
        int width = rgt - lft + 1;
        mapper.deleteNode(lft, rgt, width);
    }
    
    /**
     * 分析节点
     * @return
     */
    public List<String> analysis() {
        return mapper.analysis();
    }
    
    public SQL sql() {
        return new SQL() {{
            SELECT("*");
            
        }};
    }
}
