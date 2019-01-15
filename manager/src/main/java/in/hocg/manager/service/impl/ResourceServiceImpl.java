package in.hocg.manager.service.impl;

import in.hocg.manager.model.parameter.UResource;
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
    public Resource selectMultiByUsernameAndBuildTree(String username) throws NotRollbackException {
        List<Resource> resources = baseMapper.selectMultiByUsername(username);
        if (resources.isEmpty()) {
            throw ResponseException.wrap(NotRollbackException.class, "未找到资源");
        }
        Resource root = resources.get(0);
        return NodeModel.buildTree(root, resources);
    }
    
    @Override
    public List<Resource> selectMultiByUsername(String username) {
        return baseMapper.selectMultiByUsername(username);
    }
    
    
    @Override
    public boolean deleteMultiNode(Collection<Serializable> ids) {
        for (Serializable id : ids) {
            baseMapper.deleteNode(id);
        }
        return true;
    }
    
    @Override
    public boolean deleteMultiNodes(Collection<Serializable> ids) {
        for (Serializable id : ids) {
            baseMapper.deleteNodes(id);
        }
        return true;
    }
    
    @Override
    @Transactional
    public boolean insertOneChildNode(Serializable parentId,
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
    public boolean insertOneSiblingNode(Serializable id,
                                        Resource resource) throws NotRollbackException {
        Resource sibling = baseMapper.selectById(id);
        if (Objects.isNull(sibling)) {
            throw ResponseException.wrap(NotRollbackException.class, "请选择兄弟节点");
        }
        resource.setId(LangKit.uuid());
        baseMapper.addSiblingNode(id, resource);
        return true;
    }
    
    @Override
    public Resource selectAllAndBuildTree() throws NotRollbackException {
        List<Resource> resources = baseMapper.queryAllNodeDepth();
        if (resources.isEmpty()) {
            throw ResponseException.wrap(NotRollbackException.class, "未找到资源");
        }
        Resource root = resources.get(0);
        return NodeModel.buildTree(root, resources);
    }
    
    @Override
    public boolean updateOneById(String id, UResource parameter) throws NotRollbackException {
        Resource resource = baseMapper.selectById(id);
        if (resource == null) {
            throw ResponseException.wrap(NotRollbackException.class, "未找到资源");
        }
        parameter.copyNotNullTo(resource);
        return baseMapper.updateById(resource) > 0;
    }
    
}
