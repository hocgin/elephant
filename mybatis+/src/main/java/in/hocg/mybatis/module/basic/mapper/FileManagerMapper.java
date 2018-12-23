package in.hocg.mybatis.module.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.mybatis.module.basic.entity.FileRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [基础模块] 文件管理 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2018-10-21
 */
@Mapper
public interface FileManagerMapper extends BaseMapper<FileRecord> {
    
}
