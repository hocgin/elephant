package in.hocg.manager.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * Created by hocgin on 2018/12/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface FileUploadExtService {
    
    /**
     * 上传文件
     *
     * @param file
     * @param isPublic
     * @param uploader
     * @return FileRecord ID
     * @throws IOException
     */
    String upload(MultipartFile file, boolean isPublic, String uploader) throws IOException;
    
    /**
     * 查找文件
     * @param storageName
     * @return 流
     * @throws Exception
     */
    Optional<InputStream> fetchFile(String storageName);
}
