package in.hocg.scaffold.util;

import in.hocg.scaffold.constant.Charset;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hocgin on 2018/8/20.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@UtilityClass
public class ResponseKit {
    public static HttpServletResponse get() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
    }
    
    /**
     * 指定输出 UTF-8
     * @param response
     * @return
     */
    public static HttpServletResponse utf8(ServletResponse response) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setCharacterEncoding(Charset.UTF_8);
        return httpServletResponse;
    }
}
