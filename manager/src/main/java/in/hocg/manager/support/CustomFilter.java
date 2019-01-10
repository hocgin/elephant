package in.hocg.manager.support;

import in.hocg.scaffold.support.http.wrapper.RequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hocgin on 2018/12/30.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
@WebFilter(filterName = "CustomFilter", urlPatterns = "/*")
public class CustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("开启自定义拦截器");
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // TODO: API 调试开启，临时处理 CORS
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("application/json; charset=utf-8");
        servletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:8000");
        servletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        servletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        servletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type,Authorization");
        servletResponse.setHeader("Access-Control-Expose-Headers", "*");
        // 防 XSS 注入
        chain.doFilter(new RequestWrapper((HttpServletRequest) request), servletResponse);
    }
    
    @Override
    public void destroy() {}
}
