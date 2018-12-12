package in.hocg.sample.controller;

import in.hocg.sample.controller.body.User;
import in.hocg.sample.mybatis.example.entity.TestExample;
import in.hocg.scaffold.support.basis.BaseController;
import in.hocg.scaffold.support.json.annotation.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hocgin on 2018/10/9.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Controller
@RequestMapping
@AllArgsConstructor
public class IndexController extends BaseController {
    
    @GetMapping("worked")
    public ResponseEntity<String> worked() {
        return ResponseEntity.ok("ok");
    }
    
    @JSON(className = User.class, exclude = {"id"})
    @GetMapping("user")
    @ResponseBody
    public ResponseEntity<User> jfilter() {
        User user = new User("123", "hocgin");
        return ResponseEntity.ok(user);
    }
    
    @GetMapping("enums")
    @ResponseBody
    public ResponseEntity<TestExample> enums() {
        return ResponseEntity.ok(new TestExample());
    }
    
}
