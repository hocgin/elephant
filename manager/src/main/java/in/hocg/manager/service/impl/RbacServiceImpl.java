package in.hocg.manager.service.impl;

import in.hocg.manager.service.RbacService;
import in.hocg.manager.service.ResourceService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hocgin on 2018/12/17.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service("rbacService")
@RequiredArgsConstructor
public class RbacServiceImpl implements RbacService {
    
    @NonNull
    private ResourceService resourceService;
    
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        
        String username = ((String) authentication.getPrincipal());
        if ("anonymousUser".equals(username)) {
            return false;
        }
        String requestURI = request.getRequestURI();
        String requestMethod = request.getMethod();
        
        // todo: 后续需从缓存中读取
        return resourceService.selectMultiByUsername(username)
                .parallelStream()
                .filter(resource -> requestMethod.equalsIgnoreCase(resource.getMethod()))
                .anyMatch(resource -> {
                    String uri = resource.getPath();
                    return antPathMatcher.match(uri, requestURI);
                });
    }
    
}
