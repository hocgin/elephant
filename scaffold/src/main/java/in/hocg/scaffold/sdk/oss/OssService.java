package in.hocg.scaffold.sdk.oss;

import com.google.common.hash.HashCode;
import com.google.common.io.Files;
import in.hocg.scaffold.sdk.oss.result.QueryResult;
import in.hocg.util.LangKit;
import lombok.SneakyThrows;

import java.io.File;
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
     * 获取文件名
     *
     * @param file
     * @return
     */
    @SneakyThrows
    default String filename(File file) {
        String prefix = Files.getFileExtension(file.getName());
        HashCode hashCode = LangKit.md5(Files.toByteArray(file));
        return String.format("%s.%s", hashCode.toString(), prefix);
    }
    
    /**
     * 上传文件前，检查是否已经上传
     *
     * @param file  文件
     * @param space 上传空间
     * @return 保存的文件名
     * @throws IOException
     */
    String checkIfNoUpload(File file, String space) throws IOException;
    
    /**
     * 上传文件
     *
     * @param file  文件
     * @param space 上传空间
     * @return 保存的文件名
     * @throws IOException
     */
    String upload(File file, String space) throws IOException;
    
    
    /**
     * 是否已经存在
     *
     * @param space    上传空间
     * @param filename 保存的文件名
     * @return 是否存在
     */
    boolean isExist(String space, String filename);
    
    /**
     * 查找文件
     *
     * @param space    上传空间
     * @param filename 保存的文件名
     * @return 查找结果
     */
    Optional<QueryResult> fetch(String space, String filename);
    
    
    /**
     * 删除文件
     *
     * @param space    上传空间
     * @param filename 保存的文件名
     * @return 是否删除成功
     */
    boolean delete(String space, String filename);
    
    OssProperties.Type type();
}
