package in.hocg.mybatis.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * Created by hocgin on 2018/12/16.
 * email: hocgin@gmail.com
 * <p>
 * 资源类型
 * 0: 菜单
 * 1: 按钮
 *
 * @author hocgin
 */
public enum ResourceType  implements IEnum<Integer> {
    Menu,
    Button;
    
    @Override
    public Integer getValue() {
        return this.ordinal();
    }
}
