package in.hocg.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import in.hocg.manager.model.po.Login;
import in.hocg.manager.support.security.body.JwtToken;
import in.hocg.mybatis.module.user.entity.Account;

/**
 * Created by hocgin on 2018/12/22.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface AccountService extends IService<Account> {
    
    /**
     * 登陆
     * @param login
     * @return
     */
    JwtToken login(Login login);
}
