package in.hocg.scaffold.lang.exception;

/**
 * Created by hocgin on 2018/12/12.
 * email: hocgin@gmail.com
 * <p>
 * 非回滚异常
 *
 * @author hocgin
 */
public class NotRollbackException extends Exception {
    
    private NotRollbackException(String message) {
        super(message);
    }
    
    public static NotRollbackException wrap(String message) {
        return new NotRollbackException(message);
    }
}
