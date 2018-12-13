package in.hocg.sample.mybatis.example.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import in.hocg.mybatis.enums.Enable;
import in.hocg.mybatis.module.system.mapper.RoleStaffMapper;
import in.hocg.sample.mybatis.example.entity.TestExample;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

/**
 * Created by hocgin on 2018/12/11.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestExampleMapperTest {
    
    @Autowired
    TestExampleMapper testExampleMapper;
    @Autowired
    RoleStaffMapper roleStaffMapper;
    
    
    /**
     * 测试 Mybatis Plus 是否可以返回 Optional 类型
     * 结果: 不行, 当对象为 null 时, 返回的为 null
     */
    @Test
    public void findNull() {
        Optional<TestExample> aNull = testExampleMapper.findNull();
        System.out.println(aNull);
    }
    
    @Test
    public void findNull2() {
        TestExample null2 = testExampleMapper.findNull2();
        System.out.println(null2);
    }
    
    @Test
    public void findAll() {
        List<TestExample> all = testExampleMapper.selectList(new QueryWrapper<>());
        Assert.isTrue(all.size() == 0, "null");
    }
    
    @Test
    public void testDeleted() {
        TestExample entity = new TestExample();
        entity.setName("Ld");
        testExampleMapper.insert(entity);
        
        String id = entity.getId();
        log.debug("ID: {}", id);
        testExampleMapper.deleteById(id);
    }
    @Test
    public void testEnum() throws InterruptedException {
        TestExample entity = new TestExample();
        entity.setName("Ld");
        entity.setEnable(Enable.OFF);
        testExampleMapper.insert(entity);
    
        TestExample testExample = testExampleMapper.selectById(entity.getId());
        log.debug(testExample.getEnable().name());
    
    
        Thread.sleep(1000);
        testExample.setName("GG");
        testExampleMapper.updateById(testExample);
    }
}