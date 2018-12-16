package in.hocg.scaffold.support.filter;

import in.hocg.scaffold.constant.Charset;
import in.hocg.scaffold.support.http.Result;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hocgin
 * @date 18-8-20
 **/
public abstract class SimpleHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        
        ServletWebRequest webRequest = new ServletWebRequest(request, response);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setCharacterEncoding(Charset.UTF_8);
        
        try {
            if (preHandle(webRequest)) {
                filterChain.doFilter(request, response);
            }
        } catch (Exception e) {
            String result = Result.error(e.getMessage()).json();
            response.getWriter().write(result);
        }
    }
    
    /**
     * 简单的复写 preHandle
     *
     * @param servletWebRequest
     * @return
     */
    abstract boolean preHandle(ServletWebRequest servletWebRequest) throws Exception;
    
}
