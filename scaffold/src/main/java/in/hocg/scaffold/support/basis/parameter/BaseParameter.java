package in.hocg.scaffold.support.basis.parameter;

import in.hocg.scaffold.support.basis.Bean;

/**
 * Created by hocgin on 2018/12/18.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public abstract class BaseParameter implements Bean {
    
    @Override
    public <T extends Bean> T fill(Object object) {
        throw new UnsupportedOperationException("请实现它");
    }
}
