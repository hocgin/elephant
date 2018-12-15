package in.hocg.scaffold.support;

import in.hocg.scaffold.support.http.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author hocgin
 * @date 2018/6/18
 * email: hocgin@gmail.com
 * 全局异常处理
 */
//@ProdAndTest
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity handle(Exception e) {
        log.debug(e.getLocalizedMessage());
        return Result.error(e.getLocalizedMessage())
                .setData(e.getClass().getName())
                .asResponseEntity();
    }
    
}
