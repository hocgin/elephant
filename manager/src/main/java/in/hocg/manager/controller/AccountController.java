package in.hocg.manager.controller;

import in.hocg.manager.model.po.Login;
import in.hocg.manager.service.AccountService;
import in.hocg.manager.service.ResourceService;
import in.hocg.manager.service.StaffService;
import in.hocg.manager.support.security.body.JwtToken;
import in.hocg.mybatis.module.system.entity.Resource;
import in.hocg.mybatis.module.user.entity.Staff;
import in.hocg.scaffold.lang.exception.NotRollbackException;
import in.hocg.scaffold.support.aspect.log.ILog;
import in.hocg.scaffold.support.basis.BaseController;
import in.hocg.scaffold.support.http.Result;
import in.hocg.scaffold.support.json.annotation.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

/**
 * Created by hocgin on 2018/12/30.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Controller
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController extends BaseController {
    private final StaffService staffService;
    private final AccountService accountService;
    private final ResourceService resourceService;
    
    @ILog("获取账号信息")
    @GetMapping
    @JSON(className = Staff.class, exclude = "password")
    public ResponseEntity current(Principal principal) {
        String username = principal.getName();
        Optional<Staff> staff = staffService.findByUsername(username);
        if (staff.isPresent()) {
            return Result.success(staff.get())
                    .asResponseEntity();
        }
        return Result.error("未找到该用户")
                .asResponseEntity();
    }
    
    /**
     * POST /account/login
     * 登陆
     *
     * @param parameter
     * @return
     */
    @ILog("后台登陆")
    @PostMapping(value = "/login")
    public ResponseEntity postToken(@RequestBody Login parameter) {
        JwtToken token = accountService.login(parameter);
        return Result.success(token)
                .asResponseEntity();
    }
    
    
    /**
     * GET /account/menus
     * 获取当前用户的菜单列表
     *
     * @return
     */
    @ILog("获取菜单列表")
    @GetMapping("/menus")
    public ResponseEntity getMenu(Principal principal) throws NotRollbackException {
        String username = principal.getName();
        Resource tree = resourceService.selectMultiByUsernameAndBuildTree(username);
        return Result.success(tree.getChildren())
                .asResponseEntity();
    }
}
