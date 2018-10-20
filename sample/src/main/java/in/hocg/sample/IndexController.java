package in.hocg.sample;

import in.hocg.module.example.entity.TestExample;
import in.hocg.module.example.mapper.TestExampleMapper;
import in.hocg.scaffold.support.basis.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2018/10/9.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@RestController
public class IndexController extends BaseController {
    
    @Autowired
    TestExampleMapper mapper;
    
    @Autowired
    MessageSource messageSource;
    
    @RequestMapping("/index")
    public ResponseEntity<String> index() {
    
        String message = messageSource.getMessage("welcome", null, LocaleContextHolder.getLocale());
        log.debug(message);
        new TestExample()
                .setName("hocgin")
                .setCreatedAt(LocalDateTime.now())
                .insert();
        return ResponseEntity.ok("OK");
    }
    
}
