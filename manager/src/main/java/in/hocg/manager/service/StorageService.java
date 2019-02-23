package in.hocg.manager.service;

import in.hocg.manager.exception.StorageFileNotFoundException;
import in.hocg.manager.model.vo.FileDownload;
import in.hocg.scaffold.lang.exception.ResponseException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;
import java.util.Map;

/**
 * Created by hocgin on 2019/1/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface StorageService {
    
    /**
     * 上传多个文件
     *
     * @param files
     * @param username
     * @return 文件存储ID
     * @throws ResponseException
     * @throws IOException
     */
    Map<String, String> uploads(MultipartFile[] files, String username) throws ResponseException, IOException;
    
    /**
     * 下载文件
     * @param id
     * @param principal
     * @return
     * @throws Exception
     */
    FileDownload download(Serializable id, Principal principal) throws StorageFileNotFoundException;
    
    /**
     * 获取图片
     * @param id
     * @param principal
     * @return
     */
    String preview(Serializable id, Principal principal) throws StorageFileNotFoundException;
    
    /**
     * Base64编码上传
     * @param base64
     * @param username
     * @return
     */
    Collection<String> uploadBase64(String[] base64, String username) throws ResponseException, IOException;
    
    /**
     * 上传文件
     * @param file
     * @param principal
     * @return
     * @throws ResponseException
     * @throws IOException
     */
    String upload(MultipartFile file, Principal principal) throws ResponseException, IOException;
}
