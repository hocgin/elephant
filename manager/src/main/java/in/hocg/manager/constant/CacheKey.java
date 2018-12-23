package in.hocg.manager.constant;

/**
 * Created by hocgin on 2018/12/23.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public enum CacheKey {
    FILE_UPLOAD_SPACE("文件上传空间", "daigou-test");
    String description;
    Object defaultValue;
    
    CacheKey(String description, Object defaultValue) {
        this.description = description;
        this.defaultValue = defaultValue;
    }
    
    public Object def() {
        return this.defaultValue;
    }
}
