package in.hocg.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import in.hocg.mybatis.module.system.entity.Resource;

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
     * 查找对应用户的资源
     * @param username
     * @return
     */
    Resource findAllByUsername(String username);
}
