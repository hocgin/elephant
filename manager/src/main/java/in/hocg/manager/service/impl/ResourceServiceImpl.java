package in.hocg.manager.service.impl;

import in.hocg.manager.service.ResourceService;
import in.hocg.mybatis.basic.BaseService;
import in.hocg.mybatis.module.system.entity.RbacResource;
import in.hocg.mybatis.module.system.mapper.ResourceMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * [权限模块] 资源表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2018-10-21
 */
@Service
public class ResourceServiceImpl extends BaseService<ResourceMapper, RbacResource>
        implements ResourceService {

}
