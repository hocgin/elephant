package in.hocg.scaffold.lang.exception;

/**
 * @author hocgin
 * @date 18-8-6
 **/
public final class MapperException extends Exception {
    
    private MapperException(String message) {
        super(message);
    }
    
    public static MapperException wrap(String message) {
        return new MapperException(message);
    }
}
