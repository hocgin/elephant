package in.hocg.scaffold.support.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

/**
 * @author hocgin
 * @date 2017/10/14
 * email: hocgin@gmail.com
 * 响应结果对象
 */
public class Result<T> implements Serializable {
    private int code;
    private String message;
    private T data;
    
    private Result() {
    }
    
    public static Result get() {
        return new Result();
    }
    
    public int getCode() {
        return code;
    }
    
    public Result setCode(int code) {
        this.code = code;
        return this;
    }
    
    public String getMessage() {
        return message;
    }
    
    public Result setMessage(String message) {
        this.message = message;
        return this;
    }
    
    public T getData() {
        return data;
    }
    
    public Result setData(T data) {
        this.data = data;
        return this;
    }
    
    
    public static Result success(Object data) {
        return Result.result(Code.SUCCESS.code, Code.SUCCESS.message, data);
    }
    
    public static Result success() {
        return Result.success(null);
    }
    
    public static Result result(Integer code, String message) {
        return Result.result(code, message, null);
    }
    
    public static Result error(String message) {
        return Result.result(Code.ERROR.code, message, null);
    }
    
    public static Result error() {
        return Result.result(Code.ERROR.code, Code.ERROR.message, null);
    }
    
    public static Result result(Integer code, String message, Object data) {
        Result result = new Result<>();
        return result.setCode(code)
                .setMessage(message)
                .setData(data);
    }
    
    public ResponseEntity<Result> asResponseEntity() {
        return ResponseEntity.ok(this);
    }
    
    public String json() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "{}";
    }
}
