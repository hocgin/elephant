package in.hocg.manager.controller;

import in.hocg.manager.model.po.Login;
import in.hocg.manager.model.po.CurrentAccountUpdate;
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
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by hocgin on 2018/12/30.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Controller
@RequestMapping("/api/v1/account")
@AllArgsConstructor
public class AccountController extends BaseController {
    private final StaffService staffService;
    private final AccountService accountService;
    private final ResourceService resourceService;
    
    @ILog("获取当前账号信息")
    @GetMapping
    public ResponseEntity getCurrentAccount(Principal principal) {
        String username = principal.getName();
        Optional<Staff> staff = staffService.findByUsername(username);
        if (staff.isPresent()) {
            return Result.success(staff.get().setPassword(null))
                    .asResponseEntity();
        }
        return Result.error("未找到该用户")
                .asResponseEntity();
    }
    
    @ILog("更新个人信息")
    @PutMapping
    public ResponseEntity updateCurrentAccount(@RequestBody CurrentAccountUpdate body,
                                               Principal principal) throws NotRollbackException {
        String name = principal.getName();
        staffService.updateCurrentAccount(name, body);
        return Result.success()
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
    public ResponseEntity getMenus(Principal principal) {
        String username = principal.getName();
        Collection<Resource> tree = resourceService.selectMultiByUsername(username).stream()
                .filter((r)->r.getType() == 0)
                .filter(Resource::getEnabled)
                .collect(Collectors.toList());
        return Result.success(tree)
                .asResponseEntity();
    }
}
