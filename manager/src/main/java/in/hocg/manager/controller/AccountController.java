package in.hocg.manager.controller;

import in.hocg.manager.model.parameter.Login;
import in.hocg.manager.support.security.body.JwtToken;
import in.hocg.scaffold.support.basis.BaseController;
import in.hocg.scaffold.support.http.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.visola.spring.security.tokenfilter.TokenService;

/**
 * Created by hocgin on 2018/12/30.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Controller
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController extends BaseController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    
    /**
     * POST /staff/token
     * 登陆
     *
     * @param parameter
     * @return
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseEntity postToken(@RequestBody Login parameter) {
        String password = parameter.getPassword();
        String username = parameter.getUsername();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username,
                password
        );
        
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        String token = tokenService.generateToken(authentication);
        return Result.success(new JwtToken(token)).asResponseEntity();
    }
}
