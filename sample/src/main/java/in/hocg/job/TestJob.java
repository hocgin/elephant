package in.hocg.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author hocgin
 * @date 18-10-10
 **/
@Slf4j
public class TestJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();
        log.debug("执行任务[任务内容] {}:{}", key.getGroup(), key.getName());
    }
}
