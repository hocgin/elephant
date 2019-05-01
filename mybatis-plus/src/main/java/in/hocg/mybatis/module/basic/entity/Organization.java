package in.hocg.mybatis.module.basic.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.mybatis.basic.model.NodeModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2018/12/22.
 * email: hocgin@gmail.com
 * 组织结构表
 *
 * @author hocgin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("organization")
public class Organization extends NodeModel<Organization> {
    
    /**
     * 名称
     */
    @TableField(NAME)
    private String name;
    
    /**
     * 描述
     */
    @TableField(DESCRIPTION)
    private String description;
    
    public static final String NAME = "name";
    
    public static final String DESCRIPTION = "description";
}
