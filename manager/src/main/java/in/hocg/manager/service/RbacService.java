package in.hocg.manager.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hocgin on 2018/12/17.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface RbacService {
    /**
     * 是否具备该权限
     *
     * @param request
     * @param authentication
     * @return
     */
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
