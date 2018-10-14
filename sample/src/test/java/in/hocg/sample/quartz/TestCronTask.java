package in.hocg.sample.quartz;

import in.hocg.job.TestJob;
import in.hocg.scaffold.support.job.task.CronTask;
import org.quartz.Job;

/**
 * Created by hocgin on 2018/10/14.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class TestCronTask implements CronTask {
    @Override
    public String cron() {
        return "0/5 * * * * ?";
    }
    
    @Override
    public Class<? extends Job> clazz() {
        return TestJob.class;
    }
}
