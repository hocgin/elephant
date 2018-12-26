package in.hocg.sample.mapstruct;

import in.hocg.sample.mapstruct.bean.TestBean;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Created by hocgin on 2018/12/26.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper
public interface TestMapping {
    TestMapping INSTANCE = Mappers.getMapper(TestMapping.class);
    
    
    @Mappings({
            @Mapping(source = "username", target = "username")
    })
    TestBean.Test2 t1ToT2(TestBean.Test1 test1);
}
