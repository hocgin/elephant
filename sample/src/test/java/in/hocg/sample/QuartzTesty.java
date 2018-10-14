package in.hocg.sample;

import in.hocg.job.TestJob;
import in.hocg.sample.quartz.listener.TestJobListener;
import in.hocg.util.DateKit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
                .withIdentity("TestJob", "g")
                .storeDurably()
                .build();
        
        // ＿簡單的时间
        ScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2)
                .repeatForever();
        
        // _Cron 表達式
//        ScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule("");
        
        // 任务触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("MyTrigger")
                .withSchedule(scheduleBuilder)
                .build();
        
        // 執行器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.getListenerManager()
                .addJobListener(new TestJobListener());
        scheduler.start();
        
        
        scheduler.scheduleJob(detail, trigger);
        
        
        // 列出所有
        for (String groupName : scheduler.getJobGroupNames()) {
            
            for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                
                String jobName = jobKey.getName();
                String jobGroup = jobKey.getGroup();
                
                //get job's trigger
                List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                Date nextFireTime = triggers.get(0).getNextFireTime();
                
                System.out.println("[唯一标识] : " + jobName + " [群组] : "
                        + jobGroup + " 下一次触发时间 " + SimpleDateFormat.getDateTimeInstance().format(nextFireTime));
                
            }
            
        }
        
        
        Thread.sleep(60000);
    }
    
    @Test
    public void testCron() throws SchedulerException, ParseException, InterruptedException {
        System.out.println("==> Cron ");
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        JobDetail detail = JobBuilder.newJob(TestJob.class)
                .build();
    
        LocalDateTime startAt = LocalDateTime.now().plusMinutes(2);
        LocalDateTime endAt = LocalDateTime.now().plusMinutes(4);
    
        System.out.println("开始时间" + startAt.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println("结束时间" + endAt.format(DateTimeFormatter.ISO_DATE_TIME));
        
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(cronSchedule)
                .startAt(DateKit.as(startAt))
                .endAt(DateKit.as(endAt))
                .build();
        scheduler.scheduleJob(detail, trigger);
        scheduler.start();
    
        Thread.sleep(600_000_000);
    }
}
