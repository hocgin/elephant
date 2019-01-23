package in.hocg.manager.service;

import in.hocg.manager.model.vo.FileDownload;
import in.hocg.scaffold.lang.exception.ResponseException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

/**
 * Created by hocgin on 2019/1/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface FileService {
    
    /**
     * 上传多个文件
     *
     * @param files
     * @param username
     * @return 文件存储ID
     * @throws ResponseException
     * @throws IOException
     */
    Map<String, String> upload(MultipartFile[] files, String username) throws ResponseException, IOException;
    
    /**
     * 下载文件
     * @param id
     * @param username
     * @return
     * @throws Exception
     */
    Optional<FileDownload> download(Serializable id, String username);
    
    /**
     * 获取图片
     * @param id
     * @param username
     * @return
     */
    Optional<String> preview(Serializable id, String username);
}
