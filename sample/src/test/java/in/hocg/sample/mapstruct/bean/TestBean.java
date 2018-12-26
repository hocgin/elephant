package in.hocg.sample.mapstruct.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * Created by hocgin on 2018/12/26.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class TestBean {
    @Data
    @AllArgsConstructor
    public static class Test1 {
        private String username;
    }
    
    @Data
    @ToString
    public static class Test2 {
        private String username;
    }
}
