package in.hocg.scaffold.sdk.aliyun;

import com.aliyun.oss.OSSClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hocgin on 2018/10/18.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(AliYunProperties.class)
public class AliYunAutoConfiguration {
    
    private final AliYunProperties properties;
    
    @Bean
    @ConditionalOnProperty(prefix = "ali-yun.oss", name = "enabled")
    @ConditionalOnMissingBean
    public OSSClient ossClient() {
        log.debug("开启[AliYun OSS]");
        AliYunProperties.Oss oss = properties.getOss();
        return new OSSClient(oss.getEndpoint(),
                oss.getAccessKey(),
                oss.getSecretAccess());
    }
}
