package in.hocg.manager.controller;

/**
 * Created by hocgin on 2018/12/16.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */

import in.hocg.manager.service.ResourceService;
import in.hocg.manager.service.StaffService;
import in.hocg.manager.support.security.body.JwtToken;
import in.hocg.mybatis.module.system.entity.Resource;
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
    
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result> login(@RequestParam("username") String username,
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
     * @return
     */
    @GetMapping("/menu")
    public ResponseEntity<Result> getMenu(Principal principal) {
        String username = principal.getName();
        Resource tree = resourceService.findAllByUsername(username);
        return ResponseEntity.ok(Result.success(tree));
    }
}
