package in.hocg.manager.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.google.common.collect.Maps;
import in.hocg.manager.model.vo.FileDownload;
import in.hocg.manager.service.FileRecordService;
import in.hocg.manager.service.FileService;
import in.hocg.manager.service.FileUploadExtService;
import in.hocg.manager.service.StaffService;
import in.hocg.mybatis.module.basic.entity.FileRecord;
import in.hocg.scaffold.lang.exception.ResponseException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

/**
 * Created by hocgin on 2019/1/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRecordService fileRecordService;
    private final FileUploadExtService fileUploadExtService;
    private final StaffService staffService;
    
    
    @Override
    public Optional<FileDownload> download(Serializable id, String username) {
        Optional<FileRecord> optionalFileRecord = fileRecordService.fetchNotDeletedForId(id);
        if (!optionalFileRecord.isPresent()) {
            return Optional.empty();
        }
        FileRecord fileRecord = optionalFileRecord.get();
        if (!fileRecord.getPublicity()) {
            String accountId = staffService.getAccountIdOfStaff(username);
            if (Strings.isBlank(accountId) || !StringUtils.equals(accountId, fileRecord.getUploader())) {
                return Optional.empty();
            }
        }
        Optional<InputStream> streamOptional = fileUploadExtService.fetchFile(fileRecord.getStorageName());
        return streamOptional.map(inputStream -> new FileDownload()
                .setSize(fileRecord.getSize())
                .setStream(inputStream)
                .setOriginName(fileRecord.getOriginName())
        );
    
    }
    
    @Override
    public Optional<String> preview(Serializable id, String username) {
    
        Optional<FileRecord> optionalFileRecord = fileRecordService.fetchNotDeletedForId(id);
        if (!optionalFileRecord.isPresent()) {
            return Optional.empty();
        }
        FileRecord fileRecord = optionalFileRecord.get();
    
        if (!fileRecord.getPublicity()) {
    
            String accountId = staffService.getAccountIdOfStaff(username);
            if (Strings.isBlank(accountId) || !StringUtils.equals(accountId, fileRecord.getUploader())) {
                return Optional.empty();
            }
        }
        return Optional.of(fileRecord.getStorageName());
    }
    
    @Override
    public Map<String, String> upload(MultipartFile[] files, String username) throws ResponseException, IOException {
        String accountId = staffService.getAccountIdOfStaff(username);
        if (Strings.isBlank(accountId)) {
            throw ResponseException.wrap(ResponseException.class, "失效用户");
        }
        
        // 原文件名:上传后的文件ID
        Map<String, String> ids = Maps.newHashMap();
        for (MultipartFile file : files) {
            String id = fileUploadExtService.upload(file, true, accountId);
            ids.put(file.getOriginalFilename(), id);
        }
        return ids;
    }
}
