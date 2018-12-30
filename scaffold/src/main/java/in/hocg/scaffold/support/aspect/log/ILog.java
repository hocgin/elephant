package in.hocg.scaffold.support.aspect.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hocgin on 2017/11/17.
 * email: hocgin@gmail.com
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ILog {
    /**
     * 标志,例如 [后台登陆]
     *
     * @return
     */
    String value() default "NONE";
    
    /**
     * 消息内容, 支持SpEL
     * - #args
     * - #request
     * - #response
     * - #return
     *
     * @return
     */
    String message() default "''";
    
    String source() default "系统管理后台";
    
    Level level() default Level.INFO;
}
