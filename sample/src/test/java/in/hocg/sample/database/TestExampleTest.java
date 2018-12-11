package in.hocg.sample.database;

import in.hocg.sample.mybatis.example.mapper.TestExampleMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2018/12/11.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestExampleTest {
    
    @Autowired
    TestExampleMapper testExampleMapper;
    
    @Autowired
    MessageSource messageSource;
    
    public ResponseEntity<String> index() {
        
        String message = messageSource.getMessage("welcome", null, LocaleContextHolder.getLocale());
        new in.hocg.sample.mybatis.example.entity.TestExample()
                .setName("hocgin")
                .setCreatedAt(LocalDateTime.now())
                .insert();
        return ResponseEntity.ok("OK");
    }
}
