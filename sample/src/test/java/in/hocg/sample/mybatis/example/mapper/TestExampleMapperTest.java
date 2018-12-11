package in.hocg.sample.mybatis.example.mapper;

import in.hocg.mybatis.module.system.mapper.RoleStaffMapper;
import in.hocg.sample.mybatis.example.entity.TestExample;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}