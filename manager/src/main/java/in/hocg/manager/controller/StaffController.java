package in.hocg.manager.controller;

/**
 * Created by hocgin on 2018/12/16.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.manager.model.parameter.AddStaff;
import in.hocg.manager.model.parameter.QueryStaff;
import in.hocg.manager.model.parameter.UpdateStaff;
import in.hocg.manager.service.ResourceService;
import in.hocg.manager.service.StaffService;
import in.hocg.mybatis.basic.condition.GetCondition;
import in.hocg.mybatis.basic.condition.PostCondition;
import in.hocg.mybatis.module.user.entity.Staff;
import in.hocg.scaffold.support.basis.BaseController;
import in.hocg.scaffold.support.basis.parameter.ID;
import in.hocg.scaffold.support.basis.parameter.IDs;
import in.hocg.scaffold.support.http.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/staff")
@AllArgsConstructor
public class StaffController extends BaseController {
    private final StaffService staffService;
    private final ResourceService resourceService;
    
    /**
     * GET /staff
     * 查找所有员工列表
     *
     * @param condition
     * @return
     */
    @GetMapping
    public ResponseEntity get(GetCondition condition) {
        IPage<Staff> all = staffService.page(condition);
        return Result.success(all).asResponseEntity();
    }
    
    /**
     * POST /staff/s
     * 查找所有员工列表
     *
     * @param condition
     * @return
     */
    @PostMapping("/s")
    public ResponseEntity post(@RequestBody PostCondition<QueryStaff> condition) {
        IPage<Staff> all = staffService.page(condition);
        return Result.success(all).asResponseEntity();
    }
    
    /**
     * GET /staff/{id}
     * 查找员工详情
     *
     * @param parameter
     * @return
     */
    @GetMapping("/{id:[a-zA-Z0-9]{32}}")
    public ResponseEntity detail(@PathVariable("id") ID parameter) {
        Serializable id = parameter.getId();
        Staff result = staffService.getById(id);
        return Result.success(result).asResponseEntity();
    }
    
    
    /**
     * DELETE /staff
     * 批量删除
     *
     * @param parameter
     * @return
     */
    @DeleteMapping
    public ResponseEntity delete(@Validated IDs parameter) {
        List<Serializable> ids = Arrays.asList(parameter.getId());
        boolean result = staffService.removeByIds(ids);
        return Result.result(result).asResponseEntity();
    }
    
    /**
     * PUT /staff
     * 修改
     *
     * @return
     */
    @PutMapping("/{id:[a-zA-Z0-9]{32}}")
    public ResponseEntity putStaff(@PathVariable("id") String id,
                                   @RequestBody UpdateStaff parameter) {
        Staff staff = parameter.cast(Staff.class);
        staff.setId(id);
        boolean result = staffService.updateById(staff);
        return Result.result(result).asResponseEntity();
    }
    
    
    /**
     * POST /staff
     * 新增
     *
     * @return
     */
    @PostMapping
    public ResponseEntity postStaff(@RequestBody AddStaff parameter) {
        Staff staff = parameter.cast(Staff.class);
        boolean result = staffService.save(staff);
        return Result.result(result).asResponseEntity();
    }
    
}
