package in.hocg.sample.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;

/**
 * @author hocgin
 * @date 18-10-10
 **/
@Data
@Slf4j
@AllArgsConstructor
public class TestBean implements BeanNameAware,
        BeanFactoryAware,
        ApplicationContextAware,
        InitializingBean {
    private String name;
    
    public TestBean() {
        log.debug("## testBean 无参构造函数");
    }
    
    public void init() {
        log.debug("## testBean init() 执行中");
    }
    
    @PostConstruct
    public void PostConstruct() {
        log.debug("## testBean @PostConstruct方法执行中");
    }
    
    @Override
    public void setBeanName(String name) {
        log.debug("## testBean setBeanName()");
    }
    
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.debug("## testBean setBeanFactory()");
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.debug("## testBean setApplicationContext()");
        
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug("## testBean afterPropertiesSet()");
    }
}
