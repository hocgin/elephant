package in.hocg.scaffold.support.job;

import in.hocg.util.DateKit;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.utils.Key;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by hocgin on 2018/10/13.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface Task {
    
    /**
     * 分组
     *
     * @return
     */
    default String group() {
        return Key.DEFAULT_GROUP;
    }
    
    /**
     * 标识
     *
     * @return
     */
    default String name() {
        return clazz().getName();
    }
    
    /**
     * 描述
     *
     * @return
     */
    default String description() {
        return "";
    }
    
    /**
     * 优先级
     *
     * @return
     */
    default int priority() {
        return Trigger.DEFAULT_PRIORITY;
    }
    
    /**
     * 参数
     *
     * @return
     */
    default Map<String, Object> params() {
        return new HashMap<>();
    }
    
    /**
     * 执行类
     *
     * @return
     */
    Class<? extends Job> clazz();
    
    /**
     * 开始时间
     *
     * @return
     */
    default LocalDateTime begin() {
        return LocalDateTime.now();
    }
    
    /**
     * JobKey
     *
     * @return
     */
    default JobKey key() {
        return JobKey.jobKey(name(), group());
    }
    
    default TriggerKey triggerKey() {
        return TriggerKey.triggerKey(name(), group());
    }
    
    /**
     * Schedule
     *
     * @return
     */
    ScheduleBuilder scheduleBuilder();
    
    /**
     * Job
     *
     * @return
     */
    default JobBuilder jobBuilder() {
        return JobBuilder.newJob(clazz())
                .withIdentity(key());
    }
    
    /**
     * Trigger
     *
     * @return
     */
    default TriggerBuilder triggerBuilder() {
        // Trigger
        TriggerBuilder triggerBuilder = TriggerBuilder.newTrigger()
                .withSchedule(scheduleBuilder())
                .usingJobData(new JobDataMap(params()))
                .withDescription(description())
                .withPriority(priority())
                .startAt(DateKit.as(begin()));
        
        end().ifPresent((endAt) -> {
            triggerBuilder.endAt(DateKit.as(endAt));
        });
        return triggerBuilder;
    }
    
    /**
     * 结束时间
     *
     * @return
     */
    default Optional<LocalDateTime> end() {
        return Optional.empty();
    }
    
    /**
     * 是否生效
     *
     * @return
     */
    default boolean enable() {
        return true;
    }
    
    
    /**
     * 构建
     *
     * @return
     */
    default void submit(boolean replace) throws Exception {
        if (!enable()) {
            return;
        }
        
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        
        JobKey key = key();
        
        // 替换原有任务
        if (replace) {
            if (scheduler.checkExists(key)) {
                scheduler.rescheduleJob(triggerKey(), triggerBuilder().build());
                return;
            }
        }
        scheduler.scheduleJob(jobBuilder().build(), triggerBuilder().build());
    }
}
