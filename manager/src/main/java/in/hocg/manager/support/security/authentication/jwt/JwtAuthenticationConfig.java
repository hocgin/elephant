package in.hocg.manager.support.security.authentication.jwt;

import org.springframework.beans.factory.annotation.Autowired;
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
public class JwtAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final UserDetailsService userDetailsService;
    private final DefaultSuccessHandler successHandler;
    private final DefaultFailureHandler failureHandler;
    private final JwtTokenProvider tokenProvider;
    
    @Autowired
    public JwtAuthenticationConfig(UserDetailsService userDetailsService,
                                   DefaultSuccessHandler successHandler,
                                   DefaultFailureHandler failureHandler,
                                   JwtTokenProvider tokenProvider) {
        this.userDetailsService = userDetailsService;
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.tokenProvider = tokenProvider;
    }
    
    @Override
    public void configure(HttpSecurity builder) throws Exception {
        
        JwtAuthenticationFilter authenticationFilter = new JwtAuthenticationFilter();
        AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
        authenticationFilter.setAuthenticationManager(authenticationManager);
        authenticationFilter.setAuthenticationSuccessHandler(successHandler);
        authenticationFilter.setAuthenticationFailureHandler(failureHandler);
        
        JwtAuthenticationProvider authenticationProvider = new JwtAuthenticationProvider(userDetailsService);
    
        builder.authenticationProvider(authenticationProvider)
                .addFilterAfter(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilter(new UnauthorizedFilter(authenticationManager, tokenProvider));
    }
}
