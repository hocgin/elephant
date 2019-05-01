package in.hocg.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import in.hocg.manager.model.po.ResourceInsert;
import in.hocg.manager.model.po.ResourceUpdate;
import in.hocg.mybatis.module.system.entity.Resource;
import in.hocg.scaffold.exception.NotRollbackException;
import in.hocg.scaffold.exception.RollbackException;
import in.hocg.scaffold.support.basis.parameter.IDs;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

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
     * 查找到达该节点的路径
     *
     * @param id
     * @return
     */
    Collection<Resource> selectMultiTreePathByLeafId(Serializable id);
    
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
    boolean deleteMultiNode(@NonNull Collection<String> ids);
    
    /**
     * 删除指定节点及其子节点
     *
     * @param ids
     * @return
     */
    boolean deleteMultiNodes(@NonNull Collection<String> ids);
    
    /**
     * 按指定模式添加一个节点
     *
     * @param body    节点
     * @param mode    模式[子节点, 兄弟节点]
     * @return
     * @throws NotRollbackException
     */
    @Transactional(rollbackFor = Exception.class, noRollbackFor = NotRollbackException.class)
    boolean insert(ResourceInsert body,
                   int mode) throws NotRollbackException;
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
     *
     * @return
     */
    Resource selectAllAndBuildTree() throws NotRollbackException;
    
    Collection<Resource> selectAll() throws NotRollbackException;
    
    /**
     * 更新
     *
     * @param id
     * @param parameter
     * @return
     */
    boolean update(String id, ResourceUpdate parameter) throws NotRollbackException, RollbackException;
    
    
    /**
     * 根据模式删除节点
     *
     * @param mode 模式
     *             1. 删除指定节点, 并移动其子节点到该节点所在层级
     *             0. 删除指定节点及其子节点
     * @param parameter  节点 ID
     * @return
     * @throws NotRollbackException
     */
    boolean delete(int mode, IDs parameter) throws NotRollbackException;
    
    /**
     * 查看详情
     * @param id
     * @return
     */
    Resource detail(String id);
    
}
