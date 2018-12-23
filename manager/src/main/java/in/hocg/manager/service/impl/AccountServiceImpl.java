package in.hocg.manager.service.impl;

import in.hocg.manager.service.AccountService;
import in.hocg.mybatis.basic.BaseService;
import in.hocg.mybatis.module.user.entity.Account;
import in.hocg.mybatis.module.user.mapper.AccountMapper;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2018/12/22.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
public class AccountServiceImpl extends BaseService<AccountMapper, Account>
        implements AccountService {
}
