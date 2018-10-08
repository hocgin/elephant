package in.hocg.scaffold;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

/**
 * @author hocgin
 * @date 18-10-8
 **/
@Configurable
public class Starter {
    
    @Bean
    public RequestContextListener requestContextListener() {
        System.out.println("扫描到了,......................................");
        return new RequestContextListener();
    }
}
