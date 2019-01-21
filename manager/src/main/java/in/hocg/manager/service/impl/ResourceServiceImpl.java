package in.hocg.manager.service.impl;

import com.alibaba.druid.util.StringUtils;
import in.hocg.manager.model.parameter.UResource;
import in.hocg.manager.service.ResourceService;
import in.hocg.mybatis.basic.BaseService;
import in.hocg.mybatis.basic.model.NodeModel;
import in.hocg.mybatis.basic.model.SuperModel;
import in.hocg.mybatis.basic.model.TreeUtils;
import in.hocg.mybatis.module.system.entity.Resource;
import in.hocg.mybatis.module.system.mapper.ResourceMapper;
import in.hocg.scaffold.lang.exception.NotRollbackException;
import in.hocg.scaffold.lang.exception.ResponseException;
import in.hocg.scaffold.lang.exception.RollbackException;
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
        return TreeUtils.buildTree(resources);
    }
    
    @Override
    public List<Resource> selectMultiByUsername(String username) {
        return baseMapper.selectMultiByUsername(username);
    }
    
    
    @Override
    public boolean deleteMultiNode(Collection<Serializable> ids) {
        for (Serializable id : ids) {
            baseMapper.deleteOneNode(id);
        }
        return true;
    }
    
    @Override
    public boolean deleteMultiNodes(Collection<Serializable> ids) {
        for (Serializable id : ids) {
            baseMapper.deleteMultiNode(id);
        }
        return true;
    }
    
    @Override
    public boolean insertOneChildNode(Serializable parentId,
                                      Resource resource) throws NotRollbackException {
        Resource parent = baseMapper.selectById(parentId);
        if (Objects.isNull(parent)) {
            throw ResponseException.wrap(NotRollbackException.class, "请选择父节点");
        }
        
        // 父节点为禁用,子节点也会设置为禁用
        if (!parent.getEnabled()) {
            resource.setEnabled(false);
        }
        
        resource.setId(LangKit.uuid());
        baseMapper.insertOneChildNode(parent.getId(), resource);
        return true;
    }
    
    @Override
    public boolean insertOneSiblingNode(Serializable id,
                                        Resource resource) throws NotRollbackException {
        Resource sibling = baseMapper.selectById(id);
        if (Objects.isNull(sibling)) {
            throw ResponseException.wrap(NotRollbackException.class, "请选择兄弟节点");
        }
        
        // 兄弟节点的父节点, 即该节点的父节点
        Resource parent = baseMapper.selectOneParentNodeById(sibling.getId());
        
        // 父节点为禁用,子节点也会设置为禁用
        if (!parent.getEnabled()) {
            resource.setEnabled(false);
        }
        
        resource.setId(LangKit.uuid());
        baseMapper.insertOneSiblingNode(id, resource);
        return true;
    }
    
    @Override
    public Resource selectAllAndBuildTree() throws NotRollbackException {
        List<Resource> resources = baseMapper.selectAllNodeHasDepth();
        if (resources.isEmpty()) {
            throw ResponseException.wrap(NotRollbackException.class, "未找到资源");
        }
        return TreeUtils.buildTree(resources);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateOneById(String id, UResource parameter) throws RollbackException {
        Resource resource = baseMapper.selectById(id);
        if (resource == null) {
            throw ResponseException.wrap(RollbackException.class, "未找到资源");
        }
        Resource parent = baseMapper.selectOneParentNodeById(id);
        
        // 父节点发生变更, 父节点变更子树需跟随变更
        // - 目前方案: 删除该子树, 并按序重新进行插入
        if (parameter.getParent() != null
                && !StringUtils.equals(parent.getId(), parameter.getParent())) {
            if (baseMapper.selectById(parameter.getParent()) == null) {
                throw ResponseException.wrap(RollbackException.class, "未找到目标父节点");
            }
            List<Resource> nodes = baseMapper.selectMultiTreeNodeHasDepth(id);
            
            // 删除子树
            baseMapper.deleteMultiNode(id);
            
            // 插入子树
            insertChildTree(parameter.getParent(), TreeUtils.buildTree(nodes));
        }
        
        // 开关状态发生变更
        if (!Objects.equals(resource.getEnabled(), parameter.getEnabled())) {
            
            // 如果父节点为关闭状态, 且子节点欲切换为开启状态(拒绝)
            if (!parent.getEnabled() && parameter.getEnabled()) {
                throw ResponseException.wrap(RollbackException.class, "请先启用父节点状态");
            }
            
            // 如果该节点是变更为关闭, 则会关闭其对应的子节点
            if (!parameter.getEnabled()) {
                String[] IDs = baseMapper.selectMultiTreeNodeHasDepth(id).stream()
                        .map(SuperModel::getId)
                        .toArray(String[]::new);
                baseMapper.updateMultiEnableById(false, IDs);
            }
            
        }
        
        // 常规更新
        parameter.copyNotNullTo(resource);
        return baseMapper.updateById(resource) > 0;
    }
    
    
    /**
     * 添加一颗子树
     *
     * @param parent
     * @param resource
     */
    public void insertChildTree(String parent, Resource resource) {
        List<NodeModel> children = resource.getChildren();
        // 抹除节点位置信息
        TreeUtils.erase(resource);
        baseMapper.insertOneChildNode(parent, resource);
        TreeUtils.traversing(resource, children, (p, node) -> {
            TreeUtils.erase(node);
            baseMapper.insertOneChildNode(p.getId(), (Resource) node);
        });
    }
}
