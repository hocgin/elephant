package in.hocg.scaffold.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by hocgin on 2018/12/15.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ConfigurationProperties(prefix = "scaffold")
public class ScaffoldProperties {
    private AntiReplay antiReplay;
    
    @Data
    static class AntiReplay {
        private boolean enabled = false;
        private String[] ignoreUrl;
    }
}
