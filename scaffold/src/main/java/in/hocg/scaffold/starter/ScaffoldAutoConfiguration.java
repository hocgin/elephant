package in.hocg.scaffold.starter;

import in.hocg.scaffold.support.cache.CacheService;
import in.hocg.scaffold.support.filter.AntiReplayFilter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hocgin on 2018/12/15.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(ScaffoldProperties.class)
public class ScaffoldAutoConfiguration {
    private final ScaffoldProperties properties;
    
    @Bean
    @ConditionalOnProperty(prefix = "scaffold.anti-replay", name = "enabled")
    @ConditionalOnMissingBean
    public AntiReplayFilter antiReplayFilter(CacheService cacheService) {
        log.debug("开启防重放攻击");
        List<String> ignoreUrls = Arrays.stream(properties.getAntiReplay()
                .getIgnoreUrl())
                .collect(Collectors.toList());
        return new AntiReplayFilter(cacheService, ignoreUrls);
    }
}
