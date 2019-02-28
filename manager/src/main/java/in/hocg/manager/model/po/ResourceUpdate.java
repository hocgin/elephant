package in.hocg.manager.model.po;

import in.hocg.scaffold.support.basis.parameter.BaseParameter;
import lombok.Data;
import lombok.ToString;

/**
 * Created by hocgin on 2019/1/15.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ToString
public class ResourceUpdate implements BaseParameter {
    
    /**
     * 父节点ID
     */
    private String parent;
    
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单描述
     */
    private String description;
    /**
     * 菜单类型(0:菜单;1:按钮)
     */
    private Integer type;
    /**
     * 请求类型(GET,POST,DELETE,PUT)
     */
    private String method;
    /**
     * URI
     */
    private String path;
    /**
     * 图标
     */
    private String icon;
    /**
     * 是否开启显示(0:未启用; 1:启用)
     */
    private Boolean enabled;
}
