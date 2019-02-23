package in.hocg.manager.manager;

import in.hocg.manager.exception.StorageFileNotFoundException;
import in.hocg.manager.model.vo.FileDownload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.Principal;
import java.util.Optional;

/**
 * Created by hocgin on 2018/12/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface FileLinkOSSService {
    
    /**
     * 上传文件
     *
     * @param file
     * @param isPublic
     * @param uploader
     * @return FileRecord ID
     * @throws IOException
     */
    String upload(File file, boolean isPublic, String uploader) throws IOException;
    
    /**
     * 查找文件
     * @param storageName
     * @return 流
     * @throws Exception
     */
    Optional<InputStream> fetchFile(String storageName);
    
    /**
     * 查找文件, 根据文件的权限
     * @param fileRecordId
     * @return
     */
    FileDownload findFileById(Serializable fileRecordId, Principal principal) throws StorageFileNotFoundException;
}
