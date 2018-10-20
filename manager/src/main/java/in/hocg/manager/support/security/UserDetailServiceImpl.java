package in.hocg.manager.support.security;

import in.hocg.manager.service.impl.UserStaffService;
import in.hocg.module.system.entity.UserStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by hocgin on 2018/10/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {
    
    private final UserStaffService userStaffService;
    
    @Autowired
    public UserDetailServiceImpl(UserStaffService userStaffService) {
        this.userStaffService = userStaffService;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserStaff> staffOptional = userStaffService.findByUsername(username);
        
        // 用户不存在
        if (staffOptional.isPresent()) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
    
        UserStaff staff = staffOptional.get();
        
    
        return new User(staff.getUsername(),
                staff.getPassword(),
                staff.getEnabled(),
                staff.getExpired(),
                true,
                staff.getLocked(),
                AuthorityUtils.createAuthorityList("ROLE_USER")
        );
    }
}
