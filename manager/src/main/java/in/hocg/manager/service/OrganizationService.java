package in.hocg.manager.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import in.hocg.manager.controller.parameter.QueryOrganization;
import in.hocg.mybatis.basic.condition.GetCondition;
import in.hocg.mybatis.basic.condition.PostCondition;
import in.hocg.mybatis.module.basic.entity.Organization;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by hocgin on 2018/12/22.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface OrganizationService extends IService<Organization> {
    
    /**
     * 删除指定节点, 并移动其子节点到该节点所在层级
     *
     * @param ids
     * @return
     */
    boolean deleteNode(@NonNull Collection<Serializable> ids);
    
    /**
     * 删除指定节点及其子节点
     *
     * @param ids
     * @return
     */
    boolean deleteNodes(@NonNull Collection<Serializable> ids);
    
    /**
     * 在指定节点下, 追加一个子节点
     *
     * @param parentId
     * @param organization
     * @return
     */
    boolean addChildNode(@NonNull Serializable parentId, @NonNull Organization organization);
    
    /**
     * 在同一级指定节点之后, 追加一个子节点
     *
     * @param id
     * @param organization
     * @return
     */
    boolean addSiblingNode(@NonNull Serializable id, @NonNull Organization organization);
    
    /**
     * 分页查找
     *
     * @param condition
     * @return
     */
    IPage<Organization> page(GetCondition condition);
    
    /**
     * 分页查找
     *
     * @param condition
     * @return
     */
    IPage<Organization> page(PostCondition<QueryOrganization> condition);
}
