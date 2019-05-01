package in.hocg.manager.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import in.hocg.manager.exception.StorageFileNotFoundException;
import in.hocg.manager.manager.FileLinkOSSService;
import in.hocg.manager.model.vo.FileDownload;
import in.hocg.manager.service.StaffService;
import in.hocg.manager.service.StorageService;
import in.hocg.scaffold.exception.ResponseException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.security.Principal;
import java.util.Base64;
import java.util.Collection;
import java.util.Map;

/**
 * Created by hocgin on 2019/1/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@AllArgsConstructor
public class StorageServiceImpl implements StorageService {
    private final FileLinkOSSService fileLinkOSSService;
    private final StaffService staffService;
    
    
    @Override
    public FileDownload download(Serializable id, Principal principal) throws StorageFileNotFoundException {
        return fileLinkOSSService.findFileById(id, principal);
    }
    
    @Override
    public String preview(Serializable id, Principal principal) throws StorageFileNotFoundException {
        FileDownload fileRecord = fileLinkOSSService.findFileById(id, principal);
        return fileRecord.getStorageName();
    }
    
    @Override
    public Collection<String> uploadBase64(String[] base64, String username) throws ResponseException, IOException {String accountId = staffService.getAccountIdOfStaff(username);
        if (Strings.isBlank(accountId)) {
            throw ResponseException.wrap(ResponseException.class, "失效用户");
        }
        // 编号:上传后的文件ID
        File tempFile;
        byte[] fileBytes;
        Collection<String> arr = Lists.newArrayList();
        for (String b64 : base64) {
            tempFile = File.createTempFile("uploadBase64.", ".tmp");
            fileBytes = Base64.getDecoder().decode(b64);
            Files.write(tempFile.toPath(), fileBytes);
            String id = fileLinkOSSService.upload(tempFile, true, accountId);
            arr.add(id);
        }
        return arr;
    }
    
    @Override
    public Map<String, String> uploads(MultipartFile[] files, String username) throws ResponseException, IOException {
        String accountId = staffService.getAccountIdOfStaff(username);
        if (Strings.isBlank(accountId)) {
            throw ResponseException.wrap(ResponseException.class, "失效用户");
        }
        
        // 原文件名:上传后的文件ID
        Map<String, String> ids = Maps.newHashMap();
        File tempFile;
        for (MultipartFile file : files) {
            tempFile = File.createTempFile("uploads", file.getOriginalFilename());
            file.transferTo(tempFile);
            String id = fileLinkOSSService.upload(tempFile, true, accountId);
            ids.put(file.getOriginalFilename(), id);
        }
        return ids;
    }
    
    @Override
    public String upload(MultipartFile file, Principal principal) throws ResponseException, IOException {
        String accountId = staffService.getAccountIdOfStaff(principal.getName());
        if (Strings.isBlank(accountId)) {
            throw ResponseException.wrap(ResponseException.class, "失效用户");
        }
        File tempFile = File.createTempFile("upload", file.getOriginalFilename());
        file.transferTo(tempFile);
        return fileLinkOSSService.upload(tempFile, true, accountId);
    }
    
}
