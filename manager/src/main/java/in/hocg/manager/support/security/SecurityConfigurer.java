package in.hocg.manager.support.security;

import in.hocg.manager.support.security.authentication.jwt.JwtAuthenticationConfigurer;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by hocgin on 2018/10/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
    private final JwtAuthenticationConfigurer jwtAuthenticationConfig;
    
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    
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
        
        
        // @formatter:off
        http
                /*
                  URL 授权管理
                 */
                .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                /*
                  Session 管理
                 */
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                
                /*
                  异常处理
                 */
//                .exceptionHandling()
//                .authenticationEntryPoint(unauthorizedHandler)
//                .and()
                
                /*
                  定制化验证方式
                 */
                .apply(jwtAuthenticationConfig)
                .and()
                /*
                  默认配置开关
                 */
                .csrf().disable()
                .httpBasic().disable()
                .headers().cacheControl()
        ;
        
        // @formatter:on
    }
    
    
}
