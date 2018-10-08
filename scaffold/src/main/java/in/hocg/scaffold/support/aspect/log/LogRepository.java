package in.hocg.scaffold.support.aspect.log;

/**
 * @author hocgin
 * @date 18-10-8
 **/
public interface LogRepository {
    /**
     * 处理日志接口
     * @param source
     * @param message
     * @param timeMillis
     */
    void handle(String source, String message, long timeMillis);
    
    /**
     * 错误日志接口
     * @param source
     * @param error
     * @param timeMillis
     */
    void error(String source, String error, long timeMillis);
}
