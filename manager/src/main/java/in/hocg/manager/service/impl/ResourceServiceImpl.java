package in.hocg.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.manager.service.ResourceService;
import in.hocg.mybatis.basic.BaseService;
import in.hocg.mybatis.basic.condition.GetCondition;
import in.hocg.mybatis.basic.condition.PostCondition;
import in.hocg.mybatis.basic.model.NodeModel;
import in.hocg.mybatis.module.system.entity.Resource;
import in.hocg.mybatis.module.system.mapper.ResourceMapper;
import in.hocg.scaffold.lang.exception.NotRollbackException;
import in.hocg.scaffold.lang.exception.ResponseException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
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
    public boolean addChildNode(Serializable parentId,
                                Resource resource) {
        baseMapper.addChildNode(parentId, resource);
        return true;
    }
    
    @Override
    public boolean addSiblingNode(Serializable id,
                                  Resource resource) {
        baseMapper.addSiblingNode(id, resource);
        return true;
    }
    
    @Override
    public IPage<Resource> page(GetCondition condition) {
        Page<Resource> page = condition.page();
        QueryWrapper<Resource> wrapper = condition.wrapper();
        return baseMapper.selectPage(page, wrapper);
    }
    
    @Override
    public IPage<Resource> page(PostCondition condition) {
        Page<Resource> page = condition.page();
        QueryWrapper<Resource> wrapper = condition.wrapper();
        return baseMapper.selectPage(page, wrapper);
    }
}
