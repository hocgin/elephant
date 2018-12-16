package in.hocg.manager.controller;

import in.hocg.scaffold.support.basis.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2018/10/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
public class MainController extends BaseController {
    
    
    @GetMapping("/user/resources")
    @ResponseBody
    public ResponseEntity resource() {
        return ResponseEntity.ok("");
    }
    
}
