package in.hocg.scaffold.sdk.oss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by hocgin on 2018/12/12.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ConfigurationProperties(prefix = "oss")
public class OssProperties {
    private boolean enabled = false;
    private Type type = Type.AliYun;
    private AliYunOss aliYun;
    
    @Data
    static class AliYunOss {
        private String accessKey;
        private String secretAccess;
        private String endpoint;
    }
    
    enum Type {
        AliYun,
    }
}
