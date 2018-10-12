package in.hocg.sample;

import in.hocg.job.TestJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author hocgin
 * @date 18-10-10
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuartzTesty {
    
    @Test
    public void testX() throws InterruptedException, SchedulerException {
        System.out.println("==>");
    
        // 任务详情(执行内容)
        JobDetail detail = JobBuilder.newJob(TestJob.class)
                .withIdentity("TestJob")
                .storeDurably().build();
        
        // 时间
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2)
                .repeatForever();
        
        // 任务触发器
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("MyTrigger")
                .withSchedule(simpleScheduleBuilder)
                .build();
        
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(detail, trigger);
        scheduler.start();
    
    
        for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.anyGroup())) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            System.out.println(triggers.size());
        }
        
        
        
        
        Thread.sleep(60000);
    }
}
