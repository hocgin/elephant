package in.hocg.scaffold.sdk.ip;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by hocgin on 2018/9/22.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@NoArgsConstructor
public class IpToAddress {
    
    /**
     * ret : ok
     * ip : 106.122.172.244
     * data : ["中国","福建","厦门","电信","",""]
     */
    
    private String ret;
    private String ip;
    private List<String> data;
    
    public String getAddressString() {
        return String.join(",", data);
    }
}
