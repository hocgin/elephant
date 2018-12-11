package in.hocg.sample.database;

import in.hocg.mybatis.basic.model.NodeModel;
import in.hocg.sample.mybatis.example.entity.Tree;
import in.hocg.sample.mybatis.example.mapper.TreeMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

/**
 * Created by hocgin on 2018/12/9.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseTreeTest {
    
    @Autowired
    private TreeService<Tree, TreeMapper> treeService;
    
    @Test
    public void appendChild() {
        Tree newNode = new Tree();
        newNode.setName("OK2");
        newNode.setId(UUID.randomUUID().toString());
        treeService.appendChild("9", newNode);
    }
    
    @Test
    public void emptyNode() {
        treeService.emptyNode("7");
    }
    
    @Test
    public void deleteNode() {
        treeService.deleteNode("2");
    }
    
    @Test
    public void afterChild() {
        Tree newNode = new Tree();
        newNode.setId(UUID.randomUUID().toString());
        newNode.setName("jjjj");
        treeService.afterChild("6", newNode);
    }
    
    @Test
    public void queryNodeAndChildren() {
        List<Tree> trees = treeService.queryNodeAndChildren("6");
        System.out.println(trees);
    }
    
    @Test
    public void queryAllChildren() {
        List<Tree> trees = treeService.queryAllChildren("6");
        System.out.println(trees);
    }
    
    
    @Test
    public void queryAllLeafNode() {
        List<Tree> trees = treeService.queryAllLeafNode();
        System.out.println(trees);
    }
    
    
    @Test
    public void queryTreeNodeForLeaf() {
        List<Tree> trees = treeService.queryTreeNodeForLeaf("10");
        System.out.println(trees);
    }
    
    
    @Test
    public void queryAllNodeDepth() {
        List<Tree> nodes = treeService.queryAllNodeDepth();
        System.out.println(nodes);
        
        Tree root = NodeModel.buildTree(nodes.get(0), nodes);
        System.out.println(root);
    }
    
    
    @Test
    public void queryTreeNodeDepth() {
        List<Tree> trees = treeService.queryTreeNodeDepth("6");
        System.out.println(trees);
    }
    
    @Test
    public void analysis() {
        List<String> trees = treeService.analysis();
        System.out.println(trees.get(0));
    }
    
    
}
