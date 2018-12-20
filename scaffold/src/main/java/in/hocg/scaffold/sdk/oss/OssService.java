package in.hocg.scaffold.sdk.oss;

import in.hocg.scaffold.sdk.oss.result.QueryResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by hocgin on 2018/12/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface OssService {
    
    /**
     * 上传文件
     *
     * @param file 文件
     * @param space 上传空间
     * @return 保存的文件名
     */
    String upload(MultipartFile file, String space) throws IOException;
    
    /**
     * 是否已经存在
     * @param space 上传空间
     * @param filename 保存的文件名
     * @return
     */
    boolean isExist(String space, String filename);
    
    /**
     * 查找文件
     * @param space 上传空间
     * @param filename 保存的文件名
     * @return
     */
    Optional<QueryResult> fetch(String space, String filename);
    
    
    /**
     * 删除文件
     * @param space 上传空间
     * @param filename 保存的文件名
     * @return
     */
    boolean delete(String space, String filename);
}
