package in.hocg.scaffold.support.json.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hocgin on 2018/9/2.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JSONs {
    JSON[] value();
}
