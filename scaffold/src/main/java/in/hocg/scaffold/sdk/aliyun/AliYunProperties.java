package in.hocg.scaffold.sdk.aliyun;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by hocgin on 2018/12/12.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ConfigurationProperties(prefix = "ali-yun")
public class AliYunProperties {
    private Oss oss;
    
    @Data
    public static class Oss {
        private boolean enabled = false;
        private String accessKey;
        private String secretAccess;
        private String endpoint;
    }
}
