package in.hocg.scaffold;

import in.hocg.scaffold.support.aspect.log.DefaultLogRepository;
import in.hocg.scaffold.support.aspect.log.LogRepository;
import in.hocg.scaffold.support.json.JsonReturnValueHandler;
import in.hocg.scaffold.support.mail.MailTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.client.RestTemplate;

/**
 * @author hocgin
 * @date 18-10-8
 **/
@Slf4j
@Configuration
public class Starter {
    
    @Bean
    public JsonReturnValueHandler jsonReturnValueHandler() {
        // @JSON / @JSONs 过滤器
        return new JsonReturnValueHandler();
    }
    
    @Bean
    @ConditionalOnMissingBean(LogRepository.class)
    public LogRepository logRepository() {
        // @ILog 存储器
        return new DefaultLogRepository();
    }
    
    @Bean
    @ConditionalOnBean(JavaMailSender.class)
    public MailTemplate mailTemplate(@Value("${spring.mail.username}") String username,
                                     JavaMailSender javaMailSender) {
        // 邮件服务
        return new MailTemplate(username, "官方邮件", javaMailSender);
    }
    
    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        // Rest 客户端
        return new RestTemplate(factory);
    }
    
    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        
        //单位为ms
        factory.setReadTimeout(10000);
        
        //单位为ms
        factory.setConnectTimeout(10000);
        return factory;
    }
    
}
