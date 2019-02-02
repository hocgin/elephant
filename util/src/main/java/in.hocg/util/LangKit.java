package in.hocg.util;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;

import java.util.UUID;

/**
 * Created by hocgin on 2018/12/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class LangKit {
    
    /**
     * 获取 MD5 值
     *
     * @param bytes
     * @return
     */
    public static HashCode md5(byte[] bytes) {
        return Hashing.md5().newHasher().putBytes(bytes).hash();
    }
    
    
    /**
     * 生成UUID
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString()
                .replaceAll("-", "");
    }
    
    
}
