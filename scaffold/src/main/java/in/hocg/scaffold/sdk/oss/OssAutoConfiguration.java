package in.hocg.scaffold.sdk.oss;

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
@EnableConfigurationProperties(OssProperties.class)
public class OssAutoConfiguration {
    
    private final OssProperties properties;
    
    @Bean
    @ConditionalOnProperty(prefix = "oss", name = "enabled")
    @ConditionalOnMissingBean
    public OssService ossClient() {
        OssProperties.Type type = properties.getType();
        OssService ossService;
        switch (type) {
            case AliYun:
                ossService = aliYunOss(properties.getAliYun());
                break;
            default:
                throw new UnsupportedOperationException("请配置 OSS 服务商");
        }
        log.debug("开启[{} OSS]", type.name());
        return ossService;
    }
    
    /**
     * 阿里云 OSS 服务
     * @param aliYunOss
     * @return
     */
    private OssService aliYunOss(OssProperties.AliYunOss aliYunOss) {
        OSSClient ossClient = new OSSClient(aliYunOss.getEndpoint(),
                aliYunOss.getAccessKey(),
                aliYunOss.getSecretAccess());
        return new AliYunOssService(ossClient);
    }
}
