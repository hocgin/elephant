package in.hocg;

import in.hocg.mybatis.enums.Gender;
import in.hocg.mybatis.module.system.entity.Staff;
import in.hocg.mybatis.module.system.mapper.StaffMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by hocgin on 2018/12/17.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Tests {
    @Autowired
    StaffMapper staffMapper;
    
    @Test
    public void test() {
        Staff test1 = staffMapper.selectById("test1");
    
        Staff entity = new Staff();
        entity.setId("tes2");
        entity.setUsername("gg");
        entity.setPassword("gg");
        entity.setNickName("gg");
        entity.setGender(Gender.Boy);
        staffMapper.insert(entity);
    
        Staff test22 = staffMapper.selectById("tes2");
        System.out.println("1");
    }
}
