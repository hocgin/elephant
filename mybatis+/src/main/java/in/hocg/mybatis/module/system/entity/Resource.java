package in.hocg.mybatis.module.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.mybatis.basic.model.NodeModel;
import in.hocg.mybatis.enums.ResourceType;
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
public class Resource extends NodeModel<Resource> {
    
    private static final long serialVersionUID = 1L;
    /**
     * 菜单名称
     */
    @TableField(NAME)
    private String name;
    /**
     * 菜单别称
     */
    @TableField(ALIAS)
    private String alias;
    /**
     * 菜单描述
     */
    @TableField(DESCRIPTION)
    private String description;
    /**
     * 菜单类型(0:菜单;1:按钮)
     */
    @TableField(TYPE)
    private ResourceType type;
    /**
     * 请求类型(GET,POST,DELETE,PUT)
     */
    @TableField(METHOD)
    private String method;
    /**
     * URI
     */
    @TableField(URI)
    private String uri;
    /**
     * 图标
     */
    @TableField(ICON)
    private String icon;
    /**
     * 是否开启显示(0:未启用; 1:启用)
     */
    @TableField(ENABLE)
    private boolean enabled = true;
    
    public static final String NAME = "name";
    
    public static final String ALIAS = "alias";
    
    public static final String DESCRIPTION = "description";
    
    public static final String TYPE = "type";
    
    public static final String METHOD = "method";
    
    public static final String URI = "uri";
    
    public static final String ICON = "icon";
    
    public static final String ENABLE = "enabled";
    
}
