package in.hocg.scaffold.lang.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;

/**
 * Created by hocgin on 2018/12/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ResponseException extends Exception {
    private int code = 500;
    
    public ResponseException(String message, int code) {
        super(message);
        this.code = code;
    }
    
    @SneakyThrows
    public static <T extends ResponseException> T wrap(Class<T> tClass, String message) {
        return wrap(tClass, message, 500);
    }
    
    @SneakyThrows
    public static <T extends ResponseException> T wrap(Class<T> tClass, String message, int code) {
        Constructor<T> constructor = tClass.getConstructor(String.class, int.class);
        return constructor.newInstance(message, code);
    }
    
}
