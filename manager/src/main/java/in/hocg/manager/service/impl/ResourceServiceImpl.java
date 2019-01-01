package in.hocg.manager.service.impl;

import in.hocg.manager.service.ResourceService;
import in.hocg.mybatis.basic.BaseService;
import in.hocg.mybatis.basic.model.NodeModel;
import in.hocg.mybatis.module.system.entity.Resource;
import in.hocg.mybatis.module.system.mapper.ResourceMapper;
import in.hocg.scaffold.lang.exception.NotRollbackException;
import in.hocg.scaffold.lang.exception.ResponseException;
import in.hocg.util.LangKit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

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
            throw ResponseException.wrap(NotRollbackException.class, "未找到资源");
        }
        Resource root = resources.get(0);
        return NodeModel.buildTree(root, resources);
    }
    
    @Override
    public List<Resource> findAllByUsername(String username) {
        return baseMapper.findAllByUsername(username);
    }
    
    
    @Override
    public boolean deleteNode(Collection<Serializable> ids) {
        for (Serializable id : ids) {
            baseMapper.deleteNode(id);
        }
        return true;
    }
    
    @Override
    public boolean deleteNodes(Collection<Serializable> ids) {
        for (Serializable id : ids) {
            baseMapper.deleteNodes(id);
        }
        return true;
    }
    
    @Override
    @Transactional
    public boolean addChildNode(Serializable parentId,
                                Resource resource) throws NotRollbackException {
        Resource parent = baseMapper.selectById(parentId);
        if (Objects.isNull(parent)) {
            throw ResponseException.wrap(NotRollbackException.class, "请选择父节点");
        }
        resource.setId(LangKit.uuid());
        baseMapper.addChildNode(parent.getId(), resource);
        return true;
    }
    
    @Override
    public boolean addSiblingNode(Serializable id,
                                  Resource resource) {
        baseMapper.addSiblingNode(id, resource);
        return true;
    }
    
    @Override
    public Resource findAll() throws NotRollbackException {
        List<Resource> resources = baseMapper.queryAllNodeDepth();
        if (resources.isEmpty()) {
            throw ResponseException.wrap(NotRollbackException.class, "未找到资源");
        }
        Resource root = resources.get(0);
        return NodeModel.buildTree(root, resources);
    }
    
}
