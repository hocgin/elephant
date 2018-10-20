package in.hocg.scaffold;

import in.hocg.scaffold.support.aspect.log.DefaultLogRepository;
import in.hocg.scaffold.support.aspect.log.LogRepository;
import in.hocg.scaffold.support.cache.CacheService;
import in.hocg.scaffold.support.interceptor.AntiReplayInterceptor;
import in.hocg.scaffold.support.json.JsonReturnValueHandler;
import in.hocg.scaffold.support.mail.MailTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
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
    @Lazy
    public AntiReplayInterceptor antiReplayInterceptor(CacheService cacheService) {
        // 防重放攻击
        return new AntiReplayInterceptor(cacheService);
    }
    
    /**
     * 邮件服务
     *
     * @param username
     * @param javaMailSender
     * @return
     */
    @Bean
    @ConditionalOnBean(JavaMailSender.class)
    public MailTemplate mailTemplate(@Value("${spring.mail.username}") String username,
                                     JavaMailSender javaMailSender) {
        return new MailTemplate(username, "官方邮件", javaMailSender);
    }
    
    /**
     * Rest 客户端
     * @param factory
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
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
