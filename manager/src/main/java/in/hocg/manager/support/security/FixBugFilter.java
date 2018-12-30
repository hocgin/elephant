package in.hocg.manager.support.security;

import in.hocg.scaffold.support.http.Result;
import in.hocg.scaffold.util.ResponseKit;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by hocgin on 2018/12/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class FixBugFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (BadCredentialsException e) {
            // 处理 Token 失效问题
            ResponseKit.utf8(response).getWriter()
                    .write(Result.error(e.getLocalizedMessage()).json());
        }
    }
}