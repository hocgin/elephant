package in.hocg.scaffold.sdk.oss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by hocgin on 2018/10/18.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ConfigurationProperties(prefix = "ali.oss", ignoreUnknownFields = true)
public class AliOSSProperties {
    private boolean enabled = false;
    private String accessKey;
    private String secretAccess;
    private String endpoint;
}
