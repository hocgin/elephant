package in.hocg.mybatis.module.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.mybatis.basic.model.SuperModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * [权限模块] 资源表
 * </p>
 *
 * @author hocgin
 * @since 2018-10-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("resource")
public class Resource extends SuperModel<Resource> {

    private static final long serialVersionUID = 1L;

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
     * 菜单名称
     */
    @TableField("name")
    private String name;
    /**
     * 菜单别称
     */
    @TableField("alias")
    private String alias;
    /**
     * 菜单描述
     */
    @TableField("description")
    private String description;
    /**
     * 菜单类型(0:菜单;1:按钮)
     */
    @TableField("type")
    private Boolean type;
    /**
     * 请求类型(GET,POST,DELETE,PUT)
     */
    @TableField("method")
    private String method;
    /**
     * URI
     */
    @TableField("uri")
    private String uri;
    /**
     * 图标
     */
    @TableField("icon")
    private String icon;
    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;
    /**
     * 链接显示状态(0:不显示; 1:显示)
     */
    @TableField("showed")
    private Boolean showed;
    /**
     * 是否开启显示(0:不开启; 1:开启)
     */
    @TableField("enabled")
    private Boolean enabled;


    public static final String TREE_PATH = "tree_path";

    public static final String PARENT_ID = "parent_id";

    public static final String NAME = "name";

    public static final String ALIAS = "alias";

    public static final String DESCRIPTION = "description";

    public static final String TYPE = "type";

    public static final String METHOD = "method";

    public static final String URI = "uri";

    public static final String ICON = "icon";

    public static final String SORT = "sort";

    public static final String SHOWED = "showed";

    public static final String ENABLED = "enabled";

}
