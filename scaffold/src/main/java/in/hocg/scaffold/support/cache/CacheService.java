package in.hocg.scaffold.support.cache;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author hocgin
 * @date 18-8-20
 **/
public interface CacheService {
    
    void set(Object key, Object value, long timeout, TimeUnit unit);
    
    boolean contains(Object key);
    
    Optional<Object> get(Object key);
    
    void delete(Object key);
    
}
