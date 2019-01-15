package in.hocg.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import in.hocg.manager.model.parameter.UResource;
import in.hocg.mybatis.module.system.entity.Resource;
import in.hocg.scaffold.lang.exception.NotRollbackException;
import in.hocg.scaffold.lang.exception.RollbackException;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * [权限模块] 资源表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2018-10-21
 */
public interface ResourceService extends IService<Resource> {
    
    /**
     * 查找对应用户的资源树
     *
     * @param username
     * @return
     * @throws NotRollbackException
     */
    Resource selectMultiByUsernameAndBuildTree(String username) throws NotRollbackException;
    
    /**
     * 查找用户具备的资源
     *
     * @param username
     * @return
     */
    List<Resource> selectMultiByUsername(String username);
    
    
    /**
     * 删除指定节点, 并移动其子节点到该节点所在层级
     *
     * @param ids
     * @return
     */
    boolean deleteMultiNode(@NonNull Collection<Serializable> ids);
    
    /**
     * 删除指定节点及其子节点
     *
     * @param ids
     * @return
     */
    boolean deleteMultiNodes(@NonNull Collection<Serializable> ids);
    
    /**
     * 在指定节点下, 追加一个子节点
     *
     * @param parentId
     * @param resource
     * @return
     */
    boolean insertOneChildNode(@NonNull Serializable parentId, @NonNull Resource resource) throws NotRollbackException;
    
    /**
     * 在同一级指定节点之后, 追加一个子节点
     *
     * @param id
     * @param resource
     * @return
     */
    boolean insertOneSiblingNode(@NonNull Serializable id, @NonNull Resource resource) throws NotRollbackException;

    
    /**
     * 查找完整的树
     * @return
     */
    Resource selectAllAndBuildTree() throws NotRollbackException;
    
    /**
     * 更新
     *
     * @param id
     * @param parameter
     * @return
     */
    boolean updateOneById(String id, UResource parameter) throws NotRollbackException, RollbackException;
}
