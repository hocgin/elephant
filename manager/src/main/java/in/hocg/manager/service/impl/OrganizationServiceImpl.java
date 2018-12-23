package in.hocg.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.manager.service.OrganizationService;
import in.hocg.mybatis.basic.BaseService;
import in.hocg.mybatis.basic.condition.GetCondition;
import in.hocg.mybatis.basic.condition.PostCondition;
import in.hocg.mybatis.module.basic.entity.Organization;
import in.hocg.mybatis.module.basic.mapper.OrganizationMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by hocgin on 2018/12/22.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
public class OrganizationServiceImpl extends BaseService<OrganizationMapper, Organization>
        implements OrganizationService {
    
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
                                Organization organization) {
        baseMapper.addChildNode(parentId, organization);
        return true;
    }
    
    @Override
    public boolean addSiblingNode(Serializable id,
                                  Organization organization) {
        baseMapper.addSiblingNode(id, organization);
        return true;
    }
    
    @Override
    public IPage<Organization> page(GetCondition condition) {
        Page<Organization> page = condition.page();
        QueryWrapper<Organization> wrapper = condition.wrapper();
        return baseMapper.selectPage(page, wrapper);
    }
    
    @Override
    public IPage<Organization> page(PostCondition condition) {
        Page<Organization> page = condition.page();
        QueryWrapper<Organization> wrapper = condition.wrapper();
        return baseMapper.selectPage(page, wrapper);
    }
}
