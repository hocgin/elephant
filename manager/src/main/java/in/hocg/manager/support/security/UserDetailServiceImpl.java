package in.hocg.manager.support.security;

import in.hocg.manager.service.RbacRoleStaffService;
import in.hocg.manager.service.UserStaffService;
import in.hocg.module.system.entity.RbacRole;
import in.hocg.module.system.entity.UserStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by hocgin on 2018/10/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {
    
    private final UserStaffService staffService;
    private final RbacRoleStaffService roleStaffService;
    
    @Autowired
    public UserDetailServiceImpl(UserStaffService staffService,
                                 RbacRoleStaffService roleStaffService) {
        this.staffService = staffService;
        this.roleStaffService = roleStaffService;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserStaff> staffOptional = staffService.findByUsername(username);
        
        staffOptional.orElseThrow(() ->
                new UsernameNotFoundException("用户名或密码错误")
        );
        
        UserStaff staff = staffOptional.get();
        Collection<RbacRole> roles = roleStaffService.findByAllRoleUseStaffId(staff.getId());
        String[] roleMark = roles.stream()
                .map(RbacRole::getIdentification)
                .distinct().toArray(String[]::new);
        
        return new User(staff.getUsername(),
                staff.getPassword(),
                staff.getEnabled(),
                staff.getExpired(),
                true,
                staff.getLocked(),
                AuthorityUtils.createAuthorityList(roleMark)
        );
    }
}
