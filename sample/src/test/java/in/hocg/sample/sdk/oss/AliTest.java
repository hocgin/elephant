package in.hocg.sample.sdk.oss;

import com.aliyun.oss.OSSClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by hocgin on 2018/10/18.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AliTest {
    @Autowired
    OSSClient ossClient;
    
    @Test
    public void test() {
        log.debug("{}", ossClient != null);
    }
}
