package in.hocg.mybatis.basic.model;

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
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    
    public static final String ID = "id";
}
