package in.hocg.manager.controller;

import in.hocg.manager.model.po.ResourceInsert;
import in.hocg.manager.model.po.ResourceUpdate;
import in.hocg.manager.service.ResourceService;
import in.hocg.mybatis.basic.constant.DatabaseConstant;
import in.hocg.mybatis.module.system.entity.Resource;
import in.hocg.scaffold.lang.exception.NotRollbackException;
import in.hocg.scaffold.lang.exception.RollbackException;
import in.hocg.scaffold.support.aspect.log.ILog;
import in.hocg.scaffold.support.basis.BaseController;
import in.hocg.scaffold.support.basis.parameter.IDs;
import in.hocg.scaffold.support.http.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by hocgin on 2018/12/16.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequestMapping("/api/v1/resources")
@AllArgsConstructor
public class ResourceController extends BaseController {
    private final ResourceService resourceService;
    
    @ILog("查询所有资源")
    @GetMapping
    public ResponseEntity selectAll() throws NotRollbackException {
        Collection<Resource> all = resourceService.selectAll();
        return Result.success(all)
                .asResponseEntity();
    }
    
    @ILog("查询资源详情")
    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable("id") String id) {
        Resource detail = resourceService.detail(id);
        return Result.success(detail)
                .asResponseEntity();
    }
    
    /**
     * POST /resources
     * 新增
     * > 1: 添加兄弟节点
     * > 0 or 其他: 添加子节点
     *
     * @return
     */
    @ILog("新增资源")
    @PostMapping
    public ResponseEntity insert(@RequestBody ResourceInsert body,
                                 @RequestParam(value = "mode", required = false, defaultValue = "0") int mode) throws NotRollbackException {
        String refNode = body.getRefNode();
        // 添加兄弟节点,关联节点不能为根节点
        if (mode == 1 && refNode.contains(DatabaseConstant.DEFAULT_ROOT_NODE_UUID)) {
            return Result.error("仅能有一个根节点")
                    .asResponseEntity();
        }
        boolean result = resourceService.insert(body, mode);
        return Result.result(result)
                .asResponseEntity();
    }
    
    
    /**
     * DELETE /resources
     * 批量删除
     * > 1: 删除指定节点, 并移动其子节点到该节点所在层级
     * > 0 or 其他: 删除选中节点及其子节点
     *
     * @param parameter
     * @return
     */
    @ILog("删除资源")
    @DeleteMapping
    public ResponseEntity delete(@Validated IDs parameter,
                                 @RequestParam(value = "mode", required = false, defaultValue = "0") int mode) throws NotRollbackException {
        List<Serializable> ids = Arrays.asList(parameter.getId());
        if (ids.contains(DatabaseConstant.DEFAULT_ROOT_NODE_UUID)) {
            return Result.error("根节点不能被删除")
                    .asResponseEntity();
        }
        boolean result = resourceService.delete(mode, parameter);
        return Result.success(result)
                .asResponseEntity();
    }
    
    /**
     * PUT /resources
     * 更新资源
     *
     * @param id        唯一
     * @param parameter 参数
     * @return
     * @throws RollbackException
     * @throws NotRollbackException
     */
    @ILog("更新资源")
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") String id,
                                 @RequestBody ResourceUpdate parameter) throws RollbackException, NotRollbackException {
        if (id.contains(DatabaseConstant.DEFAULT_ROOT_NODE_UUID)) {
            return Result.error("根节点不能被修改")
                    .asResponseEntity();
        }
        boolean result = resourceService.update(id, parameter);
        return Result.success(result)
                .asResponseEntity();
    }
    
}
