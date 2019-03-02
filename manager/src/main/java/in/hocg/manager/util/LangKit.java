package in.hocg.manager.util;

import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by hocgin on 2019/1/1.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class LangKit {
    
    
    /**
     * 返回实例中值为 null 的字段名
     * @param source
     * @return
     */
    public static String[] getNullValueFields(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Arrays.stream(src.getPropertyDescriptors())
                .filter((pd) -> Objects.isNull(src.getPropertyValue(pd.getName())))
                .map(FeatureDescriptor::getName)
                .toArray(String[]::new);
    }
    
    /**
     * 草鸡管理员
     * @param username
     * @return
     */
    public static boolean isSupperMan(String username) {
        return "admin".equals(username);
    }
    
}
