package in.hocg.scaffold.util;

import in.hocg.scaffold.constant.Charset;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseKit {
    public static HttpServletResponse get() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
    }
    
    /**
     * 指定输出 UTF-8
     * @param response
     * @return
     */
    public static ServletResponse utf8(ServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding(Charset.UTF_8);
        return response;
    }
}
