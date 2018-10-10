package in.hocg.bean.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author hocgin
 * @date 18-10-10
 **/
@Slf4j
@Component
public class TestBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!Objects.equals(beanName, "testBean")) {
            return bean;
        }
        log.debug("## {}=> postProcessAfterInitialization", beanName);
        return bean;
    }
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (!Objects.equals(beanName, "testBean")) {
            return bean;
        }
        
        log.debug("## {}=> postProcessBeforeInitialization", beanName);
        return bean;
    }
}
