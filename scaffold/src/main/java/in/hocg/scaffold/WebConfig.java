package in.hocg.scaffold;

import in.hocg.scaffold.support.json.JsonReturnValueHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author hocgin
 * @date 18-10-8
 **/
@Slf4j
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    private final JsonReturnValueHandler jsonReturnValueHandler;
    
    @Autowired
    @Lazy
    public WebConfig(JsonReturnValueHandler jsonReturnValueHandler) {
        this.jsonReturnValueHandler = jsonReturnValueHandler;
    }
    
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        log.debug("加载 @JSON 和 @JSONs 的返回值过滤器");
        returnValueHandlers.add(jsonReturnValueHandler);
    }
    
}
