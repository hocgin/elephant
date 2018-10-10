package in.hocg.runner;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author hocgin
 * @date 18-10-10
 **/
@Slf4j
@Component
public class TestApplicationRunner implements ApplicationRunner {
    
    private final ObjectMapper objectMapper;
    
    @Autowired
    public TestApplicationRunner(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("@@TestApplicationRunner {}", objectMapper.writeValueAsString(args));
    }
}
