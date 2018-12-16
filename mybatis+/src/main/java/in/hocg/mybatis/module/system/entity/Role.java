package in.hocg.mybatis.module.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.mybatis.basic.model.NodeModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * [权限模块] 角色表
 * </p>
 *
 * @author hocgin
 * @since 2018-10-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("role")
public class Role extends NodeModel<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色标识
     */
    @TableField(MARK)
    private String mark;
    /**
     * 角色名称
     */
    @TableField(NAME)
    private String name;
    /**
     * 角色别称
     */
    @TableField(ALIAS)
    private String alias;
    /**
     * 角色描述
     */
    @TableField(DESCRIPTION)
    private String description;


    public static final String MARK = "mark";

    public static final String NAME = "name";

    public static final String ALIAS = "alias";

    public static final String DESCRIPTION = "description";

}
