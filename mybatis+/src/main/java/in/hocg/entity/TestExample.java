package in.hocg.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.basic.model.DeletedModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
public class TestExample extends DeletedModel<TestExample> {

    private static final long serialVersionUID = 1L;

    @TableField("name")
    private String name;
    @TableField("type")
    private String type;


    public static final String NAME = "name";

    public static final String TYPE = "type";

}
