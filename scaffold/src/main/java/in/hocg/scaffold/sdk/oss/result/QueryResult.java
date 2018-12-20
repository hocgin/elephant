package in.hocg.scaffold.sdk.oss.result;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by hocgin on 2018/12/20.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Accessors(chain = true)
@Data
public class QueryResult implements Serializable {
    private String space;
    private String filename;
    private InputStream inputStream;
}
