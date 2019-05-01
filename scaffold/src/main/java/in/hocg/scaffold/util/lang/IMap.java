package in.hocg.scaffold.util.lang;

import java.util.HashMap;
import java.util.Optional;

/**
 * Created by hocgin on 2018/8/20.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class IMap<K, V> {
    private HashMap map = new HashMap<K, V>();
    
    public static IMap get() {
        return new IMap();
    }
    
    public IMap<K, V> put(K key, V value) {
        map.put(key, value);
        return this;
    }
    
    public boolean isEmpty() {
        return map.isEmpty();
    }
    
    public Optional<Object> get(K key) {
        return Optional.ofNullable(map.get(key));
    }
    
    public <T> T get(K key, T def) {
        return ((T) get(key).orElse(def));
    }
}
