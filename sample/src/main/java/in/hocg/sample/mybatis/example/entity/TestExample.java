package in.hocg.sample.mybatis.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.mybatis.basic.model.DeletedModel;
import in.hocg.mybatis.enums.Enable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 例子表
 * </p>
 *
 * @author hocgin
 * @since 2018-10-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ToString
public class TestExample extends DeletedModel<TestExample> {
    
    private static final long serialVersionUID = 1L;
    
    @TableField("name")
    private String name;
    @TableField("type")
    private String type;
    @TableField("enable")
    private Enable enable = Enable.ON;
    
    
    public static final String NAME = "name";
    
    public static final String TYPE = "type";
    
}
