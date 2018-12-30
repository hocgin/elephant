package in.hocg.scaffold.support.aspect.log;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hocgin
 * @date 18-10-8
 **/
@Slf4j
public class DefaultLogRepository implements LogRepository {
    @Override
    public void handle(Level level,
                       String mapping,
                       String message,
                       String source,
                       String operating,
                       long timeMillis,
                       Object result) {
        String format = "\n[@ILog]来源:{}\n信息:{}\n耗时:{}ms";
        if (Level.INFO == level) {
            log.info(format, mapping, message, timeMillis);
        } else {
            log.error(format, mapping, message, timeMillis);
        }
    }
}
