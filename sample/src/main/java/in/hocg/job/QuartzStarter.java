package in.hocg.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hocgin
 * @date 18-10-10
 **/
@Configuration
@Slf4j
public class QuartzStarter {
    
    @Bean
    public JobDetail detail() {
        log.debug("创建任务详情");
        return JobBuilder.newJob(TestJob.class)
                .withIdentity("MyJob")
                .storeDurably().build();
    }
    
    @Bean
    public Trigger myJobTrigger() {
        log.debug("设定触发时间");
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(detail())
                .withIdentity("MyTrigger")
                .withSchedule(simpleScheduleBuilder)
                .build();
    }
}
