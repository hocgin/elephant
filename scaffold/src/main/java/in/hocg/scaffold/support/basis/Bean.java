package in.hocg.scaffold.support.basis;

import in.hocg.scaffold.util.ClassKit;
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
     * @param target
     * @param <T>
     * @return
     */
    default <T> T copyTo(T target) {
        BeanUtils.copyProperties(this, target);
        return target;
    }
    
    /**
     * Bean 中非空属性 -拷贝-> T
     *
     * @param target
     * @param <T>
     * @return
     */
    default <T> T copyNotNullTo(T target) {
        BeanUtils.copyProperties(this, target, ClassKit.getNullValueFields(this));
        return target;
    }
    
    /**
     * Bean -转化成-> T
     *
     * @param clazz
     * @return
     */
    @SneakyThrows
    default <T> T copyTo(Class<T> clazz) {
        T target = clazz.getDeclaredConstructor()
                .newInstance();
        return copyTo(target);
    }
    
    /**
     * object 对象填充 T
     *
     * @param object
     * @return
     */
    default Object fill(Object object) {
        throw new UnsupportedOperationException("请实现它");
    }
    
    
}
