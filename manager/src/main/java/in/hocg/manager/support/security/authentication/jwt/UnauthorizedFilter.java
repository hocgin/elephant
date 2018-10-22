package in.hocg.manager.support.security.authentication.jwt;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by hocgin on 2018/10/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
public class UnauthorizedFilter extends BasicAuthenticationFilter {
    
    private JwtTokenProvider provider;
    
    public UnauthorizedFilter(AuthenticationManager authenticationManager,
                              JwtTokenProvider provider) {
        super(authenticationManager);
        this.provider = provider;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Optional<String> optionalToken = obtainToken(request);
        if (!optionalToken.orElse("").startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
    
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(optionalToken.get());
    
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    
        chain.doFilter(request, response);
    }
    
    private Optional<String> obtainToken(HttpServletRequest request) {
        return Optional.ofNullable(Strings.trimToNull(request.getHeader("token")));
    }
    
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if (!provider.validate(token)) {
            throw new UsernameNotFoundException("Token 过期");
        }
        String username = provider.getUsername(token);
    
        if (username != null) {
            return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
        }
        return null;
    }
}
