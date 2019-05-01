package in.hocg.manager.service.impl;

import in.hocg.manager.service.ResourceService;
import in.hocg.mybatis.module.system.entity.Resource;
import in.hocg.scaffold.exception.NotRollbackException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by hocgin on 2019/1/1.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceServiceImplTest {
    @Autowired
    private ResourceService resourceService;
    
    @Test
    public void findResourceTreeByUsername() {
    }
    
    @Test
    public void findAllByUsername() {
    }
    
    @Test
    public void deleteNode() {
    }
    
    @Test
    public void deleteNodes() {
    }
    
    @Test
    public void addChildNode() throws NotRollbackException {
        Resource resource = new Resource();
        resource.setName("test")
                .setIcon("test-icon")
                .setEnabled(true)
                .setMethod("POST")
                .setPath("/baidu")
                .setType(1)
        ;
        resourceService.insertOneChildNode("root", resource);
    }
    
    @Test
    public void addSiblingNode() {
    }
    
    @Test
    public void findAll() {
    }
}