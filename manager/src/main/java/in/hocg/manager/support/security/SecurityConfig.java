package in.hocg.manager.support.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by hocgin on 2018/10/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.debug(true);
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        
        // format: off
        http
                /**
                 * 定制化验证方式
                 */
//                .apply(weChatMinaAuthenticationConfig)
//                .and()
                
                /**
                 * 默认配置开关
                 */
                .csrf().disable()
                .httpBasic().disable()
                
                /**
                 * URL 授权管理
                 */
                .authorizeRequests()
//                .antMatchers(WeChatMinaAuthenticationFilter.URL).permitAll()
                .anyRequest().permitAll()
                .and()
        ;
        // format: on
    }
    
}
