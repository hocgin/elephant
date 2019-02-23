package in.hocg.manager.controller;

import in.hocg.manager.exception.StorageFileNotFoundException;
import in.hocg.manager.model.po.UploadBase64Body;
import in.hocg.manager.model.vo.FileDownload;
import in.hocg.manager.service.StorageService;
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

import java.net.URI;
import java.security.Principal;
import java.util.Collection;
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
@RequestMapping("/api/v1/files")
@AllArgsConstructor
public class FileController extends BaseController {
    private final StorageService storageService;
    
    /**
     * 文件上传接口
     * POST /uploads
     *
     * @param files     请求Body
     * @param principal 需要登陆
     * @return
     */
    @ILog
    @PostMapping("/uploads")
    public ResponseEntity uploads(@RequestParam("file") MultipartFile[] files,
                                  Principal principal) throws Exception {
        String username = principal.getName();
        if (Strings.isBlank(username)) {
            return Result.error("请先进行登陆")
                    .asResponseEntity();
        }
        Map<String, String> ids = storageService.uploads(files, username);
        return Result.success(ids)
                .asResponseEntity();
    }
    
    
    @PostMapping("/upload")
    public ResponseEntity upload(@RequestParam("file") MultipartFile file,
                                 Principal principal) throws Exception {
        String id = storageService.upload(file, principal);
        return Result.success(id)
                .asResponseEntity();
    }
    
    /**
     * base 64 编码 上传文件
     *
     * @param body      请求体
     * @param principal
     * @return
     * @throws Exception
     */
    @ILog
    @PostMapping("/upload/base64")
    public ResponseEntity uploadBase64(@RequestBody UploadBase64Body body,
                                       Principal principal) throws Exception {
        String username = principal.getName();
        if (Strings.isBlank(username)) {
            return Result.error("请先进行登陆")
                    .asResponseEntity();
        }
        Collection<String> ids = storageService.uploadBase64(body.getBase64s(), username);
        return Result.success(ids)
                .asResponseEntity();
    }
    
    /**
     * 下载文件
     * GET /download/{id}?rename=${rename}
     *
     * @param id        文件ID
     * @param rename    可选, 重命名
     * @param principal 可选, 访问私有文件需要登陆
     * @return
     */
    @GetMapping("/download/{id}")
    public ResponseEntity download(@PathVariable String id,
                                   @RequestParam(value = "rename", required = false) String rename,
                                   Principal principal) throws StorageFileNotFoundException {
        String username = principal.getName();
        
        if (Strings.isBlank(username)) {
            return ResponseEntity.notFound().build();
        }
        FileDownload download = storageService.download(id, principal);
        return ResponseEntity
                .ok()
                .headers(new HttpHeaders() {{
                    add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
                    add(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", Optional.of(rename).orElse(download.getOriginName())));
                    add(HttpHeaders.PRAGMA, "no-cache");
                    add(HttpHeaders.EXPIRES, "0");
                }})
                .contentLength(download.getSize())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(download.getStream()));
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
                                Principal principal) throws StorageFileNotFoundException {
        String optionalStorageName = storageService.preview(id, principal);
        String url = buildImageURL(optionalStorageName, width, height);
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
