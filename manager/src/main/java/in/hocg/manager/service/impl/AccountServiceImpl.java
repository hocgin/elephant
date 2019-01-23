package in.hocg.manager.service.impl;

import in.hocg.manager.model.po.Login;
import in.hocg.manager.service.AccountService;
import in.hocg.manager.support.security.body.JwtToken;
import in.hocg.mybatis.basic.BaseService;
import in.hocg.mybatis.module.user.entity.Account;
import in.hocg.mybatis.module.user.mapper.AccountMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.visola.spring.security.tokenfilter.TokenService;

/**
 * Created by hocgin on 2018/12/22.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@AllArgsConstructor
public class AccountServiceImpl extends BaseService<AccountMapper, Account>
        implements AccountService {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    
    @Override
    public JwtToken login(Login parameter) {
        String password = parameter.getPassword();
        String username = parameter.getUsername();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username,
                password
        );
    
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        String token = tokenService.generateToken(authentication);
        return new JwtToken(token);
    }
}
