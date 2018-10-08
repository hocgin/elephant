package in.hocg.scaffold.annotation;

import org.springframework.context.annotation.Profile;

/**
 * @author hocgin
 * @date 18-9-25
 * 生产环境
 **/
@Profile({"prod", "test"})
public @interface ProdAndTest {
}
