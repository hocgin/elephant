package in.hocg.scaffold.support.basis;

import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * Created by hocgin on 2018/12/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface Bean extends Serializable {
    
    /**
     * Bean -转化成-> T
     *
     * @param object
     * @param <T>
     * @return
     */
    default <T> T cast(T object) {
        BeanUtils.copyProperties(this, object);
        return object;
    }
    
    /**
     * Bean -转化成-> T
     *
     * @param clazz
     * @return
     */
    @SneakyThrows
    default <T> T cast(Class<T> clazz) {
        T target = clazz.newInstance();
        return cast(target);
    }
    
    /**
     * object 对象填充 T
     *
     * @param object
     * @param <T>
     * @return
     */
    default <T extends Bean> T fill(Object object) {
        throw new UnsupportedOperationException("请实现它");
    }
    
    
    
}
