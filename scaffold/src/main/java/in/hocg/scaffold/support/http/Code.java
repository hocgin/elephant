package in.hocg.scaffold.support.http;

/**
 * Created by hocgin on 2018/12/12.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public enum Code {
    /**
     * 成功
     */
    SUCCESS(200, "ok"),
    ERROR(500, "error");
    int code;
    String message;
    
    Code(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
