package in.hocg.manager.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.InputStream;

/**
 * Created by hocgin on 2019/1/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class FileDownload {
    private InputStream stream;
    private Long size;
    private String originName;
}
