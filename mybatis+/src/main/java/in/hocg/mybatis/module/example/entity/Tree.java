package in.hocg.mybatis.module.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.mybatis.basic.model.NodeModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;


/**
 * Created by hocgin on 2018/12/9.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */

@Data
@ToString
@Accessors(chain = true)
@TableName("tree")
@EqualsAndHashCode(callSuper = true)
public class Tree extends NodeModel<Tree> {
    @TableField("name")
    private String name;
    
}
