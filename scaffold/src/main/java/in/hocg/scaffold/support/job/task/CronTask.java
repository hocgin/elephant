package in.hocg.scaffold.support.job.task;

import org.quartz.CronScheduleBuilder;
import org.quartz.ScheduleBuilder;

/**
 * Created by hocgin on 2018/10/14.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface CronTask extends Task {
    
    /**
     * Cron 表达式
     *
     * @return
     */
    String cron();
    
    
    @Override
    default ScheduleBuilder scheduleBuilder() {
        return CronScheduleBuilder.cronSchedule(cron());
    }
    
}
