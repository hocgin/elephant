package in.hocg.sample.mail;

import in.hocg.scaffold.support.mail.MailTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * Created by hocgin on 2018/10/17.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MainTest {
    
    @Autowired
    MailTemplate mailTemplate;
    
    @Test
    public void test() throws UnsupportedEncodingException, MessagingException {
        mailTemplate.send("578797748@qq.com", "测试", "你好");
    }
}
