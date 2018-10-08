package in.hocg.scaffold.annotation;

import org.springframework.context.annotation.Profile;

/**
 * @author hocgin
 * @date 18-9-25
 * 开发环境
 **/
@Profile({"dev","test"})
public @interface DevAndTest {
}
