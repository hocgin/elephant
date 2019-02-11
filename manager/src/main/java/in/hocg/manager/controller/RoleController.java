package in.hocg.manager.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.manager.model.po.AddRole;
import in.hocg.manager.model.po.RolePageQuery;
import in.hocg.manager.model.po.UpdateRole;
import in.hocg.manager.model.vo.RoleDetailVO;
import in.hocg.manager.service.RoleService;
import in.hocg.mybatis.basic.condition.PostCondition;
import in.hocg.mybatis.module.system.entity.Role;
import in.hocg.scaffold.lang.exception.NotRollbackException;
import in.hocg.scaffold.lang.exception.RollbackException;
import in.hocg.scaffold.support.basis.BaseController;
import in.hocg.scaffold.support.basis.parameter.IDs;
import in.hocg.scaffold.support.http.Result;
import lombok.AllArgsConstructor;
import org.mapstruct.ap.internal.util.Collections;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hocgin on 2018/12/16.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController extends BaseController {
    private final RoleService roleService;
    
    /**
     * POST /roles/_paging
     * 查找所有角色列表
     *
     * @param condition
     * @return
     */
    @PostMapping("/_paging")
    public ResponseEntity paging(@RequestBody PostCondition<RolePageQuery> condition) {
        IPage<Role> all = roleService.page(condition);
        return Result.success(all)
                .asResponseEntity();
    }
    
    
    /**
     * DELETE /roles
     * 删除角色
     *
     * @param parameter
     * @return
     */
    @DeleteMapping
    public ResponseEntity delete(@Validated @RequestBody IDs parameter) throws RollbackException {
        boolean result = roleService.removeMultiByIds(Collections.asSet(parameter.getId()));
        return Result.success(result)
                .asResponseEntity();
    }
    
    /**
     * POST /roles
     * 新增角色
     *
     * @param parameter
     * @return
     */
    @PostMapping
    public ResponseEntity insert(@Validated @RequestBody AddRole parameter) throws NotRollbackException {
        boolean result = roleService.insertOneRole(parameter);
        return Result.success(result)
                .asResponseEntity();
    }
    
    /**
     * GET /roles/:id
     * 查找角色
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable("id") String id) {
        RoleDetailVO result = roleService.selectOneById(id);
        return Result.success(result)
                .asResponseEntity();
    }
    
    /**
     * PUT /roles/:id
     * 更新角色
     *
     * @param parameter
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") String id,
                                 @Validated @RequestBody UpdateRole parameter) throws NotRollbackException {
        boolean result = roleService.updateOneById(id, parameter);
        return Result.success(result)
                .asResponseEntity();
    }
    
}
