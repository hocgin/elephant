package in.hocg.sample;

import in.hocg.entity.TestExample;
import in.hocg.mapper.TestExampleMapper;
import in.hocg.scaffold.support.basis.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
public class IndexController extends BaseController {
    
    @Autowired
    TestExampleMapper mapper;
    
    @RequestMapping("/index")
    public ResponseEntity<String> index() {
        new TestExample()
                .setName("hocgin")
                .setCreatedAt(LocalDateTime.now())
                .insert();
        return ResponseEntity.ok("OK");
    }
    
}
