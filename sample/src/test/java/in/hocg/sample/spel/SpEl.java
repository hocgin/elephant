package in.hocg.sample.spel;

import in.hocg.scaffold.support.spel.SpelParser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Created by hocgin on 2018/10/16.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */

@Slf4j
@SpringBootTest
public class SpEl {
    
    
    @Test
    public void testEl() {
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("var", 1024);
        String text = SpelParser.parser("hi #{#var + 1}", context);
        String text2 = SpelParser.parser("", context);
        log.debug("{} {}", text, text2);
    }
}
