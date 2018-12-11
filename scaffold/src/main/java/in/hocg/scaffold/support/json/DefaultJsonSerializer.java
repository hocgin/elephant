package in.hocg.scaffold.support.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.hocg.scaffold.support.json.annotation.JSON;
import in.hocg.scaffold.support.json.filter.JsonFilterProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.ResponseEntity;

/**
 * Created by hocgin on 2018/9/2.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
public class DefaultJsonSerializer implements JsonSerializer {
    ObjectMapper mapper = new ObjectMapper();
    JsonFilterProvider jacksonFilter = new JsonFilterProvider();
    
    /**
     * @param clazz   target type
     * @param include include fields
     * @param filter  filter fields
     */
    public void filter(Class<?> clazz, String include, String filter) {
        if (clazz == null) {
            return;
        }
        // 需要导入字段
        if (Strings.isNotBlank(include)) {
            jacksonFilter.include(clazz, include.split(","));
        }
        
        // 不需要导入字段
        if (Strings.isNotBlank(filter)) {
            jacksonFilter.exclude(clazz, filter.split(","));
        }
        mapper.addMixIn(clazz, jacksonFilter.getClass());
    }
    
    @Override
    public String toJson(Object object) {
        mapper.setFilterProvider(jacksonFilter);
        if (object instanceof ResponseEntity) {
            object = ((ResponseEntity) object).getBody();
        }
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    @Override
    public void filter(JSON json) {
        this.filter(json.type(), json.include(), json.exclude());
    }
}
