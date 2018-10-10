package in.hocg;

import in.hocg.scaffold.support.interceptor.AntiReplayInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author hocgin
 * @date 18-10-8
 **/
@Slf4j
@Configuration
public class WebIConfig implements WebMvcConfigurer {
    private final AntiReplayInterceptor antiReplayInterceptor;
    
    @Autowired
    @Lazy
    public WebIConfig(AntiReplayInterceptor antiReplayInterceptor) {
        this.antiReplayInterceptor = antiReplayInterceptor;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.debug("Sample => 防重放攻击 {}", antiReplayInterceptor);
        registry.addInterceptor(antiReplayInterceptor)
                .addPathPatterns("/**");
    }
    
}
