package in.hocg.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import in.hocg.mybatis.module.system.entity.Resource;
import in.hocg.scaffold.lang.exception.NotRollbackException;
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
    Resource findResourceTreeByUsername(String username) throws NotRollbackException;
    
    /**
     * 查找用户具备的资源
     *
     * @param username
     * @return
     */
    List<Resource> findAllByUsername(String username);
    
    
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
     * @param resource
     * @return
     */
    boolean addChildNode(@NonNull Serializable parentId, @NonNull Resource resource) throws NotRollbackException;
    
    /**
     * 在同一级指定节点之后, 追加一个子节点
     *
     * @param id
     * @param resource
     * @return
     */
    boolean addSiblingNode(@NonNull Serializable id, @NonNull Resource resource);

    
    /**
     * 查找完整的树
     * @return
     */
    Resource findAll() throws NotRollbackException;
}
