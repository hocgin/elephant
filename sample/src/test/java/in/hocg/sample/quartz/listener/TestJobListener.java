package in.hocg.sample.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * @author hocgin
 * @date 18-10-12
 **/
public class TestJobListener implements JobListener {
    @Override
    public String getName() {
        String name = TestJobListener.class.getName();
        System.err.println(name);
        return name;
    }
    
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        System.err.println("jobToBeExecuted");
    }
    
    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        System.err.println("jobExecutionVetoed");
    }
    
    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.err.println("jobWasExecuted");
    }
}
