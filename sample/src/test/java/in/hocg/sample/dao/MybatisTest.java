package in.hocg.sample.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import in.hocg.module.example.entity.TestExample;
import in.hocg.module.example.mapper.TestExampleMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by hocgin on 2018/10/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {
    @Autowired
    TestExampleMapper mapper;
    @Test
    public void test() {
        TestExample testExample = mapper.selectById(13);
        log.debug("testExample {}", testExample);
        List<Map<String, Object>> list = mapper.selectMaps(new QueryWrapper<>());
        log.debug("list {}", list.size());
    }
}
