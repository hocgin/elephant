package in.hocg.scaffold.sdk.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import in.hocg.scaffold.sdk.oss.result.QueryResult;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by hocgin on 2018/12/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@AllArgsConstructor
public class AliYunOssService implements OssService {
    
    private final OSSClient ossClient;
    
    @Override
    public String checkIfNoUpload(@NonNull MultipartFile file, String space) throws IOException {
        String filename = filename(file);
        if (!isExist(space, filename)) {
            return upload(file, space);
        }
        return filename;
    }
    
    @Override
    public String upload(@NonNull MultipartFile file, String space) throws IOException {
        String filename = filename(file);
        ossClient.putObject(space, filename, file.getInputStream());
        return filename;
    }
    
    @Override
    public boolean isExist(String space, String filename) {
        return ossClient.doesObjectExist(space, filename);
    }
    
    @Override
    public Optional<QueryResult> fetch(String space, String filename) {
        QueryResult result = null;
        if (ossClient.doesObjectExist(space, filename)) {
            OSSObject ossObject = ossClient.getObject(space, filename);
            result = new QueryResult()
                    .setInputStream(ossObject.getObjectContent())
                    .setSpace(space)
                    .setFilename(filename);
        }
        return Optional.ofNullable(result);
    }
    
    @Override
    public boolean delete(String space, String filename) {
        throw new UnsupportedOperationException("不支持文件删除");
//        if (ossClient.doesObjectExist(space, filename)) {
//            ossClient.deleteObject(space, filename);
//        }
//        return true;
    }
    
    @Override
    public OssProperties.Type type() {
        return OssProperties.Type.AliYun;
    }
    
}
