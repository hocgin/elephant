package in.hocg.manager.controller;

/**
 * Created by hocgin on 2018/12/16.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.manager.model.po.StaffBody;
import in.hocg.manager.model.po.StaffInsert;
import in.hocg.manager.model.po.StaffUpdate;
import in.hocg.manager.model.vo.StaffDetailVO;
import in.hocg.manager.service.StaffService;
import in.hocg.mybatis.basic.condition.PostCondition;
import in.hocg.mybatis.module.user.entity.Staff;
import in.hocg.scaffold.exception.NotRollbackException;
import in.hocg.scaffold.support.aspect.log.ILog;
import in.hocg.scaffold.support.basis.BaseController;
import in.hocg.scaffold.support.basis.parameter.IDs;
import in.hocg.scaffold.support.http.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/staff")
@AllArgsConstructor
public class StaffController extends BaseController {
    private final StaffService staffService;
    
    @ILog("分页查询员工")
    @PostMapping("/_paging")
    public ResponseEntity paging(@RequestBody PostCondition<StaffBody, Staff> condition) {
        IPage<Staff> all = staffService.paging(condition);
        return Result.success(all).asResponseEntity();
    }

    @ILog("查询员工详情")
    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable("id") String id) {
        StaffDetailVO result = staffService.detail(id);
        return Result.success(result).asResponseEntity();
    }

    @ILog("删除员工")
    @DeleteMapping
    public ResponseEntity delete(@Validated @RequestBody IDs parameter) {
        boolean result = staffService.delete(parameter);
        return Result.result(result).asResponseEntity();
    }
    
    @ILog("更新员工")
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") String id,
                                 @RequestBody StaffUpdate parameter) throws NotRollbackException {
        boolean result = staffService.update(id, parameter);
        return Result.result(result).asResponseEntity();
    }
    
    @ILog("新增员工")
    @PostMapping
    public ResponseEntity insert(@RequestBody StaffInsert parameter) throws NotRollbackException {
        boolean result = staffService.insert(parameter);
        return Result.result(result).asResponseEntity();
    }
    
}
