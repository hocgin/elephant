package in.hocg.manager.model.po;

import in.hocg.scaffold.support.basis.parameter.BaseParameter;
import lombok.Data;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by hocgin on 2019/1/26.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class UpdateRole implements BaseParameter {
    private String name;
    private String mark;
    private String description;
    
    private Collection<String> resources = Collections.emptyList();
}
