package in.hocg.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import in.hocg.manager.service.FileRecordService;
import in.hocg.mybatis.basic.BaseService;
import in.hocg.mybatis.module.basic.entity.FileRecord;
import in.hocg.mybatis.module.basic.mapper.FileManagerMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Optional;

/**
 * <p>
 * [基础模块] 文件管理表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2018-12-23
 */
@Service
public class FileRecordServiceImpl extends BaseService<FileManagerMapper, FileRecord>
        implements FileRecordService {
    
    @Override
    public Optional<FileRecord> fetchNotDeletedForId(Serializable id) {
        Wrapper<FileRecord> queryWrapper = new LambdaQueryWrapper<FileRecord>()
                .eq(FileRecord::getId, id)
                .eq(FileRecord::isDeleted, 0);
        return Optional.ofNullable(baseMapper.selectOne(queryWrapper));
    }
}
