package in.hocg.scaffold.util;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Map;

/**
 * Created by hocgin on 2018/12/27.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AntiSQL {
    private static final String[] keyWords = {";", "\"", "\'", "/*", "*/", "--", "exec",
            "select", "update", "delete", "insert",
            "alter", "drop", "create", "shutdown"};
    
    public static Map<String, Collection<String[]>> getSafeParameterMap(Map<String, String[]> parameterMap) {
        SetMultimap<String, String[]> builder = HashMultimap.create();
        for (String key : parameterMap.keySet()) {
            String[] oldValues = parameterMap.get(key);
            builder.put(key, getSafeValues(oldValues));
        }
        return builder.asMap();
    }
    
    
    public static String[] getSafeValues(String[] oldValues) {
        
        if (oldValues != null && oldValues.length != 0) {
            String[] newValues = new String[oldValues.length];
            for (int i = 0; i < oldValues.length; i++) {
                newValues[i] = getSafeValue(oldValues[i]);
            }
            return newValues;
        }
        return null;
    }
    
    public static String getSafeValue(String oldValue) {
        StringBuilder sb = new StringBuilder(oldValue);
        String lowerCase = oldValue.toLowerCase();
        for (String keyWord : keyWords) {
            int x;
            while ((x = lowerCase.indexOf(keyWord)) >= 0) {
                if (keyWord.length() == 1) {
                    sb.replace(x, x + 1, " ");
                    lowerCase = sb.toString().toLowerCase();
                    continue;
                }
                sb.deleteCharAt(x + 1);
                lowerCase = sb.toString().toLowerCase();
            }
        }
        return sb.toString();
    }
    
}
