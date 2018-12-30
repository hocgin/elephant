package in.hocg.scaffold.support.aspect.log;

/**
 * @author hocgin
 * @date 18-10-8
 **/
public interface LogRepository {
    /**
     * 处理日志接口
     *
     * @param level
     * @param mapping
     * @param message
     * @param timeMillis
     * @param result
     */
    void handle(Level level,
                String mapping,
                String message,
                String source,
                String operating,
                long timeMillis,
                Object result);
}
