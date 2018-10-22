package in.hocg.manager.support.security.authentication.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.hocg.manager.support.security.body.JwtToken;
import in.hocg.scaffold.support.http.Result;
import in.hocg.scaffold.util.ResponseKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hocgin on 2018/9/22.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
public class DefaultSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final ObjectMapper objectMapper;
    private final JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    public DefaultSuccessHandler(ObjectMapper objectMapper, JwtTokenProvider jwtTokenProvider) {
        this.objectMapper = objectMapper;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
//        String header = request.getHeader("Authorization");
        Result result = Result.success("Toekn -- 》")
                .setData(new JwtToken(jwtTokenProvider.generate(authentication)));
        ResponseKit.utf8(response).getWriter().write(objectMapper.writeValueAsString(
                result
        ));
    }
}
