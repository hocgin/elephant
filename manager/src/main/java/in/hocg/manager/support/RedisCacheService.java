package in.hocg.manager.support;

import in.hocg.scaffold.support.cache.CacheService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Created by hocgin on 2018/12/15.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
public class RedisCacheService implements CacheService {
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
