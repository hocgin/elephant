package in.hocg.manager.service.impl;

import in.hocg.manager.service.OrganizationService;
import in.hocg.mybatis.basic.BaseService;
import in.hocg.mybatis.module.basic.entity.Organization;
import in.hocg.mybatis.module.basic.mapper.OrganizationMapper;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2018/12/22.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
public class OrganizationServiceImpl extends BaseService<OrganizationMapper, Organization>
        implements OrganizationService {
}
