package in.hocg.scaffold.lang.exception;

/**
 * Created by hocgin on 2018/12/12.
 * email: hocgin@gmail.com
 * <p>
 * 回滚型异常
 *
 * @author hocgin
 */
public class RollbackException extends Exception {
    
    private RollbackException(String message) {
        super(message);
    }
    
    public static RollbackException wrap(String message) {
        return new RollbackException(message);
    }
}
