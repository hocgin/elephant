package in.hocg.manager.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.manager.model.parameter.AddOrganization;
import in.hocg.manager.model.parameter.QueryOrganization;
import in.hocg.manager.model.parameter.UpdateOrganization;
import in.hocg.manager.service.OrganizationService;
import in.hocg.mybatis.basic.condition.GetCondition;
import in.hocg.mybatis.basic.condition.PostCondition;
import in.hocg.mybatis.module.basic.entity.Organization;
import in.hocg.scaffold.support.basis.BaseController;
import in.hocg.scaffold.support.basis.parameter.ID;
import in.hocg.scaffold.support.basis.parameter.IDs;
import in.hocg.scaffold.support.http.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hocgin on 2018/12/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Controller
@AllArgsConstructor
@RequestMapping("/organization")
public class OrganizationController extends BaseController {
    private final OrganizationService organizationService;
    
    /**
     * GET /organization
     * 查找所有列表
     *
     * @param condition
     * @return
     */
    @GetMapping
    public ResponseEntity get(GetCondition condition) {
        IPage<Organization> all = organizationService.page(condition);
        return Result.success(all).asResponseEntity();
    }
    
    /**
     * POST /organization/s
     * 查找所有列表
     *
     * @param condition
     * @return
     */
    @PostMapping("/s")
    public ResponseEntity post(@RequestBody PostCondition<QueryOrganization> condition) {
        IPage<Organization> all = organizationService.page(condition);
        return Result.success(all).asResponseEntity();
    }
    
    /**
     * GET /organization/{id}
     * 查找详情
     *
     * @param parameter
     * @return
     */
    @GetMapping("/{id:[a-zA-Z0-9_]+}")
    public ResponseEntity detail(@PathVariable("id") ID parameter) {
        Serializable id = parameter.getId();
        Organization result = organizationService.getById(id);
        return Result.success(result).asResponseEntity();
    }
    
    
    /**
     * DELETE /organization
     * 批量删除
     * > 1: 删除指定节点, 并移动其子节点到该节点所在层级
     * > 其他: 删除选中节点及其子节点
     *
     * @param parameter
     * @return
     */
    @DeleteMapping
    public ResponseEntity delete(@Validated IDs parameter,
                                 @RequestParam(value = "mode", required = false) int mode) {
        List<Serializable> ids = Arrays.asList(parameter.getId());
        if (mode == 1) {
            organizationService.deleteNode(ids);
        } else {
            organizationService.deleteNodes(ids);
        }
        return Result.success().asResponseEntity();
    }
    
    /**
     * PUT /organization
     * 修改
     *
     * @return
     */
    @PutMapping("/{id:[a-zA-Z0-9_]+}")
    public ResponseEntity putOrganization(@PathVariable("id") String id,
                                          @RequestBody UpdateOrganization parameter) {
        Organization entity = parameter.copyTo(Organization.class);
        entity.setId(id);
        boolean result = organizationService.updateById(entity);
        return Result.result(result).asResponseEntity();
    }
    
    
    /**
     * POST /organization/{id}
     * 新增
     * > 1: 增加子节点
     * > 其他: 增加兄弟节点
     *
     * @return
     */
    @PostMapping("/{id}")
    public ResponseEntity postOrganization(@RequestBody AddOrganization parameter,
                                    @PathVariable(value = "id") Serializable id,
                                    @RequestParam(value = "mode", required = false) int mode) {
        Organization entity = parameter.copyTo(Organization.class);
        if (mode == 1) {
            organizationService.addChildNode(id, entity);
        } else {
            organizationService.addSiblingNode(id, entity);
        }
        return Result.success().asResponseEntity();
    }
    
}
