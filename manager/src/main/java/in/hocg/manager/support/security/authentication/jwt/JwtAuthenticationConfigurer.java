package in.hocg.manager.support.security.authentication.jwt;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2018/10/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@AllArgsConstructor
public class JwtAuthenticationConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final UserDetailsService userDetailsService;
    private final DefaultSuccessHandler successHandler;
    private final DefaultFailureHandler failureHandler;
    private final JwtTokenProvider tokenProvider;
    
    @Override
    public void configure(HttpSecurity builder) throws Exception {
        
        JwtAuthenticationFilter authenticationFilter = new JwtAuthenticationFilter();
        AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
        authenticationFilter.setAuthenticationManager(authenticationManager);
        authenticationFilter.setAuthenticationSuccessHandler(successHandler);
        authenticationFilter.setAuthenticationFailureHandler(failureHandler);
        
        JwtAuthenticationProvider authenticationProvider = new JwtAuthenticationProvider(userDetailsService);
    
        builder.authenticationProvider(authenticationProvider)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilter(new UnauthorizedFilter(authenticationManager, tokenProvider));
    }
}
