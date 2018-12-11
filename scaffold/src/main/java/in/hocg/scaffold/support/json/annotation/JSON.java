package in.hocg.scaffold.support.json.annotation;

import java.lang.annotation.*;

/**
 * Created by hocgin on 2018/9/2.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(JSONs.class)
public @interface JSON {
    
    /**
     * 需要排除的类
     * @return
     */
    Class<?> className();
    
    /**
     * 仅包含属性
     * @return
     */
    String[] include() default "";
    
    /**
     * 排除属性
     * @return
     */
    String[] exclude() default "";
}
