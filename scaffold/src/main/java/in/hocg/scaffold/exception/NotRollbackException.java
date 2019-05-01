package in.hocg.scaffold.exception;

/**
 * Created by hocgin on 2018/12/12.
 * email: hocgin@gmail.com
 * <p>
 * 非回滚异常
 *
 * @author hocgin
 */
public class NotRollbackException extends ResponseException {
    public NotRollbackException(String message, int code) {
        super(message, code);
    }
}
