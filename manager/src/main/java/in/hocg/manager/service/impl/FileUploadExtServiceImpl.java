package in.hocg.manager.service.impl;

import com.google.common.hash.HashCode;
import in.hocg.manager.constant.CacheKey;
import in.hocg.manager.service.FileRecordService;
import in.hocg.manager.service.FileUploadExtService;
import in.hocg.mybatis.module.basic.entity.FileRecord;
import in.hocg.scaffold.lang.exception.ResponseException;
import in.hocg.scaffold.sdk.oss.OssService;
import in.hocg.scaffold.sdk.oss.result.QueryResult;
import in.hocg.scaffold.support.cache.CacheService;
import in.hocg.util.LangKit;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
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
@Service
@AllArgsConstructor
public class FileUploadExtServiceImpl implements FileUploadExtService {
    private final OssService ossService;
    private final CacheService cacheService;
    private final FileRecordService fileRecordService;
    
    @Override
    public String upload(MultipartFile file, boolean isPublic, String uploader) throws IOException {
        String space = (String) cacheService.get(CacheKey.FILE_UPLOAD_SPACE.name())
                .orElse(CacheKey.FILE_UPLOAD_SPACE.def());
        String storageName = ossService.checkIfNoUpload(file, space);
        HashCode hashCode = LangKit.md5(file.getBytes());
        FileRecord fileRecord = new FileRecord()
                .setOriginName(file.getOriginalFilename())
                .setMd5(hashCode.toString())
                .setPublicity(isPublic)
                .setType(file.getContentType())
                .setUploader(uploader)
                .setSize(file.getSize())
                .setStorageName(storageName)
                .setOss(ossService.type().name());
        fileRecordService.save(fileRecord);
        return fileRecord.getId();
    }
    
    @Override
    public InputStream fetchFile(String storageName) throws Exception {
        String space = space();
        Optional<QueryResult> result = ossService.fetch(space, storageName);
        if (result.isPresent()) {
            return result.get().getInputStream();
        }
        throw ResponseException.wrap(ResponseException.class, "文件不存在");
    }
    
    public String space() {
        return  (String) cacheService.get(CacheKey.FILE_UPLOAD_SPACE.name())
                .orElse(CacheKey.FILE_UPLOAD_SPACE.def());
    }
}
