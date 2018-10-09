package in.hocg.basic;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author hocgin
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class DefaultModel<T extends Model> extends SuperModel<T> {
  /**
   * 创建时间
   */
  @TableField(value = CREATED_AT)
  private LocalDateTime createdAt;
  
  /**
   * 创建者
   */
  @TableField(value = CREATOR)
  private LocalDateTime creator;
  
  /**
   * 更新时间
   */
  @TableField(value = UPDATED_AT, update = "now()")
  private LocalDateTime updatedAt;
  
  /**
   * 更新者
   */
  @TableField(value = UPDATER)
  private LocalDateTime updater;
  
  
  public static final String UPDATER = "updater";
  public static final String UPDATED_AT = "updated_at";
  public static final String CREATOR = "creator";
  public static final String CREATED_AT = "created_at";
}
