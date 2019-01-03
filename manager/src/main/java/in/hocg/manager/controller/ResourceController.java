package in.hocg.manager.controller;

import in.hocg.manager.model.parameter.AddResource;
import in.hocg.manager.service.ResourceService;
import in.hocg.mybatis.module.system.entity.Resource;
import in.hocg.scaffold.lang.exception.NotRollbackException;
import in.hocg.scaffold.support.basis.BaseController;
import in.hocg.scaffold.support.basis.parameter.IDs;
import in.hocg.scaffold.support.http.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

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
    
    
    /**
     * DELETE /resource
     * 批量删除
     * > 1: 删除指定节点, 并移动其子节点到该节点所在层级
     * > 其他: 删除选中节点及其子节点
     *
     * @param parameter
     * @return
     */
    @DeleteMapping
    public ResponseEntity deletes(@Validated IDs parameter,
                                  @RequestParam(value = "mode", required = false, defaultValue = "0") int mode) {
        List<Serializable> ids = Arrays.asList(parameter.getId());
        if (ids.contains("root")) {
            return Result.error("根节点不能被删除").asResponseEntity();
        }
        if (mode == 1) {
            resourceService.deleteNode(ids);
        } else {
            resourceService.deleteNodes(ids);
        }
        return Result.success().asResponseEntity();
    }
    
    
}
