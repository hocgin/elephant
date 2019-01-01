package in.hocg.manager.controller;

import in.hocg.manager.model.parameter.AddResource;
import in.hocg.manager.service.ResourceService;
import in.hocg.mybatis.module.system.entity.Resource;
import in.hocg.scaffold.lang.exception.NotRollbackException;
import in.hocg.scaffold.support.basis.BaseController;
import in.hocg.scaffold.support.http.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hocgin on 2018/12/16.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequestMapping("/resource")
@AllArgsConstructor
public class ResourceController extends BaseController {
    private final ResourceService resourceService;
    
    /**
     * GET /resource
     * 查找所有员工列表
     *
     * @return
     */
    @GetMapping
    public ResponseEntity get() throws NotRollbackException {
        Resource all = resourceService.findAll();
        return Result.success(all).asResponseEntity();
    }
    
    /**
     * POST /resource
     * 新增
     *
     * @return
     */
    @PostMapping
    public ResponseEntity post(@RequestBody AddResource body) throws NotRollbackException {
        Resource entity = body.cast(Resource.class);
        boolean result = resourceService.addChildNode(body.getParent(), entity);
        return Result.result(result).asResponseEntity();
    }
    
}
