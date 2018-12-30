package in.hocg.scaffold.support.aspect.log;

import in.hocg.scaffold.support.spel.SpelParser;
import in.hocg.scaffold.util.RequestKit;
import in.hocg.scaffold.util.ResponseKit;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

/**
 * @author hocgin
 * @date 2017/11/17
 * email: hocgin@gmail.com
 * <p>
 * 使用 @ILog 记录系统日志
 */
@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class ILogAspect {
    
    private final LogRepository repository;
    
    @Pointcut("execution(* in.hocg..*(..)) && @annotation(in.hocg.scaffold.support.aspect.log.ILog)")
    public void logPointcut() {
    }
    
    @Around("logPointcut()")
    public Object logHandler(ProceedingJoinPoint point) throws Throwable {
        StopWatch watch = new StopWatch();
        watch.start();
        Object result = point.proceed();
        doLog(watch, point, result);
        return result;
    }
    
    private void doLog(StopWatch watch, ProceedingJoinPoint point, Object result) {
        if (watch.isRunning()) {
            watch.stop();
        }
        long usageTime = watch.getTotalTimeMillis();
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        Class aClass = point.getSourceLocation().getWithinType();
        String mapping = String.format("%s#%s", aClass.getName(), method.getName());
        ILog annotation = method.getAnnotation(ILog.class);
        String msg = annotation.message();
        if (Strings.isNotBlank(msg)) {
            StandardEvaluationContext context = new StandardEvaluationContext();
            context.setVariable("args", point.getArgs());
            context.setVariable("request", RequestKit.get());
            context.setVariable("response", ResponseKit.get());
            context.setVariable("return", result);
            msg = SpelParser.parser(msg, context);
        }
        repository.handle(annotation.level(),
                mapping,
                msg,
                annotation.source(),
                annotation.value(),
                usageTime,
                result);
    }
}
