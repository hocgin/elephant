package in.hocg.scaffold.sdk.ip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by hocgin on 2018/10/18.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
public class IPs {
    private final RestTemplate restTemplate;
    
    @Autowired
    public IPs(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    /**
     * IP 转化为 地址
     *
     * @param ip
     * @return
     */
    public IpToAddress toAddress(String ip) {
        String token = "34579df219c0eadf6c9f02f610c8169b";
        String url = String.format("http://api.ip138.com/query/?ip=%s&token=%s&datatype=jsonp", ip, token);
        return restTemplate.getForObject(url, IpToAddress.class);
    }
}
