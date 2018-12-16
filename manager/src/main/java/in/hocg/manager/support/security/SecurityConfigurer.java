package in.hocg.manager.support.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.visola.spring.security.tokenfilter.TokenAuthenticationFilter;

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
    private TokenAuthenticationFilter tokenAuthenticationFilter;
    
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
        
        /*
         全局配置
        */
        http.csrf().disable()
                .httpBasic().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers().cacheControl();
        /*
         配置拦截器
        */
        http.addFilterBefore(tokenAuthenticationFilter, BasicAuthenticationFilter.class);
        
        /*
         URL 授权管理
        */
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/staff/token").anonymous()
                .anyRequest().authenticated();
          /*
           异常处理
          */
//        http.exceptionHandling()
//                .authenticationEntryPoint(unauthorizedHandler);
    }
    
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
}
