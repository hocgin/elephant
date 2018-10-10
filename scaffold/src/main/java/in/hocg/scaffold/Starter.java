package in.hocg.scaffold;

import in.hocg.scaffold.support.aspect.log.DefaultLogRepository;
import in.hocg.scaffold.support.aspect.log.LogRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hocgin
 * @date 18-10-8
 **/
@Configuration
public class Starter {
    
    @Bean
    @ConditionalOnMissingBean(LogRepository.class)
    public LogRepository logRepository() {
        // @ILog 存储器
        return new DefaultLogRepository();
    }
    
}
