package in.hocg.manager.service;


import com.baomidou.mybatisplus.extension.service.IService;
import in.hocg.mybatis.module.basic.entity.FileRecord;

import java.io.Serializable;
import java.util.Optional;

/**
 * <p>
 * [基础模块] 文件管理表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2018-12-23
 */
public interface FileRecordService extends IService<FileRecord> {
    
    /**
     * 查找未删除的文件
     * @param id
     * @return
     */
    Optional<FileRecord> fetchNotDeletedForId(Serializable id);
}
