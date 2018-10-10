package in.hocg.scaffold.support.interceptor;

import in.hocg.scaffold.constant.Charset;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hocgin
 * @date 18-8-20
 **/
public abstract class SimpleHandlerInterceptor implements HandlerInterceptor {
    
    /**
     * 简单的复写 preHandle
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    abstract boolean preHandle(ServletWebRequest request,
                               HttpServletResponse response,
                               Object handler) throws Exception;
    
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        ServletWebRequest webRequest = new ServletWebRequest(request);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setCharacterEncoding(Charset.UTF_8);
        
        return preHandle(webRequest, response, handler);
    }
    
}
