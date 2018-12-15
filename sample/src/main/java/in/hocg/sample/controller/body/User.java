package in.hocg.sample.controller.body;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by hocgin on 2018/12/11.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String name;
}
