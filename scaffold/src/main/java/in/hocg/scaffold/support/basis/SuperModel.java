package in.hocg.scaffold.support.basis;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public abstract class SuperModel<T extends Model> extends Model<T> {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
