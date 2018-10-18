package in.hocg.scaffold.sdk.oss;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Autowired;
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
@Configuration
@ConditionalOnProperty(prefix = "ali.oss", name = "enabled")
@EnableConfigurationProperties(AliOSSProperties.class)
public class AliOSSAutoConfiguration {
    
    private final AliOSSProperties properties;
    
    @Autowired
    public AliOSSAutoConfiguration(AliOSSProperties properties) {
        this.properties = properties;
    }
    
    @Bean
    @ConditionalOnMissingBean
    public OSSClient ossClient() {
        return new OSSClient(properties.getEndpoint(),
                properties.getAccessKey(),
                properties.getSecretAccess());
    }
}
