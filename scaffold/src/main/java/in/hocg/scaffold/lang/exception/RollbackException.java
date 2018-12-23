package in.hocg.scaffold.lang.exception;

/**
 * Created by hocgin on 2018/12/12.
 * email: hocgin@gmail.com
 * <p>
 * 回滚型异常
 *
 * @author hocgin
 */
public class RollbackException extends ResponseException {
    public RollbackException(String message, int code) {
        super(message, code);
    }
}
