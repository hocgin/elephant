package in.hocg.scaffold.support.aspect.log;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hocgin
 * @date 18-10-8
 **/
@Slf4j
public class DefaultLogRepository implements LogRepository {
    @Override
    public void handle(String source, String message, long timeMillis) {
        log.info("[@ILog]来源:{}\n信息:{}\n耗时:{}ms", source, message, timeMillis);
    }
    
    @Override
    public void error(String source, String error, long timeMillis) {
        log.error("[@ILog]来源:{}\n错误信息:{}\n耗时:{}ms", source, error, timeMillis);
    }
}
