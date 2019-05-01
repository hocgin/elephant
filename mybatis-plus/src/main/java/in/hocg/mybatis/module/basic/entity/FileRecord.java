package in.hocg.mybatis.module.basic.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.mybatis.basic.model.DeletedModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2018/12/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("file_record")
public class FileRecord extends DeletedModel<FileRecord> {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 上传时文件名(全名称)
     */
    @TableField(ORIGIN_NAME)
    private String originName;
    /**
     * 存储后文件名(全名称)
     */
    @TableField(STORAGE_NAME)
    private String storageName;
    /**
     * 文件类型
     */
    @TableField(TYPE)
    private String type;
    /**
     * 文件大小
     */
    @TableField(SIZE)
    private Long size;
    /**
     * 文件的MD5校验码
     */
    @TableField(MD5)
    private String md5;
    /**
     * 存储的 OSS 平台
     */
    @TableField(OSS)
    private String oss;
    /**
     * 上传者
     */
    @TableField(UPLOADER)
    private String uploader;
    /**
     * 是否公开 [私有, 公开]
     */
    @TableField(PUBLICITY)
    private Boolean publicity;
    
    
    public static final String ORIGIN_NAME = "origin_name";
    
    public static final String STORAGE_NAME = "storage_name";
    
    public static final String TYPE = "type";
    
    public static final String SIZE = "size";
    
    public static final String MD5 = "md5";
    
    public static final String OSS = "oss";
    
    public static final String UPLOADER = "uploader";
    
    public static final String PUBLICITY = "publicity";
    
}
