package in.hocg.manager.controller;

/**
 * Created by hocgin on 2018/12/16.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.manager.service.ResourceService;
import in.hocg.manager.service.StaffService;
import in.hocg.manager.support.security.body.JwtToken;
import in.hocg.mybatis.basic.condition.GetCondition;
import in.hocg.mybatis.module.system.entity.Resource;
import in.hocg.mybatis.module.system.entity.Staff;
import in.hocg.scaffold.support.basis.BaseController;
import in.hocg.scaffold.support.http.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.visola.spring.security.tokenfilter.TokenService;

import java.security.Principal;

@RestController
@RequestMapping("/staff")
@AllArgsConstructor
public class StaffController extends BaseController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final StaffService staffService;
    private final ResourceService resourceService;
    
    /**
     * POST /staff/token
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<Result> postToken(@RequestParam("username") String username,
                                            @RequestParam("password") String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username,
                password
        );
        
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        String token = tokenService.generateToken(authentication);
        return ResponseEntity.ok(Result.success(new JwtToken(token)));
    }
    
    /**
     * GET /staff/menu
     *
     * @return
     */
    @GetMapping("/menu")
    public ResponseEntity<Result> getMenu(Principal principal) {
        String username = principal.getName();
        Resource tree = resourceService.findAllByUsername(username);
        return ResponseEntity.ok(Result.success(tree));
    }
    
    /**
     * GET /staff
     *
     * @param condition
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> get(GetCondition condition) {
        IPage<Staff> all = staffService.findAll(condition);
        return ResponseEntity.ok(Result.success(all));
    }
}
