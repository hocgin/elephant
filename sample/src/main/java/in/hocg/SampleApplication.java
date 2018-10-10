package in.hocg;

import in.hocg.bean.TestBean;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by hocgin on 2018/10/9.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class SampleApplication extends MainApplication {
    
    @Bean(initMethod = "init")
    public TestBean testBean() {
        return new TestBean();
    }
    
    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class);
    }
}
