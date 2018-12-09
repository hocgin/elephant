package in.hocg.basic.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2018/12/9.
 * email: hocgin@gmail.com
 *
 * 树节点专用
 * 来源: http://mikehillyer.com/articles/managing-hierarchical-data-in-mysql/
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class NodeModel<T extends Model> extends SuperModel<T> {
    @TableField("lft")
    private int lft;
    @TableField("rgt")
    private int rgt;
    
    @TableField(exist = false)
    private Integer depth;
    
    public static final String LFT = "lft";
    
    public static final String RGT = "rgt";
}
