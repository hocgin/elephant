package in.hocg.manager.model.parameter;

import in.hocg.scaffold.support.basis.parameter.BaseParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by hocgin on 2019/1/1.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class AddResource extends BaseParameter {
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
    private int type;
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
    private boolean enabled = true;
    
}
