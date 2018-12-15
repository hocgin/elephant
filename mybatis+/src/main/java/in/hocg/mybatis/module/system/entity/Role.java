package in.hocg.mybatis.module.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.mybatis.basic.model.SuperModel;
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
public class Role extends SuperModel<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色标识
     */
    @TableField(IDENTIFICATION)
    private String identification;
    /**
     * 树级路径
     */
    @TableField(TREE_PATH)
    private String treePath;
    /**
     * 父级ID
     */
    @TableField(PARENT_ID)
    private String parentId;
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
    /**
     * 排序
     */
    @TableField(SORT)
    private Integer sort;


    public static final String IDENTIFICATION = "identification";

    public static final String TREE_PATH = "tree_path";

    public static final String PARENT_ID = "parent_id";

    public static final String NAME = "name";

    public static final String ALIAS = "alias";

    public static final String DESCRIPTION = "description";

    public static final String SORT = "sort";

}
