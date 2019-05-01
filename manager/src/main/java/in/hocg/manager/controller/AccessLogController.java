package in.hocg.manager.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.manager.model.po.AccessLogBody;
import in.hocg.manager.model.po.AccessLogInsert;
import in.hocg.manager.model.po.AccessLogUpdate;
import in.hocg.manager.model.vo.AccessLogDetailVO;
import in.hocg.manager.service.AccessLogService;
import in.hocg.mybatis.basic.condition.PostCondition;
import in.hocg.mybatis.module.basic.entity.AccessLog;
import in.hocg.scaffold.exception.NotRollbackException;
import in.hocg.scaffold.support.aspect.log.ILog;
import in.hocg.scaffold.support.basis.BaseController;
import in.hocg.scaffold.support.basis.parameter.IDs;
import in.hocg.scaffold.support.http.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hocgin on 2019/2/27.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Controller
@RequestMapping("/api/v1/access-log")
@AllArgsConstructor
public class AccessLogController extends BaseController {
    private final AccessLogService accessLogService;
    
    /**
     * POST /access-log/_paging
     * 分页查询
     *
     * @param condition
     * @return
     */
//    @ILog("查询日志")
    @PostMapping("/_paging")
    public ResponseEntity paging(@RequestBody PostCondition<AccessLogBody, AccessLog> condition) {
        IPage<AccessLog> all = accessLogService.paging(condition);
        return Result.success(all).asResponseEntity();
    }
    
    /**
     * GET /access-log/{id}
     * 查找详情
     *
     * @param id
     * @return
     */
    @ILog("查询日志详情")
    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable("id") String id) {
        AccessLogDetailVO result = accessLogService.detail(id);
        return Result.success(result).asResponseEntity();
    }
    
    
    /**
     * DELETE /access-log
     * 批量删除
     *
     * @param parameter
     * @return
     */
    @ILog("删除日志")
    @DeleteMapping
    public ResponseEntity delete(@Validated @RequestBody IDs parameter) {
        boolean result = accessLogService.delete(parameter);
        return Result.result(result).asResponseEntity();
    }
    
    /**
     * PUT /access-log
     * 修改
     *
     * @return
     */
    @ILog("修改日志")
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") String id,
                                 @RequestBody AccessLogUpdate parameter) throws NotRollbackException {
        boolean result = accessLogService.update(id, parameter);
        return Result.result(result).asResponseEntity();
    }
    
    
    /**
     * POST /access-log
     * 新增
     *
     * @return
     */
    @ILog("新增日志")
    @PostMapping
    public ResponseEntity insert(@RequestBody AccessLogInsert parameter) throws NotRollbackException {
        boolean result = accessLogService.insert(parameter);
        return Result.result(result).asResponseEntity();
    }
    
    
}
