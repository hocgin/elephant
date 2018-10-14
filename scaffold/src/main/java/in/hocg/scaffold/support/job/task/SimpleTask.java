package in.hocg.scaffold.support.job.task;

import org.quartz.ScheduleBuilder;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;

/**
 * Created by hocgin on 2018/10/14.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface SimpleTask extends Task {
    
    /**
     * 间隔时间(s)
     *
     * @return
     */
    default int intervalInSeconds() {
        return 1;
    }
    
    /**
     * 重复执行次数
     * 默认为无限次数
     *
     * @return
     */
    default int repeat() {
        return SimpleTrigger.REPEAT_INDEFINITELY;
    }
    
    @Override
    default ScheduleBuilder scheduleBuilder() {
        return SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(intervalInSeconds())
                .withRepeatCount(repeat());
    }
}
