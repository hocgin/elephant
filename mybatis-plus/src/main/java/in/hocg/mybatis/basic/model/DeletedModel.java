package in.hocg.mybatis.basic.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 这对象用于存在伪删除表
 *
 * @author hocgin
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class DeletedModel<T extends Model> extends DefaultModel<T> {
    /**
     * 删除时间
     */
    @TableField(value = DELETED_AT)
    private LocalDateTime deletedAt;
    
    /**
     * 删除者
     */
    @TableField(value = DELETER)
    private String deleter;
    
    /**
     * 0:为正常状态
     * 1:为被删除状态
     */
    @TableField(value = DELETED)
    private boolean deleted;
    
    public static final String DELETED = "deleted";
    public static final String DELETED_AT = "deleted_at";
    public static final String DELETER = "deleter";
}
