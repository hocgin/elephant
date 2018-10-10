package in.hocg;

import in.hocg.scaffold.support.cache.CacheService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author hocgin
 * @date 18-10-10
 **/
@Component
public class DefaultCache implements CacheService {
    @Override
    public void set(Object key, Object value, long timeout, TimeUnit unit) {
    
    }
    
    @Override
    public boolean contains(Object key) {
        return false;
    }
    
    @Override
    public Optional<Object> get(Object key) {
        return Optional.empty();
    }
    
    @Override
    public void delete(Object key) {
    
    }
}
