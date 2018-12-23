package in.hocg.manager.service.impl;

import in.hocg.manager.service.ResourceService;
import in.hocg.mybatis.basic.BaseService;
import in.hocg.mybatis.basic.model.NodeModel;
import in.hocg.mybatis.module.system.entity.Resource;
import in.hocg.mybatis.module.system.mapper.ResourceMapper;
import in.hocg.scaffold.lang.exception.NotRollbackException;
import in.hocg.scaffold.lang.exception.ResponseException;
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
    public Resource findResourceTreeByUsername(String username) throws NotRollbackException {
        List<Resource> resources = baseMapper.findAllByUsername(username);
        if (resources.isEmpty()) {
            throw ResponseException.wrap(NotRollbackException.class,"未找到资源");
        }
        Resource root = resources.get(0);
        return NodeModel.buildTree(root, resources);
    }
    
    @Override
    public List<Resource> findAllByUsername(String username) {
        return baseMapper.findAllByUsername(username);
    }
}
