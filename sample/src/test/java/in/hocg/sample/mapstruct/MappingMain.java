package in.hocg.sample.mapstruct;

import in.hocg.sample.mapstruct.bean.TestBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by hocgin on 2018/12/26.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@RunWith(JUnit4.class)
public class MappingMain {
    
    @Test
    public void test() {
        TestMapping instance = TestMapping.INSTANCE;
        TestBean.Test2 test2 = instance.t1ToT2(new TestBean.Test1("hocgin"));
        log.debug("{}", test2);
    }
}
