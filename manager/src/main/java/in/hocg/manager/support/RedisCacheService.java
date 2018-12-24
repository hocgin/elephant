package in.hocg.manager.support;

import in.hocg.scaffold.support.cache.CacheService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.*;
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
@AllArgsConstructor
public class RedisCacheService implements CacheService {
    
    private final RedisTemplate redisTemplate;
    
    @Override
    public void set(Object key, Object value, long timeout, TimeUnit unit) {
        opsForValue().set(key, value, timeout, unit);
    }
    
    @Override
    public boolean contains(Object key) {
        return redisTemplate.hasKey(key);
    }
    
    @Override
    public Optional<Object> get(Object key) {
        return Optional.ofNullable(opsForValue().get(key));
    }
    
    @Override
    public void delete(Object key) {
        redisTemplate.delete(key);
    }
    
    /**
     * 针对 K-V 结构
     *
     * @return
     */
    private ValueOperations opsForValue() {
        return redisTemplate.opsForValue();
    }
    
    /**
     * 针对 Set 结构
     *
     * @return
     */
    private SetOperations opsForSet() {
        return redisTemplate.opsForSet();
    }
    
    /**
     * 针对 List 结构
     *
     * @return
     */
    private ListOperations opsForList() {
        return redisTemplate.opsForList();
    }
    
    /**
     * 针对 Map 结构
     *
     * @return
     */
    private HashOperations opsForHash() {
        return redisTemplate.opsForHash();
    }
}
