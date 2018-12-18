package in.hocg.scaffold.support;

import in.hocg.scaffold.lang.exception.NotRollbackException;
import in.hocg.scaffold.lang.exception.RollbackException;
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
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity handle(Exception e) {
        if (e instanceof NotRollbackException || e instanceof RollbackException) {
            log.debug("异常[{}]", e.getClass());
        } else {
            e.printStackTrace();
        }
        return Result.error(e.getLocalizedMessage())
                .setData(e.getClass().getName())
                .asResponseEntity();
    }
    
}
