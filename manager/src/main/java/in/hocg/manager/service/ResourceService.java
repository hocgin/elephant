package in.hocg.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import in.hocg.mybatis.module.system.entity.Resource;
import in.hocg.scaffold.lang.exception.NotRollbackException;

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
     * @param username
     * @return
     * @throws NotRollbackException
     */
    Resource findResourceTreeByUsername(String username) throws NotRollbackException;
    
    /**
     * 查找用户具备的资源
     * @param username
     * @return
     */
    List<Resource> findAllByUsername(String username);
    
    
}
