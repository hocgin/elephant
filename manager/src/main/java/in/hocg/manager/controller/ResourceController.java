package in.hocg.manager.controller;

import in.hocg.manager.model.po.IResource;
import in.hocg.manager.model.po.UResource;
import in.hocg.manager.service.ResourceService;
import in.hocg.mybatis.basic.constant.DatabaseConstant;
import in.hocg.mybatis.module.system.entity.Resource;
import in.hocg.scaffold.lang.exception.NotRollbackException;
import in.hocg.scaffold.lang.exception.RollbackException;
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
    
    /**
     * GET /resource
     * 查找所有菜单列表
     *
     * @return
     */
    @GetMapping
    public ResponseEntity selectAll() throws NotRollbackException {
        Collection<Resource> all = resourceService.selectAll();
        return Result.success(all)
                .asResponseEntity();
    }
    
    /**
     * GET /resource/:id
     * 查询详细信息
     *
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity selectOne(@PathVariable("id") String id) {
        Resource detail = resourceService.getById(id);
        return Result.success(detail)
                .asResponseEntity();
    }
    
    /**
     * POST /resource
     * 新增
     * > 1: 添加兄弟节点
     * > 0 or 其他: 添加子节点
     *
     * @return
     */
    @PostMapping
    public ResponseEntity insert(@RequestBody IResource body,
                                 @RequestParam(value = "mode", required = false, defaultValue = "0") int mode) throws NotRollbackException {
        String refNode = body.getRefNode();
        
        // 添加兄弟节点,关联节点不能为根节点
        if (mode == 1 && refNode.contains(DatabaseConstant.DEFAULT_ROOT_NODE_UUID)) {
            return Result.error("仅能有一个根节点")
                    .asResponseEntity();
        }
        boolean result = resourceService.insertOneNode(body, mode, refNode);
        return Result.result(result)
                .asResponseEntity();
    }
    
    
    /**
     * DELETE /resource
     * 批量删除
     * > 1: 删除指定节点, 并移动其子节点到该节点所在层级
     * > 0 or 其他: 删除选中节点及其子节点
     *
     * @param parameter
     * @return
     */
    @DeleteMapping
    public ResponseEntity delete(@Validated IDs parameter,
                                 @RequestParam(value = "mode", required = false, defaultValue = "0") int mode) throws NotRollbackException {
        List<Serializable> ids = Arrays.asList(parameter.getId());
        if (ids.contains(DatabaseConstant.DEFAULT_ROOT_NODE_UUID)) {
            return Result.error("根节点不能被删除")
                    .asResponseEntity();
        }
        boolean result = resourceService.deleteMultiNode(mode, ids);
        return Result.success(result)
                .asResponseEntity();
    }
    
    /**
     * PUT /resource
     * 更新资源
     *
     * @param id        唯一
     * @param parameter 参数
     * @return
     * @throws RollbackException
     * @throws NotRollbackException
     */
    @PutMapping("/{id}")
    public ResponseEntity updateOne(@PathVariable("id") String id,
                                    @RequestBody UResource parameter) throws RollbackException, NotRollbackException {
        if (id.contains(DatabaseConstant.DEFAULT_ROOT_NODE_UUID)) {
            return Result.error("根节点不能被修改")
                    .asResponseEntity();
        }
        boolean result = resourceService.updateOneById(id, parameter);
        return Result.success(result)
                .asResponseEntity();
    }
    
}
