package in.hocg.sample.database;

import in.hocg.mybatis.basic.BaseService;
import in.hocg.sample.mybatis.example.entity.Tree;
import in.hocg.sample.mybatis.example.mapper.TreeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hocgin on 2018/12/9.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
public class TreeService extends BaseService<TreeMapper, Tree> {
    
    /**
     * 追加子节点
     *
     * @param parentId
     * @param newNode
     */
    public void addChildNode(String parentId, Tree newNode) {
        baseMapper.insertOneChildNode(parentId, newNode);
    }
    
    /**
     * 添加兄弟节点(该节点之后)
     *
     * @param id
     * @param newNode
     */
    public void addSiblingNode(String id, Tree newNode) {
        baseMapper.insertOneSiblingNode(id, newNode);
    }
    
    /**
     * 获取当前节点及其一级子节点
     *
     * @param id
     * @return
     */
    public List<Tree> queryNodeAndChildren(String id) {
        return baseMapper.selectMultiFullTree(id);
    }
    
    /**
     * 获取当前节点及其所有子节点(检索完整的树)
     *
     * @param id
     * @return
     */
    public List<Tree> queryAllChildren(String id) {
        return baseMapper.selectAllChildren(id);
    }
    
    /**
     * 检索所有叶子节点
     *
     * @return
     */
    public List<Tree> queryAllLeafNode() {
        return baseMapper.selectAllLeaf();
    }
    
    /**
     * 根据叶子节点回溯路径
     *
     * @return
     */
    public List<Tree> queryTreeNodeForLeaf(String leafNodeId) {
        return baseMapper.selectMultiTreePathByLeafId(leafNodeId);
    }
    
    /**
     * 检索所有节点的深度
     *
     * @return
     */
    public List<Tree> queryAllNodeDepth() {
        return baseMapper.selectAllNodeHasDepth();
    }
    
    /**
     * 检索子树深度
     *
     * @param id
     * @return
     */
    public List<Tree> queryTreeNodeDepth(String id) {
        return baseMapper.selectMultiTreeNodeHasDepth(id);
    }
    
    /**
     * 删除节点及其子节点
     */
    public void deleteNodes(String id) {
        baseMapper.deleteMultiNode(id);
    }
    
    /**
     * 删除父节点不删除子节点, 子节点会向上移动
     *
     * @param id
     */
    public void deleteNode(String id) {
        baseMapper.deleteOneNode(id);
    }
    
    /**
     * 分析节点
     *
     * @return
     */
    public List<String> analysis() {
        return baseMapper.analysis();
    }
}
