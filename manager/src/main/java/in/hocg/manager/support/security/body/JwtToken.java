package in.hocg.manager.support.security.body;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * Created by hocgin on 2018/10/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ToString
@AllArgsConstructor
public class JwtToken {
    
    private String token;
    
}
