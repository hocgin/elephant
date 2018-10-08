package in.hocg.scaffold.support.basis;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 这对象用于存在伪删除表
 * @author hocgin
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class DeletedModel<T extends Model> extends DefaultModel<T> {
  @TableField(value = "deleted_at")
  private LocalDateTime deletedAt;
  /**
   * 0 为正常状态
   * 1 为伪删除状态
   */
  @TableField(value = "deleted")
  @TableLogic
  private int deleted;
}
