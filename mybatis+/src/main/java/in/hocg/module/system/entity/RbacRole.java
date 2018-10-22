package in.hocg.module.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.basic.model.SuperModel;
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
public class RbacRole extends SuperModel<RbacRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色标识
     */
    @TableField("identification")
    private String identification;
    /**
     * 树级路径
     */
    @TableField("tree_path")
    private String treePath;
    /**
     * 父级ID
     */
    @TableField("parent_id")
    private String parentId;
    /**
     * 角色名称
     */
    @TableField("name")
    private String name;
    /**
     * 角色别称
     */
    @TableField("alias")
    private String alias;
    /**
     * 角色描述
     */
    @TableField("description")
    private String description;
    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;


    public static final String IDENTIFICATION = "identification";

    public static final String TREE_PATH = "tree_path";

    public static final String PARENT_ID = "parent_id";

    public static final String NAME = "name";

    public static final String ALIAS = "alias";

    public static final String DESCRIPTION = "description";

    public static final String SORT = "sort";

}
