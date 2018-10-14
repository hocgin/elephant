package in.hocg.scaffold.lang.exception;

/**
 * Created by hocgin on 2018/10/14.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class WarnException extends Exception {
    
    public WarnException(String message) {
        super(message);
    }
    
    public static VerifyException wrap(String message) {
        return new VerifyException(message);
    }
}
