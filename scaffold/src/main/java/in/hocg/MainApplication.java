package in.hocg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

/**
 * Created by hocgin on 2018/10/8.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@SpringBootApplication
public abstract class MainApplication {
    
    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
    
}
