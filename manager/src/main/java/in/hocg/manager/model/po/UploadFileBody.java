package in.hocg.manager.model.po;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by hocgin on 2019/2/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class UploadFileBody {
    private MultipartFile[] files;
}
