package in.hocg.scaffold.support.basis;

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
  @TableField(value = "created_at")
  private LocalDateTime createdAt;
  @TableField(value = "updated_at", update = "now()")
  private LocalDateTime updatedAt;
}
