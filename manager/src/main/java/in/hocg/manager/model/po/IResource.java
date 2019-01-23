package in.hocg.manager.model.po;

import in.hocg.scaffold.support.basis.parameter.BaseParameter;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * Created by hocgin on 2019/1/1.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ToString
public class IResource implements BaseParameter {
    /**
     * 关联节点
     */
    @NotBlank(message = "请选择关联节点")
    private String refNode;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单描述
     */
    private String description;
    /**
     * 菜单类型[目录,按钮,链接]
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
    private boolean enabled = true;
    
}
