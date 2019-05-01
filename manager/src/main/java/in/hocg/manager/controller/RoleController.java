package in.hocg.manager.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Sets;
import in.hocg.manager.model.po.AddRole;
import in.hocg.manager.model.po.RolePageQuery;
import in.hocg.manager.model.po.UpdateRole;
import in.hocg.manager.model.vo.RoleDetailVO;
import in.hocg.manager.service.RoleService;
import in.hocg.mybatis.basic.condition.PostCondition;
import in.hocg.mybatis.module.system.entity.Role;
import in.hocg.scaffold.exception.NotRollbackException;
import in.hocg.scaffold.exception.RollbackException;
import in.hocg.scaffold.support.aspect.log.ILog;
import in.hocg.scaffold.support.basis.BaseController;
import in.hocg.scaffold.support.basis.parameter.IDs;
import in.hocg.scaffold.support.http.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by hocgin on 2018/12/16.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequestMapping("/api/v1/roles")
@AllArgsConstructor
public class RoleController extends BaseController {
    private final RoleService roleService;
    
    @ILog("分页查询角色")
    @PostMapping("/_paging")
    public ResponseEntity paging(@RequestBody PostCondition<RolePageQuery, Role> condition) {
        IPage<Role> all = roleService.paging(condition);
        return Result.success(all)
                .asResponseEntity();
    }
    
    @ILog("查询所有角色")
    @GetMapping
    public ResponseEntity selectAll() {
        Collection<Role> all = roleService.selectAll();
        return Result.success(all)
                .asResponseEntity();
    }
    
    @ILog("删除角色")
    @DeleteMapping
    public ResponseEntity delete(@Validated @RequestBody IDs parameter) throws RollbackException {
        boolean result = roleService.deletes(Sets.newHashSet(parameter.getId()));
        return Result.success(result)
                .asResponseEntity();
    }
    
    @ILog("新增角色")
    @PostMapping
    public ResponseEntity insert(@Validated @RequestBody AddRole parameter) throws NotRollbackException {
        boolean result = roleService.insert(parameter);
        return Result.success(result)
                .asResponseEntity();
    }
    
    @ILog("查询角色详情")
    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable("id") String id) {
        RoleDetailVO result = roleService.detail(id);
        return Result.success(result)
                .asResponseEntity();
    }
    
    @ILog("更新角色")
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") String id,
                                 @Validated @RequestBody UpdateRole parameter) throws NotRollbackException {
        boolean result = roleService.update(id, parameter);
        return Result.success(result)
                .asResponseEntity();
    }
    
}
