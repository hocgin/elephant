package in.hocg.manager.controller;

import com.alibaba.druid.util.StringUtils;
import com.google.common.collect.Maps;
import in.hocg.manager.service.FileRecordService;
import in.hocg.manager.service.FileUploadExtService;
import in.hocg.manager.service.StaffService;
import in.hocg.mybatis.module.basic.entity.FileRecord;
import in.hocg.scaffold.lang.exception.ResponseException;
import in.hocg.scaffold.support.aspect.log.ILog;
import in.hocg.scaffold.support.basis.BaseController;
import in.hocg.scaffold.support.http.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URI;
import java.security.Principal;
import java.util.Map;
import java.util.Optional;

/**
 * Created by hocgin on 2018/12/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Controller
@RequestMapping
@AllArgsConstructor
public class FileController extends BaseController {
    private final FileUploadExtService fileUploadExtService;
    private final StaffService staffService;
    private final FileRecordService fileRecordService;
    
    /**
     * 文件上传接口
     * POST /upload
     *
     * @param files     上传文件[]
     * @param principal 需要登陆
     * @return
     */
    @ILog
    @PostMapping("/upload")
    public ResponseEntity upload(@RequestParam("file") MultipartFile[] files,
                                 Principal principal) throws Exception {
        String accountId = staffService.getAccountIdOfStaff(principal);
        if (Strings.isBlank(accountId)) {
            throw ResponseException.wrap(ResponseException.class, "失效用户");
        }
        
        Map<String, String> ids = Maps.newHashMap();
        for (MultipartFile file : files) {
            String id = fileUploadExtService.upload(file, true, accountId);
            ids.put(file.getOriginalFilename(), id);
        }
        return ResponseEntity.ok(Result.success(ids));
    }
    
    /**
     * 下载文件
     * GET /download/{id}?rename=${rename}
     *
     * @param id        文件ID
     * @param rename    可选, 重命名
     * @param principal 可选, 访问私有文件需要登陆
     * @return
     * @throws Exception
     */
    @GetMapping("/download/{id}")
    public ResponseEntity download(@PathVariable String id,
                                   @RequestParam(value = "rename", required = false) String rename,
                                   Principal principal) {
        InputStream inputStream;
        Optional<FileRecord> optionalFileRecord = fileRecordService.fetchNotDeletedForId(id);
        if (!optionalFileRecord.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        FileRecord fileRecord = optionalFileRecord.get();
        try {
            if (!fileRecord.isPublicity()) {
                String accountId = staffService.getAccountIdOfStaff(principal);
                if (Strings.isBlank(accountId) || !StringUtils.equals(accountId, fileRecord.getUploader())) {
                    return ResponseEntity.notFound().build();
                }
            }
            inputStream = fileUploadExtService.fetchFile(fileRecord.getStorageName());
            
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .ok()
                .headers(new HttpHeaders() {{
                    add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
                    add(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", StringUtils.isEmpty(rename) ? fileRecord.getOriginName() : rename));
                    add(HttpHeaders.PRAGMA, "no-cache");
                    add(HttpHeaders.EXPIRES, "0");
                }})
                .contentLength(fileRecord.getSize())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(inputStream));
    }
    
    /**
     * 访问图片
     * GET /image/{id}?width=${width}&height=${height}
     *
     * @param id        文件ID
     * @param width     可选, 调整宽度
     * @param height    可选, 调整高度
     * @param principal 可选, 访问私有图片需要登陆
     * @return
     */
    @GetMapping("/image/{id}")
    public ResponseEntity image(@PathVariable String id,
                                @RequestParam(value = "width", required = false) Integer width,
                                @RequestParam(value = "height", required = false) Integer height,
                                Principal principal) {
        Optional<FileRecord> optionalFileRecord = fileRecordService.fetchNotDeletedForId(id);
        if (!optionalFileRecord.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        FileRecord fileRecord = optionalFileRecord.get();
        
        if (!fileRecord.isPublicity()) {
            try {
                String accountId = staffService.getAccountIdOfStaff(principal);
                if (Strings.isBlank(accountId) || !StringUtils.equals(accountId, fileRecord.getUploader())) {
                    return ResponseEntity.notFound().build();
                }
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }
        
        String url = buildImageURL(fileRecord.getStorageName(), width, height);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }
    
    /**
     * https://daigou-test.oss-cn-beijing.aliyuncs.com/da82a5bcba2836340e8c5857b92204aa.png?x-oss-process=image/resize,w_1200
     *
     * @param uri    相对地址
     * @param width
     * @param height
     * @return
     */
    private String buildImageURL(String uri, Integer width, Integer height) {
        String HOST = "https://daigou-test.oss-cn-beijing.aliyuncs.com";
        String w = Optional.ofNullable(width)
                .map(item -> String.format(",w_%d", item))
                .orElse("");
        String h = Optional.ofNullable(height)
                .map(item -> String.format(",h_%d", item))
                .orElse("");
        
        return String.format("%s/%s%s%s%s", HOST, uri, w.isEmpty() && h.isEmpty() ? "" : "?x-oss-process=image/resize", w, h);
    }
    
    
}
