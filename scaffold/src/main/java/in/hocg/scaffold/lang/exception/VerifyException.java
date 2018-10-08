package in.hocg.scaffold.lang.exception;

/**
 * @author hocgin
 * @date 18-8-6
 **/
public final class VerifyException extends Exception {
    
    public VerifyException(String message) {
        super(message);
    }
    
    public static VerifyException wrap(String message) {
        return new VerifyException(message);
    }
}
