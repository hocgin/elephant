package in.hocg.manager.manager.impl;

import com.alibaba.druid.util.StringUtils;
import com.google.common.hash.HashCode;
import in.hocg.manager.constant.CacheKey;
import in.hocg.manager.exception.StorageFileNotFoundException;
import in.hocg.manager.manager.FileLinkOSSService;
import in.hocg.manager.model.vo.FileDownload;
import in.hocg.manager.service.FileRecordService;
import in.hocg.manager.service.StaffService;
import in.hocg.mybatis.module.basic.entity.FileRecord;
import in.hocg.scaffold.sdk.oss.OssService;
import in.hocg.scaffold.sdk.oss.result.QueryResult;
import in.hocg.scaffold.support.cache.CacheService;
import in.hocg.util.LangKit;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;
import java.util.Optional;

/**
 * Created by hocgin on 2018/12/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@AllArgsConstructor
public class FileLinkOSSServiceImpl implements FileLinkOSSService {
    private final OssService ossService;
    private final CacheService cacheService;
    private final StaffService staffService;
    private final FileRecordService fileRecordService;
    
    @Override
    public String upload(File file, boolean isPublic, String uploader) throws IOException {
        String space = space();
        String storageName = ossService.checkIfNoUpload(file, space);
        Path path = file.toPath();
        HashCode hashCode = LangKit.md5(Files.readAllBytes(path));
        FileRecord fileRecord = new FileRecord()
                .setOriginName(file.getName())
                .setMd5(hashCode.toString())
                .setPublicity(isPublic)
                .setType(Files.probeContentType(path))
                .setUploader(uploader)
                .setSize(file.length())
                .setStorageName(storageName)
                .setOss(ossService.type().name());
        fileRecordService.save(fileRecord);
        return fileRecord.getId();
    }
    
    @Override
    public Optional<InputStream> fetchFile(String storageName) {
        String space = space();
        Optional<QueryResult> result = ossService.fetch(space, storageName);
        return result.map(QueryResult::getInputStream);
    
    }
    
    @Override
    public FileDownload findFileById(Serializable fileRecordId, Principal principal) throws StorageFileNotFoundException {
        Optional<FileRecord> optionalFileRecord = fileRecordService.fetchNotDeletedForId(fileRecordId);
        if (!optionalFileRecord.isPresent()) {
            throw new StorageFileNotFoundException();
        }
        FileRecord fileRecord = optionalFileRecord.get();
        if (!fileRecord.getPublicity()) {
            String accountId = staffService.getAccountIdOfStaff(principal.getName());
            if (Strings.isBlank(accountId) || !StringUtils.equals(accountId, fileRecord.getUploader())) {
                throw new StorageFileNotFoundException();
            }
        }
    
        Optional<InputStream> streamOptional = fetchFile(fileRecord.getStorageName());
        if (!streamOptional.isPresent()) {
            throw new StorageFileNotFoundException();
        }
        return new FileDownload()
                .setStorageName(fileRecord.getStorageName())
                .setOriginName(fileRecord.getOriginName())
                .setStream(streamOptional.get())
                .setSize(fileRecord.getSize());
    }
    
    /**
     * 命名空间
     * @return
     */
    public String space() {
        return  (String) cacheService.get(CacheKey.FILE_UPLOAD_SPACE.name())
                .orElse(CacheKey.FILE_UPLOAD_SPACE.def());
    }
}
