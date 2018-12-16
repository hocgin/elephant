package in.hocg.manager.service.impl;

import in.hocg.manager.service.ResourceService;
import in.hocg.mybatis.basic.BaseService;
import in.hocg.mybatis.basic.model.NodeModel;
import in.hocg.mybatis.module.system.entity.Resource;
import in.hocg.mybatis.module.system.mapper.ResourceMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * [权限模块] 资源表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2018-10-21
 */
@Service
public class ResourceServiceImpl extends BaseService<ResourceMapper, Resource>
        implements ResourceService {
    
    @Override
    public Resource findAllByUsername(String username) {
        List<Resource> resources = baseMapper.findAllByUsername(username);
        Resource root = resources.get(0);
        return NodeModel.buildTree(root, resources);
    }
}
