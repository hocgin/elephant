package in.hocg.scaffold;

import in.hocg.scaffold.support.json.JsonReturnValueHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author hocgin
 * @date 18-10-8
 **/
@Slf4j
@Configuration
@AllArgsConstructor
public class WebConfigurer implements WebMvcConfigurer {
    private final JsonReturnValueHandler jsonReturnValueHandler;
    
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        log.debug("加载 @JSON 和 @JSONs 的返回值过滤器");
        returnValueHandlers.add(jsonReturnValueHandler);
    }
    
}
