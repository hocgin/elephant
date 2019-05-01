package in.hocg.mybatis.module.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.mybatis.module.user.entity.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by hocgin on 2018/12/22.
 * email: hocgin@gmail.com
 * [用户模块] 账号表 Mapper 接口
 *
 * @author hocgin
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
