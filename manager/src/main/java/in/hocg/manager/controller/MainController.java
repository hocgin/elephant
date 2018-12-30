package in.hocg.manager.controller;

import in.hocg.scaffold.support.basis.BaseController;
import in.hocg.scaffold.support.http.Result;
import in.hocg.scaffold.support.http.wrapper.RequestWrapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hocgin on 2018/10/21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@AllArgsConstructor
public class MainController extends BaseController {
    private final RequestMappingHandlerMapping handlerMapping;
    
    
    @GetMapping("/user/resources")
    @ResponseBody
    public ResponseEntity resource() {
        return ResponseEntity.ok("");
    }
    
    @ResponseBody
    @RequestMapping("/urls")
    public ResponseEntity urls(RequestWrapper request) {
        Map<RequestMappingInfo, HandlerMethod> map = this.handlerMapping.getHandlerMethods();
        List<String> urls = new ArrayList<>(map.size());
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : map.entrySet()) {
            System.out.println(entry.getKey() + System.lineSeparator() + entry.getValue());
            urls.add(entry.getKey().toString());
        }
        return Result.success(urls).asResponseEntity();
    }
}
