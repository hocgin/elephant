package in.hocg.sample.database;

import in.hocg.sample.mybatis.example.entity.Tree;
import in.hocg.sample.mybatis.example.mapper.TreeMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hocgin on 2018/12/11.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisJTest {
    
    @Autowired
    private TreeMapper treeMapper;
    
    @Test
    @Transactional
    public void findByStateTest() {
        List<String> analysis = treeMapper.analysis();
        log.debug(analysis.toString());
        Tree tree = new Tree();
        tree.setName("666");
        treeMapper.insert(tree);
    }
}
