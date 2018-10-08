package in.hocg.scaffold.lang.exception;

/**
 * @author hocgin
 * @date 18-8-6
 **/
public final class ServiceException extends Exception {
    
    private ServiceException(String message) {
        super(message);
    }
    
    public static ServiceException wrap(String message) {
        return new ServiceException(message);
    }
}
