package in.hocg.scaffold.support.job;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by hocgin on 2018/10/13.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface Task {
    
    String group();
    
    String id();
    
    int priority();
    
    Map<String, Object> params();
    
    Class clazz();
    
    String cron();
    
    /**
     * 重复执行次数
     * @return
     */
    int repeat();
    
    LocalDateTime begin();
    
    LocalDateTime end();
}
