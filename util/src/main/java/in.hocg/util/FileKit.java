package in.hocg.util;


import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by hocgin on 2018/12/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Log
@UtilityClass
public class FileKit {
    /**
     * 压缩文件
     *
     * @param zipFilePath 压缩文件名
     * @param joinPaths   待压缩文件
     */
    public static void compress(Path zipFilePath, Path... joinPaths) {
        byte[] bytes = new byte[1024];
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(zipFilePath.toFile());
                ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream)
        ) {
            for (Path path : joinPaths) {
                File file = path.toFile();
                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zipOutputStream.putNextEntry(zipEntry);
                    int length;
                    while ((length = fileInputStream.read(bytes)) >= 0) {
                        zipOutputStream.write(bytes, 0, length);
                    }
                }
            }
        } catch (IOException e) {
            log.warning("压缩出现异常");
        }
    }
}
